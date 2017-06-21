package engine.world;

import engine.graphics.model.Model;
import engine.world.effects.Skydome;

/**
 * Created by Owner on 4/1/2017.
 */
public class Time
{

    Model sun;

    Model skyXP;
    //Model skyXN;
    Model skyZP;
    Model skyZN;

    Skydome skydome = new Skydome();
    public Time()
    {
        float[] quadVertices = {-1, -1, -1, 1, 1, -1, 1, 1, 1, -1, -1, 1};
        float[] skyXNcoords = {1, -1, -1, -1, 1, 1, -1, 1, 1, 1, -1, -1};
        float[] sunPositions = {-1, -1, -1, -1, 1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, 1, -1};
        //sun = Loader.load3DModel(sunPositions, null, null, quadVertices);
        //sun.setTexture(Game.getTexture("yaxis"));
        float[] skyXNvert = {-1, -1, -1, -1, -1, 1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, 1};
        //skyXN = Loader.load3DModel(sunPositions, null, null);
        //skyXN.setTexture(Game.getTexture("yaxis"));

       /*float[] skyXPcoords = {-1, -1, 1, -1, -1, 1, 1, 1, -1, 1, 1, -1};
        float[] skyXPvert = {1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, -1, 1};
        skyXP = Loader.load3DModel(skyXPvert, null, null, skyXPcoords);
        skyXP.setTeFxture(Game.getTexture("zaxis"));

        float[] skyZPcoords = {-1, -1, 1, -1, -1, 1, 1, 1, -1, 1, 1, -1};
        float[] skyZPvert = {1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, -1, 1};
        skyZP = Loader.load3DModel(skyXPvert, null, null, skyXPcoords);
        skyZP.setTexture(Game.getTexture("zaxis"));*/
        skydome.createDome();
    }
    public void update()
    {
        skydome.render();
        //PostProcess.modelRenderer.loadProjectionMatrix(Renderer.projectionMatrix);
        //PostProcess.modelRenderer.render(sun, 0, 0, 0, 0, 0, 0, 1, 1, 1);
        //PostProcess.modelRenderer.render(sun, 0, 0, 0, 0, 0, 0, 1, 1, 1);
        //PostProcess.modelRenderer.render(sun, 0, 0, 0, 0, 0, 0, 1, 1, 1);
        //PostProcess.modelRenderer.render(sun, 0, 0, 0, 0, 0, 0, 1, 1, 1);
        //PostProcess.modelRenderer.render(sun, 0, 0, 0, 0, 0, 0, 1, 1, 1);
        //PostProcess.texturedQuadRenderer.render(skyXN, 0, 0, 0, 90, 0, 0, 1, 1, 1);
        //PostProcess.modelRenderer.render(skyXP, 0, 0, 0, 0, 5, 0, 1, 1, 1);
        //PostProcess.modelRenderer.render(, 0, 0, 0, 0, 0, 0, 1, 1, 1);

    }

}
