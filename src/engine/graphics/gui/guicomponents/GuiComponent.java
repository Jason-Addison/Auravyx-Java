package engine.graphics.gui.guicomponents;

import engine.graphics.gui.guicomponents.graphical.StaticLoop;

import java.util.LinkedHashMap;

/**
 * Created by Owner on 3/8/2017.
 */
public class GuiComponent
{
    protected LinkedHashMap<String, GuiComponent> components = new LinkedHashMap<>();
    boolean overrideComponents;
    boolean locked = false;
    StaticLoop staticLoop;
    boolean active = false;
    public GuiComponent()
    {
        initializeComponent();
    }
    public void initializeComponent()
    {
        staticLoop = new StaticLoop()
        {
            @Override
            public void loop()
            {

            }
        };
    }
    public void baseComponentUpdate()
    {
        active = false;
    }
    public void update()
    {

    }
    public void render()
    {

    }
    public void setBounds(int x, int y, int width, int height)
    {

    }
    public void setBounds(float x, float y, float width, float height)
    {

    }
    public void overrideComponents(boolean toggle)
    {
        this.overrideComponents = toggle;
    }
    public void setLockStatus(boolean toggle)
    {
        this.locked = toggle;
    }
    public boolean isLocked()
    {
        return locked;
    }
    public void addStaticLoop(StaticLoop staticLoop)
    {
        this.staticLoop = staticLoop;
    }
    public void staticLoop()
    {
        this.staticLoop.loop();
    }
    public GuiComponent getComponent(String component)
    {
        return components.get(component);
    }
    public void set(Object object)
    {

    }
    public void addHint(String hint)
    {

    }
    public void addComponent(String name, GuiComponent c)
    {
        components.put(name, c);
    }
    public void setActive()
    {
        active = true;
    }
    public boolean wasSetActive()
    {
        return active;
    }

}
