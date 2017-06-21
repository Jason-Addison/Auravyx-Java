package engine.graphics;

import engine.blueprint.technology.aircraft.cessna.CessnaBlueprint;
import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.ControlComponent;
import engine.component.WorldComponent;
import engine.component.entity.UpdateComponent;
import engine.component.entity.items.UpdateInterface;
import engine.component.entity.items.technology.radio.RadioBlueprint;
import engine.component.graphical.ModelComponent;
import engine.entity.Entity;
import engine.entity.EntityRenderer;
import engine.game.Game;
import engine.game.Settings;
import engine.graphics.gui.font.FontRenderer;
import engine.graphics.gui.image.ImageRenderer;
import engine.graphics.particle.ParticleBatcher;
import engine.graphics.postprocess.FBO;
import engine.graphics.postprocess.PostProcess;
import engine.graphics.postprocess.billboard.BillboardRenderer;
import engine.physics.collision.BoundingBoxComponent;
import engine.physics.movement.PhysicsComponent;
import engine.world.World;
import engine.world.terrain.TerrainRenderer;
import engine.world.voxel.VoxelRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Owner on 2/11/2017.
 */
public class Renderer
{
    public static Matrix4f projectionMatrix;
    private FontRenderer fontRenderer = new FontRenderer();
    private ImageRenderer imageRenderer = new ImageRenderer();
    private EntityRenderer entityRenderer = new EntityRenderer();
    private TerrainRenderer terrainRenderer = new TerrainRenderer();
    private VoxelRenderer voxelRenderer = new VoxelRenderer();
    private ParticleBatcher particleBatcher = new ParticleBatcher();

    public static BillboardRenderer billboardRenderer = new BillboardRenderer();
    public static float FOV = 90;
    
