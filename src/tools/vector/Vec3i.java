package tools.vector;

/**
 * Created by Owner on 3/16/2017.
 */
public class Vec3i
{

    int x, y, z;

    public Vec3i(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getZ()
    {
        return z;
    }

    public String toString()
    {
        return "Vector3i[" + x + ", " + y + ", " + z + "]";
    }

}
