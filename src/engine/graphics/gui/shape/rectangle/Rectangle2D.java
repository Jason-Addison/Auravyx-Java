package engine.graphics.gui.shape.rectangle;

import engine.graphics.Gfx;

/**
 * Created by Owner on 2/24/2017.
 */
public class Rectangle2D
{

    int blendMode;
    float x;
    float y;
    float xScale;
    float yScale;
    float rotation;
    float r = 1, g, b, a;
    public Rectangle2D(float x, float y, float xScale, float yScale, float r, float g, float b, float a)
    {
        this.x = x;
        this.y = y;
        this.xScale = xScale;
        this.yScale = yScale;
        this.blendMode = Gfx.BLEND_MODE;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
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
}
