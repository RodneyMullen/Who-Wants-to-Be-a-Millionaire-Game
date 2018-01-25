/*
 * DisplayGUI.java
 *
 * Created on 06 September 2007, 17:45
 *
 * used as a tool for the game WHoWantsTO. It displays leaderboards, help screen, audience display, 
  *ask a friend time limit change, 
 */

package whowantsto;
import java.util.*;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

/**
 *
 * @author Rodney
 */
public class DisplayGUI extends JPanel implements ActionListener,ItemListener
{
    private JFrame DGFrame; // maine frame
    private JPanel Main_Panel;
    private Object[] smallbuttons_array;
    private JButton ExitLeaderBoard_Button, Small5050_button, SmallAskAFriend_button, SmallAskTheAudience_button, PlayerNameEntryContinue_button,EnterAudienceNumber_button,EditAskAFriendTimeLimit_button,MultiNameEntryContinue_button,AskTheAudienceContinue_button,CountDown_button,AskTheAudienceFinish_button;
    private ImageIcon FiftyFiftySmallNormalimage, FiftyFiftySmallDisabledimage, AskAFriendSmallNormalimage, AskAFriendSmallDisabledimage, AskTheAudienceSmallNormalimage, AskTheAudienceSmallDisabledimage;
    private JTextPane NameEntry,AudienceNameEntry,EditAskAFriendTimeLimitEntry,Countdown;
   
    final private JOptionPane errorpane= new JOptionPane();// creates a dialog for an error message
    private JLayeredPane Clock_pane;
    private JTextField A_answer, B_answer, C_answer, D_answer;
    private JLabel Clockimage;
    private Player Player;
    private PlayerBoard Playerboard;
    private QuestionsBase Questionsbase;
    final private javax.swing.Timer Timer = new javax.swing.Timer(1000,this);
    private ArrayList<String> Multiplayernames; // used to take in array of multiplayer names
    private Object[] multiplayernames_text_array;
    private int counter, iteration;
    private double clock_next_image;
    final private String backgroundimage = "images/background1024x768.gif";
    final private String fiftyfiftysmallnormal = "images/small5050normal.gif";
    final private String fiftyfiftysmalldisabled = "images/small5050_xed.gif";
    final private String askafriendsmallnormal = "images/smallaskafriend_normal.gif";
    final private String askafriendsmalldisabled = "images/smallaskafriend_xed.gif";
    final private String asktheaudiencesmallnormal = "images/smallasktheaudience_normal.gif";
    final private String asktheaudiencesmalldisabled = "images/smallasktheaudience_xed.gif";
    final private String scoreiconlocation = "images/scoreicon.gif";
    final private String leaderboardicon = "images/scoreboardicon.gif";
    final private String graphbackdropicon = "images/Graphbackdrop3.gif";
    final private String graphcovericon = "images/graphcover2.gif";
    final private String Clockicons = "images/Clock/Clock";  // parital string of the clock images
     public WhoWantsTo WhoWantsTo;  // class instance
     private boolean is_end_of_multiplayer_game = false; // used to show main leader board at end of multiplayer game
     
     
    
    
    /** Creates a new instance of DisplayGUI */
    public DisplayGUI() {}
    
