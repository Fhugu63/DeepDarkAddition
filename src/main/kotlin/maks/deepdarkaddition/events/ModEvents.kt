package maks.deepdarkaddition.events

import maks.deepdarkaddition.CalculationScript
import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.ModEntities
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import net.minecraft.client.Minecraft
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.phys.Vec3
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.CommandEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import java.util.*
import kotlin.math.abs


class ModEvents {
    val minecraft = Minecraft.getInstance()

    var player = minecraft.level?.getPlayerByUUID(UUID.fromString(""))

    val cs = CalculationScript()

    var flagSpawnSoul = true

    val logger = MainScript.LOGGER

    var namesOfHungrySouls = arrayOf<String>()
    var numOfHungrySouls = 0

    var ownerOfSoul = mutableMapOf<HungrySoulEntity?, Entity>()

    @SubscribeEvent
    fun onTickUpdate(event: TickEvent) {
        if (!ownerOfSoul.isEmpty()) {
            ownerOfSoul.forEach { (key, value) ->
                val entity = key
                val playerEntity = value

                if (entity != null) {
                    val raznicaInPosition = cs.raznicaInPos(playerEntity.position(), entity.position())

                    if (abs(raznicaInPosition.x) > 2 || abs(raznicaInPosition.y) > 2 || abs(raznicaInPosition.z) > 2) {
                        entity.moveTo(Vec3(
                            if (raznicaInPosition.x>1) {entity.x+0.1} else if (raznicaInPosition.x<1&&raznicaInPosition.x>-1) {entity.x} else {entity.x-0.1},
                            if (raznicaInPosition.y>1) {entity.y+0.1} else if (raznicaInPosition.y<1&&raznicaInPosition.y>-1) {entity.y} else {entity.y-0.1},
                            if (raznicaInPosition.z>1) {entity.z+0.1} else if (raznicaInPosition.z<1&&raznicaInPosition.z>-1) {entity.z} else {entity.z-0.1}
                        ))
                    }
                }
            }
        }
    }

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
                myEntity?.moveTo(entity.x, entity.y+2, entity.z)
                myEntity?.isNoGravity = true
                //myEntity?.setDeltaMovement(entity.deltaMovement.add(100.2,100.0,100.0))
                //namesOfHungrySouls += "hungrysoul${numOfHungrySouls}"
                ownerOfSoul.put(myEntity, event.source.entity as Entity)

                logger.info(event.source.entity?.name.toString())

                player?.level()?.addFreshEntity(myEntity)
                flagSpawnSoul = false

                //minecraft.level.

                numOfHungrySouls++
            }
        }
    }

    @SubscribeEvent
    fun executedCommand(event: CommandEvent) {
        logger.info(event.listenerList)
        //HungrySoulRender.scale = 5f
    }

    fun registerCaps(event: RegisterCapabilitiesEvent) {
        //event.register<IExampleCapability?>(IExampleCapability::class.java)
    }
}