package engine.audio;

import engine.Loader;
import engine.game.Game;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.EFX10;
import org.lwjgl.util.WaveData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

/**
 * Created by Owner on 5/28/2017.
 */
public class Audio
{

    private static ArrayList<Integer> buffers = new ArrayList<>();
    private static ArrayList<Integer> sources = new ArrayList<>();
    public static int loadAudio(String dir)
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(1);
        IntBuffer source = BufferUtils.createIntBuffer(1);

        create(buffer, source, dir);

        FloatBuffer position = BufferUtils.createFloatBuffer(3).put(new float[]{0, 0, 0}); position.rewind();
        FloatBuffer velocity = BufferUtils.createFloatBuffer(3).put(new float[]{0, 0, 0}); velocity.rewind();
        FloatBuffer listenerPosition = BufferUtils.createFloatBuffer(3).put(new float[]{0, 0, 0}); listenerPosition.rewind();
        FloatBuffer listenerVelocity = BufferUtils.createFloatBuffer(3).put(new float[]{0, 0, 0}); listenerVelocity.rewind();
        FloatBuffer listenerOrientation = BufferUtils.createFloatBuffer(6).put(new float[]{0, 0, -1, 0, 1, 0}); listenerOrientation.rewind();

        AL10.alGenSources(source);
        AL10.alSourcei(source.get(0), AL10.AL_BUFFER, buffer.get(0));
        AL10.alSourcef(source.get(0), AL10.AL_PITCH, 1f);
        AL10.alSourcef(source.get(0), AL10.AL_GAIN, 1f);
        AL10.alSource(source.get(0), AL10.AL_POSITION, position);
        AL10.alSource(source.get(0), AL10.AL_VELOCITY, velocity);

        AL10.alListener(AL10.AL_POSITION, listenerPosition);
        AL10.alListener(AL10.AL_VELOCITY, listenerVelocity);
        AL10.alListener(AL10.AL_ORIENTATION, listenerOrientation);

        return buffer.get(0);
    }

    private static void create(IntBuffer buffer, IntBuffer source, String dir)
    {
        AL10.alGenBuffers(buffer);

        FileInputStream input = null;
        WaveData wav = null;
        try
        {
            wav = WaveData.create(new BufferedInputStream(new FileInputStream(dir)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        AL10.alBufferData(buffer.get(0), wav.format, wav.data, wav.samplerate);

        wav.dispose();
    }

    private static void setupSource(IntBuffer source, IntBuffer buffer)
    {

    }

    public static void clean()
    {
        for(int i : sources)
        {
            AL10.alDeleteSources(i);
        }
        for(int b : buffers)
        {
            AL10.alDeleteBuffers(b);
        }
        AL.destroy();
    }


    public static void setListener(float x, float y, float z, float xVel, float yVel, float zVel, float uX, float uY, float uZ, float fX, float fY, float fZ)
    {
        AL10.alListener3f(AL10.AL_POSITION, x, y, z);
        AL10.alListener3f(AL10.AL_VELOCITY, xVel, yVel, zVel);
        FloatBuffer orientation = BufferUtils.createFloatBuffer(6).put(new float[]{-fX, -fY, -fZ, uX, uY, uZ}); orientation.rewind();
        AL10.alListener(AL10.AL_ORIENTATION, orientation);
    }
    public static void loadAllAudio()
    {
        File audioDir = new File(Game.GAME_DIR + "assests\\audio\\");
        File[] allFiles = audioDir.listFiles();
        ArrayList<File> files = Loader.directoryCheck(allFiles, "wav");
        for(File file : files)
        {
            String name = file.getName().substring(0, file.getName().length() - 4);
            int id = loadAudio(file.getAbsolutePath());
            Game.addAudio(id, name);
        }

    }
    public static void create()
    {
        try
        {
            AL.create();
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
        AL10.alDopplerFactor(1f);
        AL10.alDistanceModel(AL10.AL_INVERSE_DISTANCE);
        EFX10.alEffectf(EFX10.AL_AIR_ABSORPTION_FACTOR, EFX10.AL_EFFECT_TYPE, 100);
        //EFX10.alEffec
    }
    public static int createFilter(int filterType, float var1, float var2)
    {
        IntBuffer filter = BufferUtils.createIntBuffer(1);
        EFX10.alGenFilters(filter);

        EFX10.alFilteri(filter.get(0), EFX10.AL_FILTER_TYPE, filterType);
        EFX10.alFilterf(filter.get(0), filterType, var1);
        EFX10.alFilterf(filter.get(0), filterType, var2);

        return filter.get(0);
    }

    public static int createEffect()
    {
        IntBuffer effectsSlot = BufferUtils.createIntBuffer(1);
        EFX10.alGenAuxiliaryEffectSlots(effectsSlot);
        IntBuffer effect = BufferUtils.createIntBuffer(1);
        EFX10.alGenEffects(effect);

        // Configure the effect to be a reverb, load it to the effect slot
        EFX10.alEffecti(effect.get(0), EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_REVERB);
        EFX10.alAuxiliaryEffectSloti(effectsSlot.get(0), EFX10.AL_EFFECTSLOT_EFFECT, effect.get(0));
        return effectsSlot.get(0);
    }

}
