package tools;

import engine.graphics.Camera;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Owner on 2/12/2017.
 */
public class Maths
{

    public static Matrix4f createModelViewMatrix(Vector3f position, float xScale, float yScale, float zScale, float xRot, float yRot, float zRot, Matrix4f viewMatrix)
    {
        Matrix4f mat = new Matrix4f();
        Matrix4f.translate(position, mat, mat);
        mat.m00 = viewMatrix.m00;
        mat.m01 = viewMatrix.m10;
        mat.m02 = viewMatrix.m20;
        mat.m10 = viewMatrix.m01;
        mat.m11 = viewMatrix.m11;
        mat.m12 = viewMatrix.m21;
        mat.m20 = viewMatrix.m02;
        mat.m21 = viewMatrix.m12;
        mat.m22 = viewMatrix.m22;
        Matrix4f.rotate((float) Math.toRadians(zRot), new Vector3f(0, 0, 1), mat, mat);
        Matrix4f.scale(new Vector3f(xScale, yScale, zScale), mat, mat);
        Matrix4f modelView = Matrix4f.mul(viewMatrix, mat, null);
        return modelView;
    }
    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix,  matrix);
        return matrix;
    }
    public static Matrix4f createTransformationMatrixVertex(Vector3f translation, Vector3f pivot, float rx, float ry, float rz, float xScale, float yScale, float zScale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
       // Matrix4f.translate(translation, matrix, matrix);
        //Matrix4f.translate(new Vector3f(-pivot.x, -pivot.y, -pivot.z), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        //Matrix4f.translate(pivot, matrix, matrix);
        //Matrix4f.scale(new Vector3f(xScale, yScale, zScale), matrix,  matrix);
        return matrix;
    }
    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f pivot, float rx, float ry, float rz, float xScale, float yScale, float zScale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.translate(new Vector3f(-pivot.x, -pivot.y, -pivot.z), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.translate(pivot, matrix, matrix);
        Matrix4f.scale(new Vector3f(xScale, yScale, zScale), matrix,  matrix);
        return matrix;
    }
    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float xScale, float yScale, float zScale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(xScale, yScale, zScale), matrix,  matrix);
        return matrix;
    }
    public static Matrix4f createViewMatrix(Camera camera)
    {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getXRot()), new Vector3f(1,0,0), viewMatrix,
                viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYRot()), new Vector3f(0,1,0), viewMatrix,
                viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getZRot()), new Vector3f(0,0,1), viewMatrix,
                viewMatrix);
        Vector3f negativeCameraPos = new Vector3f(-camera.getX(), -camera.getY(), -camera.getZ());
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }
    public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax)
    {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }
    public static float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos)
    {
        float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
        float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
        float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
        float l3 = 1.0f - l1 - l2;
        return l1 * p1.y + l2 * p2.y + l3 * p3.y;
    }
    public static float randomFloat(float min, float max)
    {
        Random random = new Random();
        return random.nextFloat() * (max - min) + min;
    }
    public static int randomInt(int min, int max)
    {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale, Vector2f pivot, float rot)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        //Matrix4f.translate(pivot, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rot), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);

        return matrix;
    }
    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
        Matrix4f.translate(translation, matrix, matrix);

        return matrix;
    }
    public static float clamp(float val, float min, float max)
    {
        return Math.max(min, Math.min(max, val));
    }
    public static float normalize(float value, float min, float max)
    {
        float scaleFactor = max - min;
        return ((value - min) / scaleFactor);
    }
    public static Vector3f rotationToNormal(float xRot, float yRot, float zRot)
    {
        xRot = Math.abs(xRot);
        yRot = Math.abs(yRot);
        zRot = Math.abs(zRot);
        Vector3f normal = new Vector3f(0, 0, 0);
        xRot = (xRot) % 180;
        yRot = (yRot) % 180;
        zRot = (zRot) % 180;
        normal.x = 1 - (xRot / 90);
        normal.y = 1 - (yRot / 90);
        normal.z = 1 - (zRot / 90);
        return normal;
    }
    public static Vector3f calculateCentroid(Vector3f[] vecs)
    {
        Vector3f centroid = new Vector3f();
        for(int i = 0; i < vecs.length; i++)
        {
            centroid.x += vecs[i].x;
            centroid.y += vecs[i].y;
            centroid.z += vecs[i].z;
        }
        centroid.x = centroid.x / vecs.length;
        centroid.y = centroid.y / vecs.length;
        centroid.z = centroid.z / vecs.length;
        return centroid;
    }
    public static float roundToTenth(float number)
    {
        DecimalFormat rounded = new DecimalFormat("0.##");
        float roundedNumber = Float.parseFloat(rounded.format(number));
        return roundedNumber;
    }

    /*public static Vector3f[] closestFace(Vector3f centroid, Vector3f[] vecs2)
    {
        ArrayList<Vector3f> allVecs = new ArrayList<Vector3f>();

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        float aV = Float.MIN_VALUE;
        float bV = Float.MIN_VALUE;
        float cV = Float.MIN_VALUE;
        float dV = Float.MIN_VALUE;
        for(int i = 0; i < vecs2.length; i++)
        {
            allVecs.add(vecs2[i]);
            float dist = distanceBetween(new Vector3f(Math.abs(vecs2[i].x), Math.abs(vecs2[i].y), Math.abs(vecs2[i].z)),
                    new Vector3f(Math.abs(centroid.x), Math.abs(centroid.y), Math.abs(centroid.z)));
            //	float dist = distanceBetween(new Vector3f((vecs2[i].x), (vecs2[i].y), (vecs2[i].z)),
            //			new Vector3f((centroid.x), (centroid.y), (centroid.z)));
            if(dist > aV)
            {
                a = i;
                aV = dist;
            }
            else if(dist > bV)
            {
                b = i;
                bV = dist;
            }
            else if(dist > cV)
            {
                c = i;
                cV = dist;
            }
            else if(dist > dV)
            {
                d = i;
                dV = dist;
            }
        }
        pos = centroid;
        allVecs.sort(compare);
        Vector3f[] finalVecs = {allVecs.get(0), allVecs.get(1), allVecs.get(2), allVecs.get(3)};
        return finalVecs;

    }*/

    @Deprecated  public static float distBetweenVecs(Vector3f a, Vector3f b)
    {
        Vector3f dist = new Vector3f();
        dist.x = a.x - b.x;
        dist.y = a.y - b.y;
        dist.z = a.z - b.z;
        return dist.x + dist.y + dist.z;
    }
    public static float distanceBetween(Vector3f a, Vector3f b)
    {
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
    }
    /*public static float lerp(float x, float y, float scale)
    {
        /* scale is value between 0 - 1

        return x + (y - x) * scale;
    }*/
    public static float lerp(float a, float b, float c)
    {
        return a + c * (b - a);
    }
    public static boolean isEven(float number)
    {
        return number % 2 == 0;
    }
    public static float[] booleanToFloat(boolean[] bools)
    {
        float[] floats = new float[bools.length];
        for(int i = 0; i < bools.length; i++)
        {
            if(bools[i])
            {
                floats[i] = 1;
            }
            else
            {
                floats[i] = 0;
            }
        }
        return floats;

    }
    public static Vector2f Vec3ToVec2(Matrix4f viewMatrix, Matrix4f projectionMatrix, Vector3f point)
    {
        Vector4f point4 = new Vector4f(point.x, point.y, point.z, 1);
        /*point4 = Matrix4f.transform(viewMatrix, point4, null);
        point4 = Matrix4f.transform(projectionMatrix, point4, null);
        if(point4.w != 0 && point4.w > 0)
        {
            point4.x /= point4.w;
            point4.y /= point4.w;
            point4.z /= point4.w;
        }*/


        /*pos.x /= pos.z;  WHAT IS POS????????
        pos.y /= pos.z;
        pos.x = (pos.x + 1) * Display.getWidth() / 2;
        pos.y = (pos.y + 1) * Display.getHeight() / 2;*/
        point4 = Matrix4f.transform(viewMatrix, point4, point4);
        point4 = Matrix4f.transform(projectionMatrix, point4, point4);

        float x = (point4.x / point4.w + 1) / 2f;
        float y = 1 - ((point4.y / point4.w + 1) / 2);

        return new Vector2f(x, y);
    }
    public static float distBetween(float x1, float y1, float x2, float y2)
    {
        float dist = (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        return dist;
    }
    public static boolean notNegative(int number)
    {
        return number > 0;
    }

    public static float mag(float x, float y, float z)
    {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
    public static Vector3f normalize(Vector3f vector)
    {
        float mag = mag(vector.x, vector.y, vector.z);
        return new Vector3f(vector.x / mag, vector.y / mag, vector.z / mag);
    }
    public static Vector3f invert(Vector3f vector)
    {
        return new Vector3f(-vector.x, -vector.y, -vector.z);
    }
}
