public class Controller {
    Robot controlledRobot;
    String[] instructions;

    public void update(){
        if(controlledRobot.inactiveTimeRemaining==0){
            while(this.inactiveTimeRemaining == 0) {
                //fetch instruction
            }
        }else{
            controlledRobot.inactiveTimeRemaining-=1;
        }
    }

    public Controller(String name){

    }


}
