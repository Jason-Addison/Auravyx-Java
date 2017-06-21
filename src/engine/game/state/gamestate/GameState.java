package engine.game.state.gamestate;

import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.ControlComponent;
import engine.component.WorldComponent;
import engine.component.entity.UpdateComponent;
import engine.component.entity.items.UpdateInterface;
import engine.component.graphical.ModelComponent;
import engine.entity.Entity;
import engine.game.Debug;
import engine.game.Game;
import engine.game.interaction.MousePicker;
import engine.game.state.State;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.graphics.gui.PlayerOverlay;
import engine.graphics.particle.ParticleBatcher;
import engine.graphics.postprocess.FBO;
import engine.graphics.postprocess.PostProcess;
import engine.physics.collision.BoundingBoxComponent;
import engine.physics.movement.PhysicsComponent;
import engine.world.World;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import tools.Input;
import tools.Maths;

import java.util.HashMap;

/**
 * Created by Owner on 2/16/2017.
 */
public class GameState extends State
{
    public static boolean PAUSE = false;
    Debug debug = new Debug();
   // Renderer renderer = new Renderer();
    EscapeMenu escapeMenu = new EscapeMenu();
    MousePicker mousePicker = new MousePicker();
    private PlayerOverlay playerOverlay = new PlayerOverlay();
    float width;
    float height;
    World world = new World();
    public GameState()
    {
        fbo = new FBO(Display.getWidth(), Display.getHeight());
        setFBO(Display.getWidth(), Display.getHeight());
        Mouse.setGrabbed(true);
        width = Gfx.WIDTH;
        height = Gfx.HEIGHT;
    }
    public void update()
    {
        escapeMenu.update();
        if(!PAUSE)
        {
            Gfx.gameRenderer.setCamera(Game.player.cameraToPlayer(Renderer.getCamera()));
            debug.update();
            HashMap<String, Entity> entities = World.getEntities();
            for(String id : entities.keySet())
            {
                entities.get(id).update();
            }
            Game.player.calculateInput();
        }
        Mouse.setGrabbed(true);
        updateFBO();
        render();
        //mousePicker.update(fbo);
        if(GameState.PAUSE)
        {
            Game.setState(State.MENU_STATE);
            GameState.PAUSE = false;
        }
        if(!PAUSE)
        {
            debug.render();
        }
    }

    FBO menuBlur = new FBO(Display.getWidth() / 4, Display.getHeight() / 4);
    FBO menuBlur2 = new FBO(Display.getWidth() / 6, Display.getHeight() / 6);
    float q = 0;
    FBO skyFBO = new FBO((int) Gfx.WIDTH, (int) Gfx.HEIGHT);

