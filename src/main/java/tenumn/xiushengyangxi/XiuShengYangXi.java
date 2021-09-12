package tenumn.xiushengyangxi;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tenumn.xiushengyangxi.common.ModRegistry;
import tenumn.xiushengyangxi.joker.JokerRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Utils.MODID)
public class XiuShengYangXi {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public XiuShengYangXi() {

        //注册事件
        MinecraftForge.EVENT_BUS.register(this);
        ModRegistry.registryAll();
        JokerRegistry.registryAll();
    }




}
