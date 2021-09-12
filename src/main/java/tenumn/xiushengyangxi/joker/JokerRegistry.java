package tenumn.xiushengyangxi.joker;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.joker.item.ItemShit;


public class JokerRegistry {
    public static ItemGroup JOKERGROUP=new ItemGroup(Utils.MODID+".jokerGroup") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.SHIT.get());
        }
    };
    public static class Items{
    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MODID);
    public static final RegistryObject<Item> SHIT=ITEMS.register("shit", ItemShit::new);
    }

    public static void registryAll(){
        IEventBus bus= FMLJavaModLoadingContext.get().getModEventBus();
        Items.ITEMS.register(bus);
    }
}
