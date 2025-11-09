package ru.deepdarkaddition.DataSavers

import net.minecraft.core.Direction
import ru.deepdarkaddition.interfaces.IHungrySouls
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import ru.deepdarkaddition.events.ModEvents


class OwnerOfHungrySoulCapability {
    val OWNEROFSOUL_HANDLER: Capability<IHungrySouls> = CapabilityManager.get<IHungrySouls>(object : CapabilityToken<IHungrySouls>() {})


    //var OwnerOfSoulHandlerLazyOptional = ModEvents().ihungrySouls

    /*public fun <T> getCapability(cap: Capability<T?>?, side: Direction?): LazyOptional<T?> {
        if (cap === ForgeCapabilities.ITEM_HANDLER) {
            return OwnerOfSoulHandlerLazyOptional.cast()
        }
        return getCapability(cap, side)
    }

    public fun invalidateCaps() {
        OwnerOfSoulHandlerLazyOptional.invalidate()
    }*/

}