package maks.deepdarkaddition.entity.client

import maks.deepdarkaddition.MainScript
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.resources.ResourceLocation

object ModModelLayers {
    val HUNGRY_SOUL_LAYER = ModelLayerLocation(
        ResourceLocation.tryBuild(MainScript.MOD_ID, "hungrysoulentity_layer"), "main")
}