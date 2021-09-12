package tenumn.xiushengyangxi.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.common.ModRegistry;
import tenumn.xiushengyangxi.common.capabilities.IRecuperateCap;
import tenumn.xiushengyangxi.common.capabilities.RecuperateCapabilityProvider;

@Mod.EventBusSubscriber()
public class CommonEventHandler {
    //给玩家添加养生的能力
    @SubscribeEvent
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(Utils.modLoc("recuperate"), new RecuperateCapabilityProvider());
        }
    }

    //在玩家死后转移能力给新玩家
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            LazyOptional<IRecuperateCap> oldSpeedCap = event.getOriginal().getCapability(ModRegistry.RECUPERATE_CAPABILITY);
            LazyOptional<IRecuperateCap> newSpeedCap = event.getPlayer().getCapability(ModRegistry.RECUPERATE_CAPABILITY);
            if (oldSpeedCap.isPresent() && newSpeedCap.isPresent()) {
                newSpeedCap.ifPresent((newCap) -> {
                    oldSpeedCap.ifPresent((oldCap) -> {
                        newCap.deserializeNBT(oldCap.serializeNBT());
                    });
                });
            }
        }
    }

    //相应玩家tick事件
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof ServerPlayerEntity) {
            event.player.getCapability(ModRegistry.RECUPERATE_CAPABILITY).ifPresent((iRecuperateCap -> {
                iRecuperateCap.onTick(event.player);
            }));
        }
    }
}
