package ru.deepdarkaddition.item

import net.minecraft.core.Direction
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.UseOnContext
import ru.deepdarkaddition.block.ModBlocks

class MyPortalItem(properties: Properties) : Item(properties) {
    override fun useOn(context: UseOnContext): InteractionResult {
        val player = context.player
        val level = context.level
        val pos = context.clickedPos
        val facing = context.clickedFace

        // Пример упрощенной логики: если кликнули по рамке, пытаемся создать портал
        if (facing == Direction.UP && !level.isClientSide) {
            val portalPos = pos.above()
            if (level.isEmptyBlock(portalPos)) {
                // Здесь должна быть проверка формы рамки (прямоугольник)
                level.setBlock(portalPos, ModBlocks.PORTALBLOCKTOSCULKDIMENSION.get().defaultBlockState(), 3)
                return InteractionResult.SUCCESS
            }
        }
        return InteractionResult.CONSUME
    }
}
