package maks.deepdarkaddition

import net.minecraft.client.Minecraft

class CalculationScript {
    val minecraft: Minecraft = Minecraft.getInstance()

    private val widthOfScreen = minecraft.screen?.width ?: 1
    private val heightOfScreen = minecraft.screen?.height ?: 1

    fun calculateCenterOfScrennX(widthOfImage: Int): Int {
        return (widthOfScreen/2) + (widthOfImage/2)
    }
    fun calculateCenterOfScrennY(heightOfImage: Int): Int {
        return ((heightOfScreen) + (heightOfImage))/3
    }
}