/*import org.lwjgl.util.vector.Vector2f;
import tools.vector.Vec3D;
import tools.vector.Vec4D;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/*package tools.matrix;

import org.lwjgl.util.vector.Vector2f;
import tools.vector.Vec3D;
import tools.vector.Vec4D;

import java.io.Serializable;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Created by Owner on 6/15/2017.
 */
/*
public class Matrix4d extends org.lwjgl.util.vector.Matrix
{
    private static final long serialVersionUID = 1L;
    public double m00;
    public double m01;
    public double m02;
    public double m03;
    public double m10;
    public double m11;
    public double m12;
    public double m13;
    public double m20;
    public double m21;
    public double m22;
    public double m23;
    public double m30;
    public double m31;
    public double m32;
    public double m33;

    public Matrix4d()
    {
        this.setIdentity();
    }

    public Matrix4d(Matrix4d src)
    {
        this.load(src);
    }

    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        buf.append(this.m00).append(' ').append(this.m10).append(' ').append(this.m20).append(' ').append(this.m30).append('\n');
        buf.append(this.m01).append(' ').append(this.m11).append(' ').append(this.m21).append(' ').append(this.m31).append('\n');
        buf.append(this.m02).append(' ').append(this.m12).append(' ').append(this.m22).append(' ').append(this.m32).append('\n');
        buf.append(this.m03).append(' ').append(this.m13).append(' ').append(this.m23).append(' ').append(this.m33).append('\n');
        return buf.toString();
    }

    public org.lwjgl.util.vector.Matrix setIdentity()
    {
        return setIdentity(this);
    }

    public static Matrix4d setIdentity(Matrix4d m)
    {
        m.m00 = 1.0F;
        m.m01 = 0.0F;
        m.m02 = 0.0F;
        m.m03 = 0.0F;
        m.m10 = 0.0F;
        m.m11 = 1.0F;
        m.m12 = 0.0F;
        m.m13 = 0.0F;
        m.m20 = 0.0F;
        m.m21 = 0.0F;
        m.m22 = 1.0F;
        m.m23 = 0.0F;
        m.m30 = 0.0F;
        m.m31 = 0.0F;
        m.m32 = 0.0F;
        m.m33 = 1.0F;
        return m;
    }

    public org.lwjgl.util.vector.Matrix setZero()
    {
        return setZero(this);
    }

    public static Matrix4d setZero(Matrix4d m)
    {
        m.m00 = 0.0F;
        m.m01 = 0.0F;
        m.m02 = 0.0F;
        m.m03 = 0.0F;
        m.m10 = 0.0F;
        m.m11 = 0.0F;
        m.m12 = 0.0F;
        m.m13 = 0.0F;
        m.m20 = 0.0F;
        m.m21 = 0.0F;
        m.m22 = 0.0F;
        m.m23 = 0.0F;
        m.m30 = 0.0F;
        m.m31 = 0.0F;
        m.m32 = 0.0F;
        m.m33 = 0.0F;
        return m;
    }

    public Matrix4d load(Matrix4d src)
    {
        return load(src, this);
    }

    public static Matrix4d load(Matrix4d src, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        dest.m00 = src.m00;
        dest.m01 = src.m01;
        dest.m02 = src.m02;
        dest.m03 = src.m03;
        dest.m10 = src.m10;
        dest.m11 = src.m11;
        dest.m12 = src.m12;
        dest.m13 = src.m13;
        dest.m20 = src.m20;
        dest.m21 = src.m21;
        dest.m22 = src.m22;
        dest.m23 = src.m23;
        dest.m30 = src.m30;
        dest.m31 = src.m31;
        dest.m32 = src.m32;
        dest.m33 = src.m33;
        return dest;
    }

    public org.lwjgl.util.vector.Matrix load(DoubleBuffer buf)
    {
        this.m00 = buf.get();
        this.m01 = buf.get();
        this.m02 = buf.get();
        this.m03 = buf.get();
        this.m10 = buf.get();
        this.m11 = buf.get();
        this.m12 = buf.get();
        this.m13 = buf.get();
        this.m20 = buf.get();
        this.m21 = buf.get();
        this.m22 = buf.get();
        this.m23 = buf.get();
        this.m30 = buf.get();
        this.m31 = buf.get();
        this.m32 = buf.get();
        this.m33 = buf.get();
        return this;
    }

    public org.lwjgl.util.vector.Matrix loadTranspose(DoubleBuffer buf)
    {
        this.m00 = buf.get();
        this.m10 = buf.get();
        this.m20 = buf.get();
        this.m30 = buf.get();
        this.m01 = buf.get();
        this.m11 = buf.get();
        this.m21 = buf.get();
        this.m31 = buf.get();
        this.m02 = buf.get();
        this.m12 = buf.get();
        this.m22 = buf.get();
        this.m32 = buf.get();
        this.m03 = buf.get();
        this.m13 = buf.get();
        this.m23 = buf.get();
        this.m33 = buf.get();
        return this;
    }

    public org.lwjgl.util.vector.Matrix store(FloatBuffer buf)
    {
        buf.put((float) this.m00);
        buf.put((float)this.m01);
        buf.put((float)this.m02);
        buf.put((float)this.m03);
        buf.put((float)this.m10);
        buf.put((float)this.m11);
        buf.put((float)this.m12);
        buf.put((float)this.m13);
        buf.put((float)this.m20);
        buf.put((float)this.m21);
        buf.put((float)this.m22);
        buf.put((float)this.m23);
        buf.put((float)this.m30);
        buf.put((float)this.m31);
        buf.put((float)this.m32);
        buf.put((float)this.m33);
        return this;
    }

    public org.lwjgl.util.vector.Matrix storeTranspose(FloatBuffer buf)
    {
        buf.put(this.m00);
        buf.put(this.m10);
        buf.put(this.m20);
        buf.put(this.m30);
        buf.put(this.m01);
        buf.put(this.m11);
        buf.put(this.m21);
        buf.put(this.m31);
        buf.put(this.m02);
        buf.put(this.m12);
        buf.put(this.m22);
        buf.put(this.m32);
        buf.put(this.m03);
        buf.put(this.m13);
        buf.put(this.m23);
        buf.put(this.m33);
        return this;
    }

    public org.lwjgl.util.vector.Matrix store3f(DoubleBuffer buf)
    {
        buf.put(this.m00);
        buf.put(this.m01);
        buf.put(this.m02);
        buf.put(this.m10);
        buf.put(this.m11);
        buf.put(this.m12);
        buf.put(this.m20);
        buf.put(this.m21);
        buf.put(this.m22);
        return this;
    }

    public static Matrix4d add(Matrix4d left, Matrix4d right, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        dest.m00 = left.m00 + right.m00;
        dest.m01 = left.m01 + right.m01;
        dest.m02 = left.m02 + right.m02;
        dest.m03 = left.m03 + right.m03;
        dest.m10 = left.m10 + right.m10;
        dest.m11 = left.m11 + right.m11;
        dest.m12 = left.m12 + right.m12;
        dest.m13 = left.m13 + right.m13;
        dest.m20 = left.m20 + right.m20;
        dest.m21 = left.m21 + right.m21;
        dest.m22 = left.m22 + right.m22;
        dest.m23 = left.m23 + right.m23;
        dest.m30 = left.m30 + right.m30;
        dest.m31 = left.m31 + right.m31;
        dest.m32 = left.m32 + right.m32;
        dest.m33 = left.m33 + right.m33;
        return dest;
    }

    public static Matrix4d sub(Matrix4d left, Matrix4d right, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        dest.m00 = left.m00 - right.m00;
        dest.m01 = left.m01 - right.m01;
        dest.m02 = left.m02 - right.m02;
        dest.m03 = left.m03 - right.m03;
        dest.m10 = left.m10 - right.m10;
        dest.m11 = left.m11 - right.m11;
        dest.m12 = left.m12 - right.m12;
        dest.m13 = left.m13 - right.m13;
        dest.m20 = left.m20 - right.m20;
        dest.m21 = left.m21 - right.m21;
        dest.m22 = left.m22 - right.m22;
        dest.m23 = left.m23 - right.m23;
        dest.m30 = left.m30 - right.m30;
        dest.m31 = left.m31 - right.m31;
        dest.m32 = left.m32 - right.m32;
        dest.m33 = left.m33 - right.m33;
        return dest;
    }

    public static Matrix4d mul(Matrix4d left, Matrix4d right, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        double m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02 + left.m30 * right.m03;
        double m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02 + left.m31 * right.m03;
        double m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02 + left.m32 * right.m03;
        double m03 = left.m03 * right.m00 + left.m13 * right.m01 + left.m23 * right.m02 + left.m33 * right.m03;
        double m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12 + left.m30 * right.m13;
        double m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12 + left.m31 * right.m13;
        double m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12 + left.m32 * right.m13;
        double m13 = left.m03 * right.m10 + left.m13 * right.m11 + left.m23 * right.m12 + left.m33 * right.m13;
        double m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22 + left.m30 * right.m23;
        double m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22 + left.m31 * right.m23;
        double m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22 + left.m32 * right.m23;
        double m23 = left.m03 * right.m20 + left.m13 * right.m21 + left.m23 * right.m22 + left.m33 * right.m23;
        double m30 = left.m00 * right.m30 + left.m10 * right.m31 + left.m20 * right.m32 + left.m30 * right.m33;
        double m31 = left.m01 * right.m30 + left.m11 * right.m31 + left.m21 * right.m32 + left.m31 * right.m33;
        double m32 = left.m02 * right.m30 + left.m12 * right.m31 + left.m22 * right.m32 + left.m32 * right.m33;
        double m33 = left.m03 * right.m30 + left.m13 * right.m31 + left.m23 * right.m32 + left.m33 * right.m33;
        dest.m00 = m00;
        dest.m01 = m01;
        dest.m02 = m02;
        dest.m03 = m03;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m12 = m12;
        dest.m13 = m13;
        dest.m20 = m20;
        dest.m21 = m21;
        dest.m22 = m22;
        dest.m23 = m23;
        dest.m30 = m30;
        dest.m31 = m31;
        dest.m32 = m32;
        dest.m33 = m33;
        return dest;
    }

    public static Vec4D transform(Matrix4d left, Vec4D right, Vec4D dest)
    {
        if (dest == null)
        {
            dest = new Vec4D();
        }

        double x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z + left.m30 * right.w;
        double y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z + left.m31 * right.w;
        double z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z + left.m32 * right.w;
        double w = left.m03 * right.x + left.m13 * right.y + left.m23 * right.z + left.m33 * right.w;
        dest.x = x;
        dest.y = y;
        dest.z = z;
        dest.w = w;
        return dest;
    }

    public org.lwjgl.util.vector.Matrix transpose()
    {
        return this.transpose(this);
    }

    public Matrix4d translate(Vector2f vec)
    {
        return this.translate(vec, this);
    }

    public Matrix4d translate(Vec3D vec)
    {
        return this.translate(vec, this);
    }

    public Matrix4d scale(Vec3D vec)
    {
        return scale(vec, this, this);
    }

    public static Matrix4d scale(Vec3D vec, Matrix4d src, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        dest.m00 = src.m00 * vec.x;
        dest.m01 = src.m01 * vec.x;
        dest.m02 = src.m02 * vec.x;
        dest.m03 = src.m03 * vec.x;
        dest.m10 = src.m10 * vec.y;
        dest.m11 = src.m11 * vec.y;
        dest.m12 = src.m12 * vec.y;
        dest.m13 = src.m13 * vec.y;
        dest.m20 = src.m20 * vec.z;
        dest.m21 = src.m21 * vec.z;
        dest.m22 = src.m22 * vec.z;
        dest.m23 = src.m23 * vec.z;
        return dest;
    }

    public Matrix4d rotate(double angle, Vec3D axis)
    {
        return this.rotate(angle, axis, this);
    }

    public Matrix4d rotate(double angle, Vec3D axis, Matrix4d dest)
    {
        return rotate(angle, axis, this, dest);
    }

    public static Matrix4d rotate(double angle, Vec3D axis, Matrix4d src, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        double c = (double) Math.cos((double) angle);
        double s = (double) Math.sin((double) angle);
        double oneminusc = 1.0F - c;
        double xy = axis.x * axis.y;
        double yz = axis.y * axis.z;
        double xz = axis.x * axis.z;
        double xs = axis.x * s;
        double ys = axis.y * s;
        double zs = axis.z * s;
        double f00 = axis.x * axis.x * oneminusc + c;
        double f01 = xy * oneminusc + zs;
        double f02 = xz * oneminusc - ys;
        double f10 = xy * oneminusc - zs;
        double f11 = axis.y * axis.y * oneminusc + c;
        double f12 = yz * oneminusc + xs;
        double f20 = xz * oneminusc + ys;
        double f21 = yz * oneminusc - xs;
        double f22 = axis.z * axis.z * oneminusc + c;
        double t00 = src.m00 * f00 + src.m10 * f01 + src.m20 * f02;
        double t01 = src.m01 * f00 + src.m11 * f01 + src.m21 * f02;
        double t02 = src.m02 * f00 + src.m12 * f01 + src.m22 * f02;
        double t03 = src.m03 * f00 + src.m13 * f01 + src.m23 * f02;
        double t10 = src.m00 * f10 + src.m10 * f11 + src.m20 * f12;
        double t11 = src.m01 * f10 + src.m11 * f11 + src.m21 * f12;
        double t12 = src.m02 * f10 + src.m12 * f11 + src.m22 * f12;
        double t13 = src.m03 * f10 + src.m13 * f11 + src.m23 * f12;
        dest.m20 = src.m00 * f20 + src.m10 * f21 + src.m20 * f22;
        dest.m21 = src.m01 * f20 + src.m11 * f21 + src.m21 * f22;
        dest.m22 = src.m02 * f20 + src.m12 * f21 + src.m22 * f22;
        dest.m23 = src.m03 * f20 + src.m13 * f21 + src.m23 * f22;
        dest.m00 = t00;
        dest.m01 = t01;
        dest.m02 = t02;
        dest.m03 = t03;
        dest.m10 = t10;
        dest.m11 = t11;
        dest.m12 = t12;
        dest.m13 = t13;
        return dest;
    }

    public Matrix4d translate(Vec3D vec, Matrix4d dest)
    {
        return translate(vec, this, dest);
    }

    public static Matrix4d translate(Vec3D vec, Matrix4d src, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        dest.m30 += src.m00 * vec.x + src.m10 * vec.y + src.m20 * vec.z;
        dest.m31 += src.m01 * vec.x + src.m11 * vec.y + src.m21 * vec.z;
        dest.m32 += src.m02 * vec.x + src.m12 * vec.y + src.m22 * vec.z;
        dest.m33 += src.m03 * vec.x + src.m13 * vec.y + src.m23 * vec.z;
        return dest;
    }

    public Matrix4d translate(Vector2f vec, Matrix4d dest)
    {
        return translate(vec, this, dest);
    }

    public static Matrix4d translate(Vector2f vec, Matrix4d src, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        dest.m30 += src.m00 * vec.x + src.m10 * vec.y;
        dest.m31 += src.m01 * vec.x + src.m11 * vec.y;
        dest.m32 += src.m02 * vec.x + src.m12 * vec.y;
        dest.m33 += src.m03 * vec.x + src.m13 * vec.y;
        return dest;
    }

    public Matrix4d transpose(Matrix4d dest)
    {
        return transpose(this, dest);
    }

    public static Matrix4d transpose(Matrix4d src, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        double m00 = src.m00;
        double m01 = src.m10;
        double m02 = src.m20;
        double m03 = src.m30;
        double m10 = src.m01;
        double m11 = src.m11;
        double m12 = src.m21;
        double m13 = src.m31;
        double m20 = src.m02;
        double m21 = src.m12;
        double m22 = src.m22;
        double m23 = src.m32;
        double m30 = src.m03;
        double m31 = src.m13;
        double m32 = src.m23;
        double m33 = src.m33;
        dest.m00 = m00;
        dest.m01 = m01;
        dest.m02 = m02;
        dest.m03 = m03;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m12 = m12;
        dest.m13 = m13;
        dest.m20 = m20;
        dest.m21 = m21;
        dest.m22 = m22;
        dest.m23 = m23;
        dest.m30 = m30;
        dest.m31 = m31;
        dest.m32 = m32;
        dest.m33 = m33;
        return dest;
    }

    public float determinant()
    {
        float f = (float) (this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
        f -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
        f += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
        f -= this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
        return f;
    }

    private static double determinant3x3(double t00, double t01, double t02, double t10, double t11, double t12, double t20, double t21, double t22)
    {
        return t00 * (t11 * t22 - t12 * t21) + t01 * (t12 * t20 - t10 * t22) + t02 * (t10 * t21 - t11 * t20);
    }

    public org.lwjgl.util.vector.Matrix invert()
    {
        return invert(this, this);
    }

    public static Matrix4d invert(Matrix4d src, Matrix4d dest)
    {
        double determinant = src.determinant();
        if (determinant != 0.0F)
        {
            if (dest == null)
            {
                dest = new Matrix4d();
            }

            double determinant_inv = 1.0F / determinant;
            double t00 = determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
            double t01 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
            double t02 = determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
            double t03 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
            double t10 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
            double t11 = determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
            double t12 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
            double t13 = determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
            double t20 = determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33);
            double t21 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33);
            double t22 = determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33);
            double t23 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32);
            double t30 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23);
            double t31 = determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23);
            double t32 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23);
            double t33 = determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);
            dest.m00 = t00 * determinant_inv;
            dest.m11 = t11 * determinant_inv;
            dest.m22 = t22 * determinant_inv;
            dest.m33 = t33 * determinant_inv;
            dest.m01 = t10 * determinant_inv;
            dest.m10 = t01 * determinant_inv;
            dest.m20 = t02 * determinant_inv;
            dest.m02 = t20 * determinant_inv;
            dest.m12 = t21 * determinant_inv;
            dest.m21 = t12 * determinant_inv;
            dest.m03 = t30 * determinant_inv;
            dest.m30 = t03 * determinant_inv;
            dest.m13 = t31 * determinant_inv;
            dest.m31 = t13 * determinant_inv;
            dest.m32 = t23 * determinant_inv;
            dest.m23 = t32 * determinant_inv;
            return dest;
        } else
        {
            return null;
        }
    }

    public org.lwjgl.util.vector.Matrix negate()
    {
        return this.negate(this);
    }

    public Matrix4d negate(Matrix4d dest)
    {
        return negate(this, dest);
    }

    public static Matrix4d negate(Matrix4d src, Matrix4d dest)
    {
        if (dest == null)
        {
            dest = new Matrix4d();
        }

        dest.m00 = -src.m00;
        dest.m01 = -src.m01;
        dest.m02 = -src.m02;
        dest.m03 = -src.m03;
        dest.m10 = -src.m10;
        dest.m11 = -src.m11;
        dest.m12 = -src.m12;
        dest.m13 = -src.m13;
        dest.m20 = -src.m20;
        dest.m21 = -src.m21;
        dest.m22 = -src.m22;
        dest.m23 = -src.m23;
        dest.m30 = -src.m30;
        dest.m31 = -src.m31;
        dest.m32 = -src.m32;
        dest.m33 = -src.m33;
        return dest;
    }
}*/