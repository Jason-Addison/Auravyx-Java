package engine.graphics.gui.font;

import engine.graphics.Gfx;

/**
 * Created by Owner on 2/12/2017.
 */
public class DrawableString
{
    String string;
    float size;
    float r, g, b, a;
    float x, y;
    boolean fitToScreen = true;
    float bR, bG, bB, bA;
    float stringWidth;
    public DrawableString(String string, float x, float y, float size)
    {
        this.string = string;
        this.size = size;

        r = Gfx.getCurrentColour().getX();
        g = Gfx.getCurrentColour().getY();
        b = Gfx.getCurrentColour().getZ();
        a = Gfx.getCurrentColour().getW();
        bR = Gfx.colour2.x;
        bG = Gfx.colour2.y;
        bB = Gfx.colour2.z;
        bA = Gfx.colour2.w;
        this.x = x;
        this.y = y;
        this.fitToScreen = Gfx.DRAW_MODE == Gfx.FIT_TO_SCREEN;
    }
    public DrawableString(String string, float x, float y, float size, float r, float g, float b, float a)
    {
        this.string = string;
        this.size = size;

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        bR = Gfx.colour2.x;
        bG = Gfx.colour2.y;
        bB = Gfx.colour2.z;
        bA = Gfx.colour2.w;
        this.x = x;
        this.y = y;
        this.fitToScreen = Gfx.DRAW_MODE == Gfx.FIT_TO_SCREEN;
        float width = 0;
        for(int k = 0; k < string.length(); k++)
        {
            Character character = Character.characters.get(string.charAt(k) + "");
            if(character != null)
            {
                width += character.getWidth();
                width -= character.getXOffset();
                width *= size * 1000;
            }
            else
            {
                string.substring(k, k);
            }
        }

        //this.stringWidth = width;
        //this.x -= (stringWidth / 2);
    }
    public String getString()
    {
        return string;
    }
    public float getSize()
    {
        return size;
    }
    public void setSize(float size)
    {
        this.size = size;
    }
    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public void setString(String string)
    {
        this.string = string;
    }
    public float getR()
    {
        return r;
    }
    public float getG()
    {
        return g;
    }
    public float getB()
    {
        return b;
    }
    public float getA()
    {
        return a;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public boolean isFitToScreen()
    {
        return fitToScreen;
    }
    public void setFitToScreen(boolean toggle)
    {
        fitToScreen = toggle;
    }
    public void setColour(float r, float g, float b, float a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public float getbR()
    {
        return bR;
    }
    public float getbG()
    {
        return bG;
    }
    public float getbB()
    {
        return bB;
    }
    public float getbA()
    {
        return bA;
    }
    public float getStringWidth()
    {
        return stringWidth;
    }
    public static float getPixelWidth(DrawableString drawableString)
    {
        float width = 0;
        String string = drawableString.getString();
        for(int k = 0; k < string.length(); k++)
        {
            width += Character.characters.get(string.charAt(k) + "").getWidth();
            width -= Character.characters.get(string.charAt(k) + "").getXOffset();
        }
        width = width * drawableString.getSize() * 30;
        return width;
    }
    public static float getPixelWidthT(DrawableString drawableString)
    {
        float width = 0;
        String string = drawableString.getString();
        for(int k = 0; k < string.length(); k++)
        {
            width += Character.characters.get(string.charAt(k) + "").getWidth() * drawableString.getSize();
            width -= Character.characters.get(string.charAt(k) + "").getXOffset() * drawableString.getSize();
        }
        width = width;
        return width;
    }
}
