package ru.deepdarkaddition.entity.global

import com.google.common.collect.ImmutableList
import com.mojang.serialization.Dynamic
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.Brain
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.behavior.*
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.minecraft.world.entity.ai.sensing.Sensor
import net.minecraft.world.entity.ai.sensing.SensorType
import net.minecraft.world.entity.monster.warden.Warden
import net.minecraft.world.entity.schedule.Activity
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity
import java.util.function.Predicate

class SculkCreeperAI {
    companion object {
        protected var speedModifier = 1f
        val SENSOR_TYPES: List<SensorType<out Sensor<in SculkCreeperEntity>>> =
            listOf<SensorType<out Sensor<in SculkCreeperEntity>>>(SensorType.NEAREST_PLAYERS);
        val MEMORY_TYPES = listOf<MemoryModuleType<*>>(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.ATTACK_TARGET,
        );

        fun configureBrainBehaviours(pSC: SculkCreeperEntity, brain: Brain<SculkCreeperEntity>) {
            brain.addActivity(
                Activity.CORE,
                0,
                // Всегда смотреть на цель, если она есть
                ImmutableList.of(LookAtTargetSink(45, 90) as BehaviorControl<in SculkCreeperEntity>?)
            )

            // --- IDLE Activity (бездействие, когда нет угрозы) ---
            brain.addActivity(
                Activity.IDLE, 10, // Приоритет 10 (низкий)
                ImmutableList.of(
                    RandomStroll.stroll(0.5f, 20, 5),
                    SetWalkTargetFromBlockMemory.create(MemoryModuleType.HOME, 1f, 10, 1, 10) // Возможно, здесь просто SetWalkTarget(0.5f)? AwayFrom обычно для убегания.
                ) as ImmutableList<out BehaviorControl<in SculkCreeperEntity>?>
            )

            // --- FIGHT Activity (бой, когда есть цель ATTACK_TARGET в памяти) ---
            brain.addActivity(
                Activity.FIGHT, 5, // Приоритет 5 (выше, чем IDLE)
                ImmutableList.of(

                SetEntityLookTarget.create(Predicate { p_219535_: LivingEntity? ->
                    this.isTarget(
                        pSC,
                        p_219535_
                    )
                }, pSC.getAttributeValue(Attributes.FOLLOW_RANGE).toFloat()),
                SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(1.2f),
                SonicBoom(),
                MeleeAttack.create(18)
            ) as ImmutableList<out BehaviorControl<in SculkCreeperEntity>?>
            )

            // 3. Устанавливаем, какие режимы активны по умолчанию
            brain.setCoreActivities(setOf(Activity.CORE)) // CORE должен быть всегда активен
            brain.setDefaultActivity(Activity.IDLE)
            //brain.updateActivityFromRiskAndBrain(this) // Обновляем мозг
        }

        private fun isTarget(pSC: SculkCreeperEntity, pEntity: LivingEntity?): Boolean {
            return pSC.getBrain().getMemory<LivingEntity?>(MemoryModuleType.ATTACK_TARGET)
                .filter(Predicate { p_219509_: LivingEntity? -> p_219509_ === pEntity }).isPresent()
        }

        fun makeBrain(sculkCreeper: SculkCreeperEntity, pOps: Dynamic<*>): Brain<SculkCreeperEntity> {
            var sCBrainProvider: Brain.Provider<SculkCreeperEntity> = Brain.provider(MEMORY_TYPES, SENSOR_TYPES)
            var sCBrain: Brain<SculkCreeperEntity> = sCBrainProvider.makeBrain(pOps)

            // !!! ВЫЗЫВАЕМ НАСТРОЙКУ АКТИВНОСТЕЙ ЗДЕСЬ !!!
            configureBrainBehaviours(sculkCreeper, sCBrain)

            return sCBrain
        }

        fun updateActivity(pSC: SculkCreeperEntity) {
            pSC.getBrain().setActiveActivityToFirstValid(
                ImmutableList.of<Activity?>(
                    Activity.FIGHT
                )
            )
        }
    }
}