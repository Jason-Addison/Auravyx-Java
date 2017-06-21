package engine.world.terrain;

import engine.graphics.Camera;
import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;
import tools.Maths;

/**
 * Created by Owner on 2/15/2017.
 */
public class TerrainShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/world/terrain/terrainVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/world/terrain/terrainFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    private int location_camera;
    private int location_farPlane;

    public TerrainShader()
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
        location_camera = super.getUniformLocation("camera");
        location_farPlane = super.getUniformLocation("farPlane");
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "normal");
    }

    public void loadViewMatrix(Camera camera)
    {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
    public void loadCamera(Camera camera)
    {
        super.loadVector(location_camera, camera.getX(), camera.getY(), camera.getZ());
    }
    public void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        super.loadMatrix(location_projectionMatrix, projectionMatrix);
    }
    public void loadFarPlane(float farPlane)
    {
        super.loadFloat(location_farPlane, farPlane);
    }
}
