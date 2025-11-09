package ru.deepdarkaddition.interfaces

import ru.deepdarkaddition.entity.custom.HungrySoulEntity
import net.minecraft.world.entity.Entity
import net.minecraftforge.common.capabilities.AutoRegisterCapability

@AutoRegisterCapability
interface IHungrySouls {
    var ownerOfSoul: MutableMap<HungrySoulEntity?, Entity>

    fun getSouls(): MutableMap<HungrySoulEntity?, Entity>?

    fun getPlayerBySoul(keySoul: HungrySoulEntity?): Entity?

    fun addSoul(soulEntity: HungrySoulEntity?, pEntity: Entity)

    fun removeSoul(removebleSoul: HungrySoulEntity)
}