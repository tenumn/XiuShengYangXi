package tenumn.xiushengyangxi.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.common.capabilities.IRecuperateCap;
import tenumn.xiushengyangxi.common.item.ItemShit;

public class ModRegistry {
    public static ItemGroup MISCGROUP = new ItemGroup(Utils.MODID + ".miscGroup") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModRegistry.Items.SHIT.get());
        }
    };
    @CapabilityInject(IRecuperateCap.class)//玩家养生能力
    public static Capability<IRecuperateCap> RECUPERATE_CAPABILITY;


    //实列化物品
    public static class Items {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MODID);
        public static final RegistryObject<Item> SHIT = ITEMS.register("shit", ItemShit::new);
    }

    //实现注册
    public static void registryAll() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistry.Items.ITEMS.register(bus);
        ParticleTypes.PARTICLE_TYPES.register(bus);
    }

    //实列化物品
    public static class ParticleTypes {
        public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Utils.MODID);
        public static final RegistryObject<ParticleType<BasicParticleType>> TEST = PARTICLE_TYPES.register("test", () -> new BasicParticleType(true));
    }
}
