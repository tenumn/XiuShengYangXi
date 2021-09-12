package tenumn.xiushengyangxi.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.common.item.ItemShit;
import tenumn.xiushengyangxi.joker.JokerRegistry;

public class ModRegistry {
    public static ItemGroup MISCGROUP = new ItemGroup(Utils.MODID + ".miscGroup") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModRegistry.Items.SHIT.get());
        }
    };

    //实现注册
    public static void registryAll() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        JokerRegistry.Items.ITEMS.register(bus);
    }

    //实列化物品
    public static class Items {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MODID);
        public static final RegistryObject<Item> SHIT = ITEMS.register("shit", ItemShit::new);
    }
}
