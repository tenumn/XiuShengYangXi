package tenumn.xiushengyangxi.data;


import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.common.ModRegistry;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utils.MODID, existingFileHelper);
    }

    //直接用注册名作为材质文件名的物品列表
    private static final ImmutableList<RegistryObject<Item>> simpleTextureList = new ImmutableList.Builder<RegistryObject<Item>>().add(
            ModRegistry.Items.SHIT
    ).build();

    @Override
    protected void registerModels() {
        simpleTextureList.forEach((itemRegistryObject -> generated(itemRegistryObject.get())));
    }

    protected ResourceLocation itemTexture(IItemProvider itemProvider) {
        return modLoc("item/" + itemProvider.asItem().getRegistryName().getPath());
    }

    //用注册名表示材质名并生成json
    protected ItemModelBuilder generated(IItemProvider itemProvider) {
        return generated(itemProvider, itemTexture(itemProvider));
    }

    protected ItemModelBuilder generatedBlockItem(BlockItem blockItem) {
        String name = blockItem.getBlock().getRegistryName().getPath();
        return withExistingParent(name, modLoc("block/" + name));
    }

    protected ItemModelBuilder generated(IItemProvider itemProvider, ResourceLocation texture) {
        return generated(itemProvider, texture, "item/generated");
    }

    protected ItemModelBuilder generated(IItemProvider itemProvider, ResourceLocation texture, String parent) {
        ItemModelBuilder itemModelBuilder = withExistingParent(itemProvider.asItem().getRegistryName().getPath(), parent);
        itemModelBuilder.texture("layer0", texture);
        return itemModelBuilder;
    }

    //手持物品
    protected ItemModelBuilder handheld(IItemProvider itemProvider) {
        return handheld(itemProvider, itemTexture(itemProvider));
    }

    protected ItemModelBuilder handheld(IItemProvider itemProvider, ResourceLocation texture) {
        return withExistingParent(itemProvider.asItem().getRegistryName().getPath(), "item/handheld").texture("layer0", texture);

    }
}
