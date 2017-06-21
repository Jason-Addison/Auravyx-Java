package engine.graphics.postprocess.deferred;

import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/17/2017.
 */
public class DeferredShader extends ShaderProgram
{
    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/deferred/deferredVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/deferred/deferredFragmentShader.glsl";

    private int location_transformationMatrix;
    private int albedo;
    private int normal;
    private int specular;
    private int glow;
    private int position;
    private int settings;
    private int sunDirection;

    public DeferredShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    public void loadTransformation(Matrix4f matrix)
    {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadSunDirection(float x, float y, float z)
    {
        super.loadVector(sunDirection, x, y ,z);
    }

    @Override
    protected void getAllUniformLocations()
    {
        super.bindAttribute(albedo, "albedo");
        super.bindAttribute(normal, "normal");
        super.bindAttribute(specular, "specular");
        super.bindAttribute(glow, "glow");
        super.bindAttribute(position, "position");
        super.bindAttribute(position, "depth");
        super.bindAttribute(settings, "settings");
        sunDirection = super.getUniformLocation("sunDirection");
    }

    public void loadTextures()
    {
        super.loadTexture("albedo", 0);
        super.loadTexture("normal", 1);
        super.loadTexture("specular", 2);
        super.loadTexture("glow", 3);
        super.loadTexture("position", 4);
        super.loadTexture("depth", 5);
        super.loadTexture("settings", 7);
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
    }
}
