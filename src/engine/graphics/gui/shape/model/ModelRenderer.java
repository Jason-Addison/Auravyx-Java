package engine.graphics.gui.shape.model;

import engine.graphics.Gfx;
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
public class ModelRenderer
{

    private ModelShader shader = new ModelShader();
    public ModelRenderer()
    {
        shader = new ModelShader();
    }

    float meme;
    public void render(Model model, float x, float y, float z, float xRot, float yRot, float zRot, float xScale, float yScale, float zScale)
    {
        shader.start();
        shader.loadViewMatrix(Renderer.getCamera());
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        //GL11.glEnable(GL11.GL_DEPTH_TEST);
        Renderer.disableCulling();
        prepare(model);
        shader.loadTransformation(Maths.createTransformationMatrix(new Vector3f(x, y, z), xRot, yRot, zRot, xScale, yScale, zScale));
        //GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        Renderer.disableCulling();
        Gfx.TOTAL_VERTS += model.getVertexCount();
        shader.stop();
        end();
    }
    private void prepare(Model model)
    {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(2);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

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
