import java.lang.String;
import java.util.Random;
public class Robot {
    public String name;
    public int numberOfBarrels;
    public Cell currentCell;
    public float currentHeliumConcentration;
    public Position position;
    public static int BASE_TIME_TO_DO_ACTIONS;

    public Robot(String name) {
        this.name = name;
        this.numberOfBarrels = 0;
        this.currentHeliumConcentration = -1;
        BASE_TIME_TO_DO_ACTIONS = 1;
    }

    public void scanHeliumConcentration(){
        Random random = new Random();
        float[] errorBounds = this.currentCell.readingError;
        float error = errorBounds[0] + random.nextFloat() * (errorBounds[1] - errorBounds[0]);
        this.currentHeliumConcentration = this.currentCell.heliumConcentration + error;
    }

    public float scanFollowingRoughness(){
        return this.currentCell.adjacentCells[this.position.orientation].roughness;
    }

    public void turnRight(){
        this.position.orientation = (this.position.orientation+1)%4;
    }

    public void turnLeft(){
        this.position.orientation = (this.position.orientation-1)%4;
    }

    public boolean isFollowingPossibleToWalk(){
        if(this.currentCell.adjacentCells[this.position.orientation] != null)
            return this.currentCell.adjacentCells[this.position.orientation].isOccupied;
        return false;
    }

    public void walk() {
        if(!this.currentCell.adjacentCells[this.position.orientation].isOccupied && this.currentCell.adjacentCells[this.position.orientation] != null) {
            this.currentCell.isOccupied = false;
            this.currentCell = this.currentCell.adjacentCells[this.position.orientation];
            this.currentCell.isOccupied = true;
            this.position.updateCoordinatesAfterWalking();
            this.currentHeliumConcentration = -1;
        }
    }

    public int getTimeToWalk(){
        if(this.currentCell.adjacentCells[position.orientation] == null)
            return 0;
        return this.currentCell.adjacentCells[position.orientation].getSecondsToTravelTo();
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

    public void printRobotInformation(){
        System.out.print("    Robo: " + this.name + " || ");
        System.out.print("Posicao x: " + this.position.coordinates[0] + " || ");
        System.out.print("Posicao y: " + this.position.coordinates[1] + " || ");
        System.out.println("Numero de Barris : " + this.numberOfBarrels + "\n");
    }

}
