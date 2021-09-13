package tenumn.xiushengyangxi.joker;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.joker.block.WheatHoloGraphicRender;
import tenumn.xiushengyangxi.joker.block.WheatHolographicBlock;
import tenumn.xiushengyangxi.joker.block.WheatHolographicTileEntity;


public class JokerRegistry {
    //代理注册
    public static void registryAll() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Items.ITEMS.register(bus);
        Blocks.BLOCKS.register(bus);
        TileEntityTypes.TYPES.register(bus);
    }

    //物品
    public static class Items {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MODID);
        public static final RegistryObject<BlockItem> WHEAT_HOLOGRAPHIC = ITEMS.register("wheat_holo", () -> new BlockItem(Blocks.WHEAT_HOLOGRAPHIC.get(), new Item.Properties().group(ItemGroup.MISC)));
    }

    //方块
    public static class Blocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.MODID);
        public static final RegistryObject<WheatHolographicBlock> WHEAT_HOLOGRAPHIC = BLOCKS.register("wheat_holo", WheatHolographicBlock::new);
    }

    //方块实体
    public static class TileEntityTypes {
        public static final DeferredRegister<TileEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utils.MODID);
        public static final RegistryObject<TileEntityType<WheatHolographicTileEntity>> WHEAT_HOLOGRAPHIC = TYPES.register("wheat_holo", () -> TileEntityType.Builder.create(WheatHolographicTileEntity::new, Blocks.WHEAT_HOLOGRAPHIC.get()).build(null));
    }

    //客户端的mod事件类
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModClientEvent {
        @SubscribeEvent
        public static void onClientEvent(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                //将方块渲染模式设置为透明
                RenderTypeLookup.setRenderLayer(Blocks.WHEAT_HOLOGRAPHIC.get(), RenderType.getTranslucent());
                //绑定特殊渲染
                ClientRegistry.bindTileEntityRenderer(TileEntityTypes.WHEAT_HOLOGRAPHIC.get(), WheatHoloGraphicRender::new);
            });
        }
    }

}
