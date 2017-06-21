package engine.game.interaction;

/**
 * Created by Owner on 3/12/2017.
 */
public class Controller
{

    public static byte MOUSE;
    public static byte KEYBOARD;
    boolean active;
    byte controlType;

    public Controller(byte controlType)
    {
        this.controlType = controlType;
    }
    public byte getControlType()
    {
        return controlType;
    }
    public void setControlType(byte controlType)
    {
        this.controlType = controlType;
    }
    public void setActive(boolean active)
    {
        this.active = active;
    }
    public boolean isActive()
    {
        return active;
    }
}
