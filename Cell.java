import java.util.Random;

public class Cell {
    public float roughness,heliumConcentration;
    public float[] readingError;
    public boolean isOccupied;
    public int unavailableTimeRemaining;

    public void setRoughness(float[] Bounds){
        Random random = new Random();
        this.roughness = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
    }

    public void setHeliumConcentration(float[] Bounds){
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

    public void updateMiningStatus(int newTime){
        if(this.unavailableTimeRemaining < newTime)
            this.unavailableTimeRemaining = newTime;
    }

    public Cell(float[] errorBounds,float[] roughnessBounds, float[] heliumBounds){
        this.readingError = errorBounds;
        setRoughness(roughnessBounds);
        setHeliumConcentration(heliumBounds);
        this.isOccupied = false;
        this.unavailableTimeRemaining = 0;
    }

}
