package maks.deepdarkaddition.events

import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.ModEntities
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import maks.deepdarkaddition.item.ModItems
import net.minecraft.client.Minecraft
import net.minecraft.core.SectionPos.z
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraftforge.event.CommandEvent
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import java.util.*


class ModEvents {
    val minecraft = Minecraft.getInstance()

    var player = minecraft.level?.getPlayerByUUID(UUID.fromString(""))

    var flagSpawnSoul = true

    val logger = MainScript.LOGGER

    @SubscribeEvent
    fun attackEntity(event: AttackEntityEvent) {
        val entity = event.target
            if (entity.type == EntityType.WARDEN) {
                MainScript.LOGGER.info("warden is attacked")
                player = event.entity
            }

        flagSpawnSoul = true
    }

    @SubscribeEvent
    fun dropItemFromEntity(event: LivingDeathEvent) {
        val entity = event.entity
        if (flagSpawnSoul) {
            if (entity.type == EntityType.WARDEN) {
                MainScript.LOGGER.info("warden is died")

                val myEntity = ModEntities.HUNGRYSOULENTITY.get().create(player?.level())
                myEntity?.moveTo(entity.x, entity.y, entity.z)
                myEntity?.isBaby = true
                player?.level()?.addFreshEntity(myEntity)
                flagSpawnSoul = false
            }
        }
    }

    @SubscribeEvent
    fun executedCommand(event: CommandEvent) {
        logger.info(event.listenerList)
    }

}