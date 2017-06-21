package engine.graphics.postprocess.hBlur;
import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/17/2017.
 */
public class HBlurShader extends ShaderProgram
{
    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/hBlur/hBlurVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/hBlur/hBlurFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_frameWidth;

    public HBlurShader()
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
        location_frameWidth = super.getUniformLocation("width");
    }
    public void loadFrameWidth(float width)
    {
        super.loadFloat(location_frameWidth, width);
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }
}
