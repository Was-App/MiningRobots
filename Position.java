public class Position {
    public int[] coordinates;
    public int orientation;

    public Position(int[] coordinates){
        this.coordinates = coordinates;
        this.orientation = 0;
    }

    public void updateCoordinatesAfterWalking(){
        switch(this.orientation){
            case 0:
                this.coordinates[1] += 1;
            case 1:
                this.coordinates[0] += 1;
            case 2:
                this.coordinates[1] -= 1;
            case 3:
                this.coordinates[0] -= 1;
        }
    }

}
