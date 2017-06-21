package engine.entity;

import engine.graphics.Camera;
import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;
import tools.Maths;

/**
 * Created by Owner on 2/15/2017.
 */
public class EntityShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/entity/entityVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/entity/entityFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    private int location_camera;
    private int location_farPlane;
    private int location_id;
    private int location_entityCount;

    private int texture;
    private int normal;
    private int specular;
    private int glow;

    public EntityShader()
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
        location_id = super.getUniformLocation("id");
        location_entityCount = super.getUniformLocation("entityCount");
    }

    @Override
    protected void bindAttributes()
    {
        loadTextures();
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "normal");
        super.bindAttribute(3, "textureCoords");
        super.bindAttribute(texture, "entityTexture");
        super.bindAttribute(normal, "normalMap");
        super.bindAttribute(specular, "specular");
        super.bindAttribute(glow, "glow");
        loadTextures();
    }
    public void loadTextures()
    {
        super.loadTexture("entityTexture", 4);
        super.loadTexture("normalMap", 1);
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
    public void loadEntityCount(int count)
    {
        super.loadFloat(location_entityCount, count);
    }
    public void loadID(int id)
    {
        super.loadFloat(location_id, id);
    }
    public void loadFarPlane(float farPlane)
    {
        super.loadFloat(location_farPlane, farPlane);
    }
}
