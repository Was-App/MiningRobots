import java.lang.String;
import java.util.Random;
public class Robot {
    public String name;
    public int numberOfBarrels;
    public int inactiveTimeRemaining;
    public int orientation;
    public Cell currentCell;
    public float currentHeliumConcentration;
    public static int baseTimeToDoActions;

    //Constructor
    public Robot(String name) {
        this.name = name;
        this.numberOfBarrels = 0;
        this.inactiveTimeRemaining = 0;
        this.orientation = 0;
        this.currentHeliumConcentration = -1;
        baseTimeToDoActions = 1;
    }

    public float readHeliumConcentration(){
        Random random = new Random();
        float[] errorBounds = this.currentCell.readingError;
        float error = errorBounds[0] + random.nextFloat() * (errorBounds[1] - errorBounds[0]);
        return this.currentCell.heliumConcentration + error;
    }

    // Need to check if next cell is valid
    public float scanFollowingRoughness(){
        return this.currentCell.adjacentCells[this.orientation].roughness;
    }

    // Implement this better
    public int getTimePassed(Game game){
        return game.secondsPassed;
    }

    public void turnRight(){
        this.orientation = (this.orientation+1)%4;
    }

    public void turnLeft(){
        this.orientation = (this.orientation-1)%4;
    }

    // Again, check if next is valid first
    public boolean isFollowingPossibleToWalk(){
        if(this.currentCell.adjacentCells[this.orientation] != null)
            return this.currentCell.adjacentCells[this.orientation].isOccupied;
        return false;
    }

    // Have to check if it's valid, or it'll be checking a null Cell
    public void walk() {
        //Depending on the orientation the robot is facing
        if(!this.currentCell.adjacentCells[this.orientation].isOccupied && this.currentCell.adjacentCells[this.orientation] != null) {
            this.inactiveTimeRemaining = this.currentCell.getSecondsToTravelTo();
            this.currentCell.isOccupied = false;
            this.currentCell = this.currentCell.adjacentCells[this.orientation];
            this.currentCell.isOccupied = true;
            this.currentHeliumConcentration = -1;
        }
    }

    public void extractHelium(){
        if(this.currentCell.unavailableTimeRemaining==0){
            this.inactiveTimeRemaining = this.currentCell.getSecondsToExtract();
            this.numberOfBarrels += (int) this.currentCell.heliumConcentration*10;
            this.currentCell.updateAllExtractingStatus(this.inactiveTimeRemaining);
            this.currentCell.emptyHelium();
        }
    }

}
