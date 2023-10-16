public class Controller {
    Robot controlledRobot;

    public void update(){
        if(controlledRobot.inactiveTimeRemaining==0){
            //fetch instruction
        }else{
            controlledRobot.inactiveTimeRemaining-=1;
        }
    }

    public Controller(String name){

    }


}
