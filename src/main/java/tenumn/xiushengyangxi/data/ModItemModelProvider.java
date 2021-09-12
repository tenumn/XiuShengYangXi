package tenumn.xiushengyangxi.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tenumn.xiushengyangxi.Utils;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utils.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
    private void simpleTexture(Item itemin){

    }
}
