package tools;

/**
 * Created by Owner on 3/12/2017.
 */
public class SavableObject
{

    String objectName;
    String objectValue;

    public SavableObject()
    {

    }

    public void setName(String name)
    {
        this.objectName = name;
    }
    public String getName()
    {
        return objectName;
    }
    public void setValue(String value)
    {
        this.objectValue = value;
    }
    public String getValue()
    {
        return objectValue;
    }

    public String toString()
    {
        return ("SavableObject[" + objectName + " : " + objectValue + "]");
    }
}
