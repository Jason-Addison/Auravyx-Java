package engine.world.terrain;

import engine.Loader;
import engine.graphics.model.Model;
import tools.Maths;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Owner on 2/19/2017.
 */
public class Terrain
{
    private Model terrainModel;

    private int width = 64;
    private int height = 64;
    private float[][] heights;
    public Terrain()
    {
        heights = new float[width][height];
    }
    public void generate(int chunkX, int chunkZ)
    {
        int DATA_SIZE = width * height * 6;
        float[] vertices = new float[DATA_SIZE * 3];
        float[] normals = new float[DATA_SIZE * 3];
        int vertexPointer = 0;
        for(int x = 0; x < width; x++)
        {
            for(int z = 0; z < height; z++)
            {
                heights[x][z] = Maths.randomFloat(0, 1f) + 1;

                int gridSize = 1;
                int worldX = (chunkX * width * gridSize) + (gridSize * x);
                int worldZ = (chunkZ * height * gridSize) + (gridSize * z);

                float heightTL = getHeightOfTerrain(worldX, worldZ);
                float heightTR = getHeightOfTerrain(worldX + 1, worldZ);
                float heightBL = getHeightOfTerrain(worldX, worldZ + 1);
                float heightBR = getHeightOfTerrain(worldX + 1, worldZ + 1);

                float tX = x;
                float tY = heights[x][z];
                float tZ = z;
                vertices[(vertexPointer * 18) + 17] = tX * gridSize;
                vertices[(vertexPointer * 18) + 16] = heightTL;
                vertices[(vertexPointer * 18) + 15] = tZ * gridSize;
                vertices[(vertexPointer * 18) + 14] = (tX * gridSize);
                vertices[(vertexPointer * 18) + 13] = heightBL;
                vertices[(vertexPointer * 18) + 12] = (tZ * gridSize) + gridSize;
                vertices[(vertexPointer * 18) + 11] = (tX * gridSize) + gridSize;
                vertices[(vertexPointer * 18) + 10] = heightTR;
                vertices[(vertexPointer * 18) + 9] = (tZ * gridSize);

                vertices[(vertexPointer * 18) + 8] = (tX * gridSize) + gridSize;
                vertices[(vertexPointer * 18) + 7] = heightBR;
                vertices[(vertexPointer * 18) + 6] = (tZ * gridSize) + gridSize;
                vertices[(vertexPointer * 18) + 5] = (tX * gridSize) + gridSize;
                vertices[(vertexPointer * 18) + 4] = heightTR;
                vertices[(vertexPointer * 18) + 3] = (tZ * gridSize);
                vertices[(vertexPointer * 18) + 2] = (tX * gridSize);
                vertices[(vertexPointer * 18) + 1] = heightBL;
                vertices[(vertexPointer * 18) + 0] = (tZ * gridSize) + gridSize;
                vertexPointer++;
            }
        }
        normals = Loader.generateNormals(vertices);
        terrainModel = Loader.load3DModel(vertices, normals);
    }
    public Model getTerrain()
    {
        return terrainModel;
    }
    public static float getHeightOfTerrain(int x, int z)
    {
        x = x + 800;//Math.abs(x);
        z = z +800;//Math.abs(z);
        x = x % heightMap.getWidth();
        z = z % heightMap.getHeight();
        if(x < heightMap.getWidth() && z < heightMap.getHeight() && x > 0 && z > 0)
        {
            int rgb = heightMap.getRGB(x, z);
            Color color = new Color(rgb);
            float height = color.getRed();
            return height * 2;
        }
        return 3;
    }
    public static BufferedImage heightMap;
    public static void loadMap()
    {
        try
        {
            heightMap = ImageIO.read(new File("D:/meme.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
