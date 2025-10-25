package maks.deepdarkaddition.datagen

//import ModGlobalLootModifiersProvider
import maks.deepdarkaddition.MainScript
import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = MainScript.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
class DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val genrator: DataGenerator = event.generator
        val packOutput: PackOutput = genrator.getPackOutput()

        //val xz = genrator.addProvider(event.includeServer(), ModGlobalLootModifiersProvider(packOutput))
    }
}