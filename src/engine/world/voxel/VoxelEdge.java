package engine.world.voxel;

import org.lwjgl.util.vector.Vector3f;
import tools.vector.Vec;

import java.util.ArrayList;

/**
 * Created by Owner on 3/16/2017.
 */
public class VoxelEdge
{

    //Vector3f main;
    ArrayList<Vector3f> edgeCrosses = new ArrayList<>();
    ArrayList<Integer> edgeType = new ArrayList<>();
    public VoxelEdge()
    {

    }

    public void addEdgeCross(Vector3f cross, Integer type, Integer type2)
    {
        Integer typeNotZero = 3;
        if(type != 0)
        {
            typeNotZero = type;
        }
        if(type2 != 0)
        {
            typeNotZero = type2;
        }

        edgeCrosses.add(cross);
        edgeType.add(typeNotZero);

    }
    public ArrayList<Vector3f> getEdgeCrosses()
    {
        return edgeCrosses;
    }

    public Vector3f getAverage()
    {
        Vector3f average = new Vector3f(0, 0, 0);

        for(int i = 0; i < edgeCrosses.size(); i++)
        {
            average = Vec.vecAdd(average, edgeCrosses.get(i));
        }

        average = Vec.divideVec(average, edgeCrosses.size());
        if(average.getX() == 0 || average.getY() == 0 || average.getZ() == 0)
        {
            System.err.println("null average!");
        }
        return average;
    }
    public Vector3f getAverageMaterial()
    {
        Vector3f avg = new Vector3f(0, 0, 0);
        for(int i = 0; i < edgeType.size(); i++)
        {
            avg = Vec.vecAdd(avg, Material.getMaterial(edgeType.get(i)).getColourAsVector());
        }
        avg = Vec.divideVec(avg, edgeType.size());
        return avg;
    }
}
