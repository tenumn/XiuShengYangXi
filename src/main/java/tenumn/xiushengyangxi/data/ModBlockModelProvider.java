package tenumn.xiushengyangxi.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import tenumn.xiushengyangxi.Utils;

public class ModBlockModelProvider extends BaseBlockModelProvider {
    public ModBlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utils.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

}
