package ru.deepdarkaddition

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.RegisterEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import ru.deepdarkaddition.block.ModBlocks
import ru.deepdarkaddition.engine.SoundListiner
import ru.deepdarkaddition.entity.ModEntities
import ru.deepdarkaddition.entity.client.SculkCreeper.SculkCreeperRenderer
import ru.deepdarkaddition.entity.client.luiza.HungrySoulRender
import ru.deepdarkaddition.entity.custom.SculkCreeperEntity
import ru.deepdarkaddition.events.ModEvents
import ru.deepdarkaddition.item.ModItems
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist


/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An maks for blocks is in the `blocks` package of this mod.
 */
@Mod(MainScript.MOD_ID)
class MainScript {
    init {
        LOGGER.log(Level.INFO, "Hello world!")

        // Register the KDeferredRegister to the mod-specific events bus
        ModBlocks.REGISTRY.register(MOD_BUS)

        ModItems().REGISTRY.register(MOD_BUS)


        ModEntities.register(MOD_BUS)

        MinecraftForge.EVENT_BUS.register(ModEvents())
        MinecraftForge.EVENT_BUS.register(SoundListiner())

        //MinecraftForge.EVENT_BUS.register(ModEvents())

        //ModLootModifiers.register(MOD_BUS)

        //MOD_BUS.addListener(this::modEventHandler);

        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                //Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific events bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
        EntityRenderers.register(ModEntities.HUNGRYSOULENTITY.get(), ::HungrySoulRender)
        EntityRenderers.register(ModEntities.SCULKCREEPERENTITY.get(), ::SculkCreeperRenderer)

        //ModEvents().provider.ifPresent { cap -> ModEvents().ownerOfSoul = cap.getSouls()!! }
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")

    }

    private fun modEventHandler(event: RegisterEvent) {

    }
    
    companion object {
        const val MOD_ID = "deepdarkaddition"

        // the logger for our mod
        @JvmField val LOGGER: Logger = LogManager.getLogger(MOD_ID)
    }
    
}


