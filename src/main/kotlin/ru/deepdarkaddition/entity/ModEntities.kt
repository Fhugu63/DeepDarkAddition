package ru.deepdarkaddition.entity


import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.entity.custom.HungrySoulEntity
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object ModEntities {
    val ENTITY_TYPES: DeferredRegister<EntityType<*>> =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MainScript.MOD_ID)


    @JvmField val HUNGRYSOULENTITY = ENTITY_TYPES.register("hungrysoulentity") {
        EntityType.Builder.of(::HungrySoulEntity, MobCategory.CREATURE
        )
            .sized(0.5f, 0.5f).build("hungrysoulentity")
    }

    @JvmField val SCULKCREEPERENTITY = ENTITY_TYPES.register("sculkcreeperentity") {
        EntityType.Builder.of(::SculkCreeperEntity, MobCategory.MONSTER)
            .sized(1f, 1.8f).build("sculkcreeperentity")
            //.defaultLootTable.
    }

    fun register(eventBus: IEventBus) {
        ENTITY_TYPES.register(eventBus)
    }
}
