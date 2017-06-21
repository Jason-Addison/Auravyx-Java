package engine.world.voxel;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;
import tools.TerrainData;

import java.util.ArrayList;

/**
 * Created by Owner on 3/15/2018.
 */
public class VoxelMesh
{

    public TerrainData createVoxelMesh(int[][][] voxelData, int xOff, int zOff)
    {
        /* Voxel data being a 16 x 16 x 256 chunk */
        /* Vertices will be the final exported vertex list */
        ArrayList<Vector3f> vertices = new ArrayList<>();

        VoxelNode voxels[][][] = new VoxelNode[21][256][21];
        VoxelEdge[][][] edges = new VoxelEdge[21][256][21];
        for(int x = 1; x < 20; x++)
        {
            for(int z = 1; z < 20; z++)
            {
                for(int y = 2; y < 255; y++)
                {
                    /* Check edge intersection
                    *
                    * list of all 6 edges
                    * if null / not set, ignore in average calculations
                    */
                    if(true)
                    {
                        //if(checkAround(voxelData, x, y, z))
                        {
                            {
                                VoxelEdge crossing = findEdgeCrossing(voxelData, x, y, z);
                                if(crossing != null)
                                {
                                    Vector3f average = crossing.getAverage();
                                    Vector3f averageMaterial = crossing.getAverageMaterial();
                                    //average = new Vector3f(x, y, z);
                                    voxels[x][y][z] = new VoxelNode(new Vector3f(average.getX() - 0.0f, average.getY() - 0.0f, average.getZ() - 0.0f), x, y, z, averageMaterial); ///////////////offff
                                }
                                else if(voxelData[x][y][z] != 0)
                                {
                                    voxels[x][y][z] = new VoxelNodeSolid();
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int x = 0; x < 21; x++)
        {
            for (int z = 0; z < 21; z++)
            {
                for (int y = 0; y < 255; y++)
                {
                    if(voxels[x][y][z] != null && !voxels[x][y][z].isEmpty())// && voxels[x][y][z].getClass().getSimpleName().equals("VoxelNode"))
                    {
                        voxels = voxels[x][y][z].normalChecker(voxels, voxelData, x, y, z);
                    }
//                    if(voxels[x][y][z] != null && voxels[x][y][z].getNode().getX() == voxels[a][b][c].getNode().getX() && voxels[x][y][z].getNode().getY() == voxels[a][b][c].getNode().getY()
//                            && voxels[x][y][z].getNode().getZ() == voxels[a][b][c].getNode().getZ())
                    {
                        //System.out.println("XSAFD");
                    }
                }
            }
        }
        /*
            Go through nodes and find neighbours to make triangles
         */
        ArrayList<Float> positions = new ArrayList<>();
        ArrayList<Float> normals = new ArrayList<>();
        ArrayList<Float> colours = new ArrayList<>();
        ArrayList<Float> textureCoords = new ArrayList<>();
        ArrayList<Float> collision = new ArrayList<>();
        for(int x = 0; x < 21; x++)
        {
            for (int z = 0; z < 21; z++)
            {
                for (int y = 0; y < 255; y++)
                {
                    if(voxels[x][y][z] != null && !voxels[x][y][z].isEmpty())
                    {
                        voxels[x][y][z].neighbourScan(voxels, voxelData, x, y, z);
                    }
                    if(voxels[x][y][z] != null && !voxels[x][y][z].isEmpty())
                    {
                        //Material material = Material.getMaterial(getClosestNonAir(voxelData, x, y, z));
                        ArrayList<Vector3f> triangles = voxels[x][y][z].getAllTriangles();
                        ArrayList<Vector3f> colourVec = voxels[x][y][z].getAllColours();
                        ArrayList<Vector3f> normalVec = voxels[x][y][z].getAllNormals();
                        ArrayList<Vector2f> coordVec = voxels[x][y][z].getTextureCoords();
                        ArrayList<Vector3f> collisionVec = voxels[x][y][z].getCollision();

                        for(int i = 0; i < triangles.size(); i++)
                        {
                            if(x > 2 && y > 2 && z > 2 && x < 18 && y < 255 && z < 18)
                            {
                                positions.add(triangles.get(i).getX() - xOff / 8 - 2);
                                positions.add(triangles.get(i).getY());
                                positions.add(triangles.get(i).getZ() - zOff / 8 - 2);
                                if(colourVec.get(i) != null)
                                {
                                    colours.add(colourVec.get(i).getX());
                                    colours.add(colourVec.get(i).getY());
                                    colours.add(colourVec.get(i).getZ());
                                }
                                if(normalVec.get(i) != null)
                                {
                                    normals.add(normalVec.get(i).getX());
                                    normals.add(normalVec.get(i).getY());
                                    normals.add(normalVec.get(i).getZ());
                                }
                                if(i < coordVec.size())
                                {
                                    textureCoords.add(coordVec.get(i).getX());
                                    textureCoords.add(coordVec.get(i).getY());
                                }
                            }
                            if(i < collisionVec.size() && collisionVec.get(i) != null)
                            {
                                collision.add(collisionVec.get(i).getX());
                                collision.add(collisionVec.get(i).getY());
                                collision.add(collisionVec.get(i).getZ());
                            }
                        }
                        for(int i = 0; i < normals.size(); i++)
                        {
                        }
                    }
                }
            }
        }
        /* Clear off all duplicate vertices caused by not checking */

        return new TerrainData(positions, normals, colours, textureCoords, collision);
    }
    private void d()
    {

    }
    private VoxelEdge findEdgeCrossing(int[][][] voxelData, int x, int y, int z)
    {
        VoxelEdge voxelEdge = new VoxelEdge();
        //if(x > 1 && y > 1 && z > 1 && x < 20 && y < 255 && z < 20)
        {
            if(x < 20 && voxelData[x][y][z] != voxelData[x + 1][y][z])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 0.5f, y, z), voxelData[x][y][z], voxelData[x + 1][y][z]);
            }
            if(x < 20 && z < 20 && voxelData[x + 1][y][z] != voxelData[x + 1][y][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 1, y, z + 0.5f), voxelData[x + 1][y][z], voxelData[x + 1][y][z + 1]);
            }
            if(z < 20 && voxelData[x][y][z] != voxelData[x][y][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y, z + 0.5f), voxelData[x][y][z], voxelData[x][y][z + 1]);
            }
            if(x < 20 && z < 20 && voxelData[x][y][z + 1] != voxelData[x + 1][y][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 0.5f, y, z + 1f), voxelData[x][y][z + 1], voxelData[x + 1][y][z + 1]);
            }

            if(voxelData[x][y][z] != voxelData[x][y + 1][z] && (voxelData[x][y + 1][z] != voxelData[x][y - 1][z]))
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y + 0.5f, z), voxelData[x][y][z], voxelData[x][y + 1][z]);
            }
            if(x < 20 && voxelData[x + 1][y][z] != voxelData[x + 1][y + 1][z])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 1, y + 0.5f, z + 0f), voxelData[x + 1][y][z], voxelData[x + 1][y + 1][z]);
            }
            if(z < 20 && voxelData[x][y][z + 1] != voxelData[x][y + 1][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y + 0.5f, z + 1), voxelData[x][y][z + 1], voxelData[x][y + 1][z + 1]);
            }
            if(x < 20 && z < 20 && voxelData[x + 1][y][z + 1] != voxelData[x + 1][y + 1][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 1, y + 0.5f, z + 1f), voxelData[x + 1][y][z + 1], voxelData[x + 1][y + 1][z + 1]);
            }

            if(x < 20 && voxelData[x][y + 1][z] != voxelData[x + 1][y + 1][z])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 0.5f, y + 1, z), voxelData[x][y + 1][z], voxelData[x + 1][y + 1][z]);
            }
            if(x < 20 && z < 20 && voxelData[x + 1][y + 1][z] != voxelData[x + 1][y + 1][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 1, y + 1, z + 0.5f), voxelData[x + 1][y + 1][z], voxelData[x + 1][y + 1][z + 1]);
            }
            if(z < 20 && voxelData[x][y + 1][z] != voxelData[x][y + 1][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y + 1, z + 0.5f), voxelData[x][y + 1][z], voxelData[x][y + 1][z + 1]);
            }
            if(x < 20 && z < 20 && voxelData[x][y + 1][z + 1] != voxelData[x + 1][y + 1][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 0.5f, y + 1, z + 1f), voxelData[x][y + 1][z + 1], voxelData[x + 1][y + 1][z + 1]);
            }

        }

        {
            //voxelEdge.addEdgeCross(new Vector3f(x + 0.5f, y + 0.5f, z + 0.5f), 1, 1);
        }
        /*if(x > 1 && y > 1 && z > 1)
        {
            if(voxelData[x][y][z] != voxelData[x - 1][y][z])
            {
                voxelEdge.addEdgeCross(new Vector3f(x - 0.5f, y + 0.5f, z), voxelData[x][y][z], voxelData[x - 1][y][z]);
            }
            if(voxelData[x][y][z] != voxelData[x + 1][y][z])
            {
                voxelEdge.addEdgeCross(new Vector3f(x + 0.5f, y + 0.5f, z), voxelData[x][y][z], voxelData[x + 1][y][z]);
            }
            if(voxelData[x][y][z] != voxelData[x][y - 1][z])
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y - 0.5f, z), voxelData[x][y][z], voxelData[x][y - 1][z]);
            }
            if(voxelData[x][y][z] != voxelData[x][y + 1][z])
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y + 0.5f, z), voxelData[x][y][z], voxelData[x][y + 1][z]);
            }
            if(voxelData[x][y][z] != voxelData[x][y][z - 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y, z - 0.5f), voxelData[x][y][z], voxelData[x][y][z - 1]);
            }
            if(voxelData[x][y][z] != voxelData[x][y][z + 1])
            {
                voxelEdge.addEdgeCross(new Vector3f(x, y, z + 0.5f), voxelData[x][y][z], voxelData[x][y][z + 1]);
            }
        }*/
        if(voxelEdge.getEdgeCrosses().size() <= 0)
        {
            return null;
        }

        return voxelEdge;
    }

    private Vector3f[] findClosestVerts(Vector3f vert, ArrayList<Vector3f> vertices, int index)
    {
        /* 0 = closest, 1 = second closest */
        Vector3f[] closestVerts = new Vector3f[2];
        float distBest = Float.MAX_VALUE;
        float distSecond = Float.MAX_VALUE;

        for(int i = 0; i < vertices.size(); i++)
        {
            if(i != index)
            {
                Vector3f vertex = vertices.get(i);
                float dist = Maths.distanceBetween(vert, vertex);
                if(dist < distBest)
                {
                    closestVerts[0] = vertex;
                    distBest = dist;
                }
                else if(dist < distSecond)
                {
                    closestVerts[1] = vertex;
                    distSecond = dist;
                }
            }
        }
        return closestVerts;
    }

    private boolean checkAround(int[][][] vox, int x, int y, int z)
    {
        if(x > 1 && y > 1 && z > 1 && x < 20 && y < 20 && z < 20)
        {
            if(vox[x][y][z] != vox[x + 1][y][z] || vox[x][y][z] != vox[x - 1][y][z] || vox[x][y][z] != vox[x][y + 1][z] || vox[x][y][z] != vox[x][y - 1][z] || vox[x][y][z] != vox[x][y][z + 1] || vox[x][y][z] != vox[x][y][z - 1])
            {
                return false;
            }
        }
        return true;
    }
}
