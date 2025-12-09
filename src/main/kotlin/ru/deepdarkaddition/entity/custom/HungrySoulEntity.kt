package ru.deepdarkaddition.entity.custom

import net.minecraft.nbt.CompoundTag
import ru.deepdarkaddition.entity.ModEntities
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.Level
import ru.deepdarkaddition.interfaces.IHungrySouls
import java.util.UUID

class HungrySoulEntity(pEntityType: EntityType<out PathfinderMob>, pLevel: Level) : PathfinderMob(pEntityType, pLevel) {
    private fun setupAnimationStates() {

    }

    var ownerOfSoul: UUID? = null
    var numOfEatenSouls: Int = 0

    fun setOwnerOfSoulUUID(
        playerUUID: UUID
    ) {
        ownerOfSoul = playerUUID
    }

    fun removeOwnerOfSoulUUID(removebleSoul: UUID) {
        ownerOfSoul = null
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putInt("numOfEatenSouls", numOfEatenSouls)
        if (ownerOfSoul != null) {
            nbt.putUUID("ownerUUID", ownerOfSoul!!)
        }
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        if (nbt.contains("numOfEatenSouls")) {
            numOfEatenSouls = nbt.getInt("numOfEatenSouls")
        }
        if (nbt.contains("ownerUUID")) {
            ownerOfSoul = nbt.getUUID("ownerUUID")
        }
    }

    override fun tick() {
        if (ownerOfSoul != null && !level().isClientSide) {
            val sLevel = level() as ServerLevel
            val playerOwner = sLevel.getPlayerByUUID(ownerOfSoul!!)
            if (playerOwner != null) {
                this.navigation.moveTo(playerOwner, 0.4)
            }
        }
    }

    /*
    @Override
    protected void updateWalkAnimation(float pParticalTick) {
        super.updateWalkAnimation(pParticalTick);
    }
    */
    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))

        //goalSelector.addGoal(1, BreedGoal(this, 1.15))
    }

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ARMOR_TOUGHNESS, 100.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 1.0)
        }

    }
}
