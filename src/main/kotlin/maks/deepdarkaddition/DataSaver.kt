package maks.deepdarkaddition

import maks.deepdarkaddition.additionstominecraft.ModDataCapability
import net.minecraft.client.Minecraft
import net.minecraft.commands.arguments.SlotArgument.slot
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler


class DataSaver {
    val ITEM_HANDLER: Capability<ModDataCapability?>? = CapabilityManager.get<ModDataCapability?>(object : CapabilityToken<ModDataCapability?>() {})
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