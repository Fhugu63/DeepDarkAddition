package ru.deepdarkaddition.engine

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.Vec3


class Methods {
    val cs = CalculationScript()

    fun smoothMovement(entity: Entity, playerEntity: Entity, speed: Float) {
        val raznicaInPosition = cs.raznicaInPos(playerEntity.position(), entity.position())
        
        entity.moveTo(Vec3(
            if (raznicaInPosition.x>1) {(entity.x+speed)}
                else if (raznicaInPosition.x<1&&raznicaInPosition.x>-1) {entity.x} else {(entity.x-speed)},

            if (raznicaInPosition.y>1) {(entity.y+speed)}
                else if (raznicaInPosition.y<1&&raznicaInPosition.y>-1) {entity.y} else {(entity.y-speed)},

            if (raznicaInPosition.z>1) {(entity.z+speed)}
                else if (raznicaInPosition.z<1&&raznicaInPosition.z>-1) {entity.z} else {(entity.z-speed)}
        ))
    }
}

class MyEntity {
    private var numOfEatenSouls = 0

    // Переопределение методов сериализации
    fun writeNbt(tag: CompoundTag, entity: Entity) {
        tag.putInt("numOfEatenSouls", numOfEatenSouls);
    }

    protected fun readAdditionalSaveData(tag: CompoundTag) {
        numOfEatenSouls = if (tag.contains("numOfEatenSouls")) tag.getInt("numOfEatenSouls") else 0
    }
}
