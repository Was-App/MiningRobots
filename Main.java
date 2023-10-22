import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args){
        Input entrada = new Input();
        String entry = entrada.readGameSettings("Game.json");
        System.out.println(entry);
    }
}
