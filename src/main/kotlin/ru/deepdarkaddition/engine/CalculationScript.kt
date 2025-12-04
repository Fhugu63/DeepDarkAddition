package ru.deepdarkaddition.engine

import net.minecraft.client.Minecraft
import net.minecraft.world.phys.Vec3

class CalculationScript {


    fun calculateCenterOfScrennX(widthOfImage: Int): Int {
        val minecraft: Minecraft = Minecraft.getInstance()

         val widthOfScreen = minecraft.screen?.width ?: 1
         val heightOfScreen = minecraft.screen?.height ?: 1

        return (widthOfScreen/2) + (widthOfImage/2)
    }
    fun calculateCenterOfScrennY(heightOfImage: Int): Int {
        val minecraft: Minecraft = Minecraft.getInstance()

         val widthOfScreen = minecraft.screen?.width ?: 1
         val heightOfScreen = minecraft.screen?.height ?: 1

        return ((heightOfScreen) + (heightOfImage))/3
    }

    fun raznicaInPos(pos1: Vec3, pos2: Vec3): Vec3 {
        //val newPos = Vec3(abs(pos1.x - pos2.x), abs(pos1.y - pos2.y), abs(pos1.z - pos2.z))
        val newPos = Vec3(pos1.x - pos2.x, pos1.y - pos2.y, pos1.z - pos2.z)

        return newPos
    }
}