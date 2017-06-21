package engine.world.terrain;

import engine.game.Settings;
import engine.graphics.Camera;
import engine.graphics.Renderer;
import engine.graphics.model.Model;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

/**
 * Created by Owner on 2/15/2017.
 */
public class TerrainRenderer
{

    private TerrainShader shader = new TerrainShader();
    public static Terrain terrain = new Terrain();
    public TerrainRenderer()
    {
        shader = new TerrainShader();
        terrain.generate(0, 0);
    }
    private boolean e = false;
    public void render(Camera camera)
    {
        shader.start();
        shader.loadViewMatrix(camera);
        shader.loadCamera(camera);
        shader.loadFarPlane(Settings.getFloatSetting("viewDistance"));
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            Renderer.enableCulling();
            prepare();
            Matrix4f matrix = Maths.createTransformationMatrix(new Vector3f(0, 0, 0), 0, 0, 0, 1, 1, 1);
            shader.loadTransformation(matrix);

            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, terrain.getTerrain().getVertexCount());
            Renderer.disableCulling();
        shader.stop();
        end();
    }
    public void prepare()
    {
        GL30.glBindVertexArray(terrain.getTerrain().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL11.glDisable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

       // GL13.glActiveTexture(GL13.GL_TEXTURE0);
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D, Loop.meme);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
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
