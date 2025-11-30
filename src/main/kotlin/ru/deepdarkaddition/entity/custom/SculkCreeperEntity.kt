package ru.deepdarkaddition.entity.custom

import net.minecraft.core.BlockPos
import net.minecraft.core.particles.VibrationParticleOption
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.EntityPositionSource
import net.minecraft.world.level.gameevent.PositionSource
import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.FollowPlayerGoal
import thedarkcolour.kotlinforforge.forge.vectorutil.v3d.toVec3i


class SculkCreeperEntity(pEntityType: EntityType<out Monster>, pLevel: Level) : Monster(pEntityType, pLevel) {

    // Позиция источника вибрации (используем высоту глаз)
    private val positionSource: PositionSource = EntityPositionSource(this, this.getEyeHeight(Pose.STANDING))

    //var SCULKCREEPER_CAN_LISTEN = GameEventTags.create("SCULKCREEPER_CAN_LISTEN")

    protected lateinit var target: Entity

    fun detectNearestPlayer(sculkCreeper: Entity): Player? {
        val nearestPlayer = level().getNearestPlayer(sculkCreeper, 20.0)
        return nearestPlayer
    }

    //@ReactOnVibrations
    fun soundVibration(entity: Entity?) {
        MainScript.LOGGER.info("call method with annotation has work!")
        val radius = 16 // Радиус обнаружения вибрации
        val pPos = entity?.position()!!
        val distance = this.distanceTo(entity)
        if (distance <= radius && !level().isClientSide && level() is ServerLevel) {
            //val options = VibrationParticleOption(destination, duration)

            (level() as ServerLevel).addParticle(
                VibrationParticleOption(BlockPositionSource(BlockPos(position().toVec3i())), 10),
                true,
                pPos.x, pPos.y, pPos.z,
                1.0, 1.0, 1.0
            )
            println("where particle?")
        }
    }

    override fun tick() {
        if (!this.level().isClientSide) {
            val serverLevel = this.level() as ServerLevel

        }


        super.tick()
    }


    // Регистрация целей (цели движутся плавают и ищут пищу)
    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))

        goalSelector.addGoal(1, FollowPlayerGoal(this))
    }

    // Компаньон-объект для создания аттрибутов сущности
    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.ARMOR_TOUGHNESS, 100.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 1.0)
        }
    }
}