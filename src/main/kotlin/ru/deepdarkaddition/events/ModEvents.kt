package ru.deepdarkaddition.events

import ru.deepdarkaddition.engine.CalculationScript
import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.ModEntities
import ru.deepdarkaddition.entity.custom.HungrySoulEntity
import ru.deepdarkaddition.interfaces.IHungrySouls
import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.CommandEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.event.level.ChunkDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.deepdarkaddition.engine.AnnotationProcessor
import java.util.*
import ru.deepdarkaddition.engine.Methods
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity
import thedarkcolour.kotlinforforge.forge.vectorutil.v3d.toVec3
import kotlin.math.abs


class ModEvents() : IHungrySouls {
    val minecraft = Minecraft.getInstance()

    var player = minecraft.level?.getPlayerByUUID(UUID.fromString(""))

    val cs = CalculationScript()

    var flagSpawnSoul = true

    val logger = MainScript.LOGGER

    var namesOfHungrySouls = arrayOf<String>()
    var numOfHungrySouls = 0

    var ihungrySouls: LazyOptional<IHungrySouls> = LazyOptional.of { this }

    //override var ownerOfSoul = ihungrySouls.resolve().get().ownerOfSoul

    override var ownerOfSoul: MutableMap<HungrySoulEntity?, Entity> = mutableMapOf<HungrySoulEntity?, Entity>()

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

    //Инициализация методов интерфейса
    override fun getSouls(): MutableMap<HungrySoulEntity?, Entity> {
        return ownerOfSoul
    }

    override fun getPlayerBySoul(keySoul: HungrySoulEntity?): Entity? {
        return ownerOfSoul.get(keySoul)

    }

    override fun addSoul(
        soulEntity: HungrySoulEntity?,
        pEntity: Entity
    ) {
        ownerOfSoul.put(soulEntity, pEntity)
    }

    override fun removeSoul(removebleSoul: HungrySoulEntity) {
        ownerOfSoul.remove(removebleSoul)
    }


    //Метод срабатывающий каждый тик
    @SubscribeEvent
    fun onTickUpdate(event: TickEvent) {
        if (!getSouls().isEmpty()) {
            getSouls().forEach { (key, value) ->
                val hungrySoul = key
                val playerEntity = value

                if (hungrySoul != null) {
                    val raznicaInPosition = cs.raznicaInPos(playerEntity.position(), hungrySoul.position())
                    if (abs(raznicaInPosition.x) > 2 || abs(raznicaInPosition.y) > 2 || abs(raznicaInPosition.z) > 2) {

                        movedTime += 1



                        Methods().smoothMovement(hungrySoul, playerEntity)
                    }
                    val nbt = CompoundTag()
                    hungrySoul.saveWithoutId(nbt)

                    logger.info(nbt)
                    //entity.tag
                } else {
                    movedTime = 0
                }
            }
        }

        //logger.info(SculkCatalystBlock.)
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

                val hungrySoul = ModEntities.HUNGRYSOULENTITY.get().create(player?.level())!!
                hungrySoul.moveTo(entity.x, entity.y+2, entity.z)
                hungrySoul.isNoGravity = true
                hungrySoul.numOfEatenSouls = 5
                //hungrySoul?.setDeltaMovement(entity.deltaMovement.add(100.2,100.0,100.0))
                //namesOfHungrySouls += "hungrysoul${numOfHungrySouls}"
                val entity = event.source.entity
                if (entity != null) {
                    addSoul(hungrySoul, entity)
                }

                logger.info(event.source.entity?.name.toString())
                player?.level()?.addFreshEntity(hungrySoul)
                player?.sendSystemMessage(net.minecraft.network.chat.Component.translatable("[голодная душа] ты меня освободил, теперь накорми меня живыми существами и возможно и я исполню твоё желание"))

                //val tag = CompoundTag()
                //tag.putInt()
                val nbt = CompoundTag()
                hungrySoul.saveWithoutId(nbt)

                nbt.putInt("numOfEatenSouls", 5)

                hungrySoul.load(nbt)

                logger.info(hungrySoul.loadAdditional(CompoundTag()))


                flagSpawnSoul = false

                numOfHungrySouls++
            }
        }
    }
    //Тесты
    val level = minecraft.level?.server?.getLevel(minecraft.level?.dimension())
    //val test = level?.findNearestMapStructure(StructureTags., player?.onPos, 1000, false)
    //val test = Anci

    @SubscribeEvent
    fun executedCommand(event: CommandEvent) {
        //logger.info(event.listenerList)
        //HungrySoulRender.scale = 5f
    }

    @SubscribeEvent
    fun playerTick(event: TickEvent.PlayerTickEvent) {
        val player = event.player
    }

    @SubscribeEvent
    fun onPlaceBlock(event: BlockEvent.EntityPlaceEvent) {
        val pos: Vec3 = event.pos.toVec3()
        val sculkCreepers: List<SculkCreeperEntity> = event.entity?.level()!!.getEntitiesOfClass(SculkCreeperEntity::class.java, AABB.ofSize(pos, 8.0, 8.0, 8.0))
        sculkCreepers.forEach { sculkCreeperEntity -> sculkCreeperEntity.soundVibration(event.entity) }
    }

    fun registerCaps(event: RegisterCapabilitiesEvent) {
        //event.register<IExampleCapability?>(IExampleCapability::class.java)
    }

    fun testing(event: ChunkDataEvent) {

    }
}