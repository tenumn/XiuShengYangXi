package tenumn.xiushengyangxi.common.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import tenumn.xiushengyangxi.common.ModRegistry;

public class ItemShit extends Item {
    public ItemShit( ) {
        //简简单单一坨屎，要品尝一些吗
        super(new Properties().
                setNoRepair().//难道你想修shit
                maxStackSize(16).//太多了会很臭的
                food(new Food.Builder().hunger(1).saturation(0.5f).build()).//嘿嘿嘿，吃一口吗
                isImmuneToFire().//防火
                rarity(Rarity.COMMON).//一点也不稀有
                group(ModRegistry.MISCGROUP));//是个杂物呢
    }
}
