package ru.deepdarkaddition.entity.client

import ru.deepdarkaddition.MainScript
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.resources.ResourceLocation

object ModModelLayers {
    val HUNGRY_SOUL_LAYER = ModelLayerLocation(
        ResourceLocation.tryBuild(MainScript.MOD_ID, "hungrysoulentity_layer"), "main")

    val SCULK_CREEPER_LAYER = ModelLayerLocation(
        ResourceLocation.tryBuild(MainScript.MOD_ID, "sculkcreeper_layer"), "main")
}