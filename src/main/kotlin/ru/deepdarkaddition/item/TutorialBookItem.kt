package ru.deepdarkaddition.item

import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraftforge.common.property.Properties

class TutorialBookItem(settings: Properties) : Item(settings) {
    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack?> {
        pPlayer.sendSystemMessage(Component.translatable("В кратце: этот мод дополняет в основном заброшенный город, сечас единственный способ" +
                "начать узнавать сюжет это убить скалкового крипера,  возле скалкового катализатора." +
                "Если вам кажется что он через чур сильный, то пожалуйста скажиете, мне об этом по ссылке https://github.com/Fhugu63/DeepDarkAddition/issues."))

        return super.use(pLevel, pPlayer, pUsedHand)
    }
}