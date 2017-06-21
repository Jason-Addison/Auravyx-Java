package engine.component.input;

import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.physics.collision.BoundingBoxComponent;
import engine.physics.collision.CollisionComponent;
import engine.physics.collision.CollisionSandbox;
import engine.physics.movement.PhysicsComponent;
import engine.world.World;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

/**
 * Created by Owner on 6/11/2017.
 */
public class PlayerPhysicsComponent extends PhysicsComponent
{
    ComponentManager manager;
    private float MAX_SLOPE = 0.2f; // Defines point at which  the player will no longer be able to stay stable and will fall.
    public void update(ComponentManager manager, boolean t)
    {

    }
    public void update(ComponentManager manager)
    {
        //if(Keyboard.isKeyDown(Keyboard.KEY_G))

            BoundingBoxComponent obb = (BoundingBoxComponent) manager.get(Component.COLLISION_COMPONENT);
            if(obb != null)
            {
                obb.setXRot(0);
                obb.setYRot(0);
                obb.setZRot(0);
            }
            this.manager = manager;
            CollisionSandbox.testForCollision(manager, World.getEntities());
            CollisionSandbox.testForWorldCollision(manager);
            WorldComponent wc = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
            /*Vector3f impulse = null;
            if(impulses.size() > 0)
            {
                Vector3f[] array = new Vector3f[impulses.size()];
                array = impulses.toArray(array);
                impulse = Vec.average(array);
            }*/
            for(Vector3f impulse : impulses)
            {

            }
            impulses.clear();

        //CollisionResponse r = Collision.OBBtoQuad(
       //         Maths.createTransformationMatrix(new Vector3f(wc.getX(), wc.getY(), wc.getZ()), new Vector3f(0, 0, 0), 0, 0, 0, 1, 1, 1),
       //         0, 0, 0, new Vector3f[]{new Vector3f(100, 100, -100), new Vector3f(-100, 100, -100), new Vector3f(100, 0, 100), new Vector3f(-100, 0, 100)});

    }
    public void addImpulse(Vector3f impulse, ComponentManager manager)
    {
        WorldComponent wc = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
        PlayerControlComponent pc = (PlayerControlComponent) manager.get(Component.PLAYER_CONTROL_COMPONENT);
        CollisionComponent cc = (CollisionComponent) manager.get(Component.COLLISION_COMPONENT);
        Vector3f nOff = Maths.normalize(impulse);
        if(nOff.y > 0.2f && pc.getYVel() < 0)
        {
            pc.setVelocity(0, 0, 0);
            wc.offset(0, impulse.getY() * Math.abs(nOff.getY()), 0);
        }
        else
        {
            wc.offset(impulse.getX(), impulse.getY(), impulse.getZ());
        }
        //System.out.println(impulse);
        cc.update(manager);
        Camera camera = Renderer.getCamera();
        camera.set(wc.getX(), wc.getY(), wc.getZ(), wc.getXRot(), wc.getYRot(), wc.getZRot());
        Gfx.gameRenderer.setCamera(camera);
        System.out.println(impulse);
    }
    protected void offset(WorldComponent wc, float x, float y, float z)
    {
        wc.setX(wc.getX() + x);
        wc.setY(wc.getY() + y);
        wc.setZ(wc.getZ() + z);
    }
}
