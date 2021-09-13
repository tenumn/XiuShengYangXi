/*
 * Original class written by Vazkii for Botania.
 * Copied from TTFTCUTS' ShadowsOfPhysis
 */

// TEMA: this is the main shader stuff, where the programs are loaded and compiled for the card.
// other relevant files are the shader in /assets/physis/shader/, and the tesr in /client/render/tile/
// they have other comments like this in.

package tenumn.xiushengyangxi.client.shader;


import net.minecraft.client.Minecraft;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import tenumn.xiushengyangxi.Utils;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.IntConsumer;

public final class ShaderManager {

    private static final int VERT = ARBVertexShader.GL_VERTEX_SHADER_ARB;
    private static final int FRAG = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
    private static final String PREFIX = "shaders/";
    public static int

            Holographic;
    private static ISelectiveResourceReloadListener shaderReloadListener;

    public static void reloadShaders() {

        deleteProgram(Holographic);

        initShaderList();
    }

    private static void deleteProgram(int id) {
        if (id != 0) ARBShaderObjects.glDeleteObjectARB(id);
    }

    private static void initShaderList() {
        Holographic = createProgram("holographic.vert", "holographic.frag");

    }

    public static void useShader(int shader, @Nullable IntConsumer callback) {
        if (!useShaders())
            return;

        ARBShaderObjects.glUseProgramObjectARB(shader);

        if (shader != 0 && callback != null) callback.accept(shader);
    }

    public static void useShader(int shader, ShaderUniform uniform) {
        if (!useShaders())
            return;

        ARBShaderObjects.glUseProgramObjectARB(shader);

        if (shader != 0) uniform.assignUniform(shader);
    }

    public static void useShader(int shader, ShaderUniform... uniforms) {
        if (!useShaders())
            return;
        System.out.println("shader = " + shader + ", uniforms = " + Arrays.deepToString(uniforms));
        ARBShaderObjects.glUseProgramObjectARB(shader);

        if (shader != 0) {
            for (ShaderUniform uniform : uniforms) {
                uniform.assignUniform(shader);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static void useShader(int shader) {
        if (!useShaders())
            return;

        ARBShaderObjects.glUseProgramObjectARB(shader);
    }

    public static void releaseShader() {
        useShader(0);
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean useShaders() {
        return true;
    }

    @SuppressWarnings("SameParameterValue")
    private static int createProgram(String vert, String frag) {

        int vertId = 0, fragId = 0, program;
        if (vert != null)
            vertId = createShader(vert, VERT);
        if (frag != null)
            fragId = createShader(frag, FRAG);

        program = ARBShaderObjects.glCreateProgramObjectARB();
        if (program == 0)
            return 0;
        if (vert != null)
            ARBShaderObjects.glAttachObjectARB(program, vertId);
        if (frag != null)
            ARBShaderObjects.glAttachObjectARB(program, fragId);

        ARBShaderObjects.glLinkProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(String.format("Failed to create shader! (VALIDATE) %s %s", vert, frag));
            System.err.println(getInfoLog(program));
            return 0;
        }

        ARBShaderObjects.glValidateProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(String.format("Failed to create shader! (VALIDATE) %s %s", vert, frag));
            System.err.println(getInfoLog(program));
            return 0;
        }

        return program;
    }

    // Most of the code taken from the LWJGL wiki
    // http://lwjgl.org/wiki/index.php?title=GLSL_Shaders_with_LWJGL

    private static int createShader(String filename, int shaderType) {
        int shader = 0;
        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);

            if (shader == 0)
                return 0;

            ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename));
            ARBShaderObjects.glCompileShaderARB(shader);

            if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
                System.err.println("Failed to create shader! (COMPILE) " + filename);
                throw new RuntimeException("Error creating shader: " + getInfoLog(shader));
            }

            return shader;

        } catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            e.printStackTrace();
            return -1;
        }
    }

    // See ShaderLoader.loadShader for buffer management
    private static String readFileAsString(String path) throws Exception {
        StringBuilder source = new StringBuilder();
        InputStream in = Minecraft.getInstance().getResourceManager().getResource(Utils.modLoc(PREFIX + path)).getInputStream();
        Exception exception = null;
        BufferedReader reader;
        if (in == null)
            return "";

        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            Exception innerExc = null;
            try {
                String line;
                while ((line = reader.readLine()) != null)
                    source.append(line).append('\n');
            } catch (Exception exc) {
                exception = exc;
            } finally {
                try {
                    reader.close();
                } catch (Exception exc) {
                    if (innerExc == null)
                        innerExc = exc;
                    else exc.printStackTrace();
                }
            }

            if (innerExc != null)
                throw innerExc;
        } catch (Exception exc) {
            exception = exc;
        } finally {
            try {
                in.close();
            } catch (Exception exc) {
                if (exception == null)
                    exception = exc;
                else exc.printStackTrace();
            }

            if (exception != null)
                throw exception;
        }
        return source.toString();
    }

    private static String getInfoLog(int program) {
        return ARBShaderObjects.glGetInfoLogARB(program, ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    static void glUniform2i(int location, int v0, int v1) {
        ARBShaderObjects.glUniform2iARB(location, v0, v1);
    }

    static void glUniform1f(int location, float v0) {
        ARBShaderObjects.glUniform1fARB(location, v0);
    }

    static void glUniform2f(int location, float v0, float v1) {
        ARBShaderObjects.glUniform2fARB(location, v0, v1);
    }

    public static final class Uniforms {

        public static final ShaderUniform TIME = ShaderUniform.create("time", () -> Minecraft.getInstance().getRenderPartialTicks());
        public static final ShaderUniform YAW = ShaderUniform.create("yaw", () -> (Minecraft.getInstance().player.rotationYaw * 2.0f * Utils.PI) / 360.0f);
        public static final ShaderUniform PITCH = ShaderUniform.create("pitch", () -> -(Minecraft.getInstance().player.rotationPitch * 2.0f * Utils.PI) / 360.0f);
        public static final ShaderUniform RESOLUTION = ShaderUniform.create("resolution", () -> Minecraft.getInstance().getMainWindow().getWidth(), () -> Minecraft.getInstance().getMainWindow().getHeight());
        public static final ShaderUniform ZERO = ShaderUniform.create("zero", 0);
        public static final ShaderUniform ONE = ShaderUniform.create("one", 1);
        public static final ShaderUniform TWO = ShaderUniform.create("two", 2);

    }
}
