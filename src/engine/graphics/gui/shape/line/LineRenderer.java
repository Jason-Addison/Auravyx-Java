package engine.graphics.gui.shape.line;

/**
 * Created by Owner on 3/28/2017.
 */

import engine.Loader;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.graphics.model.Model;
import entities.entityEditor.EditorState;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

import java.nio.FloatBuffer;

public class LineRenderer
{

    private Model quad;
    private LineShader shader;


    int width = 25;
    public LineRenderer()
    {
        float[] positions = new float[width * 6];
        for(int x = 0; x < width; x++)
        {

            positions[x * 6 + 0] = x;
            positions[x * 6 + 1] = 0;
            positions[x * 6 + 2] = 0;
            positions[x * 6 + 3] = x;
            positions[x * 6 + 4] = 0;
            positions[x * 6 + 5] = width;

        }
        quad = Loader.load3DModel(positions, null, null);
        shader = new LineShader();
        shader.loadTransformation(Renderer.projectionMatrix);
    }
    
    public void render(Camera camera)
    {
        renderGrid(0, 0, 0, 0, 0, 1, EditorState.zAlpha, -width / 2, 0, -width / 2, camera);
        renderGrid(0, 90, 0, 1, 0, 0, EditorState.xAlpha, -width / 2, 0, -width / 2 + width, camera);
        renderGrid(0, 0, 90, 0, 0, 1, EditorState.zAlpha, 0, -width / 2, -width / 2, camera);
        renderGrid(90, 0, 90, 0, 1, 0, EditorState.yAlpha, 0, width / 2, -width / 2, camera);
        renderGrid(90, 0, 0, 0, 1, 0, EditorState.yAlpha, -width / 2, width / 2, 0, camera);
        renderGrid(90, 90, 0, 1, 0, 0, EditorState.xAlpha, -width / 2, -width / 2, 0, camera);
    }

    public void renderGrid(float xRot, float yRot, float zRot, float r, float g, float b, float a, float x, float y, float z, Camera camera)
    {
        FloatBuffer buf = BufferUtils.createFloatBuffer(100);
        GL11.glGetFloat(GL11.GL_LINE_WIDTH_RANGE, buf);
        shader.start();
        shader.loadViewMatrix(camera);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        prepare();

        float xOff = Gfx.WIDTH / 1000;
        float pixSpace = Gfx.WIDTH / 1000;

        //Vector2f position = new Vector2f((-Gfx.WIDTH / (rectangle.getXScale() * 1000f))
        //        , (-Gfx.HEIGHT / (rectangle.getYScale() * 1000f)));
        //Matrix4f matrix = Maths.createTransformationMatrix(position,
        //        new Vector2f(rectangle.getXScale() / Gfx.WIDTH * 1000f, rectangle.getYScale() / Gfx.HEIGHT * 1000f));
        Renderer.disableCulling();
        Matrix4f matrix = Maths.createTransformationMatrix(new Vector3f(x, y, z), xRot, yRot, zRot, 1, 1, 1);
        shader.loadTransformation(matrix);
        //shader.loadScreenDimensions((float) Display.getWidth(), (float) Display.getHeight());
        //shader.loadOffsets(rectangle.getX(), rectangle.getY());
        shader.loadColour(r, g, b, a);
        GL11.glLineWidth(20f);
        GL11.glDrawArrays(GL11.GL_LINES, 0, quad.getVertexCount());
        shader.stop();
        end();
    }
    public void prepare()
    {
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
    }
    public void end()
    {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
    public void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
    public void cleanUp()
    {
        shader.cleanUp();
    }
}

