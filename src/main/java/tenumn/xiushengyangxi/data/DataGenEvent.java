package tenumn.xiushengyangxi.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvent {
    @SubscribeEvent
    public static void genModData(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        //注册方块状态和模型
        ModBlockStateProvider modBlockStateProvider = new ModBlockStateProvider(dataGenerator, existingFileHelper);
        dataGenerator.addProvider(modBlockStateProvider);
        //注册物品模型
        ModItemModelProvider modItemModelProvider = new ModItemModelProvider(dataGenerator, existingFileHelper);
        dataGenerator.addProvider(modItemModelProvider);
    }
}
