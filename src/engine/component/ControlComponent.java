package engine.component;

import engine.physics.movement.PhysicsComponent;
import org.lwjgl.input.Keyboard;

/**
 * Created by Owner on 2/18/2017.
 */
public class ControlComponent extends Component
{

    public void update(ComponentManager manager)
    {
        PhysicsComponent pc = (PhysicsComponent) manager.get(Component.PHYSICS_COMPONENT);

        WorldComponent wc = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            wc.setX(wc.getX() + 0.01f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {
            wc.setX(wc.getX() - 0.01f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {
            wc.setZ(wc.getZ() + 0.01f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {
            wc.setZ(wc.getZ() - 0.01f);
            if(pc != null)
            {
                pc.addVelocity(0, 0, 0.1f);
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_MINUS))
        {
            wc.setYRot(wc.getYRot() - 1f);
            wc.setXRot(wc.getXRot() - 1f);
            wc.setZRot(wc.getZRot() - 1f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_O))
        {
            wc.setXRot(wc.getXRot() + 3);
            wc.setYRot(wc.getYRot() + 3);
            wc.setZRot(wc.getZRot() + 3);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))
        {
            wc.setY(wc.getY() + 0.01f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            //wc.setY(wc.getY() - 0.01f);
        }
    }

}
