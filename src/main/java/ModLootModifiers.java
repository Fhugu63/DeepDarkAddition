import com.mojang.serialization.Codec;
import maks.deepdarkaddition.MainScript;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MainScript.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            GLM.register("add_item", AddItemModifier.CODEC);


    public static void register(IEventBus eventBus) {
        GLM.register(eventBus);
    }
}