public class Terrain {
    public float[] errorBounds = {-0.1f, 0.1f}; //É de -0.1 a 0.1 mesmo? erro da leitura intervalo contido em [0, 0.1]. O valor padrão é [0.02, 0.02].
    public float[] heliumBounds = {0.0f,1f};
    public float[] roughnessBounds = {0.0f,1.0f};
    public int length, height;

    public final Cell[][]  grid;

    public void updateAdjacentMiningStatus(int newTime, int coordinateX, int coordinateY){
        grid[coordinateX][coordinateY].updateMiningStatus(newTime);
        grid[coordinateX-1][coordinateY].updateMiningStatus(newTime);
        grid[coordinateX+1][coordinateY].updateMiningStatus(newTime);
        grid[coordinateX][coordinateY-1].updateMiningStatus(newTime);
        grid[coordinateX][coordinateY+1].updateMiningStatus(newTime);
    }

    public Terrain(int height, int length){
        this.height = height;
        this.length = length;
        this.grid = new Cell[height][length];
        for(int i=0;i<height;i++){
            for(int j=0;j<length;j++){
                grid[i][j] = new Cell(this.errorBounds,this.heliumBounds,this.roughnessBounds);
            }
        }
    }

}
