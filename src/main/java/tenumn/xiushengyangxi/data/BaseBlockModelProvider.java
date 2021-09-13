package tenumn.xiushengyangxi.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public abstract class BaseBlockModelProvider extends BlockModelProvider {


    public BaseBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Block model provider: " + modid;
    }

    public BlockModelBuilder sideBottomTop(String name, ResourceLocation parent, ResourceLocation texture) {
        return withExistingParent(name, parent)
                .texture("side", texture)
                .texture("bottom", texture)
                .texture("top", texture);
    }


}