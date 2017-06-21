package engine.game.state.gamestate;

import engine.graphics.Gfx;
import engine.graphics.gui.Gui;
import engine.graphics.postprocess.FBO;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Created by Owner on 2/24/2017.
 */
public class EscapeMenu
{

    private Gui escapeMenu = new Gui();
    private boolean escape;
    private boolean escapeWasDown;
    public EscapeMenu()
    {
        escapeMenu.setToFillScreen(true);
    }
    public void update()
    {
        handleKeys();
        if(escape)
        {
            escapeMenu.update();
        }
    }

    private void handleKeys()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            if(!escapeWasDown)
            {
                if(escape)
                {
                    escape = false;
                    GameState.PAUSE = false;
                }
                else
                {
                    escape = true;
                    GameState.PAUSE = true;
                }
            }
            escapeWasDown = true;
        }
        else
        {
            escapeWasDown = false;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            //Game.setState(State.MENU_STATE);
        }
    }
    FBO menuFBO = new FBO(Display.getWidth(), Display.getHeight(), GL11.GL_RGBA);
    public void render()
    {
        menuFBO.updateFBO();
        menuFBO.bind();
        if(escape)
        {
            //escapeMenu.render();
            Gfx.fillRect(0.8f, 0, 0.01f, 1, 1, 0, 0, 1);
            menuFBO.unbind();
            menuFBO.render();
        }
        menuFBO.unbind();
    }
    public void stateChanged()
    {
        escape = false;
        escapeWasDown = false;
    }
}
