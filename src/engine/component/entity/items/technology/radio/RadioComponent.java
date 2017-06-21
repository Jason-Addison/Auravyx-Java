package engine.component.entity.items.technology.radio;

import engine.audio.Source;
import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import engine.game.Game;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

/**
 * Created by Owner on 5/28/2017.
 */
public class RadioComponent extends Component
{

    Source source;
    float volume = 10;
    public RadioComponent(float x, float y, float z)
    {
        source = new Source();
        source.setEquation(1, 2, volume);
        //source.play(Game.getAudio("cessna_outside"));
        source.play(Game.getAudio("rainbow"));
        source.setPitch(Maths.randomFloat(0.7f, 1.3f));
        source.setPitch(0.96f);
        source.setGain(1);
        setSource(x, y, z);
    }

    boolean e = true;
    boolean f = false;
    float meme = 0.8f;
    long last = System.currentTimeMillis();
    public void update(ComponentManager manager)
    {
        //meme += 0.0001f;
        //source.setPitch(Maths.randomFloat(0.1f, 1.9f));

        source.update();
        WorldComponent wc = ((WorldComponent) manager.get(Component.WORLD_COMPONENT));

        if(System.currentTimeMillis() > last + 3000)
        {
            last = System.currentTimeMillis();
            e = !e;
            if(e)
            {
               // wc.setVelocity(-1 * 20, 0, 0);
            }
            else
            {
                //wc.setVelocity(1 * 20, 0, 0);
            }
            //wc.setVelocity(Maths.randomFloat(0, 10) - 5, Maths.randomFloat(0, 10) - 5, Maths.randomFloat(0, 10) - 5);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_INSERT))
        {
            meme += 0.0001f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_HOME))
        {
            meme -= 0.0001f;
        }
        Vector3f position = wc.getPosition();
        //wc.setVelocity(position.x - MousePicker.getX(), position.y - MousePicker.getY(), position.z - MousePicker.getZ());
        //wc.setXYZ(MousePicker.getX(), MousePicker.getY(), MousePicker.getZ());
        Vector3f velocity = wc.getVelocity();
        source.setPosition(position.x, position.y, position.z);
        source.setVelocity(velocity.x, velocity.y, velocity.z);
    }
    public void setSource(float x, float y, float z)
    {
        source.setPosition(x, y, z);
    }

}
