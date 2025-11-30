package ru.deepdarkaddition.entity

import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity

class FollowPlayerGoal(private val entity: SculkCreeperEntity) : Goal() {

    private val navigation: PathNavigation = entity.navigation

    override fun canUse(): Boolean {
        val nearestPlayer = entity.detectNearestPlayer(entity)
        return nearestPlayer != null && navigation.isDone
    }

    override fun canContinueToUse(): Boolean {
        val nearestPlayer = entity.detectNearestPlayer(entity)
        return nearestPlayer != null && !navigation.isDone
    }

    override fun start() {
        val nearestPlayer = entity.detectNearestPlayer(entity)
        if (nearestPlayer != null) {
            navigation.moveTo(nearestPlayer.x, nearestPlayer.y, nearestPlayer.z, 1.0)
        }
    }

    override fun stop() {
        navigation.stop()
    }

    override fun tick() {
        // Оставляем пустым, так как moveTo() обновляет путь автоматически
    }
}