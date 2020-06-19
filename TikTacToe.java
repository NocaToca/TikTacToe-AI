
import java.math.*;
import java.util.Scanner;
import java.util.*;
/**
 * Write a description of class TikTacToe here.
 *  I finished half the tutorial. https://www.youtube.com/watch?v=gQb3dE-y1S4&t=1590s
 *  I stopped at 15:08. Would you finish it up, so we could start working on some AI stuff?
 *  We got this!! :D :D
 *
 * @author (MITWannabe and FoxGirl/AIenthusiast)
 * @version (Friday Apparently)
 */
public class TikTacToe
{

    static ArrayList<Integer> playerPos = new ArrayList<Integer>(); //The player's positions
    static ArrayList<Integer> AIPos = new ArrayList<Integer>(); //The AI's pos
    static Scanner input = new Scanner(System.in); //Scanner
    static int loc;
    
    //These are basically where the player and the AI have their X or O on the feild. 

    public static void main(String[] args) {
        //Builds a 2D array game board.
        //validates input, checking if it meet the criteria of int 1-9
        //play player and AI
        //Build board
        /*When the program first prints, the board is slightly messed up; however that disapears
        on the second and third trial*/

        char [] [] Board = {{' ', '|', ' ', '|', ' '},
                {'-', '*', '-', '*', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '*', '-', '*', '-'},
                {' ', '|', ' ', '|', ' '}}; //Making the board from Chars

        int k = 1;

        System.out.println("1 for Player v.s. Player, 2 for Player v.s. AI");
        int num = input.nextInt();
        boolean gameOver = false;

        Players player1;
        Players player2;
        

        if (num ==1){
            //Player v.s. Player
            System.out.println("PvP!");
            //Loop-Even and Odd Counter; increments
            //  Player1 or Player2 Input
            player1 = new Human(1);
            player2 = new Human(2);
            while (gameOver = false) {
                if(k % 2 != 0) {//player1.play
                    loc = player1.pickLoc(Board);
                    validate(input, player1.getPlayerType());
                    play(Board, loc, "player1", false); 
                    gameOver = checkWinner("player1");
                    buildBoard(Board);
                }
                else {
                    loc = player2.pickLoc(Board);
                    validate(input, player2.getPlayerType());
                    play(Board, loc, "player2", false); 
                    gameOver = checkWinner("player2");
                    buildBoard(Board);
                }
                k++;
            }

        } else {
            //Player v.s. AI
            System.out.println("PvE!");
            System.out.println("1 for Player First, 2 for AI First");
            int n = input.nextInt();

            if(n == 1){

                //Player First
                System.out.println("PvE!P1!");
                player1 = new Human(1);
                player2 = new AI();
                
                    while (gameOver == false) {
                        if(k % 2 != 0) {//player1.play
                            loc = player1.pickLoc(Board);
                            validate(input, player1.getPlayerType());
                            play(Board, loc, "player1", false); 
                            gameOver = checkWinner("player");
                            buildBoard(Board);
                        }
                        else {
                            loc = player2.pickLoc(Board);
                            validate(input, player2.getPlayerType());
                            play(Board, loc, "AI", false); 
                            gameOver = checkWinner("AI");
                            buildBoard(Board);
                        }
                        k++;
                    }
                

            } else {

                player2 = new Human(1);
                player1 = new AI();
                
                    while (gameOver == false) {
                        if(k % 2 != 0) {//player1.play
                            loc = player1.pickLoc(Board);
                            validate(input, player1.getPlayerType());
                            play(Board, loc, "AI", false); 
                            gameOver = checkWinner("AI");
                            buildBoard(Board);
                        }
                        else {
                            loc = player2.pickLoc(Board);
                            validate(input, player2.getPlayerType());
                            play(Board, loc, "player2", false); 
                            gameOver = checkWinner("player");
                            buildBoard(Board);
                        }
                        k++;
                    }

                
                //The while loop for the game which will run until the winner is determined

            }
        }

        boolean cont = true;
        while(cont = true){

            System.out.println("Play new game? Yes/No");
            String answer = input.nextLine();
            if(answer.equals("Yes") || answer.equals("yes")){
                
                cont = false;
                TikTacToe newGame = new TikTacToe();
                newGame.main(args);

            } else if(answer.equals("No") || answer.equals("no")){
                cont = false;
                return;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
    /**
     * While loop to check if the location is valid either by number or by the spot already having something in it. Asks for another one if it's wrong
     * player = 1 : Player; player = 2: AI or Player 2
     */
    public static void validate(Scanner input, String player) {
        if(player.equals("Human")) {   
            while (loc < 1 || loc > 9 || playerPos.contains(loc) || AIPos.contains(loc)) {
                System.out.println("Error: Please print a valid location: ");
                loc = input.nextInt();
            }
        }
        else {

        }
    }

    /**
     *This function serves as the board building function. It builds it by rows and uses the 2D array we have to check what char is supposed to be where.
     *
     */
    public static void buildBoard(char [][] Board) {
        //iterates through the 2D array and printout each character from each array element
        for(int r = 0; r<5;++r) {
            for(int c = 0; c<5; ++c) {
                System.out.print(Board[r][c]);
            }
            System.out.println();
        }
    }

    public static void play(char [][] Board, int loc, String player, boolean simulation){
        //Switch statement its basically if else, but you do not need to type so many if elses
        /* Basically this is how it works: switch(variable). The variable in here is like the
         * if's variable (variable == 6). And case 'value' is each possible equal value
         * Its analogous to if(x==6) {do ...} e;se if (x==3) {do ...} 
         * switch(x) case 6 do ... case 3 do ... and default is just the else statement
         */
        char token = ' '; //This is the token char that is to be edited based on the player

        if (!player.equals("AI")) {
            token = 'X'; //Players are Xs
            if(!simulation){
                playerPos.add(loc); //Adds the location used to the positions that the player put
            }

        } else {
            token = 'O'; //AI's are Os for oranges
            if(!simulation){
                AIPos.add(loc); //Adds the location used to the positions that the AI put! :D
            }

        }
        //Check player or AI
        //If the user inputs 1-9, put it to the desinated location on the board with correct token
        //Mike your comments are bad bc I'm too dumb to understand them. But this switch basically puts a the token that's an X or an O at the relative spot
        //For reference:
        /*
        1|2|3
        -*-*-
        4|5|6
        -*-*-
        7|8|9
         */
        switch(loc) {
            case 1:
            Board[0][0] = token;
            break;
            case 2:
            Board[0][2] = token;
            break;
            case 3:
            Board[0][4] = token;
            break;
            case 4:
            Board[2][0] = token;
            break;
            case 5:
            Board[2][2] = token;
            break;
            case 6:
            Board[2][4] = token;
            break;
            case 7:
            Board[4][0] = token;
            break;
            case 8:
            Board[4][2] = token;
            break;
            case 9:
            Board[4][4] = token;
            break;
            default:
            break;
        }
    }

    /**
     * This function is fun! It basically creates a whole bunch of List objects that contain integers that relate to how you can win on the board. If the position
     * ArrayList we made contain these integers, then the owner of that position array list wins!
     * 
     * For example: if the player has the top row that is position 1, 2, and 3. And so the ArrayList contains those numbers and matches with List topRow!
     *
     */
    public static boolean checkWinner(String player){

        String result = "";
        //Row win conditions:
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        //Column win conditions:
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List RightCol = Arrays.asList(3, 6, 9);

        //Cross win conditions:
        List rightCross = Arrays.asList(1, 5, 9);
        List leftCross = Arrays.asList(3, 5, 7);

        //Making a list of all of the win conditions and adding all of these ones to that list!
        List<List> winCons = new ArrayList<List>();
        winCons.add(topRow);
        winCons.add(middleRow);
        winCons.add(botRow);
        winCons.add(leftCol);
        winCons.add(midCol);
        winCons.add(RightCol);
        winCons.add(rightCross);
        winCons.add(leftCross);

        //This for loop then checks whether or not a position object has any of the win conditions and it prints a message relative to that
        for(List l : winCons){

            if(playerPos.containsAll(l)){
                //This happens if the player position has one of the win conditions, which would mean that you won! :D
                result = player + " won!";
                

            } else if(AIPos.containsAll(l)){
                //This happens if the AI position object has one of the win conditions, which would mean that you lost! D:
                result = player + " won!";
                

            } 
        }
        if(playerPos.size() + AIPos.size() == 9){
            //This happens if theres no more room on the board for someone to make a move, which would make a tie! -_-
            result = "Tie!";
            

        }
        //Returns an empty air string if the game needs continuing. 

        if(!result.equals("")){

            System.out.println(result);
            
            return true;
            

        }
        return false;

    }
    
    

}
