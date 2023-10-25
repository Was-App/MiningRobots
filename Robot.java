import java.lang.String;
import java.util.Random;
public class Robot {
    public String name;
    public int numberOfBarrels;
    public int inactiveTimeRemaining;
    public int orientation;
    public Cell currentCell;


    public Robot(String name) {
        this.name = name;
        this.numberOfBarrels = 0;
        this.inactiveTimeRemaining = 0;
        this.orientation = 0;
    }

    public float readHeliumConcentration(){
        Random random = new Random();
        float[] errorBounds = this.currentCell.readingError;
        float error = errorBounds[0] + random.nextFloat() * (errorBounds[1] - errorBounds[0]);
        return this.currentCell.heliumConcentration + error;
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


    public boolean isFollowingOccupied(){
        return this.currentCell.adjacentCells[this.orientation].isOccupied;
    }


    public void walk() {
        if(!this.currentCell.adjacentCells[this.orientation].isOccupied) {
            this.inactiveTimeRemaining = this.currentCell.getSecondsToTravelTo();
            this.currentCell.isOccupied = false;
            this.currentCell = this.currentCell.adjacentCells[this.orientation];
            this.currentCell.isOccupied = true;
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
