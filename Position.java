class Position {
    public int directionX,directionY,orientation;
    public Position(int directionX,int directionY,int orientation){
        this.directionX = x;
        this.directionY = y;
        this.orientation = orientation;
    }
    public void moveForward(Cell[][] terrain){
        int newDirectionX = directionX;
        int newDirectionY = directionY;

        if(orientation == 0){ //norte
            newDirectionY = Math.max(0, directionY - 1);
        } else if (orientation == 2) {//sul
            newDirectionY = Math.min(terrain[0].length - 1, directionY + 1 );
        } else if (orientation == 1) {//leste
            newDirectionX = Math.min(terrain.length - 1, directionX+1);
        } else if (orientation == 3) {//oeste
            newDirectionX = Math.max(0, directionX - 1);
        }

        double timeCost = terrain[newDirectionX][newDirectionY].getTerrainRoughness();

        directionX = newDirectionX;
        directionY = newDirectionY;

        int timeSpent = Math.max(1, (int) (timeCost * 10));
        isProspecting = false;
    }
    public void turnRight(){
        orientation = (orientation + 1) % 4;
    }
    public void turnLeft(){
        orientation = (orientation +3) % 4;
    }
}
