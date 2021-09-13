package tenumn.xiushengyangxi.client.particle;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;
import tenumn.xiushengyangxi.common.ModRegistry;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TestParticle extends BasicParticle {
    public static final int DEFAULT_LIGHT_MAP = 15728880;
    public static final VertexFormat PARTICLE_POSITION_TEX_LMAP = new VertexFormat(ImmutableList.<VertexFormatElement>builder().add(POSITION_3F).add(TEX_2F).add(TEX_2SB).build());
    IParticleRenderType MYTYPE = new IParticleRenderType() {
        public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
            // RenderSystem.disableTexture();
            bufferBuilder.begin(GL11.GL_QUADS, PARTICLE_POSITION_TEX_LMAP);
            RenderSystem.lineWidth(2f);
            RenderSystem.shadeModel(GL11.GL_SMOOTH);
            textureManager.bindTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE);
            RenderSystem.enableDepthTest();
        }

        @Override
        public void finishRender(Tessellator tesselator) {
            tesselator.draw();
            RenderSystem.enableTexture();
            RenderSystem.shadeModel(GL11.GL_FLAT);
        }
    };
    int r = 4;
    private IAnimatedSprite iAnimatedSprite;
    private Vector3f part = new Vector3f(rand.nextInt(r * 2) - r, rand.nextInt(r * 2) - r, rand.nextInt(r * 2) - r);
    private Vector3f addToPart;

    public TestParticle(ClientWorld world, double x, double y, double z, IAnimatedSprite iAnimatedSprite) {
        super(world, x, y, z);
        setMaxAge(60);
        float step_times = 1f / (float) maxAge;
        addToPart = part.copy();
        addToPart.mul(-step_times);
        this.iAnimatedSprite = iAnimatedSprite;
    }

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ModRegistry.ParticleTypes.TEST.get(), Factory::new);
    }

    @Override
    public void tick() {
        super.tick();
        part.add(addToPart);
    }

    @Override
    public float getScale(float scaleFactor) {
        return super.getScale(scaleFactor) * 5;
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        PosHelper posHelper = new PosHelper(buffer, renderInfo, partialTicks);
        drawPart(part, posHelper);
    }

    private void drawPart(Vector3f centerPos, PosHelper posHelper) {
        float minU = this.getMinU();
        float maxU = this.getMaxU();
        float minV = this.getMinV();
        float maxV = this.getMaxV();
        float width = 0.4f, height = 0.4f;
        Vector3f[] temp = new Vector3f[]{
                new Vector3f((float) (centerPos.getX() - width / 2), (float) (centerPos.getY() - height / 2), (float) centerPos.getZ()),
                new Vector3f((float) (centerPos.getX() - width / 2), (float) (centerPos.getY() + height / 2), (float) centerPos.getZ()),
                new Vector3f((float) (centerPos.getX() + width / 2), (float) (centerPos.getY() + height / 2), (float) centerPos.getZ()),
                new Vector3f((float) (centerPos.getX() + width / 2), (float) (centerPos.getY() - height / 2), (float) centerPos.getZ())
        };
        Vector3d[] drawable = posHelper.posArray(true, temp);
        posHelper.getBuffer().pos(drawable[0].x, drawable[0].y, drawable[0].z).color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255).tex(minU, minV).lightmap(DEFAULT_LIGHT_MAP).endVertex();
        posHelper.getBuffer().pos(drawable[1].x, drawable[1].y, drawable[1].z).color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255).tex(minU, maxV).lightmap(DEFAULT_LIGHT_MAP).endVertex();
        posHelper.getBuffer().pos(drawable[2].x, drawable[2].y, drawable[2].z).color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255).tex(maxU, maxV).lightmap(DEFAULT_LIGHT_MAP).endVertex();
        posHelper.getBuffer().pos(drawable[3].x, drawable[3].y, drawable[3].z).color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255).tex(maxU, minV).lightmap(DEFAULT_LIGHT_MAP).endVertex();

    }

    @Override
    protected int getBrightnessForRender(float partialTick) {
        return super.getBrightnessForRender(partialTick);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return MYTYPE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            TestParticle testParticle = new TestParticle(worldIn, x, y, z, spriteSet);
            testParticle.selectSpriteRandomly(this.spriteSet);
            return testParticle;
        }

    }
}
