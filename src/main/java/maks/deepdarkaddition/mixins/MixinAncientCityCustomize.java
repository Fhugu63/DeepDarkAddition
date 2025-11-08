package maks.deepdarkaddition.mixins;

import maks.deepdarkaddition.MainScript;
import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

@Mixin(Warden.class)
public abstract class MixinAncientCityCustomize extends Monster {
    protected MixinAncientCityCustomize(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //@Inject(method = "tick", at = @At("FIELD"))
    public void onTick() {
        System.out.println("dda_succesfull");
        super.tick();
    }
}
