package tenumn.xiushengyangxi.common.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import tenumn.xiushengyangxi.common.ModRegistry;

public class ItemShit extends Item {
    public ItemShit( ) {
        //简简单单一坨屎，要品尝一些吗
        super(new Properties().
                setNoRepair().
                maxStackSize(16).
                food(new Food.Builder().hunger(1).saturation(0.5f).build()).
                isImmuneToFire().
                rarity(Rarity.COMMON).
                group(ModRegistry.MISCGROUP));
    }
}
