/*
 * GameMultiPlayer.java
 * 
 * Created on 19-Sep-2007, 16:19:14
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package whowantsto;
import java.util.*;
/**
 *
 * @author Rodney
 */
public class GameMultiPlayer extends Game
{
    
    PlayerBoard Multiplayerboard; // unique player board for multiplayer
     // used to lis names of players in multiplayer
//needed method from Game,  begins new protocols for a game with one player
    @Override
    public void startGameProtocol()
    {
       Multiplayers = new ArrayList<>();
       Multiplayerboard = new PlayerBoard();
       WhoWantsTo.enterMultiNamePanel();
        
    }
    
    //needed method from Game, ends game protocol
    @Override
    public void lastAction()
    {
        
        Multiplayerboard.updatePlayerBoard(Player);
        
        if(Multiplayers.isEmpty() == false)
        {
            getNextPlayer();
             WhoWantsTo.setGameStart();
        }
        else
        {
            WhoWantsTo.showMultiPlayerLeaderBoard(Multiplayerboard);
            WhoWantsTo.Playerboard.combinePlayerBoards(Multiplayerboard);
            WhoWantsTo.Playerboard.writePlayerBoardToFile();
        }
        
       
    }
    
    // takes in a vector and creates a new Player instance
    @Override
    public void setUpPlayers(ArrayList<String> Playername)
    {
        Multiplayers = Playername;
        getNextPlayer();
        
    }
    // places next player in the multiplayer vector, returns null if 
    // no more players exist
    public void getNextPlayer()
    {
        Player = new Player(Multiplayers.remove(0));
       
    }
    
    // returns the vector string of multiplayers
    public ArrayList<String> getMultiPlayerNames()
    {
        return Multiplayers;
    }
    
    // takes in a vector string and replaces current vector of multiplayers
    public void replaceMultiPlayerNames(ArrayList<String> names)
    {
        Multiplayers = names;
    }

    
}    