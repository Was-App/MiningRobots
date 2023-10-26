public class Game {
    //This will run the game
    public int secondsPassed;
    public int gameTime;
    public Terrain gameTerrain;
    public Team teams[];
    public Game(int gameTime, Terrain gameTerrain){

    }

    public void run(){
        while(secondsPassed <= this.gameTime){
            for(Team team : this.teams){
                team.updateAllControllers();
            }
            secondsPassed+=1;
            this.printInformation();
        }
    }
    // Basically a while loop that runs until all the game time has passed, and sleeps for 1 second
    // Update all controllers in it, can be done in the team class (team.updateAllControllers)
    // At the end of it, print Information to the user, can create a method to do so in each Relevant Class
    // Maybe bring back Robot's position, so it can be printed easily

}
