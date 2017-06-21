package engine.component.entity.items.technology.radio;

import engine.component.Component;
import engine.component.WorldComponent;
import engine.component.graphical.ModelComponent;
import engine.entity.Entity;
import tools.Maths;

/**
 * Created by Owner on 5/28/2017.
 */
public class RadioBlueprint
{

    public static Entity createRadio(float x, float y, float z)
    {
        Entity radio = new Entity();
        WorldComponent wc = new WorldComponent();
        ModelComponent mc = new ModelComponent("medium_grey_rock");
        float scale = Maths.randomFloat(0.7f, 1.4f);
        wc.set(x, y, z, 0, Maths.randomFloat(0, 360), 0, scale, scale, scale, 0, 0, 0);
        RadioComponent rc = new RadioComponent(x, y, z);

        radio.addComponent(Component.WORLD_COMPONENT, wc);
        radio.addComponent(Component.MODEL_COMPONENT, mc);
        radio.addComponent("radio", rc);

        return radio;
    }

}
