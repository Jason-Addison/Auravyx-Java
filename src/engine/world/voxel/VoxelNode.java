package engine.world.voxel;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;
import tools.vector.Vec;

import java.util.ArrayList;

/**
 * Created by Owner on 3/16/2012.
 */
public class VoxelNode
{
    float xN, yN, zN;
    int x, y, z;
    Vector3f material;
    ArrayList<Vector3f> triangles = new ArrayList<>();
    ArrayList<Vector3f> colours = new ArrayList<>();
    ArrayList<Vector3f> normals = new ArrayList<>();
    ArrayList<Vector3f> unprocessedNormals = new ArrayList<>();
    ArrayList<Vector2f> textureCoords = new ArrayList<>();
    ArrayList<Vector3f> collision = new ArrayList<>();
    Vector3f emptyVector = new Vector3f(0, 0, 0);
    public VoxelNode()
    {

    }
    public VoxelNode(Vector3f vec, int x, int y, int z, Vector3f material)
    {
        this.xN = vec.getX();
        this.yN = vec.getY();
        this.zN = vec.getZ();
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = material;
    }

    /*

    y+
    |
    |
    ------z+
     \
      \
       x+



     */

    public VoxelNode[][][] normalChecker(VoxelNode[][][] voxelData, int[][][] worldData, int x, int y, int z)
    {

        //if(worldData[x][y][z] != worldData[x + 1][y][z] || worldData[x][y][z] != worldData[x][y + 1][z] || worldData[x][y][z] != worldData[x][y][z + 1] ||
        //        worldData[x][y][z] != worldData[x - 1][y][z] || worldData[x][y][z] != worldData[x][y - 1][z] || worldData[x][y][z] != worldData[x][y][z - 1])
        {
            if(x < 19 && voxelData[x + 1][y][z] != null && !voxelData[x + 1][y][z].isEmpty() && voxelData[x + 1][y + 1][z] != null && !voxelData[x + 1][y + 1][z].isEmpty() && voxelData[x][y + 1][z] != null && !voxelData[x][y + 1][z].isEmpty())
            {
                voxelData = addFace(voxelData, x, y, z, x + 1, y, z, x + 1, y + 1, z, x, y + 1, z, 0, 0, 1);
                voxelData = addFace(voxelData, x, y, z, x, y + 1, z, x + 1, y + 1, z, x + 1, y, z, 0, 0, 1);

            }

            if(x < 20 && z < 20 && voxelData[x + 1][y][z] != null && !voxelData[x + 1][y][z].isEmpty() && voxelData[x + 1][y][z + 1] != null && !voxelData[x + 1][y][z + 1].isEmpty() && voxelData[x][y][z + 1] != null && !voxelData[x][y][z + 1].isEmpty())
            {
                voxelData = addFace(voxelData, x, y, z,x + 1, y, z, x + 1, y, z + 1, x, y, z + 1, 0, 1, 0);
                voxelData = addFace(voxelData, x, y, z, x, y, z + 1, x + 1, y, z + 1, x + 1, y, z, 0, 1, 0);
            }

            if(z < 20 && voxelData[x][y][z + 1] != null && !voxelData[x][y][z + 1].isEmpty() && voxelData[x][y + 1][z + 1] != null && !voxelData[x][y + 1][z + 1].isEmpty() && voxelData[x][y + 1][z] != null && !voxelData[x][y + 1][z].isEmpty())
            {
                voxelData = addFace(voxelData, x, y, z, x, y, z + 1, x, y + 1, z + 1, x, y + 1, z, 1, 0, 0);
                voxelData = addFace(voxelData, x, y, z, x, y + 1, z, x, y + 1, z + 1, x, y, z + 1, 1, 0, 0);
            }
        }

        return voxelData;
    }

    private static boolean checkInvert(VoxelNode[][][] data, int x, int y, int z)
    {
        if(data[x][y][z] == null)// || data[x][y][z].isEmpty())
        {
            return true;
        }
        return false;
    }

