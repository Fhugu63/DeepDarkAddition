package ru.deepdarkaddition.entity.global

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity

class SculkCreeperAI {
    protected var speedModifier = 1f

    fun moveToTarger(level: ServerLevel, target: Mob) {
        MoveToTargetSink().tryStart(level, target, 10)
    }
}