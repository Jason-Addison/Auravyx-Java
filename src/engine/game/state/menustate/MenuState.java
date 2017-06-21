package engine.game.state.menustate;

import engine.game.Game;
import engine.game.Settings;
import engine.game.state.State;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.gui.Gui;
import engine.graphics.gui.button.Button;
import engine.graphics.gui.button.OnClickEvent;
import engine.graphics.gui.guicomponents.Event;
import engine.graphics.gui.guicomponents.KeyEvent;
import engine.graphics.gui.guicomponents.KeyboardListener;
import engine.graphics.gui.guicomponents.graphical.StaticLoop;
import engine.graphics.gui.guicomponents.tickbox.TickBox;
import engine.graphics.gui.shape.rectangle.HollowRectangle;
import engine.graphics.postprocess.FBO;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import server.Server;
import server.menu.MultiplayerMenu;
import tools.Maths;

/**
 * Created by Owner on 3/6/2017.
 */
public class MenuState extends State
{
    //Renderer renderer = new Renderer();
    Camera camera = new Camera();
    float rS, gS, bS;
    Gui gui = new Gui();
    Server server = new Server();
    //Client client = new Client();
    public static String SERVER_TESTER = "tester";
    public MenuState()
    {
        fbo = new FBO(Display.getWidth(), Display.getHeight());
        setFBO(Display.getWidth(), Display.getHeight());
        rS = Maths.randomFloat(0, 1);
        gS = Maths.randomFloat(0, 1);
        bS = Maths.randomFloat(0, 1);
        initGui();
        //Map map = new Map("island");
        //client.send();
        //server.receive();
        Vector3f[] tri1 = {new Vector3f(-1, 0, -1), new Vector3f(1, 0, -1), new Vector3f(1, 0, 1), new Vector3f(-1, 0, 1)};
        Vector3f[] tri2 = {new Vector3f(-1, 1, 1.01f), new Vector3f(1, 1, 1.01f), new Vector3f(1, -1, 1.01f), new Vector3f(-1, -1, 1.01f)};
       // client.send();
        //client.update();
    }
    public void update()
    {
        r = (float) Math.sin((rS * time)) + 0.1f;
        g = (float) Math.sin((gS * time)) + 0.1f;
        b = (float) Math.sin((bS * time)) + 0.1f;
        menuScale =  (Gfx.HEIGHT * 0.002f);
        gui.getComponent("playButton").setBounds(30, (int) (100 * menuScale), (int) (200 * menuScale), (int) (37.5f * menuScale));
        gui.getComponent("multiplayerButton").setBounds(30, (int) (150 * menuScale), (int) (200 * menuScale), (int) (37.5f * menuScale));
        gui.getComponent("editorButton").setBounds(30, (int) (200 * menuScale), (int) (200 * menuScale), (int) (37.5f * menuScale));
        //gui.getComponent("playButton").setBounds((int) 30, (int) (100 * menuScale), (int) (200 * menuScale), (int) (37.5f * menuScale));
        //gui.getComponent("settingsButton").setBounds((int) 30, (int) (250 * menuScale), (int) (200 * menuScale), (int) (37.5f * menuScale));
        gui.getComponent("settingsButton").setBounds(30, (int) (250 * menuScale), (int) (200 * menuScale), (int) (37.5f * menuScale));
        gui.getComponent("guiSettingsMain").getComponent("Video Settings").setBounds(Gfx.WIDTH / 2 - (400 * menuScale / 2) ,
                Gfx.HEIGHT / 2 - (40 * menuScale / 2), 400 * menuScale, 40 * menuScale);
        gui.getComponent("guiSettingsMain").getComponent("videoSettingsGui")
                .getComponent("showAllColourChannelsButton").setBounds(10 * menuScale, 10 * menuScale, (int) (50 * menuScale), (50 * menuScale));
        render();
    }
    float e;
    float width, height;
    public void render()
    {
        e+=0.1f;
        camera.set(0, 10, 0, 0, e, 0);
        Gfx.gameRenderer.setCamera(camera);
        if(width != Gfx.WIDTH || height != Gfx.HEIGHT)
        {
            updateFBO();
        }
        Mouse.setGrabbed(false);
        clear();
        fbo.bind();
        clear();
        Gfx.gameRenderer.render(fbo);

        fbo.unbind();

        //fbo.blur(20);
        fbo.bind();
        Gfx.fillRect(0, 0, Gfx.WIDTH, Gfx.HEIGHT, r, g, b, 0.7f);
        renderOverlay(); //drawing rects
        gui.update();
        fbo.unbind();
        //Gfx.drawString(SERVER_TESTER, 300, 100, 50, 0, 0, 0, 1);
        fbo.render();
    }
    float time;
    float r, g, b;
    public void renderOverlay()
    {
        time+= 2f * Game.getDelta();

        //drawText();
        //drawButtons();
    }
    public void initGui()
    {
        Button playButton = new Button("Play", 1, 1, 1, 1);
        playButton.addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {
                Game.setState(State.GAME_STATE);
            }
        });
        KeyboardListener keyboardListener = new KeyboardListener();
        keyboardListener.addKeyEvent(new KeyEvent()
        {
            @Override
            public void onType()
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_0))
                {
                }
            }
        });
        Button editorButton = new Button("Editor", 1, 1, 1, 1);
        editorButton.addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {
                Game.setState(State.EDITOR_STATE);
            }
        });
        Button multiplayerButton = new Button("Multiplayer", 1, 1, 1, 1);
        multiplayerButton.addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {
                gui.setLockStatus(false);
                gui.setActiveSubComponent("multiplayer");
            }
        });

        Button settingsButton = new Button("Settings", 1, 1, 1, 1);

        gui.addStaticLoop(new StaticLoop()
        {
            @Override
            public void loop()
            {
                drawText();
            }
        });

        Gui guiSettingsMain = initSettings(gui);
        settingsButton.addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {
                gui.setActiveSubComponent("guiSettingsMain");
            }
        });
        gui.addComponent("guiSettingsMain", guiSettingsMain);
        gui.addComponent("settingsButton", settingsButton);
        gui.addComponent("playButton", playButton);
        gui.addComponent("editorButton", editorButton);
        gui.addComponent("multiplayerButton", multiplayerButton);
        //gui.addComponent("keyboardListener", keyboardListener);
        gui = MultiplayerMenu.setUp(gui);
    }
    private Gui initSettings(Gui guiMain)
    {
        Gui guiSettingsMain = new Gui();
        guiSettingsMain.setLockStatus(true);
        Gui videoSettings = new Gui();

        TickBox showAllColourChannels = new TickBox("Show all colour channels", 1, 1, 1, 1);
        showAllColourChannels.setToggle(Settings.getBooleanSetting("showAllColourChannels"));
        showAllColourChannels.addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {
                if(!Settings.getBooleanSetting("showAllColourChannels"))
                {
                    Settings.setSetting("showAllColourChannels", true);
                }
            }
        });
        showAllColourChannels.addOnUntoggleEvent(new Event()
        {
            @Override
            public void onEvent()
            {
                if(Settings.getBooleanSetting("showAllColourChannels"))
                {
                    Settings.setSetting("showAllColourChannels", false);
                }
            }
        });
        videoSettings.addComponent("showAllColourChannelsButton", showAllColourChannels);
        videoSettings.addStaticLoop(new StaticLoop()
        {
            @Override
            public void loop()
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    videoSettings.setLockStatus(true);
                    guiSettingsMain.setActiveSubComponent("this");
                }
                Gfx.fillRect(0, 0, Gfx.WIDTH, Gfx.HEIGHT, 0, 0, 0, 0.7f);
            }
        });
        videoSettings.setLockStatus(true);
        guiSettingsMain.addStaticLoop(new StaticLoop()
        {
            @Override
            public void loop()
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    guiSettingsMain.setLockStatus(true);
                    gui.setActiveSubComponent("this");
                }
            }
        });
        Button videoSettingsButton = new Button("Video Settings", 1, 1, 1, 1);
        videoSettingsButton.addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {
                videoSettings.setLockStatus(false);
                guiSettingsMain.setActiveSubComponent("videoSettingsGui");
            }
        });


        guiSettingsMain.addComponent("videoSettingsGui", videoSettings);
        guiSettingsMain.addComponent("Video Settings", videoSettingsButton);
        return guiSettingsMain;
    }
    float menuScale =  (Gfx.HEIGHT * 0.002f);
    public void drawText()
    {
        Gfx.drawStringOutline("Quality Game", 25, 10 * menuScale, 62 * menuScale, 1, 1, 1, 1, 1);
        Gfx.drawStringOutline(Game.VERSION, Gfx.WIDTH - 85 * menuScale, Gfx.HEIGHT - (15 * menuScale), 10 * menuScale, 1, 1, 1, 1, 1);
    }
    public void drawButtons()
    {
        Gfx.fillRect(30, 100 * menuScale, 200 * menuScale, 37.5f * menuScale, 0, 0, 0, 0.3f);
        HollowRectangle.drawRectangle(30, 100 * menuScale, 200 * menuScale, 37.5f * menuScale, 3, 0, 0, 0, 1);
        Gfx.drawString("Play", 35, 110 * menuScale, 20 * menuScale, 1, 1, 1, 1);

        Gfx.fillRect(30, 150 * menuScale, 200 * menuScale, 37.5f * menuScale, 0, 0, 0, 0.3f);
        HollowRectangle.drawRectangle(30, 150 * menuScale, 200 * menuScale, 37.5f * menuScale, 3, 0, 0, 0, 1);
        Gfx.drawString("Free Memes", 35, 160 * menuScale, 20 * menuScale, 1, 1, 1, 1);

        Gfx.fillRect(30, 200 * menuScale, 200 * menuScale, 37.5f * menuScale, 0, 0, 0, 0.3f);
        HollowRectangle.drawRectangle(30, 200 * menuScale, 200 * menuScale, 37.5f * menuScale, 3, 0, 0, 0, 1);
        Gfx.drawString("Editor", 35, 210 * menuScale, 20 * menuScale, 1, 1, 1, 1);

        Gfx.fillRect(30, 250 * menuScale, 200 * menuScale, 37.5f * menuScale, 0, 0, 0, 0.3f);
        HollowRectangle.drawRectangle(30, 250 * menuScale, 200 * menuScale, 37.5f * menuScale, 3, 0, 0, 0, 1);
        Gfx.drawString("Settings", 35, 260 * menuScale, 20 * menuScale, 1, 1, 1, 1);
    }
}
