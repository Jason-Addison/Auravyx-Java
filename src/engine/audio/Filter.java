package engine.audio;

import org.lwjgl.openal.EFX10;

/**
 * Created by Owner on 5/29/2017.
 */
public class Filter
{

    public static int LOW_PASS = Audio.createFilter(EFX10.AL_FILTER_LOWPASS, 3f, 0.1f);

}
