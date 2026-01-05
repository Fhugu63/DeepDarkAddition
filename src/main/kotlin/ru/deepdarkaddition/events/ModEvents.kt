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
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.Container
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.monster.Creeper
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DoubleBlockCombiner
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.CommandEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.EntityEvent
import net.minecraftforge.event.entity.item.ItemEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.AdvancementEvent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.event.level.ChunkDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.deepdarkaddition.engine.AnnotationProcessor
import ru.deepdarkaddition.engine.DeepDarkAdditionSaveData
import java.util.*
import ru.deepdarkaddition.engine.Methods
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity
import ru.deepdarkaddition.item.ModItems
import kotlin.math.abs
import kotlin.math.log


class ModEvents() {
    //var minecraft: Minecraft = Minecraft.getInstance()

    var player: Player? = null

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
            //player = event.entity
        }

        flagSpawnSoul = true
    }

    @SubscribeEvent
    fun dropItemFromEntity(event: LivingDeathEvent) {
        val damageSourceEntity = event.source.entity
        val entity = event.entity
        if (flagSpawnSoul) {
            if (entity.type == EntityType.WARDEN && damageSourceEntity != null) {
                if (!entity.level().isClientSide) {
                    val sLevel = entity.level() as ServerLevel
                    val savedData = DeepDarkAdditionSaveData.getSavedData(sLevel)

                    val diarys = savedData.diarys
                    val splitedDiarys = diarys.split(",")

                    if (splitedDiarys.contains("rdp1") && splitedDiarys.contains("rdp2") && splitedDiarys.contains("rdp3") &&
                        splitedDiarys.contains("rdp4") && splitedDiarys.contains("rdp5") && splitedDiarys.contains("rdp6") &&
                        !splitedDiarys.contains("rdp7")) {
                        val DiaryPartSeven = ItemStack(ModItems().RESEARHDIARYPARTSEVEN.get(), 1)

                        entity.spawnAtLocation(DiaryPartSeven)

                        savedData.diarys += "rdp7"
                        savedData.setDirty()
                    }
                }

                val hungrySoul = ModEntities.HUNGRYSOULENTITY.get().create(damageSourceEntity.level())!!
                hungrySoul.moveTo(entity.x, entity.y+2, entity.z)
                hungrySoul.isNoGravity = true
                hungrySoul.numOfEatenSouls = 5
                hungrySoul.setOwnerOfSoulUUID(entity.uuid)

                player?.level()?.addFreshEntity(hungrySoul)
                player?.sendSystemMessage(Component.translatable("[голодная душа] ты меня освободил, теперь накорми меня живыми существами и возможно и я исполню твоё желание"))

                flagSpawnSoul = false

                //numOfHungrySouls++
            }
        }

        if (event.getEntity().level() !is ServerLevel) {
            val level = entity.level()

            if (entity !is Creeper) {
                if (event.getSource().getEntity() !is Player) {
                    val deathPos = entity.blockPosition()

                    val hasCatalystNearby = BlockPos.betweenClosedStream(
                        deathPos.offset(-8, -8, -8),
                        deathPos.offset(8, 8, 8)
                    ).anyMatch { pos: BlockPos? -> level.getBlockState(pos).`is`(Blocks.SCULK_CATALYST) }

                    if (!hasCatalystNearby) {
                        val sculkCreeper: Entity? = ModEntities.SCULKCREEPERENTITY.get().create(level)

                        if (sculkCreeper != null) {
                            sculkCreeper.moveTo(entity.position())
                            level.addFreshEntity(sculkCreeper)
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    fun onAdvancement(playerAdvancementEvent: AdvancementEvent) {
        val player = playerAdvancementEvent.entity as? ServerPlayer ?: return

        val advancementId = playerAdvancementEvent.advancement.id.toString()

        val sLevel = player.level() as ServerLevel
        val savedData = DeepDarkAdditionSaveData.getSavedData(sLevel)

        val diarys = savedData.diarys
        val splitedDiarys = diarys.split(",")
        if (advancementId == "minecraft:adventure/hidden" && !splitedDiarys.contains("rdp3") && splitedDiarys.contains("rdp1") && splitedDiarys.contains("rdp2")) { // Мыш (кродётся)
            // Выдать дневник
            player.inventory.add(ItemStack(ModItems().RESEARHDIARYPARTTHREE.get(), 1))

            savedData.diarys += "rdp3,"
            savedData.setDirty()
        }
    }

    @SubscribeEvent
    fun onPlayerInteract(event: PlayerInteractEvent.RightClickBlock) {
        val blockPos = event.pos
        val level = event.level
        var block = level.getBlockEntity(blockPos)

        if (block != null && !level.isClientSide) {
            if (block is Container) {

                logger.info(block)
                logger.info(block.type.toString())
                logger.info(blockPos)

                val inventory: Container = block

                val numOfSlots = inventory.containerSize-1

                val sLevel = level as ServerLevel
                val savedData = DeepDarkAdditionSaveData.getSavedData(sLevel)

                val diarys = savedData.diarys
                val splitedDiarys = diarys.split(",")

                if (splitedDiarys.contains("rdp1") && splitedDiarys.contains("rdp2") &&
                    splitedDiarys.contains("rdp3") && !splitedDiarys.contains("rdp4")) {
                    logger.info("idk")

                    inventory.setItem(numOfSlots, ItemStack(ModItems().RESEARHDIARYPARTFOUR.get(), 1))

                    savedData.diarys += "rdp4,"
                } else if (splitedDiarys.contains("rdp1") && splitedDiarys.contains("rdp2") &&
                    splitedDiarys.contains("rdp3") && splitedDiarys.contains("rdp4")
                    && !splitedDiarys.contains("rdp5")) {
                    logger.info("idk")
                    inventory.setItem(numOfSlots, ItemStack(ModItems().RESEARHDIARYPARTFIVE.get(), 1))

                    savedData.diarys += "rdp5,"
                } else if (splitedDiarys.contains("rdp1") && splitedDiarys.contains("rdp2") &&
                    splitedDiarys.contains("rdp3") && splitedDiarys.contains("rdp4") && splitedDiarys.contains("rdp5")
                    && !splitedDiarys.contains("rdp6")) {
                    logger.info("idk")
                    inventory.setItem(numOfSlots, ItemStack(ModItems().RESEARHDIARYPARTSIX.get(), 1))

                    savedData.diarys += "rdp6,"
                }
            }
        }
    }

    /*@SubscribeEvent
    fun onLevelTick(event: ItemEvent) {
        if (event.entity.item == ItemStack(Items.ECHO_SHARD, 1)) {
            val level = event.entity.level()
            val hasCatalystNearby = level.getEntitiesOfClass()

            if (!hasCatalystNearby) {
                logger.info("nice")
            }
        }
    }*/


    @SubscribeEvent
    fun onPickUpLoot(event: PlayerEvent.ItemPickupEvent) {
        val player = event.entity
        val item = event.originalEntity.item

        if (item.displayName == Component.translatable("Coblestone")) {
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