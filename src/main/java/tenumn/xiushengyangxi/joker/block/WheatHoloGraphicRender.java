package tenumn.xiushengyangxi.joker.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import org.lwjgl.opengl.GL11;
import tenumn.xiushengyangxi.Utils;
import tenumn.xiushengyangxi.common.ModRegistry;


//特殊渲染（未完成）
public class WheatHoloGraphicRender extends TileEntityRenderer<WheatHolographicTileEntity> {
    protected static final RenderState.WriteMaskState DEPTH_WRITE = new RenderState.WriteMaskState(false, true);
    protected static RenderType HOLO_TYPE = RenderType.makeType("holo", DefaultVertexFormats.POSITION_TEX, GL11.GL_LINE_LOOP, 256, RenderType.State.getBuilder().texture(new RenderState.TextureState(Utils.modLoc("textures/item/shit.png"), false, false)).writeMask(DEPTH_WRITE).build(false));

    public WheatHoloGraphicRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }


    @Override
    public void render(WheatHolographicTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(.5, .5, .5);
        matrixStackIn.rotate(Vector3f.YN.rotationDegrees(Minecraft.getInstance().player.rotationYaw));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(Minecraft.getInstance().player.rotationPitch));
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(ModRegistry.Items.SHIT.get());
        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
       /*
       IVertexBuilder iVertexBuilder= bufferIn.getBuffer(HOLO_TYPE);
        iVertexBuilder.pos(0,0,0).tex(0,0).endVertex();
        iVertexBuilder.pos(0,0,1).tex(0,1).endVertex();
        iVertexBuilder.pos(1,0,1).tex(1,1).endVertex();
        iVertexBuilder.pos(1,0,0).tex(1,0).endVertex();
        */
        matrixStackIn.pop();

    }

}
