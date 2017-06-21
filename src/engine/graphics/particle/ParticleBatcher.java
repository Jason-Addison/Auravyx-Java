package engine.graphics.particle;

import engine.game.Game;
import engine.graphics.Camera;
import engine.graphics.postprocess.FBO;
import engine.graphics.postprocess.PostProcess;
import org.lwjgl.opengl.Display;

/**
 * Created by Owner on 6/20/2017.
 */
public class ParticleBatcher
{

    public static FBO fireFBO = new FBO(Display.getWidth(), Display.getHeight());

    public void render(Camera camera)
    {
        renderFire(camera);
    }

    public void renderFire(Camera camera)
    {
       // fireFBO.bind();
        //fireFBO.clear();
        fireFBO.setWidth(Display.getWidth());
        fireFBO.setHeight(Display.getHeight());

        for(int i = 0; i < 1; i++)
        {
            PostProcess.oneColourPassRenderer.render(camera, fireFBO, Game.getTexture("fire2"), 0, i, 0, 1, 1, 1, 0, 0, 0);
            //PostProcess.particleRenderer.render(0, i, 0, 1, 1, 1, 0, 0, 0, Game.getTexture("fire2"));
        }
        //fireFBO.unbind();
    }

    public static void renderOver()
    {
        //Gfx.drawImageWithDepth(0, Gfx.HEIGHT, Gfx.WIDTH, -Gfx.HEIGHT, fireFBO.getTexture(), fireFBO.getDepthTexture(), Gfx.DEPTH_FBO.getDepthTexture(), GL11.GL_ONE_MINUS_SRC_ALPHA);
        //Gfx.drawImage(0, Gfx.HEIGHT, Gfx.WIDTH, -Gfx.HEIGHT, Gfx.DEPTH_FBO.getDepthTexture());

    }


}
