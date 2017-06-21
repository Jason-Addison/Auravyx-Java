package engine.graphics.postprocess.combine;

import engine.graphics.shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

public class CombineShader extends ShaderProgram
{

    private static final String VERTEX_FILE = "src/engine/graphics/postprocess/combine/combineVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/graphics/postprocess/combine/combineFragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_transparency;
    private int location_width;
    private int location_height;
    private int location_xOffset;
    private int location_yOffset;
    private int texture1;
    private int texture2;

    public CombineShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
     
    public void loadTransformation(Matrix4f matrix)
    {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadTransparency(float transparency)
    {
        super.loadFloat(location_transparency, transparency);
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
        location_transparency = super.getUniformLocation("transparency");
        location_width = super.getUniformLocation("width");
        location_height = super.getUniformLocation("height");
        location_xOffset = super.getUniformLocation("xOffset");
        location_yOffset = super.getUniformLocation("yOffset");
        super.bindAttribute(texture1, "texture1");
        super.bindAttribute(texture2, "texture2");
    }

    public void loadTextures()
    {
        super.loadTexture("texture1", 0);
        super.loadTexture("texture2", 1);
    }
 
    @Override
    protected void bindAttributes() 
    {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
     
}