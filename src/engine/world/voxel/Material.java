package engine.world.voxel;

import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

import java.util.HashMap;

/**
 * Created by Owner on 3/14/2017.
 */
public class Material
{

    float r, g, b;
    String simpleName;
    int id;
    public static HashMap<String, Material> materials = new HashMap<>();
    public static HashMap<Integer, Material> materialsID = new HashMap<>();
    public Material(float r, float g, float b, int id, String simpleName)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.id = id;
        this.simpleName = simpleName;
    }

    public float getR()
    {
        return r;
    }

    public float getG()
    {
        return g;
    }

    public float getB()
    {
        return b;
    }

    public static void register(String simpleName, int id, float r, float g, float b)
    {
        materials.put(simpleName, new Material(r, g, b, id, simpleName));
        materialsID.put(id, new Material(r, g, b, id, simpleName));
    }
    public void toneColour()
    {
        float randomness = 0.02f;
        r = (Maths.randomFloat(r - (randomness / 2), r + (randomness / 2)));
        g = (Maths.randomFloat(g - (randomness / 2), g + (randomness / 2)));
        b = (Maths.randomFloat(b - (randomness / 2), b + (randomness / 2)));
    }
    public void shadeColour(int x, int y, int z)
    {
        float scale = 0.02f;
        float offset = (float) Math.sin((x + y + z)) * 0.01f;
        r += offset;
        g += offset;
        b += offset;
    }
    public static Material getCopy(Material material)
    {
        return material;
    }
    public String getSimpleName()
    {
        return simpleName;
    }
    public int getId()
    {
        return id;
    }
    public static Material getMaterial(int id)
    {
        return materialsID.get(id);
    }
    public Vector3f getColourAsVector()
    {
        return new Vector3f(r, g, b);
    }
}
