public class Terrain {
    public float[] errorBounds = {0.0f, 0.1f};
    public float[] heliumBounds = {0.0f,1f};
    public float[] roughnessBounds = {0.0f,1.0f};
    public int width,length;

    private final Cell[][]  grid;

    public Terrain(int width, int length){
        this.width = width;
        this.length = length;
        this.grid = new Cell[width][length];
        for(int i=0;i<width;i++){
            for(int j=0;j<length;j++){
                grid[i][j] = new Cell(this.errorBounds,this.heliumBounds,this.roughnessBounds);
            }
        }
    }

}
