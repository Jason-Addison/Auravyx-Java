package engine.graphics.particle.onecolourpass;

import engine.graphics.Camera;
import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;
import tools.Maths;

/**
 * Created by Owner on 6/20/2017.
 */
public class OneColourPassShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/particle/onecolourpass/oneColourPassVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/particle/onecolourpass/oneColourPassFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    private int modelView;

    public OneColourPassShader()
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
        modelView = super.getUniformLocation("modelView");
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }
    public void loadViewMatrix(Camera camera)
    {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
    public void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        super.loadMatrix(location_projectionMatrix, projectionMatrix);
    }
    public void loadModelViewMatrix(Matrix4f mat)
    {
        super.loadMatrix(modelView, mat);
    }
}

