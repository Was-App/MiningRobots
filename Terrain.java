public class Terrain {
    public float[] errorBounds = {-0.1f, 0.1f};
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
        this.setAdjacentCells();
    }

    private void setAdjacentCells(){

        for(int i=0;i<this.length;i++){
            for(int j=0;j<this.height;j++){
                if(isValidCoordinate(i,j+1)){ 
                    this.grid[i][j].adjacentCells[0] = grid[i][j+1];
                }
                if(isValidCoordinate(i+1,j)){ 
                    this.grid[i][j].adjacentCells[1] = grid[i+1][j];
                }
                if(isValidCoordinate(i,j-1)){ 
                    this.grid[i][j].adjacentCells[2] = grid[i][j-1];
                }
                if(isValidCoordinate(i-1,j)){ 
                    this.grid[i][j].adjacentCells[3] = grid[i-1][j];
                }
            }
        }
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < this.height && y >= 0 && y < this.length;
    }

    public void printTerrainInformation(){
        System.out.println("TERRENO: ");
        for(int i=0;i<this.height;i++){
            System.out.print("X " + i + ": [ ");
            for(int j=0;j<this.length;j++){
                System.out.print("Coluna " + j + "= (");
                System.out.print("Rugosidade: " + this.grid[i][j].roughness);
                System.out.print("Helium: " + this.grid[i][j].heliumConcentration);
                System.out.print(")");
            }
            System.out.println(" ]");
        }
    }

}
