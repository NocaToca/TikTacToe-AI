import java.util.*;
/**
 * This is the main AI for the TikTacToe game that you play against. It uses Minimaxing to decide which move is best, and this is done by playing a simulation
 * for each possible move
 *
 * @author (MITWannabe and FoxGirl/AIenthusiast)
 * @version (Friday Apparently)
 */
public class AI extends Players
{
    //Minimizing player = Human
    //Maximizing player = AI
    //MIKE: If you wanna understand Minimaxing better this is a good vide: https://www.youtube.com/watch?v=l-hh51ncgDI
    
    //Empty constructor statement. This whole class is made solely for organization purpopses. 
    public AI(){

        super("AI");
    
    }
    
    
    /**
    * The pickLoc() function is the main function for this class that calls all other functions. Basically, it's main idea is choosing
    * the location from 1-9 of the best location for the AI to go.
    * 
    * The char[][] board is the playing board that's passed in
    */
    public int pickLoc(char [][] board){
        TikTacToe game = new TikTacToe(); //A pseudo game that the AI plays through to determine the best move in the actual game
        int bestScore = Integer.MIN_VALUE; //Setting the best score to the lowest possible value so it HAS to be changed
        char [][] testBoard; //Making a test board for AI to use so it doesnt have to use it
        int[] bestMove = new int[2]; //The array to hold the column number and row of the best possible move
        boolean debug = true; //Debug variable to test things
        
        //This double for loop runs through the whole Tik Tac Toe board
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                
                //However, we don't care unless the spot on the board is open
                if(board[r][c] == ' '){
                    
                    testBoard = copyBoard(board); //Copying the main board to the test board with a method I made
                    int loc = findLoc(r, c); //Finding the relative location based on the board position (see the findLoc() function)
                    game.play(testBoard, loc, "AI", true); //Plays the location in the test game
                    int score = miniMax(testBoard, 0, false, game); //Assigns a score based off of that location (see the miniMax function)
                    
                    //Checks to see if the given score is actually better than the last score, obviously
                    if(score > bestScore){
                        //Debug line to print the score if its changed
                        if(debug){
                        System.out.println("The score is higher than the last and its: " + score + " at " + loc);
                        
                        }

                        bestScore = score; //Setting the best score to the score that was higher than it, so its now the best score
                        
                        //Setting the best board position move
                        bestMove[0] = r;
                        bestMove[1] = c;
                        
                        
                        //This only happens if different moves have the same score
                    } else 
                    if (score == bestScore){
                        //debug statement to see how many of the same scores there are and where
                        if(debug){
                        System.out.println("The score is the same as the last and its: " + score + " at " + loc);
                        
                        }
                        
                        //New position array for this position
                        int[] newScore = new int[2];
                        newScore[0] = r;
                        newScore[1] = c;
                        
                        bestMove = randomizeMove(newScore, bestMove); //Randomizes which position is returned
                    
                    }

                }

            }

        }
        
        //Returns the variable (1-9) assiociated with the board position
        return findLoc(bestMove[0], bestMove[1]);
    }
    
    /**
    *This is the main minimax function. If you dont understand minimaxing, it's going to be confusing, so use this video to help
    *you: https://www.youtube.com/watch?v=l-hh51ncgDI
    *
    *Anywho, the maximzing player is the AI while the minimizing player is the human. This means that when the AI is doing it's turn,
    *it's going to look for the highest score that is possible, which would hopefully be a 1 for it, but most likely a 0 for tie. Then
    *when the AI is calculating the human's turn, it's going to assume the human is going to make the best move they could, and assign
    *the lowest value to that move that is possible, which would be -1. 
    *
    *Need the char [][] for the board, depth is for debug purposes, boolean is to determine if it's the AI or Player's turn, game is for
    *the pseudo-game.
    */
    public int miniMax(char [][] board,int depth, boolean isMaximizing, TikTacToe game){
        boolean debug = false;
        if(debug){
            System.out.println("");
            System.out.println("Depth: " + depth);
        
            game.buildBoard(board); //debug line to see building the board
        
        }
        
        
        //First we have to calculate if the game is over
        
        String gameOver = gameOver(board); //Checking to see if anybody won by returning a string based off of the game state
        if(!gameOver.equals("")){
            if(gameOver.equals("Tie!")){
                
            
                return 0; //A tie would return a score of 0
            
            } else if(gameOver.equals("You lost!")){
            
                return 1; //A win would return a score of 1
            
            } else {
            
                return -1; //A loss would return a score of -1
            
            }
         
        
        }
        
        
        //This if statement runs if its doing the AI's turn
        if(isMaximizing){
            int bestScore = Integer.MIN_VALUE; //Setting the best score to the lowest possible value so something has to be higher
            char [][] testBoard; //Making another test board for more simulations
            
            //These for loops obviously just run through the whole board again
            for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
            
                //sees if theres an empty spot
                if (board[r][c] == ' '){
                    testBoard = copyBoard(board); //Makes a copy of the board
                    int loc = findLoc(r, c); //Finds the location relative to the board location
                    game.play(testBoard, loc, "AI", true); //Plays the test
                    int score = miniMax(testBoard, depth + 1, false, game); //Runs this function again, with the new board
                    
                    //if the new score is greater than the best score it sets it to that again
                    if(score > bestScore){

                        bestScore = score;
                
                }}
                
            }
        
        }
            if(debug){
                
                    System.out.println("Score: " + bestScore); //debug line
                
                }
            return bestScore; //returns the score to the previous itteration 
    
        //This else statement only runs if the AI is calculating what the human player could do
        } else {
        
            int bestScore = Integer.MAX_VALUE; //Since this is trying to find the lowest value, we set the bestScore to the highest number
            char [][] testBoard; //Hello new test board!
            
            //You can guess what this does, running through the board again
            for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
            
                //If there's nothing in the slot, you can guess at this point
                if (board[r][c] == ' '){
                    testBoard = copyBoard(board); //Copying the board onto the test board
                    int loc = findLoc(r, c); //Finding the location
                    game.play(testBoard, loc, "player", true); //Playing on the pseudo-game
                    int score = miniMax(testBoard, depth + 1, true, game); //And calling this function again except its the AIs turn
                    
                    //And it tries to find the lowest score here
                    if(score < bestScore){

                        bestScore = score;
                
                }}
                
            }
        
        }
            if(debug){
                
                    System.out.println("Score: " + bestScore); //debug line
                
                }
            return bestScore; //returning the best score (AKA the lowest score)
    
        
        
        }
    
    }

    /**
    *Copies a char[][]. Takes in a char[][] and copies it out   
    **/
    public char[][] copyBoard(char[][] board){

        char[][] boardCopy = new char[board.length][]; //Making the array

        for(int i = 0; i < board.length; i++){

            boardCopy[i] = board[i].clone(); //Cloning the contents in the array

        }

        return boardCopy; //returning it

    }
    
    /**
    *Finds the location relative to the board position. It's a bunch of if statements that check to see if the position is anywhere
    *on the board. It takes the two positions and puts it into an array (I realize now its useless but I dont see a need to change it)
    *and then based on the numbers it returns 1-9
    */
    public int findLoc(int r, int c){
        int[] pos = new int[2];
        pos[0] = r;
        pos[1] = c;
    
        if(pos[0] == 0 && pos[1] == 0){
        
            return 1;
        
        } else
        if(pos[0] == 0 && pos[1] == 2){
            
            return 2;
            
        } else
        if(pos[0] == 0 && pos[1] == 4){
            
            return 3;
            
       } else
        if(pos[0] == 2 && pos[1] == 0){
            
            return 4;
            
        } else
        if(pos[0] == 2 && pos[1] == 2){
            
            return 5;
            
        } else
        if(pos[0] == 2 && pos[1] == 4){
            
            return 6;
        
        } else
        if(pos[0] == 4 && pos[1] == 0){
            
            return 7;
        
        } else
        if(pos[0] == 4 && pos[1] == 2){
            
            return 8;
          
        } else
        if(pos[0] == 4 && pos[1] == 4){
        
            return 9;
        
        }
        return -1; //This is never gonna happen unless theres an error, but it wouldn't compile without it
    }
    
    /**
    *This is literally an RNG function that just takes two arrays and returns a random one
    */
    public int[] randomizeMove(int[] score1, int[] score2){
    
        int rng = (int)(Math.random()*2)+1; //the rng number
        if(rng == 1){
        
            return score1;
            
        } else {
        
            return score2;
        
        }
    
    }
    
    /**
    *Mostly a copy and paste of the checkWinner() function in the class TikTacToe, but edited to add positions that the simulation has
    *so it can properly check for a winner without messing up the actual game.
    **/
    public String gameOver(char[][] board){
    
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
        
        ArrayList<Integer> playerPos = new ArrayList<Integer>(); //The player's positions
        ArrayList<Integer> AIPos = new ArrayList<Integer>(); //The AI's pos
        
        //Going through the board to see where there are Xs and Os, and assigning the positions relative to that
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
            
                if(board[r][c] == 'X'){
                
                    playerPos.add(findLoc(r, c));
                    
                
                } else if(board[r][c] == 'O'){
                
                    AIPos.add(findLoc(r, c));
                
                }
            
            }}
        
           
        for(List l : winCons){
        
            if(playerPos.containsAll(l)){
                //This happens if the player position has one of the win conditions, which would mean that you won! :D
                return "You won!";
            
            } else if(AIPos.containsAll(l)){
                //This happens if the AI position object has one of the win conditions, which would mean that you lost! D:
                return "You lost!";
            
            } 
        
        }
        if(playerPos.size() + AIPos.size() == 9){
                //This happens if theres no more room on the board for someone to make a move, which would make a tie! -_-
                return "Tie!";
            
            }
        
        return "";
    
    }
}
