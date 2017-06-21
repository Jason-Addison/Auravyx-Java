package engine.entity;

import engine.component.Component;
import engine.component.ComponentManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Owner on 2/18/2017.
 */
public class Entity
{

    private int ID;
    ComponentManager manager = new ComponentManager();
    public Entity()
    {

    }

    public void update()
    {
        LinkedHashMap<String, ArrayList<Component>> components = manager.getAllComponentsAsMap();

        for (String key: components.keySet())
        {
            for(Component c : components.get(key))
            {
                c.update(manager);
            }
        }
    }
    public Component getComponent(String component)
    {
        return manager.get(component);
    }
    public ArrayList<Component> getComponentList(String component)
    {
        return manager.getComponents(component);
    }
    public void addComponent(String name, Component component)
    {
        manager.addComponent(name, component);
    }
    public void addComponents(String name, ArrayList<Component> component)
    {
        manager.addComponents(name, component);
    }
    public void removeComponent(String name)
    {
        manager.removeComponent(name);
    }
    public void setID(int id)
    {
        this.ID = id;
    }
    public int getID()
    {
        return ID;
    }
    public LinkedHashMap<String, ArrayList<Component>> getAllComponent()
    {
        return manager.getAllComponentsAsMap();
    }
    public <T extends ArrayList<Component>> ArrayList<Component> getComponentList(Class<T> type, String name)
    {
        return (T) manager.getComponentList(type, name);
    }
    public ComponentManager getComponentManager()
    {
        return manager;
    }
}
