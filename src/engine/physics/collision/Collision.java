package engine.physics.collision;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import tools.Maths;
import tools.matrix.Matrix;
import tools.vector.Vec;

import java.util.ArrayList;

/**
 * Created by Owner on 5/20/2017.
 */
public class Collision
{

    public static boolean triangleToTriangle(Vector3f[] tri0, Vector3f tri2, Vector3f axis)
    {
        return true;
    }

    public static Vector3f[] generateOBBMesh(Matrix4f matrix)
    {
        Vector3f[] vertices = new Vector3f[8];

        //
        // +--------+       2 3
        // |\       |\
        // | \      | \
        // |  \     |  \
        // +---\----+   \   0 1
        //  \   +--------+  6 7     x +
        //   \  |     \  |  +
        //    \ |      \ |  y
        //     \|       \|
        //      +--------+  4 5
        //
        //             z
        //             +

        float aWidth = 1;//width / 2;
        float aHeight = 1;//height / 2;
        float aDepth = 1;//depth / 2;
        /*
            Creates 3D mesh around origin, ready for matrix transformation
         */
        vertices[0] = new Vector3f(-aWidth, -aHeight, -aDepth);
        vertices[1] = new Vector3f(aWidth, -aHeight, -aDepth);
        vertices[2] = new Vector3f(-aWidth, aHeight, -aDepth);
        vertices[3] = new Vector3f(aWidth, aHeight, -aDepth);
        vertices[4] = new Vector3f(-aWidth, -aHeight, aDepth);
        vertices[5] = new Vector3f(aWidth, -aHeight, aDepth);
        vertices[6] = new Vector3f(-aWidth, aHeight, aDepth);
        vertices[7] = new Vector3f(aWidth, aHeight, aDepth);

        for(int i = 0; i < 8; i++)
        {
            Vector3f vec3 = vertices[i];
            Vector4f vec = new Vector4f(vec3.x, vec3.y, vec3.z, 1);
            Vector4f transformed = matrix.transform(matrix, vec, vec);
            vertices[i] = new Vector3f(transformed.x, transformed.y, transformed.z);
        }
        //System.out.println(Arrays.toString(vertices));

        return vertices;
    }
    public static CollisionResponse OBBtoOBB(float xRot, float yRot, float zRot, Matrix4f matrix, float xRot2, float yRot2, float zRot2, Matrix4f matrix2)
    {
        Vector3f[] obb0 = generateOBBMesh(matrix);
        Vector3f[] obb1 = generateOBBMesh(matrix2);
        Vector3f[] axes = new Vector3f[6];

        /*
            0 = X
            1 = Y
            2 = Z
         */
        axes[0] = Maths.normalize(Vec.triangleToNormal(obb0[1], obb0[5], obb0[7]));
        axes[1] = Maths.normalize(Vec.triangleToNormal(obb0[3], obb0[6], obb0[7]));
        axes[2] = Maths.normalize(Vec.triangleToNormal(obb0[4], obb0[5], obb0[6]));

        axes[3] = Maths.normalize(Vec.triangleToNormal((obb1[1]), obb1[5], obb1[7]));
        axes[4] = Maths.normalize(Vec.triangleToNormal(obb1[3], obb1[6], obb1[7]));
        axes[5] = Maths.normalize(Vec.triangleToNormal(obb1[4], obb1[5], obb1[6]));

        Matrix4f rotA = Matrix.rotate(xRot, yRot, zRot);
        Matrix4f rotB = Matrix.rotate(xRot2, yRot2, zRot2);
        axes[0] = (Vec.downscale(matrix.transform(rotA, new Vector4f(1, 0, 0, 1), null)));
        axes[1] = (Vec.downscale(matrix.transform(rotA, new Vector4f(0, 1, 0, 1), null)));
        axes[2] = (Vec.downscale(matrix.transform(rotA, new Vector4f(0, 0, 1, 1), null)));

        axes[3] = (Vec.downscale(matrix.transform(rotB, new Vector4f(1, 0, 0, 1), null)));
        axes[4] = (Vec.downscale(matrix.transform(rotB, new Vector4f(0, 1, 0, 1), null)));
        axes[5] = (Vec.downscale(matrix.transform(rotB, new Vector4f(0, 0, 1, 1), null)));

        //System.out.println(Arrays.toString(axes));

        //System.out.println(axes[3] + " " + axes[4] + " " + axes[5] + " " + Maths.normalize(Vec.triangleToNormal(obb1[3], obb1[6], obb1[7])) + " " + obb1[3] + " " + obb1[6] + " " + obb1[7]);
        /*axes[6] = Vector3f.cross(axes[0], axes[3], axes[6]);
        axes[7] = Vector3f.cross(axes[0], axes[4], axes[7]);
        axes[8] = Vector3f.cross(axes[0], axes[5], axes[8]);

        axes[9] = Vector3f.cross(axes[1], axes[3], axes[9]);
        axes[10] = Vector3f.cross(axes[1], axes[4], axes[10]);
        axes[11] = Vector3f.cross(axes[1], axes[5], axes[11]);

        axes[12] = Vector3f.cross(axes[2], axes[3], axes[12]);
        axes[13] = Vector3f.cross(axes[2], axes[4], axes[13]);
        axes[14] = Vector3f.cross(axes[2], axes[5], axes[14]);*/
        ArrayList<CollisionData> responses = new ArrayList<>();
        for(int i = 0; i < axes.length; i++)
        {
            CollisionData data = projectedCollision(obb0, obb1, axes[i]);
            boolean collided = data.doesCollide();
            if(!collided)
            {
                return null;
            }
            responses.add(data);
        }
    //System.out.println(true);
        float xDis = 0;
        float yDis = 0;
        float zDis = 0;
        CollisionData smallestOverlap = null;
        Vector3f average = new Vector3f(0, 0, 0);
        for(CollisionData data : responses)
        {
            if(smallestOverlap == null || data.getOverlapPositive() < smallestOverlap.getOverlapPositive())
            {
                smallestOverlap = data;
            }
            if(data.getIntersectionPoint() != null)
            {
                average = Vec.average(average, data.getIntersectionPoint());
            }
        }
        xDis = (float) (smallestOverlap.getAxis().getX()) * (smallestOverlap.getTotalSpan() - smallestOverlap.getSumSpan());
        yDis = (float) (smallestOverlap.getAxis().getY()) * (smallestOverlap.getTotalSpan() - smallestOverlap.getSumSpan());
        zDis = (float) (smallestOverlap.getAxis().getZ()) * (smallestOverlap.getTotalSpan() - smallestOverlap.getSumSpan());
        //System.out.println(smallestOverlap.getAxis() + " " + smallestOverlap.getTotalSpan() + " " + smallestOverlap.getSumSpan());
        Vector3f mtv = new Vector3f(xDis, yDis, zDis);
        if(smallestOverlap.doesInvert())
        {
            mtv = Maths.invert(mtv);
        }
        if(Float.isNaN(mtv.getX()) || Float.isNaN(mtv.getY()) || Float.isNaN(mtv.getZ()))
        {
            return null;
        }
        return new CollisionResponse(mtv, average);
    }
    public static CollisionResponse OBBtoQuad(float xRot, float yRot, float zRot, Matrix4f matrix, Vector3f... quad)
    {
        Vector3f[] axes = new Vector3f[8];
        Vector3f[] obb = generateOBBMesh(matrix);
        Matrix4f rotA = Matrix.rotate(xRot, yRot, zRot);
        axes[0] = (Vec.downscale(matrix.transform(rotA, new Vector4f(1, 0, 0, 1), null)));
        axes[1] = (Vec.downscale(matrix.transform(rotA, new Vector4f(0, 1, 0, 1), null)));
        axes[2] = (Vec.downscale(matrix.transform(rotA, new Vector4f(0, 0, 1, 1), null)));

        axes[3] = Maths.normalize(Vec.triangleToNormal(quad[0], quad[1], quad[2])); /* Up normal */
        axes[4] = Maths.normalize(new Vector3f(Vec.subtract(quad[0], quad[1])));
        axes[5] = Maths.normalize(new Vector3f(Vec.subtract(quad[1], quad[2])));
        axes[6] = Maths.normalize(new Vector3f(Vec.subtract(quad[2], quad[3])));
        axes[7] = Maths.normalize(new Vector3f(Vec.subtract(quad[3], quad[0])));

        CollisionData overlap = null;
        for(int i = 0; i < axes.length; i++)
        {
            CollisionData data = projectedCollision(obb, quad, axes[i]);
            boolean collided = data.doesCollideEqual();
            if(!collided)
            {
                return null;
            }
            if(i == 3)
            {
                overlap = data;
            }
        }
        //overlap.setOff(1);
        float xDis = (float) (overlap.getAxis().getX()) * (overlap.getOff());
        float yDis = (float) (overlap.getAxis().getY()) * (overlap.getOff());
        float zDis = (float) (overlap.getAxis().getZ()) * (overlap.getOff());
        if(overlap.getOff() != 0)
        {
           // System.out.println(BigDecimal.valueOf(overlap.getOff()).toPlainString());
        }
        /*if(axes[3].getX() > 0)
        {
            xDis = -xDis;
        }
        if(axes[3].getY() < 0)
        {
            yDis = -yDis;
        }
        if(axes[3].getZ() < 0)
        {
            zDis = -zDis;
        }*/
        Vector3f mtv = new Vector3f(xDis, yDis, zDis);
        if(overlap.doesInvert())
        {
            mtv = Maths.invert(mtv);
        }
        if(Float.isNaN(mtv.getX()) || Float.isNaN(mtv.getY()) || Float.isNaN(mtv.getZ()))
        {
            return null;
        }
        if(mtv.length() > 1.3f)
        {
            System.out.println("ohno" + " " + mtv + " " + overlap.getOff());
        }

        return new CollisionResponse(mtv, new Vector3f(0, 0, 0));
    }
    public static boolean planeToPlane(Vector3f[] plane0, Vector3f[] plane1)
    {
        Vector3f[] axes = new Vector3f[15];
        axes[0] = Maths.normalize(Vec.triangleToNormal(plane0[0], plane0[1], plane0[2]));
        axes[1] = Maths.normalize(Vec.zxy(Vec.triangleToNormal(plane0[0], plane0[1], plane0[2])));
        axes[2] = Maths.normalize(Vec.yzx(Vec.triangleToNormal(plane0[0], plane0[1], plane0[2])));

        axes[3] = Maths.normalize(Vec.triangleToNormal(plane1[0], plane1[1], plane1[2]));
        axes[4] = Maths.normalize(Vec.zxy(Vec.triangleToNormal(plane1[0], plane1[1], plane1[2])));
        axes[5] = Maths.normalize(Vec.yzx(Vec.triangleToNormal(plane1[0], plane1[1], plane1[2])));
        axes[6] = Vector3f.cross(axes[0], axes[3], axes[6]);
        axes[7] = Vector3f.cross(axes[0], axes[4], axes[7]);
        axes[8] = Vector3f.cross(axes[0], axes[5], axes[8]);

        axes[9] = Vector3f.cross(axes[1], axes[3], axes[9]);
        axes[10] = Vector3f.cross(axes[1], axes[4], axes[10]);
        axes[11] = Vector3f.cross(axes[1], axes[5], axes[11]);

        axes[12] = Vector3f.cross(axes[2], axes[3], axes[12]);
        axes[13] = Vector3f.cross(axes[2], axes[4], axes[13]);
        axes[14] = Vector3f.cross(axes[2], axes[5], axes[14]);
        ArrayList<CollisionData> response = new ArrayList<>();
        for(int i = 0; i < 15; i++)
        {
            CollisionData data = projectedCollision(plane0, plane1, axes[i]);
            boolean collided = data.doesCollide();
            if(!collided)
            {
                return false;
            }
        }
        return true;
    }

