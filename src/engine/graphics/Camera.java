package engine.graphics;

/**
 * Created by Owner on 2/14/2017.
 */
public class Camera
{

    private float x, y, z;
    private float xRot, yRot, zRot;

    public Camera()
    {

    }

    public void set(float x, float y, float z, float xRot, float yRot, float zRot)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xRot = xRot;
        this.yRot = yRot;
        this.zRot = zRot;
    }
    public void set(Camera camera)
    {
        this.x = camera.x;
        this.y = camera.y;
        this.z = camera.z;
        this.xRot = camera.getXRot();
        this.yRot = camera.getYRot();
        this.zRot = camera.getZRot();
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
        return xRot;
    }
    public float getYRot()
    {
        return yRot;
    }
    public float getZRot()
    {
        return zRot;
    }
}
