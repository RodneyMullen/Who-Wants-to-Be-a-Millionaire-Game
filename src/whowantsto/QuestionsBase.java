/*
 * QuestionsBase.java
 *
 * Created on 30 August 2007, 15:13
 *
 * this class gives access to the questions. for WHOWANTSTO
 */

package whowantsto;
import java.sql.*;
import java.io.*;
import java.util.*;


/**
 *
 * @author Rodney
 */
public class QuestionsBase 
{
    private boolean[] easyquestions; // is edited when easy questions are answered
    private boolean[] mediumquestions; // is edited when medium questions are answered
    private boolean[] hardquestions; // is edited when hard questions are answered
    private int easycount = 0; //used to count number of questions asked in easy questions
    private int mediumcount = 0; // used to count number of questions asked in medium quesitons
    private int hardcount = 0 ;//used to count number of questions asked in hard questions.
    private int easy_number_of_questions; // number of questions in the easy table
    private int medium_number_of_questions; // number of questions in the medium table
    private int hard_number_of_questions; // number of questions in the hard table
    private int ask_a_friend_timelimit; // time in seconds for ask a friend, default is set to 20 
    private String databasefilename = "files/QuestionsBase.mdb";
    private AccessJDBCUtil DB; // a database utility for easyquestions.
    ResultSet Resultset; // resultset used for database connectivity
    //construtor, creates the question array variables and count varialbles
    public QuestionsBase() 
    {
        
        
        
        /* access database to find number of questions in each question table 
         and creates arrays for each indicating what questions has been asked
         */
        DB = new AccessJDBCUtil(databasefilename);
         Resultset = DB.setUpResultSet("Select Count(*) from Easy_questions");
         
        try
        {
            Resultset.first();
            easy_number_of_questions =  Resultset.getInt(1);
            Resultset.close();
            easyquestions = new boolean[easy_number_of_questions];
            for(int i = 0; i<easy_number_of_questions; i++)
            {
                
                easyquestions[i] = false;
            }
             Resultset = DB.setUpResultSet("Select Count(*) from Medium_questions");
             Resultset.first();
            medium_number_of_questions =  Resultset.getInt(1);
            Resultset.close();
            mediumquestions = new boolean[medium_number_of_questions];
            for(int i = 0; i<medium_number_of_questions; i++)
            {
                mediumquestions[i] = false;
            }
             Resultset = DB.setUpResultSet("Select Count(*) from Hard_questions");
            Resultset.first();
            hard_number_of_questions =  Resultset.getInt(1);
            Resultset.close();
            hardquestions = new boolean[hard_number_of_questions];
            for(int i = 0; i<hard_number_of_questions; i++)
            {
                hardquestions[i] = false;
            }
        }
        catch(SQLException sqle)
        {
            DisplayGUI ErrorGui = new DisplayGUI();
            ErrorGui.errorMessage("Database or SQL error encountered: "+sqle);
             
        }
         String easyline = "";
         String mediumline= "";
         String hardline= "";
         try
         {
            BufferedReader Readfile = new BufferedReader(new FileReader("files/answered_questions.dat"));
            easyline = Readfile.readLine();
            mediumline = Readfile.readLine();
            hardline = Readfile.readLine();
            Readfile.close();
            
            Readfile = new BufferedReader(new FileReader("files/version.dat"));
            Readfile.readLine(); // version of program do not need
            ask_a_friend_timelimit = Integer.parseInt(Readfile.readLine());
            Readfile.close();
         }
         catch(IOException ioe)
         {
             DisplayGUI ErrorGui = new DisplayGUI();
             if(hardline.length() == 0)
             {
                  ErrorGui.errorMessage("File answered_questions.dat could not be read");
             }
             else
             {
                 ErrorGui.errorMessage("File version.dat could not be read");
             }
             
         }
         StringTokenizer ST;
         int questiontoken;
        
         if(easyline != null)
         {
             
         
            ST = new StringTokenizer(easyline," ");
            
            while(ST.hasMoreTokens())
            {
                questiontoken = Integer.parseInt(ST.nextToken());
                easyquestions[questiontoken] = true;
             
            }
         }
         
         if(mediumline != null)
         {
            ST = new StringTokenizer(mediumline," ");
            while(ST.hasMoreTokens())
            {
                 questiontoken = Integer.parseInt(ST.nextToken());
                 mediumquestions[questiontoken] = true;
             
            }
         }
         
         if(hardline != null)
         {
            ST = new StringTokenizer(hardline," ");
            while(ST.hasMoreTokens())
            {
                questiontoken = Integer.parseInt(ST.nextToken());
                hardquestions[questiontoken] = true;
             
            }
         }
        
          
    }
    
