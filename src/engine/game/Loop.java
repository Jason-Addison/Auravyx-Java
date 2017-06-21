package engine.game;

import engine.Loader;
import engine.audio.Audio;
import engine.audio.Source;
import engine.display.DisplayManager;
import engine.game.state.State;
import engine.game.state.gamestate.GameState;
import engine.game.state.menustate.MenuState;
import engine.graphics.Gfx;
import engine.graphics.Texture;
import engine.graphics.gui.font.Character;
import engine.graphics.gui.font.FontRenderer;
import engine.graphics.model.Model;
import engine.world.World;
import engine.world.terrain.Terrain;
import engine.world.voxel.Chunk;
import engine.world.voxel.Material;
import entities.entityEditor.EditorState;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import server.Client;
import tools.Input;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Owner on 2/11/2017.
 */
public class Loop
{

    //static FBO fbo = new FBO(Display.getWidth(), Display.getHeight());

   // static State state = new State();
    public static int meme;
    static HashMap<Byte, State> states = new HashMap<>();
    public static Model mod;
    static Client client = new Client();
    public static void update()
    {
        Audio.create();
        System.setProperty("org.lwjgl.util.Debug", "true");
        client.send();
        client.update();
        Material.register("air", 0, 0, 0, 0);
        Material.register("grass", 1, (float) (244f / 255f), (float) (247f / 255f), (float) (140f / 255f));
        Material.register("stone", 2, 0.7f, 0.7f, 0.7f);
        Material.register("red", 3, 1f, 0.5f, 0.5f);
        Material.register("plains", 5, 0f, 0.5f, 0);
        Material.register("stoncxde", 5, 0f, 0.5f, 0);
        Settings.loadDefaults();
        Terrain.loadMap();
        Character.init();
        Audio.loadAllAudio();
        Loader.loadAllTextures();
        Loader.loadAllModels();
        Texture.loadAllTextures();
        Gfx.loadPowerOfTwos();
        //EntityLoader.loadEntity("C:\\Sandbox\\Sandbox\\assests\\entities\\items\\pickaxe\\pickaxe.dat", );
        //EntityLoader.loadEntity("C:\\Sandbox\\Sandbox\\assests\\entities\\items\\nature\\woodlog\\woodenlog.dat");
        //mod = OBJReader.loadModel("C:\\Sandbox\\Sandbox\\assests\\entities\\items\\barrel.obj", );
        //Game.addModel(OBJReader.loadModel("D:\\meme.obj"), "box");

        int scale = 10;
        int halfScale = scale / 2;
        for(int x = 0; x < scale; x++)
        {
            for(int y = 0; y < scale; y++)
            {
                Chunk chunk = new Chunk(x - halfScale, y - halfScale);
                World.addChunk((x - halfScale) + " " + (y - halfScale), chunk);
            }
        }

        long time = System.currentTimeMillis();
        System.out.println("Starting world generation...");
        for(String key : World.getChunks().keySet())
        {
            World.getChunks().get(key).generateVoxelMesh();
        }
        System.out.println("Finished genration in : " + (System.currentTimeMillis() - time) + " ms");
        GameState gameState = new GameState();
        MenuState menuState = new MenuState();
        EditorState editorState = new EditorState();
        states.put(State.GAME_STATE, gameState);
        states.put(State.MENU_STATE, menuState);
        states.put(State.EDITOR_STATE, editorState);

        FontRenderer.texID = Loader.loadTexture("C:/Sandbox/font.png").getTextureID();
        byte lastState = State.MENU_STATE;

        int music = Audio.loadAudio("D:/sine.wav");
        Source source = new Source();
        source.setGain(5);
        //source.play(music);
        Source source2 = new Source();
        source2.setGain(1);
        //source2.play(Game.getAudio("rainbow"));
        float ed = 0;
        while(!Game.closeRequested)
        {
            for(int i = 0; i < 5; i++)
            {
                //Chunk chunk = new Chunk(0, 0);
                //chunk.update();
                //chunk.generateVoxelMesh();
            }
            ed += 0.0001f;
            source2.setPitch(ed);
            Input.MOUSE_SCROLL = 0;
            while(Mouse.next())
            {
                int change = Mouse.getDWheel();
                Input.MOUSE_SCROLL = change;
            }
            client.update();
            Gfx.update();
            states.get(Game.getCurrentState()).update();
            if(lastState != Game.getCurrentState())
            {
                states.get(Game.getCurrentState()).stateChanged();
            }

            DisplayManager.updateDisplay();
            screenUpdate();
            lastState = Game.getCurrentState();
        }
        /*BufferedImage image = new BufferedImage((int) Gfx.WIDTH, (int) Gfx.HEIGHT, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < image.getWidth(); x++)
        {
            for(int y = 0; y < image.getHeight(); y++)
            {
                Vector3f vec = Gfx.COLOUR_FBO.getColour(x, y);
                Color colour = new Color(vec.x, vec.y, vec.z);
                image.setRGB(x, y, colour.getRGB());
            }
        }*/
        //File outputfile = new File("D:/image.png");
        /*try
        {
            //ImageIO.write(image, "png", outputfile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
        Game.onClose();
        try
        {
            Client.channel.disconnect();
            Client.channel.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void screenUpdate()
    {
        if(Display.wasResized())
        {
            Gfx.updateFrustum();
            //fbo.destroy();
            //fbo = new FBO(Display.getWidth(), Display.getHeight());
        }
    }
}
