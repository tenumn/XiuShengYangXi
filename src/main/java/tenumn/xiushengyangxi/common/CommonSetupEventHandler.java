package tenumn.xiushengyangxi.common;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import tenumn.xiushengyangxi.common.capabilities.IRecuperateCap;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        //注册capabilities
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IRecuperateCap.class,
                    new Capability.IStorage<IRecuperateCap>() {
                        @Nullable
                        @Override
                        public INBT writeNBT(Capability<IRecuperateCap> capability, IRecuperateCap instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<IRecuperateCap> capability, IRecuperateCap instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
        });
    }
}
