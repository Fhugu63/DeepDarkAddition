package maks.deepdarkaddition.UI

import maks.deepdarkaddition.DeepDarkAddition
import maks.deepdarkaddition.item.ModItems
import maks.deepdarkaddition.item.ResearhDiaryPartOne
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.*
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.FormattedText
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Display.TextDisplay
import java.lang.reflect.Array.set


class DiaryWindow(title: Component, getedNumOfPart: Int): Screen(title) {
    //отсчёт начинается с 1
    var numOfPart: Int = getedNumOfPart
        set(value) {
            if (value <= 5 && value > 0) {
                field = value
            }
        }

    var TextInDiary: String = "";

    init {
        var TextInDiary: String = when {
            numOfPart == 1 -> "1"
            numOfPart == 2 -> "2"
            numOfPart == 3 -> "3"
            numOfPart == 4 -> "4"
            numOfPart == 5 -> "5"
            else -> {
                "none"
            }
        }
        this.TextInDiary = TextInDiary
    }

    override fun init() {
        super.init()
        //this.addRenderableOnly(MultiLine(this.font, width/2-150, height/2-100, width/2+150, height/2+100, Component.translatable("fagagfd /ndsf")))
        //this.addRenderableOnly()
        this.addRenderableOnly(MultiLineTextWidget(100, 100, Component.translatable(TextInDiary), this.font))
        this.addRenderableOnly(ImageWidget(300, 190, ResourceLocation.tryBuild(DeepDarkAddition.MOD_ID,"textures/UI/TextureFowDiaryWindow")))
    }

    override fun render(graphics: GuiGraphics, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        //graphics.draw(this.font, "sadfhaer atrhnaja teh", width/2, height/2, 15)

        graphics.fill(width/2-150, height/2-200, width/2+150, height/2+200, 0, -1000255255)

        super.render(graphics, pMouseX, pMouseY, pPartialTick)
    }

    override fun removed() {
        ModItems.RESEARHDIARYPARTONE().flag = false
        super.removed()
    }
}
