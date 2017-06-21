package engine.graphics;

import engine.game.Game;

/**
 * Created by Owner on 4/22/2017.
 */
public class Texture
{

    public static int DEBUG_TEXTURE;
    public static int FULL_HEART;
    public static int EMPTY_HEART;
    public static void loadAllTextures()
    {
        DEBUG_TEXTURE = Game.getTexture("goodtexture");
        FULL_HEART = Game.getTexture("fullheart");
        EMPTY_HEART = Game.getTexture("emptyheart");
    }

}
