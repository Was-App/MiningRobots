public class Game {
    //This will run the game
    public int secondsPassed;
    public int gameTime;
    public Terrain gameTerrain;
    public Team[] teams;
    public Game(int gameTime, Terrain gameTerrain, Team[] teams){

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
