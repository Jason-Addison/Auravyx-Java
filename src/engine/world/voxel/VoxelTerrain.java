package engine.world.voxel;

import engine.Loader;
import engine.graphics.model.Model;
import engine.world.World;
import engine.world.terrain.Terrain;
import tools.TerrainData;

import java.util.ArrayList;

/**
 * Created by Owner on 3/14/2017.
 */
public class VoxelTerrain
{

    static VoxelMesh meshMaker = new VoxelMesh();
    int[][][] chunkData = new int[21][256][21];
    ArrayList<Float> collisionMesh = new ArrayList<>();
    int meshSize;
    Model mesh;
    int chunkX;
    int chunkZ;
    public VoxelTerrain(int chunkX, int chunkZ)
    {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        for(int x = 0; x < 21; x++)
        {
            for(int y = 0; y < 256; y++)
            {
                for(int z = 0; z < 21; z++)
                {
                    int terrainHeight = (int) (Terrain.getHeightOfTerrain(x + chunkX * 16 - chunkX * 2, z + chunkZ * 16 - chunkZ * 2)) / 4;//(Terrain.getHeightOfTerrain(x + chunkX * 16 - (50 * 16 / 2), z + chunkZ * 16 - (50 * 16 / 2))) / 4 + 5;

                    if(y < terrainHeight)
                    {
                        if(x % 2 == 1)
                        {
                            chunkData[x][y][z] = 1;
                        }
                        else
                        {
                            chunkData[x][y][z] = 1;
                        }
                    }
                    if(y > 20)
                    {
                        //chunkData[x][y][z] = 1;
                    }
                }
            }
        }
        chunkData[14][6][14] = 1;
        chunkData[15][6][14] = 1;
        chunkData[14][6][15] = 1;
        chunkData[15][6][15] = 1;
        chunkData[15][6][14] = 1;
        chunkData[15][7][15] = 1;
    }

    public void generateMesh()
    {
        //Material material;// = new Material(0.5f, 1f, 1.0f);
        //[] positions = new float[meshSize * 10000];
        ArrayList<Float> positions = new ArrayList<>();
        ArrayList<Float> colours = new ArrayList<>();
        ArrayList<Float> normals = new ArrayList<>();
        ArrayList<Float> coords = new ArrayList<>();
        int vertexIndex = 0;
        //positions[0] = 5;
        int chunkXOff = chunkX * 16;
        int chunkZOff = chunkZ * 16;
        TerrainData data = meshMaker.createVoxelMesh(chunkData, chunkXOff, chunkZOff);
        positions = data.getPositions();
        normals = data.getNormals();
        coords = data.getCoords();
        colours = data.getColours();
        //this.collisionMesh = data.getCollision();
        float[] newPositions = new float[positions.size()];
        float[] newColours = new float[positions.size()];
        float[] newNormals = new float[positions.size()];
        float[] textureCoords = new float[positions.size() / 3 * 2];
        for(int i = 0; i < positions.size(); i++)
        {
            if(i % 3 == 0)
            {
                newPositions[i] = positions.get(i) + chunkXOff;
            }
            if(i % 3 == 1)
            {
                newPositions[i] = positions.get(i);
            }
            if(i % 3 == 2)
            {
                newPositions[i] = positions.get(i) + chunkZOff;
            }
            newColours[i] = colours.get(i);
            newNormals[i] = normals.get(i);
            if(i < coords.size())
            {
                textureCoords[i] = coords.get(i);
            }
        }

        for(int i = 0; i < positions.size() / 9; i++)
        {
            if(i % 2 == 0)
            {
                collisionMesh.add(positions.get(i * 9 + 0) + chunkXOff);
                collisionMesh.add(positions.get(i * 9 + 1));
                collisionMesh.add(positions.get(i * 9 + 2) + chunkZOff);
                collisionMesh.add(positions.get(i * 9 + 3) + chunkXOff);
                collisionMesh.add(positions.get(i * 9 + 4));
                collisionMesh.add(positions.get(i * 9 + 5) + chunkZOff);
                collisionMesh.add(positions.get(i * 9 + 6) + chunkXOff);
                collisionMesh.add(positions.get(i * 9 + 7));
                collisionMesh.add(positions.get(i * 9 + 8) + chunkZOff);

                collisionMesh.add(positions.get(i * 9 + 9) + chunkXOff);
                collisionMesh.add(positions.get(i * 9 + 10));
                collisionMesh.add(positions.get(i * 9 + 11) + chunkZOff);
            }
        }
        for(int x = 0; x < 18; x++)
        {
            for(int y = 0; y < 256; y++)
            {
                for(int z = 0; z < 18; z++)
                {

                }
            }
        }
        //newNormals = Loader.generateNormals(newPositions);
        //These arrays make tons of memory go to waste when filled
        mesh = Loader.load3DModel(newPositions, newColours, newNormals, textureCoords);
    }
    private ArrayList<Float> addColour(ArrayList<Float> colours, float r, float g, float b)
    {
        colours.add(r);
        colours.add(g);
        colours.add(b);
        return colours;
    }
    private ArrayList<Float> addVertex(ArrayList<Float> vertices, float x, float y, float z)
    {
        vertices.add(x);
        vertices.add(y);
        vertices.add(z);
        return vertices;
    }

