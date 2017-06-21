package tools.vector;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by Owner on 3/16/2017.
 */
public class Vec
{
    /*
        Contains tools for Vectors
     */
    public static Vector3f vecAdd(Vector3f original, Vector3f toBeAdded)
    {
        original.set(original.getX() + toBeAdded.getX(), original.getY() + toBeAdded.getY(), original.getZ() + toBeAdded.getZ());
        return original;
    }
    public static Vector3f divideVec(Vector3f original, float amount)
    {
        original.set(original.getX() / amount, original.getY() / amount, original.getZ() / amount);
        return original;
    }
    public static Vector3f multiplyVec(Vector3f original, float amount)
    {
        return new Vector3f(original.x * amount, original.y * amount, original.z * amount);
    }
    public static Vector3f flip(Vector3f vector)
    {
        return new Vector3f(1 - vector.x, 1 - vector.y, 1 - vector.z);
    }
    public static Vector3f flip(Vector3f vector, float value)
    {
        return new Vector3f(value - vector.x, value - vector.y, value - vector.z);
    }
    public static Vector3f subtract(Vector3f base, Vector3f amount)
    {
        return new Vector3f(base.x - amount.x, base.y - amount.y, base.z - amount.z);
    }
    public static Vector3f average(Vector3f... toAverage)
    {
        Vector3f average = new Vector3f(0, 0, 0);
        for(Vector3f nextAvg : toAverage)
        {
            if(nextAvg == null)
            {
                return null;
            }
            float x = nextAvg.getX() / (float) toAverage.length;
            float y = nextAvg.getY() / (float) toAverage.length;
            float z = nextAvg.getZ() / (float) toAverage.length;
            average = new Vector3f(average.getX() + x, average.getY() + y, average.getZ() + z);
        }
        return average;
    }
    public static Vector3f triangleToNormal(Vector3f ver1, Vector3f ver2, Vector3f ver3)
    {
        Vector3f normal = new Vector3f();
        Vector3f v = new Vector3f(ver2.x - ver1.x, ver2.y - ver1.y, ver2.z - ver1.z);
        Vector3f w = new Vector3f(ver3.x - ver1.x, ver3.y - ver1.y, ver3.z - ver1.z);

        Vector3f n = new Vector3f();

        n.x = (v.y * w.z) - (v.z * w.y);
        n.y = (v.z * w.x) - (v.x * w.z);
        n.z = (v.x * w.y) - (v.y * w.x);
        return n;
    }
    public static Vector3f yzx(Vector3f vec)
    {
        return new Vector3f(vec.y, vec.z, vec.x);
    }
    public static Vector3f zxy(Vector3f vec)
    {
        return new Vector3f(vec.z, vec.x, vec.y);
    }

    public static Vector3f angleToDirection(float x, float y, float z)
    {
        return new Vector3f(((float) Math.sin(Math.toRadians(x))), ((float) Math.cos(Math.toRadians(y))), ((float) Math.sin(Math.toRadians(z))));
    }

    public static Vector3f abs(Vector3f source)
    {
        return new Vector3f(Math.abs(source.getX()), Math.abs(source.getY()), Math.abs(source.getZ()));
    }
    public static Vector3f downscale(Vector4f vector)
    {
        return new Vector3f(vector.x, vector.y, vector.z);
    }
    public static boolean zero(Vector3f zero)
    {
        if(zero.getX() == 0 && zero.getY() == 0 && zero.getZ() == 0)
        {
            return true;
        }
        return false;
    }
}
