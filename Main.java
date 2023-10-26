import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args){
        Read input = new Read();
        ArrayList<Team> teams = new ArrayList<Team>();
        Initialization initializator = new Initialization("Terrain.json", "Game.json", teams);
        JSONObject gameFile = (JSONObject) input.readFile("Game.json");
        JSONObject teamsFile = (JSONObject) gameFile.get("Teams");
        JSONObject terrainFile = (JSONObject) input.readFile("Terrain.json");
        teams = initializator.setTeams();
        Terrain terrain = new Terrain((int) ((long)terrainFile.get("height")), (int)((long)terrainFile.get("length")));
        Game game = new Game((int) gameFile.get("Game Time"), terrain, teams);
        try{
            game.run();
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
