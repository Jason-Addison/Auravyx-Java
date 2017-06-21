package engine.game;

import tools.SavableObject;
import tools.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Owner on 3/11/2017.
 */
public class Settings
{
    public static void loadDefaults()
    {
        Game.settings.put("showAllColourChannels", "false");
        Game.settings.put("viewDistance", "15000");
        Game.settings.put("debugMode", "false");
        Game.settings.put("userName", "testuser");
        Game.settings.put("passWord", "testpass");


        Game.settings.put("editorXAxisVisible", "true");
        Game.settings.put("editorYAxisVisible", "false");
        Game.settings.put("editorZAxisVisible", "true");
        loadFileSettings();
        loadEditorSettings();
    }
    private static void loadEditorSettings()
    {
        ArrayList<SavableObject> settings  = Utilities.readSavableObject("C:/Sandbox/" + "editorSettings.txt");
        for(SavableObject setting : settings)
        {
            if(Game.settings.get(setting.getName()) != null)
            {
                Game.settings.replace(setting.getName(), setting.getValue());
            }
            else
            {
                Game.settings.put(setting.getName(), setting.getValue());
            }
        }
    }
    public static void loadFileSettings()
    {
        ArrayList<SavableObject> settings  = Utilities.readSavableObject(Game.GAME_DIR + "settings.txt");
        for(SavableObject setting : settings)
        {
            if(Game.settings.get(setting.getName()) != null)
            {
                Game.settings.replace(setting.getName(), setting.getValue());
            }
            else
            {
                Game.settings.put(setting.getName(), setting.getValue());
            }
        }
    }
    public static Object getSetting(String setting)
    {
        Object settingObject = null;
        try
        {
            settingObject = Game.settings.get(setting);
        }
        catch (Exception e)
        {
            System.err.println("Setting not found! " + setting);
        }
        return settingObject;
    }
    public static int getIntSetting(String setting)
    {
        return Integer.parseInt(getStringSetting(setting));
    }
    public static float getFloatSetting(String setting)
    {
        return Float.parseFloat(getStringSetting(setting));
    }
    public static double getDoubleSetting(String setting)
    {
        return Double.parseDouble(getStringSetting(setting));
    }
    public static byte getByteSetting(String setting)
    {
        return Byte.parseByte(getStringSetting(setting));
    }
    public static String getStringSetting(String setting)
    {
        return (String) getSetting(setting);
    }
    public static boolean getBooleanSetting(String setting)
    {
        return Boolean.parseBoolean(getStringSetting(setting));
    }
    public static void setSetting(String setting, Object value)
    {
        Game.settings.put(setting, value + "");
    }
    public static void saveAllSettings()
    {
        HashMap<String, String> settings = Game.settings;

        String settingsFile = "#Settings   " + Utilities.getDate() + System.lineSeparator() + System.lineSeparator();

        for(String setting : settings.keySet())
        {
            settingsFile = settingsFile + setting + "=" + settings.get(setting) + System.lineSeparator();
        }

        Utilities.writeStringToFile(Game.GAME_DIR + "settings.txt", settingsFile);
    }
}
