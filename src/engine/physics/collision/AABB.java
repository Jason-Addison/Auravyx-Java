package engine.physics.collision;

/**
 * Created by Owner on 6/9/2017.
 */
public class AABB
{

    private float x, y, z;
    private float xScale, yScale, zScale;

    public AABB(float x, float y, float z, float xScale, float yScale, float zScale)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getZ()
    {
        return z;
    }

    public void setZ(float z)
    {
        this.z = z;
    }

    public float getXScale()
    {
        return xScale;
    }

    public void setXScale(float xScale)
    {
        this.xScale = xScale;
    }

    public float getYScale()
    {
        return yScale;
    }

    public void setYScale(float yScale)
    {
        this.yScale = yScale;
    }

    public float getZScale()
    {
        return zScale;
    }

    public void setZScale(float zScale)
    {
        this.zScale = zScale;
    }

    public String toString()
    {
        return "AABB[" + x + ", " + y + ", " + z + "]";
    }
}