    public boolean[] checkAround(int x, int y, int z)
    {
        boolean[] surroundingBlocks = new boolean[6];

        if(isTransparentMaterial(x - 1, y, z))
        {
            surroundingBlocks[0] = true;
        }
        if(isTransparentMaterial(x + 1, y, z))
        {
            surroundingBlocks[1] = true;
        }
        if(isTransparentMaterial(x, y - 1, z))
        {
            surroundingBlocks[2] = true;
        }
        if(isTransparentMaterial(x, y + 1, z))
        {
            surroundingBlocks[3] = true;
        }
        if(isTransparentMaterial(x, y, z - 1))
        {
            surroundingBlocks[4] = true;
        }
        if(isTransparentMaterial(x, y, z + 1))
        {
            surroundingBlocks[5] = true;
        }
        return surroundingBlocks;
    }

    public boolean isTransparentMaterial(int x, int y, int z)
    {
        if(x < 0)
        {
            if(World.getChunks().get(this.chunkX - 1 + " " + this.chunkZ) != null &&
                    World.getChunks().get(this.chunkX - 1 + " " + this.chunkZ).getTerrain().isTransparentMaterial(15, y, z))
            {
                return true;
            }
        }
        if(x > 15)
        {
            if(World.getChunks().get(this.chunkX + 1 + " " + this.chunkZ) != null &&
                    World.getChunks().get(this.chunkX + 1 + " " + this.chunkZ).getTerrain().isTransparentMaterial(0, y, z))
            {
                return true;
            }
        }
        if(z < 0)
        {
            if(World.getChunks().get(this.chunkX + " " + (this.chunkZ - 1)) != null &&
                    World.getChunks().get(this.chunkX + " " + (this.chunkZ - 1)).getTerrain().isTransparentMaterial(x, y, 15))
            {
                return true;
            }
        }
        if(z > 15)
        {
            if(World.getChunks().get(this.chunkX + " " + this.chunkZ + 1) != null &&
                    World.getChunks().get(this.chunkX + " " + this.chunkZ + 1).getTerrain().isTransparentMaterial(x, y, 0))
            {
                return true;
            }
        }
        if(x < 0 || x > 15)
        {
            return false;
        }
        if(y < 0 || y > 255)
        {
            return false;
        }
        if(z < 0 || z > 15)
        {
            return false;
        }
        return chunkData[x][y][z] == 0;
    }

    public Model getMesh()
    {
        return mesh;
    }

    public void setVoxel(int x, int y, int z, int id)
    {
        chunkData[x][y][z] = id;
    }
    public int[][][] getChunkData()
    {
        return chunkData;
    }
    public int getVoxel(int x, int y, int z)
    {
        return chunkData[x][y][z];
    }

    public ArrayList<Float> getCollisionMesh()
    {
        return collisionMesh;
    }
}
