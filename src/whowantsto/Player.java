/*
 * Player.java
 *
 * Created on 30 August 2007, 14:21
 *
 * This class creates instances of each player in a multiplayer of WhoWantsTo. 
 */
package whowantsto;
import java.util.*;

/**
 *
 * @author Rodney
 */


public class Player 
{
    private String playername; // string of name of player
    private int score; // score achieved 0-15
    private int score_reached; // score that they reached in the game 0-15
    private boolean[] lifelines; // 3 array of lifelines , first represents 50:50, second ask a friend , and third is ask the audience
    
    /** Constructor, creates a new instance of Player with no input*/
    public Player() 
    {
        lifelines = new boolean[] {false,false,false};
        score = 0;
        score_reached = 0;
     }
    
    //Consructor, creates a new instance of player with name input
    public Player(String newname)
    {
        lifelines = new boolean[] {false,false,false};
        score = 0;
        score_reached = 0;
        playername = newname;
    }
    
    //This method allows the change of player name
    public void changePlayerName(String newname)
    {
        playername = newname;
    }
    
    //changes player's score
    public void changePlayerScore(int newscore)
    {
        score = newscore;
    }
    
    //changes this player's score reached
    public void changePlayerScoreReached(int newscore_reached)
    {
        score_reached = newscore_reached;
    }
    
    //Takes in an int to represent life line used, 0 for 50:50, 1 for ask a friend, and 2 for ask the audience
    public void usedLifeLine(int lifelineused)
    {
        lifelines[lifelineused] = true;
    }
    
    //changes array of lifelines used, inputs are 50:50,phone a friend, ask the audience
    public void changePlayerLifeLines(boolean inputone, boolean inputtwo, boolean inputthree)
    {
        lifelines = new boolean[] {inputone,inputtwo,inputthree};
    }
    
    //this method returns player name
    public String getPlayerName()
    {
        return playername;
    }
    
    //returns this player's score
    public int getPlayerScore()
    {
        return score;
     }
    
    //returns this player's score reached
    public int getPlayerScoreReached()
    {
        return score_reached;
    }
    
    //returns array of lifelines
    public boolean[] getPlayerLifelines()
    {
        return lifelines;
    }
    
    //returns a number of lifelines used 0,1,2,3
    public int getNumberOfLifeLinesUsed()
    {
        int count = 0;
        for(boolean value: lifelines)
        {
            if(value == true)
            {
                count++;
            }
        }
        return count;
    }
    
    //converts current instance into a string for output to file
    public String convertToString()
    {
        String convertion = playername+":@:"+score+":@:"+score_reached+":@:"+lifelines[0]+":@:"+lifelines[1]+":@:"+lifelines[2];
        return convertion;
    }
    
    //checks current player if a score has been made, if not returns false
    public boolean hasScoredYet()
    {
        return score != 0;
    }
    
    public Player convertFromString(String convert)
    {
        
        StringTokenizer ST = new StringTokenizer(convert,":@:");
        
        
        this.changePlayerName(ST.nextToken());
        this.changePlayerScore(Integer.parseInt(ST.nextToken()));
        this.changePlayerScoreReached(Integer.parseInt(ST.nextToken()));
        this.changePlayerLifeLines(Boolean.parseBoolean(ST.nextToken()),Boolean.parseBoolean(ST.nextToken()),Boolean.parseBoolean(ST.nextToken()));
       
        return this;
        
   }
    
    
    
    
    
    
}