    private static CollisionData projectedCollision(Vector3f[] mesh0, Vector3f[] mesh1, Vector3f axis)
    {
        float aMin = Float.MAX_VALUE;
        float aMax = -Float.MAX_VALUE;
        float bMin = Float.MAX_VALUE;
        float bMax = -Float.MAX_VALUE;

        Vector3f average = new Vector3f(0, 0, 0);
        Vector3f[] averages = new Vector3f[4];

        for(int i = 0; i < mesh0.length; i++)
        {
            float dotA = Vector3f.dot(mesh0[i], axis);
            if(dotA < aMin)
            {
                aMin = dotA;
                averages[0] = mesh0[i];
            }
            if(dotA > aMax)
            {
                aMax = dotA;
                averages[1] = mesh0[i];
            }
            //ystem.out.println(aMax + " " + dotA + " " + (int) aMax + " " + (dotA > aMax) + " " + axis);

            if(i < mesh1.length)
            {
                float dotB = Vector3f.dot(mesh1[i], axis);
                if(dotB < bMin)
                {
                    bMin = dotB;
                    averages[2] = mesh1[i];
                }
                if(dotB > bMax)
                {
                    bMax = dotB;
                    averages[3] = mesh1[i];
                }
            }
        }
        average = Vec.average(averages);
        float totalSpan = Math.max(aMax, bMax) - Math.min(aMin, bMin);
        float sumSpan = aMax - aMin + bMax - bMin;
        float off = bMax - aMin;
        float smallestA = (Math.abs(aMin - bMin));
        float smallestB = Math.abs(aMax - bMax);
        float smallest = Math.min(smallestA, smallestB);
        if(smallestA > smallestB)
        {
            smallest = -smallest;
        }
        if(aMin > bMax)
        {
           //off = -off;
        }
        CollisionData collisionData = new CollisionData(totalSpan, sumSpan, smallest, axis);
        if(aMin < bMin)
        {
            collisionData.invert();
        }
        if(collisionData.doesCollide())
        {
            collisionData.setIntersectionPoint(average);
        }
        return collisionData;
    }

    public static boolean broadPhaseAABB(AABB a, AABB b)
    {
        if(a.getX() + (a.getXScale() / 2) > b.getX() - (b.getXScale() / 2) && a.getX() - (a.getXScale() / 2) < b.getX() + (b.getXScale() / 2) &&
                a.getY() + (a.getYScale() / 2) > b.getY() - (b.getYScale() / 2) && a.getY() - (a.getYScale() / 2) < b.getY() + (b.getYScale() / 2) &&
                a.getZ() + (a.getZScale() / 2) > b.getZ() - (b.getZScale() / 2) && a.getZ() - (a.getZScale() / 2) < b.getZ() + (b.getZScale() / 2))
        {
            return true;
        }
        return false;
    }

}
