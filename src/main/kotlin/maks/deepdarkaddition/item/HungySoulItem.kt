package maks.deepdarkaddition.item

import maks.deepdarkaddition.MainScript
import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class HungySoulItem(settings: Properties): Item(settings) {
    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {


        return super.use(pLevel, pPlayer, pUsedHand)
    }

    override fun appendHoverText(pStack: ItemStack, pLevel: Level?, pTooltipComponents: MutableList<Component>, pIsAdvanced: TooltipFlag) {
        if (Minecraft.getInstance().languageManager.selected == "en_us") {
            pTooltipComponents.add(Component.translatable(ChatFormatting.YELLOW.toString() + "A mythical item... \n" +
                    "The hungry soul - a soul that has been trapped inside the body of the guardian of the ancient city for centuries. If you feed it, it may grant your wish"))
        } else if (Minecraft.getInstance().languageManager.selected == "ru_ru") {
            pTooltipComponents.add(Component.translatable(ChatFormatting.YELLOW.toString() + "Мифический предмет... \nГолодная душа - душа, которая была заперта внунтри тела хранителя древнего города столетиями. Если её накормить, то возможно, она исполнит ваше желание"))
        }
        MainScript.LOGGER.info("Selected language: "+Minecraft.getInstance().languageManager.selected)
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced)
    }
}