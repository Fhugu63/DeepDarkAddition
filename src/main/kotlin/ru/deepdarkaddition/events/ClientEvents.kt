package ru.deepdarkaddition.events

import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.client.HungrySoul.HungrySoulModel
import net.minecraft.world.entity.Entity
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import ru.deepdarkaddition.entity.client.ModModelLayers
import ru.deepdarkaddition.entity.client.SculkCreeper.SculkCreeperModel
import net.minecraftforge.client.event.EntityRenderersEvent

@Mod.EventBusSubscriber(modid = MainScript.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = arrayOf(Dist.CLIENT))
object ClientEvents {
    @SubscribeEvent
    fun registerLayerDefinitions(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(ModModelLayers.HUNGRY_SOUL_LAYER, HungrySoulModel<Entity>::createBodyLayer)
        event.registerLayerDefinition(ModModelLayers.SCULK_CREEPER_LAYER, SculkCreeperModel<Entity>::createBodyLayer)
    }
}