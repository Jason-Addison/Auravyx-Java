package engine.entity;

import tools.SavableObject;
import tools.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 3/25/2017.
 */
public class EntityLoader
{

    public static void loadEntity(String fileName)
    {
        Map<String, SavableObject> dataMap = new HashMap<>();
        ArrayList<SavableObject> data = Utilities.readSavableObject(fileName);
        for(SavableObject object : data)
        {
            dataMap.put(object.getName(), object);
        }
        EntityData entityData;
        String name = getName(dataMap);
        String className = getClassName(dataMap);
        float[] vertices = getVertices(dataMap);
    }

    private static String getName(Map<String, SavableObject> data)
    {
        if(data.get("name") != null)
        {
            return data.get("name").getName();
        }
        return "no name";
    }
    private static String getClassName(Map<String, SavableObject> data)
    {
        if(data.get("classname") != null)
        {
            return data.get("classname").getName();
        }
        return "no classname";
    }
    private static float[] getVertices(Map<String, SavableObject> data)
    {
        if(data.get("vertices") != null)
        {
            return Utilities.stringToFloatArray(data.get("vertices").getValue());
        }
        return null;
    }
}
