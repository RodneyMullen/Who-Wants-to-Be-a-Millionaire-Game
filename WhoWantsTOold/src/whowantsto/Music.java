/*
 * Music.java
 * 
 * Created on 24-Sep-2007, 16:27:46
 * 
 * Plays the music for WhoWants to
 */

package whowantsto;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.*;
/**
 *
 * @author Rodney
 */
public class Music
{
    private String startup = "music/0-Commercials.mp3";
    private String enternames = "music/0-Theme.mp3";
    private String explain = "music/0-Explain.mp3";
    // for each of the areas : letsplay, Background, final, win, lose
    // need more easy music
    private String[] easymusic = new String[] {"music/1-Letsplay.mp3","music/1-Background.mp3","","music/1-Win.mp3","music/1-Lose.mp3"};
    private String[] mediummusic = new String[] {"music/2-Letsplay.mp3","music/2-Background.mp3","music/2-Final.mp3","music/2-Win.mp3","music/2-Lose.mp3"};
    private String[] hardmusic = new String[] {"music/3-Letsplay.mp3","music/3-Background.mp3","music/3-Final.mp3", "music/3-Win.mp3","music/3-Lose.mp3"};
    private String progress = "music/1-Progress.mp3";
    private String leaderboard = "music/0-Closing.mp3";
    private String winmillion ="music/0-Winmillion.mp3";
    private String exit = "music/0-Goodbye.mp3";
    private String fiftyfifty = "music/0-5050.mp3";
    private String askafriend = "music/0-Phone.mp3";
    private String asktheaudience = "music/0-asktheaudience.mp3"; // need
    private String asktheaudienceslam = "music/0-asktheaudienceslamp.mp3";
    private boolean continues;
    
    
    
    
    private Player player; 
    // empty contructor
    public Music()
    {}
    
    // this method plays the music for system startup
    public void playStartUp()
    {   
        play(startup);
        
        
    }
      
    // plays end of program music
    public void playSystemClose()
    {
        play(exit);
    }
    // plays enter name music and repeats it
    public void playEnterName()
    {
        
        playRepeat(enternames);
    }
    
    //plays start of game music
    public void playStartOfGame()
    {
        playRepeat(explain);
    }
    
    public void playProgress()
    {
        play(progress);
    }
    
    // plays win million music
    public void playWinMillion()
    {
        play(winmillion);
    }
    // play music for leaderboard
    public void playLeaderBoard()
    {
        playRepeat(leaderboard);
    }
    // plays music for 5050
    public void play5050()
    {
        play(fiftyfifty);
    }
    // plays music for ask a friend
    public void playAskAFriend()
    {
        play(askafriend);
    }
    // takes in an boolean, if true plays the first ask the audience clip
    // if false plays the second slam clip
    public void playAskTheAudience(boolean isfirstclip)
    {
        if(isfirstclip == true)
        {
            playRepeat(asktheaudience);
        }
        else
        {
            play(asktheaudienceslam);
        }
        
    }
    // takes in a file and allows facility to repeat music
    private void playRepeat(String file)
    {
        continues = true;
        final String filename = file;
        final Runnable OuterThread = new Runnable()
        {
             
                public void run() 
                {
                   
                        
                        
                        try 
                        {
                            
                                
                               play(filename);
                                while(continues == true)
                                {
                                    if(player.isComplete() == true)
                                    {
                                        
                                          play(filename);
                                    }
                                   
                                }
                           
                        }
                        
                        catch (Exception e) 
                        {
                            System.err.println(" in Music "+e); 
                        }
                        
                        
                
                
                    
                }
        };
         new Thread(OuterThread).start();
    }
    
    
    /** following method takes in a character to show what level is needed
     * from e for easy, m for medium or h for hard. an int shows what sound is needed
     * from 0 for lets play, 1 for background, 2 for win or 3 for lose
     */
     
    public void playSound(char level, int soundneeded)
    {
        //levels indicate e for easy, me for medium and h for hard questions
        String[] thislevelmusic;
       if(level == 'e')
       {
           thislevelmusic = easymusic;
       }
       else if(level == 'm')
       {
           thislevelmusic = mediummusic;
       }
       else // h
       {
           thislevelmusic = hardmusic;
       }
        
       if(soundneeded == 1) // background music needs to repeat
       {
           playRepeat(thislevelmusic[soundneeded]);
       }
       else // does not need to repeat music
       {
            play(thislevelmusic[soundneeded]);
       }
    }
    
     public void close() 
    {
         if (player != null) 
         {
              player.close(); 
              continues = false;
         }
            
             
     }

    // play the MP3 file to the sound card
    public void play(String filename) 
    {
        try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
        
        // run in new thread to play in background
       final String file = filename;
         final Runnable Thread = new Runnable()
        {
             
                public void run() 
                {
                   
                        
                        
                        try 
                        {
                                
                                player.play(); 
                           
                        }
                        catch (Exception e) 
                        {
                            close(); 
                            play(file); 
                            System.err.println(e); 
                        }
                        
                
                
                    
                }
        };
         new Thread(Thread).start();




    }
    
    
    

}
