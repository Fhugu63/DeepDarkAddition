package ru.deepdarkaddition.events

import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.client.HungrySoul.HungrySoulModel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import ru.deepdarkaddition.entity.client.ModModelLayers
import ru.deepdarkaddition.entity.client.SculkCreeper.SculkCreeperModel
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.event.TickEvent
import ru.deepdarkaddition.item.ModItems

@Mod.EventBusSubscriber(modid = MainScript.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = arrayOf(Dist.CLIENT))
object ClientEvents {
    @SubscribeEvent
    fun registerLayerDefinitions(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(ModModelLayers.HUNGRY_SOUL_LAYER, HungrySoulModel<Entity>::createBodyLayer)
        event.registerLayerDefinition(ModModelLayers.SCULK_CREEPER_LAYER, SculkCreeperModel<Entity>::createBodyLayer)
    }



}