package maks.deepdarkaddition.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import maks.deepdarkaddition.MainScript;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.AncientCityStructurePools;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Warden.class)
public abstract class MixinAncientCityCustomize {
    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick() {
        MainScript.LOGGER.info("dda_succesful");
    }
}
