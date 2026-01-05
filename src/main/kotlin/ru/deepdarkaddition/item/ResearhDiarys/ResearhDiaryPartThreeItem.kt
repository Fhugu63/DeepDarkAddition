package ru.deepdarkaddition.item.ResearhDiarys

import ru.deepdarkaddition.UI.DiaryWindow
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.network.chat.Component
import net.minecraft.client.Minecraft

class ResearhDiaryPartThreeItem(settings: Properties) : Item(settings) {
    var flag: Boolean = false
    //val diaryWindow: DiaryWindow = DiaryWindow(Component.translatable("test"), 1)

    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        pPlayer.sendSystemMessage(Component.translatable("diary.part3"))

        return super.use(pLevel, pPlayer, pUsedHand)
    }
}