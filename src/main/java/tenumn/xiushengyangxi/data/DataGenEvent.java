package tenumn.xiushengyangxi.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber
public class DataGenEvent {
    @SubscribeEvent
    public static void genModData(GatherDataEvent event){
        DataGenerator dataGenerator=event.getGenerator();
        ExistingFileHelper existingFileHelper=event.getExistingFileHelper();
        ModItemModelProvider modItemModelProvider=new ModItemModelProvider(dataGenerator,existingFileHelper);
        dataGenerator.addProvider(modItemModelProvider);
    }
}
