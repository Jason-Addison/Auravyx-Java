package engine.physics.collision;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Owner on 6/14/2017.
 */
public class CollisionResponse
{

    Vector3f mtv;
    Vector3f localIntersection;
    public CollisionResponse(Vector3f mtv, Vector3f localIntersection)
    {
        this.mtv = mtv;
        this.localIntersection = localIntersection;
    }

    public Vector3f getMtv()
    {
        return mtv;
    }

    public void setMtv(Vector3f mtv)
    {
        this.mtv = mtv;
    }

    public Vector3f getLocalIntersection()
    {
        return localIntersection;
    }

    public void setLocalIntersection(Vector3f localIntersection)
    {
        this.localIntersection = localIntersection;
    }
}
