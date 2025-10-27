package maks.deepdarkaddition.events

import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.ModEntities
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import maks.deepdarkaddition.item.ModItems
import net.minecraft.client.Minecraft
import net.minecraft.core.SectionPos.z
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import java.util.*


class ModEvents {
    val minecraft = Minecraft.getInstance()

    var player = minecraft.level?.getPlayerByUUID(UUID.fromString(""))

    @SubscribeEvent
    fun attackEntity(event: AttackEntityEvent) {
        val entity = event.target

        if (entity.type == EntityType.WARDEN) {
            MainScript.LOGGER.info("warden is attacked")
            player = event.entity
        }
    }

    @SubscribeEvent
    fun dropItemFromEntity(event: LivingDeathEvent) {
        val entity = event.entity

        if (entity.type == EntityType.WARDEN) {
            MainScript.LOGGER.info("warden is died")


        }
    }
}