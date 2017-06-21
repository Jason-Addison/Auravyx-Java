package entities.blueprint;

import engine.entity.Entity;

/**
 * Created by Owner on 4/24/2017.
 */
public class Blueprint
{

    public static final PlayerBlueprint PLAYER = new PlayerBlueprint();

    public static Entity createEntityFromBlueprint(Blueprint blueprint)
    {
        Entity entity = new Entity();
        entity = blueprint.addBlueprintInformation(entity);
        return null;
    }

    public static Entity addBlueprintInformation(Entity e)
    {
        System.err.println("Using default blueprint!");
        return null;
    }

}
