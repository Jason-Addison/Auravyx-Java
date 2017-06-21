package engine.component;

import engine.game.Game;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

/**
 * Created by Owner on 2/18/2017.
 */
public class WorldComponent extends Component
{
    float x, y, z;
    float xR, yR, zR;
    float xScale, yScale, zScale;
    float pivotX, pivotY, pivotZ;
    float xVel, yVel, zVel;
    Matrix4f matrix;

    public WorldComponent()
    {

    }

    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getZ()
    {
        return z;
    }
    public float getXRot()
    {
        return xR;
    }
    public float getYRot()
    {
        return yR;
    }
    public float getZRot()
    {
        return zR;
    }
    public float getXScale()
    {
        return xScale;
    }
    public float getYScale()
    {
        return yScale;
    }
    public float getZScale()
    {
        return zScale;
    }
    public float getXVel()
    {
        return xVel;
    }
    public float getYVel()
    {
        return yVel;
    }
    public float getZVel()
    {
        return zVel;
    }
    public void set(float x, float y, float z, float xRot, float yRot, float zRot, float xScale, float yScale, float zScale,
                    float pivotX, float pivotY, float pivotZ)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xR = xRot;
        this.yR = yRot;
        this.zR = zRot;
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
        this.pivotX = pivotX;
        this.pivotY = pivotY;
        this.pivotZ = pivotZ;
    }
    public void update(ComponentManager manager)
    {
        x += (Game.getDelta() * xVel);
        y += (Game.getDelta() * yVel);
        z += (Game.getDelta() * zVel);
    }
    public Matrix4f getAsMatrix()
    {
        if(matrix == null)
        {
            Matrix4f matrix = Maths.createTransformationMatrix(new Vector3f(x, y, z), new Vector3f(pivotX, pivotY, pivotZ),
                    xR, yR, zR, xScale, yScale, zScale);
            return matrix;
        }
        else
        {
            return matrix;
        }
    }
    public void setVelocity(float xV, float yV, float zV)
    {
        this.xVel = xV;
        this.yVel = yV;
        this.zVel = zV;
    }
    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }
    public void setZ(float z)
    {
        this.z = z;
    }
    public void setXScale(float xScale)
    {
        this.xScale = xScale;
    }
    public void setYScale(float yScale)
    {
        this.yScale = yScale;
    }
    public void setZScale(float zScale)
    {
        this.zScale = zScale;
    }
    public void setXRot(float xRot)
    {
        this.xR = xRot;
    }
    public void setYRot(float yRot)
    {
        this.yR = yRot;
    }
    public void setZRot(float zRot)
    {
        this.zR = zRot;
    }
    public Vector3f getPosition()
    {
        return new Vector3f(x, y, z);
    }
    public void setXYZ(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float getPivotX()
    {
        return pivotX;
    }
    public float getPivotY()
    {
        return pivotY;
    }
    public float getPivotZ()
    {
        return pivotZ;
    }
    public Vector3f getVelocity()
    {
        return new Vector3f(xVel, yVel, zVel);
    }
    public void setMatrix(Matrix4f matrix)
    {
        this.matrix = matrix;
    }
    public void setRotation(float xR, float yR, float zR)
    {
        this.xR = xR;
        this.yR = yR;
        this.zR = zR;
    }
    public void setScale(float xScale, float yScale, float zScale)
    {
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
    }
    public void offset(float x, float y, float z)
    {
        this.x += x;
        this.y += y;
        this.z += z;
    }
    public String toString()
    {
        return "World Component [ x " + x + ", y " + y + ", z " + z + ", xR " + xR + ", yR " + yR + ", zR " + zR + ", xS " + xScale + ", yS " + yScale + ", zS " + zScale + "]";
    }
}
