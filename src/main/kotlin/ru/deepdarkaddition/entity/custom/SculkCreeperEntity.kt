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
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
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
import net.minecraft.world.item.ItemStack
import org.apache.logging.log4j.core.jmx.Server
import ru.deepdarkaddition.engine.DeepDarkAdditionSaveData
import ru.deepdarkaddition.engine.Methods
import ru.deepdarkaddition.item.ModItems


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
            nbt.putUUID("targetUUID", targetUUID!!)
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
            target = level().getPlayerByUUID(targetUUID!!)
        }
    }

    override fun dropCustomDeathLoot(pSource: DamageSource, pLooting: Int, pRecentlyHit: Boolean) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit)

        if (pSource.entity != null && !pSource.entity!!.level().isClientSide) {
            val entity = pSource.entity!!
            val sLevel = entity.level() as ServerLevel
            val savedData = DeepDarkAdditionSaveData.getSavedData(sLevel)

            val diarys = savedData.diarys
            val splitedDiarys = diarys.split(", ")

            var diaryHasSelected = false

            if (!splitedDiarys.contains("rdp1") && !diaryHasSelected) {
                diaryHasSelected = true

                val item = ItemStack(ModItems().RESEARHDIARYPARTONE.get(), 1)

                savedData.diarys += "rdp1, "
                savedData.setDirty()

                this.spawnAtLocation(item)
            }
            if (!splitedDiarys.contains("rdp2") && !diaryHasSelected) {
                diaryHasSelected = true

                val item = ItemStack(ModItems().RESEARHDIARYPARTTWO.get(), 1)

                savedData.diarys += "rdp2, "
                savedData.setDirty()

                this.spawnAtLocation(item)
            }
        }
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))

        goalSelector.addGoal(1, MeleeAttackGoal(this, 2.0, true))
        this.targetSelector.addGoal(1, HurtByTargetGoal(this))

        goalSelector.addGoal(9, WaterAvoidingRandomStrollGoal(this, 0.5))
        goalSelector.addGoal(10, RandomLookAroundGoal(this))
    }

    // Позиция источника вибрации (используем высоту глаз)
    private val positionSource: PositionSource = EntityPositionSource(this, this.getEyeHeight(Pose.STANDING))


    override fun sendDebugPackets() {

    }

    override fun setTarget(pTarget: LivingEntity?) {
        if (target != null) {
            targetUUID = target!!.uuid
        }
        super.setTarget(pTarget)
    }

    fun deleteTarget() {
        this.target = null
    }

    fun detectNearestPlayer(sculkCreeper: Entity): Player? {
        val nearestPlayer = level().getNearestPlayer(sculkCreeper, 20.0)
        return nearestPlayer
    }

    fun canTargerEntity(entity: Entity): Boolean {
        if (entity is LivingEntity) {
            val entityType = entity.type
            if (EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity) && entityType != EntityType.ARMOR_STAND && entityType != EntityType.WARDEN
                && entityType != ModEntities.HUNGRYSOULENTITY.get() && entityType != ModEntities.SCULKCREEPERENTITY.get() && entityType != EntityType.ARROW &&
                entityType != EntityType.SPECTRAL_ARROW && entityType != EntityType.ITEM && entityType != EntityType.ITEM_FRAME && entityType != EntityType.ITEM_DISPLAY
                && this.isAlive && distanceTo(entity) <= 30
            ) {
                return true
            } else {
                deleteTarget()
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
                position().x, position().y+1, position().z,
                50,
                0.1, 0.1, 0.1,
                1.0
            )

            players.forEach { player -> player.hurt(damageSources().explosion(null, this), 9.5f) }
            cooldownToAbility = 60
        }
    }

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

                    setTarget(entity)
                /*} else if (level.isClientSide) {
                    val cLevel = level as ClientLevel
                    cLevel.addParticle(
                        particle,
                        true,
                        pPos.x + 0.5, pPos.y + 0.5, pPos.z + 0.5,
                        0.0, 0.0, 0.0
                    )
                    target = entity*/
                }
            }
        }
    }

    fun jump() {
        if (this.onGround()) {
            val jumpStrength = 0.42 // Стандартная сила прыжка
            this.deltaMovement.y
            this.hasImpulse = true
        }
    }

    override fun tick() {
        if (!this.level().isClientSide) {
            val serverLevel = this.level() as ServerLevel

            if (this.tickCount % 40 == 0) {
                Warden.applyDarknessAround(serverLevel, position(), this, 16)
            }


            if (numOfAngry <= 30) {
                stepOfAngry = StepOfAngry.NEUTRAL
            } else if (numOfAngry <= 80) {
                stepOfAngry = StepOfAngry.MEDIUMANGRY
            } else {
                stepOfAngry = StepOfAngry.ANGRY
            }

            if (target != null) {
                val desiredSpeed = if (numOfAngry >= 60) 1.3 else 0.7

                this.navigation.moveTo(target!!.x, target!!.y, target!!.z, desiredSpeed)

                if (target!!.y-this.y >= 1.5) {
                    //jump()
                }


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