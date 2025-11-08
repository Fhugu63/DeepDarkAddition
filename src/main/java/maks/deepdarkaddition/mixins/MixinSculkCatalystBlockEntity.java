package maks.deepdarkaddition.mixins;

import maks.deepdarkaddition.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(SculkCatalystBlockEntity.class)
public class MixinSculkCatalystBlockEntity extends BaseEntityBlock implements GameEventListener.Holder<SculkCatalystBlockEntity.CatalystListener> {
    @Shadow
    private SculkCatalystBlockEntity.CatalystListener catalystListener;

    @Shadow
    public static BooleanProperty PULSE;

    @Shadow
    SculkSpreader sculkSpreader;

    @Shadow
    private void tryAwardItSpreadsAdvancement(Level pLevel, LivingEntity pEntity) {
    }

    @Shadow
    private PositionSource positionSource;

    @Shadow
    private BlockState blockState;

    @Shadow
    private void bloom(ServerLevel pLevel, BlockPos pPos, BlockState pState, RandomSource pRandom) {
    }

    public MixinSculkCatalystBlockEntity(Properties p_222090_) {
        super(p_222090_);
        this.registerDefaultState((BlockState) ((BlockState) this.stateDefinition.any()).setValue(PULSE, false));
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos p_222117_, BlockState p_222118_) {
        return new SculkCatalystBlockEntity(p_222117_, p_222118_);
    }

    @Overwrite
    public boolean handleGameEvent(ServerLevel pLevel, GameEvent pGameEvent, GameEvent.Context pContext, Vec3 pPos) {
        System.out.println("dda:sucesfull2!");
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


    @Override
    public SculkCatalystBlockEntity.CatalystListener getListener() {
        return this.catalystListener;
    }
}
