import java.util.Random;
import java.lang.String;
public class Team {
    Robot[] robots;
    String name;
    int numberOfRobots

    public void placeRobots(Terrain terrain){
        Random random = new Random();
        boolean freeSpace = false;
        for(int i=0;i<numberOfRobots;i++){
            int randomX = random.nextInt(terrain.length);
            int randomY = random.nextInt(terrain.width);
            while(true){
                if()//não tem robo na posição aleatoria
                break;
            }


        }
    }
    public Team(int numberOfRobots,String teamName,String[] robotNames){
        this.numberOfRobots = numberOfRobots;
        this.name = teamName;
        for(int i=0;i<numberOfRobots;i++) {
            robots[i] = new Robot(robotNames[i]);
        }
    }
}
