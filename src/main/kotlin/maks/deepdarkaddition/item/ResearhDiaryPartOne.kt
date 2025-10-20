package maks.deepdarkaddition.item

import maks.deepdarkaddition.UI.DiaryWindow
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.ThrownEnderpearl
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui

class ResearhDiaryPartOne(settings: Properties) : Item(settings) {
    public var flag: Boolean = false
    val diaryWindow: DiaryWindow = DiaryWindow(Component.translatable("test"), 1)

    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!flag) {
            diaryWindow.numOfPart = 0
            Minecraft.getInstance().setScreen(diaryWindow)

            flag = !flag
        }

        return super.use(pLevel, pPlayer, pUsedHand)
    }
}