import org.json.simple.JSONObject;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args){

        Read input = new Read();
        JSONObject gameFile = (JSONObject) input.readFile("Game.json");
        JSONObject teamsFile = (JSONObject) gameFile.get("Teams");
        for(int i = 1; i < 3; i++){
            JSONObject team = (JSONObject) teamsFile.get("Team" + i);
        }
        Terrain terrain = new Terrain();
        Game game = new Game();
        game.run();
    }
}
