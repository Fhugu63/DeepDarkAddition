package ru.deepdarkaddition.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import ru.deepdarkaddition.MainScript
import ru.deepdarkaddition.worldgen.dimensions.ModDimensions
import java.util.concurrent.CompletableFuture

class ModWorldGenProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    DatapackBuiltinEntriesProvider(output, registries, BUILDER, setOf(MainScript.MOD_ID)) {

    companion object {
        val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstapType)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
    }
}