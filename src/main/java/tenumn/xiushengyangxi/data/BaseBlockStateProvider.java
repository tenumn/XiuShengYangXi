package tenumn.xiushengyangxi.data;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import tenumn.xiushengyangxi.Utils;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class BaseBlockStateProvider<PROVIDER extends BaseBlockModelProvider> extends BlockStateProvider {

    private final String modid;
    private final PROVIDER modelProvider;

    public BaseBlockStateProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper,
                                  BiFunction<DataGenerator, ExistingFileHelper, PROVIDER> providerCreator) {
        super(generator, modid, existingFileHelper);
        this.modid = modid;
        modelProvider = providerCreator.apply(generator, existingFileHelper);
    }

    public PROVIDER getModelProvider() {
        return modelProvider;
    }

    @Nonnull
    @Override
    public String getName() {
        return "Block state provider: " + modid;
    }


    protected String name(Block block) {
        return block.getRegistryName().getPath();
    }


    /**
     * Like directionalBlock but allows us to skip specific properties
     */
    protected void directionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset, Property<?>... toSkip) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction dir = state.get(BlockStateProperties.FACING);
            return ConfiguredModel.builder()
                    .modelFile(modelFunc.apply(state))
                    .rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
                    .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.getYOffset()) + angleOffset) % 360)
                    .build();
        }, toSkip);
    }

    public ModelBuilder cubeAll(Block block, String textureName) {
        ResourceLocation name = new ResourceLocation(Utils.MODID, ModelProvider.BLOCK_FOLDER + "/" + textureName);
        return models().cubeAll(name(block), name);
    }

    public ModelBuilder cubeEmpty(Block block) {
        return cubeAll(block, "empty");
    }

    public void empty(Block block) {
        simpleBlock(block, cubeEmpty(block));
    }
}