package ru.deepdarkaddition.engine

import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.AABB
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.EntityEvent
import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity
import kotlin.collections.forEach

class SoundListiner {
    var backTickOnGround = false

    @SubscribeEvent
    fun playerTick(event: TickEvent.PlayerTickEvent) {
        val player = event.player
        val entity = player as Entity
        val pos = player.position()
        val pPos = BlockPos(
            Vec3i(player.position().x.toInt(), player.position().y.toInt(), player.position().z.toInt())
        )


        var itTickOnGround = player.onGround()

        if (backTickOnGround != itTickOnGround) {
            val sculkCreepers: List<SculkCreeperEntity> = entity.level().getEntitiesOfClass(SculkCreeperEntity::class.java, AABB.ofSize(pos, 32.0, 32.0, 32.0))
            sculkCreepers.forEach { sculkCreeperEntity -> sculkCreeperEntity.soundVibration(entity, pPos) }
        }
        backTickOnGround = itTickOnGround
        //if (player.)
    }

    @SubscribeEvent
    fun onPlayerMove(event: EntityEvent.EnteringSection) {
        if (event.entity != null) {
            if (event.getEntity().xo != event.getEntity().getX() ||
                event.getEntity().yo != event.getEntity().getY() ||
                event.getEntity().zo != event.getEntity().getZ()
            ) {
                val entity = event.entity
                val pos = entity.blockPosition()

                if (entity.isCrouching) {
                    val sculkCreepers: List<SculkCreeperEntity> = entity.level()
                        .getEntitiesOfClass(SculkCreeperEntity::class.java, AABB.ofSize(pos.center, 32.0, 32.0, 32.0))
                    sculkCreepers.forEach { sculkCreeperEntity -> sculkCreeperEntity.soundVibration(entity, pos, 6) }
                } else if (!entity.isCrouching) {
                    val sculkCreepers: List<SculkCreeperEntity> = entity.level()
                        .getEntitiesOfClass(SculkCreeperEntity::class.java, AABB.ofSize(pos.center, 32.0, 32.0, 32.0))
                    sculkCreepers.forEach { sculkCreeperEntity -> sculkCreeperEntity.soundVibration(entity, pos, 14) }
                }
            }
        }
    }

    @SubscribeEvent
    fun onPlaceBlock(event: BlockEvent.EntityPlaceEvent) {
        val pos = event.pos
        val sculkCreepers: List<SculkCreeperEntity> = event.entity?.level()!!.getEntitiesOfClass(SculkCreeperEntity::class.java, AABB.ofSize(pos.center, 32.0, 32.0, 32.0))
        sculkCreepers.forEach { sculkCreeperEntity -> sculkCreeperEntity.soundVibration(event.entity, pos) }
    }

    @SubscribeEvent
    fun onDestroyBlock(event: BlockEvent.BreakEvent) {
        val entity = event.player as Entity
        val pos = event.pos
        val sculkCreepers: List<SculkCreeperEntity> = entity.level().getEntitiesOfClass(SculkCreeperEntity::class.java, AABB.ofSize(pos.center, 32.0, 32.0, 32.0))
        sculkCreepers.forEach { sculkCreeperEntity -> sculkCreeperEntity.soundVibration(entity, pos) }
    }

}