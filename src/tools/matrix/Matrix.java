package tools.matrix;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Owner on 6/1/2017.
 */
public class Matrix
{

    public static Matrix4f createAttached(Vector3f translationLocal, Vector3f pivotLocal, float rxLocal, float ryLocal, float rzLocal, float xScaleLocal, float yScaleLocal, float zScaleLocal,
                                  Vector3f translation, Vector3f pivot, float rx, float ry, float rz, float xScale, float yScale, float zScale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translationLocal, matrix, matrix);
        Matrix4f.translate(new Vector3f(-pivotLocal.x, -pivotLocal.y, -pivotLocal.z), matrix, matrix);
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.translate(new Vector3f(-pivot.x, -pivot.y, -pivot.z), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.translate(pivot, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rxLocal), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ryLocal), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rzLocal), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.translate(pivotLocal, matrix, matrix);
        Matrix4f.scale(new Vector3f(xScaleLocal, yScaleLocal, zScaleLocal), matrix,  matrix);

        Matrix4f.scale(new Vector3f(xScale, yScale, zScale), matrix,  matrix);
        return matrix;
    }
    public static Matrix4f rotate(float xRot, float yRot, float zRot)
    {
        Matrix4f mat = new Matrix4f();
        mat.setIdentity();

        mat.rotate((float) Math.toRadians(xRot), new Vector3f(1, 0, 0), mat, mat);
        mat.rotate((float) Math.toRadians(yRot), new Vector3f(0, 1, 0), mat, mat);
        mat.rotate((float) Math.toRadians(zRot), new Vector3f(0, 0, 1), mat, mat);

        return mat;
    }
}
