package entities.entityEditor;

import engine.component.Component;
import engine.component.WorldComponent;
import engine.entity.Entity;
import engine.game.Game;
import engine.game.Settings;
import engine.game.state.State;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.gui.Gui;
import engine.graphics.gui.guicomponents.Event;
import engine.graphics.gui.guicomponents.KeyEvent;
import engine.graphics.gui.guicomponents.KeyboardListener;
import engine.graphics.gui.guicomponents.tickbox.OnToggleEvent;
import engine.graphics.gui.guicomponents.tickbox.TexturedTickBox;
import engine.graphics.postprocess.FBO;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Created by Owner on 3/20/2017.
 */
public class EditorState extends State
{
    FBO viewport = new FBO((int) Gfx.WIDTH, (int) Gfx.HEIGHT);
    EditorRenderer renderer = new EditorRenderer();
    Camera camera;
    EditorOverlay editorOverlay = new EditorOverlay();
    public static float xAlpha = 0.5f;
    public static float yAlpha = 0.5f;
    public static float zAlpha = 0.5f;
    Gui gui = new Gui();
    public EditorState()
    {
        fbo = new FBO((int) Gfx.WIDTH, (int) Gfx.HEIGHT);
        Entity e = new Entity();
        WorldComponent wc = new WorldComponent();
        wc.set(0, 0, 0, 50, 0, 0, 1, 10, 1, 0, 0, 0);
        e.addComponent(Component.WORLD_COMPONENT, wc);
        camera = new Camera();
        camera.set(-1.5f, 1, 5, 0, 0, 0);
        initGui();
    }
    public void update()
    {
        resizeComponents();
        render();
    }

