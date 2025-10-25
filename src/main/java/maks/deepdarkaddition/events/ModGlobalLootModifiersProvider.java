package maks.deepdarkaddition.events;

import maks.deepdarkaddition.MainScript;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

/*
class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, MainScript.MOD_ID);

    }

    @Override
    protected void start() {/*
        add("hungry_soul_from_grass", new AddItemModifier(
                    new LootItemCondition[] {
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                    LootItemRandomChanceCondition.randomChance(1f).build()
                }
        ));

    }*

}
*/