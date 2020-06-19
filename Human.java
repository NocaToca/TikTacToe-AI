import java.util.Scanner;
/**
 * Write a description of class Human here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Human extends Players
{
    public int player;
    static int loc;
    static Scanner input = new Scanner(System.in); //Scanner 
    public Human(int playerNumber){
    
        super("Human");
        player = playerNumber;
    
    }
    
    public String getPlayer(){
    
        return "player"+player;
    
    }
    
    public int pickLoc(char [][] board){
        System.out.println("Enter Location(1-9): ");                     
        loc = input.nextInt();
        return loc;
        
    }
}
