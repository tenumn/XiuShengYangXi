package tenumn.xiushengyangxi.data;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.joker.JokerRegistry;

public class ModBlockStateProvider extends BaseBlockStateProvider<ModBlockModelProvider> {
    protected static final ExistingFileHelper.ResourceType TEXTURE = new ExistingFileHelper.ResourceType(ResourcePackType.CLIENT_RESOURCES, ".png", "textures");
    ExistingFileHelper existingFileHelper;

    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utils.MODID, existingFileHelper, ModBlockModelProvider::new);
        this.existingFileHelper = existingFileHelper;
    }

    //注册方块状态和模型json
    @Override
    protected void registerStatesAndModels() {
        empty(JokerRegistry.Blocks.WHEAT_HOLOGRAPHIC.get());
    }

    private void checkExistAndMake(Block block, Runnable runnable) {
        try {
            ResourceLocation texture = blockTexture(block);
            Preconditions.checkArgument(existingFileHelper.exists(texture, TEXTURE),
                    "Texture %s does not exist in any known resource pack,will skip", texture);
            runnable.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void simpleBlock(Block block, ModelFile model) {
        super.simpleBlock(block, model);
    }
}
