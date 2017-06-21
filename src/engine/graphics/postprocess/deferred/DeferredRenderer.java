package engine.graphics.postprocess.deferred;

import engine.Loader;
import engine.graphics.model.Model;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * Created by Owner on 2/17/2017.
 */
public class DeferredRenderer
{
    private Model quad;
    private static DeferredShader shader;


    public DeferredRenderer()
    {
        float[] positions = {-1, -1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1};
        quad = Loader.load2DModel(positions);
        shader = new DeferredShader();
    }

    float time = 0;
    public void render(int albedo, int normal, int specular, int glow, int position, int depth, int settings)
    {
        time += 0.005f;
        shader.start();
        prepare(albedo, normal, specular, glow, position, depth, settings);
        shader.loadTextures();
        shader.loadSunDirection((float) Math.cos(time), (float) Math.sin(time), 0);
        shader.loadSunDirection(0, -1, 0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
        shader.stop();
    }
    public void prepare(int albedo, int normal, int specular, int glow, int position, int depth, int settings)
    {
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, albedo);
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, normal);
        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, specular);
        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, glow);
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, position);
        GL13.glActiveTexture(GL13.GL_TEXTURE5);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth);
        GL13.glActiveTexture(GL13.GL_TEXTURE7);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, settings);
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
