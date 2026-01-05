package ru.deepdarkaddition.block

import ru.deepdarkaddition.MainScript
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object ModBlocks {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MainScript.MOD_ID)

    val PORTALBLOCKTOSCULKDIMENSION = REGISTRY.register("sculkdimensionportalblock") {
        Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .noLootTable()
            .noOcclusion()
            .noCollission()
        )
    }

    // the returned ObjectHolderDelegate can be used as a property delegate
    // this is automatically registered by the deferred registry at the correct times

}