    static Camera MAIN_CAMERA = new Camera();
    public Renderer()
    {
        createProjectionMatrix();
        entityRenderer.loadProjectionMatrix(projectionMatrix);
        PostProcess.particleRenderer.loadProjectionMatrix(projectionMatrix);

        Entity e = new Entity();
        /*WorldComponent wc = new WorldComponent();
        wc.set(0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0,0 );
        e.addComponent(Component.WORLD_COMPONENT, wc);
        Entity q = new Entity();
        WorldComponent newC = new WorldComponent();
        newC.set(0, 0, 0, 0, 0, 0, 20, 20, 20, 0, 0, 0);
        q.addComponent(Component.WORLD_COMPONENT, newC);
        e.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
        CompanionEntity added = new CompanionEntity(q, 0, 0.01f, 5f);
        //e.addComponent("sub", added);
        ModelComponent modelComponent = new ModelComponent("high res cube");
        e.addComponent(Component.MODEL_COMPONENT, modelComponent);*/
        int index = 0;
        int xInd = 0;
        int test = 0;
        for(String model : Game.models.keySet())
        {
            for(int i = 0; i < 3; i++)
            {
                e = new Entity();
                ModelComponent modelComponent = new ModelComponent(model);
                WorldComponent wc = new WorldComponent();
                wc.set(index * 10, 50, -xInd * 10, 0, 0, 0, 1, 1, 1, 0, 0, 0);
                e.addComponent(Component.WORLD_COMPONENT, wc);
                e.addComponent(Component.MODEL_COMPONENT, modelComponent);
                e.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
                //e.addComponent(Component.PLAYER_CONTROL_COMPONENT, new PlayerControlComponent());
                World.addEntity(e);
                index++;
                if(index > 10)
                {
                    index = 0;
                    xInd++;
                }
            }
            if(test++ > 10)
            {
                break;
            }
        }
        Entity et = new Entity();
        et.addComponent(Component.MODEL_COMPONENT, new ModelComponent("big_stone_cube"));
        WorldComponent wc = new WorldComponent();
        wc.set(-105f, -5, -105f, 90, 0.0f, 0.0f, 200, 200, 5, 0, 0, 0);
        et.addComponent(Component.WORLD_COMPONENT, wc);
        et.addComponent(Component.COLLISION_COMPONENT, new BoundingBoxComponent());
        UpdateInterface updater = new UpdateInterface()
        {
            float rot;
            @Override

            public void update(ComponentManager manager)
            {
                WorldComponent wc = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
                rot += 0.1f;
                wc.setX(wc.getX() + 0.01f);
               // wc.setYRot(rot);
               // wc.setYRot((float) Math.sin(rot) * 1);
            }
        };
        et.addComponent(Component.UPDATE_COMPONENT, new UpdateComponent(updater));
        World.addEntity(et);

        {
            Entity f = new Entity();
            //f.addComponent(Component.MODEL_COMPONENT, new ModelComponent("green_cube"));
            WorldComponent wc2 = new WorldComponent();
            wc2.set(10, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0);
            f.addComponent(Component.WORLD_COMPONENT, wc2);
            //f.addComponent(Component.COLLISION_COMPONENT, new BoundingBoxComponent());
            f.addComponent(Component.PHYSICS_COMPONENT, new PhysicsComponent());
        }
        Entity f = new Entity();
        f.addComponent(Component.MODEL_COMPONENT, new ModelComponent("green_cube"));
        WorldComponent wc2 = new WorldComponent();
        wc2.set(5, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0);
        f.addComponent(Component.WORLD_COMPONENT, wc2);
        f.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
        //f.addComponent(Component.COLLISION_COMPONENT, new BoundingBoxComponent());
        f.addComponent(Component.PHYSICS_COMPONENT, new PhysicsComponent());
        //World.addEntity(f);
        Entity en = RadioBlueprint.createRadio(-50, 0, -50);

        World.addEntity(en);

        Entity plane = CessnaBlueprint.create(0, 10, 0);
        plane.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
        World.addEntity(plane);
        /*for(int x = 0; x < 150; x++)
        {
            for(int y = 0; y < 150; y++)
            {
                e = new Entity();
                wc = new WorldComponent();
                wc.set((x - 25) * 3f, 0, (y - 25) * 3, 0, 0, 0, 1, 1, 1f, 0, 0, 0);
                e.addComponent(Component.WORLD_COMPONENT, wc);
                e.addComponent(Component.KEYBOARD_CONTROL_COMPONENT, new ControlComponent());
                World.addEntity(e);
            }
        }*/
    }
    public void clear()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    public void render(FBO fbo)
    {
        Gfx.TOTAL_VERTS = 0;
        if(Display.wasResized())
        {
            update(FOV);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_5))
        {
            update(5);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_4))
        {
            update(0.1f);

        }
        voxelRenderer.render(MAIN_CAMERA);
        entityRenderer.render(MAIN_CAMERA);
        particleBatcher.render(MAIN_CAMERA);
        fbo.bind();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
    }
    public void overlayRender()
    {
        //clear();
        imageRenderer.render();
        //PostProcess.rectangleRenderer.render();

    }

    public static void enableCulling()
    {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }
    public void update(float FOV)
    {
        Renderer.FOV = FOV;
        createProjectionMatrix();
        entityRenderer.loadProjectionMatrix(projectionMatrix);
        terrainRenderer.loadProjectionMatrix(projectionMatrix);
        voxelRenderer.loadProjectionMatrix(projectionMatrix);
        PostProcess.particleRenderer.loadProjectionMatrix(projectionMatrix);
        billboardRenderer.loadProjectionMatrix(projectionMatrix);
        PostProcess.modelRenderer.loadProjectionMatrix(projectionMatrix);
        PostProcess.texturedQuadRenderer.loadProjectionMatrix(projectionMatrix);
        PostProcess.oneColourPassRenderer.loadProjectionMatrix(projectionMatrix);
    }
    public void setCamera(Camera camera)
    {
        MAIN_CAMERA.set(camera);
    }
    public static Camera getCamera()
    {
        return MAIN_CAMERA;
    }

    public static void disableCulling()
    {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
    public Matrix4f getProjectionMatrix()
    {
        return projectionMatrix;
    }
    public static void createProjectionMatrix()
    {
        projectionMatrix = new Matrix4f();
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = Settings.getFloatSetting("viewDistance") - Gfx.NEAR_PLANE;

        /*float farPlane = Settings.getFloatSetting("viewDistance");
        float l = -1, b = -1, t = 2, r = 1;
        projectionMatrix.m00 = 2 / (r - l) / 180;

        projectionMatrix.m11 = 2 / (t - b) / 180;

        projectionMatrix.m22 = -2 / (farPlane - Gfx.NEAR_PLANE);

        projectionMatrix.m30 = -(r + l) / (r - l);
        projectionMatrix.m31 = -(t + b) / (t - b);
        projectionMatrix.m32 = -(farPlane + Gfx.NEAR_PLANE) / (frustum_length);
        projectionMatrix.m33 = 1;*/
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((Settings.getFloatSetting("viewDistance") + Gfx.NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * Gfx.NEAR_PLANE * Settings.getFloatSetting("viewDistance")) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
