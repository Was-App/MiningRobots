import org.json.simple.JSONObject;

import static java.lang.Thread.sleep;

public class Main {
    Game game;
    public static void main(String[] args){
        Read input = new Read();
        JSONObject game = (JSONObject) input.readFile("Game.json");
        JSONObject teams = (JSONObject) game.get("Teams");
        for(int i = 1; i < 3; i++){
            JSONObject team = (JSONObject) teams.get("Team" + i);
        }
    }
}
