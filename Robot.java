public class Robot {
    Position position;
    public char[] name;
    public int numberOfBarrels;
    public int inactiveTimeRemaining;

    public int[] getPosition(){
        return new int[]{this.position.x,this.position.y};
    }
    public float getHeliumConcentration(Terrain terrain){
        return terrain.grid[position.x][position.y].heliumConcentration;
    }
    public float getFollowingRoughness(Terrain terrain){
        switch(this.position.orientation) {
            case 0:
                if(position.y == terrain.width)
                    break;
                return terrain.grid[position.x][position.y+1].roughness;
            case 1:
                if(position.x == terrain.length)
                    break;
                return terrain.grid[position.x+1][position.y].roughness;
            case 2:
                if(position.y == 0)
                    break;
                return terrain.grid[position.x][position.y-1].roughness;
            case 3:
                if(position.x == 0)
                    break;
                return terrain.grid[position.x-1][position.y].roughness;
            default:
                return 1;
        }
        return 1;
    }
    public int timePassed(Game game){
        return game.secondsPassed;
    }

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

    public void mineHelium(Terrain terrain){
        if(terrain.grid[position.x][position.y].unavailableTimeRemaining==0){
            this.inactiveTimeRemaining = terrain.grid[position.x][position.y].getSecondsToMine();
            this.numberOfBarrels += (int) this.getHeliumConcentration(terrain)*10;
            terrain.grid[position.x][position.y].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x-1][position.y].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x+1][position.y].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x][position.y-1].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x][position.y+1].updateMiningStatus(this.inactiveTimeRemaining);
            terrain.grid[position.x][position.y].emptyHelium();
        }
    }


    //Constructor
    public Robot(int x,int y,char[] name) {
        this.name = name;
        this.position = new Position(x,y,0);
        this.numberOfBarrels = 0;
        this.inactiveTimeRemaining = 0;
    }
}
