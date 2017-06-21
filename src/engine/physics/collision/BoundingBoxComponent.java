package engine.physics.collision;

import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

/**
 * Created by Owner on 6/5/2017.
 */
public class BoundingBoxComponent extends CollisionComponent
{

    public BoundingBoxComponent()
    {
    }

    public void update(ComponentManager manager)
    {
        WorldComponent worldComponent = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
        //ModelComponent modelComponent = (ModelComponent) manager.get(Component.MODEL_COMPONENT);
        float AABBWidth = 0;
        float AABBHeight = 0;
        float AABBDepth = 0;
        Vector3f[] obb = Collision.generateOBBMesh(getLocalMatrix());
        for(Vector3f vec : obb)
        {
            if(vec.x > AABBWidth)
            {
                AABBWidth = vec.x;
            }
            if(vec.y > AABBHeight)
            {
                AABBHeight = vec.y;
            }
            if(vec.z > AABBDepth)
            {
                AABBDepth = vec.z;
            }
        }
        setAABB(worldComponent.getX(), worldComponent.getY(), worldComponent.getZ(), AABBWidth * 2, AABBHeight * 2, AABBDepth * 2); ////////?/////MAY WANT TO CHANGE FOR MIN MAX INSTEAD FOR ACCURACY
        //System.out.println(worldComponent.getX() + " " + worldComponent.getY() + " " + worldComponent.getZ() + " ||||| " + getBroadPhaseAABB());
        //worldComponent.setXRot(worldComponent.getXRot() + 1);
        //worldComponent.setYRot(worldComponent.getYRot() + 1);
        //worldComponent.setZRot(worldComponent.getZRot() + 1);
        set(worldComponent.getX(), worldComponent.getY(), worldComponent.getZ(), worldComponent.getXScale(), worldComponent.getYScale(), worldComponent.getZScale(), worldComponent.getXRot(), worldComponent.getYRot(), worldComponent.getZRot());
        //System.out.println(worldComponent.getXRot());
        /*Matrix4f local = Maths.createTransformationMatrix(new Vector3f(worldComponent.getX(), worldComponent.getY(), worldComponent.getZ()), new Vector3f(0, 0, 0), xRot, yRot, zRot, xScale, yScale, zScale);
        Matrix4f outside = Maths.createTransformationMatrix(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 0, 0, 0, 1, 1, 1);
        Vector3f colliding = Collision.OBBtoOBB(2, 2, 2, local, 4, 4, 4, outside);
       //System.out.println(colliding);
        if(colliding != null)
        {
            //System.out.println(colliding.getX() + " " + colliding.y + " " + colliding.z);
            worldComponent.setXYZ(worldComponent.getX() + -colliding.getX(), worldComponent.getY() + -colliding.getY(), worldComponent.getZ() + -colliding.getZ());
            modelComponent.setModel(Game.getModel("green_cube"));
        }
        else
        {
            modelComponent.setModel(Game.getModel("high res cube"));
        }*/
    }
    public Matrix4f getLocalMatrix()
    {
        return Maths.createTransformationMatrix(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), xRot, yRot, zRot, xScale, yScale, zScale);
    }
    public Matrix4f getAsMatrix()
    {
        return Maths.createTransformationMatrix(new Vector3f(xOffset, yOffset, zOffset), new Vector3f(0, 0, 0), xRot, yRot, zRot, xScale, yScale, zScale);
    }

    public void set(float x, float y, float z, float xScale, float yScale, float zScale, float xRot, float yRot, float zRot)
    {
        this.xOffset = x;
        this.yOffset = y;
        this.zOffset = z;
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
        this.xRot = xRot;
        this.yRot = yRot;
        this.zRot = zRot;
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

    public float getZOffset()
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
}
