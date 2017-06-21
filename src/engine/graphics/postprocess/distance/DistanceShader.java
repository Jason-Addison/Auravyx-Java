package engine.graphics.postprocess.distance;

import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/14/2017.
 */
public class DistanceShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/distance/distanceVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/distance/distanceFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_transparency;
    private int location_depthTexture;

    public DistanceShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    public void loadTransformation(Matrix4f matrix)
    {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadDepthTexture()
    {
        super.loadTexture(location_depthTexture, 1);
    }
    @Override
    protected void getAllUniformLocations()
    {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_depthTexture = super.getUniformLocation("depthTexture");
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }

}