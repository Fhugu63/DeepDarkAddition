package maks.deepdarkaddition.entity.custom

import maks.deepdarkaddition.entity.ModEntities
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.AnimationState
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity.createLivingAttributes
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class HungrySoulEntity(pEntityType: EntityType<out Animal>, pLevel: Level) : Animal(pEntityType, pLevel) {
    private fun setupAnimationStates() {

    }


    /*
    @Override
    protected void updateWalkAnimation(float pParticalTick) {
        super.updateWalkAnimation(pParticalTick);
    }
    */
    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))

        goalSelector.addGoal(1, BreedGoal(this, 1.15))
    }

    override fun getBreedOffspring(pLevel: ServerLevel, pOtherParent: AgeableMob): HungrySoulEntity? {
        return ModEntities.HUNGRYSOULENTITY.get().create(pLevel)
    }

    override fun isFood(pStack: ItemStack): Boolean {
        return super.isFood(pStack)
    }

    fun createAttributes(): AttributeSupplier.Builder {
        return createLivingAttributes()
            .add(Attributes.MAX_HEALTH, 10.0)
            .add(Attributes.MOVEMENT_SPEED, 0.5)
            .add(Attributes.ARMOR_TOUGHNESS, 0.5)
            .add(Attributes.ATTACK_KNOCKBACK, 0.0)
            .add(Attributes.FOLLOW_RANGE, 1.0)
    }

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ARMOR_TOUGHNESS, 0.5)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 1.0)
        }
    }
}
