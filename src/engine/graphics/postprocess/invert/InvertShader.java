package engine.graphics.postprocess.invert;

import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/14/2017.
 */
public class InvertShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/invert/invertVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/invert/invertFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_transparency;

    public InvertShader()
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
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }

}