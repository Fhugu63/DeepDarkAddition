package ru.deepdarkaddition

import ru.deepdarkaddition.interfaces.IHungrySouls
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.items.IItemHandler


class DataSaver {
    val ITEM_HANDLER: Capability<IHungrySouls?>? = CapabilityManager.get<IHungrySouls?>(object : CapabilityToken<IHungrySouls?>() {})
    val ITEM_HANDLER1: Capability<IItemHandler?>? = CapabilityManager.get<IItemHandler?>(object : CapabilityToken<IItemHandler?>() {})

    //val test = Minecraft.getInstance().level.



    fun create(): DataSaver {
        return DataSaver()
    }

    fun load(tag: CompoundTag?): DataSaver {
        val data: DataSaver = this.create()
        // Load saved data
        return data
    }

    //val test = DimensionDataStorage(File("./<level_folder>/DIM-1/data/"), DataFixer {})
}