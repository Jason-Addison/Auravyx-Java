package engine.graphics.postprocess.vBlur;
import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/17/2017.
 */
public class VBlurShader extends ShaderProgram
{
    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/vBlur/vBlurVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/vBlur/vBlurFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_frameHeight;

    public VBlurShader()
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
        location_frameHeight = super.getUniformLocation("height");
    }
    public void loadFrameHeight(float height)
    {
        super.loadFloat(location_frameHeight, height);
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }
}
