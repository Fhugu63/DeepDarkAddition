package maks.deepdarkaddition.item

import maks.deepdarkaddition.MainScript
import net.minecraft.ChatFormatting
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.Rarity
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

class ModItems {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MainScript.MOD_ID)

    val mythycalRarity = Rarity.create("MYTHICAL", ChatFormatting.DARK_RED)

    val RESEARHDIARYPARTONE = REGISTRY.registerObject("rdp_one") {
        ResearhDiaryPartOneItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTTWO = REGISTRY.registerObject("rdp_two") {
        ResearhDiaryPartTwoItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTTHREE = REGISTRY.registerObject("rdp_three") {
        ResearhDiaryPartThreeItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTFOUR = REGISTRY.registerObject("rdp_four") {
        ResearhDiaryPartFourItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTFIVE = REGISTRY.registerObject("rdp_five") {
        ResearhDiaryPartFiveItem(Item.Properties().rarity(Rarity.RARE))
    }

    val HUNGRYSOUL = REGISTRY.registerObject("hungry_soul") {
        HungySoulItem(Item.Properties().rarity(mythycalRarity).stacksTo(1))
    }

    companion object {

    }
}