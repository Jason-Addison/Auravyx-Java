package engine.world.effects;

/**
 * Created by Owner on 4/2/2017.
 */
public class SkyLevel
{
    
    float distance;
    float r;
    float g;
    float b;
    
    public SkyLevel(float distance, float r, float g, float b)
    {
        this.distance = distance;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getDistance() 
    {
        return distance;
    }

    public void setDistance(float distance) 
    {
        this.distance = distance;
        
    }

    public float getR()
    {
        return r;
    }

    public void setR(float r) 
    {
        this.r = r;
        
    }

    public float getG() 
    {
        return g;
    }

    public void setG(float g)
    {
        this.g = g;
        
    }

    public float getB()
    {
        return b;
    }

    public void setB(float b)
    {
        this.b = b;
        
    }

    
    
}
