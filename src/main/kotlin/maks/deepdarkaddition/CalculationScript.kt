package maks.deepdarkaddition

import net.minecraft.client.Minecraft
import net.minecraft.world.phys.Vec3
import kotlin.math.abs

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

    fun raznicaInPos(pos1: Vec3, pos2: Vec3): Vec3 {
        //val newPos = Vec3(abs(pos1.x - pos2.x), abs(pos1.y - pos2.y), abs(pos1.z - pos2.z))
        val newPos = Vec3(pos1.x-pos2.x, pos1.y-pos2.y, pos1.z-pos2.z)

        return newPos
    }
}