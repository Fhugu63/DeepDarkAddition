package maks.deepdarkaddition.UI

import com.mojang.blaze3d.vertex.PoseStack
import maks.deepdarkaddition.CalculationScript
import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.item.ModItems
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.*
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import kotlin.properties.Delegates


class DiaryWindow(title: Component, getedNumOfPart: Int): Screen(title) {
    //отсчёт начинается с 1
    var numOfPart: Int = getedNumOfPart
        set(value) {
            if (value <= 5 && value > 0) {
                field = value
            }
        }

    var TextInDiary: String = "";

    val Location = ResourceLocation.tryBuild(MainScript.MOD_ID, "textures/ui/texturefordiarywindow.png")

    val cs = CalculationScript()

    init {
        var TextInDiary: String = when {
            numOfPart == 1 -> "З-здравствуйте искатели, \n" +
                    "я решил начать дневник\n" +
                    "так как обнаружил какой-то\n" +
                    "заброшенный город который\n" +
                    "полностью окутон какой-то\n" +
                    "чёрной, непонятной массой.\n" +
                    "Спустя час после прошлой\n" +
                    "записи, я понял что лучше...\n" +
                    "Не шуметь... Если же вы ре-\n" +
                    "шите издать громкий звук,\n" +
                    "то из под земли выле-\n" +
                    "зит какая-то сущность,\n" +
                    "я решил назвать её «Варден»."
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

    lateinit var widgetText: MultiLineTextWidget

    var widthOfBook by Delegates.notNull<Int>()
    var heightOfBook by Delegates.notNull<Int>()

    override fun init() {
        super.init()
        //this.addRenderableOnly(MultiLine(this.font, width/2-150, height/2-100, width/2+150, height/2+100, Component.translatable("fagagfd /ndsf")))
        //this.addRenderableOnly()
        val screen: Screen? = Minecraft.getInstance().screen
        widthOfBook = (screen?.width ?: 1) / 2
        heightOfBook = (((screen?.width ?: 1)/2)/1.5).toInt()






    }

    override fun render(graphics: GuiGraphics, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        val book = addRenderableWidget(ImageWidget(widthOfBook, heightOfBook, Location))
        book.x = cs.calculateCenterOfScrennX(widthOfBook)
        book.y = cs.calculateCenterOfScrennY(heightOfBook)

        book.render(graphics, pMouseX, pMouseY, pPartialTick)

        val screen: Screen? = Minecraft.getInstance().screen
        val scaleX =  (((screen?.width ?: 1)/2)/210).toFloat()
        val scaleY = scaleX*1.4F
        //graphics.draw(this.font, "sadfhaer atrhnaja teh", width/2, height/2, 15)
        val multilineText = MultiLineTextWidget(book.x/10, book.y, Component.translatable(TextInDiary), this.font)


        //widgetText = this.addRenderableOnly(multilineText)
        multilineText.setColor(0)
        //graphics.fill(width/2-150, height/2-200, width/2+150, height/2+200, 0, -1000255255)
        val pose: PoseStack = graphics.pose()
        pose.pushPose()
        //widgetText.render(graphics, pMouseX, pMouseY, pPartialTick)

        multilineText.x = cs.calculateCenterOfScrennX(scaleX.toInt())
        pose.scale(scaleX, scaleY, 1.0F)
        multilineText.render(graphics, pMouseX, pMouseY, pPartialTick)
        pose.popPose()


        //multilineText.width = ((screen?.width ?: 1)/4.5).toInt()
        //multilineText.height = ((height/4/0.5)/2).toInt()



        //widgetText.render(graphics, pMouseX, pMouseY, pPartialTick)
        this.renderBackground(graphics);
        //widgetText.width = (width/4.5).toInt()
        //widgetText.height = ((height/4/0.5)/2).toInt()

        super.render(graphics, pMouseX, pMouseY, pPartialTick)
    }

    override fun removed() {
        ModItems().RESEARHDIARYPARTONE().flag = false
        super.removed()
    }
}
