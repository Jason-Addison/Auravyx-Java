package engine.graphics.gui.button;

import engine.graphics.Gfx;
import engine.graphics.gui.font.DrawableString;
import engine.graphics.gui.guicomponents.GuiComponent;
import engine.graphics.gui.shape.rectangle.HollowRectangle;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * Created by Owner on 2/22/2017.
 */
public class Button extends GuiComponent
{
    float x, y;
    float width, height;
    boolean mouseOver;
    boolean mouseClick;
    boolean mouseClicked;
    OnClickEvent onClickEvent;
    OnHoverEvent onHoverEvent;
    OnHoldEvent onHoldEvent;
    String buttonText;
    float r, g, b, a;
    float defaultWidth;
    float defaultHeight;
    public Button(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initInterfaces();
        initializeComponent();
    }
    public Button(String text, float r, float g, float b, float a)
    {
        this.buttonText = text;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.defaultWidth = Gfx.WIDTH;
        this.defaultHeight = Gfx.HEIGHT;
        initInterfaces();
    }
    boolean visible = true;
    public Button()
    {
        visible = false;
        initInterfaces();
    }
    private void initInterfaces()
    {
        addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {

            }
        });
        addOnHoverEvent(new OnHoverEvent()
        {
            @Override
            public void onHover()
            {

            }
        });
        addOnHoldEvent(new OnHoldEvent()
        {
            @Override
            public void onHold()
            {

            }
        });
    }
    public void update()
    {
        mouseOver = false;
        mouseClicked = false;
        updatePosition();
        intersects();
        checkMouseClicked();
        checkMouseClick();
        render();
    }
    public void updatePosition()
    {
        if(false && Display.wasResized())
        {
            float xFactor = (Gfx.WIDTH / defaultWidth);
            float yFactor = (Gfx.HEIGHT / defaultHeight);
            defaultWidth = Gfx.WIDTH;
            defaultHeight = Gfx.HEIGHT;
            float newX = x * xFactor;
            float newY = y * yFactor;
            x = newX;
            y = newY;
            setBounds(x, y, width, height);
        }
    }
    public void render()
    {
        if(visible)
        {
            float stringWidth = (DrawableString.getPixelWidth(new DrawableString(buttonText, 0, 0, height / 1.5f, 1, 1, 1, 1)));
            //stringWidth *= 500;
            if(mouseOver && !mouseClick)
            {
                Gfx.fillRect(x, y, width, height, 0.5f, 0.5f, 0.5f, 0.3f);
            }
            else
            {
                Gfx.fillRect(x, y, width, height, 0, 0, 0, 0.3f);
            }
            HollowRectangle.drawRectangle(x, y, width, height, 3, 0, 0, 0, 1);
            if(!mouseClick)
            {
                Gfx.drawString(buttonText, x + (width / 2) - (stringWidth / 4), y + height / 8, height / 1.5f, r, g, b, a);
            }
            else
            {
                Gfx.drawString(buttonText, x + (width / 2) - (stringWidth / 4), y + height / 8, height / 1.5f, r * 0.5f, g * 0.5f, b * 0.5f, a);
            }
        }
    }
    private void checkMouseClicked()
    {
        if(mouseClick && !Mouse.isButtonDown(0) && mouseOver)
        {
            mouseClicked = true;
            onClickEvent.onClick();
        }
        if(!Mouse.isButtonDown(0))
        {
            mouseClick = false;
        }
    }
    private void checkMouseClick()
    {
        if(mouseOver)
        {
            if(Mouse.isButtonDown(0))
            {
                mouseClick = true;
                onHoldEvent.onHold();
            }
        }
    }
    private void intersects()
    {
        mouseOver = false;
        if(Mouse.getX() > x && Mouse.getX() < x + width && Gfx.HEIGHT - Mouse.getY() > y && Gfx.HEIGHT - Mouse.getY() < y + height)
        {
            mouseOver = true;
            onHoverEvent.onHover();
        }

    }
    public void addOnClickEvent(OnClickEvent onClickEvent)
    {
        this.onClickEvent = onClickEvent;
    }
    public void addOnHoverEvent(OnHoverEvent onHoverEvent)
    {
        this.onHoverEvent = onHoverEvent;
    }
    public void addOnHoldEvent(OnHoldEvent onHoldEvent)
    {
        this.onHoldEvent = onHoldEvent;
    }

    public boolean isMouseOver()
    {
        return mouseOver;
    }
    public boolean isMouseClickHold()
    {
        return mouseClick;
    }
    public boolean isMouseClicked()
    {
        return mouseClicked;
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
    public void set(Object object)
    {
        this.buttonText = (String) object;
    }
}
