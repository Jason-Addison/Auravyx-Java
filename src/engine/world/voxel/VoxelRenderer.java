package engine.world.voxel;

import engine.game.Game;
import engine.game.Settings;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.world.World;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import tools.Input;

import java.util.HashMap;

/**
 * Created by Owner on 2/15/2017.
 */
public class VoxelRenderer
{

    private VoxelShader shader = new VoxelShader();
    public VoxelRenderer()
    {
        shader = new VoxelShader();
        shader.start();
        shader.loadFarPlane(Settings.getFloatSetting("viewDistance"));
        shader.stop();
    }
    boolean qq;
    public void render(Camera camera)
    {
        //Vector3f a = new Vector3f(0, 0, 0);
        //Vector3f b = new Vector3f(1, 0, 0);
       // Vector3f c = new Vector3f(0, 0, 1);
        if(Mouse.isButtonDown(Input.MOUSE_RIGHT))
        {
            qq = false;
        }
        else
        {
            qq = true;
        }
        shader.start();
        shader.loadViewMatrix(camera);
        shader.loadCamera(camera);

        prepare();
        Renderer.disableCulling();
        HashMap<String, Chunk> chunks = World.getChunks();
        GL11.glPointSize(5);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Game.getTexture("planks"));
        GL11.glBindTexture(GL30.GL_TEXTURE_2D_ARRAY, 56);

        for(String vox : chunks.keySet())
        {

            Chunk voxel = chunks.get(vox);
            prepareChunk(voxel);
            if(qq)
            {
                prepare();
                //Renderer.enableCulling();
                //GL11.glEnable(GL11.GL_CULL_FACE);
                //GL11.glCullFace(GL11.GL_BACK);
                //Matrix4f matrix = Maths.createTransformationMatrix(new Vector3f(voxel.getX() * 16, 0, voxel.getZ() * 16),
                //        0, 0, 0, 1, 1, 1);
                //shader.loadTransformation(matrix);
                //GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
                GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
                GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, voxel.getTerrain().getMesh().getVertexCount() / 3);
                Gfx.TOTAL_VERTS += voxel.getTerrain().getMesh().getVertexCount();
                GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            }
            else //////////////// LINE
            {
                prepare();
                GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
                GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, voxel.getTerrain().getMesh().getVertexCount() / 3);
                Gfx.TOTAL_VERTS += voxel.getTerrain().getMesh().getVertexCount();
                GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            }
        }

        Renderer.disableCulling();
        shader.stop();
        end();
    }
    public void prepareChunk(Chunk chunk)
    {
        chunk.update();
        GL30.glBindVertexArray(chunk.getTerrain().getMesh().getVaoID());
    }
    public void prepare()
    {
        //terrain.getMesh().getVaoID()
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        GL20.glEnableVertexAttribArray(3);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        //GL13.glActiveTexture(GL13.GL_TEXTURE0);
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D, Loop.meme);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

       // GL13.glActiveTexture(GL13.GL_TEXTURE0);
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D, Loop.meme);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
       // GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
    }

    public void end()
    {
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    public void cleanUp()
    {
        shader.cleanUp();
    }

    public void loadProjectionMatrix(Matrix4f projectionMatrix)
    {
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
}
