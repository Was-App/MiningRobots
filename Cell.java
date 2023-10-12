import java.util.Random;

public class Cell {
    public float roughness,heliumConcentration;
    public float[] readingError;
    public int isOccupied;
    public int isBeingUsed;

    public void setRoughness(float[] Bounds){
        Random random = new Random();
        this.roughness = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
    }

    public void setHeliumConcentration(float[] Bounds){
        Random random = new Random();
        this.heliumConcentration = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
    }

    public float getTimeToTravel(){
        return this.roughness*10;
    }

    public Cell(float[] errorBounds,float[] roughnessBounds, float[] heliumBounds){
        this.readingError = errorBounds;
        setRoughness(roughnessBounds);
        setHeliumConcentration(heliumBounds);
        this.isOccupied = 0;
        this.isBeingUsed = 0;
    }

}
