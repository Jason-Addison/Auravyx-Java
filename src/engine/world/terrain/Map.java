package engine.world.terrain;

import engine.game.Game;
import tools.Utilities;

import java.util.ArrayList;

/**
 * Created by Owner on 3/11/2017.
 */
public class Map
{

    private ArrayList<MapPreview> mapPreviews = new ArrayList<>();
    private String mapName = "NoName";
    private String mapState = "NoState";

    //private HashMap<>
    public Map(String mapName)
    {
        loadMap(mapName);
    }

    private void loadMap(String mapName)
    {
        String mapLocation = Game.GAME_DIR + "\\Sandbox\\map\\" + mapName;
        String mapMeta = Utilities.readStringFromFile(mapLocation + "\\mapmeta.txt");
        String[] seperatedMeta = mapMeta.split("\\n+");
        setupMapInformation(seperatedMeta);
    }
    private void setupMapInformation(String[] meta)
    {
        this.mapName = meta[0];
        this.mapState = meta[1];
    }
    public String getMapName()
    {
        return mapName;
    }
    public String getMapState()
    {
        return mapState;
    }
}
