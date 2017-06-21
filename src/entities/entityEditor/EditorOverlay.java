package entities.entityEditor;

import engine.display.DisplayManager;
import engine.game.Game;
import engine.graphics.Gfx;

/**
 * Created by Owner on 3/28/2017.
 */
public class EditorOverlay
{

    public void render()
    {
        Gfx.drawString("FPS : " + DisplayManager.FPS, Gfx.WIDTH * 0.001f, Gfx.HEIGHT - (Gfx.HEIGHT * 0.03f), Gfx.WH * 0.01f, 0, 0, 0, 1);
        //Gfx.drawImage(Gfx.WIDTH * 0.01f, Gfx.HEIGHT - Gfx.HEIGHT * 0.11f, Gfx.WH * 0.02f, Gfx.WH * 0.02f, Game.getTexture("xaxis"));
        Gfx.drawImage(Gfx.WIDTH * 0.05f, Gfx.HEIGHT - Gfx.HEIGHT * 0.11f, Gfx.WH * 0.02f, Gfx.WH * 0.02f, Game.getTexture("yaxis"));
        Gfx.drawImage(Gfx.WIDTH * 0.09f, Gfx.HEIGHT - Gfx.HEIGHT * 0.11f, Gfx.WH * 0.02f, Gfx.WH * 0.02f, Game.getTexture("zaxis"));
    }

}
