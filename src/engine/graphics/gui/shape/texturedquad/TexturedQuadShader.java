package engine.graphics.gui.shape.texturedquad;

import engine.graphics.Camera;
import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;
import tools.Maths;

/**
 * Created by Owner on 2/15/2017.
 */
public class TexturedQuadShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/gui/shape/texturedquad/texturedQuadVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/gui/shape/texturedquad/texturedQuadFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    public TexturedQuadShader()
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
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }

    public void loadViewMatrix(Camera camera)
    {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        viewMatrix.m30 = 0;
        viewMatrix.m31 = 0;
        viewMatrix.m32 = 0;
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
    public void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        super.loadMatrix(location_projectionMatrix, projectionMatrix);
    }
}
