package ru.deepdarkaddition.entity.custom

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.ShriekParticle
import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.particles.SculkChargeParticleOptions
import net.minecraft.core.particles.ShriekParticleOption
import net.minecraft.core.particles.VibrationParticleOption
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.memory.WalkTarget
import net.minecraft.world.entity.monster.warden.AngerManagement
import net.minecraft.world.entity.monster.warden.Warden
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.EntityPositionSource
import net.minecraft.world.level.gameevent.PositionSource
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem
import net.minecraft.world.phys.AABB
import ru.deepdarkaddition.entity.ModEntities
import java.util.UUID
import java.util.function.Consumer
import java.util.function.Predicate
import net.minecraft.world.entity.ai.navigation.PathNavigation
import ru.deepdarkaddition.engine.Methods


class SculkCreeperEntity(pEntityType: EntityType<out PathfinderMob>, pLevel: Level) : PathfinderMob(pEntityType, pLevel) {

    private var cooldownToAbility: Int = 0
    private var targetUUID: UUID? = null

    private var numOfAngry = 0

    enum class StepOfAngry {
        NEUTRAL,
        MEDIUMANGRY,
        ANGRY
    }

    private var stepOfAngry: StepOfAngry = StepOfAngry.NEUTRAL

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)

        nbt.putInt("cdToAbility", cooldownToAbility)
        nbt.putInt("numOfAngry", numOfAngry)
        if (targetUUID != null) {
            nbt.putUUID("targetUUID", targetUUID)
        }
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)

        if (nbt.contains("cdToAbility")) {
            cooldownToAbility = nbt.getInt("cdToAbility")
        }
        if (nbt.contains("numOfAngry")) {
            numOfAngry = nbt.getInt("numOfAngry")
        }
        if (nbt.contains("targetUUID")) {
            targetUUID = nbt.getUUID("targetUUID")
            target = level().getPlayerByUUID(targetUUID)
        }
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))

        goalSelector.addGoal(1, MeleeAttackGoal(this, 2.0, true))
        this.targetSelector.addGoal(1, HurtByTargetGoal(this))

    }

    // Позиция источника вибрации (используем высоту глаз)
    private val positionSource: PositionSource = EntityPositionSource(this, this.getEyeHeight(Pose.STANDING))


    override fun sendDebugPackets() {

    }

    fun detectNearestPlayer(sculkCreeper: Entity): Player? {
        val nearestPlayer = level().getNearestPlayer(sculkCreeper, 20.0)
        return nearestPlayer
    }

    fun canTargerEntity(entity: Entity): Boolean {
        if (entity is LivingEntity) {
            val entityType = entity.type
            if (EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity) && entityType != EntityType.ARMOR_STAND && entityType != EntityType.WARDEN
                && entityType != ModEntities.HUNGRYSOULENTITY.get() && entityType != ModEntities.SCULKCREEPERENTITY.get() && entityType == EntityType.PLAYER
            ) {
                return true
            }
        }
        return false
    }

    fun useAbility() {
        val players: List<Player> = level().getEntitiesOfClass(Player::class.java, AABB.ofSize(position(), 20.0, 20.0, 20.0))
        //players.forEach { sculkCreeperEntity -> sculkCreeperEntity.soundVibration(entity, pPos) }
        if (!level().isClientSide && cooldownToAbility == 0) {
            val sLevel: ServerLevel = level() as ServerLevel
            //val particle = ShriekParticleOption(10)
            val particle = SculkChargeParticleOptions(10f)

            sLevel.sendParticles(
                particle,
                position().x, position().y, position().z,
                50,
                0.1, 0.1, 0.1,
                1.0
            )

            players.forEach { player -> player.hurt(damageSources().explosion(null, this), 9.5f) }
            cooldownToAbility = 60
        }
    }

    //@ReactOnVibrations
    fun soundVibration(entity: Entity?, pos: BlockPos, radius: Int = 16) {
        if (entity != null && entity.isAlive && canTargerEntity(entity)) {
            val entity = entity as LivingEntity
            val pPos = Vec3i(pos.x, pos.y, pos.z)
            val distance = this.distanceTo(entity)
            val level = level()

            if (canTargerEntity(entity) && distance <= radius) {
                val particle = VibrationParticleOption(BlockPositionSource(BlockPos(Vec3i(position().x.toInt(), position().y.toInt(), position().z.toInt()))), 10)
                if (!level.isClientSide) {
                    val sLevel = level as ServerLevel
                    sLevel.sendParticles(
                        particle,
                        pPos.x + 0.5, pPos.y + 0.5, pPos.z + 0.5,
                        1,
                        0.0, 0.0, 0.0,
                        0.0
                    )

                    target = entity
                    targetUUID = target!!.uuid
                } else if (level.isClientSide) {
                    val cLevel = level as ClientLevel
                    cLevel.addParticle(
                        particle,
                        true,
                        pPos.x + 0.5, pPos.y + 0.5, pPos.z + 0.5,
                        0.0, 0.0, 0.0
                    )
                    target = entity
                }
            }
        }
    }



    override fun tick() {
        if (!this.level().isClientSide) {
            val serverLevel = this.level() as ServerLevel

            if (numOfAngry <= 30) {
                stepOfAngry = StepOfAngry.NEUTRAL
            } else if (numOfAngry <= 80) {
                stepOfAngry = StepOfAngry.MEDIUMANGRY
            } else {
                stepOfAngry = StepOfAngry.ANGRY
            }

            if (target != null) {
                val desiredSpeed = if (numOfAngry >= 80) 1.2 else 0.7

                this.navigation.moveTo(target!!.x, target!!.y, target!!.z, desiredSpeed)

                lookAtTarget()
                if (this.tickCount % 6 == 0 && numOfAngry <= 150) {
                    numOfAngry++
                }
                if (numOfAngry >= 80) {
                    //navigation.moveTo(target!!.position().x, target!!.position().y, target!!.position().z, 0.2)
                }
                if (numOfAngry >= 90) {
                    useAbility()
                }
            }



            if (cooldownToAbility != 0) {
                cooldownToAbility--
            }
        }


        super.tick()
    }

    fun lookAtTarget() {
        this.lookAt(target, 10F, 10F)
    }

    // Компаньон-объект для создания аттрибутов сущности
    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ARMOR_TOUGHNESS, 10.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 3.0)
                .add(Attributes.ATTACK_DAMAGE, 3.5)
        }
    }
}