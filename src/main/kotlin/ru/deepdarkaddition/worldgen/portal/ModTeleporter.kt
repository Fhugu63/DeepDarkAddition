package ru.deepdarkaddition.worldgen.portal

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.Fluids
import net.minecraftforge.common.util.ITeleporter
import ru.deepdarkaddition.block.ModBlocks
import ru.deepdarkaddition.block.PortalBlockToSculkDimension
import java.util.function.Function


class ModTeleporter(pos: BlockPos, insideDim: Boolean) : ITeleporter {
    init {
        thisPos = pos
        insideDimension = insideDim
    }

    override fun placeEntity(
        entity: Entity, currentWorld: ServerLevel?, destinationWorld: ServerLevel,
        yaw: Float, repositionEntity: Function<Boolean?, Entity?>
    ): Entity {
        var entity = entity
        entity = repositionEntity.apply(false)!!
        var y = 61

        if (!insideDimension) {
            y = thisPos.getY()
        }

        var destinationPos = BlockPos(thisPos.getX(), y, thisPos.getZ())

        var tries = 0
        while ((destinationWorld.getBlockState(destinationPos)
                .getBlock() !== Blocks.AIR) && !destinationWorld.getBlockState(destinationPos)
                .canBeReplaced(Fluids.WATER) &&
            (destinationWorld.getBlockState(destinationPos.above())
                .getBlock() !== Blocks.AIR) && !destinationWorld.getBlockState(destinationPos.above())
                .canBeReplaced(Fluids.WATER) && (tries < 25)
        ) {
            destinationPos = destinationPos.above(2)
            tries++
        }

        entity.setPos(
            destinationPos.getX().toDouble(),
            destinationPos.getY().toDouble(),
            destinationPos.getZ().toDouble()
        )

        if (insideDimension) {
            var doSetBlock = true
            for (checkPos in BlockPos.betweenClosed(
                destinationPos.below(10).west(10),
                destinationPos.above(10).east(10)
            )) {
                if (destinationWorld.getBlockState(checkPos).getBlock() is PortalBlockToSculkDimension) {
                    doSetBlock = false
                    break
                }
            }
            if (doSetBlock) {
                destinationWorld.setBlock(destinationPos, ModBlocks.PORTALBLOCKTOSCULKDIMENSION.get().defaultBlockState(), 3)
            }
        }

        return entity
    }

    companion object {
        var thisPos: BlockPos = BlockPos.ZERO
        var insideDimension: Boolean = true
    }
}