    boolean f;
    float df;
    long lastTime = System.currentTimeMillis();
    boolean test = true;
    public void render()
    {
        q+= 0.1f;
        fbo.bind();
        clear();

        world.update();
        //Renderer.billboardRenderer.render(Renderer.getCamera());
        Vector2f pos = Maths.Vec3ToVec2(Maths.createViewMatrix(Renderer.getCamera()), Renderer.projectionMatrix, new Vector3f(0, 1, 0));
        //Gfx.drawImage(pos.x * Gfx.WIDTH, pos.y * Gfx.HEIGHT, 50, 50, Game.getTexture("xaxis"));
        Gfx.gameRenderer.render(fbo);
        fbo.bind();
        ParticleBatcher.renderOver();
        updateMainFBO();////////////////////////
        //Gfx.COLOUR_FBO.fadeDistance();
        //fbo.fadeDistance();
        //System.out.println(Debug.FPS);
        fbo.unbind();

        fbo.bind();
        PostProcess.deferredRenderer.render(Gfx.COLOUR_FBO.getTexture(), Gfx.NORMALS_FBO.getTexture(), Gfx.SPECULAR_FBO.getTexture(), Gfx.GLOW_FBO.getTexture(), Gfx.POSITIONS_FBO.getTexture(), Gfx.DEPTH_FBO.getTexture(), Gfx.SETTINGS_FBO.getTexture());
        Gfx.gameRenderer.overlayRender();

        playerOverlay.render();
        fbo.bind();
        debug.render();
        fbo.unbind();

        //fbo.blur(16);
        fbo.render();

        //Gfx.COLOUR_FBO.render();
        if(PAUSE)
        {
            //escapeMenu.render();
        }
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f mouse = new Vector3f(MousePicker.getX(), MousePicker.getY(), MousePicker.getZ());
        Vector3f newMouse = new Vector3f(0, 0, 0);
        float dist = Maths.distanceBetween(origin, mouse);
        newMouse.x = mouse.x + (mouse.x - origin.x) / dist * 0.5f;
        newMouse.y = mouse.y + (mouse.y - origin.y) / dist * 0.5f;
        newMouse.z = mouse.z + (mouse.z - origin.z) / dist * 0.5f;

        Gfx.imageBatch.clear();
        if(System.currentTimeMillis() > lastTime + 100 && Mouse.isButtonDown(Input.MOUSE_MIDDLE))
        {
            lastTime = System.currentTimeMillis();
            Entity f = new Entity();
            ModelComponent mc = new ModelComponent(Game.getModel("green_cube").clone());
            f.addComponent(Component.MODEL_COMPONENT, mc);
            WorldComponent wc2 = new WorldComponent();
            wc2.set(Game.player.getX(), Game.player.getY(), Game.player.getZ(), 0, 0, 0, Maths.randomFloat(1, 5), Maths.randomFloat(1, 3), Maths.randomFloat(1, 3), 0, 0, 0);
            float amt = 10;
            Vector3f orbit = new Vector3f(Game.player.getX() + ((float) Math.sin(Game.player.getXRot()) * amt), Game.player.getY() + ((float) Math.sin(Game.player.getYRot()) * amt),
                    Game.player.getZ() + ((float) Math.sin(Game.player.getZRot()) * amt));
            wc2.set(orbit.x, orbit.y, orbit.z, Maths.randomFloat(0, 360), Maths.randomFloat(0, 360), Maths.randomFloat(0, 360), Maths.randomFloat(1, 5), Maths.randomFloat(1, 3), Maths.randomFloat(1, 3), 0, 0, 0);
            f.addComponent(Component.WORLD_COMPONENT, wc2);
            f.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
            f.addComponent(Component.COLLISION_COMPONENT, new BoundingBoxComponent());
            f.addComponent(Component.PHYSICS_COMPONENT, new PhysicsComponent());
            float ran = Maths.randomFloat(0, 20);
            if(ran < 2)
            {
                mc.setTexture(Game.getTexture("smooth_base"));
            }
            else
            {
                //System.out.println("XD");
            }
            World.addEntity(f);
        }
        Matrix4f ma = Maths.createTransformationMatrix(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 0, 45, 0, 2, 1, 1);
        if(!f && Mouse.isButtonDown(Input.MOUSE_LEFT))
        {
                float amt = 10;
                Vector3f orbit = new Vector3f(Game.player.getX() + ((float) Math.sin(Game.player.getXRot()) * amt), Game.player.getY() + ((float) Math.sin(Game.player.getYRot()) * amt + 10),
                        Game.player.getZ() + ((float) Math.sin(Game.player.getZRot()) * amt));
                orbit = new Vector3f(Game.player.getX(), Game.player.getY(), Game.player.getZ());
            //for(int i = 0; i < 100; i++)
            {
                f = true;
                //World.addEntity(RadioBlueprint.createRadio(mouse.x, mouse.y + 3, mouse.z));
                Entity f = new Entity();//CessnaBlueprint.create(Game.player.getX(), Game.player.getY(), Game.player.getZ());
                f.addComponent(Component.MODEL_COMPONENT, new ModelComponent("green_cube"));
                WorldComponent wc2 = new WorldComponent();
                wc2.set(Game.player.getX(), Game.player.getY(), Game.player.getZ(), 0, 0, 0, 1f, 1, 1f, 0, 0, 0);
                wc2.set(orbit.x, orbit.y, orbit.z, Maths.randomFloat(0, 360), Maths.randomFloat(0, 360), Maths.randomFloat(0, 360), Maths.randomFloat(1, 5), Maths.randomFloat(3, 3), Maths.randomFloat(1, 3), 0, 0, 0);
                f.addComponent(Component.WORLD_COMPONENT, wc2);
                f.addComponent(Component.UPDATE_COMPONENT, new UpdateComponent(new UpdateInterface()
                {
                    float x;
                    @Override
                    public void update(ComponentManager manager)
                    {
                        WorldComponent wc = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
                        x+= 0.01f;
                        wc.setXRot(x);
                    }
                }));
                //f.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
                f.addComponent(Component.COLLISION_COMPONENT, new BoundingBoxComponent());
                PhysicsComponent pc = new PhysicsComponent();
                pc.setAsStaticCollider(true);
                f.addComponent(Component.PHYSICS_COMPONENT, pc);
                //pc.addVelocity((float) (Math.cos(Math.toRadians(Game.player.getXRot()))) * amt, (float) (Math.sin(Math.toRadians(Game.player.getYRot()))) * amt, (float) (Math.sin(Math.toRadians(Game.player.getZRot())) * amt));
                World.addEntity(f);
            }
        }
        else if(!Mouse.isButtonDown(Input.MOUSE_LEFT))
        {
            f = false;
        }
        if(System.currentTimeMillis() > Debug.SYSTEM_START_TIME + 100 && !true)
        {
            test = false;
            float amt = 10;
            Vector3f orbit = new Vector3f(0 + ((float) Math.sin(0) * amt), 100 + ((float) Math.sin(0) * amt + 10),
                    0 + ((float) Math.sin(0) * amt));
            for(int i = 0; i < 500; i++)
            {
                f = true;
                //World.addEntity(RadioBlueprint.createRadio(mouse.x, mouse.y + 3, mouse.z));
                Entity f = new Entity();
                f.addComponent(Component.MODEL_COMPONENT, new ModelComponent("green_cube"));
                WorldComponent wc2 = new WorldComponent();
                wc2.set(Game.player.getX(), Game.player.getY(), Game.player.getZ(), 0, 0, 0, 1.7f, 2, 1.7f, 0, 0, 0);
                wc2.set(orbit.x, orbit.y, orbit.z, Maths.randomFloat(0, 360), Maths.randomFloat(0, 360), Maths.randomFloat(0, 360), Maths.randomFloat(1, 5), Maths.randomFloat(1, 3), Maths.randomFloat(1, 3), 0, 0, 0);
                f.addComponent(Component.WORLD_COMPONENT, wc2);
                f.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
                //f.addComponent(Component.COLLISION_COMPONENT, new BoundingBoxComponent());
                f.addComponent(Component.PHYSICS_COMPONENT, new PhysicsComponent());
                World.addEntity(f);
            }
        }
        //Gfx.NORMALS_FBO.render();
        if(Mouse.isButtonDown(0) && e&& false)
        {
            //e = false;zg
            Entity e = new Entity();
            WorldComponent wc = new WorldComponent();
            //wc.set(mousePicker.getX(), mousePicker.getY(), mousePicker.getZ(), 0, 0, 0, 1, 10, 1);
            e.addComponent("position", wc);
            //World.addEntity(e);
            //for(int i = (int) Game.player.getY() - 10; i < (int) Game.player.getY() + 10; i++)
            {
               // World.setVoxel(mousePicker.getClosestVoxel().getX(), mousePicker.getClosestVoxel().getY(), mousePicker.getClosestVoxel().getZ(), 0);

            }
        }
        if(Mouse.isButtonDown(1) && true)
        {
            for(int i = (int) Game.player.getX(); i < (int) Game.player.getX() + 10; i++)
            {
                //World.setVoxel(i, Game.player.getY(), Game.player.getZ(), 1);
            }
           // World.setVoxel((int) MousePicker.getX() + 1, (int)(MousePicker.getY() + 0f), (int) MousePicker.getZ() + 1, 0);
            //World.setVoxel(Game.player.getX(), Game.player.getY(), Game.player.getZ(), 0);
        }
        else if(!Mouse.isButtonDown(0))
        {
            e = true;
        }
    }
    public void updateMainFBO()
    {
        if(width != Gfx.WIDTH || height != Gfx.HEIGHT)
        {
            width = Gfx.WIDTH;
            height = Gfx.HEIGHT;
            Gfx.COLOUR_FBO.updateFBO();
            Gfx.NORMALS_FBO.updateFBO();
            Gfx.POSITIONS_FBO.updateFBO();
            Gfx.DEPTH_FBO.updateFBO();
            Gfx.TO_CAMERA_FBO.updateFBO();
            Gfx.SPECULAR_FBO.updateFBO();
            Gfx.GLOW_FBO.updateFBO();
            Gfx.SETTINGS_FBO.updateFBO();
            skyFBO.updateFBO();
            ParticleBatcher.fireFBO.updateFBO();
            setFBO((int) Gfx.WIDTH * 1, (int) Gfx.HEIGHT * 1);
        }
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT0, Gfx.COLOUR_FBO);
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT1, Gfx.NORMALS_FBO);
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT2, Gfx.POSITIONS_FBO);
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT3, Gfx.DEPTH_FBO);
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT4, Gfx.TO_CAMERA_FBO);
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT5, Gfx.SPECULAR_FBO);
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT6, Gfx.GLOW_FBO);
        fbo.resolve(GL30.GL_COLOR_ATTACHMENT7, Gfx.SETTINGS_FBO);
        Gfx.DEPTH_FBO.visualizeDepth();
    }
    public void stateChanged()
    {
        escapeMenu.stateChanged();
    }
    boolean e = true;
}
