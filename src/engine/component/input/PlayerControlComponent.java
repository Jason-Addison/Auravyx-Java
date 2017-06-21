package engine.component.input;

import engine.audio.Audio;
import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import engine.graphics.Camera;
import engine.graphics.Renderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Input;
import tools.Maths;

/**
 * Created by Owner on 4/24/2017.
 */
public class PlayerControlComponent extends Component
{

    float xVel, yVel, zVel;
    //float x, y, z;
    float xRot, yRot, zRot;
    boolean jumped = false;
    public void update(ComponentManager manager)
    {
        WorldComponent playerPosition = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
        calculateInput(manager);
    }
    public Camera cameraToPlayer(Camera camera)
    {
        float xO = 0;// (float) ((Math.sin(Math.toRadians(-yRot) * xRot * 0.1f))) * 10;
        float yO = 0;//(float) ((Math.sin(Math.toRadians(xRot)))) * 10;
        float zO = 0;//(float) ((Math.cos(Math.toRadians(-yRot) * xRot * 0.1f))) * 10;
        //camera.set(x + xO, y + yO, z + zO, xRot, yRot, zRot);
        return camera;
    }
    public void calculateInput(ComponentManager manager)
    {
        WorldComponent position = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
        calculateMouseMovement(position);
        calculateKeyboardInputs(position);
        calculateMovement(manager);
        Vector3f ori = Maths.rotationToNormal(xRot, yRot, zRot);

        Matrix4f viewMatrix = Maths.createViewMatrix(Renderer.getCamera());
        Vector3f forwardVec = new Vector3f(viewMatrix.m20, viewMatrix.m21, viewMatrix.m22);
        Vector3f upVec = new Vector3f(viewMatrix.m10, viewMatrix.m11, viewMatrix.m12);

        Audio.setListener(position.getX(), position.getY(), position.getZ(), xVel * speed, yVel * speed, zVel * speed, upVec.x, upVec.y, upVec.z, forwardVec.x, forwardVec.y, forwardVec.z);

    }
    private void calculateKeyboardInputs(WorldComponent worldComponent)
    {
        speed = 3;
        xVel = 0;
        yVel = 0;
        zVel = 0;
       // yVel -= World.GRAVITY / 16;
        double angle = Math.toRadians(yRot);
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            xVel += ((float) + Math.sin(angle));
            zVel -= ((float) Math.cos(angle));
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
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            yVel -= 2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {
            yVel += 2;
        }
        /*if(!jumped && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {
            jumped = true;
            yVel += 2;
        }
        else if(!Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {
            jumped = false;
        }*/
        if(Keyboard.isKeyDown(Keyboard.KEY_E))
        {
            speed *= 15;
        }
        if(Mouse.isButtonDown(Input.MOUSE_MIDDLE))
        {
            speed *= 15;
        }
        if(Mouse.isButtonDown(Input.MOUSE_RIGHT))
        {
            speed *= 2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_END))
        {
            worldComponent.setXYZ(0, 0, 0);
        }
    }
    public void calculateMouseMovement(WorldComponent wc)
    {
        yRot += (Mouse.getDX() * 0.001f * Renderer.FOV) * 1.4f;
        xRot -= (Mouse.getDY() * 0.001f * Renderer.FOV) * 1.4f;
        if(xRot > 90)
        {
            xRot = 90;
        }
        if(xRot < -90)
        {
            xRot = -90;
        }
        wc.setXRot(xRot);
        wc.setYRot(yRot);
    }
    float speed = 3;
    public void calculateMovement(ComponentManager manager)
    {
        PlayerPhysicsComponent pc = (PlayerPhysicsComponent) manager.get(Component.PHYSICS_COMPONENT);
        WorldComponent position = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
        ////x += speed * (xVel * Game.getDelta());
        ////y += speed * (yVel * Game.getDelta());
        ///z += speed * (zVel * Game.getDelta());
       // pc.update(manager, true);
        position.offset(speed * (xVel * 0.01f), speed * (yVel * 0.01f), speed * (zVel * 0.01f));
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
    public Vector3f getVelocity()
    {
        return new Vector3f(xVel, yVel, zVel);
    }
    public Vector3f getRealVelocity()
    {
        return new Vector3f(xVel * speed, yVel * speed, zVel * speed);
    }

    public void setYVel(float yVel)
    {
        this.yVel = yVel;
    }

    public float getYVel()
    {
        return yVel;
    }

    public void setVelocity(float x, float y, float z)
    {
        this.xVel = x;
        this.yVel = y;
        this.zVel = z;
    }

}
