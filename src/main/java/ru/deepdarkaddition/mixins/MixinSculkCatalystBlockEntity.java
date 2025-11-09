package ru.deepdarkaddition.mixins;

import ru.deepdarkaddition.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SculkCatalystBlockEntity.CatalystListener.class)
public abstract class MixinSculkCatalystBlockEntity implements GameEventListener {

    @Shadow
    private void tryAwardItSpreadsAdvancement(Level pLevel, LivingEntity pEntity) {}

    @Shadow
    SculkSpreader sculkSpreader;

    @Shadow
    private BlockState blockState;

    @Shadow
    private PositionSource positionSource;

    //@Shadow
    //private void bloom(ServerLevel pLevel, BlockPos pPos, BlockState pState, RandomSource pRandom) {}

    @Overwrite
    public boolean handleGameEvent(ServerLevel pLevel, GameEvent pGameEvent, GameEvent.Context pContext, Vec3 pPos) {
        if (pGameEvent == GameEvent.ENTITY_DIE) {
            Entity var6 = pContext.sourceEntity();
            if (var6 instanceof LivingEntity) {
                LivingEntity $$4 = (LivingEntity) var6;
                if (!$$4.wasExperienceConsumed()) {
                    int $$5 = $$4.getExperienceReward();
                    if ($$4.shouldDropExperience() && $$5 > 0) {
                        this.sculkSpreader.addCursors(BlockPos.containing(pPos.relative(Direction.UP, (double) 0.5F)), $$5);
                        this.tryAwardItSpreadsAdvancement(pLevel, $$4);
                    }
                    if (var6.getType() == EntityType.CREEPER) {
                        Entity myEntity = ModEntities.SCULKCREEPERENTITY.get().create(var6.level());
                        myEntity.moveTo(var6.position().x, var6.position().y, var6.position().z);

                        var6.level().addFreshEntity(myEntity);
                    }
                    $$4.skipDropExperience();
                    this.positionSource.getPosition(pLevel).ifPresent((p_289513_) -> this.bloom(pLevel, BlockPos.containing(p_289513_), this.blockState, pLevel.getRandom()));
                }

                return true;
            }
        }

        return false;
    }
    @Overwrite
    public void bloom(ServerLevel pLevel, BlockPos pPos, BlockState pState, RandomSource pRandom) {
        pLevel.setBlock(pPos, (BlockState)pState.setValue(SculkCatalystBlock.PULSE, true), 3);
        pLevel.scheduleTick(pPos, pState.getBlock(), 8);
        pLevel.sendParticles(ParticleTypes.SCULK_SOUL, (double)pPos.getX() + (double)0.5F, (double)pPos.getY() + 1.15, (double)pPos.getZ() + (double)0.5F, 2, 0.2, (double)0.0F, 0.2, (double)0.0F);
        pLevel.playSound((Player)null, pPos, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.BLOCKS, 2.0F, 0.6F + pRandom.nextFloat() * 0.4F);
    }
}
