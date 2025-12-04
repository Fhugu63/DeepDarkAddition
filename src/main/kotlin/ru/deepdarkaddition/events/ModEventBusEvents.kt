package ru.deepdarkaddition.events

import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.ModEntities
import ru.deepdarkaddition.entity.custom.HungrySoulEntity
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity


@Mod.EventBusSubscriber(modid = MainScript.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModEventBusEvents {
    @SubscribeEvent
    fun registerAttributes(event: EntityAttributeCreationEvent) {
        event.put(ModEntities.HUNGRYSOULENTITY.get(), HungrySoulEntity.createAttributes().build())
        event.put(ModEntities.SCULKCREEPERENTITY.get(), SculkCreeperEntity.createAttributes().build())
    }
}