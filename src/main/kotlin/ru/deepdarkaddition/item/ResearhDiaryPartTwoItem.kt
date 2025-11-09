package ru.deepdarkaddition.item

import ru.deepdarkaddition.UI.DiaryWindow
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.network.chat.Component
import net.minecraft.client.Minecraft

class ResearhDiaryPartTwoItem(settings: Properties) : Item(settings) {
    var flag: Boolean = false
    val diaryWindow: DiaryWindow = DiaryWindow(Component.translatable("test"), 2)

    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!flag) {
            diaryWindow.numOfPart = 2
            Minecraft.getInstance().setScreen(diaryWindow)

            flag = !flag
        }

        return super.use(pLevel, pPlayer, pUsedHand)
    }
}