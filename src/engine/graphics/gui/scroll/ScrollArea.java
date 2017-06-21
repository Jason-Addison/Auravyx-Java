package engine.graphics.gui.scroll;

import engine.game.Game;
import engine.graphics.Gfx;
import engine.graphics.gui.guicomponents.GuiComponent;
import tools.Input;

/**
 * Created by Owner on 5/26/2017.
 */
public class ScrollArea extends GuiComponent
{

    float scroll = 0;
    int change = 0;
    public ScrollArea()
    {

    }

    public void update()
    {
        updateScroll();
    }
    private void updateScroll()
    {
        change = Input.MOUSE_SCROLL;
        scroll += (change * 0.3f);

    }
    public void render()
    {
        for(int i = 0; i < components.size(); i++)
        {
            Gfx.drawImage(i * 50, 0 + scroll, 50, 50, Game.getTexture("wooden_semi_smooth"));
        }
    }

}
