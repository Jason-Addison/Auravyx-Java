package engine.graphics.gui;

import engine.graphics.Gfx;

/**
 * Created by Owner on 4/22/2017.
 */
public class PlayerOverlay
{

    public void update()
    {

    }

    public void render()
    {
        Gfx.fillRect(Gfx.WIDTH / 2 - 1, Gfx.HEIGHT / 2 - 1, Gfx.WH * 0.0015f, Gfx.WH * 0.0015f, 0, 0, 0, 1);
        renderHearts();
    }

    private void renderHearts()
    {
        int amount = 128;
        for(int i = 0; i < 10; i++)
        {
            //Gfx.drawImage(Gfx.WH * 0.003f + (i * 0.0125f * Gfx.WH), Gfx.WH * 0.003f, Gfx.WH * 0.02f, Gfx.WH * 0.02f, Texture.FULL_HEART);
            /*if(i < 10)
            {
                Gfx.drawImage(i * (int) (Gfx.WH * 0.0125f) + 50, 50, (int) (Gfx.WH * 0.011f), (int) (Gfx.WH * 0.011f), Texture.FULL_HEART);
            }
            else
            {
                Gfx.drawImage(i * 40 + 0.375f, 0 + 0.375f, Gfx.STEP / amount, Gfx.STEP / amount, Texture.EMPTY_HEART);
            }*/
        }
    }
}
