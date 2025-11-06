package maks.deepdarkaddition.mixins;

import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Warden.class)
public abstract class MixinAncientCityCustomize {
    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick() {
        System.out.println("dda_succesfull");
    }
}
