package engine.graphics.model;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import tools.OBJMaterial;

import java.util.ArrayList;

/**
 * Created by Owner on 2/12/2017.
 */
public class Model implements Cloneable
{
    private int vaoID;
    private int vertexCount;
    private int texture;
    private boolean hasAlpha;
    private ArrayList<Integer> vertexAttributes = new ArrayList<Integer>();
    private ArrayList<OBJMaterial> materials = new ArrayList<>();

    public Model(int vaoID, int vertexCount)
    {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        for(int i = 0; i < materials.size(); i++)
        {
            if(materials.get(i).hasAlpha())
            {
                hasAlpha = true;
                break;
            }
        }
    }

    public void setVaoID(int vaoID)
    {
        this.vaoID = vaoID;
    }
    public int getVaoID()
    {
        return vaoID;
    }
    public int getVertexCount()
    {
        return vertexCount;
    }
    public ArrayList<Integer> getVertexAttributes()
    {
        return vertexAttributes;
    }
    public void linkVertexAttribute(int attributeID)
    {
        vertexAttributes.add(attributeID);
    }
    public void destroy()
    {
        GL30.glDeleteVertexArrays(vaoID);
        for(int i = 0; i < vertexAttributes.size(); i++)
        {
            GL15.glDeleteBuffers(vertexAttributes.get(i));
        }
        vertexAttributes.clear();
    }
    public int getTexture()
    {
        return texture;
    }
    public void setTexture(int texture)
    {
        this.texture = texture;
    }
    public ArrayList<OBJMaterial> getMaterials()
    {
        return materials;
    }
    public void setMaterials(ArrayList<OBJMaterial> materials)
    {
        this.materials = materials;
    }

    public boolean hasAlpha()
    {
        return hasAlpha;
    }

    public void overrideTexture(int texture)
    {
        OBJMaterial override = materials.get(0);
        override.setTexture(texture);
        materials.remove(0);
        materials.add(override);
    }
    public Model clone()
    {
        try
        {
            return (Model) super.clone();
        }
        catch (CloneNotSupportedException c)
        {
            c.printStackTrace();
        }
        return null;
    }


}
