package ru.deepdarkaddition.mixins;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.deepdarkaddition.entity.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;

@Mixin(Warden.class)
public abstract class MixinWarden implements VibrationSystem {

    @Inject(method = "canTargetEntity", at = @At("HEAD"), cancellable = true)
    @Contract("null->false")
    private void canTargetEntity(@Nullable Entity entity, CallbackInfoReturnable<Boolean> cir) {
        Boolean returnValue = false;
        if (entity.getType() == ModEntities.HUNGRYSOULENTITY.get() || entity.getType() == ModEntities.SCULKCREEPERENTITY.get()) {
            returnValue = true;
        }
        cir.setReturnValue(returnValue);

        cir.cancel();
    }
}