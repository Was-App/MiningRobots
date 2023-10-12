import java.util.Random;

public class Cell {
    float roughness,error,heliumConcentration;
    public void setError(float[] errorBounds){
        Random random = new Random();
        this.error = errorBounds[0] + random.nextFloat() * (errorBounds[1] - errorBounds[0]);
    }

    public void setRoughness(float[] Bounds){
        Random random = new Random();
        this.error = Bounds[0] + random.nextFloat() * (Bounds[1] - Bounds[0]);
    }

    public float timeToTravel(){
        return this.roughness*10;
    }
}
