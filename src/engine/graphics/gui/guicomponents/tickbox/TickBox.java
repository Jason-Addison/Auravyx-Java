package engine.graphics.gui.guicomponents.tickbox;

import engine.graphics.Gfx;
import engine.graphics.gui.button.OnClickEvent;
import engine.graphics.gui.guicomponents.Event;
import engine.graphics.gui.guicomponents.GuiComponent;
import engine.graphics.gui.shape.rectangle.HollowRectangle;
import org.lwjgl.input.Mouse;

/**
 * Created by Owner on 3/10/2017.
 */
public class TickBox extends GuiComponent
{
    float x, y;
    float width, height;
    OnClickEvent onClickEvent;
    OnToggleEvent onToggleEvent;
    Event onUntoggleEvent;
    boolean toggled;
    boolean clicked;
    boolean mouseInBounds;
    boolean mouseDown;
    float r, g, b, a;
    String text;
    int texture;
    public TickBox()
    {

    }
    public TickBox(String text, float r, float g, float b, float a)
    {
        initialize();
        initializeComponent();
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.text = text;
    }
    public void initialize()
    {
        addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {

            }
        });
        addOnToggleEvent(new OnToggleEvent()
        {
            @Override
            public void onToggle()
            {

            }
        });
    }
    public void update()
    {
        checkBounds();
        checkClick();
        if(clicked && !Mouse.isButtonDown(0) && mouseInBounds)
        {
            clicked = false;
            onClickEvent.onClick();
            toggled = !toggled;
            if(toggled == false)
            {
                onUntoggleEvent.onEvent();
            }
        }
        if(toggled && mouseInBounds)
        {
            onToggleEvent.onToggle();
        }
        if(!Mouse.isButtonDown(0))
        {
            clicked = false;
            mouseDown = false;
        }
        else
        {
            mouseDown = true;
        }
    }
    public void render()
    {
        Gfx.fillRect(x, y, width, height, 0, 0, 0, 0.5f);
        HollowRectangle.drawRectangle(x, y, width, height, 3, 0, 0, 0, 1);
        if(toggled)
        {
            Gfx.fillRect(x + (width * 0.15f), y + (height * 0.15f), width - (width * 0.3f), height - (height * 0.3f), 0.8f, 0.8f, 0.8f, 1);
        }
        Gfx.drawString(text, x + width, y + (height / 4), height / 3f, r, g, b, a);
    }
    private void checkBounds()
    {
        mouseInBounds = Mouse.getX() > x && Mouse.getX() < x + width && Gfx.HEIGHT - Mouse.getY() > y && Gfx.HEIGHT - Mouse.getY() < y + height;
    }
    private void checkClick()
    {
        if(!mouseDown && Mouse.isButtonDown(0) && mouseInBounds)
        {
            clicked = true;
        }
    }

    public void setBounds(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setBounds(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setToggle(boolean toggle)
    {
        this.toggled = toggle;
    }
    public void addOnClickEvent(OnClickEvent onClickEvent)
    {
        this.onClickEvent = onClickEvent;
    }
    public void addOnToggleEvent(OnToggleEvent onToggleEvent)
    {
        this.onToggleEvent = onToggleEvent;
    }
    public void addOnUntoggleEvent(Event event)
    {
        this.onUntoggleEvent = event;
    }
}
