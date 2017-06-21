package engine.graphics;

import engine.Loader;
import engine.graphics.gui.Style;
import engine.graphics.gui.font.DrawableString;
import engine.graphics.gui.image.Texture2D;
import engine.graphics.gui.shape.rectangle.Rectangle2D;
import engine.graphics.postprocess.FBO;
import engine.graphics.postprocess.PostProcess;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;
import tools.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 2/12/2017.
 */
public class Gfx
{
    public static final int FIT_TO_SCREEN = 0;
    public static final int LOCK_TO_SCREEN = 1;

    public static int TOTAL_VERTS;
    public static int DRAW_MODE = 0;
    public static int BLEND_MODE = GL11.GL_ONE_MINUS_SRC_ALPHA;
    public static float TRANSPARENCY_2D = 1;
    public static Vector4f colour = new Vector4f(0, 1, 0, 1);
    public static Vector4f colour2 = new Vector4f(0, 1, 0, 0.5f);
    public static float ASPECT_RATIO = 1 / 1;
    public static float WIDTH = (float) Display.getWidth();
    public static float HEIGHT = (float) Display.getHeight();
    public static ArrayList<Texture2D> imageBatch = new ArrayList<Texture2D>();
    public static ArrayList<Rectangle2D> rectangleBatch = new ArrayList<Rectangle2D>();
    public static boolean DRAW_TOP = false;
    public static boolean DRAW_RIGHT = false;
    public static float NEAR_PLANE = 0.1f;
    //public static float FAR_PLANE = 15000;
    public static Renderer gameRenderer = new Renderer();

    public static FBO COLOUR_FBO = new FBO(Display.getWidth() / 1, Display.getHeight() / 1);
    public static FBO NORMALS_FBO = new FBO(Display.getWidth(), Display.getHeight());
    public static FBO POSITIONS_FBO = new FBO(Display.getWidth(), Display.getHeight());
    public static FBO DEPTH_FBO = new FBO(Display.getWidth(), Display.getHeight());
    public static FBO SPECULAR_FBO = new FBO(Display.getWidth(), Display.getHeight());
    public static FBO GLOW_FBO = new FBO(Display.getWidth(), Display.getHeight());
    public static FBO TO_CAMERA_FBO = new FBO(Display.getWidth(), Display.getHeight());
    public static FBO SETTINGS_FBO = new FBO(Display.getWidth(), Display.getHeight());
    public static float WH = Display.getWidth() + Display.getHeight();
    public static int[] powerOfTwos = new int[16];
    public static int STEP;

    public static void loadPowerOfTwos()
    {
        for(int i = 0; i < 16; i++)
        {
            powerOfTwos[i] = (int) Math.pow(2, i);
        }
    }

    public static void update()
    {
        STEP = Utilities.findClosestValueFromArray((int) WH, powerOfTwos);
    }

    //public static ArrayList<Texture2D> imageBatch = new ArrayList<Texture2D>();
    public static Map<String, Integer> textures = new HashMap<String, Integer>();


