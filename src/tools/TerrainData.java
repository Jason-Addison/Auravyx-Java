package tools;

import java.util.ArrayList;

/**
 * Created by Owner on 3/19/2017.
 */
public class TerrainData
{

    ArrayList<Float> positions = new ArrayList<>();
    ArrayList<Float> normals = new ArrayList<>();
    ArrayList<Float> colours = new ArrayList<>();
    ArrayList<Float> textureCoords = new ArrayList<>();
    ArrayList<Float> collision = new ArrayList<>();

    public TerrainData(ArrayList<Float> positions, ArrayList<Float> normals, ArrayList<Float> colours, ArrayList<Float> coords, ArrayList<Float> collision)
    {
        this.positions = positions;
        this.normals = normals;
        this.colours = colours;
        this.textureCoords = coords;
        this.collision = collision;
    }

    public ArrayList<Float> getPositions()
    {
        return positions;
    }
    public ArrayList<Float> getNormals()
    {
        return normals;
    }
    public ArrayList<Float> getColours()
    {
        return colours;
    }
    public ArrayList<Float> getCoords()
    {
        return textureCoords;
    }
    public ArrayList<Float> getCollision()
    {
        return collision;
    }
}
