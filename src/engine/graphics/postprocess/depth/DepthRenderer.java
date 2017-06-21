package engine.graphics.postprocess.depth;

import engine.Loader;
import engine.game.Settings;
import engine.graphics.model.Model;
import engine.graphics.postprocess.FBO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import tools.Maths;

/**
 * Created by Owner on 2/17/2017.
 */
public class DepthRenderer
{
    private Model quad;
    private static DepthShader shader;


    public DepthRenderer()
    {
        float[] positions = {-1, -1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1};
        quad = Loader.load2DModel(positions);
        shader = new DepthShader();
    }

    public void render(int texture)
    {
        shader.start();
        prepare(texture);

        Matrix4f matrix = Maths.createTransformationMatrix(new Vector2f(0, 0),
                new Vector2f(1, 1),
                new Vector2f(0, 0), 0);
        shader.loadTransformation(matrix);
        shader.loadFarPlane(Settings.getFloatSetting("viewDistance"));
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());

        shader.stop();
    }
    public void render(int texture, FBO fbo)
    {
        fbo.bind();
        shader.start();
        prepare(texture);

        Matrix4f matrix = Maths.createTransformationMatrix(new Vector2f(0, 0),
                new Vector2f(1, 1),
                new Vector2f(0, 0), 0);
        shader.loadTransformation(matrix);
        shader.loadFarPlane(Settings.getFloatSetting("viewDistance"));
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());

        shader.stop();
        fbo.unbind();
    }
    public void prepare(int texture)
    {
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
    }

    public void end()
    {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void cleanUp()
    {
        shader.cleanUp();
    }
}
