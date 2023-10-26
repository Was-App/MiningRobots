import org.json.simple.JSONObject;
import java.util.ArrayList;

public class Initialization {
    private final JSONObject gameSettings;
    private final JSONObject terrainSettings;

    private final ArrayList<Team> teams;
    public Initialization(String terrainPath, String gamePath, ArrayList<Team> teams){
        Read readInput = new Read();
        this.terrainSettings = (JSONObject) readInput.readFile(terrainPath);
        this.gameSettings = (JSONObject) readInput.readFile(gamePath);
        this.teams = teams;
    }

    public int getTerrainLength() { return (int) terrainSettings.get("length"); }

    public int getTerrainHeight(){ return (int) terrainSettings.get("height"); }

    public ArrayList<Team> setTeams(){
        JSONObject teamsFromFile = (JSONObject) gameSettings.get("Teams");
        long numOfTeams = (long) gameSettings.get("Number of Teams");
        JSONObject actualTeam = new JSONObject();
        for(int i = 1; i < numOfTeams; i++){
            actualTeam = (JSONObject) teamsFromFile.get("Team" + i);
            Team team = new Team((int) ((long) actualTeam.get("number of robots")), (String) actualTeam.get("name"), (String) actualTeam.get("controller type"));
            teams.add(team);
        }
        return teams;
    }
}
