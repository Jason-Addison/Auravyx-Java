package engine.graphics.gui.shape.quad3d;

import engine.Loader;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.graphics.model.Model;
import entities.entityEditor.Test;
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
public class QuadRenderer
{

    private QuadShader shader = new QuadShader();
    Model quad;
    public QuadRenderer()
    {
        float[] positions = Test.vert;
        quad = Loader.load3DModel(positions, null, null);
        shader = new QuadShader();
    }

    float meme;
    public void render(Model model, float x, float y, float z, float xRot, float yRot, float zRot, float xScale, float yScale, float zScale)
    {
        shader.start();
        shader.loadViewMatrix(Renderer.getCamera());
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        //GL11.glEnable(GL11.GL_DEPTH_TEST);
        Renderer.disableCulling();
        prepare(quad);
        shader.loadTransformation(Maths.createTransformationMatrix(new Vector3f(x, y, z), xRot, yRot, zRot, xScale, yScale, zScale));
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 100000);
        Renderer.disableCulling();
        Gfx.TOTAL_VERTS += quad.getVertexCount();

        shader.stop();
        end();
    }
    private void prepare(Model model)
    {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        GL20.glEnableVertexAttribArray(3);
        GL11.glDisable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture());
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
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
        GL20.glDisableVertexAttribArray(1);
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
}
