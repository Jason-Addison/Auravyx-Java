package engine.graphics.particle;

import tools.Maths;

/**
 * Created by Owner on 5/28/2017.
 */
public class Emitter
{

    private float dieOff = 1000;
    private float randomnessInMillis = 1000;

    private float rate = 1000;
    private float rateRandomness = 1000;
    private float nextSpawn = 1000;

    private long timeSinceLastParticle = System.currentTimeMillis();
    public Emitter()
    {

    }

    public void setRate(float millis, float randomnessInMillis)
    {
        /*
               millis : time before next particle.

         */
        this.rate = millis;
        this.rateRandomness = randomnessInMillis;

        if(randomnessInMillis != 0)
        {
            float random = Maths.randomFloat(0, randomnessInMillis);
        }
        else
        {
            this.nextSpawn = millis;
        }
    }

    public void setDieOff(float millis, float randomnessInMillis)
    {
        /*
             Millis : time in milliseconds for particle to despawn / began fade.
             randomnessInMillis : positive and negative randomness offset in millis;
         */
        this.dieOff = millis;
        this.randomnessInMillis = randomnessInMillis;
    }

    public void emit()
    {
        if(System.currentTimeMillis() > timeSinceLastParticle + dieOff)
        {

        }
    }

}
