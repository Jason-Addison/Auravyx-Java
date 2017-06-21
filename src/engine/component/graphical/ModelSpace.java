package engine.component.graphical;

/**
 * Created by Owner on 3/23/2017.
 */
public class ModelSpace
{

    String model;
    float xOff;
    float yOff;
    float zOff;

    public ModelSpace(String model, float xOff, float yOff, float zOff)
    {
        this.model = model;
        this.xOff = xOff;
        this.yOff = yOff;
        this.zOff = zOff;
    }

    public String getModel()
    {
        return model;
    }
    public float getXOff()
    {
        return xOff;
    }
    public float getYOff()
    {
        return yOff;
    }
    public float getZOff()
    {
        return zOff;
    }

}
