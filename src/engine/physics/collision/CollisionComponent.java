package engine.physics.collision;

import engine.component.Component;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

/**
 * Created by Owner on 6/7/2017.
 */
public class CollisionComponent extends Component
{
    private AABB broadPhaseAABB = new AABB(0, 0, 0, 1, 1, 1);
    float xOffset;
    float yOffset;
    float zOffset;

    float xScale;
    float yScale;
    float zScale;

    float xRot;
    float yRot;
    float zRot;
    boolean awake = true;
    public void collideWith()
    {

    }

    public AABB getBroadPhaseAABB()
    {
        return broadPhaseAABB;
    }

    public void setBroadPhaseAABB(AABB broadPhaseAABB)
    {
        this.broadPhaseAABB = broadPhaseAABB;
    }

    public void setAABB(float x, float y, float z, float xScale, float yScale, float zScale)
    {
        broadPhaseAABB.setX(x);
        broadPhaseAABB.setY(y);
        broadPhaseAABB.setZ(z);

        broadPhaseAABB.setXScale(xScale);
        broadPhaseAABB.setYScale(yScale);
        broadPhaseAABB.setZScale(zScale);
    }

    public Matrix4f getAsMatrix()
    {
        return Maths.createTransformationMatrix(new Vector3f(xOffset, yOffset, zOffset), new Vector3f(0, 0, 0), xRot, yRot, zRot, xScale, yScale, zScale);
    }

    public float getXOffset()
    {
        return xOffset;
    }

    public void setXOffset(float xOffset)
    {
        this.xOffset = xOffset;
    }

    public float getYOffset()
    {
        return yOffset;
    }

    public void setYOffset(float yOffset)
    {
        this.yOffset = yOffset;
    }

    public float getzOffset()
    {
        return zOffset;
    }

    public void setZOffset(float zOffset)
    {
        this.zOffset = zOffset;
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

    public float getXRot()
    {
        return xRot;
    }

    public void setXRot(float xRot)
    {
        this.xRot = xRot;
    }

    public float getYRot()
    {
        return yRot;
    }

    public void setYRot(float yRot)
    {
        this.yRot = yRot;
    }

    public float getZRot()
    {
        return zRot;
    }

    public void setZRot(float zRot)
    {
        this.zRot = zRot;
    }

    public boolean isAwake()
    {
        return awake;
    }
    public void wake()
    {
        awake = true;
    }
    public void sleep()
    {
        awake = false;
    }

}
