public abstract class Controller {
    Robot controlledRobot;
    int inactiveTimeRemaining;
    boolean isActive;

    public Controller(Robot robot){
        this.controlledRobot = robot;
        this.inactiveTimeRemaining = 0;
        this.isActive = true;
    }

    public abstract void update();

}

class simpleController extends Controller{
    public simpleController(Robot robot) {
        super(robot);
    }

    public void update(){
        if(isActive){

            if(this.controlledRobot.currentHeliumConcentration == -1){
                this.controlledRobot.scanHeliumConcentration();
            }else if(this.controlledRobot.currentHeliumConcentration > 0){
                this.controlledRobot.extractHelium();
                this.inactiveTimeRemaining += this.controlledRobot.getTimeToExtract();
            }else if(!this.controlledRobot.isFollowingPossibleToWalk()){
                this.controlledRobot.turnRight();
            }else if(this.controlledRobot.isFollowingPossibleToWalk()){
                this.controlledRobot.walk();
                this.inactiveTimeRemaining += controlledRobot.getTimeToWalk();
            }
            this.isActive = false;
            this.inactiveTimeRemaining += Robot.baseTimeToDoActions;

        }else{
            this.inactiveTimeRemaining -= 1;
            if(this.inactiveTimeRemaining == 0)
                this.isActive = true;
        }
    }
}

class complexController extends Controller {
    public complexController(Robot robot) {
        super(robot);
    }

    public int bestDirection;

    public void update() {
        if (isActive) {
            if (this.controlledRobot.currentHeliumConcentration == -1) {
                this.controlledRobot.scanHeliumConcentration();
                this.inactiveTimeRemaining += Robot.baseTimeToDoActions;
            } else if (this.controlledRobot.currentHeliumConcentration > 0.1) {
                this.controlledRobot.extractHelium();
                this.inactiveTimeRemaining += controlledRobot.getTimeToExtract();
            } else if (this.bestDirection == -1) {
                findBestUnoccupiedDirection();
            } else {
                walkToBestDirection();
            }
        } else {
            this.inactiveTimeRemaining -= 1;
        }
        this.isActive = this.inactiveTimeRemaining == 0;
    }

    private void walkToBestDirection() {
        int numberOfTurns = 0;
        while (this.controlledRobot.orientation != bestDirection) {
            this.controlledRobot.turnRight();
            numberOfTurns += 1;
        }
        this.controlledRobot.walk();
        this.inactiveTimeRemaining += Math.max(numberOfTurns, 1) * Robot.baseTimeToDoActions;
        this.inactiveTimeRemaining += controlledRobot.getTimeToWalk();
        this.bestDirection = -1;
    }

    private void findBestUnoccupiedDirection() {
        this.bestDirection = controlledRobot.orientation;
        float leastRoughness = this.controlledRobot.scanFollowingRoughness();
        for (int i = 0; i < this.controlledRobot.currentCell.adjacentCells.length; i++) {
            this.controlledRobot.turnRight();
            if (controlledRobot.scanFollowingRoughness() < leastRoughness && !this.controlledRobot.isFollowingPossibleToWalk()) {
                this.bestDirection = this.controlledRobot.orientation;
                leastRoughness = controlledRobot.scanFollowingRoughness();
            }
        }
        this.inactiveTimeRemaining += 4 * Robot.baseTimeToDoActions;
    }
}
