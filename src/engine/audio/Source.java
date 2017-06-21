package engine.audio;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.EFX10;

import java.nio.FloatBuffer;

/**
 * Created by Owner on 5/28/2017.
 */
public class Source
{

    float gain = 1f;
    float pitch = 1f;

    float speed = 1f;
    int source;
    int audio;

    boolean looping = true;
    boolean playWhileOutOfBounds = false;
    public Source()
    {
        source = AL10.alGenSources();
        AL10.alSourcef(source, AL10.AL_ROLLOFF_FACTOR, 1);
        AL10.alSourcef(source, AL10.AL_REFERENCE_DISTANCE, 1);
        AL10.alSourcef(source, AL10.AL_MAX_DISTANCE, 100);
        //AL10.alSourcei(source, EFX10.AL_DIRECT_FILTER, Filter.LOW_PASS);
        //AL10.alSource3f(source, EFX10.AL_AUXILIARY_SEND_FILTER, Effects.REVERB, 0, Filter.LOW_PASS);
        AL10.alSourcef(source, EFX10.AL_AIR_ABSORPTION_FACTOR, 2.5f);
        AL10.alSourcef(source, EFX10.AL_CONE_OUTER_GAINHF, 0.1f);

        FloatBuffer orientation = BufferUtils.createFloatBuffer(6).put(new float[]{1, 0, 0, 0, 1, 0}); orientation.rewind();
        AL10.alSource(source, AL10.AL_ORIENTATION, orientation);

        loop(looping);
    }

    public void play(int audio)
    {
        this.audio = audio;
        AL10.alSourcef(source, AL10.AL_BUFFER, audio);
        AL10.alSourcePlay(source);
    }

    public void setPitch(float pitch)
    {
        this.pitch = pitch;
        AL10.alSourcef(source, AL10.AL_PITCH, pitch);
    }

    public void setGain(float gain)
    {
        this.gain = gain;
        AL10.alSourcef(source, AL10.AL_GAIN, gain);
    }

    public void update()
    {
        //setOffsetInSeconds(getOffsetInSeconds() + speed);
    }

    public void stop()
    {
        AL10.alSourceStop(source);
    }

    public void pause()
    {
        AL10.alSourcePause(source);
    }

    public void loop(boolean toggle)
    {
        looping = toggle;
        if(toggle)
        {
            AL10.alSourcef(source, AL10.AL_LOOPING, 1);
        }
        else
        {
            AL10.alSourcef(source, AL10.AL_LOOPING, 0);
        }
    }
    public void setPosition(float x, float y, float z)
    {
        AL10.alSource3f(source, AL10.AL_POSITION, x, y, z);
    }
    public void setVelocity(float xV, float yV, float zV)
    {
        AL10.alSource3f(source, AL10.AL_VELOCITY, xV, yV, zV);
    }
    public void setRollOff(float factor)
    {
        AL10.alSourcef(source, AL10.AL_ROLLOFF_FACTOR, factor);
    }
    public void setReferenceDistance(float dist)
    {
        AL10.alSourcef(source, AL10.AL_REFERENCE_DISTANCE, dist);
    }
    public void setMax(float max)
    {
        AL10.alSourcef(source, AL10.AL_MAX_DISTANCE, max);
    }
    public void setEquation(float rollOff, float refDist, float max)
    {
        AL10.alSourcef(source, AL10.AL_ROLLOFF_FACTOR, rollOff);
        AL10.alSourcef(source, AL10.AL_REFERENCE_DISTANCE, refDist);
        AL10.alSourcef(source, AL10.AL_MAX_DISTANCE, max);
    }
    public void playWhenOutOfBounds(boolean toggle)
    {
        this.playWhileOutOfBounds = toggle;
    }
    public void setOffsetInSeconds(float seconds)
    {
        AL10.alSourcef(source, AL11.AL_SEC_OFFSET, seconds);
    }
    public float getOffsetInSeconds()
    {
        return AL10.alGetFloat(AL11.AL_SEC_OFFSET);
    }
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    public void setLowPass(float high, float low)
    {
        setFilter(Filter.LOW_PASS, EFX10.AL_FILTER_LOWPASS, high, low);
    }
    public void setFilter(int filter, int filterType, float var1, float var2)
    {
        EFX10.alFilteri(filter, EFX10.AL_FILTER_TYPE, filterType);
        EFX10.alFilterf(filter, filterType, var1);
        EFX10.alFilterf(filter, filterType, var2);
    }
}
