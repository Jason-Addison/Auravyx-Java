package engine.game.state;

import engine.graphics.postprocess.FBO;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Created by Owner on 2/16/2017.
 */
public class State
{
    protected FBO fbo;
    int width, height;

    public static final byte DEAD_STATE = 0; /* Debug */
    public static final byte GAME_STATE = 1; /* In game */
    public static final byte MENU_STATE = 2; /* In menu */
    public static final byte EDITOR_STATE = 3; /* In menu */

    public void update()
    {

    }

    public void render()
    {

    }

    public FBO getFBO()
    {
        return fbo;
    }

    public void updateFBO()
    {
        int desiredWidth = Display.getWidth() * 1;
        int desiredHeight = Display.getHeight() * 1;
        if(Display.wasResized() || width != desiredWidth || height != desiredHeight)
        {
            setFBO(desiredWidth, desiredHeight);
            fbo.destroy();
            fbo = new FBO(width, height);
        }
    }

    public void setFBO(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    public void clear()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 1, 1, 0);
    }
    public void stateChanged()
    {

    }
}
