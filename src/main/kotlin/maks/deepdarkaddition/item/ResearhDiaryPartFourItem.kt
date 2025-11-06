package maks.deepdarkaddition.item

import maks.deepdarkaddition.UI.DiaryWindow
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.network.chat.Component
import net.minecraft.client.Minecraft

class ResearhDiaryPartFourItem(settings: Properties) : Item(settings) {
    public var flag: Boolean = false
    val diaryWindow: DiaryWindow = DiaryWindow(Component.translatable("test"), 4)

    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!flag) {
            Minecraft.getInstance().setScreen(diaryWindow)

            flag = !flag
        }

        return super.use(pLevel, pPlayer, pUsedHand)
    }
}