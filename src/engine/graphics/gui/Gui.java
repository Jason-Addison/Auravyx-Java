package engine.graphics.gui;

import engine.graphics.gui.guicomponents.GuiComponent;
import org.lwjgl.opengl.Display;

import java.util.HashMap;

/**
 * Created by Owner on 2/24/2017.
 */
public class Gui extends GuiComponent
{
    private boolean fillScreen;
    private float x, y;
    private float width, height;

    String activeSubComponent = "this"; /* this means no sub component is active */
    public Gui()
    {
        initializeComponent();
    }

    public void update()
    {
        handleResize();
        updateComponents();
    }
    public void updateComponents()
    {
        if(activeSubComponent.equals("this"))
        {
            staticLoop();
            for (String key : components.keySet())
            {
                if(!components.get(key).isLocked() && !(components.get(key) instanceof Gui))
                {
                    components.get(key).update();
                    components.get(key).staticLoop();
                    components.get(key).render();
                    components.get(key).baseComponentUpdate();
                }
            }
        }
        else
        {
            components.get(activeSubComponent).update();
        }
    }

    public void render()
    {
        renderBackground();
    }

    private void renderBackground()
    {

    }

    private void handleResize()
    {
        if(Display.wasResized())
        {
            if(fillScreen)
            {
                x = 0;
                y = 0;
                width = Display.getWidth();
                height = Display.getHeight();
            }
        }
    }

    public void setBounds(int x, int y, int width, int height)
    {

    }

    public void setToFillScreen(boolean toggle)
    {
        fillScreen = toggle;
        if(toggle)
        {
            x = 0;
            y = 0;
            width = Display.getWidth();
            height = Display.getHeight();
        }
    }
    public void addComponent(String name, GuiComponent button)
    {
        components.put(name, button);
    }
    public String getActiveSubComponent()
    {
        return activeSubComponent;
    }
    public HashMap<String, GuiComponent> getAllComponents()
    {
        return components;
    }
    public void setActiveSubComponent(String componentName)
    {
        this.activeSubComponent = componentName;
        if(!componentName.equals("this"))
        {
            components.get(componentName).setActive();
        }
    }
    public void addAllComponents(Gui gui)
    {
        components.putAll(gui.getAllComponents());
    }
}
