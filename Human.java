import java.util.Scanner;
/**
 * Human class inherits Players class and holds a couple variables and methods for players and methods in TikTacToe.
 *
 * @author (MITWannabe(Not on Github) and NocaToca AKA FoxGirl/AIenthusiast)
 * @version 6/10/2020
 */
public class Human extends Players
{
    public int player;
    static int loc;
    static Scanner input = new Scanner(System.in); //Scanner 
    public Human(int playerNumber){
        //constructor that uses super/Player's constructor. And determines player number.
        super("Human");
        player = playerNumber;
        
    }
    
    public String getPlayer(){
    
        return "player"+player;
        // return string player
    }
    
    public int pickLoc(char [][] board){
        System.out.println("Enter Location(1-9): ");                     
        loc = input.nextInt();
        return loc;
        //take in an location
    }
}
