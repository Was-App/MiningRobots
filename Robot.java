public class Robot {
    public Position position;
    public int numberOfBarrels;
    public int inactiveTimeRemaining;

    public void turnRight(){
        this.position.orientation = (this.position.orientation+1)%4;
    }

    public void turnLeft(){
        this.position.orientation = (this.position.orientation-1)%4;
    }

    public int walk(Terrain terrain) {
        //Depending on the orientation the robot is facing
        switch(this.position.orientation) {
            case 0:
                //if he's not at the edge, and there isn't already a robot at the destination, walks to destination
                if((this.position.y != terrain.width) && (!terrain.grid[position.x][position.y+1].isOccupied)) {
                    terrain.grid[position.x][position.y].isOccupied = false;
                    this.position.y += 1;
                    terrain.grid[position.x][position.y].isOccupied = true;
                    this.inactiveTimeRemaining = terrain.grid[position.x][position.y].getSecondsToTravelTo();
                    return 0;
                }
                return 1;
            case 1:
                if(this.position.x != terrain.length  && (!terrain.grid[this.position.x][this.position.y+1].isOccupied)){
                    terrain.grid[position.x][position.y].isOccupied = false;
                    this.position.x+=1;
                    terrain.grid[position.x][position.y].isOccupied = true;
                    this.inactiveTimeRemaining = terrain.grid[position.x][position.y].getSecondsToTravelTo();
                    return 0;
                }
                return 1;
            case 2:
                if (this.position.y != 0  && (!terrain.grid[this.position.x][this.position.y+1].isOccupied)) {
                    terrain.grid[position.x][position.y].isOccupied = false;
                    this.position.y -= 1;
                    terrain.grid[position.x][position.y].isOccupied = true;
                    this.inactiveTimeRemaining = terrain.grid[position.x][position.y].getSecondsToTravelTo();
                    return 0;
                }
                return 1;
            case 3:
                if (this.position.x != 0  && (!terrain.grid[this.position.x][this.position.y+1].isOccupied)) {
                    terrain.grid[position.x][position.y].isOccupied = false;
                    this.position.x-=1;
                    terrain.grid[position.x][position.y].isOccupied = true;
                    this.inactiveTimeRemaining = terrain.grid[position.x][position.y].getSecondsToTravelTo();
                    return 0;
                }
                return 1;
            default:
                return 1;
        }
    }

    public void fillBarrels(Cell cell){
        this.numberOfBarrels += (int) cell.heliumConcentration*10;
    }
    public void mineHelium(Terrain terrain){
        if(terrain.grid[position.x][position.y].unavailableTimeRemaining==0){
            this.inactiveTimeRemaining = terrain.grid[position.x][position.y].getSecondsToMine();
            this.fillBarrels(terrain.grid[position.x][position.y]);
            terrain.grid[position.x][position.y].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x-1][position.y].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x+1][position.y].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x][position.y-1].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x][position.y+1].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x][position.y].emptyHelium();
        }
    }

    public void update(){
        if(this.inactiveTimeRemaining==0){
            //fetch instruction
        }else{
            this.inactiveTimeRemaining-=1;
        }
    }

    //Constructor
    public Robot(int x,int y) {
        this.position = new Position(x,y,0);
        this.numberOfBarrels = 0;
        this.inactiveTimeRemaining = 0;
    }
}
