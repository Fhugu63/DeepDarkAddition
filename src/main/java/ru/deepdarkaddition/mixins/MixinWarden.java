package ru.deepdarkaddition.mixins;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Contract;
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
    @Contract("null->false")
    public boolean canTargetEntity(@Nullable Entity p_219386_) {
        boolean var10000;
        if (p_219386_ instanceof LivingEntity $$1) {
            if (this.level() == p_219386_.level() && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(p_219386_) && !this.isAlliedTo(p_219386_) &&
                    $$1.getType() != EntityType.ARMOR_STAND && $$1.getType() != EntityType.WARDEN && !$$1.isInvulnerable() &&
                    !$$1.isDeadOrDying() && this.level().getWorldBorder().isWithinBounds($$1.getBoundingBox()) &&
                    $$1.getType() != ModEntities.SCULKCREEPERENTITY.get() && $$1.getType() != ModEntities.HUNGRYSOULENTITY.get()) {

                var10000 = true;
                return var10000;
            }
        }

        var10000 = false;
        return var10000;
    }
}
