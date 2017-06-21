package engine.entity;

/**
 * Created by Owner on 3/11/2017.
 */
public class ID
{
    private static int idIterator = 0;
    public static int generateID()
    {
        int newID = idIterator;
        idIterator++;
        return newID;
    }

}
