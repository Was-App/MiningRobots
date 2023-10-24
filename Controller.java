import java.util.LinkedList;
import java.util.Queue;
public class Controller {
    Robot controlledRobot;
    Queue <String> instructions;

    public Controller(Robot robot,String[] instructions){
        this.controlledRobot = robot;
        // The instructions for each robot are store in a queue, so as to be simpler to fetch
        for(String instruction : instructions)
            this.instructions.add(instruction);
    }

    public void update(){
        if(controlledRobot.inactiveTimeRemaining==0){
            while(controlledRobot.inactiveTimeRemaining == 0) {
                // Fetch next instruction and execute it, only stops when it gets an instruction that takes time
                this.executeInstruction(this.instructions.poll());
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

    private void extractIfGreaterThenHalf(){
        if(this.controlledRobot.readHeliumConcentration()>0.5)
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
