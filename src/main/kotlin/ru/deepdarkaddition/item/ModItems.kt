package ru.deepdarkaddition.item

import ru.deepdarkaddition.MainScript
import net.minecraft.ChatFormatting
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

class ModItems {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MainScript.MOD_ID)

    val mythycalRarity = Rarity.create("MYTHICAL", ChatFormatting.DARK_RED)

    val RESEARHDIARYPARTONE = REGISTRY.register("rdp_one") {
        ResearhDiaryPartOneItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTTWO = REGISTRY.register("rdp_two") {
        ResearhDiaryPartTwoItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTTHREE = REGISTRY.register("rdp_three") {
        ResearhDiaryPartThreeItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTFOUR = REGISTRY.register("rdp_four") {
        ResearhDiaryPartFourItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTFIVE = REGISTRY.register("rdp_five") {
        ResearhDiaryPartFiveItem(Item.Properties().rarity(Rarity.RARE))
    }

    val HUNGRYSOUL = REGISTRY.register("hungry_soul") {
        HungySoulItem(Item.Properties().rarity(mythycalRarity).stacksTo(1))
    }

    companion object {

    }
}