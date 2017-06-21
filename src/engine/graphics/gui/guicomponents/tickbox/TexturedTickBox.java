package engine.graphics.gui.guicomponents.tickbox;

import engine.graphics.Gfx;

/**
 * Created by Owner on 3/30/2017.
 */
public class TexturedTickBox extends TickBox
{

    float darken;
    int texture;
    public TexturedTickBox(float darken, int texture)
    {
        initialize();
        initializeComponent();
        this.darken = darken;
        this.texture = texture;
    }
    public void render()
    {
        if(toggled)
        {
            Gfx.drawImage(x, y, width, height, texture);
        }
        else
        {
            Gfx.drawImage(x, y, width, height, texture);
            Gfx.fillRect(x, y, width, height, 0, 0, 0, darken);
        }
    }
}
