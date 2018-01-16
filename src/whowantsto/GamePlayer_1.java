/*
 * GamePlayer.java
 *
 * Created on 13 September 2007, 16:20
 *
 * extends Game, this class is used for the individual settings for Who Wants To. 
 * This allows access to name entry board and audience number. Then when ends access to last 
 * board and back to title screen
 */

package whowantsto;
import java.util.*;
/**
 *
 * @author Rodney
 */
public class GamePlayer_1 extends Game
{
    
    //needed method from Game,  begins new protocols for a game with one player
    public void startGameProtocol()
    {
       
       WhoWantsTo.setEnterNamePanel();
        
    }
    
    //needed method from Game, ends game protocol
    public void lastAction()
    {
        
        WhoWantsTo.Playerboard.updatePlayerBoard(Player);
        
        WhoWantsTo.Playerboard.writePlayerBoardToFile();
       
        WhoWantsTo.showLeaderBoard();
    }
    
    // takes in a vector and creates a new Player instance
    public void setUpPlayers(Vector Playername)
    {
        String blank = "";
        if(blank.compareTo((String) Playername.elementAt(0))==0)
        {
              Player = new Player("Player");
        }
        else
        {
             Player = new Player((String) Playername.elementAt(0));
        }
       
       
    }
    
    
}
