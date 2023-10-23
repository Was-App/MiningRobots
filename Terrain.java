public class Terrain {
    //PEGAR ISSO DO ARQUIVO
    public float[] errorBounds = {-0.1f, 0.1f}; //É de -0.1 a 0.1 mesmo? erro da leitura intervalo contido em [0, 0.1]. O valor padrão é [0.02, 0.02].
    public float[] heliumBounds = {0.0f,1f};
    public float[] roughnessBounds = {0.0f,1.0f};
    public int length, height;
    public final Cell[][]  grid;

    public Terrain(int height, int length){
        this.height = height;
        this.length = length;
        this.grid = new Cell[length][height];
        for(int i=0;i<length;i++){
            for(int j=0;j<height;j++){
                this.grid[i][j] = new Cell(this.errorBounds,this.heliumBounds,this.roughnessBounds);
            }
        }
        // Because of the way the loop works, it won't have the adjacent cells from the east and south as it is being constructed,
        // so we must fill the grid and then set the adjacent cells
        this.setAdjacentCells();
    }

    private void setAdjacentCells(){
        // Runs through the grid setting up each cell's adjacent cells
        for(int i=0;i<this.length;i++){
            for(int j=0;j<this.height;j++){
                if(isValidCoordinate(i,j+1)){ //north
                    this.grid[i][j].adjacentCells[0] = grid[i][j+1];
                }
                if(isValidCoordinate(i+1,j)){ //east
                    this.grid[i][j].adjacentCells[1] = grid[i+1][j];
                }
                if(isValidCoordinate(i,j-1)){ //south
                    this.grid[i][j].adjacentCells[2] = grid[i][j-1];
                }
                if(isValidCoordinate(i-1,j)){ //west
                    this.grid[i][j].adjacentCells[3] = grid[i-1][j];
                }
            }
        }
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < this.height && y >= 0 && y < this.length;
    }

}