    /* This method clears the associated array of questions{} to 0 
     for unanswered for easy medium and hard depending on char inputed; 
     e for easy, m for medium or h for hard
    */
    public boolean clearQuestionArray(char charinput)
    {
        
        if(charinput == 'e')
        {
            for(int e=0; e<easyquestions.length; e++)
            {
                easyquestions[e] = false;
            }
            easycount = 0;
            writeQuestionArrayToFile();
            return true;
        }
        else if(charinput == 'm')
        {
             for(int m=0; m<mediumquestions.length; m++)
            {
                mediumquestions[m] = false;
            }
            mediumcount = 0;
            writeQuestionArrayToFile();
            return true;
        }
        else if(charinput == 'h')
        {
             for(int h=0; h<hardquestions.length; h++)
            {
                hardquestions[h] = false;
            }
            hardcount = 0;
            writeQuestionArrayToFile();
            return true;
        }
        else
        {
            return false;
        }
        
        
    }
    
    
    
    //This method re-writes all question[] arrays and sets to 0 
    public boolean clearAllQuestionArrays()
    {
        
        if(clearQuestionArray('e')==true)
        {
            if(clearQuestionArray('m')==true)
            {
                if(clearQuestionArray('h')==true)
                {
                    
                    return true;
                }
            }
        }
        return false;
        
            
    }
    
    
    /* takes in an interger and changes entry in questions array 
     depending on char to indicate easy, medium or hard from 0 to 1
     */
    public void updateQuestionArray(int questioninput, char difficultyinput)
    {
        if(difficultyinput == 'e')
        {
            easyquestions[questioninput] = true;
            easycount++;
        }
        else if(difficultyinput == 'm')
        {
            mediumquestions[questioninput] = true;
            mediumcount++;
        }
        else
        {
            hardquestions[questioninput] = true;
            hardcount++;
        }
    }
    
    
    /*takes in an int, a char for type of question and sees if questions 
     has been asked, if it has returns true
     **/
    public boolean checkQuestionArray(int questioninput, char difficultyinput)
    {
        
        if(difficultyinput == 'e')
        {
            return easyquestions[questioninput] == true;
                     
        }
        else if(difficultyinput == 'm')
        {
            return mediumquestions[questioninput] == true;
                           
        }
        else
        {
            return hardquestions[questioninput] == true;
            
        }
    }
    
    
    
   /* needed after every game, rewrites answered_questions.dat to update 
    file of questions asked so not repeated 
    */
    public boolean writeQuestionArrayToFile()
    {
        String easyline ="";
        String mediumline ="";
        String hardline ="";
        for(int i = 0; i<easyquestions.length; i++)
        {
            if(easyquestions[i] == true)
            {
                 easyline = easyline+" "+i;
            }
           
        }
        for(int i = 0; i<mediumquestions.length; i++)
        {
            if(mediumquestions[i] == true)
            {
                 mediumline = mediumline+" "+i;
            }
           
        }
        
        for(int i = 0; i<hardquestions.length; i++)
        {
            if(hardquestions[i] == true)
            {
                 hardline = hardline+" "+i;
            }
           
        }
        try(PrintWriter Print_to_file= new PrintWriter(new FileWriter("files/answered_questions.dat")))
        {
            
            Print_to_file.println(easyline);
            Print_to_file.println(mediumline);
            Print_to_file.println(hardline);
            Print_to_file.close();
            
        }
        catch(IOException ioe)
        {
            DisplayGUI ErrorGui = new DisplayGUI();
            ErrorGui.errorMessage("File answered_questions.dat could not be written to");
            return false;
        }
        
        return true;
        
        
    }
    
