package maks.deepdarkaddition.item

import maks.deepdarkaddition.DeepDarkAddition
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModItems {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, DeepDarkAddition.MOD_ID)

    val RESEARHDIARYPARTONE = REGISTRY.registerObject("rdp_one") {
        ResearhDiaryPartOne(Item.Properties().rarity(Rarity.RARE))
    }


}