package tools.vector;

/**
 * Created by Owner on 6/13/2017.
 */
public class Vec4D extends Vec
{
    /*
        Creates a 3 double array
     */

    public double x;
    public double y;
    public double z;
    public double w;

    public Vec4D()
    {

    }
    public Vec4D(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec4D(Vec4D original)
    {
        this.x = original.getX();
        this.y = original.getY();
        this.z = original.getZ();
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(double z)
    {
        this.z = z;
    }

    public double getW()
    {
        return w;
    }
    public void setW(double w)
    {
        this.w = w;
    }
}
