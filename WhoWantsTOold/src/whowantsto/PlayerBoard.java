/*
 * PlayerBoard.java
 *
 * Created on 05 September 2007, 15:05
 *
 * This class gives access to all options involving player and audience number for WhoWantsTo
 */

package whowantsto;
import java.util.*;
import java.io.*;
/**
 *
 * @author Rodney
 */
public class PlayerBoard 
{
    
    private Vector<Player> PlayersBoardVector; // vector of Player Objects holding the player
   
    
    
    // constructor to intialise PlayersBoardVector and to get the audience number from version.dat
    public PlayerBoard()
    {
            PlayersBoardVector = new Vector<Player>();
            
             
    }
    
     // populates player board with previous scoreboard
    public void populatePlayerBoard() 
    {
        
        try
        {
            BufferedReader Readfile = new BufferedReader(new FileReader("files/playerboard.dat"));
            String line = Readfile.readLine();
             while(line != null)
            {
                
                Player Tempplayer = new Player();
                Tempplayer.convertFromString(line);
                line = Readfile.readLine();
                PlayersBoardVector.add(Tempplayer);
            }
           
            Readfile.close();
            
        }
        catch(IOException ioe)
        {
            DisplayGUI ErrorGui = new DisplayGUI();
            ErrorGui.errorMessage("File playerboard.dat could not be read");
           
        }
    }
    
    // This method returns a vector of Player objects for the player boardplayer information.
    public Vector returnPlayerBoard()
    {
        return PlayersBoardVector;
    }
    
    //takes in vector and changes current player board
    public void createNewPlayerBoard(Vector<Player> Newboard)
    {
        PlayersBoardVector = Newboard;
    }
    
    //after each finished game current player board must be written to file
    public boolean writePlayerBoardToFile()
    {
       
        try
        {
            PrintWriter Print_to_file= new PrintWriter(new FileWriter("files/playerboard.dat"));
           
        
            for(int i=0; i< PlayersBoardVector.size(); i++ )
            {
                Player TempPlayer = PlayersBoardVector.elementAt(i);
                Print_to_file.println(TempPlayer.convertToString());
            }
            Print_to_file.close();
            return true;
        }
        catch(IOException ioe)
        {
            DisplayGUI ErrorGui = new DisplayGUI();
            ErrorGui.errorMessage("File playerboard.dat could not be written to");
            return false;
        }
       
        
    }
    
    
     /*takes in a player object and finds where to place on leader board. 
         if no other players have stats place at top, otherwise use amount reached, 
         if equal use lifelines used, if equal use amonut reached. if equal show as 
         same spot under previous entry
         */
    public void updatePlayerBoard(Player Player)
    {
        
        int amount;
        int score_values=0; // used to determine which value to check
        boolean position_found = false;
        int get_position =0; // used as a postion marker for vector for new player
        if(PlayersBoardVector.isEmpty() == false)
        {
            while(position_found == false)
            {
                if(get_position >= PlayersBoardVector.size())
                {
                    break;
                }
               
                if(score_values == 0) // amount is player score to compare
                {
                    
                    amount = Player.getPlayerScore();
                    Player PlayerToCheck = PlayersBoardVector.elementAt(get_position);
                    if(PlayerToCheck.getPlayerScore() == amount)
                    {
                        score_values = 1; // next phase if equal
                    }
                    else if(PlayerToCheck.getPlayerScore() < amount)
                    {
                        position_found = true;
                       
                    }
                    else // less then 
                    {
                      get_position++;
                    }
                
                }
                else if(score_values == 1) // second phase, check lifelines used
                {
                    amount = Player.getNumberOfLifeLinesUsed();
                    // better to have less lifelines used
                    Player PlayerToCheck = PlayersBoardVector.elementAt(get_position);
                    if(PlayerToCheck.getNumberOfLifeLinesUsed() == amount)
                    {
                        score_values = 2; // next phase if equal
                    }
                    else if(PlayerToCheck.getNumberOfLifeLinesUsed() > amount)
                    {
                        position_found = true;
                       
                    }
                    else // less then do nothing
                    {
                        get_position++;
                        score_values = 0;
                     }
                    
                }
                else //scorevalues = 2
                {
                   amount = Player.getPlayerScoreReached();
                    // amount reached before withdrawl or missing a question
                    Player PlayerToCheck = PlayersBoardVector.elementAt(get_position);
                    if(PlayerToCheck.getPlayerScoreReached() == amount)
                    {
                        position_found = true;
                    }
                    else if(PlayerToCheck.getPlayerScoreReached() < amount)
                    {
                        position_found = true;
                        
                    }
                    else // all equal just pick a spot above check player
                    {
                      
                        get_position++;
                        score_values = 0;
                     }
                }
            }
        }
        PlayersBoardVector.insertElementAt(Player,get_position);
        if(PlayersBoardVector.size() > 8)
        {
            PlayersBoardVector.setSize(8);   // only top 10 are kept
        }
       
        
    }
    
    
    
   
    
    //takes in a playerboard from multiplayer and combines with current board
    public void combinePlayerBoards(PlayerBoard Multi_player_board)
    {
        Vector Multiboard = Multi_player_board.returnPlayerBoard();
        for(Object value : Multiboard)
        {
            Player Player = (Player) value;
            updatePlayerBoard(Player);
            if(isLastElement(Player)==true)
            {
                break;
            }
        }
    }
    // takes in a Player and finds if it is the last element on the playerboard
    public boolean isLastElement(Player Player)
    {
        
        if(Player.equals(PlayersBoardVector.lastElement())==true)
        {
            return true;
        }
        
        for(Object value: PlayersBoardVector)
        {
            if(Player.equals((Player) value)==true)
            {
                return false;
            }
        }
        return true;
        
        
    }
    
}
