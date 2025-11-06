package maks.deepdarkaddition.entity.custom

import maks.deepdarkaddition.entity.ModEntities
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity.createLivingAttributes
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.Level

class SculkCreeperEntity(pEntityType: EntityType<out Animal>?, pLevel: Level?) : Animal(pEntityType, pLevel) {
    private fun setupAnimationStates() {

    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))

        goalSelector.addGoal(1, BreedGoal(this, 1.15))
    }

    override fun getBreedOffspring(pLevel: ServerLevel, pOtherParent: AgeableMob): SculkCreeperEntity? {
        return ModEntities.SCULKCREEPERENTITY.get().create(pLevel)
    }

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ARMOR_TOUGHNESS, 100.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 1.0)
        }

    }
}