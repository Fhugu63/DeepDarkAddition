package ru.deepdarkaddition.events

import ru.deepdarkaddition.CalculationScript
import ru.deepdarkaddition.DataSavers.OwnerOfHungrySoulCapability
import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.ModEntities
import ru.deepdarkaddition.entity.custom.HungrySoulEntity
import ru.deepdarkaddition.interfaces.IHungrySouls
import ru.deepdarkaddition.item.ModItems
import net.minecraft.client.Minecraft
import net.minecraft.core.Direction
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.phys.Vec3
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.CommandEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.event.level.ChunkDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import java.util.*
import kotlin.math.abs


class ModEvents() : IHungrySouls, ICapabilityProvider {
    val minecraft = Minecraft.getInstance()

    var player = minecraft.level?.getPlayerByUUID(UUID.fromString(""))

    val cs = CalculationScript()

    var flagSpawnSoul = true

    val logger = MainScript.LOGGER

    var namesOfHungrySouls = arrayOf<String>()
    var numOfHungrySouls = 0

    var ihungrySouls: LazyOptional<IHungrySouls> = LazyOptional.of { this }

    //override var ownerOfSoul = ihungrySouls.resolve().get().ownerOfSoul

    //lateinit var myentity: HungrySoulEntity

    override lateinit var ownerOfSoul: MutableMap<HungrySoulEntity?, Entity>

    @Suppress("OVERRIDE_BY_INLINE")
    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return OwnerOfHungrySoulCapability().OWNEROFSOUL_HANDLER.orEmpty(cap, ihungrySouls)
    }

    fun invalidate() {
        ihungrySouls.invalidate()
    }

    val provider = this.getCapability(OwnerOfHungrySoulCapability().OWNEROFSOUL_HANDLER, null)

    //Инициализация методов интерфейса
    override fun getSouls(): MutableMap<HungrySoulEntity?, Entity> {
        var i: MutableMap<HungrySoulEntity?, Entity> = ownerOfSoul
        provider.addListener { cap -> i = ownerOfSoul }

        return i
    }

    override fun getPlayerBySoul(keySoul: HungrySoulEntity?): Entity? {
        var i: Entity? = null
        provider.addListener { cap -> i = ownerOfSoul?.get(keySoul) }

        return i
    }

    override fun addSoul(
        soulEntity: HungrySoulEntity?,
        pEntity: Entity
    ) {
        provider.addListener { cap -> ownerOfSoul.put(soulEntity, pEntity) }
    }

    override fun removeSoul(removebleSoul: HungrySoulEntity) {
        provider.addListener { cap -> ownerOfSoul?.remove(removebleSoul) }
    }


    //Метод срабатывающий каждый тик
    @SubscribeEvent
    fun onTickUpdate(event: TickEvent) {
        var oOS: MutableMap<HungrySoulEntity?, Entity> = ownerOfSoul
        provider.addListener { cop -> oOS = getSouls() }
        if (!oOS.isEmpty()) {
            oOS.forEach { (key, value) ->
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
        } //else {
            //ModEvents().getCapability(OwnerOfHungrySoulCapability().OWNEROFSOUL_HANDLER, null).ifPresent { cap -> ModEvents().ownerOfSoul = cap.getSouls() }
        //}

        //logger.info(SculkCatalystBlock.)
    }
    //Метод срабатывающий когда игрок бъёт по энтити
    @SubscribeEvent
    fun attackEntity(event: AttackEntityEvent) {
        val entity = event.target
        if (entity.type == EntityType.WARDEN) {
            MainScript.LOGGER.info("warden is attacked")
            player = event.entity
        }

        flagSpawnSoul = true
    }
    //Метод срабатывающий когда живая сущность умерла
    @SubscribeEvent
    fun spawnEntityWhenMobDie(event: LivingDeathEvent) {
        val entity = event.entity

        if (flagSpawnSoul) {
            if (entity.type == EntityType.WARDEN) {
                MainScript.LOGGER.info("warden is died")

                val myEntity = ModEntities.HUNGRYSOULENTITY.get().create(player?.level())
                myEntity?.moveTo(entity.x, entity.y+2, entity.z)
                myEntity?.isNoGravity = true
                //addSoul(myEntity, event.source.entity as Entity)
                val sourceDamage = event.source.entity
                if (sourceDamage != null) {
                    addSoul(myEntity, sourceDamage)
                    logger.info(ownerOfSoul.toString())
                }

                player?.inventory?.add(ModItems().RESEARHDIARYPARTONE.get().defaultInstance)

                logger.info(event.source.entity?.name.toString())

                player?.level()?.addFreshEntity(myEntity)
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

    fun registerCaps(event: RegisterCapabilitiesEvent) {
        //event.register<IExampleCapability?>(IExampleCapability::class.java)
    }

    fun testing(event: ChunkDataEvent) {

    }
}