package engine.graphics.gui.shape.line;

import engine.graphics.Camera;
import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;
import tools.Maths;

public class LineShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/gui/shape/line/lineVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/gui/shape/line/lineFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_colour;
    private int location_width;
    private int location_height;
    private int location_xOffset;
    private int location_yOffset;
    private int location_viewMatrix;
    private int location_projectionMatrix;

    public LineShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
     
    public void loadTransformation(Matrix4f matrix)
    {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadColour(float r, float g, float b, float a)
    {
        super.loadVector4f(location_colour, r, g, b, a);
    }
    public void loadScreenDimensions(float width, float height)
    {
        super.loadFloat(location_width, width);
        super.loadFloat(location_height, height);
    }
    public void loadOffsets(float xOffset, float yOffset)
    {
        super.loadFloat(location_xOffset, xOffset);
        super.loadFloat(location_yOffset, yOffset);
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

    @Override
    protected void getAllUniformLocations() 
    {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_colour = super.getUniformLocation("colour");
        location_width = super.getUniformLocation("width");
        location_height = super.getUniformLocation("height");
        location_xOffset = super.getUniformLocation("xOffset");
        location_yOffset = super.getUniformLocation("yOffset");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }
 
    @Override
    protected void bindAttributes() 
    {
        super.bindAttribute(0, "position");
    }
     
}