package maks.deepdarkaddition.mixins;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Warden.class)
public abstract class MixinAncientCityCustomize {
    @Inject(method = "canRide", at = @At("TAIL"))
    protected void onCanRide(Entity pVehicle) {
        System.out.println("dda_succesfull");
    }
}
