package engine.graphics.postprocess.depth;

import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/17/2017.
 */
public class DepthShader extends ShaderProgram
{
    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/depth/depthVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/depth/depthFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_zFar;

    public DepthShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    public void loadTransformation(Matrix4f matrix)
    {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    @Override
    protected void getAllUniformLocations()
    {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_zFar = super.getUniformLocation("zFar");
    }
    public void loadFarPlane(float farPlane)
    {
        super.loadFloat(location_zFar, farPlane);
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }
}