    public static VoxelNode[][][] addFace(VoxelNode[][][] data, int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3, int x4, int y4, int z4, int x, int y, int z)
    {
        VoxelNode a = data[x1][y1][z1];
        VoxelNode b = data[x2][y2][z2];
        VoxelNode c = data[x3][y3][z3];
        VoxelNode d = data[x4][y4][z4];
        //if(isValid(data, a, x, y, z) || isValid(data, b, x, y, z) || isValid(data, c, x, y, z) || isValid(data, d, x, y, z))
        {
            //avg = Vec.vecAdd(avg, vec2);
            //avg = Vec.vecAdd(avg, vec3);
            //avg = Vec.divideVec(avg, 4);

            Vector3f node0 = data[x1][y1][z1].getNode();
            Vector3f node1 = data[x2][y2][z2].getNode();
            Vector3f node2 = data[x3][y3][z3].getNode();
            Vector3f normal = new Vector3f();
            Vector3f v = new Vector3f(node1.x - node0.x, node1.y - node0.y, node1.z - node0.z);
            Vector3f w = new Vector3f(node2.x - node0.x, node2.y - node0.y, node2.z - node0.z);

            Vector3f n = new Vector3f();

            n.x = (v.y * w.z) - (v.z * w.y);
            n.y = (v.z * w.x) - (v.x * w.z);
            n.z = (v.x * w.y) - (v.y * w.x);


            normal.x = w.x;//n.x / (Math.abs(n.x) + (Math.abs(n.y) + Math.abs(n.z)));
            normal.y = w.y;//n.y / (Math.abs(n.x) + (Math.abs(n.y) + Math.abs(n.z)));
            normal.z = w.z;//n.z / (Math.abs(n.x) + (Math.abs(n.y) + Math.abs(n.z)));

            //normal.x = (node1.x - node0.x) * (node2.x - node0.x);
           // normal.y = (node1.y - node0.y) * (node2.y - node0.y);
           /// normal.z = (node1.z - node0.z) * (node2.z - node0.z);

            normal = Vec.flip(normal);
            //normal = Vec.flip(normal);
            //normal = Maths.invert(normal);

            //normal = Maths.invert(normal);
            if(normal.x < 0.05 && normal.y < 0.05 && normal.z < 0.05)// > -0.05 && normal.y > -0.05 && normal.z > -0.05)
            {
               // normal = Vec.flip(normal);
            }
            if(x1 > 1 && checkInvert(data, x1 - 1, y1, z1))
            {
                normal.x = -normal.x;
            }
            else if(x2 > 1 && checkInvert(data, x2 - 1, y2, z2))
            {
                normal.x = -normal.x;
            }
            else if(x3 > 1 && checkInvert(data, x3 - 1, y3, z3))
            {
                normal.x = -normal.x;
            }
            else if(x4 > 1 && checkInvert(data, x4 - 1, y4, z4))
            {
                normal.x = -normal.x;
            }
            if (y1 > 1 && checkInvert(data, x1, y1 - 1, z1))
            {
                normal.y = -normal.y;
            }
            else if (y2 > 1 && checkInvert(data, x2, y2 - 1, z2))
            {
                normal.y = -normal.y;
            }
            else if (y3 > 1 && checkInvert(data, x3, y3 - 1, z3))
            {
                normal.y = -normal.y;
            }
            else if (y4 > 1 && checkInvert(data, x4, y4 - 1, z4))
            {
                normal.y = -normal.y;
            }
            if (z1 > 1 && checkInvert(data, x1, y1, z1 - 1))
            {
                normal.z = -normal.z;
            }
            else if (z2 > 1 && checkInvert(data, x2, y2, z2 - 1))
            {
                normal.z = -normal.z;
            }
            else if (z3 > 1 && checkInvert(data, x3, y3, z3 - 1))
            {
                normal.z = -normal.z;
            }
            else if (z4 > 1 && checkInvert(data, x4, y4, z4 - 1))
            {
                normal.z = -normal.z;
            }
            /*if(x1 == 0 || x2 == 0 || x3 == 0 || x4 == 0)
            {
                normal.x = -normal.x;
            }
            if(y1 == 0 || y2 == 0 || y3 == 0 || y4 == 0)
            {
                normal.y = -normal.y;
            }
            if(z1 == 0 || z2 == 0 || z3 == 0 || z4 == 0)
            {
                normal.z = -normal.z;
            }*/
            data[x1][y1][z1].addNormal(normal.x, normal.y, normal.z);
            data[x2][y2][z2].addNormal(normal.x, normal.y, normal.z);
            data[x3][y3][z3].addNormal(normal.x, normal.y, normal.z);
        }

        return data;
    }
    public void addNormal(float x, float y, float z)
    {
        unprocessedNormals.add(new Vector3f(x, y, z));

    }
    public void neighbourScan(VoxelNode[][][] voxelData, int[][][] worldData, int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        //voxelData[5][7][5].setNode(0, 0, 0);
        //voxelData[2][4][5].setNode(0, 0, 0);
        //voxelData[4][5][5] = new VoxelNode(new Vector3f(13, 5, 0), x, y, z, new Vector3f(0, 1, 2));

        if(voxelData[x + 1][y][z] != null && !voxelData[x + 1][y][z].isEmpty() && voxelData[x + 1][y + 1][z] != null && !voxelData[x + 1][y + 1][z].isEmpty() && voxelData[x][y + 1][z] != null && !voxelData[x][y + 1][z].isEmpty())// && (worldData[x + 1][y + 1][z + 1] != worldData[x + 1][y + 1][z - 1] || (worldData[x + 1][y + 1][z + 0] != worldData[x + 1][y + 1][z + 1] && worldData[x + 1][y + 1][z + 0] != worldData[x + 1][y + 1][z - 1])))
        {
            addTriangle(this, voxelData[x + 1][y][z], voxelData[x + 1][y + 1][z], voxelData[x][y + 1][z], voxelData, worldData, 0, 0, 1, true);
            addTriangle(voxelData[x + 1][y + 1][z], voxelData[x][y + 1][z], this,  voxelData[x + 1][y][z], voxelData, worldData, 0, 0, 1, false);
        }
        if(voxelData[x + 1][y][z] != null && !voxelData[x + 1][y][z].isEmpty() && voxelData[x + 1][y][z + 1] != null && !voxelData[x + 1][y][z + 1].isEmpty() && voxelData[x][y][z + 1] != null && !voxelData[x][y][z + 1].isEmpty())// && (worldData[x + 1][y + 1][z + 1] != worldData[x + 1][y - 1][z + 1] || (worldData[x + 1][y + 0][z + 1] != worldData[x + 1][y + 1][z + 1] && worldData[x + 1][y + 0][z + 1] != worldData[x + 1][y - 1][z + 1])))
        {
            addTriangle(this, voxelData[x + 1][y][z + 1], voxelData[x + 1][y][z], voxelData[x][y][z + 1], voxelData, worldData, 0, 1, 0, true);
            addTriangle(voxelData[x][y][z + 1], voxelData[x + 1][y][z + 1], this,  voxelData[x + 1][y][z], voxelData, worldData, 0, 1, 0, false);
        }
        if(voxelData[x][y][z + 1] != null && !voxelData[x][y][z + 1].isEmpty() && voxelData[x][y + 1][z + 1] != null && !voxelData[x][y + 1][z + 1].isEmpty() && voxelData[x][y + 1][z] != null && !voxelData[x][y + 1][z].isEmpty())// && (worldData[x + 1][y + 1][z + 1] != worldData[x - 1][y + 1][z + 1] || (worldData[x + 0][y + 1][z + 1] != worldData[x + 1][y + 1][z + 1] && worldData[x + 0][y + 1][z + 1] != worldData[x - 1][y + 1][z + 1])))
        {
            addTriangle(this, voxelData[x][y + 1][z + 1], voxelData[x][y][z + 1], voxelData[x][y + 1][z],  voxelData, worldData, 1, 0, 0, true);
            addTriangle(voxelData[x][y + 1][z + 1], this, voxelData[x][y + 1][z], voxelData[x][y][z + 1], voxelData, worldData, 1, 0, 0, false);
        }


    }
    public static boolean isValid(VoxelNode[][][] data, VoxelNode v, int x, int y, int z)
    {
        int xV = v.getX();
        int yV = v.getY();
        int zV = v.getZ();


        if(xV - 1 >= 0 && yV - 1 >= 0 && zV - 1 >= 0)
        {
            boolean a = data[xV + x][yV + y][zV + z] == null;
            boolean b = data[xV - x][yV - y][zV - z] == null;
            boolean c = data[xV + x][yV + y][zV + z] instanceof VoxelNodeSolid;
            boolean d = data[xV - x][yV - y][zV - z] instanceof VoxelNodeSolid;
            boolean e = data[xV + x][yV + y][zV + z] instanceof VoxelNode;
            boolean f = data[xV - x][yV - y][zV - z] instanceof VoxelNode;
            if(a || b)//data[xV + 1][yV][zV] == null || data[xV - 1][yV][zV] == null || data[xV][yV + 1][zV] == null || data[xV][yV - 1][zV] == null || data[xV][yV][zV + 1] == null || data[xV][yV][zV - 1] == null)
            {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidPos(VoxelNode[][][] data, VoxelNode v, int x, int y, int z)
    {
        int xV = v.getX();
        int yV = v.getY();
        int zV = v.getZ();


        if(xV - 1 >= 0 && yV - 1 >= 0 && zV - 1 >= 0)
        {
            boolean a = data[xV + x][yV + y][zV + z] == null;
            if(a)
            {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidNeg(VoxelNode[][][] data, VoxelNode v, int x, int y, int z)
    {
        int xV = v.getX();
        int yV = v.getY();
        int zV = v.getZ();


        if(xV - 1 >= 0 && yV - 1 >= 0 && zV - 1 >= 0)
        {
            boolean a = data[xV - x][yV - y][zV - z] == null;
            if(a)
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Vector3f> getAllTriangles()
    {
        return triangles;
    }
    public ArrayList<Vector3f> getAllColours()
    {
        return colours;
    }
    public ArrayList<Vector3f> getAllNormals()
    {
        return normals;
    }
    public ArrayList<Vector2f> getTextureCoords()
    {
        return textureCoords;
    }
    public ArrayList<Vector3f> getCollision()
    {
        return collision;
    }
    private void addTriangle(VoxelNode a, VoxelNode b, VoxelNode c, VoxelNode d, VoxelNode[][][] data, int[][][] worldData, int x, int y, int z, boolean col)
    {
        //if((isValid(data, a, x, y, z) || isValid(data, b, x, y, z) || isValid(data, c, x, y, z) || isValid(data, d, x, y, z)))
        //        (isValidNeg(data, a, x, y, z) && isValidNeg(data, b, x, y, z) && isValidNeg(data, c, x, y, z) && isValidNeg(data, d, x, y, z)))
        {
            triangles.add(a.getNode(x, y, z));
            triangles.add(b.getNode(x, y, z));
            triangles.add(c.getNode(x, y, z));
            colours.add(a.getMaterial());
            colours.add(b.getMaterial());
            colours.add(c.getMaterial());
            //colours.add(new Vector3f(a.getX() % 2, a.getY() % 2, a.getZ() % 2));
            //colours.add(new Vector3f(b.getX() % 2, b.getY() % 2, b.getZ() % 2));
            //colours.add(new Vector3f(c.getX() % 2, c.getY() % 2, c.getZ() % 2));

            normals.add(a.getNormalizedNormal());
            normals.add(b.getNormalizedNormal());
            normals.add(c.getNormalizedNormal());
            //float dif = Maths.distanceBetween(a.getNode(), b.getNode());

            //float dif2 = Maths.distanceBetween(new Vector3f(x1, y1, z1), new Vector3f(x2, y2, z2));

            Vector2f aV = new Vector2f(a.getX(), a.getZ());
            Vector2f bV = new Vector2f(b.getX() - (a.getY() - b.getY()), b.getZ());
            Vector2f cV = new Vector2f(c.getX(), c.getZ() - (a.getY() - c.getY()));
            textureCoords.add(aV);
            textureCoords.add(bV);
            textureCoords.add(cV);
        }
        if(col)
        {
            collision.add(a.getNode(x, y, z));
            collision.add(b.getNode(x, y, z));
            collision.add(c.getNode(x, y, z));
            collision.add(d.getNode(x, y, z));
        }
        /*else if(false)
        {
            triangles.add(a.getNode(x, y, z));
            triangles.add(b.getNode(x, y, z));
            triangles.add(c.getNode(x, y, z));
            //colours.add(a.getMaterial());
            //colours.add(b.getMaterial());
            //colours.add(c.getMaterial());
            colours.add(new Vector3f(1, 1, 1));
            colours.add(new Vector3f(1, 1, 1));
            colours.add(new Vector3f(1, 1, 1));

            normals.add(a.getNormalizedNormal());
            normals.add(b.getNormalizedNormal());
            normals.add(c.getNormalizedNormal());
        }*/
    }
    public void addTriangle(VoxelNode a, VoxelNode b, VoxelNode c, int e)
    {
        triangles.add(a.getNode(x, y, z));
        triangles.add(b.getNode(x, y, z));
        triangles.add(c.getNode(x, y, z));
        colours.add(a.getMaterial());
        colours.add(b.getMaterial());
        colours.add(c.getMaterial());

        normals.add(a.getNormalizedNormal());
        normals.add(b.getNormalizedNormal());
        normals.add(c.getNormalizedNormal());
    }
    public Vector3f getNode(int x, int y, int z)
    {
        return new Vector3f(xN, yN, zN);
    }
    public Vector3f getNode()
    {
        return new Vector3f(xN, yN, zN);
    }
    public Vector3f getMaterial()
    {
        return material;
    }
    public Vector3f getNormal()
    {
        return emptyVector;
    }

    public boolean isSolid()
    {
        return true;
    }
    public boolean isEmpty()
    {
        return false;
    }

    public Vector3f getNormalizedNormal()
    {
        Vector3f normal = new Vector3f(0, 0, 0);

        for(int i = 0; i < unprocessedNormals.size(); i++)
        {
            normal = Vec.vecAdd(normal, unprocessedNormals.get(i));
        }

        normal = Maths.normalize(normal);
        return normal;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getZ()
    {
        return z;
    }

    public void setNode(float x, float y, float z)
    {
        this.xN = x;
        this.yN = y;
        this.zN = z;
    }
    public void setXYZ(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
