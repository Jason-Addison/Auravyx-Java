package engine.world.effects;

import engine.Loader;
import engine.game.Game;
import engine.graphics.model.Model;
import engine.graphics.postprocess.PostProcess;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;
import tools.Utilities;

import java.util.ArrayList;

/**
 * Created by Owner on 4/1/2017.
 */
public class Skydome
{


    public static Model model;
    public Skydome()
    {

    }

    public void render()
    {
        PostProcess.modelRenderer.render(model, 0, 0, 0, 0f, 0, 0, 1, 1, 1);

       // PostProcess.modelRenderer.render(model, 0, 0, 0, 0f, 0, 0, 1, 1, 1);
    }

    public void createDome()
    {
        ArrayList<SkyLevel> skyLevels = new ArrayList<>();

        float brighten = 1.4f;
        skyLevels.add(new SkyLevel(0.3f * brighten, 0.32f * brighten, 0.47f * brighten, 0.9f));
        //skyLevels.add(new SkyLevel(0.1f, 0.32f, 0.47f, 0.9f));
       // skyLevels.add(new SkyLevel(0.1f, 0.48f, 0.65f, 0.88f));
        //skyLevels.add(new SkyLevel(5f, 0.99f, 0.99f, 0.99f));
        skyLevels.add(new SkyLevel(1.7f * brighten, 0.99f * brighten, 0.99f * brighten, 0.99f));
        String vertexData = Utilities.readStringFromFile(Game.GAME_DIR + "skydome.raw");
        float[] vertices = Utilities.stringToFloatArray(vertexData);

        float[] colours = new float[vertices.length];
        for(int i = 0; i < vertices.length / 3; i++)
        {
            float dist = Maths.distanceBetween(new Vector3f(0, 1,  0), new Vector3f((vertices[i * 3 + 0]), (vertices[i * 3 + 1]), (vertices[i * 3 + 2])));

            float r = 0.5f;
            float g = 0.5f;
            float b = 1f;

            float min = Float.MAX_VALUE;
            float max = Float.MIN_VALUE;
            SkyLevel minSky = null;
            SkyLevel maxSky = null;

            for(SkyLevel skyLevel : skyLevels)
            {
                if(dist < min)
                {
                    min = dist;
                    minSky = skyLevel;
                }
                else if(dist > max)
                {
                    max = dist;
                    maxSky = skyLevel;
                }
            }
            if(minSky == null)
            {
                minSky = maxSky;
            }
            if(maxSky == null)
            {
                maxSky = minSky;
            }

            Vector3f colourMin = new Vector3f(minSky.getR(), minSky.getG(), minSky.getB());
            Vector3f colourMax = new Vector3f(maxSky.getR(), maxSky.getG(), maxSky.getB());
            r = Maths.lerp(colourMin.x, colourMax.x, 0.6f * dist / (maxSky.getDistance() - minSky.getDistance()));
            g = Maths.lerp(colourMin.y, colourMax.y, 0.6f * dist / (maxSky.getDistance() - minSky.getDistance()));
            b = Maths.lerp(colourMin.z, colourMax.z, 0.6f * dist / (maxSky.getDistance() - minSky.getDistance()));

            colours[i * 3 + 0] = r;
            colours[i * 3 + 1] = g;
            colours[i * 3 + 2] = b;
        }
        model = Loader.load3DModel(vertices, colours, null);
    }

}
