/*
 * Questions.java
 *
 * Created on 30 August 2007, 15:01
 *
 * // creates instance of each question for game Who Wants To
 */
package whowantsto;
/**
 *
 * @author Rodney
 */
public class Questions_1 
{
    private String question; // string of the question
    private char answer; // char of the answer a b c d answers
    private String a_answer; // possible answer a
    private String b_answer;	// possible answer b
    private String c_answer;// possible answer c
    private String d_answer;	// possible answer d
    
    /** Creates a new instance of Questions */
    public Questions_1() 
    {}
    
    //Construtor, takes in array of strings and assigns values to question instance
    public Questions_1(String[] newquestioninstance)
    {
        question = newquestioninstance[0];
        a_answer = newquestioninstance[1];
        b_answer = newquestioninstance[2];
        c_answer = newquestioninstance[3];
        d_answer = newquestioninstance[4];
        answer =  newquestioninstance[5].charAt(0);
    }
    // constructor that allows input of strings for each variable
    public Questions_1(String new_question, String new_a_answer,String new_b_answer, String new_c_answer, String new_d_answer, String new_answer)
    {
        question = new_question;
        a_answer = new_a_answer;
        b_answer = new_b_answer;
        c_answer = new_c_answer;
        d_answer = new_d_answer;
        answer =  new_answer.charAt(0);
    }
    
    //returns the question
    public String getQuestion()
    {
        return question;
    }
    
    //returns answer a
    public String getAAnswer()
    {
        return a_answer;
    }
    
    //returns answer b
    public String getBAnswer()
    {
        return b_answer;
    }
    
    //returns answer c
    public String getCAnswer()
    {
        return c_answer;
    }
    
    //returns answer d
    public String getDAnswer()
    {
        return d_answer;
    }
    
    //returns answer
    public char getAnswer()
    {
        return answer;
    }
    
    //takes in the possible answer as a character depending on selection and checks against the answer, returns true if it is the answer
    public boolean isAnswer(char possibleanswer)
    {
        if( possibleanswer == answer)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    
    
    
    
}
