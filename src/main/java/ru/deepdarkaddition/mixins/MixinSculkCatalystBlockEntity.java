package ru.deepdarkaddition.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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
    @Inject(method = "handleGameEvent", at = @At("HEAD"), cancellable = true)
    private void onHandleGameEvent(ServerLevel pLevel, GameEvent pGameEvent, GameEvent.Context pContext, Vec3 pPos, CallbackInfoReturnable<Boolean> cir) {
        if (pGameEvent == GameEvent.ENTITY_DIE) {
            Entity sourceEntity = pContext.sourceEntity();
            if (sourceEntity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) sourceEntity;
                if (!livingEntity.wasExperienceConsumed()) {
                    if (sourceEntity.getType() == EntityType.CREEPER) {
                        Entity myEntity = ModEntities.SCULKCREEPERENTITY.get().create(sourceEntity.level());
                        if (myEntity != null) {
                            myEntity.moveTo(sourceEntity.position().x, sourceEntity.position().y, sourceEntity.position().z);
                            sourceEntity.level().addFreshEntity(myEntity);
                        }
                    }
                }
            }
        }

        cir.setReturnValue(true);
        cir.cancel();
    }
}
