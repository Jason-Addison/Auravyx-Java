package engine.graphics.postprocess.billboard;

import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/15/2017.
 */
public class BillboardShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/billboard/billboardVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/billboard/billboardFragmentShader.glsl";

    private int location_projectionMatrix;
    private int modelView;

    private int texture;
    private int normal;
    private int specular;
    private int glow;

    public BillboardShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }


    @Override
    protected void getAllUniformLocations()
    {
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        modelView = super.getUniformLocation("modelView");
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }
    public void loadTextures()
    {
        //super.loadTexture("entityTexture", 0);
        //super.loadTexture("normalMap", 1);
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
