package engine.graphics.particle.onecolourpass;

import engine.Loader;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.graphics.model.Model;
import engine.graphics.postprocess.FBO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

/**
 * Created by Owner on 2/15/2017.
 */
public class OneColourPassRenderer
{

    private OneColourPassShader shader = new OneColourPassShader();
    public static Model quad;
    public OneColourPassRenderer()
    {
        float[] dat = {-1, -1, 0, 1, -1, 0, 1, 1, 0, 1, 1, 0, -1, 1, 0, -1, -1, 0};
        float[] clr = {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0};
        quad = Loader.load3DModel(dat, clr, null);
        shader = new OneColourPassShader();
    }

    public void render(Camera camera, FBO fbo, int texture, float x, float y, float z, float xScale, float yScale, float zScale, float xRot, float yRot, float zRot)
    {
        shader.start();
        shader.loadViewMatrix(Renderer.getCamera());
        prepare(quad, texture, fbo.getDepthTexture());
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        Renderer.disableCulling();
        loadModelViewMatrix(new Vector3f(x, y, z), xScale, yScale, zScale, xRot, yRot, zRot, Maths.createViewMatrix(Renderer.getCamera()));
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount() / 3);
        Gfx.TOTAL_VERTS += quad.getVertexCount();
        shader.stop();
        end();
    }
    private void prepare(Model model, int texture, int sceneTexture)
    {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, sceneTexture);
        //GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        // GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
    }
    private void prepareObject(Model model)
    {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
    }

    public void end()
    {
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void cleanUp()
    {
        shader.cleanUp();
    }

    public void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
    public void loadModelViewMatrix(Vector3f position, float xScale, float yScale, float zScale, float xRot, float yRot, float zRot, Matrix4f viewMatrix)
    {
        Matrix4f mat = new Matrix4f();
        Matrix4f.translate(position, mat, mat);
        mat.m00 = viewMatrix.m00;
        mat.m01 = viewMatrix.m10;
        mat.m02 = viewMatrix.m20;
        mat.m10 = viewMatrix.m01;
        mat.m11 = viewMatrix.m11;
        mat.m12 = viewMatrix.m21;
        mat.m20 = viewMatrix.m02;
        mat.m21 = viewMatrix.m12;
        mat.m22 = viewMatrix.m22;
        Matrix4f.rotate((float) Math.toRadians(zRot), new Vector3f(0, 0, 1), mat, mat);
        Matrix4f.scale(new Vector3f(xScale, yScale, zScale), mat, mat);
        Matrix4f modelView = Matrix4f.mul(viewMatrix, mat, null);
        shader.loadModelViewMatrix(modelView);
    }
}
