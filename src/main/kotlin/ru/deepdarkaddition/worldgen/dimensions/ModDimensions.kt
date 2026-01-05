package ru.deepdarkaddition.worldgen.dimensions

import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.Climate
import net.minecraft.world.level.biome.Climate.ParameterList
import net.minecraft.world.level.biome.MultiNoiseBiomeSource
import net.minecraft.world.level.dimension.BuiltinDimensionTypes
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import ru.deepdarkaddition.MainScript
import java.util.*
import java.util.List


class ModDimensions {
    companion object {
        val SCULKDIM_KEY: ResourceKey<LevelStem> = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(MainScript.MOD_ID, "sculkdim"))

        val SCULKDIM_LEVEL_KEY: ResourceKey<Level> = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(MainScript.MOD_ID, "sculkdim"))

        val SCULK_DIM_TYPE: ResourceKey<DimensionType> = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(MainScript.MOD_ID, "sculkdim_type"))

        fun bootstapType(context: BootstapContext<DimensionType>) {
            context.register(SCULK_DIM_TYPE, DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                false,
                1.0,
                true,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.8f,
                DimensionType.MonsterSettings(false, false,
                    ConstantInt.of(0), 0)
            ))
        }

        fun bootstrapStem(context: BootstapContext<LevelStem>) {
            val biomeRegistry: HolderGetter<Biome> = context.lookup(Registries.BIOME)
            val dimTypes: HolderGetter<DimensionType> = context.lookup(Registries.DIMENSION_TYPE)
            val noiseGenSettings: HolderGetter<NoiseGeneratorSettings> = context.lookup(Registries.NOISE_SETTINGS)


            val noiseBasedChunkGenerator = NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                    ParameterList(
                        List.of(
                            com.mojang.datafixers.util.Pair.of(
                                Climate.parameters(0.1f, 0.2f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f),
                                biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)
                            ),
                            com.mojang.datafixers.util.Pair.of(
                                Climate.parameters(0.3f, 0.6f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f),
                                biomeRegistry.getOrThrow(Biomes.OCEAN)
                            ),
                            com.mojang.datafixers.util.Pair.of(
                                Climate.parameters(0.4f, 0.3f, 0.2f, 0.1f, 0.0f, 0.0f, 0.0f),
                                biomeRegistry.getOrThrow(Biomes.DARK_FOREST)
                            )

                        )
                    )
                ),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED)
            )

            val stem = LevelStem(dimTypes.getOrThrow(ModDimensions.SCULK_DIM_TYPE), noiseBasedChunkGenerator)

            context.register(SCULKDIM_KEY, stem)
        }
    }
}