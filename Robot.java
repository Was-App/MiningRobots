public class Robot {
    private int[] robotCoordinates = new int[2];
    private int orientation;
    private int numberOfBarrels;

    //Constructor
    public Robot(int[] coordinates){
        this.robotCoordinates[0] = coordinates[0];
        this.robotCoordinates[1] = coordinates[1];
        this.numberOfBarrels = 0;
        this.orientation = 0;
    }

    public int[] getRobotCoordinates() {
        return this.robotCoordinates;
    }
}
