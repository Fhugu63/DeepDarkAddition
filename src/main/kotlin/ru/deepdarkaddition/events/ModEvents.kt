package ru.deepdarkaddition.events

import net.minecraft.ChatFormatting
import ru.deepdarkaddition.engine.CalculationScript
import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.ModEntities
import ru.deepdarkaddition.entity.custom.HungrySoulEntity
import ru.deepdarkaddition.interfaces.IHungrySouls
import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.CommandEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.EntityEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.event.level.ChunkDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.deepdarkaddition.engine.AnnotationProcessor
import java.util.*
import ru.deepdarkaddition.engine.Methods
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity
import ru.deepdarkaddition.item.ModItems
import kotlin.math.abs


class ModEvents() {
    var minecraft: Minecraft = Minecraft.getInstance()

    var player = minecraft.level?.getPlayerByUUID(UUID.fromString(""))

    val cs = CalculationScript()

    var flagSpawnSoul = true

    val logger = MainScript.LOGGER

    var movedTime = 0
/*
    @Suppress("OVERRIDE_BY_INLINE")
    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return OwnerOfHungrySoulCapability().OWNEROFSOUL_HANDLER.orEmpty(cap, ihungrySouls)
    }

    fun invalidate() {
        ihungrySouls.invalidate()
    }

    val provider = this.getCapability(OwnerOfHungrySoulCapability().OWNEROFSOUL_HANDLER, null)*/



    //Метод срабатывающий каждый тик
    @SubscribeEvent
    fun onTickUpdate(event: TickEvent) {
        /*if (!getSouls().isEmpty()) {
            getSouls().forEach { (key, value) ->
                val hungrySoul = key
                val playerEntity = value

                if (hungrySoul != null) {
                    val raznicaInPosition = cs.raznicaInPos(playerEntity.position(), hungrySoul.position())
                    if (abs(raznicaInPosition.x) > 2 || abs(raznicaInPosition.y) > 2 || abs(raznicaInPosition.z) > 2) {

                        movedTime += 1



                        Methods().smoothMovement(hungrySoul, playerEntity, 0.1f)
                    }
                    val nbt = CompoundTag()
                    hungrySoul.saveWithoutId(nbt)

                    logger.info(nbt)
                    //entity.tag
                } else {
                    movedTime = 0
                }
            }
        }*/


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
        val damageSourceEntity = event.source.entity
        val entity = event.entity
        if (flagSpawnSoul) {
            if (entity.type == EntityType.WARDEN && damageSourceEntity != null) {
                MainScript.LOGGER.info("warden is died")

                val hungrySoul = ModEntities.HUNGRYSOULENTITY.get().create(damageSourceEntity.level())!!
                hungrySoul.moveTo(entity.x, entity.y+2, entity.z)
                hungrySoul.isNoGravity = true
                hungrySoul.numOfEatenSouls = 5
                //hungrySoul?.setDeltaMovement(entity.deltaMovement.add(100.2,100.0,100.0))
                //namesOfHungrySouls += "hungrysoul${numOfHungrySouls}"
                val entity = event.source.entity
                if (entity != null) {
                    hungrySoul.setOwnerOfSoulUUID(entity.uuid)
                }

                player?.level()?.addFreshEntity(hungrySoul)
                player?.sendSystemMessage(net.minecraft.network.chat.Component.translatable("[голодная душа] ты меня освободил, теперь накорми меня живыми существами и возможно и я исполню твоё желание"))

                flagSpawnSoul = false

                //numOfHungrySouls++
            }
        }
    }

    @SubscribeEvent
    fun onPickUpLoot(event: PlayerEvent.ItemPickupEvent) {
        val player = event.entity
        val item = event.originalEntity.item

        if (item.displayName == Component.translatable("Cobblestone")) {
            val level = player.level()
            if (!level.isClientSide) {
                (level as ServerLevel).server.sendSystemMessage(Component.translatable("короче, типо ищи ну как бы древний город, хз что ещё сказать").withStyle(
                    ChatFormatting.OBFUSCATED))
            }
            player.sendSystemMessage(Component.translatable("короче, типо ищи ну как бы древний город, хз что ещё сказать").withStyle(
                ChatFormatting.OBFUSCATED))
        }
    }

    @SubscribeEvent
    fun executedCommand(event: CommandEvent) {
        //logger.info(event.listenerList)
        //HungrySoulRender.scale = 5f
    }

    private val TAG_GIVEN_STARTER_ITEMS = "has_received_starter_items"

    @SubscribeEvent
    fun onPlayerLoggedIn(event: PlayerEvent.PlayerLoggedInEvent) {
        val player = event.entity
        val level = player.level()

        if (level.isClientSide) return


        val persistentData = player.persistentData

        val modData = persistentData.getCompound("deepdarkaddition")

        // Проверяем флаг
        if (!modData.getBoolean(TAG_GIVEN_STARTER_ITEMS)) {
            val starterItem = ItemStack(ModItems().TUTORIALBOOKITEM.get(), 1)
            player.inventory.add(starterItem)

            modData.putBoolean(TAG_GIVEN_STARTER_ITEMS, true)

            persistentData.put("deepdarkaddition", modData)
        }
    }

    fun registerCaps(event: RegisterCapabilitiesEvent) {
        //event.register<IExampleCapability?>(IExampleCapability::class.java)
    }

    fun testing(event: ChunkDataEvent) {

    }
}