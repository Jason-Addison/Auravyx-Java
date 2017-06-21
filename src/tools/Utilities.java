package tools;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Owner on 2/12/2017.
 */
public class Utilities
{

    public static void writeStringToFile(String dir, String data)
    {
        File file = new File(dir);
        try (FileOutputStream fop = new FileOutputStream(file)) {

            if (!file.exists())
            {
                file.createNewFile();
            }

            byte[] contentInBytes = data.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static String readStringFromFile(String fileName)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();
            br = null;
            return sb.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<String> readFileByLines(String fileName)
    {
        try
        {
            ArrayList<String> strings = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null)
            {
                strings.add(line);
                line = br.readLine();
            }
            br.close();
            return strings;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static void createFolder(String dir)
    {
        File file = new File(dir);

        if(!file.exists())
        {
            if(file.mkdir())
            {

            }
            else
            {
                System.err.println("Failed to create folder : " + dir);
            }
        }
    }
    public static ArrayList<SavableObject> readSavableObject(String dir)
    {
        ArrayList<String> strings = readFileByLines(dir);
        ArrayList<SavableObject> objects = new ArrayList<>();

        for(String s : strings)
        {
            if(s.startsWith("#"))
            {
                continue;
            }
            try
            {
                String[] split = s.split("=");
                SavableObject object = new SavableObject();
                object.setName(split[0]);
                object.setValue(split[1]);
                objects.add(object);
            }
            catch(Exception e)
            {

            }
        }
        return objects;
    }
    public static String getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static float[] stringToFloatArray(String string)
    {
        String[] floatValues = string.split(" ");
        float[] floats = new float[floatValues.length];
        for(int i = 0; i < floats.length; i++)
        {
            floats[i] = Float.parseFloat(floatValues[i]);
        }
        return floats;
    }
    public static float[] floatListToArray(List<Float> floatArray)
    {
        float[] floats = new float[floatArray.size()];
        for(int i = 0; i < floatArray.size(); i++)
        {
            floats[i] = floatArray.get(i);
        }
        return floats;
    }
    public static String stringArrayToString(String[] strings)
    {
        String string = "";
        for(int i = 0; i < strings.length; i++)
        {
            string = string + strings[i];
        }
        return string;
    }

    public static int findClosestValueFromArray(int value, int[] array)
    {
        int closest = Integer.MAX_VALUE;
        int closestArrValue = 333333;

        for(int i = 0; i < array.length; i++)
        {
            if(Math.abs(value - array[i]) < closest)
            {
                closest = Math.abs(value - array[i]);
                closestArrValue = array[i];
            }

        }
        return closestArrValue;
    }
    public static byte[] shortToBytes(short s)
    {
        byte[] arr = new byte[2];
        arr[0] = (byte)(s & 0xff);
        arr[1] = (byte)((s >> 8) & 0xff);
        return arr;
    }
    public static byte[] combineArray(byte[] base, byte[] added, int offset)
    {
        for(int i = 0; i < added.length; i++)
        {
            base[i + offset] = added[i];
        }
        return base;
    }

}
