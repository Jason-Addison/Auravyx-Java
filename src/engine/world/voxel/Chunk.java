package engine.world.voxel;

import java.util.HashMap;

/**
 * Created by Owner on 3/15/2017.
 */
public class Chunk
{

    int x;
    int z;
    VoxelTerrain terrain;
    boolean updateStatus;
    public Chunk(int x, int z)
    {
        terrain = new VoxelTerrain(x, z);
        this.x = x;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }
    public int getZ()
    {
        return z;
    }
    public VoxelTerrain getTerrain()
    {
        return terrain;
    }
    public void setVoxel(int x, int y, int z, int id)
    {
        terrain.setVoxel(x, y, z, id);
    }
    public void update()
    {
        if(updateStatus)
        {
            generateVoxelMesh();
            updateStatus = false;
        }
    }
    public void generateVoxelMesh()
    {
        //HashMap<String, Chunk> nearChunks = new HashMap<>();
        /*nearChunks.put(x + " " + z, this);
        nearChunks.put((x + 1) + " " + (z), World.getChunk(x + 1, z));
        nearChunks.put((x - 1) + " " + (z), World.getChunk(x - 1, z));
        nearChunks.put((x + 1) + " " + (z + 1), World.getChunk(x + 1, z + 1));
        nearChunks.put((x - 1) + " " + (z + 1), World.getChunk(x - 1, z + 1));
        nearChunks.put((x + 1) + " " + (z - 1), World.getChunk(x + 1, z - 1));
        nearChunks.put((x - 1) + " " + (z - 1), World.getChunk(x - 1, z - 1));
        nearChunks.put((x) + " " + (z + 1), World.getChunk(x, z + 1));
        nearChunks.put((x) + " " + (z - 1), World.getChunk(x, z - 1));*/
        terrain.generateMesh();
    }
    public void setUpdateStatus(boolean toggle)
    {
        this.updateStatus = toggle;
    }
    public int getVoxel(int x, int y, int z)
    {
        return terrain.getVoxel(x, y, z);
    }
}
