package engine.physics.collision;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Owner on 6/5/2017.
 */
public class CollisionData
{

    float totalSpan;
    float sumSpan;
    float off;
    Vector3f axis;
    boolean invert;
    Vector3f intersectionPoint;
    public CollisionData(float totalSpan, float sumSpan, float off, Vector3f axis)
    {
        this.totalSpan = totalSpan;
        this.sumSpan = sumSpan;
        this.axis = axis;
        this.axis = axis;
        this.off = off;
    }

    public Vector3f getAxis()
    {
        return axis;
    }

    public float getTotalSpan()
    {
        return totalSpan;
    }
    public float getSumSpan()
    {
        return sumSpan;
    }
    public float getOff()
    {
        return off;
    }
    public void setOff(float off)
    {
        this.off = off;
    }

    public boolean doesCollideEqual()
    {
        if(axis.x == 0 && axis.y == 0 && axis.z == 0)
        {
            return true;
        }
        return totalSpan <= sumSpan;
    }

    public boolean doesCollide()
    {
        if(axis.x == 0 && axis.y == 0 && axis.z == 0)
        {
            return true;
        }
        return totalSpan < sumSpan;
    }
    public float getOverlapPositive()
    {
        return Math.abs(totalSpan - sumSpan);
    }

    public boolean doesInvert()
    {
        return invert;
    }

    public void invert()
    {
        invert = !invert;
    }

    public Vector3f getIntersectionPoint()
    {
        return intersectionPoint;
    }
    public void setIntersectionPoint(Vector3f intersectionPoint)
    {
        this.intersectionPoint = intersectionPoint;
    }

}
