package tools;

/**
 * Created by Owner on 4/18/2017.
 */
public class OBJMaterial
{
    int texture;
    int length;
    boolean hasAlpha;

    public OBJMaterial(int texture, boolean hasAlpha, int length)
    {
        this.texture = texture;
        this.length = length;
        this.hasAlpha = hasAlpha;
    }

    public int getTexture()
    {
        return texture;
    }

    public void setTexture(int texture)
    {
        this.texture = texture;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public boolean hasAlpha()
    {
        return hasAlpha;
    }
}
