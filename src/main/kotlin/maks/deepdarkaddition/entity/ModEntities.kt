package maks.deepdarkaddition.entity


import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityType.EntityFactory
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object ModEntities {
    val ENTITY_TYPES: DeferredRegister<EntityType<*>> =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MainScript.MOD_ID)


    val HUNGRYSOULENTITY = ENTITY_TYPES.register("hungrysoulentity") {
        EntityType.Builder.of(::HungrySoulEntity, MobCategory.CREATURE
        )
            .sized(5f, 5f).build("hungrysoulentity")
    };

    fun register(eventBus: IEventBus) {
        ENTITY_TYPES.register(eventBus)
    }
}
