package entities.entityEditor;

import engine.entity.EntityRenderer;
import engine.game.Settings;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.gui.font.FontRenderer;
import engine.graphics.gui.image.ImageRenderer;
import engine.graphics.gui.shape.line.LineRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/11/2017.
 */
public class EditorRenderer
{
    public static Matrix4f projectionMatrix;
    private FontRenderer fontRenderer = new FontRenderer();
    private ImageRenderer imageRenderer = new ImageRenderer();
    private EntityRenderer entityRenderer = new EntityRenderer();
    LineRenderer lineRenderer = new LineRenderer();
    public static float FOV = 90;

    static Camera MAIN_CAMERA = new Camera();
    public EditorRenderer()
    {
        createProjectionMatrix();
        entityRenderer.loadProjectionMatrix(projectionMatrix);
        lineRenderer.loadProjectionMatrix(projectionMatrix);
    }
    public void clear()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    public void render(Camera camera)
    {
        Gfx.TOTAL_VERTS = 0;
        if(Display.wasResized())
        {
            entityRenderer.loadProjectionMatrix(projectionMatrix);
            lineRenderer.loadProjectionMatrix(projectionMatrix);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_5))
        {
            FOV = 5f;
            createProjectionMatrix();
            entityRenderer.loadProjectionMatrix(projectionMatrix);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_4))
        {
            FOV = 0.1f;
            createProjectionMatrix();
            entityRenderer.loadProjectionMatrix(projectionMatrix);
        }
        entityRenderer.render(camera);
        lineRenderer.render(camera);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
    }

    public void update(float FOV)
    {
        EditorRenderer.FOV = FOV;
        createProjectionMatrix();
    }

    public Matrix4f getProjectionMatrix()
    {
        return projectionMatrix;
    }
    public static void createProjectionMatrix()
    {
        projectionMatrix = new Matrix4f();
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = Settings.getFloatSetting("viewDistance") - Gfx.NEAR_PLANE;

        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((Settings.getFloatSetting("viewDistance") + Gfx.NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * Gfx.NEAR_PLANE * Settings.getFloatSetting("viewDistance")) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
