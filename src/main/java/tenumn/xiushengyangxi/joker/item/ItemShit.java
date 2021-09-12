package tenumn.xiushengyangxi.joker.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import tenumn.xiushengyangxi.joker.JokerRegistry;

public class ItemShit extends Item {
    public ItemShit( ) {
        super(new Properties().
                setNoRepair().
                maxStackSize(16).
                food(new Food.Builder().hunger(1).saturation(0.5f).build()).
                isImmuneToFire().
                rarity(Rarity.COMMON).
                group(JokerRegistry.JOKERGROUP));
    }
}
