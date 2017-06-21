package tools;

/**
 * Created by Owner on 3/2/2017.
 */
public class Vector3d
{
    double x, y, z;
    public Vector3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
