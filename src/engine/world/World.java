package engine.world;

import engine.entity.Entity;
import engine.entity.ID;
import engine.game.Debug;
import engine.world.voxel.Chunk;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Owner on 2/18/2017.
 */
public class World
{

    private static HashMap<String, Entity> entities = new HashMap<>();
    private static LinkedHashMap<String, Chunk> chunks = new LinkedHashMap<>();
    public static final float GRAVITY = 9.807f / 12;
    Time time = new Time();
    public static void addEntity(Entity e)
    {
        int id = ID.generateID();
        e.setID(id);
        entities.put(id + "", e);
    }
    public static int addEntityID(Entity e)
    {
        int id = ID.generateID();
        e.setID(id);
        entities.put(id + "", e);
        return id;
    }
    public void update()
    {
        time.update();
    }
    public static HashMap<String, Entity> getEntities()
    {
        return entities;
    }
    public static void removeEntity(String id)
    {
        entities.remove(id);
    }
    public static LinkedHashMap<String, Chunk> getChunks()
    {
        return chunks;
    }
    public static void addChunk(String key, Chunk chunk)
    {
        chunks.put(key, chunk);
    }
    public static int getVoxel(int x, int y, int z)
    {
        int chunkX = x / 16;
        int chunkZ = z / 16;

        int voxel = 0;
        Chunk chunk = chunks.get(chunkX + " " + chunkZ);
        if(chunk == null)
        {
            Debug.warn("requested unloaded chunk at [" + chunkX + ", " + chunkZ + "] requesting voxels [" + x + ", " + y + ", " + z + "]", 2);
        }
        else if(y < 0 || y >= 256)
        {
            voxel = 0;
        }
        else if(x < 0)
        {
            voxel = getVoxel(x + 1, y, z);
        }
        else if(z < 0)
        {
            voxel = getVoxel(x, y, z + 1);
        }
        else
        {
            voxel = chunk.getVoxel(x % 16, y, z % 16);
        }

        return voxel;
    }
    public static int getVoxel(HashMap<String, Chunk> chunkMap, int x, int y, int z)
    {
        int chunkX = x / 16;
        int chunkZ = z / 16;

        int voxel = 0;
        if(chunks.get(chunkX + " " + chunkZ) == null)
        {
            Debug.warn("requested unloaded chunk at [" + chunkX + ", " + chunkZ + "] requesting voxels [" + x + ", " + y + ", " + z + "]", 2);
        }
        else if(y < 0 || y >= 256)
        {
            voxel = 0;
        }
        else if(x < 0)
        {
            voxel = getVoxel(x + 1, y, z);
        }
        else if(z < 0)
        {
            voxel = getVoxel(x, y, z + 1);
        }
        else
        {
            voxel = chunkMap.get(chunkX + " " + chunkZ).getVoxel(x % 16, y, z % 16);
        }

        return voxel;
    }
    public static void setVoxel(double xD, double yD, double zD, int id)
    {
        int x = (int) xD;//(int) Math.round(xD);
        int y = (int) yD;//(int) Math.round(yD);
        int z = (int) zD;//(int) Math.round(zD);
        int chunkX = x / 16;
        int chunkZ = z / 16;
        Chunk chunk = chunks.get(chunkX + " " + chunkZ);
        if(chunk != null)
        {
            chunk.getTerrain().getMesh().destroy();
            chunk.setVoxel(Math.abs(x) % 16, Math.abs(y), Math.abs(z) % 16, id);
            updateChunk(chunkX, chunkZ);
            //if(z % 16 <= 1)
            {
                updateChunk(chunkX, chunkZ - 1);
            }
            //if(x % 16 <= 1)
            {
                updateChunk(chunkX - 1, chunkZ);
            }
            updateChunk(chunkX, chunkZ + 1);
            updateChunk(chunkX, chunkZ - 1);
            updateChunk(chunkX + 1, chunkZ + 1);
            updateChunk(chunkX + 1, chunkZ - 1);
            updateChunk(chunkX - 1, chunkZ - 1);
            updateChunk(chunkX - 1, chunkZ + 1);
        }
    }
    public static void updateChunk(int x, int z)
    {
        Chunk chunk = chunks.get(x + " " + z);
        if(chunk != null)
        {
            chunk.setUpdateStatus(true);
        }
    }
    public static Chunk getChunk(int x, int z)
    {
        Chunk chunk = chunks.get(x + " " + z);
        if(chunk != null)
        {
            return chunk;
        }
        return null;
    }
    public static Entity getEntity(int id)
    {
        return entities.get(id + "");
    }
    public static void worldCollision()
    {
        
    }
}