    public static void fillRect(float x, float y, float xScale, float yScale, float r, float g, float b, float a)
    {
        Rectangle2D rectangle = new Rectangle2D((x), y, xScale / 1000, yScale / 1000 * 2, r, g, b, a);
        PostProcess.rectangleRenderer.render(rectangle);
    }
    /*
    public static void fillRect(float x, float y, float xScale, float yScale, float rotation)
    {
        ShapeRenderer.fillRect(x, y, xScale, yScale, rotation);
    }
    public static void fillRect(float x, float y, float pivotX, float pivotY, float xScale, float yScale, float rotation)
    {
        ShapeRenderer.fillRect(x, y, pivotX, pivotY, xScale, yScale, rotation);
    }*/
    public static void setColour(float r, float g, float b, float a)
    {
        //colour = new Vector4f(r, g, b, a);
    }
    public static void gradientRect()
    {

    }
    public static void drawImage(float x, float y, float width, float height, int textureID)
    {
        /*
          !!! Use integer scales !!!
         */
        PostProcess.imageRenderer.render((int) x, Gfx.HEIGHT - (int) y,  (width), (int) (-height), textureID);
    }
    public static void drawImage(float x, float y, float width, float height, int textureID, int mode)
    {
        PostProcess.imageRenderer.render((int) x, Gfx.HEIGHT - (int) y,  (width), (int) (-height), textureID, mode);
    }
    public static void drawImageWithDepth(float x, float y, float width, float height, int textureID, int depthTex, int sceneDepthTex, int mode)
    {
        PostProcess.depthRenderRenderer.render((int) x, Gfx.HEIGHT - (int) y,  (width), (int) (-height), textureID, depthTex, sceneDepthTex, mode);
    }
    public static void drawImageFBO(float x, float y, float width, float height, int textureID)
    {
        PostProcess.imageRenderer.render((int) x, Gfx.HEIGHT - height - y, width / 1000, height / 1000, textureID);
    }
    /*public static void drawImage(float x, float y, float width, float height, int textureID)
    {
        PostProcess.imageRenderer.render(x, Gfx.HEIGHT - height - y, width / 1000, height / 1000, textureID);
    }*/
    //public static void drawString(String string, float x, float y, float size)
   // {
   //     FontRenderer.stringsToDraw.add(new DrawableString(string, x, y, size / 1000));
   // }
    public static void drawString(String string, float x, float y, float size)
    {
        PostProcess.fontRenderer.render(new DrawableString(string, (int) x, (int) y, size / 1000));
    }
    public static void drawString(String string, float x, float y, float size, float r, float g, float b, float a)
    {
        /*float width = 0;
        for(int k = 0; k < string.length(); k++)
        {
            Character character = Character.characters.get(string.charAt(k) + "");
            width += character.getWidth();
            width -= character.getXOffset();
            width *= size;
            width /= 35;
        }
        //-= width;*/

        PostProcess.fontRenderer.render(new DrawableString(string, x, y, size / 1000, r, g, b, a));
    }
    public static void drawString(String string, float x, float y, float size, float r, float g, float b, float a, int style)
    {
        float stringWidth = DrawableString.getPixelWidth(new DrawableString(string, x, y, size, r, g, b, a));
        if(style == Style.CENTERED)
        {
            Gfx.drawString(string, Gfx.WIDTH / 2 - (stringWidth / 4), y, size, r, g, b, a);
        }
        if(style == Style.RIGHT_TO_LEFT)
        {
            Gfx.drawString(string, Gfx.WIDTH - (stringWidth / 2) - x, y, size, r, g, b, a);
        }
        if(style == Style.LEFT_TO_RIGHT)
        {
            Gfx.drawString(string, x, y, size, r, g, b, a);
        }
    }
    public static void setDrawMode(int drawMode)
    {
        DRAW_MODE = drawMode;
    }

    public static Vector4f getCurrentColour()
    {
        return colour;
    }

    public static int getTexture(String name)
    {
        int texture = 6;
        try
        {
            texture = textures.get(name);
        }
        catch(Exception e)
        {
            System.err.println("Texture not found : " + name);
        }
        return texture;
    }
    public static int tex(String name)
    {
        int texture = 6;
        try
        {
            texture = textures.get(name);
        }
        catch(Exception e)
        {
            System.err.println("Texture not found : " + name);
        }
        return texture;
    }
    public static void drawStringOutline(String string, float x, float y, float size, float thickness, float r, float g, float b, float a)
    {
        drawString(string, x, y - thickness, size, 0, 0, 0, a);
        drawString(string, x, y + thickness, size, 0, 0, 0, a);
        drawString(string, x - thickness, y, size, 0, 0, 0, a);
        drawString(string, x + thickness, y, size, 0, 0, 0, a);
        drawString(string, x, y, size, r, g, b, a);
    }
    public static void loadTexture(String textureName, String textureLocation)
    {
        int id = Loader.loadTexture(textureLocation).getTextureID();
        textures.put(textureName, id);
    }
    public static void setBlendMode(int blendMode)
    {
        BLEND_MODE = blendMode;
    }
    public static void setTransparency(float transparency)
    {
        TRANSPARENCY_2D = transparency;
    }
    public static void updateFrustum()
    {
        // createProjectionMatrix();
        //DisplayManager.WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        //DisplayManager.HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        /*renderer = new EntityRenderer(shader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
        waterRenderer = new WaterRenderer(waterShader, projectionMatrix);
        objectRenderer = new ObjectRenderer(objectShader, projectionMatrix);*/
        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        ASPECT_RATIO = (float) Display.getWidth() / (float) Display.getHeight();
        WIDTH = (float) Display.getWidth();
        HEIGHT = (float) Display.getHeight();
        WH = WIDTH + HEIGHT;
        Renderer.createProjectionMatrix();
    }
    public static void wireframe(boolean flag)
    {
        if(flag)
        {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        }
        else
        {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        }
    }
}