    /* takes in a char and checks if the corresponding easy, medium or 
     hard question array is full of true's, if so then blanks array 
     with other method amd returns int for questions not asked
     */
    private int checkIfAllQuestionsAsked(char difficultyinput)
    {
        double doublerandom;
         int randomnumber;
        
        if(difficultyinput == 'e')
        {
            for(int i=0; i< easyquestions.length; i++)
            {
                if(easyquestions[i] == false)
                {
                    return i;
                }
            }
            // no entry found that is false clear all and pick randome number
            clearQuestionArray('e');
            doublerandom = Math.random()*100;
            randomnumber = ((int)doublerandom)% easyquestions.length;
            return randomnumber;
        }
         else if(difficultyinput == 'm')
        {
            for(int i=0; i< mediumquestions.length; i++)
            {
                if(mediumquestions[i] == false)
                {
                    return i;
                }
            }
            // no entry found that is false clear all and pick randome number
            clearQuestionArray('m');
            doublerandom = Math.random()*100;
            randomnumber = ((int)doublerandom)% mediumquestions.length;
            return randomnumber;
        }
        else
        {
            for(int i=0; i< hardquestions.length; i++)
            {
                if(hardquestions[i] == false)
                {
                    return i;
                }
            }
            // no entry found that is false clear all and pick randome number
            clearQuestionArray('h');
            doublerandom = Math.random()*100;
            randomnumber = ((int)doublerandom)% hardquestions.length;
            return randomnumber;
        }
         
         
    }
    
    
    /* accesses the database with interger and char e for easy, 
     m for medium or h for hard for question and returns Question
     */
    private Questions retrieveQuestion(int questioninput, char difficultyinput)
    {
        String query;
        Questions Question = new Questions();
       
        
        if(difficultyinput == 'e')
        {
            query = "Select * from Easy_questions";
        }
        else if(difficultyinput == 'm')
        {
             query = "Select * from Medium_questions";
        }
        else
        {
            query = "Select * from Hard_questions";
        }
        Resultset = DB.setUpResultSet(query);
        try
        {
             Resultset.absolute(questioninput+1);
             Question = new Questions(Resultset.getString(1),Resultset.getString(2),Resultset.getString(3),Resultset.getString(4),Resultset.getString(5),Resultset.getString(6));
             Resultset.close();
             
                 
         }
         catch(SQLException sqle)
         {
             DisplayGUI ErrorGui = new DisplayGUI();
             ErrorGui.errorMessage("Database or SQL error encountered: "+sqle);
           
         }
        return Question;
        
        
    }
    
    
    /* finds out what question can be asked, retrieves it from the database and 
     returns the question plus answer
     */
    public Questions askQuestion(char difficultyinput)
    {
        
       // this local varialble allows for quicker access to determine if all 
        //questions have been asked already, on the forth try a search is begun
        int number_of_times = 0;
       int number_of_questions;
       
        double doublerandom = Math.random()*100;
        int newnumber;
        if(difficultyinput == 'e')
        {
            
            number_of_questions = easy_number_of_questions;
        }
        else if(difficultyinput == 'm')
        {
           number_of_questions = medium_number_of_questions;
        }
        else
        {
          number_of_questions = hard_number_of_questions;
        }
        
        do
        {
            newnumber = ((int)doublerandom)% number_of_questions;
             
             if(checkQuestionArray(newnumber,difficultyinput)==true)
             {
                 
                 number_of_times++;
                 
                 doublerandom = Math.random()*100;
             }
             else // not yet asked
             {
                
                 updateQuestionArray(newnumber,difficultyinput);
                 
                 return retrieveQuestion(newnumber,difficultyinput);
                          
                 
             }
        }
        while(number_of_times < 3);
        
        newnumber = checkIfAllQuestionsAsked(difficultyinput);
        updateQuestionArray(newnumber,difficultyinput);
       
        return retrieveQuestion(newnumber,difficultyinput);
       
     }
    
    
    /* takes in a question and returns answers that can be removed for 50:50 in char array
     */
    public char[] get5050(Questions Question)
    {
        char answer = Question.getAnswer();
        char[] notanswers = new char[3];
        char[] randomselection = new char[2];
         double doublerandom = Math.random()*10;
        
        switch(answer)
        {
             case 'A': notanswers = new char[]{'B','C','D'}; break;
             case 'B': notanswers = new char[]{'A','C','D'}; break;
             case 'C': notanswers = new char[]{'A','B','D'}; break;
             case 'D': notanswers = new char[]{'A','B','C'}; break;
        }
      randomselection[0] = notanswers[((int)doublerandom)% notanswers.length];
      do
      {
          doublerandom = Math.random()*10;
          randomselection[1] = notanswers[((int)doublerandom)% notanswers.length];
      }while(randomselection[0] == randomselection[1]);
      
      
      return randomselection;
        
    }
    

    /* changes value for ask a friend timelimit and edits entry in version.dat
     */
    
    public boolean editAskAFriendTimeLimit(int newtimelimit)
    {
        ask_a_friend_timelimit = newtimelimit;
        String version="";
        try(BufferedReader Readfile = new BufferedReader(new FileReader("files/version.dat")))
        {
            
        
        
        version = Readfile.readLine();
        Readfile.close();
            try (PrintWriter Print_to_file = new PrintWriter(new FileWriter("files/version.dat"))) {
                Print_to_file.println(version);
                Print_to_file.println(ask_a_friend_timelimit);
            }
        return true;
        
        }
        catch(IOException ioe)
        {
            DisplayGUI ErrorGui = new DisplayGUI();
            if(version.length() == 0)
            {
                ErrorGui.errorMessage("File version.dat could not be read");
            }
            else
            {
                 ErrorGui.errorMessage("File version.dat could not be written to");
            }
           
            return false;
            
        }
    }
    
    //returns ask a friend time limit value
    public int getAskAFriendTimeLimit()
    {
        return ask_a_friend_timelimit;
    }
    
   
    
    
}
