package engine.entity;

import engine.component.Component;
import engine.component.WorldComponent;
import engine.component.graphical.ModelComponent;
import engine.game.Game;
import engine.game.Settings;
import engine.graphics.Camera;
import engine.graphics.Gfx;
import engine.graphics.Renderer;
import engine.graphics.model.Model;
import engine.world.World;
import entities.entityEditor.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import tools.OBJMaterial;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Owner on 2/15/2017.
 */
public class EntityRenderer
{

    private EntityShader shader = new EntityShader();
    //Model quad;
    public EntityRenderer()
    {
        float[] positions = Test.vert;
        //quad = Loop.mod;//Loader.load3DModel(positions, Loader.generateNormals(positions));
        shader = new EntityShader();
    }

    float meme;
    boolean e = true;
    public void render(Camera camera)
    {
        shader.start();
        shader.loadViewMatrix(camera);
        shader.loadCamera(camera);
        shader.loadFarPlane(Settings.getFloatSetting("viewDistance"));
        HashMap<String, Entity> entityList = World.getEntities();
        shader.loadEntityCount(World.getEntities().size());
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        Renderer.disableCulling();
        Gfx.wireframe(false);
        shader.loadTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE10);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Gfx.COLOUR_FBO.getTexture());
        ArrayList<Entity> entities = new ArrayList<>(entityList.values());
        ArrayList<Entity> entitiesToRender = new ArrayList<>();
        //entitiesToRender.addAll(entities);
        for(int i = 0; i < entities.size(); i++)
        {
            if(entities.get(i).getComponent(Component.MODEL_COMPONENT) != null)
            {
                if(!((ModelComponent) (entities.get(i).getComponent(Component.MODEL_COMPONENT))).getModel().hasAlpha())
                {
                    entitiesToRender.add(0, entities.get(i));
                }
                else
                {
                    entitiesToRender.add(entities.get(i));
                }
            }
        }
        //System.out.println(((ModelComponent) entitiesToRender.get(entitiesToRender.size() - 1).getComponent(Component.MODEL_COMPONENT)).getModel().hasAlpha());
        for(Entity entity : entitiesToRender)
        {
            ModelComponent modelComponent = (ModelComponent) entity.getComponent(Component.MODEL_COMPONENT);
            Model model = modelComponent.getModel();
            GL30.glBindVertexArray(model.getVaoID());
            int startingIndex = 0;
            int length = 3;
            WorldComponent worldComponent = (WorldComponent) entity.getComponent(Component.WORLD_COMPONENT);
            shader.loadTransformation(worldComponent.getAsMatrix());
            //shader.loadID(id);
            //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SblRC_ALPHA);
            prepare();
            Gfx.TOTAL_VERTS += model.getVertexCount();
            ArrayList<OBJMaterial> materialsUnsorted = model.getMaterials();
            ArrayList<OBJMaterial> materials = new ArrayList<>();
            for(int i = 0; i < materialsUnsorted.size(); i++)
            {
                if(!materialsUnsorted.get(i).hasAlpha())
                {
                    materials.add(0, materialsUnsorted.get(i));
                }
                else
                {
                    materials.add(materialsUnsorted.get(i));
                }
            }

            for(int i = 0; i < materials.size(); i++)
            {
                if(materials.get(i).hasAlpha())
                {
                    GL11.glDepthMask(false);
                    Renderer.disableCulling();
                }
                //else
                {
                    Renderer.disableCulling();
                    GL11.glDepthMask(true);
                }
                length = materials.get(i).getLength();
                GL13.glActiveTexture(GL13.GL_TEXTURE4);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, materials.get(i).getTexture());
                GL13.glActiveTexture(GL13.GL_TEXTURE1);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, Game.getTexture("normals"));
                /*GL13.glActiveTexture(GL13.GL_TEXTURE2);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, specular);
                GL13.glActiveTexture(GL13.GL_TEXTURE3);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, glow);*/

                GL11.glDrawArrays(GL11.GL_TRIANGLES, startingIndex, length);

                startingIndex += materials.get(i).getLength();
            }
        }
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        shader.stop();
        end();
    }
    public void prepare()
    {
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(3);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
       // GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
    }
    private void prepareObject(Model model)
    {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
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
