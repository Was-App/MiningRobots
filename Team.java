import java.util.Random;
public class Team {
    Robot[] robots;
    Controller[] controllers;
    String name;
    String[] robotNames;
    int numberOfRobots;
    String[][] robotsInstructions;

    public Team(int numberOfRobots, String teamName, String[] robotNames, String controllerType){
        this.numberOfRobots = numberOfRobots;
        this.name = teamName;
        this.robots = new Robot[numberOfRobots];
        this.controllers = new Controller[numberOfRobots];
        for(int i=0;i<numberOfRobots;i++) {
            this.robots[i] = new Robot(robotNames[i]);
            this.controllers[i] = chooseController(controllerType,robots[i]);
        }

    }

    public Controller chooseController(String controllerType, Robot robot) {
        if(controllerType.equals("Simple")){
            return new simpleController(robot);
        }
        if(controllerType.equals("Complex")){
            return new complexController(robot);
        }
        return null;
    }

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

}
