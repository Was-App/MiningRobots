import java.util.ArrayList;

public class Game {
    //This will run the game
    public int secondsPassed;
    public int gameTime;
    public Terrain gameTerrain;
    public ArrayList<Team> teams;
    public Game(int gameTime, Terrain gameTerrain, ArrayList<Team> teams){
        this.secondsPassed = 0;
        this.gameTime = gameTime;
        this.gameTerrain = gameTerrain;
        this.teams = teams;
    }

    public void run(){
        for(Team team : this.teams)
            team.placeRobots(gameTerrain);
        gameTerrain.printTerrainInformation();
        while(secondsPassed <= this.gameTime){
            for(Team team : this.teams){
                team.printTeamInformation();
                team.updateAllControllers();

            }
            secondsPassed+=1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
