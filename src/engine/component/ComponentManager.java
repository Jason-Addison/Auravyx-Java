package engine.component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Owner on 6/8/2017.
 */
public class ComponentManager
{
    private LinkedHashMap<String, ArrayList<Component>> components = new LinkedHashMap<>();

    public Component get(String name)
    {
        if(components.get(name) != null && components.get(name).size() > 0)
        {
            return components.get(name).get(0);
        }
        return null;
    }
    public <T extends Component> Component getComponent(Class<T> type, String name)
    {
        return (T) components.get(name).get(0);
    }
    public <T extends ArrayList<Component>> ArrayList<Component> getComponentList(Class<T> type, String name)
    {
        return (T) components.get(name);
    }
    public void addComponent(String name, Component component)
    {
        if(components.get(name) != null)
        {
            components.get(name).add(component);
        }
        else
        {
            ArrayList<Component> list = new ArrayList<Component>();
            list.add(component);
            components.put(name, list);
        }
    }
    public void addComponents(String name, ArrayList<Component> component)
    {
        components.put(name, component);
    }

    public ArrayList<Component> getComponents(String name)
    {
        return components.get(name);
    }
    public LinkedHashMap<String, ArrayList<Component>> getAllComponentsAsMap()
    {
        return components;
    }

    public void removeComponent(String name)
    {
        components.remove(name);
    }

}
