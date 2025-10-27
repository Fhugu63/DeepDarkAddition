package maks.deepdarkaddition.events

import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.ModEntities
import maks.deepdarkaddition.entity.client.HungrySoul.HungrySoulModel
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import maks.deepdarkaddition.item.ModItems
import net.maks.deepdarkaddition.entity.client.luiza.HungrySoulRender
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.CreativeModeTabs
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.maks.deepdarkaddition.entity.client.ModModelLayers
import net.minecraftforge.client.event.EntityRenderersEvent

@Mod.EventBusSubscriber(modid = MainScript.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = arrayOf(Dist.CLIENT))
class ClientEvents {
    @SubscribeEvent
    fun registerLayer(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(ModModelLayers.HUNGRY_SOUL_LAYER, HungrySoulModel<HungrySoulEntity>::createBodyLayer)
    }
}