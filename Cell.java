import java.util.Random;

public class Cell {
    public float roughness,heliumConcentration;
    public float[] readingError;
    public boolean isOccupied;
    // The objective is to know if the Cell is being used, for each turn ingame, it deminishes the unavailable time remaining, to simulate time passing
    public int unavailableTimeRemaining;
    // Each cell posesses the knowledge of its adjacent cells, so a robot can navigate to them
    public Cell[] adjacentCells;


    public Cell(float[] errorBounds,float[] roughnessBounds, float[] heliumBounds){
        setRoughness(roughnessBounds);
        setHeliumConcentration(heliumBounds);
        setReadingError(errorBounds);
        this.isOccupied = false;
        this.unavailableTimeRemaining = 0;
        this.adjacentCells = new Cell[4]; // Initializes as null
    }

    private void setReadingError(float[] Bounds){
        Random random = new Random();
        float randomValue1 = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
        float randomValue2 = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
        this.readingError = new float[2];
        if(randomValue1<randomValue2){
            this.readingError[0]=randomValue1;
            this.readingError[1]=randomValue2;
        }else{
            this.readingError[0]=randomValue2;
            this.readingError[1]=randomValue1;
        }
    }

    private void setRoughness(float[] Bounds){//don't know if I do a "getRandomFromBounds()" function on a Math Class or something, or keep these 2
        Random random = new Random();
        this.roughness = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
    }

    private void setHeliumConcentration(float[] Bounds){
        Random random = new Random();
        this.heliumConcentration = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
    }

    public int getSecondsToTravelTo(){
        return (int) this.roughness*10;
    }

    public int getSecondsToMine(){
        return (int) this.heliumConcentration*10;
    }

    public void emptyHelium(){
        this.heliumConcentration=0;
    }

    public void updateAllMiningStatus(int newTime){
        // Updates itself's and the adjacent cell's unavailable time
        this.updateMiningStatus(newTime);
        for (Cell adjacentCell : this.adjacentCells) {
            if(adjacentCell != null){
                adjacentCell.updateMiningStatus(newTime);
            }
        }
    }

    public void updateMiningStatus(int newTime){
        if(this.unavailableTimeRemaining < newTime)
            this.unavailableTimeRemaining = newTime;
    }
}
