package engine.graphics.gui.shape.rectangle;

import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

public class RectangleShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/gui/shape/rectangle/rectangleVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/gui/shape/rectangle/rectangleFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_colour;
    private int location_width;
    private int location_height;
    private int location_xOffset;
    private int location_yOffset;

    public RectangleShader()
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

    @Override
    protected void getAllUniformLocations() 
    {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_colour = super.getUniformLocation("colour");
        location_width = super.getUniformLocation("width");
        location_height = super.getUniformLocation("height");
        location_xOffset = super.getUniformLocation("xOffset");
        location_yOffset = super.getUniformLocation("yOffset");
    }
 
    @Override
    protected void bindAttributes() 
    {
        super.bindAttribute(0, "position");
    }
     
}