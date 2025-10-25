package maks.deepdarkaddition.events

import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.ModEntities
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod


@Mod.EventBusSubscriber(modid = MainScript.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
class ModEventBusEvents {
    @SubscribeEvent
    fun registerAttributes(event: EntityAttributeCreationEvent) {
        event.put(ModEntities.HUNGRYSOULENTITY.get(), HungrySoulEntity.createAttributes().build())
    }
}