import java.util.Random;
public class Team {
    Robot[] robots;
    String name;
    int numberOfRobots;

    public void placeRobots(Terrain terrain){
        Random random = new Random();
        boolean freeSpace = false;
        for(int i=0;i<numberOfRobots;i++){
            while(true){
                int randomX = random.nextInt(terrain.length);
                int randomY = random.nextInt(terrain.height);
                if(!terrain.grid[randomX][randomY].isOccupied){
                    robots[i].currentCell = terrain.grid[randomX][randomY];
                    break;
                }
            }


        }
    }
    public Team(int numberOfRobots,String teamName,String[] robotNames){
        this.numberOfRobots = numberOfRobots;
        this.name = teamName;
        this.robots = new Robot[numberOfRobots];
        for(int i=0;i<numberOfRobots;i++) {
            robots[i] = new Robot(robotNames[i]);
        }
    }
}
