package engine.physics.movement;

import engine.audio.Source;
import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import engine.game.Game;
import engine.physics.collision.CollisionComponent;
import engine.physics.collision.CollisionSandbox;
import engine.world.World;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import tools.Maths;
import tools.vector.Vec;

import java.util.ArrayList;

/**
 * Created by Owner on 6/9/2017.
 */
public class PhysicsComponent extends Component
{
    protected ArrayList<Vector3f> impulses = new ArrayList<>();

    float xVel = 0;
    float yVel = 0;
    float zVel = 0;
    Source source = new Source();
    public PhysicsComponent()
    {
        //source.play(Game.getAudio("star"));
        source.setGain(10);
        timeSinceStopped = System.currentTimeMillis();
    }
    long timeSinceStopped;
    boolean countdown = false;
    boolean awake = true;
    boolean isStaticCollider = false;
    public void update(ComponentManager manager)
    {
        //isStaticCollider = false;
        if(true && !isStaticCollider)
        {
            CollisionSandbox.testForCollision(manager, World.getEntities());
            WorldComponent wc = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
            source.setPosition(wc.getX(), wc.getY(), wc.getZ());
            source.setVelocity(xVel, yVel, zVel);
           // wc.setRotation(wc.getXRot() + 1, wc.getYRot() + 0, wc.getZRot() + 0);
            yVel -= World.GRAVITY;
            //offset(wc, 0, -0.1f, 0);
            Vector3f impulse = null;
            if(impulses.size() > 0)
            {
                Vector3f[] array = new Vector3f[impulses.size()];
                array = impulses.toArray(array);
                impulse = Vec.average(array);
            }
            for(Vector3f list : impulses)
            {

            }
            if(impulse != null)
            {
                offset(wc, impulse.getX(), impulse.getY(), impulse.getZ());
                float speed = impulse.length();
                impulse = Maths.normalize(impulse);
                float dot = Vector4f.dot(new Vector4f(impulse.getX(), impulse.getY(), impulse.getZ(), 1), new Vector4f(xVel, yVel, zVel, 1));

                float amt = 2f;
                //r=d−2(d⋅n)n;
                xVel = xVel - (amt * (dot) * impulse.x * 1f);
                yVel = yVel - (amt * (dot) * impulse.y * 0.25f);
                zVel = zVel - (amt * (dot) * impulse.z * 1f);

                xVel *= 0.85f;
                zVel *= 0.85f;
            }
               // System.out.println(xVel + " " + yVel + " " + zVel);
            if(yVel < 0.1f && yVel > -0.1f)
            {
                //yVel = 0;
            }
            if(Float.isNaN(yVel))
            {
                yVel = 0;
            }
            if(Float.isNaN(xVel))
            {
                xVel = 0;
            }
            if(Float.isNaN(zVel))
            {
                zVel = 0;
            }
            if(yVel == 0 && xVel == 0 && zVel == 0)
            {
                countdown = true;
            }
            else
            {
                countdown = false;
                timeSinceStopped = System.currentTimeMillis();
            }
            if(countdown)
            {
                if(System.currentTimeMillis() > timeSinceStopped + 1000)
                {
                    sleepAll(manager);
                    awake = false;
                }
            }
            wc.setX(wc.getX() + (xVel * Game.getDelta()));
            wc.setY(wc.getY() + (yVel * Game.getDelta()));
            wc.setZ(wc.getZ() + (zVel * Game.getDelta()));
            impulses.clear();
        }
    }

    public void sleepAll(ComponentManager manager)
    {
        if(awake)
        {
            ArrayList<Component> collisionComponents = manager.getComponents(Component.COLLISION_COMPONENT);
            for(int i = 0; i < collisionComponents.size(); i++)
            {
                ((CollisionComponent) collisionComponents.get(i)).sleep();
            }
            CollisionSandbox.SLEEPING_COLLIDERS++;
        }
    }

    public void wakeAll(ComponentManager manager)
    {
        if(!awake)
        {
            ArrayList<Component> collisionComponents = manager.getComponents(Component.COLLISION_COMPONENT);
            for(int i = 0; i < collisionComponents.size(); i++)
            {
                ((CollisionComponent) collisionComponents.get(i)).wake();
            }
            CollisionSandbox.SLEEPING_COLLIDERS--;
            awake = true;
        }
    }
    protected void offset(WorldComponent wc, float x, float y, float z)
    {
        wc.setX(wc.getX() + x);
        wc.setY(wc.getY() + y);
        wc.setZ(wc.getZ() + z);
    }
    public void addImpulse(Vector3f impulse, ComponentManager manager)
    {
        impulses.add(impulse);
    }
    public void addVelocity(float x, float y, float z)
    {
        this.xVel = xVel + x;
        this.yVel = yVel + y;
        this.zVel = zVel + z;
    }
    public boolean isStaticCollider()
    {
        return isStaticCollider;
    }
    public void setAsStaticCollider(boolean flag)
    {
        this.isStaticCollider = flag;
    }
}
