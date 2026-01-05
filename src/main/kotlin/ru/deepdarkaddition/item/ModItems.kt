package ru.deepdarkaddition.item

import ru.deepdarkaddition.MainScript
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.deepdarkaddition.item.ResearhDiarys.ResearhDiaryPartOneItem
import ru.deepdarkaddition.item.ResearhDiarys.ResearhDiaryPartThreeItem
import ru.deepdarkaddition.item.ResearhDiarys.ResearhDiaryPartTwoItem
import ru.deepdarkaddition.item.ResearhDiarys.ResearhDiaryPartFourItem
import ru.deepdarkaddition.item.ResearhDiarys.ResearhDiaryPartFiveItem
import ru.deepdarkaddition.item.ResearhDiarys.ResearhDiaryPartSixItem
import ru.deepdarkaddition.item.ResearhDiarys.ResearhDiaryPartSevenItem

class ModItems {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MainScript.MOD_ID)

    //val mythycalRarity = Rarity.create("MYTHICAL", ChatFormatting.DARK_RED)

    val RESEARHDIARYPARTONE = REGISTRY.register("rdp_one") {
        ResearhDiaryPartOneItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTTWO = REGISTRY.register("rdp_two") {
        ResearhDiaryPartTwoItem(Item.Properties().rarity(Rarity.RARE))
    }

    val TUTORIALBOOKITEM = REGISTRY.register("tutorial_book") {
        TutorialBookItem(Item.Properties().stacksTo(1))
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

    val RESEARHDIARYPARTSIX = REGISTRY.register("rdp_six") {
        ResearhDiaryPartSixItem(Item.Properties().rarity(Rarity.RARE))
    }

    val RESEARHDIARYPARTSEVEN = REGISTRY.register("rdp_seven") {
        ResearhDiaryPartSevenItem(Item.Properties().rarity(Rarity.RARE))
    }

    val MYPORTALITEM = REGISTRY.register("myportalitem") {
        MyPortalItem(Item.Properties().stacksTo(1))
    }

    companion object {

    }
}