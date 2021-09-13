package tenumn.xiushengyangxi.client.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public abstract class BasicParticle extends SpriteTexturedParticle {
    public BasicParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public class PosHelper {
        IVertexBuilder buffer;
        ActiveRenderInfo renderInfo;
        float partialTicks;

        public PosHelper(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
            this.buffer = buffer;
            this.renderInfo = renderInfo;
            this.partialTicks = partialTicks;
        }

        public IVertexBuilder getBuffer() {
            return buffer;
        }

        public ActiveRenderInfo getRenderInfo() {
            return renderInfo;
        }

        public float getPartialTicks() {
            return partialTicks;
        }

        public Vector3d pos(float x, float y, float z) {
            return posArray(new Vector3f(x, y, z))[0];
        }

        public Vector3d[] posArray(Vector3f... vector3fIn) {
            return posArray(true, vector3fIn);
        }

        public Vector3d[] posArray(boolean rotateToPlayer, Vector3f... vector3fIn) {
            Vector3d vector3d = renderInfo.getProjectedView();
            float f = (float) (MathHelper.lerp((double) partialTicks, prevPosX, posX) - vector3d.getX());
            float f1 = (float) (MathHelper.lerp((double) partialTicks, prevPosY, posY) - vector3d.getY());
            float f2 = (float) (MathHelper.lerp((double) partialTicks, prevPosZ, posZ) - vector3d.getZ());

            for (int index = 0; index < vector3fIn.length; index++) {
                Vector3f vector3f = vector3fIn[index];
                float f4 = getScale(partialTicks);
                if (rotateToPlayer) rotateToPlayer(vector3f);
                vector3f.mul(f4);
                vector3f.add(f, f1, f2);
            }
            return f2d(vector3fIn);
        }

        public Vector3d[] f2d(Vector3f[] vector3fs) {
            Vector3d[] vector3ds = new Vector3d[vector3fs.length];
            for (int i = 0; i < vector3fs.length; i++) {
                vector3ds[i] = new Vector3d(vector3fs[i]);
            }
            return vector3ds;
        }

        public void rotateToPlayer(Vector3f pos) {
            Quaternion quaternion;
            if (particleAngle == 0.0F) {
                quaternion = renderInfo.getRotation();
            } else {
                quaternion = new Quaternion(renderInfo.getRotation());
                float f3 = MathHelper.lerp(partialTicks, prevParticleAngle, particleAngle);
                quaternion.multiply(Vector3f.ZP.rotation(f3));
            }
            pos.transform(quaternion);
        }
    }
}
