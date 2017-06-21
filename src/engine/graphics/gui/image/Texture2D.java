package engine.graphics.gui.image;

import engine.graphics.Gfx;

/**
 * Created by Owner on 2/14/2017.
 */
public class Texture2D
{

    int id;
    int blendMode;
    float x;
    float y;
    float xScale;
    float yScale;
    float rotation;
    float transparency = 1;
    public Texture2D(int textureID, float x, float y, float xScale, float yScale)
    {
        this.id = textureID;
        this.x = x;
        this.y = y;
        this.xScale = xScale;
        this.yScale = yScale;
        this.blendMode = Gfx.BLEND_MODE;
        this.transparency = Gfx.TRANSPARENCY_2D;
    }
    public int getTexture()
    {
        return id;
    }
    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }
    public void setXScale(float xScale)
    {
        this.xScale = xScale;
    }
    public void setYScale(float yScale)
    {
        this.yScale = yScale;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getXScale()
    {
        return xScale;
    }
    public float getYScale()
    {
        return yScale;
    }
    public int getBlendMode()
    {
        return blendMode;
    }
    public float getRotation()
    {
        return rotation;
    }
    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }
    public float getTransparency()
    {
        return transparency;
    }
}
