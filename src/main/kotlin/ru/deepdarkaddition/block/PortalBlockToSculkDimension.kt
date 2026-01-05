package ru.deepdarkaddition.block

import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceKey
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import ru.deepdarkaddition.worldgen.portal.ModTeleporter
import ru.deepdarkaddition.worldgen.dimensions.ModDimensions

class PortalBlockToSculkDimension(properties: Properties) : Block(properties) {
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        println("used!")
        if (pPlayer.canChangeDimensions()) {
            handleSculkPortal(pPlayer, pPos)
            return InteractionResult.SUCCESS
        } else {
            handleSculkPortal(pPlayer, pPos)
            return InteractionResult.CONSUME
        }
    }

    private fun handleSculkPortal(player: Entity, pPos: BlockPos) {
        if (player.level() is ServerLevel) {
            val sLevel: ServerLevel = player.level() as ServerLevel

            val minecraftServer: MinecraftServer = sLevel.server
            val resourceKey: ResourceKey<Level> = if (player.level().dimension() == ModDimensions.SCULKDIM_LEVEL_KEY) {
                Level.OVERWORLD
            } else {
                ModDimensions.SCULKDIM_LEVEL_KEY
            }

            val portalDimension: ServerLevel? = minecraftServer.getLevel(resourceKey)
            if (portalDimension != null && !player.isPassenger) {
                player.changeDimension(portalDimension, ModTeleporter(pPos, true))
                if (resourceKey == ModDimensions.SCULKDIM_LEVEL_KEY) {
                    player.changeDimension(portalDimension, ModTeleporter(pPos, true))
                } else {
                    player.changeDimension(portalDimension, ModTeleporter(pPos, false))
                }
            }
        }
    }
}