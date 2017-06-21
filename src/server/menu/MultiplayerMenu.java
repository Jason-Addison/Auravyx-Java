package server.menu;

import engine.game.Game;
import engine.graphics.Gfx;
import engine.graphics.gui.Gui;
import engine.graphics.gui.Looper;
import engine.graphics.gui.Style;
import engine.graphics.gui.button.Button;
import engine.graphics.gui.button.OnClickEvent;
import engine.graphics.gui.guicomponents.Event;
import engine.graphics.gui.guicomponents.graphical.StaticLoop;
import engine.graphics.gui.scroll.ScrollArea;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import server.Client;

/**
 * Created by Owner on 5/25/2017.
 */
public class MultiplayerMenu
{

    static final float tileScale = 128;
    public static Gui setUp(Gui input)
    {
        Gui gui = new Gui();
        gui.setLockStatus(true);
        gui.addStaticLoop(new StaticLoop()
        {
            @Override
            public void loop()
            {
                if(Display.wasResized() || gui.wasSetActive())
                {
                    gui.getComponent("connectButton").setBounds(Gfx.WIDTH * 0.1f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);
                    gui.getComponent("addServerButton").setBounds(Gfx.WIDTH * 0.3f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);
                    gui.getComponent("directConnectButton").setBounds(Gfx.WIDTH * 0.5f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);
                    gui.getComponent("removeServerButton").setBounds(Gfx.WIDTH * 0.7f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);
                }
            }
        });
        Looper background = new Looper(new Event()
        {
            @Override
            public void onEvent()
            {
                int xScale = (int) (Gfx.WIDTH / tileScale) + 1;
                int yScale = (int) (Gfx.HEIGHT / tileScale) + 1;

                float guiScale = Gfx.WH * 0.0001f;
                for(int x = 0; x < xScale; x++)
                {
                    for(int y = 0; y < yScale; y++)
                    {
                        Gfx.drawImage(x * tileScale, y * tileScale, tileScale, tileScale, Game.getTexture("stone_tile_rough"));
                    }
                }
            }
        });
        Looper serverMenu = new Looper(new Event()
        {
            @Override
            public void onEvent()
            {
                Gfx.fillRect(0, 0, Gfx.WIDTH, Gfx.HEIGHT * 0.15f, 0, 0, 0, 0.6f);
                Gfx.fillRect(0, Gfx.HEIGHT * 0.15f, Gfx.WIDTH, Gfx.HEIGHT - (Gfx.HEIGHT * 0.15f * 2), 0, 0, 0, 0.9f);
                Gfx.fillRect(0, Gfx.HEIGHT - (Gfx.HEIGHT * 0.15f), Gfx.WIDTH, Gfx.HEIGHT * 0.15f, 0, 0, 0, 0.6f);
                Gfx.drawString("Server List", Gfx.WIDTH * 0.001f, Gfx.HEIGHT * 0.03f, Gfx.HEIGHT * 0.06f, 0.9f, 0.9f, 0.9f, 1);
                Gfx.drawString("Your computer : " + Client.HOST_NAME + " IP : " + Client.IP, 10, Gfx.HEIGHT * 0.03f, Gfx.HEIGHT * 0.02f, 0.9f, 0.9f, 0.9f, 1, Style.RIGHT_TO_LEFT);
            }
        });
        Button connectButton = new Button("Connect", 1, 1, 1, 1);
        connectButton.setBounds(Gfx.WIDTH * 0.1f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);

        Button addServerButton = new Button("Add Server", 1, 1, 1, 1);
        addServerButton.setBounds(Gfx.WIDTH * 0.3f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);

        Button directConnect = new Button("Direct Connect", 1, 1, 1, 1);
        directConnect.setBounds(Gfx.WIDTH * 0.5f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);

        Button removeServerButton = new Button("Remove", 1, 1, 1, 1);
        removeServerButton.setBounds(Gfx.WIDTH * 0.7f, Gfx.HEIGHT - Gfx.HEIGHT * 0.13f, Gfx.WH * 0.12f, Gfx.WH * 0.015f);

        ScrollArea scrollArea = new ScrollArea();
        Button button = new Button();
        scrollArea.addComponent("tester", button);
        Looper keyListener = new Looper(new Event()
        {
            public void onEvent()
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    input.setActiveSubComponent("this");
                }
            }
        });

        addServerButton.addOnClickEvent(new OnClickEvent()
        {
            @Override
            public void onClick()
            {
                gui.setActiveSubComponent("addServerGui");
            }
        });

        Gui addServerGui = new Gui();
        addServerGui.addComponent("background", background);

        addServerGui.addStaticLoop(new StaticLoop()
        {
            @Override
            public void loop()
            {
                Gfx.fillRect(0, 0, Gfx.WIDTH, Gfx.HEIGHT, 0, 0, 0, 0.9f);
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    gui.setActiveSubComponent("this");
                }
            }
        });

        gui.addComponent("background", background);
        gui.addComponent("menu", serverMenu);
        gui.addComponent("addServerGui", addServerGui);
        gui.addComponent("removeServerButton", removeServerButton);
        gui.addComponent("directConnectButton", directConnect);
        gui.addComponent("addServerButton", addServerButton);
        gui.addComponent("connectButton", connectButton);
        gui.addComponent("serverScrollArea", scrollArea);
        gui.addComponent("keyListener", keyListener);
        input.addComponent("multiplayer", gui);

        //input.addComponent("oh", event);
        return input;
    }

    private static Gui addServer(Gui input)
    {
        Gui gui = new Gui();

        input.addComponent("addServerGui", gui);
        return input;
    }

}
