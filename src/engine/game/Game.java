package engine.game;

import engine.audio.Audio;
import engine.display.DisplayManager;
import engine.entity.EntityData;
import engine.graphics.model.Model;
import entities.Player;
import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

/**
 * Created by Owner on 3/7/2017.
 */
public class Game
{

    public static String GAME_DIR = "C:\\Sandbox\\Sandbox\\";
    public static final String VERSION = "Alpha 1.1.0";
    private static byte currentState = 2;
    public static HashMap<String, String> settings = new HashMap<>();
    public static HashMap<String, EntityData> entities = new HashMap<>();
    public static HashMap<String, Texture> textures = new HashMap<>();
    public static HashMap<String, Integer> audio = new HashMap<>();
    public static HashMap<String, Model> models = new HashMap<>();
    public static HashMap<String, Model> texturesAdvanced = new HashMap<>();
    public static void setState(byte stateID)
    {
        currentState = stateID;
    }
    public static byte getCurrentState()
    {
        return currentState;
    }
    public static float getDelta()
    {
        return DisplayManager.getDelta() * 1;
    }
    public static Player player = new Player();
    public static boolean closeRequested = false;

    public static void onClose()
    {
        System.out.println("Attempting cleanup...");
        Settings.saveAllSettings();
        DisplayManager.closeDisplay();
        Audio.clean();
        System.out.println("Cleanup complete. Closing display...");
        DisplayManager.frame.dispose();
        System.out.println("Closed.");
    }
    public static Texture getFullTexture(String texture)
    {
        if(textures.get(texture) != null)
        {
            return textures.get(texture);
        }
        else
        {
            System.err.println("There was a problem getting texture : \"" + texture + "\" because it does not exist! ---- Whole texture ----");
        }
        return null;
    }
    public static int getTexture(String texture)
    {
        if(textures.get(texture) != null)
        {
            return textures.get(texture).getTextureID();
        }
        else
        {
            System.err.println("There was a problem getting texture : \"" + texture + "\" because it does not exist!");
        }
        return 0;
    }
    public static Model getModel(String name)
    {
        return models.get(name);
    }
    public static void addModel(Model model, String name)
    {
        models.put(name, model);
    }

    public static void addAudio(int id, String name)
    {
        audio.put(name, id);
    }
    public static int getAudio(String name)
    {
        return audio.get(name);
    }
}
