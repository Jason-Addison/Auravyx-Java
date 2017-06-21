package engine.game;

import engine.display.DisplayManager;

/**
 * Created by Owner on 2/11/2017.
 */
public class Startup
{

    public static void main(String[] args)
    {
        load();
        startDisplay();
        startLoop();
        return;
    }

    public static void load()
    {

    }

    public static void startDisplay()
    {
        DisplayManager.startUp();
    }
    public static void startLoop()
    {
        Loop.update();
    }

}
