
/**
 * Super class for human and ai. Serve as a template to be overrided by childclasses
 *
 * @author (MITWannabe(Not on Github) and NocaToca AKA FoxGirl/AIenthusiast)
 * @version 6/10/2020
 */
public class Players
{
    String playerType;
    public Players(String type){
    
        playerType = type;
    
    }
    
    public int pickLoc(char [][] board){
    
        return 0;
        
    }
    
    public String getPlayerType(){
    
        return playerType;
        //return String variable Playertype.
    
    }
}
