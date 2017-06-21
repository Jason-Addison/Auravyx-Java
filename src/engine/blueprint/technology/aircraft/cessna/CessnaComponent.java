package engine.blueprint.technology.aircraft.cessna;

import engine.audio.Source;
import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import engine.component.graphical.CompanionEntity;
import engine.entity.Entity;
import engine.game.Game;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import tools.vector.Vec;

/**
 * Created by Owner on 5/31/2017.
 */
public class CessnaComponent extends Component
{

    float amt = 0.1f;
    Source source = new Source();
    public CessnaComponent()
    {
        source.play(Game.getAudio("star"));
        source.setEquation(50, 2, 1000);
        source.setGain(100);
        source.setPosition(100, 0, 0);
    }
    public void update(ComponentManager manager)
    {
        Entity propeller = ((CompanionEntity) manager.get("propeller")).getEntity();
        WorldComponent wc = (WorldComponent) propeller.getComponent(Component.WORLD_COMPONENT);
        wc.setXRot(wc.getXRot() + amt);
        amt += 0.01f;
        //source.setPitch(amt * 0.01f);

        WorldComponent plane = (WorldComponent) manager.get(Component.WORLD_COMPONENT);

        Vector3f dir = Vec.angleToDirection(plane.getXRot(), plane.getYRot(), plane.getZRot());
        //dir = Maths.normalize(dir);
        float inc = 0.1f;
        //plane.setXYZ(plane.getX() + (dir.x) * inc, plane.getY() + (dir.y) * inc, plane.getZ() + (dir.z) * inc);
        double angle = Math.toRadians(Game.player.getYRot());
        float xVel = 0; float yVel = 0; float zVel = 0;
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            xVel += ((float) + Math.sin(angle));
            yVel += ((float) + Math.sin(angle));
            //zVel -= ((float) Math.cos(angle));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            xVel += ((float) Math.cos(angle));
            zVel += ((float) Math.sin(angle));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            xVel -= ((float) Math.cos(angle));
            zVel -= ((float) Math.sin(angle));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            xVel -= ((float) Math.sin(angle));
            zVel += ((float) + Math.cos(angle));
        }
        xVel *= inc;
        yVel *= inc;
        zVel *= inc;
        //plane.setXRot(-Game.player.getXRot());
        //plane.setYRot(-Game.player.getYRot());
        //plane.setZRot(-Game.player.getZRot());
        plane.setXYZ(plane.getX() + xVel, plane.getY() + yVel, plane.getZ() + zVel);
    }

}
