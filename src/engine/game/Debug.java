package engine.game;

import engine.display.DisplayManager;
import engine.game.interaction.MousePicker;
import engine.graphics.Gfx;
import engine.physics.collision.CollisionSandbox;
import engine.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector4f;
import tools.Maths;

import java.util.ArrayList;

/**
 * Created by Owner on 2/11/2017.
 */
public class Debug
{

    public static long START = 1487118490;
    public static float FPS = 0;
    public static final long SYSTEM_START_TIME = System.currentTimeMillis();
    public static long SYSTEM_TOTAL_RUNTIME = 0;
    private ArrayList<Float> FPS_MEMORY = new ArrayList<Float>();
    private long FPS_TIMER = System.currentTimeMillis();
    public static float AVERAGE_FPS;
    public static float HIGH_FPS;
    public static float LOW_FPS;
    private float START_TO_END = 0;
    private int colourChannelCycle = 3;
    private int[] channelCycle = new int[5];
    int cycleOffset;
    static boolean printAllWarnings = false;
    private final static String LOW_WARNING = "[Caution]";
    private final static String MEDIUM_WARNING = "[Warning]";
    private final static String HIGH_WARNING = "[!!! Warning !!!]";

    boolean debugMenuOn;
    boolean debugMenuHold;
    public static void warn(String message, int level)
    {
        if(printAllWarnings)
        {
            String prefix;
            String suffix = "";
            if(level == 0)
            {
                prefix = LOW_WARNING;
            }
            else if(level == 1)
            {
                prefix = MEDIUM_WARNING;
            }
            else
            {
                prefix = HIGH_WARNING;
                suffix = HIGH_WARNING;
            }
            System.err.println(prefix + " : " + message + " " + suffix);
        }
    }
    public void update()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_F1) && !debugMenuHold)
        {
            debugMenuHold = true;
            if(debugMenuOn)
            {
                debugMenuOn = false;
            }
            else
            {
                debugMenuOn = true;
            }
        }
        else if(!Keyboard.isKeyDown(Keyboard.KEY_F1))
        {
            debugMenuHold = false;
        }
        calculateFPS();
        SYSTEM_TOTAL_RUNTIME = System.currentTimeMillis() - SYSTEM_START_TIME;
    }

    public void calculateFPS()
    {
        FPS = DisplayManager.FPS;
        START_TO_END = ((System.currentTimeMillis() / 1000) - START);
        if(FPS_TIMER < System.currentTimeMillis() - 1000)
        {
            FPS_TIMER = System.currentTimeMillis();
            if(FPS_MEMORY.size() < 10)
            {
                FPS_MEMORY.add(FPS);
            }
            else
            {
                FPS_MEMORY.remove(0);
                FPS_MEMORY.add(FPS);
            }
            float average = 0;
            float high = 120;
            float low = 0;
            for(int i = 0; i < FPS_MEMORY.size(); i++)
            {
                average += FPS_MEMORY.get(i);
            }
            if(FPS > high)
            {
                FPS = FPS;
            }
            if(FPS < low)
            {
                low = FPS;
            }
            HIGH_FPS = high;
            LOW_FPS = low;
            AVERAGE_FPS = (average / FPS_MEMORY.size());
        }
    }

    public void runDebugMenu()
    {

        if(debugMenuOn)
        {
            Gfx.DRAW_RIGHT = false;
            Gfx.colour = new Vector4f(0, 0, 0, 1);
            //Gfx.fillRect(0, 0, 100, 100, 0, 0, 0, 0.1f);
            Gfx.drawString("FPS : " + FPS + " | Avg : " + (int) AVERAGE_FPS + " | High : " + HIGH_FPS + " | Low : " + LOW_FPS, 2, 0.1f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Total Time : " + Maths.roundToTenth(START_TO_END / 60 / 60) + " hours", 2, 1.5f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Current Runtime : " + (SYSTEM_TOTAL_RUNTIME / 1000) + " seconds", 2, 3f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("X : " + Maths.roundToTenth(Game.player.getX()) + " Y : " + Maths.roundToTenth(Game.player.getY()) + " Z : " +
                    Maths.roundToTenth(Game.player.getZ()), 2, 4.5f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Display | Width : " + Display.getWidth() + " Height : " + Display.getHeight(), 2, 6f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Entity Count : " + World.getEntities().size() + " | Sleeping : " + CollisionSandbox.SLEEPING_COLLIDERS, 2, 7.5f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Crosshair | X : " + Maths.roundToTenth(MousePicker.getX()) + "  Y : " + Maths.roundToTenth(MousePicker.getY()) +
                    "  Z : " + Maths.roundToTenth(MousePicker.getZ())  , 2, 9f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Total Vertices : " + Gfx.TOTAL_VERTS, 2, 10.5f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Loaded Chunks : " + World.getChunks().size(), 2, 12f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
            Gfx.drawString("Player rotation  x :  " + Game.player.getXRot() + "  y : " + Game.player.getYRot() + "  z : " +
                    Game.player.getZRot(), 2, 13.5f * Gfx.WIDTH * 0.01f, Gfx.WIDTH * 0.01f);
        }
    }

    public void render()
    {
        updateCycle();
        if(Settings.getBooleanSetting("showAllColourChannels"))
        {
            Gfx.drawImageFBO(0, 0, Gfx.WIDTH, Gfx.HEIGHT, channelCycle[cycleOffset % (channelCycle.length)]);
            Gfx.drawImageFBO(0, Gfx.HEIGHT - (Gfx.HEIGHT * 0.25f), Gfx.WIDTH * 0.25f, Gfx.HEIGHT * 0.25f, channelCycle[(cycleOffset + 1) % (channelCycle.length)]);
            Gfx.drawImageFBO(Gfx.WIDTH - (Gfx.WIDTH * 0.25f), Gfx.HEIGHT - (Gfx.HEIGHT * 0.25f), Gfx.WIDTH * 0.25f, Gfx.HEIGHT * 0.25f, channelCycle[(cycleOffset + 2) % (channelCycle.length)]);
            Gfx.drawImageFBO(Gfx.WIDTH / 2, Gfx.HEIGHT - (Gfx.HEIGHT * 0.25f), Gfx.WIDTH * 0.25f, Gfx.HEIGHT * 0.25f, channelCycle[(cycleOffset + 3) % (channelCycle.length)]);
            Gfx.drawImageFBO(Gfx.WIDTH / 4, Gfx.HEIGHT - (Gfx.HEIGHT * 0.25f), Gfx.WIDTH * 0.25f, Gfx.HEIGHT * 0.25f, channelCycle[(cycleOffset + 4) % (channelCycle.length)]);
        }
        runDebugMenu();
    }

    boolean leftWasDown;
    boolean rightWasDown;
    private void updateCycle()
    {
        channelCycle[0] = Gfx.COLOUR_FBO.getTexture();
        channelCycle[1] = Gfx.DEPTH_FBO.getTexture();
        channelCycle[2] = Gfx.NORMALS_FBO.getTexture();
        channelCycle[3] = Gfx.POSITIONS_FBO.getTexture();
        channelCycle[4] = Gfx.TO_CAMERA_FBO.getTexture();
        int dir = 0;
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !leftWasDown)
        {
            dir--;
            leftWasDown = true;
        }
        else if(!Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {
            leftWasDown = false;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !rightWasDown)
        {
            rightWasDown = true;
            dir++;
        }
        else if(!Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {
            rightWasDown = false;
        }
        cycleOffset += dir;
        if(cycleOffset < 0)
        {
            cycleOffset = channelCycle.length - 1;
        }
    }
}
