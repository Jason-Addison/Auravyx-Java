package engine.game.interaction;

import engine.game.Settings;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.graphics.postprocess.FBO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;
import tools.vector.Vec3i;

/**
 * Created by Owner on 3/4/2017.
 */
public class MousePicker
{
    static float x;
    static float y;
    static float z;
    static int id;
    Vec3i closestVoxel;
    public void update(FBO fbo)
    {
        Vector3f colour = Gfx.POSITIONS_FBO.getColour((int) (Gfx.WIDTH / 2), (int) (Gfx.HEIGHT / 2));
        x = ((colour.getX() * Settings.getIntSetting("viewDistance")) - (Settings.getIntSetting("viewDistance") / 2)
                + Renderer.getCamera().getX());
        y = ((colour.getY() * Settings.getIntSetting("viewDistance")) - (Settings.getIntSetting("viewDistance") / 2)
                + Renderer.getCamera().getY());
        z = ((colour.getZ() * Settings.getIntSetting("viewDistance")) - (Settings.getIntSetting("viewDistance") / 2)
                + Renderer.getCamera().getZ());
        updateID(fbo);
        calculateVoxel();
    }
    private void calculateVoxel()
    {
        float mouseVoxX =  (x);
        float mouseVoxY =  (y);
        float mouseVoxZ =  (z);

        float closestVoxel = Float.MAX_VALUE;
        Vector3f closestVec = new Vector3f(mouseVoxX, mouseVoxY, mouseVoxZ);
        if(Maths.distanceBetween(new Vector3f(mouseVoxX + 1 + 0.5f, mouseVoxY - 0.5f, mouseVoxZ + 0.5f), new Vector3f(x, y, z)) < closestVoxel)
        {
            closestVec = new Vector3f(mouseVoxX + 1, mouseVoxY, mouseVoxZ);
            closestVoxel = Maths.distanceBetween(new Vector3f(mouseVoxX + 1 + 0.5f, mouseVoxY - 0.5f, mouseVoxZ + 0.5f), new Vector3f(x, y, z));
        }
        if(Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f - 1, mouseVoxY - 0.5f, mouseVoxZ + 0.5f), new Vector3f(x, y, z)) < closestVoxel)
        {
            closestVec = new Vector3f(mouseVoxX - 1, mouseVoxY, mouseVoxZ);
            closestVoxel = Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f - 1, mouseVoxY - 0.5f, mouseVoxZ + 0.5f), new Vector3f(x, y, z));
        }
        if(Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY + 1 - 0.5f, mouseVoxZ + 0.5f), new Vector3f(x, y, z)) < closestVoxel)
        {
            closestVec = new Vector3f(mouseVoxX, mouseVoxY + 1, mouseVoxZ);
            closestVoxel = Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY + 1 - 0.5f, mouseVoxZ + 0.5f), new Vector3f(x, y, z));
        }
        if(Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY - 0.5f - 1, mouseVoxZ + 0.5f), new Vector3f(x, y, z)) < closestVoxel)
        {
            closestVec = new Vector3f(mouseVoxX, mouseVoxY - 1, mouseVoxZ);
            closestVoxel = Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY - 0.5f - 1, mouseVoxZ + 0.5f), new Vector3f(x, y, z));
        }
        if(Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY - 0.5f, mouseVoxZ + 0.5f + 1), new Vector3f(x, y, z)) < closestVoxel)
        {
            closestVec = new Vector3f(mouseVoxX, mouseVoxY, mouseVoxZ + 1);
            closestVoxel = Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY - 0.5f, mouseVoxZ + 0.5f + 1), new Vector3f(x, y, z));
        }
        if(Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY - 0.5f, mouseVoxZ - 1 + 0.5f), new Vector3f(x, y, z)) < closestVoxel)
        {
            closestVec = new Vector3f(mouseVoxX + 1, mouseVoxY + 1, mouseVoxZ + 1);
            closestVoxel = Maths.distanceBetween(new Vector3f(mouseVoxX + 0.5f, mouseVoxY - 0.5f, mouseVoxZ - 1 + 0.5f), new Vector3f(x, y, z));
        }
        Vec3i closestVoxelPos = new Vec3i((int) closestVec.getX(), (int) closestVec.getY(), (int) closestVec.getZ());
        //closestVec = new Vec3i(mouseVoxX, mouseVoxY, mouseVoxZ);
        this.closestVoxel = closestVoxelPos;
    }
    public void updateID(FBO fbo)
    {
        GL30.glClampColor(GL30.GL_CLAMP_VERTEX_COLOR, GL11.GL_FALSE);
        GL30.glClampColor(GL30.GL_CLAMP_FRAGMENT_COLOR, GL11.GL_FALSE);
        //glClampColorARB(GL_CLAMP_FRAGMENT_COLOR_ARB, GL_FALSE);
        int newID = fbo.getColourAsInt((int) (Gfx.WIDTH / 2), (int) (Gfx.HEIGHT / 2));
        //id =  (int) (newID) * World.getEntities().size();
       // float q = 4;
       // World.removeEntity((int) (newID.x * World.getEntities().size()));
    }
    public Vec3i getClosestVoxel()
    {
        return closestVoxel;
    }

    public static float getX()
    {
        return x;
    }
    public static float getY()
    {
        return y;
    }
    public static float getZ()
    {
        return z;
    }
    public static float getID()
    {
        return id;
    }
}
