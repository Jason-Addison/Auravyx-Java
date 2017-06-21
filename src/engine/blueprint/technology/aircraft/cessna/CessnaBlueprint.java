package engine.blueprint.technology.aircraft.cessna;

import engine.component.Component;
import engine.component.WorldComponent;
import engine.component.graphical.CompanionEntity;
import engine.component.graphical.ModelComponent;
import engine.entity.Entity;

/**
 * Created by Owner on 5/31/2017.
 */
public class CessnaBlueprint
{
    public static Entity create(float x, float y, float z)
    {
        Entity entity = new Entity();
        WorldComponent wc = new WorldComponent();
        ModelComponent mc = new ModelComponent("cessna");
        wc.set(x, y, z, 90, -90, 0, 1, 1, 1, 0, 0, 0);

        Entity propeller = new Entity();
        WorldComponent wcP = new WorldComponent();
        ModelComponent mcP = new ModelComponent("cessna_propeller");
        wcP.set(x, y, z, 0, 0, 0, 1, 1, 1, 0, 0, 0);
        propeller.addComponent(Component.WORLD_COMPONENT, wcP);
        propeller.addComponent(Component.MODEL_COMPONENT, mcP);

        CessnaComponent cc = new CessnaComponent();
        CompanionEntity ce = new CompanionEntity(propeller, 2.35f, 0.75f, 0);
        entity.addComponent("cc", cc);
        entity.addComponent(Component.WORLD_COMPONENT, wc);
        entity.addComponent(Component.MODEL_COMPONENT, mc);
        entity.addComponent("propeller", ce);

        return entity;
    }
}
