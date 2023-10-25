import java.lang.String;
import java.util.Random;
public class Robot {
    public String name;
    public int numberOfBarrels;
    public int orientation;
    public Cell currentCell;
    public float currentHeliumConcentration;
    public static int baseTimeToDoActions;

    public Robot(String name) {
        this.name = name;
        this.numberOfBarrels = 0;
        this.orientation = 0;
        this.currentHeliumConcentration = -1;
        baseTimeToDoActions = 1;
    }

    public void scanHeliumConcentration(){
        Random random = new Random();
        float[] errorBounds = this.currentCell.readingError;
        float error = errorBounds[0] + random.nextFloat() * (errorBounds[1] - errorBounds[0]);
        this.currentHeliumConcentration = this.currentCell.heliumConcentration + error;
    }

    public float scanFollowingRoughness(){
        return this.currentCell.adjacentCells[this.orientation].roughness;
    }

    public int getTimePassed(Game game){
        return game.secondsPassed;
    }

    public void turnRight(){
        this.orientation = (this.orientation+1)%4;
    }

    public void turnLeft(){
        this.orientation = (this.orientation-1)%4;
    }

    public boolean isFollowingPossibleToWalk(){
        if(this.currentCell.adjacentCells[this.orientation] != null)
            return this.currentCell.adjacentCells[this.orientation].isOccupied;
        return false;
    }

    public void walk() {
        if(!this.currentCell.adjacentCells[this.orientation].isOccupied && this.currentCell.adjacentCells[this.orientation] != null) {
            this.currentCell.isOccupied = false;
            this.currentCell = this.currentCell.adjacentCells[this.orientation];
            this.currentCell.isOccupied = true;
            this.currentHeliumConcentration = -1;
        }
    }
    public int getTimeToWalk(){
        if(this.currentCell.adjacentCells[orientation] == null)
            return 0;
        return this.currentCell.adjacentCells[orientation].getSecondsToTravelTo();
    }

    public int getTimeToExtract(){
        return this.currentCell.getSecondsToExtract();
    }

    public void extractHelium(){
        if(this.currentCell.unavailableTimeRemaining==0){
            this.numberOfBarrels += (int) this.currentCell.heliumConcentration*10;
            this.currentCell.updateAllExtractingStatus(this.getTimeToExtract());
            this.currentCell.emptyHelium();
        }
    }

}
