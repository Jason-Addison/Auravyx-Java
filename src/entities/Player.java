package entities;

import engine.component.Component;
import engine.component.WorldComponent;
import engine.component.input.PlayerControlComponent;
import engine.component.input.PlayerPhysicsComponent;
import engine.entity.Entity;
import engine.graphics.Camera;
import engine.physics.collision.BoundingBoxComponent;
import engine.world.World;

/**
 * Created by Owner on 2/14/2017.
 */
public class Player
{

    float x  = 0, y = 1, z = 0;
    float xRot, yRot, zRot;
    float xVel, yVel, zVel;
    Entity player = new Entity();
    public Player()
    {
        //player.addComponent(Component.MODEL_COMPONENT, new ModelComponent("green_cube"));
        WorldComponent wc = new WorldComponent();
        wc.set(0, 5, 0, 0, 0, 0, 0.5f, 0.5f, 0.5f, 0, 0, 0);
        PlayerControlComponent controller = new PlayerControlComponent();
        BoundingBoxComponent boundingBox = new BoundingBoxComponent();
        player.addComponent(Component.WORLD_COMPONENT, wc);
        player.addComponent(Component.COLLISION_COMPONENT, boundingBox);
        player.addComponent(Component.PHYSICS_COMPONENT, new PlayerPhysicsComponent());
        player.addComponent(Component.PLAYER_CONTROL_COMPONENT, controller);
        World.addEntity(player);
    }

    public Camera cameraToPlayer(Camera camera)
    {
        WorldComponent wc = (WorldComponent) player.getComponent(Component.WORLD_COMPONENT);
        camera.set(wc.getX(), wc.getY(), wc.getZ(), wc.getXRot(), wc.getYRot(), wc.getZRot());
        return camera;
    }

    public void calculateInput()
    {
        WorldComponent position = (WorldComponent) player.getComponent(Component.WORLD_COMPONENT);
       // System.out.println(position.getY());
        x = position.getX();
        y = position.getY();
        z = position.getZ();
        xRot = position.getXRot();
        yRot = position.getYRot();
        zRot = position.getZRot();
        /*calculateMouseMovement();
        calculateKeyboardMovement();
        calculateMovement();
        Vector3f ori = Maths.rotationToNormal(xRot, yRot, zRot);

        Matrix4f viewMatrix = Maths.createViewMatrix(Renderer.getCamera());
        Vector3f forwardVec = new Vector3f(viewMatrix.m20, viewMatrix.m21, viewMatrix.m22);
        Vector3f upVec = new Vector3f(viewMatrix.m10, viewMatrix.m11, viewMatrix.m12);

        Audio.setListener(x, y, z, xVel * speed, yVel * speed, zVel * speed, upVec.x, upVec.y, upVec.z, forwardVec.x, forwardVec.y, forwardVec.z);*/
    }

    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getZ()
    {
        return z;
    }
    public float getXRot()
    {
        return xRot;
    }
    public float getYRot()
    {
        return yRot;
    }
    public float getZRot()
    {
        return zRot;
    }
    public Entity getPlayer()
    {
        return player;
    }
}
