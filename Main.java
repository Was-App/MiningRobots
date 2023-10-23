import org.json.simple.JSONObject;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args){
        Input entrada = new Input();
        JSONObject jogo = (JSONObject) entrada.readFile("Game.json");
        JSONObject times = (JSONObject) jogo.get("Times");
        for(int i = 1; i < 3; i++){
            JSONObject time = (JSONObject) times.get("Time" + i);
            System.out.println(time.get("nome"));
        }
    }
}
