package engine.world.voxel;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Owner on 5/6/2017.
 */
public class VoxelNodeSolid extends VoxelNode
{
    /* This is a node that doesn't get calculated */
    public VoxelNodeSolid()
    {

    }

    public boolean isSolid()
    {
        return false;
    }

    public Vector3f getNode(int x, int y, int z)
    {
        return new Vector3f(x + 0.5f, y + 0.5f, z + 0.5f);
    }
    public boolean isEmpty()
    {
        return true;
    }
}
