package whowantsto;
/*
 * ScoreBoard.java
 *
 * Created on 06 September 2007, 15:48
 *
 * Used to allow access and update scoreboard on the main game screen for WhoWantsTo. game.
 * scores are shown and current score is highlighted. as questions progress scores 
 * also progress. 
 */

/**
 *
 * @author Rodney
 */
public class ScoreBoard 
{
    final private String[] scores; 
    final private String[] milestones = new String[] {"€1,000","€32,000","€1 MILLION"}; // milestones
    private int last_answer; // shows the last answer
    private char difficulty; // set to e for easy, m for medium or h for hard. 
    public WhoWantsTo Who_wants_to_game;   //current game
    
    /** Creates a new instance of ScoreBoard */
    public ScoreBoard()
    {   
        this.scores = new String[]{"€0","€100","€200","€300","€500","€1,000","€2,000","€4,000","€8,000","€16,000","€32,000","€64,000","€125,000","€250,000","€500,000","€1 MILLION"};
}
    
    
    public ScoreBoard(WhoWantsTo newWho_wants_to_game) 
    {
        this.scores = new String[]{"€0","€100","€200","€300","€500","€1,000","€2,000","€4,000","€8,000","€16,000","€32,000","€64,000","€125,000","€250,000","€500,000","€1 MILLION"};
        Who_wants_to_game = newWho_wants_to_game;
    }
    
    //loads up the scoreboard on the main gui
    public void loadScoreBoard()
    {
        this.last_answer = 0;
        this.difficulty = 'e';
        Who_wants_to_game.setGameScorePanels(scores);
        for(int i=15; i> 0 ; i--) // loads all cells in starting formaion
        {
            Who_wants_to_game.setScoreCells('s', i);
        }
        
    }
    
    //method to advance score board, changes two cells as needed
    public void nextQuestion()
    {
        
        if(this.last_answer == 0) // beginning of game
        {
            this.last_answer= this.last_answer+1;
            Who_wants_to_game.setScoreCells('l', this.last_answer);
            
        }
        else
        {
             
             Who_wants_to_game.setScoreCells('a', last_answer);
             this.last_answer= this.last_answer+1;
             Who_wants_to_game.setScoreCells('l', this.last_answer);
             
        }
        checkDifficulty();
    }
    
    //returns the the difficulty
    public char getDifficulty()
    {
       
        return this.difficulty;
       
    }
    
    //checks against questions and sets the current diffiulty rating
    private void checkDifficulty()
    {
        if(this.last_answer < 5) // easy questions
        {
            this.difficulty = 'e';
        }
        else if(this.last_answer >=5 && this.last_answer < 10) // medium questions
        {
            this.difficulty = 'm';
        }
        else // hard questions
        {
            this.difficulty = 'h';
        }
    }
    
     //returns current score in string format i.e. �1,000
    public String getScoreString()
    {
        
        return scores[last_answer];
    }
    
    //returns current score in int format i.e. 4 for �1,000
    public int getScoreInt()
    {
        return this.last_answer;
    }
    
    // takes in an int and returns its string representation
    public String intToString(int score)
    {
        return scores[score];
    }
    
    //returns true if reached last quesiton
    public boolean isLastQuestion()
    {
        return this.last_answer == 15;
        
    }
    
    //returns true if the next question is the last question
    public boolean isNextLastQuestion()
    {
        int nextanswer = last_answer+1;
        return nextanswer == 15;
        
    }
    
    //works out current score if got question wrong
    public int workOutScore()
    {
        if(this.last_answer <5) // �100 - �500
        {
            return 0;
        }
        else if(this.last_answer >= 5 && this.last_answer <10) // �1,000 - �16,000
        {
            return 5;
        }
        else if(this.last_answer >= 10 && this.last_answer <15) // �32,000 - �500,000
        {
            return 10;
        }
        else // �1 MILLION
        {
            return 15;
        }
    }
    // returns true if current value is a milestone
    public boolean isMilestone()
    {
        return this.last_answer == 5 || this.last_answer == 10;
    }
    
    // returns true if current value is a milestone
    public boolean isNextQuestionMilestone()
    {
        int nextanswer = this.last_answer+1;
        return nextanswer == 5 || nextanswer == 10;
    }
    
}
