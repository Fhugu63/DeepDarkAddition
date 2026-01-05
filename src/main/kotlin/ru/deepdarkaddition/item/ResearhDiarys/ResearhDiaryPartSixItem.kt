package ru.deepdarkaddition.item.ResearhDiarys

import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class ResearhDiaryPartSixItem(settings: Properties) : Item(settings) {
    var flag: Boolean = false
    //val diaryWindow: DiaryWindow = DiaryWindow(Component.translatable("test"), 1)

    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        pPlayer.sendSystemMessage(Component.translatable("diary.part6"))

        return super.use(pLevel, pPlayer, pUsedHand)
    }
}