package ru.deepdarkaddition.mixins;

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

@Mixin(Warden.class)
public abstract class MixinWarden extends Monster {
    protected MixinWarden(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Overwrite
    public boolean canRide(Entity pVehicle) {
        System.out.println("dda:It work!");
        return true;
    }

    @Overwrite
    public boolean doHurtTarget(Entity pEntity) {
        if (pEntity.getType() != ModEntities.HUNGRYSOULENTITY.get() || pEntity.getType() != ModEntities.SCULKCREEPERENTITY.get()) {
            this.level().broadcastEntityEvent(this, (byte)4);
            this.playSound(SoundEvents.WARDEN_ATTACK_IMPACT, 10.0F, this.getVoicePitch());
            SonicBoom.setCooldown(this, 40);
            return super.doHurtTarget(pEntity);
        } else { return false; }
    }
}
