package tenumn.xiushengyangxi.joker;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tenumn.xiushengyangxi.Utils;


public class JokerRegistry {

    public static class Items{
    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MODID);

    }

    public static void registryAll(){
        IEventBus bus= FMLJavaModLoadingContext.get().getModEventBus();
        Items.ITEMS.register(bus);
    }
}