    public void render()
    {
        updateFBO();
        clear();
        camera = inputCamera(camera);
        //camera.set((float) Math.sin(x) * 15, 3, (float) Math.cos(x) * 15, 0, 0, 0);
        viewport.updateFBO();
        renderViewport();
        fbo.bind();
        clear();
        renderGui();
        Gfx.fillRect(Gfx.WIDTH * 0.01f - 1, Gfx.HEIGHT * 0.06f - 1, Gfx.WIDTH * 0.82f + 2, Gfx.HEIGHT * 0.82f + 2, 0, 0, 0, 1);
        viewport.render(Gfx.WIDTH * 0.01f, Gfx.HEIGHT * 0.06f, Gfx.WIDTH * 0.82f, Gfx.HEIGHT * 0.82f);
        editorOverlay.render();
        gui.updateComponents();
        fbo.unbind();
        fbo.render();
    }
    float xScale, yScale;
    public void resizeComponents()
    {
        gui.getComponent("xAxisButton").setBounds(Gfx.WIDTH * 0.01f, Gfx.HEIGHT - Gfx.HEIGHT * 0.11f, Gfx.WH * 0.02f, Gfx.WH * 0.02f);
        gui.getComponent("yAxisButton").setBounds(Gfx.WIDTH * 0.05f, Gfx.HEIGHT - Gfx.HEIGHT * 0.11f, Gfx.WH * 0.02f, Gfx.WH * 0.02f);
        gui.getComponent("zAxisButton").setBounds(Gfx.WIDTH * 0.09f, Gfx.HEIGHT - Gfx.HEIGHT * 0.11f, Gfx.WH * 0.02f, Gfx.WH * 0.02f);
        gui.update();
        gui.updateComponents();
    }
    public void renderViewport()
    {
        viewport.bind();
        clear();
        renderer.render(camera);
        viewport.unbind();
    }
    public void renderGui()
    {
        Gfx.fillRect(0, 0, Gfx.WIDTH, Gfx.HEIGHT * 0.05f, 0.7f, 0.7f, 0.7f, 1);
        Gfx.drawString("yes.png", 0, 0, 10, 1, 1, 1, 1);
    }
    private void initGui()
    {
        KeyboardListener editorKeyboardListener = new KeyboardListener();
        editorKeyboardListener.addKeyEvent(new KeyEvent()
        {
            @Override
            public void onType()
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    Game.setState(State.MENU_STATE);
                }
            }
        });
        gui.addComponent("keyboardListener", editorKeyboardListener);
        TexturedTickBox xAxisButton = new TexturedTickBox(0.4f, Game.getTexture("xaxis"));
        xAxisButton.setToggle(Settings.getBooleanSetting("editorXAxisVisible"));
        xAxisButton.addOnToggleEvent(new OnToggleEvent()
        {
            @Override
            public void onToggle()
            {
                xAlpha = 0.7f;
            }
        });
        xAxisButton.addOnUntoggleEvent(new Event()
        {
            @Override
            public void onEvent()
            {
                xAlpha = 0.1f;
            }
        });
        gui.addComponent("xAxisButton", xAxisButton);
        TexturedTickBox yAxisButton = new TexturedTickBox(0.4f, Game.getTexture("yaxis"));
        yAxisButton.setToggle(Settings.getBooleanSetting("editorYAxisVisible"));
        yAxisButton.addOnToggleEvent(new OnToggleEvent()
        {
            @Override
            public void onToggle()
            {
                yAlpha = 0.7f;
            }
        });
        yAxisButton.addOnUntoggleEvent(new Event()
        {
            @Override
            public void onEvent()
            {
                yAlpha = 0.1f;
            }
        });
        gui.addComponent("yAxisButton", yAxisButton);
        TexturedTickBox zAxisButton = new TexturedTickBox(0.4f, Game.getTexture("zaxis"));
        zAxisButton.setToggle(Settings.getBooleanSetting("editorZAxisVisible"));
        zAxisButton.addOnToggleEvent(new OnToggleEvent()
        {
            @Override
            public void onToggle()
            {
                zAlpha = 0.7f;
            }
        });
        zAxisButton.addOnUntoggleEvent(new Event()
        {
            @Override
            public void onEvent()
            {
                zAlpha = 0.1f;
            }
        });
        gui.addComponent("zAxisButton", zAxisButton);
    }

    float xRot;
    float yRot;
    float zRot;
    float xRotAcc;
    float yRotAcc;
    public Camera inputCamera(Camera camera)
    {

        if(Mouse.isButtonDown(1) && Mouse.getX() > Gfx.WIDTH * 0.01f - 1 && Gfx.HEIGHT - Mouse.getY() > Gfx.HEIGHT * 0.06f - 1 && Mouse.getX() < Gfx.WIDTH * 0.01f - 1 + Gfx.WIDTH * 0.82 + 2
                && Gfx.HEIGHT - Mouse.getY() < ((Gfx.HEIGHT * 0.06f - 1) + (Gfx.HEIGHT * 0.82f + 2)))
        {
            xRot -= Mouse.getDY() * Game.getDelta() * 20;
            yRot += Mouse.getDX() * Game.getDelta() * 20;
        }
        updateMouseAcc();
        camera.set(x, y, z, xRot, yRot, 0);
        return camera;
    }
    float scale = 1f;
    float xVel, yVel, zVel;
    float x, y, z;
    private void updateMouseAcc()
    {
        xVel = 0;
        yVel = 0;
        zVel = 0;

        double angle = Math.toRadians(yRot);
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            xVel += ((float) + Math.sin(angle));
            zVel -= ((float) Math.cos(angle));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            xVel += ((float) Math.cos(angle));
            zVel += ((float) Math.sin(angle));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            xVel -= ((float) Math.cos(angle));
            zVel -= ((float) Math.sin(angle));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            xVel -= ((float) Math.sin(angle));
            zVel += ((float) + Math.cos(angle));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
           //yVel -= 2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {
            yVel += 2;
        }
        if((xRot) % 360 > 90)
        {
            xRot = 90;
        }
        if((xRot) % 360 < -90)
        {
            xRot = -90;
        }
        if(xRotAcc > 0)
        {
            xRotAcc -= scale;
        }
        if(xRotAcc < 0)
        {
            xRotAcc += scale;
        }
        if(yRotAcc > 0)
        {
            yRotAcc -= scale;
        }
        if(yRotAcc < 0)
        {
            yRotAcc += scale;
        }
        x += xVel * Game.getDelta();
        y += yVel * Game.getDelta();
        z += zVel * Game.getDelta();
    }
}
