package engine.graphics.gui.shape.rectangle;

import engine.graphics.Gfx;

/**
 * Created by Owner on 3/7/2017.
 */
public class HollowRectangle
{

    public static void drawRectangle(float x, float y, float width, float height, float thickness, float r, float g, float b, float a)
    {
        Gfx.fillRect(x, y, width, thickness, r, g, b, a);
        Gfx.fillRect(x, y, thickness, height, r, g, b, a);
        Gfx.fillRect(x + width - thickness, y, thickness, height, r, g, b, a);
        Gfx.fillRect(x, y + height - thickness, width, thickness, r, g, b, a);
    }

}
