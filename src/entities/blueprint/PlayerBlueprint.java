package entities.blueprint;

import engine.component.Component;
import engine.component.WorldComponent;
import engine.entity.Entity;

/**
 * Created by Owner on 4/24/2017.
 */
public class PlayerBlueprint extends Blueprint
{

    public PlayerBlueprint()
    {

    }

    public static Entity addBlueprintInformation(Entity e)
    {
        WorldComponent worldComponent = new WorldComponent();
        worldComponent.set(0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0);
        e.addComponent(Component.WORLD_COMPONENT, worldComponent);
        return Blueprint.addBlueprintInformation(e);
    }
}
