import java.util.LinkedList;
import java.util.Queue;
public class Controller {
    Robot controlledRobot;
    Queue <String> instructions;
    int inactiveTimeRemaining;

    public Controller(Robot robot){
        this.controlledRobot = robot;

    }

    public void update(){
        if(this.inactiveTimeRemaining == 0){
            while(this.inactiveTimeRemaining == 0) {

            }
        }else{
            controlledRobot.inactiveTimeRemaining-=1;
        }
    }

    private void executeInstruction(String instruction){
        switch(instruction){
            case "extractIfGreaterThenHalf":
                this.extractIfGreaterThenHalf();
                break;
            case "walkToBestDirection":
                this.walkToBestDirection();
                break;
            case "turnRight":
                this.controlledRobot.turnRight();
                break;
            case "turnLeft":
                this.controlledRobot.turnLeft();
                break;
            case "walk":
                this.controlledRobot.walk();
                break;
            case "mineHelium":
                this.controlledRobot.extractHelium();
                break;
            default:
                break;
        }
    }

    private void extractIfGreaterThen(float value){
        if(this.controlledRobot.readHeliumConcentration()>value)
            this.controlledRobot.extractHelium();
    }

    private void walkToBestDirection(){
        int bestDirection = findBestUnoccupiedDirection();
        while(this.controlledRobot.orientation!=bestDirection)
            this.controlledRobot.turnRight();
        this.controlledRobot.walk();
    }

    private int findBestUnoccupiedDirection(){
        int bestDirection = controlledRobot.orientation;
        float leastRoughness = this.controlledRobot.scanFollowingRoughness();
        // The robot does a full cycle around itself, scanning the terrain roughness and determining the best path
        for(int i=0;i<this.controlledRobot.currentCell.adjacentCells.length;i++){
            this.controlledRobot.turnRight();
            if(controlledRobot.scanFollowingRoughness() < leastRoughness && !this.controlledRobot.isFollowingOccupied()){
                bestDirection = this.controlledRobot.orientation;
                leastRoughness = controlledRobot.scanFollowingRoughness();
            }
        }
        return bestDirection;
    }


}

class simpleController extends Controller{
    public simpleController(Robot robot) {
        super(robot);
    }
}
