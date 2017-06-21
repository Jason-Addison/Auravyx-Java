package tools.vector;

/**
 * Created by Owner on 6/13/2017.
 */
public class Vec3D extends Vec
{
    /*
        Creates a 3 double array
     */

    public double x;
    public double y;
    public double z;

    public Vec3D()
    {

    }
    public Vec3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec3D(Vec3D original)
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
}
