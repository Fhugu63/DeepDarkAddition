package ru.deepdarkaddition.engine

import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import java.lang.reflect.Method

@Retention
annotation class ReactOnVibrations()


class AnnotationProcessor {
    private var listenerObject: Any? = null // Экземпляр объекта, содержащего методы с аннотацией

    constructor(listenerObject: Any) {
        this.listenerObject = listenerObject
    }

    /**
     * Вызывает все методы, помеченные аннотацией CallOnEvent
     */
    @Throws
    fun invokeAnnotatedMethods(entity: Entity?) {
        val clazz: Class<*> = listenerObject!!.javaClass
        val methods: Array<Method> = clazz.declaredMethods

        for (method in methods) {
            if (method.isAnnotationPresent(ReactOnVibrations::class.java)) {
                method.invoke(listenerObject, entity)

                println(method.isAnnotationPresent(ReactOnVibrations::class.java))
            }
            println("work!")
        }
        println(methods.get(0).toString())
    }
}