    // initialises and starts GUI
    public void startGUI()
    {
         javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{ 
                        @Override
			public void run() 
			{ 
				createAndShowGUI(); 
			} 
		}); 
    }
    
    public void setUpFrame(String framename)
    {
        DGFrame = new JFrame(framename);
        DGFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        DGFrame.setSize(new Dimension(1024, 768));
    }
    public void showFrame()
    {
                DGFrame.pack(); 
                DGFrame.setLocationRelativeTo(null);
                DGFrame.setVisible(true); 
    }
    
   //exits GUI 
    public void endGUI()
    {
        
            DGFrame.dispose();
        
    }
    
     private void setMainPanel()
    {
        // setting up main panel and background
        
        ImageIcon image = new ImageIcon(backgroundimage);
        
	JLabel background = new JLabel(image);
      
	background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        
            
        
	DGFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        
        
        
         
	Main_Panel = new JPanel();
        Main_Panel.setLayout(new BoxLayout(Main_Panel, BoxLayout.Y_AXIS));
	Main_Panel.setOpaque(false);
       	DGFrame.setContentPane(Main_Panel);
        
       
	
    }
    
    //takes in Playerboard and displays in window
    public void showLeaderBoard(PlayerBoard Playerboard)
    {
        
        startGUI();
        setUpFrame("Leader Board");
        setMainPanel();
        JPanel TopOfLeaderBoard = new JPanel();
        TopOfLeaderBoard.setOpaque(false);
        
        JLabel LeaderBoard_Label = new JLabel("Leader Board");
         LeaderBoard_Label.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 38));
         LeaderBoard_Label.setForeground(new Color(0xFFFFFF));
        LeaderBoard_Label.setOpaque(false);
        TopOfLeaderBoard.add(LeaderBoard_Label);
        Main_Panel.add(TopOfLeaderBoard);
        
        JLayeredPane LeaderBoard_Pane = new JLayeredPane();
        ImageIcon LeaderBoardimage = new ImageIcon(leaderboardicon);
        ImageIcon FiftyFiftySmallNormal_image = new ImageIcon(fiftyfiftysmallnormal);
        ImageIcon FiftyFiftySmallDisabled_image = new ImageIcon(fiftyfiftysmalldisabled);
        ImageIcon AskAFriendSmallNormal_image = new ImageIcon(askafriendsmallnormal);
        ImageIcon AskAFriendSmallDisabled_image = new ImageIcon(askafriendsmalldisabled);
        ImageIcon AskTheAudienceSmallNormal_image = new ImageIcon(asktheaudiencesmallnormal);
        ImageIcon AskTheAudienceSmallDisabled_image = new ImageIcon(asktheaudiencesmalldisabled);
        
        LeaderBoard_Pane.setPreferredSize(new Dimension(960, 550));
        
        ArrayList<Player> Playerboard_vector = Playerboard.returnPlayerBoard();
        int number_on_scoreboard;
        if(Playerboard_vector.isEmpty()==true)
        {
             number_on_scoreboard = 0;
        }
        else
        {
            number_on_scoreboard = Playerboard_vector.size();
        }
        
         if(number_on_scoreboard == 0)
          {
            JTextPane Nothinghere_textpane= new JTextPane();
            Nothinghere_textpane.setText( "                         No Entries currently on Score Board");
            Nothinghere_textpane.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
            Nothinghere_textpane.setForeground(new Color(0xFFFF94));
            Nothinghere_textpane.setEditable(false);
            Nothinghere_textpane.setOpaque(false);
            Nothinghere_textpane.setBounds(290, 60,320,40);
            LeaderBoard_Pane.add(Nothinghere_textpane,new Integer(1));
              
          }
         else
         {
            JLabel TempJLabel = new JLabel(LeaderBoardimage);
            TempJLabel.setBounds(0,0,960,47);
            LeaderBoard_Pane.add(TempJLabel,new Integer(0));
            JLabel HeadingsLabel = new JLabel();
            HeadingsLabel.setText("Player Name                            Lifelines       Score Reached     Final Score");
            HeadingsLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
            HeadingsLabel.setForeground(new Color(0xFFFF94));
            HeadingsLabel.setBounds(290,0,960,47);
            LeaderBoard_Pane.add(HeadingsLabel,new Integer(1));
            JTextPane Playername_textpane;
            JLabel TempFiftyFiftyLabel = new JLabel();
            JLabel TempAskAFriendLabel= new JLabel();
            JLabel TempAskTheAudienceLabel= new JLabel();
            JLabel ScoreReachedLabel;
            JLabel FinalScoreLabel;
            int ycoordinate;
             for(int i=0; i<number_on_scoreboard; i++)
             {
                    Player thisPlayer = (Player) Playerboard_vector.get(i);
                    ycoordinate = (i*60) + 60;  
                    TempJLabel = new JLabel(LeaderBoardimage);
                    TempJLabel.setBounds(0, ycoordinate,960,47);
                    LeaderBoard_Pane.add(TempJLabel,new Integer(0));
                    ycoordinate = ycoordinate+10;
                    Playername_textpane = new JTextPane();
                    Playername_textpane.setText( i+1+"  "+thisPlayer.getPlayerName());
                    Playername_textpane.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
                    Playername_textpane.setForeground(new Color(0xFFFFFF));
                    Playername_textpane.setEditable(false);
                    Playername_textpane.setOpaque(false);
                    Playername_textpane.setBounds(290, ycoordinate,320,40);
                    LeaderBoard_Pane.add(Playername_textpane,new Integer(1));
                    boolean[] lifelines = thisPlayer.getPlayerLifelines();
                    if(lifelines[0] == true)
                    {
                        TempFiftyFiftyLabel = new JLabel(FiftyFiftySmallDisabled_image);
                    }
                    else if(lifelines[0] == false)
                    {
                        TempFiftyFiftyLabel = new JLabel(FiftyFiftySmallNormal_image);
                    }
                    TempFiftyFiftyLabel.setBounds(430, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempFiftyFiftyLabel,new Integer(1));
            
                    if(lifelines[1] == true)
                    {
                        TempAskAFriendLabel = new JLabel(AskAFriendSmallDisabled_image);
                    }
                    else if(lifelines[1] == false)
                    {
                        TempAskAFriendLabel = new JLabel(AskAFriendSmallNormal_image);
                    }
                    TempAskAFriendLabel.setBounds(465, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempAskAFriendLabel,new Integer(1));
            
                    if(lifelines[2] == true)
                    {
                        TempAskTheAudienceLabel = new JLabel(AskTheAudienceSmallDisabled_image);
                    }
                    else if(lifelines[2] == false)
                    {
                        TempAskTheAudienceLabel = new JLabel(AskTheAudienceSmallNormal_image);
                    }
            
                    TempAskTheAudienceLabel.setBounds(500, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempAskTheAudienceLabel,new Integer(1));
                    ScoreBoard Scoreboard = new ScoreBoard();
                    ScoreReachedLabel = new JLabel(Scoreboard.intToString(thisPlayer.getPlayerScoreReached()));
                    ScoreReachedLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
                    ScoreReachedLabel.setForeground(new Color(0x52416B));
                    ScoreReachedLabel.setBounds(555, ycoordinate-10,100,40);
                    LeaderBoard_Pane.add(ScoreReachedLabel,new Integer(1));
             
                    FinalScoreLabel = new JLabel(Scoreboard.intToString(thisPlayer.getPlayerScore()));
                    FinalScoreLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
                    FinalScoreLabel.setForeground(new Color(0x42C36B));
                    FinalScoreLabel.setBounds(617, ycoordinate-10,100,40);
                    LeaderBoard_Pane.add(FinalScoreLabel,new Integer(1));
            
                }
          }
          Main_Panel.add(LeaderBoard_Pane);
          JPanel  BottomScoreBoard_Panel = new JPanel();  
        BottomScoreBoard_Panel.setOpaque(false);
        ExitLeaderBoard_Button = new JButton("Exit");
        ExitLeaderBoard_Button.setFont(new Font("Arial", Font.BOLD, 12));
        ExitLeaderBoard_Button.setForeground(new Color(0x42C36B));
        ExitLeaderBoard_Button.setBackground(new Color(0x52416B));
        ExitLeaderBoard_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        ExitLeaderBoard_Button.setBorderPainted(true);
        ExitLeaderBoard_Button.setContentAreaFilled(false);
        ExitLeaderBoard_Button.setMargin(new Insets(10,2,2,2));
        ExitLeaderBoard_Button.addActionListener(this);
        ExitLeaderBoard_Button.setBounds(320,50,50,30);
        ExitLeaderBoard_Button.setOpaque(true);
         BottomScoreBoard_Panel.add(ExitLeaderBoard_Button);
         Main_Panel.add(BottomScoreBoard_Panel);
          showFrame();
        
    
    }
    
    // used to tell the class that leader board from WhoWantsTo must be shown with ext 
    // button is pressed while showing player board
    public void setIsEndOfMultiPlayerGame()
    {
        is_end_of_multiplayer_game = true;
    }
    
    /* takes in a Player gets name and places in an editable box. 
     * changes Playername if need be
     */
    public void editPlayerOne(Player oldPlayer, WhoWantsTo currentWhoWantsTo)
    {
        Player = oldPlayer;
        WhoWantsTo = currentWhoWantsTo;
        startGUI();
        setUpFrame("Edit Player");
        setMainPanel();
        JPanel Blank_Panel1 = new JPanel();
        Blank_Panel1.setPreferredSize(new Dimension(10, 47));
        Blank_Panel1.setOpaque(false);
        Main_Panel.add(Blank_Panel1);
        
        JPanel EnterName_Panel = new JPanel();
        EnterName_Panel.setOpaque(false);
        JTextPane EnterNameText = new JTextPane();
        EnterNameText.setText("Edit your name:");
        EnterNameText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        EnterNameText.setForeground(new Color(0xFFFFFF));
        EnterNameText.setEditable(false);
        EnterNameText.setOpaque(false);
        EnterName_Panel.add(EnterNameText);
         Main_Panel.add(EnterName_Panel);
       
        
        ImageIcon nameimage = new ImageIcon("images/nameicon.gif");
        JLayeredPane EnterName_Pane = new JLayeredPane();
        EnterName_Pane.setPreferredSize(new Dimension(871, 247));
        JLabel EntryLabel = new JLabel(nameimage);
        EntryLabel.setBounds(0,0,871,47);
        EnterName_Pane.add( EntryLabel,new Integer(0));
        NameEntry =  new JTextPane();
        NameEntry.setText(Player.getPlayerName());
        NameEntry.setFocusable(true);
        NameEntry.setEditable(true);
        NameEntry.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        NameEntry.setForeground(new Color(0xFFFF94));
        NameEntry.setBackground(new Color(0x000000));
        NameEntry.setMargin(new Insets(0,0,0,0));
        NameEntry.setCaretColor(new Color(0xFFFFFF));
        NameEntry.setBounds(360,4,260,40);
        EnterName_Pane.add(NameEntry,new Integer(1));
       
        
         JPanel Blank_Panel2 = new JPanel();
         Blank_Panel2.setPreferredSize(new Dimension(10, 87));
        Blank_Panel2.setOpaque(false);
         Main_Panel.add(Blank_Panel2);
         
        PlayerNameEntryContinue_button = new JButton("Continue");
        PlayerNameEntryContinue_button.setFont(new Font("Arial", Font.BOLD, 18));
        PlayerNameEntryContinue_button.setForeground(new Color(0x42C36B));
        PlayerNameEntryContinue_button.setBackground(new Color(0x52416B));
        PlayerNameEntryContinue_button.setBorder(BorderFactory.createRaisedBevelBorder());
        PlayerNameEntryContinue_button.setBorderPainted(true);
        PlayerNameEntryContinue_button.setContentAreaFilled(false);
        PlayerNameEntryContinue_button.setMargin(new Insets(2,2,2,2));
        PlayerNameEntryContinue_button.addActionListener(this);
        PlayerNameEntryContinue_button.setBounds(370,50,100,30);
        PlayerNameEntryContinue_button.setOpaque(true);
        EnterName_Pane.add(PlayerNameEntryContinue_button, new Integer(1));
        Main_Panel.add(EnterName_Pane);
        
         
         showFrame();
         
        
   
    }
    
    //returns Player instance
    public Player getPlayer()
    {
        return this.Player;
    }
    
    // takes in an array of strings and allows user to edit them
    public void editMultiPlayerNames(ArrayList<String> names, WhoWantsTo WWT)
    {
        Multiplayernames = names;
        WhoWantsTo = WWT;
        startGUI();
        setUpFrame("Edit Multi-Player Names");
        setMainPanel();
        JPanel Blank_Panel1 = new JPanel();
        Blank_Panel1.setPreferredSize(new Dimension(10, 47));
        Blank_Panel1.setOpaque(false);
        Main_Panel.add(Blank_Panel1);
        JPanel EnterName_Panel = new JPanel();
        EnterName_Panel.setOpaque(false);
        JTextPane EnterNameText = new JTextPane();
        EnterNameText.setText("Edit your names:");
        EnterNameText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        EnterNameText.setForeground(new Color(0xFFFFFF));
        EnterNameText.setEditable(false);
        EnterNameText.setOpaque(false);
        EnterName_Panel.add(EnterNameText);
        Main_Panel.add(EnterName_Panel);
        multiplayernames_text_array = new Object[Multiplayernames.size()];
        for(int i =0; i<Multiplayernames.size(); i++)
        {
            
       
        
            ImageIcon nameimage = new ImageIcon("images/nameicon.gif");
            JLayeredPane EnterName_Pane = new JLayeredPane();
            EnterName_Pane.setPreferredSize(new Dimension(871, 55));
            JLabel EntryLabel = new JLabel(nameimage);
            EntryLabel.setBounds(0,0,871,47);
            EnterName_Pane.add( EntryLabel,new Integer(0));
            
            JTextPane MultiPlayerNameEntry =  new JTextPane();
            MultiPlayerNameEntry.setText(Multiplayernames.get(i));
            MultiPlayerNameEntry.setFocusable(true);
            MultiPlayerNameEntry.setEditable(true);
            MultiPlayerNameEntry.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
            MultiPlayerNameEntry.setForeground(new Color(0xFFFF94));
            MultiPlayerNameEntry.setBackground(new Color(0x000000));
            MultiPlayerNameEntry.setCaretColor(new Color(0xFFFFFF));
            MultiPlayerNameEntry.setMargin(new Insets(0,0,0,0));
            MultiPlayerNameEntry.setBounds(360,4,260,40);
            EnterName_Pane.add(MultiPlayerNameEntry,new Integer(1));
            multiplayernames_text_array[i] = MultiPlayerNameEntry;
            Main_Panel.add( EnterName_Pane);
        }
        
         JPanel Blank_Panel2 = new JPanel();
         Blank_Panel2.setPreferredSize(new Dimension(10, 87));
         Blank_Panel2.setOpaque(false);
         Main_Panel.add(Blank_Panel2);
         
         JPanel Continue_panel = new JPanel();
         Continue_panel.setOpaque(false);
        MultiNameEntryContinue_button = new JButton("Continue");
        MultiNameEntryContinue_button.setFont(new Font("Arial", Font.BOLD, 18));
        MultiNameEntryContinue_button.setForeground(new Color(0x42C36B));
        MultiNameEntryContinue_button.setBackground(new Color(0x52416B));
        MultiNameEntryContinue_button.setBorder(BorderFactory.createRaisedBevelBorder());
        MultiNameEntryContinue_button.setBorderPainted(true);
        MultiNameEntryContinue_button.setContentAreaFilled(false);
        MultiNameEntryContinue_button.setMargin(new Insets(2,2,2,2));
        MultiNameEntryContinue_button.addActionListener(this);
        MultiNameEntryContinue_button.setBounds(370,50,100,30);
        MultiNameEntryContinue_button.setOpaque(true);
        Continue_panel.add(MultiNameEntryContinue_button);
        Main_Panel.add(Continue_panel);
        
         
         showFrame();
        
    }
    
    // returns vector of multi playernames
    public ArrayList<String> getMultiPlayerNames()
    {
        return Multiplayernames;
    }
    
    
    
    // takes in a string error and displays it in a new window
    public void errorMessage(String message)
    {
        JOptionPane.showMessageDialog(DGFrame, message,"Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    public void editAskAFriendTimeLimit(QuestionsBase Questionsbase_editable)
    {
        setUpFrame("Edit Ask a Friend Time Limit");
        Questionsbase=Questionsbase_editable;
        startGUI();
        setMainPanel();
        JPanel Blank_Panel1 = new JPanel();
        Blank_Panel1.setPreferredSize(new Dimension(10, 47));
        Blank_Panel1.setOpaque(false);
        Main_Panel.add(Blank_Panel1);
        
        JPanel EditAskAFriendTimeLimit_Panel = new JPanel();
        EditAskAFriendTimeLimit_Panel.setOpaque(false);
        JTextPane EditAskAFriendTimeLimitText = new JTextPane();
        EditAskAFriendTimeLimitText.setText("Edit ask a friend time limit:");
        EditAskAFriendTimeLimitText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 26));
        EditAskAFriendTimeLimitText.setForeground(new Color(0xFFFFFF));
        EditAskAFriendTimeLimitText.setEditable(false);
        EditAskAFriendTimeLimitText.setOpaque(false);
        EditAskAFriendTimeLimit_Panel.add(EditAskAFriendTimeLimitText);
         Main_Panel.add(EditAskAFriendTimeLimit_Panel);
         
        ImageIcon enteraudienceimage = new ImageIcon("images/nameicon.gif");
         JLayeredPane EditAskAFriendTimeLimit_Pane = new JLayeredPane();
        EditAskAFriendTimeLimit_Pane.setPreferredSize(new Dimension(871, 247));
        JLabel EditAskAFriendTimeLimitLabel = new JLabel(enteraudienceimage);
        EditAskAFriendTimeLimitLabel.setBounds(0,0,871,47);
        EditAskAFriendTimeLimit_Pane.add( EditAskAFriendTimeLimitLabel,new Integer(0));
        EditAskAFriendTimeLimitEntry =  new JTextPane();
        EditAskAFriendTimeLimitEntry.setEditable(true);
        EditAskAFriendTimeLimitEntry.setText(String.valueOf(Questionsbase.getAskAFriendTimeLimit()));
        
        EditAskAFriendTimeLimitEntry.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        EditAskAFriendTimeLimitEntry.setForeground(new Color(0xFFFF94));
        EditAskAFriendTimeLimitEntry.setBackground(new Color(0x000000));
        EditAskAFriendTimeLimitEntry.setCaretColor(new Color(0xFFFFFF));
        EditAskAFriendTimeLimitEntry.setMargin(new Insets(0,0,0,0));
        EditAskAFriendTimeLimitEntry.setBounds(400,4,100,40);
        EditAskAFriendTimeLimit_Pane.add(EditAskAFriendTimeLimitEntry,new Integer(1));
        
         EditAskAFriendTimeLimit_button = new JButton("Continue");
         EditAskAFriendTimeLimit_button.setFont(new Font("Arial", Font.BOLD, 18));
         EditAskAFriendTimeLimit_button.setForeground(new Color(0x42C36B));
         EditAskAFriendTimeLimit_button.setBackground(new Color(0x52416B));
         EditAskAFriendTimeLimit_button.setBorder(BorderFactory.createRaisedBevelBorder());
         EditAskAFriendTimeLimit_button.setBorderPainted(true);
         EditAskAFriendTimeLimit_button.setContentAreaFilled(false);
         EditAskAFriendTimeLimit_button.setMargin(new Insets(10,2,2,2));
         EditAskAFriendTimeLimit_button.addActionListener(this);
         EditAskAFriendTimeLimit_button.setBounds(370,50,100,30);
        EditAskAFriendTimeLimit_button.setOpaque(true);
        EditAskAFriendTimeLimit_Pane.add( EditAskAFriendTimeLimit_button, new Integer(1));
        Main_Panel.add(EditAskAFriendTimeLimit_Pane);
        
         
         showFrame();
    }
    
    // returns QuestionsBase instance
    public QuestionsBase getQuestionBase()
    {
        return Questionsbase;
    }
  
    //Takes in an array of items and displays graph in the display GUI
    private void askTheAudienceGraph(int[] answers)
    {
       
        double tally;
        // incase audience number does not tally
        tally = answers[0] + answers[1] + answers[2] + answers[3];
        // works out percentage as a double
        double a = (answers[0]/tally)*100;
        double b = (answers[1]/tally)*100;
        double c = (answers[2]/tally)*100;
        double d = (answers[3]/tally)*100;
        
        // rounds double to nearest integer for top of graph
        int anumber =  (int) Math.round(a);
        int bnumber =  (int) Math.round(b);
        int cnumber =  (int) Math.round(c);
        int dnumber =  (int) Math.round(d);
        
       // works out the percentage of the graph size to display
        int a_pane_percent = (int) Math.round((a/100)*354);
        int b_pane_percent = (int) Math.round((b/100)*354);
        int c_pane_percent = (int) Math.round((c/100)*354);
        int d_pane_percent = (int) Math.round((d/100)*354);
       
        
       
        startGUI();
        
        setUpFrame("Ask The Audience Graph");
        ImageIcon image = new ImageIcon(graphbackdropicon);
        JLabel background = new JLabel(image);
        background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        DGFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        Main_Panel = new JPanel();
        Main_Panel.setLayout(new BoxLayout(Main_Panel, BoxLayout.Y_AXIS));
	Main_Panel.setOpaque(false);
       	DGFrame.setContentPane(Main_Panel);
        
        // top panel which shows the percentage values
        JPanel PercentageGraph_panel = new JPanel();
        PercentageGraph_panel.setPreferredSize(new Dimension(300,52));
        PercentageGraph_panel.setOpaque(false);
        
        JTextPane Percentages_text = new JTextPane();
       
        // add a space for just 0% which takes up less room and part of the frame does not appear
       
        String anumberstring = (String.valueOf(anumber))+"%";
        String bnumberstring = (String.valueOf(bnumber))+"%";
        String cnumberstring = (String.valueOf(cnumber))+"%";
        String dnumberstring = (String.valueOf(dnumber))+"%";
        
        int spaces = anumberstring.length();
        
        int extraspace = 3-spaces;
        for(int an=0; an<extraspace; an++)
        {
           
            anumberstring = anumberstring+" ";
        }
        spaces = bnumberstring.length();
        extraspace = 3-spaces;
        for(int bn=0; bn<extraspace; bn++)
        {
            bnumberstring = bnumberstring+" ";
        }
        spaces = cnumberstring.length();
        extraspace = 3-spaces;
        for(int cn=0; cn<extraspace; cn++)
        {
            cnumberstring = cnumberstring+" ";
        }
        spaces = dnumberstring.length();
        extraspace = 3-spaces;
        for(int dn=0; dn<extraspace; dn++)
        {
            dnumberstring = dnumberstring+" ";
        }
        
        
            Percentages_text.setText(" "+anumberstring+" "+bnumberstring+" "+cnumberstring+" "+dnumberstring);
        
        
        Percentages_text.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 32));
        Percentages_text.setForeground(new Color(0xFFFFFF));
        Percentages_text.setMargin(new Insets(0,0,0,0));
        Percentages_text.setEditable(false);
        Percentages_text.setOpaque(false);
        PercentageGraph_panel.add(Percentages_text);
        Main_Panel.add(PercentageGraph_panel);
        
        //Panel which holds 4 panes which hold all the bars, 
        //Pane only shows what is needed
        JLayeredPane GraphAnswers_pane = new JLayeredPane();
       GraphAnswers_pane.setPreferredSize(new Dimension(54, 420));
    
       
        GraphAnswers_pane.setOpaque(false);
        
        // answers bar A
       
        ImageIcon graphcoverimage = new ImageIcon(graphcovericon);
        JLayeredPane AnswerA_pane = new JLayeredPane();
        AnswerA_pane.setOpaque(false);
        AnswerA_pane.setPreferredSize(new Dimension(54,  354));
        
        JLabel CoverImageA_label = new JLabel(graphcoverimage);
         CoverImageA_label.setBounds(0,0, 54,  354);
      
        AnswerA_pane.add( CoverImageA_label,new Integer(1));
        AnswerA_pane.setBounds(26,15,54,420-(a_pane_percent+66));
        GraphAnswers_pane.add(AnswerA_pane,new Integer(1));
        
         // answers bar B
        JLayeredPane AnswerB_pane = new JLayeredPane();
        AnswerB_pane.setOpaque(false);
        AnswerB_pane.setPreferredSize(new Dimension(54,  354));
        JLabel CoverImageB_label = new JLabel(graphcoverimage);
        CoverImageB_label.setBounds(0,0, 54,  354);
        AnswerB_pane.add( CoverImageB_label,new Integer(1));
        AnswerB_pane.setBounds(90,15,54,420-(b_pane_percent+66));
        GraphAnswers_pane.add(AnswerB_pane,new Integer(1));
        
        // answers bar C
        JLayeredPane AnswerC_pane = new JLayeredPane();
        AnswerC_pane.setOpaque(false);
        AnswerC_pane.setPreferredSize(new Dimension(54,  354));
        JLabel CoverImageC_label = new JLabel(graphcoverimage);
        CoverImageC_label.setBounds(0,0,54,  354);
        AnswerC_pane.add(CoverImageC_label,new Integer(1));
        AnswerC_pane.setBounds(154,15,54,420-(c_pane_percent+66));
        GraphAnswers_pane.add(AnswerC_pane,new Integer(1));
        
        // answers bar D
        JLayeredPane AnswerD_pane = new JLayeredPane();
        AnswerD_pane.setOpaque(false);
        AnswerD_pane.setPreferredSize(new Dimension(54,  354));
        JLabel CoverImageD_label = new JLabel(graphcoverimage);
        CoverImageD_label.setBounds(0,0,54,  354);
        AnswerD_pane.add(CoverImageD_label,new Integer(1));
        AnswerD_pane.setBounds(218,15,54,420-(d_pane_percent+66));
        GraphAnswers_pane.add(AnswerD_pane,new Integer(1));
        
        
        
        
        
        
        Main_Panel.add(GraphAnswers_pane);
        JPanel AskTheAudienceLowerGraph_panel = new JPanel();
        AskTheAudienceLowerGraph_panel.setBackground(new Color(0x62345B));
        AskTheAudienceLowerGraph_panel.setOpaque(true);
        AskTheAudienceFinish_button = new JButton("Finish");
        AskTheAudienceFinish_button.setFont(new Font("Arial", Font.BOLD, 18));
        AskTheAudienceFinish_button.setForeground(new Color(0x42C36B));
        AskTheAudienceFinish_button.setBackground(new Color(0x52416B));
        AskTheAudienceFinish_button.setBorder(BorderFactory.createRaisedBevelBorder());
        AskTheAudienceFinish_button.setBorderPainted(true);
        AskTheAudienceFinish_button.setContentAreaFilled(false);
        AskTheAudienceFinish_button.setMargin(new Insets(10,2,2,2));
        AskTheAudienceFinish_button.addActionListener(this);
        AskTheAudienceFinish_button.setBounds(370,50,100,30);
        AskTheAudienceFinish_button.setOpaque(true);
        AskTheAudienceLowerGraph_panel.add(AskTheAudienceFinish_button);
        Main_Panel.add(AskTheAudienceLowerGraph_panel);
        showFrame();
        if(WhoWantsTo.music == true)
        {
            WhoWantsTo.Background_music.close();
            WhoWantsTo.Background_music.playAskTheAudience(false);
            
            WhoWantsTo.Background_music.playSound(WhoWantsTo.Scoreboard.getDifficulty(),1);
        }
    }
    
    /* Shows a gui to select a b c or d with access to ask the audience, 
     *then sends to another method for graph.
     */
    public void askTheAudienceGUI(WhoWantsTo CurrentWhoWantsTo)
    {   
        WhoWantsTo = CurrentWhoWantsTo;
        startGUI();
        setUpFrame("Ask the Audience");
        setMainPanel();
        //JPanel AskTheAudience_panel = new JPanel(new BoxLayout(Main_Panel, BoxLayout.Y_AXIS));
        
        JTextPane AskTheAudience_text = new JTextPane();
        AskTheAudience_text.setText("Enter Audience Selection Below");
        AskTheAudience_text.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
        AskTheAudience_text.setForeground(new Color(0xFFFFFF));
        AskTheAudience_text.setMargin(new Insets(0,0,0,0));
        AskTheAudience_text.setEditable(false);
       AskTheAudience_text.setOpaque(false);
       Main_Panel.add(AskTheAudience_text);
        
        JPanel A_Panel = new JPanel();
        A_Panel.setOpaque(false);
        ImageIcon a_answericon = new ImageIcon("images/a_answericon.gif");
        JLabel alabel = new JLabel(a_answericon,JLabel.TRAILING);
        A_answer = new JTextField(5);
        
        A_answer.setEditable(true);
        A_Panel.add(alabel);
        A_Panel.add(A_answer);
        Main_Panel.add(A_Panel);
        
        JPanel B_Panel = new JPanel();
        B_Panel.setOpaque(false);
        ImageIcon b_answericon = new ImageIcon("images/b_answericon.gif");
        JLabel blabel = new JLabel(b_answericon);
        B_answer = new JTextField(5);
      
        B_answer.setEditable(true);
        B_Panel.add(blabel);
        B_Panel.add(B_answer);
        Main_Panel.add(B_Panel);
        
        JPanel C_Panel = new JPanel();
        C_Panel.setOpaque(false);
        ImageIcon c_answericon = new ImageIcon("images/c_answericon.gif");
        JLabel clabel = new JLabel(c_answericon);
        C_answer = new JTextField(5);
        C_answer.setEditable(true);
        C_Panel.add(clabel);
        C_Panel.add(C_answer);
        Main_Panel.add(C_Panel);
        
        JPanel D_Panel = new JPanel();
        D_Panel.setOpaque(false);
        ImageIcon d_answericon = new ImageIcon("images/d_answericon.gif");
        JLabel dlabel = new JLabel(d_answericon);
        D_answer = new JTextField(5);
        D_answer.setEditable(true);
        D_Panel.add(dlabel);
        D_Panel.add(D_answer);
        Main_Panel.add(D_Panel);
        
        JPanel AskTheAudienceContinue_panel = new JPanel();
        AskTheAudienceContinue_panel.setOpaque(false);
        AskTheAudienceContinue_button = new JButton("Continue");
        AskTheAudienceContinue_button.setFont(new Font("Arial", Font.BOLD, 18));
        AskTheAudienceContinue_button.setForeground(new Color(0x42C36B));
        AskTheAudienceContinue_button.setBackground(new Color(0x52416B));
        AskTheAudienceContinue_button.setBorder(BorderFactory.createRaisedBevelBorder());
        AskTheAudienceContinue_button.setBorderPainted(true);
        AskTheAudienceContinue_button.setContentAreaFilled(false);
        AskTheAudienceContinue_button.setMargin(new Insets(10,2,2,2));
        AskTheAudienceContinue_button.addActionListener(this);
        AskTheAudienceContinue_button.setBounds(370,50,100,30);
        AskTheAudienceContinue_button.setOpaque(true);
        AskTheAudienceContinue_panel.add(AskTheAudienceContinue_button);
        Main_Panel.add(AskTheAudienceContinue_panel);
         //Main_Panel.add(AskTheAudience_panel);
         showFrame();
         if(WhoWantsTo.music == true)
         {
            WhoWantsTo.Background_music.close();
            WhoWantsTo.Background_music.playAskTheAudience(true);
         }
         
       
    }
    
    // takes in an int and counts down a timer from that int
    public void showTimer(int count,WhoWantsTo CurrentWhoWantsTo)
    {
        WhoWantsTo = CurrentWhoWantsTo;
         startGUI();
        setUpFrame("Ask A Friend");
        setMainPanel();
        counter = count;
        Clock_pane = new JLayeredPane();
        Clock_pane.setOpaque(false);
        Clock_pane.setPreferredSize(new Dimension(172, 172));
        ImageIcon Clock_0 =  new ImageIcon(Clockicons+"0.gif");
        Clockimage = new JLabel(Clock_0);
        Clockimage.setBounds(0,0,172,172);
        Clock_pane.add(Clockimage, new Integer(0));
        Countdown = new JTextPane();
        Countdown.setText(String.valueOf(counter));
        Countdown.setEditable(false);
        Countdown.setForeground(new Color(0xFFB631));
        Countdown.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 56));
        Countdown.setOpaque(false);
        Countdown.setBounds(51,41,150,150);
        Clock_pane.add(Countdown, new Integer(1));
        Main_Panel.add(Clock_pane);
        JPanel Countdown_panel = new JPanel();
        Countdown_panel.setOpaque(false);
        CountDown_button = new JButton("Finish");
        CountDown_button.setFont(new Font("Arial", Font.BOLD, 18));
        CountDown_button.setForeground(new Color(0x42C36B));
        CountDown_button.setBackground(new Color(0x52416B));
        CountDown_button.setBorder(BorderFactory.createRaisedBevelBorder());
        CountDown_button.setBorderPainted(true);
        CountDown_button.setContentAreaFilled(false);
        CountDown_button.setMargin(new Insets(10,2,2,2));
        CountDown_button.addActionListener(this);
        CountDown_button.setBounds(370,50,100,30);
        CountDown_button.setOpaque(true);
        Countdown_panel.add(CountDown_button);
        Main_Panel.add(Countdown_panel);
        double images = 15;
        iteration = 0;
        clock_next_image = images/counter;
         showFrame();
        
        if(WhoWantsTo.music == true)
         {
            WhoWantsTo.Background_music.close();
            WhoWantsTo.Background_music.playAskAFriend();
        }
        Timer.start(); 
       
    }
    
    @Override 
    public void itemStateChanged(ItemEvent e) 
	{}
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
             if(e.getSource() ==  PlayerNameEntryContinue_button)
             {
                 String buttontext = NameEntry.getText().trim();
                 if(buttontext.compareTo(Player.getPlayerName())!=0)
                 {
                     this.Player.changePlayerName(buttontext);
                     WhoWantsTo.changePlayerNamePanel();
                     
                      
                     endGUI();
                 }
                 else
                 {
                     endGUI();
                 }
                         
             }
            
             else if(e.getSource() ==  EditAskAFriendTimeLimit_button)
             {
                 int ask_a_friend_time_limit = Integer.parseInt(EditAskAFriendTimeLimitEntry.getText().trim());
                 if(ask_a_friend_time_limit != Questionsbase.getAskAFriendTimeLimit())
                 {
                     Questionsbase.editAskAFriendTimeLimit(ask_a_friend_time_limit);
                    
                                       
                     endGUI();
                 }
                 else
                 {
                     endGUI();
                 }
                         
             }
             
             else if(e.getSource() ==  MultiNameEntryContinue_button)
             {
                     
                     
                     
                     for(int i=0; i< multiplayernames_text_array.length; i++)
                     {
                         JTextPane value = (JTextPane) multiplayernames_text_array[i];
                         Multiplayernames.set(i,value.getText().trim());
                        
                     }
                     
                     
                     endGUI();
                                        
             }
             
             else if(e.getSource() ==   AskTheAudienceContinue_button)
             {
                     int[] audience_responce = new int[] {0,0,0,0};
                     endGUI();
                     if(A_answer.getText().compareTo("") != 0)
                     {
                         audience_responce[0] =Integer.parseInt(A_answer.getText().trim());
                     }
                     
                     if(B_answer.getText().compareTo("") != 0)
                     {
                         audience_responce[1] = Integer.parseInt(B_answer.getText().trim());
                     }
                     
                     if(C_answer.getText().compareTo("") != 0)
                     {
                         audience_responce[2] = Integer.parseInt(C_answer.getText().trim());
                     }
                     
                     if(D_answer.getText().compareTo("") != 0)
                     {
                         audience_responce[3] = Integer.parseInt(D_answer.getText().trim());
                     }
                      
                     
                     askTheAudienceGraph(audience_responce);
              }
            
            else if(e.getSource() == CountDown_button)
            {
                 endGUI();
                 if(WhoWantsTo.music == true)
                 {
                     Timer.stop();
                    WhoWantsTo.Background_music.close();
                    WhoWantsTo.Background_music.playSound(WhoWantsTo.Scoreboard.getDifficulty(),1);
                 }
            }
              
             else if(e.getSource() == AskTheAudienceFinish_button)
            {
                 endGUI();
            }
             
            else if(e.getSource() == ExitLeaderBoard_Button)
            {
                 endGUI();
                 if(is_end_of_multiplayer_game == true)
                 {
                      WhoWantsTo.showLeaderBoard();
                 }
            }
             
             else if(e.getSource() == Timer)
             {
                 // this tells the GUI when to change to next image, there are only
                 // 15 pics so 20 seconds would change the pic every .75 iterations.
                 // counter is number of seconds on timer
                 // clock_next_image is each iteration
                int current_count;
               
                     current_count = (int) Math.round(iteration*clock_next_image);
                     
               
                
               
                iteration++;
                if(counter <10)
                {
                    Countdown.setText(" "+String.valueOf(counter));
                }
                else
                {
                    Countdown.setText(String.valueOf(counter));
                }
                 
                 ImageIcon Clock =  new ImageIcon(Clockicons+""+current_count+".gif");
                 
                 Clockimage.setIcon(Clock);
                 if(counter == 0)
                 {
                     Timer.stop();
                     // WhoWantsTo.Background_music.close();
                     WhoWantsTo.Background_music.playSound(WhoWantsTo.Scoreboard.getDifficulty(),1);
                     endGUI();
                 }
                 else
                 {
                     counter--;
                     Timer.restart();
                 }
               
             }
                 
    
    }
    
    
     public void createAndShowGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true); 

		try
		{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			System.out.println("Error in main GUI");
		}
		
		JComponent newContentPaneDisplayGUI = new DisplayGUI();
		newContentPaneDisplayGUI.setOpaque(true);
                
               
                
		
	}
     /*
     public static void main(String args[])
     {
         DisplayGUI newGui = new DisplayGUI();
         //PlayerBoard newPlayerboard = new PlayerBoard();
         //Player GamePlayer = new Player("Rodders");
         
        
         //QuestionsBase Questionsbase = new QuestionsBase();
         //newGui.editPlayerOne(GamePlayer);
         //newGui.editAskAFriendTimeLimit(Questionsbase);
         // newGui.startGUI();
         //int[] chararray = new int[] {20,22,40,7};
         //newGui.askTheAudienceGraph(chararray);
         //String names[] = new String[] {"Rod", "Claire", "Edelweis", "JKL","jkj","jKJ","JKJ", "jkjl", "jklj","jkljl"};
         //newGui.editMultiPlayerNames(names);
         newGui.askTheAudienceGUI();
         
        // newGui.showTimer(15);
         
     }*/
    
}
