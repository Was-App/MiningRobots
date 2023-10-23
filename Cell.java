import java.util.Random;

public class Cell {
    public float roughness,heliumConcentration;
    public float[] readingError;
    public boolean isOccupied;
    public int unavailableTimeRemaining;
    public Cell[] adjacentCells;


    public Cell(float[] errorBounds,float[] roughnessBounds, float[] heliumBounds){
        setRoughness(roughnessBounds);
        setHeliumConcentration(heliumBounds);
        setReadingError(errorBounds);
        this.isOccupied = false;
        this.unavailableTimeRemaining = 0;
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
        this.updateMiningStatus(newTime);
        for (Cell adjacentCell : this.adjacentCells) {
            adjacentCell.updateMiningStatus(newTime);
        }
    }

    public void updateMiningStatus(int newTime){
        if(this.unavailableTimeRemaining < newTime)
            this.unavailableTimeRemaining = newTime;
    }
}
