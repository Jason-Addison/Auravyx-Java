package engine.display;

import engine.game.Game;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Owner on 2/11/2017.
 */

public class DisplayManager
{

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH = (int) screenSize.getWidth();
    public static int HEIGHT = (int) screenSize.getHeight();
    public static final int FPS_CAP = 120;

    private static long lastFrameTime;
    private static float delta;

    public static JFrame frame = new JFrame();
    public static AWTGLCanvas canvas;

    public static void startUp()
    {
        ContextAttribs	 attribs = new ContextAttribs(3,2)
                .withForwardCompatible(true)
                .withProfileCore(true);
        Display.setVSyncEnabled(false);
        try
        {
            int x = 1200;
            int y = 600;
            canvas = new AWTGLCanvas();
            frame.setSize(x, y);
            frame.setVisible(true);
            frame.add(canvas);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            WindowListener exitListener = new WindowAdapter()
            {

                @Override
                public void windowClosing(WindowEvent e)
                {

                    Game.closeRequested = true;
                }
            };
            frame.addWindowListener(exitListener);
            Display.setParent(canvas);
            Display.create();
            Display.makeCurrent();
            GL11.glViewport(0, 0, x, y);
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
        lastFrameTime = getCurrentTime();
    }
    static float framesCounter = 0;
    public static float FPS;
    static long timer = System.currentTimeMillis();
    public static void updateDisplay()
    {
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;

        lastFrameTime = currentFrameTime;
        framesCounter++;
        if(System.currentTimeMillis() - timer > 1000)
        {
            timer += 1000;
            FPS = framesCounter * 1;
            framesCounter = 0;
        }
    }

    public static float getDelta()
    {
        return delta;
    }

    public static float getFrameTimeSeconds()
    {
        return delta;
    }

    public static void closeDisplay()
    {
        Display.destroy();
    }

    private static long getCurrentTime()
    {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }


}
