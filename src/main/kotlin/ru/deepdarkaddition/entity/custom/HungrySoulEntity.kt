package ru.deepdarkaddition.entity.custom

import net.minecraft.nbt.CompoundTag
import ru.deepdarkaddition.entity.ModEntities
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.Level

class HungrySoulEntity(pEntityType: EntityType<out Animal>?, pLevel: Level?) : Animal(pEntityType, pLevel) {
    private fun setupAnimationStates() {

    }

    var numOfEatenSouls = 0

    fun saveAdditional(nbt: CompoundTag) {
        this.saveAdditional(nbt)
        nbt.putInt("numOfEatenSouls", numOfEatenSouls) // Сохраняем своё значение
    }

    fun loadAdditional(nbt: CompoundTag): Int {
        numOfEatenSouls = nbt.getInt("numOfEatenSouls") // Читаем своё значение
        return numOfEatenSouls
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

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ARMOR_TOUGHNESS, 100.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 1.0)
        }

    }
}
