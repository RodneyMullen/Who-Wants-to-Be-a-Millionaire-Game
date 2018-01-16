/*
 * Game.java
 *
 * Created on 13 September 2007, 14:11
 *
 * This is an abstact class taking in all the functions similar to all games possible 
 * with WhoWantsTo.  main game classes extends this class
 * 
 */

package whowantsto;
import java.util.*;

/**
 *
 * @author Rodney
 */
public abstract class Game 
{
    
    public Player Player; // Player object
    public Questions Question; // Question object for any question asked
    public WhoWantsTo WhoWantsTo;   //main window instance
    public DisplayGUI DisplayGUI; // displaygui for extra messages  while game screen is running
    public Vector<String> Multiplayers; // used for multiplayers
    /** Empty Constructor */
    public Game() 
    {
       
    }
    
    public void setWhoWantsToInstance(WhoWantsTo WWT_instance)
    {
         WhoWantsTo = WWT_instance;
    }
    
    
    //Method used to begin game
    public void startGame(Vector<String> Playernames)
    {
        setUpPlayers(Playernames);
        
        WhoWantsTo.setGameStart();
    }
    
   
    
    //finishes the game, takes in Player object of game details to post to leader board
    public void endGame()
    {
        WhoWantsTo.setEndGame();
        lastAction();
    }
    
    /* gets a gui to ask for input from the audience about the answers and then 
     * displays the results in the far right panel
     */
    public void askTheAudience()
    {
        DisplayGUI = new DisplayGUI();
        DisplayGUI.askTheAudienceGUI(WhoWantsTo);
        Player.usedLifeLine(2);
    }
    
    //gets program to pick two random wrong answers and removes them
    public void ask5050()
    {
        Player.usedLifeLine(0);
    }
    
    //displays a timelimit to ask a friend in audience
    public void askAFriend(int count)
    {
        DisplayGUI = new DisplayGUI();
        DisplayGUI.showTimer(count,WhoWantsTo);
        Player.usedLifeLine(1);
    }
    
    // edits current player name
    public void editPlayerName()
    {
        
        
            DisplayGUI = new DisplayGUI();
            DisplayGUI.editPlayerOne(Player,WhoWantsTo);
        
            Player = DisplayGUI.getPlayer();
           
        
       
       
       
        
    }
    
    public void editMultiPlayerNames()
    {
        DisplayGUI = new DisplayGUI();
        DisplayGUI.editMultiPlayerNames(Multiplayers,WhoWantsTo);
        Multiplayers = DisplayGUI.getMultiPlayerNames();
    }
    
    
    //abstract method to show last actions to be taken for games.
    public abstract void lastAction();
    
    //abstract method to start the protocols at the start of a particular game 
    public abstract void startGameProtocol();
    
    public abstract void setUpPlayers(Vector<String> Playernames);
    
}
