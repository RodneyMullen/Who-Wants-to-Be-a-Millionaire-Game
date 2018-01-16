/*
 * WhoWantsTo.java
 *
 * Created on 07 August 2007, 15:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package whowantsto;
import java.io.*;
import java.util.*;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.color.*;
import javax.swing.AbstractButton.*;
import javax.swing.JCheckBox.*;


/**
 *
 * @author Rodney
 */
public class WhoWantsTo extends JPanel implements ActionListener
{
    private JFrame frame; // frame for main window
    private String game_name = "Who Wants to be An Altar Server";
    private JMenuBar Menubar; // menu tool bar
    private JMenu File_menu, Edit_menu, View_menu, Help_menu, MusicSetting; //Menus on toolbar; File, Edit, View, Help
    private JMenuItem OnePlayer, MultiPlayer, RestoreQuestions, EditMultiPlayers,EditPlayerName, MusicOn, MusicOff,Ask_A_Friend_Time_Limit, ViewPlayerBoard, HelpScreen, Version, SkipQuestion; // menu itmes for each menu
    private JPanel Main_Panel, MainMenuLogo_Panel, WWTBLogo_Panel, StartSingleGame_Panel, StartMultiGame_Panel, Quit_Panel, TopGame_Panel, PlayerName_Panel,LeftIcon_Panel, RightScore_Panel, ScoreOne_Panel, ScoreTwo_Panel, ScoreThree_Panel, ScoreFour_Panel, ScoreFive_Panel, ScoreSix_Panel, ScoreSeven_Panel, ScoreEight_Panel, ScoreNine_Panel, ScoreTen_Panel, ScoreEleven_Panel, ScoreTweleve_Panel, ScoreThirteen_Panel, ScoreFourteen_Panel, ScoreFifteen_Panel, LowerGame_Panel, Question_Panel, Answers_Panel, Answers2_Panel, EnterName_Panel, EnterAudienceNumber_Panel;   //panel for the frame
    private JLabel background, mainmenu, AmountBar, QuestionLabel, AnswerA_Label, AnswerB_Label, AnswerC_Label, AnswerD_Label,playername;  
    private JButton StartSingleGame_Button, StartMultiGame_Button, QuitGame_Button,FiftyFiftyGame_Button, AskAFriendGame_Button, AskTheAudienceGame_Button, WithdrawGame_Button, PlayGame_Button, A_AnswerText, B_AnswerText, C_AnswerText, D_AnswerText, Play_Button, Cancel_Button, MainMenu_Button, PlayAgain_Button,AddPlayer_Button,GoToLeaderboard_button;
    private JTextPane ScoreOne, ScoreOneAmount, ScoreOneIcon, ScoreTwo,ScoreTwoAmount, ScoreTwoIcon, ScoreThree, ScoreThreeAmount, ScoreThreeIcon, ScoreFour, ScoreFourAmount, ScoreFourIcon, ScoreFive, ScoreFiveAmount, ScoreFiveIcon, ScoreSix, ScoreSixAmount, ScoreSixIcon, ScoreSeven, ScoreSevenAmount, ScoreSevenIcon, ScoreEight, ScoreEightAmount, ScoreEightIcon, ScoreNine, ScoreNineAmount, ScoreNineIcon, ScoreTen, ScoreTenAmount, ScoreTenIcon, ScoreEleven, ScoreElevenAmount, ScoreElevenIcon, ScoreTweleve, ScoreTweleveAmount, ScoreTweleveIcon, ScoreThirteen, ScoreThirteenAmount, ScoreThirteenIcon, ScoreFourteen, ScoreFourteenAmount, ScoreFourteenIcon, ScoreFifteen, ScoreFifteenAmount, ScoreFifteenIcon,NameEntry,AudienceNameEntry,EnterNameText;
    private JTextArea Question, AnswerA, AnswerB,AnswerC, AnswerD;
    private JLayeredPane EnterName_Pane, EnterAudienceNumber_Pane;
    private JOptionPane infopane; // creates a dialog for an information message
    private JOptionPane errorpane;// creates a dialog for an error message
    private JOptionPane Versionpane; // create a dialog to return a yes or no answer
   
    private String logo = "images/altarserverlogo3.gif"; // logo for game
    private String backgroundimage = "images/background1024x768.gif";
    private String fiftyfifty = "images/5050icon_normal.gif";
    private String fiftyfiftypressed = "images/5050icon_yellow.gif";
    private String fiftyfiftydisabled = "images/5050icon_xed.gif";
    private String fiftyfiftysmallnormal = "images/small5050normal.gif";
    private String fiftyfiftysmalldisabled = "images/small5050_xed.gif";
    private String askafriend = "images/askafriendicon_normal.gif";
    private String askafriendpressed = "images/askafriendicon_yellow.gif";
    private String askafrienddisabled = "images/askafriendicon_xed.gif";
    private String askafriendsmallnormal = "images/smallaskafriend_normal.gif";
    private String askafriendsmalldisabled = "images/smallaskafriend_xed.gif";
    private String asktheaudience = "images/asktheaudienceicon_normal.gif";
    private String asktheaudiencepressed = "images/asktheaudienceicon_yellow.gif";
    private String asktheaudiencedisabled = "images/asktheaudienceicon_xed.gif";
    private String asktheaudiencesmallnormal = "images/smallasktheaudience_normal.gif";
    private String asktheaudiencesmalldisabled = "images/smallasktheaudience_xed.gif";
    private String withdraw = "images/withdrawicon_normal.gif";
    private String withdrawpressed = "images/withdrawicon_yellow.gif";
    private String scoreiconlocation = "images/scoreicon.gif";
    private String amountbaricon = "images/amountbar.gif";
    private String questionicon = "images/questionicon.gif";
    private String answericon = "images/answericon.gif";
    private String a_answericon = "images/a_answericon.gif";
    private String a_answerselecticon = "images/a_answericonselect.gif";
    private String b_answericon = "images/b_answericon.gif";
    private String b_answerselecticon = "images/b_answericonselect.gif";
    private String c_answericon = "images/c_answericon.gif";
    private String c_answerselecticon = "images/c_answericonselect.gif";
    private String d_answericon = "images/d_answericon.gif";
    private String d_answerselecticon = "images/d_answericonselect.gif";
    private String answerselectedicon = "images/answerselectedicon.gif";
    private String answercorrecticon = "images/answercorrecticon.gif";
    private String answerlineicon = "images/answerline.gif";
    private String leaderboardicon = "images/scoreboardicon.gif";
    private JLayeredPane QuestionLayeredPane,AnswersLayeredPane;
    
    public boolean music; // tells program if music is set to be on or off
    public PlayerBoard Playerboard;  //instance of playerboard
    public QuestionsBase Questionsbase;  //instance of questionbase
    private boolean[] game_info; // used to indicate information on screen for each question, first is the question and the following 4 are answers a,b,c,d
    Questions QuestionAsked; //current question being asked
    public ScoreBoard Scoreboard; // shows instance of Scoreboard 
   
    private Vector <String> Name_stack;  // stack of names to pass to Game instances to intialise players
    private Game Game;
    private  DisplayGUI DisplayGUI;
    public Music Background_music;
    
    /** Creates a new instance of WhoWantsTo */
    public WhoWantsTo() 
    {
        
        frame = new JFrame(game_name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(new Dimension(1024, 768));
        setMenuItems();
        frame.setJMenuBar(Menubar);
        infopane= new JOptionPane(); // creates a dialog for an information message
        errorpane= new JOptionPane();// creates a dialog for an error message
        Versionpane= new JOptionPane();
         setSystemStart();
         setStartScreen();
        //setGameScreen();
        //setEnterNamePanel();
        //showLeaderBoard();
       
        
       
        frame.pack(); 
        frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
        
        
    }
    //initaialises all items
    private void setSystemStart()
    {
        music = true;
        Background_music = new Music();
        Playerboard = new PlayerBoard();
        Playerboard.populatePlayerBoard(); 
        Questionsbase = new QuestionsBase();
        
    }
    // sets up setting for the main starting panel
    private void setMainPanel()
    {
        // setting up main panel and background
        
        ImageIcon image = new ImageIcon(backgroundimage);
        
	background = new JLabel(image);
       
	background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
	frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
         
	Main_Panel = new JPanel();
        Main_Panel.setLayout(new BoxLayout(Main_Panel, BoxLayout.Y_AXIS));
	Main_Panel.setOpaque(false);
       	frame.setContentPane(Main_Panel);
        
       
	
    }
    //set up logo panel
    private void setLogoPanel()
    {
        ImageIcon WwtbLogoImage = new ImageIcon(logo); 
        JLabel WwtbLogo = new JLabel(WwtbLogoImage);
        WWTBLogo_Panel = new JPanel();
        WWTBLogo_Panel.setLayout(new GridBagLayout());
        WWTBLogo_Panel.add(WwtbLogo);
        WWTBLogo_Panel.setOpaque(false);
       
    }
    
    private void setStartScreen()
    {
        if(music == true)
        {
            Background_music.close();
            Background_music.playStartUp();
        }
        setMainPanel();
        ImageIcon mainmenuimage = new ImageIcon("images/mainmenu.gif");
        mainmenu = new JLabel(mainmenuimage);
        MainMenuLogo_Panel = new JPanel();
        MainMenuLogo_Panel.setLayout(new GridBagLayout());
        MainMenuLogo_Panel.add(mainmenu);
        MainMenuLogo_Panel.setOpaque(false);
         // setting up Main Menu logo pael
       
	Main_Panel.add(MainMenuLogo_Panel);
        setLogoPanel();
        Main_Panel.add(WWTBLogo_Panel);
       
        // setting up of Start single game image and interactive button.
        ImageIcon buttonlineimage = new ImageIcon("images/doublebuttonline.gif");
        ImageIcon startsinglegameimage = new ImageIcon("images/startsinglegame.gif");
        ImageIcon startsinglegamepressedimage = new ImageIcon("images/startsinglegamepressed.gif");
        JLabel buttonline = new JLabel(buttonlineimage);
        JLabel buttonline2 = new JLabel(buttonlineimage);
       
        StartSingleGame_Button =  new JButton(startsinglegameimage);
        StartSingleGame_Button.setBorderPainted(false);
        StartSingleGame_Button.setContentAreaFilled(false);
        StartSingleGame_Button.setMargin(new Insets(-2,-2,-2,-2));
        StartSingleGame_Button.setFocusPainted(false);
        StartSingleGame_Button.setOpaque(false);
         StartSingleGame_Button.setPressedIcon(startsinglegamepressedimage);
        
       
        StartSingleGame_Button.addActionListener(this);
        StartSingleGame_Panel = new JPanel();
        StartSingleGame_Panel.setLayout(new GridBagLayout());
        StartSingleGame_Panel.add(buttonline);
        StartSingleGame_Panel.add(StartSingleGame_Button);
        StartSingleGame_Panel.add(buttonline2); 
        StartSingleGame_Panel.setOpaque(false);
        Main_Panel.add(StartSingleGame_Panel);
        
        //setting up Multigame image and interactive panel
        ImageIcon startmultigameimage = new ImageIcon("images/startmultigame.gif");
        ImageIcon startmultigamepressedimage = new ImageIcon("images/startmultigamepressed.gif");
        JLabel buttonline3 = new JLabel(buttonlineimage);
        JLabel buttonline4 = new JLabel(buttonlineimage);
        StartMultiGame_Button =  new JButton(startmultigameimage);
        StartMultiGame_Button.setBorderPainted(false);
        StartMultiGame_Button.setContentAreaFilled(false);
        StartMultiGame_Button.setFocusPainted(false);
        StartMultiGame_Button.setPressedIcon(startmultigamepressedimage);
        StartMultiGame_Button.addActionListener(this);
        StartMultiGame_Button.setMargin(new Insets(-2,-2,-2,-2));
        StartMultiGame_Panel = new JPanel();
        StartMultiGame_Panel.setLayout(new GridBagLayout());
        StartMultiGame_Panel.add(buttonline3);
        StartMultiGame_Panel.add(StartMultiGame_Button);
        StartMultiGame_Panel.add(buttonline4);
        StartMultiGame_Panel.setOpaque(false);
        Main_Panel.add( StartMultiGame_Panel);
        
        // setting up quit image and interactive panel
        ImageIcon quitgameimage = new ImageIcon("images/quitgame.gif");
        ImageIcon quitgamepressedimage = new ImageIcon("images/quitgamepressed.gif");
        JLabel buttonline5 = new JLabel(buttonlineimage);
        JLabel buttonline6 = new JLabel(buttonlineimage);
        QuitGame_Button =  new JButton(quitgameimage);
        QuitGame_Button.setBorderPainted(false);
        QuitGame_Button.setContentAreaFilled(false);
        QuitGame_Button.setMargin(new Insets(-2,-2,-2,-2));
        QuitGame_Button.setPressedIcon(quitgamepressedimage);
        QuitGame_Button.setFocusPainted(false);
        QuitGame_Button.addActionListener(this);
        Quit_Panel = new JPanel();
        Quit_Panel.setLayout(new GridBagLayout());
        Quit_Panel.add(buttonline5);
        Quit_Panel.add(QuitGame_Button);
        Quit_Panel.add(buttonline6);
        Quit_Panel.setOpaque(false);
        Main_Panel.add(Quit_Panel);
        
        frame.pack(); 
        frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
        
        
        
    }
    private void setGameScreen()
    {
        setMainPanel();
        //setting up top panel 
        
       
        playername = new JLabel(Game.Player.getPlayerName(),SwingConstants.CENTER);
       
        JLabel player = new JLabel("Player: ", SwingConstants.CENTER);
        player.setFont(new Font("Serif", Font.PLAIN, 36));
        playername.setFont(new Font("Serif", Font.PLAIN, 36));
        playername.setForeground(new Color(0xffffdd));
        player.setForeground(new Color(0x54833E));
        PlayerName_Panel = new JPanel();
        PlayerName_Panel.setLayout(new GridBagLayout());
        PlayerName_Panel.setOpaque(false);
        PlayerName_Panel.add(player);
        PlayerName_Panel.add(playername);
        Main_Panel.add(PlayerName_Panel);
        // use a text box instead
       
        
        TopGame_Panel = new JPanel();
      
       
       // TopGame_Panel.setLayout(new GridBagLayout());
        TopGame_Panel.setLayout(new BorderLayout());
        TopGame_Panel.setOpaque(false);
        Main_Panel.add(TopGame_Panel);
        
        //setting up left icon panel 
        LeftIcon_Panel = new JPanel();
        LeftIcon_Panel.setLayout(new BoxLayout(LeftIcon_Panel, BoxLayout.Y_AXIS));
        LeftIcon_Panel.setOpaque(false);
        
        // setting up 50:50 game button
        ImageIcon FiftyFiftyGameimage = new ImageIcon(fiftyfifty);
        FiftyFiftyGame_Button =  new JButton(FiftyFiftyGameimage);
        setGameButtons(FiftyFiftyGame_Button,fiftyfiftypressed, fiftyfiftydisabled);
        LeftIcon_Panel.add(FiftyFiftyGame_Button);
       // TopGame_Panel.add(LeftIcon_Panel, BorderLayout.WEST);
        
        // setting up ask a friend game button
        ImageIcon AskAFriendGameimage = new ImageIcon(askafriend);
        AskAFriendGame_Button =  new JButton(AskAFriendGameimage);
        setGameButtons( AskAFriendGame_Button,askafriendpressed, askafrienddisabled);
        LeftIcon_Panel.add(AskAFriendGame_Button);
        
        //setting up ask the audience game button
        ImageIcon AskTheAudienceGameimage = new ImageIcon(asktheaudience);
        AskTheAudienceGame_Button =  new JButton(AskTheAudienceGameimage);
        setGameButtons(AskTheAudienceGame_Button,asktheaudiencepressed, asktheaudiencedisabled);
        LeftIcon_Panel.add(AskTheAudienceGame_Button);
        
        //setting up withdraw game button
        ImageIcon WithdrawGameimage = new ImageIcon(withdraw);
        ImageIcon SelectedGameImage = new ImageIcon(withdrawpressed);
        WithdrawGame_Button =  new JButton(WithdrawGameimage);
        WithdrawGame_Button.setBorderPainted(false);
        WithdrawGame_Button.setContentAreaFilled(false);
        WithdrawGame_Button.setMargin(new Insets(2,2,2,2));
        WithdrawGame_Button.setSelectedIcon(SelectedGameImage);
        WithdrawGame_Button.setRolloverEnabled(false);
        WithdrawGame_Button.setFocusPainted(false);
        WithdrawGame_Button.addActionListener(this); 
        LeftIcon_Panel.add(WithdrawGame_Button);
           
       
               
        //setting up play game button
        ImageIcon PlayGameimage = new ImageIcon("images/playicon.gif");
        ImageIcon PlayGamepressedimage = new ImageIcon("images/playiconpressed.gif");
        PlayGame_Button =  new JButton(PlayGameimage);
        PlayGame_Button.setBorderPainted(false);
        PlayGame_Button.setContentAreaFilled(false);
        PlayGame_Button.setMargin(new Insets(10,2,2,2));
        PlayGame_Button.setFocusPainted(false);
        PlayGame_Button.setPressedIcon(PlayGamepressedimage);
        PlayGame_Button.addActionListener(this);
        LeftIcon_Panel.add(PlayGame_Button);
      
        TopGame_Panel.add(LeftIcon_Panel,BorderLayout.WEST);
       
       
        //setting up middle logo panel in top panel
        setLogoPanel();
       
        TopGame_Panel.add(WWTBLogo_Panel,BorderLayout.CENTER);
       
        
        //setting up right score panel
        RightScore_Panel = new JPanel();
        RightScore_Panel.setLayout(new BoxLayout(RightScore_Panel, BoxLayout.Y_AXIS));
        RightScore_Panel.setBorder(BorderFactory.createLineBorder(Color.blue));
        RightScore_Panel.setOpaque(false);
        
        Scoreboard.loadScoreBoard();
        TopGame_Panel.add(RightScore_Panel,BorderLayout.EAST);
        setLowerGamePanel();
        //Main_Panel.add(LowerGame_Panel);
        
        setQuestionPanel();
        Main_Panel.add(Question_Panel);
        setAnswerPanel();
        Main_Panel.add(Answers_Panel);
        
        frame.pack(); 
        frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
        
        if(music == true)
        {
            Background_music.close();
            Background_music.playStartOfGame();
            
        }
        
        
        
        
    }
    
    private void setLowerGamePanel()
    {
        LowerGame_Panel = new JPanel();
        LowerGame_Panel.setPreferredSize(new Dimension(960, 205));
        LowerGame_Panel.setOpaque(false);
        AmountBar = new JLabel(new ImageIcon(amountbaricon));
         //AmountBar.setText("€500");
         AmountBar.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 85));
         AmountBar.setHorizontalTextPosition(JLabel.CENTER);
         AmountBar.setVerticalTextPosition(JLabel.CENTER);
         //AmountBar.setMargin(new Insets(2,2,2,2));
        AmountBar.setForeground(new Color(0xF7C752));
        LowerGame_Panel.add(AmountBar);
        Main_Panel.add(LowerGame_Panel);
        LowerGame_Panel.setVisible(false);
        
       
        
    
   
        
      
   }
    
    public void showLeaderBoard()
    {
        
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
        ImageIcon FiftyFiftySmallNormalimage = new ImageIcon(fiftyfiftysmallnormal);
        ImageIcon FiftyFiftySmallDisabledimage = new ImageIcon(fiftyfiftysmalldisabled);
        ImageIcon AskAFriendSmallNormalimage = new ImageIcon(askafriendsmallnormal);
        ImageIcon AskAFriendSmallDisabledimage = new ImageIcon(askafriendsmalldisabled);
        ImageIcon AskTheAudienceSmallNormalimage = new ImageIcon(asktheaudiencesmallnormal);
        ImageIcon AskTheAudienceSmallDisabledimage = new ImageIcon(asktheaudiencesmalldisabled);
        
        LeaderBoard_Pane.setPreferredSize(new Dimension(960, 550));
         Vector Playerboard_vector = Playerboard.returnPlayerBoard();
        int number_on_scoreboard;
        if(Playerboard_vector.isEmpty())
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
            Nothinghere_textpane.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
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
            HeadingsLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
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
                    Player Player = (Player) Playerboard_vector.elementAt(i);
                    ycoordinate = (i*60) + 60;  
                    TempJLabel = new JLabel(LeaderBoardimage);
                    TempJLabel.setBounds(0, ycoordinate,960,47);
                    LeaderBoard_Pane.add(TempJLabel,new Integer(0));
                    ycoordinate = ycoordinate+10;
                    Playername_textpane = new JTextPane();
                    Playername_textpane.setText( i+1+"  "+Player.getPlayerName());
                    Playername_textpane.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
                    Playername_textpane.setForeground(new Color(0xFFFFFF));
                    Playername_textpane.setEditable(false);
                    Playername_textpane.setOpaque(false);
                    Playername_textpane.setBounds(290, ycoordinate,320,40);
                    LeaderBoard_Pane.add(Playername_textpane,new Integer(1));
                    boolean[] lifelines = Player.getPlayerLifelines();
                    if(lifelines[0] == true)
                    {
                        TempFiftyFiftyLabel = new JLabel(FiftyFiftySmallDisabledimage);
                    }
                    else if(lifelines[0] == false)
                    {
                        TempFiftyFiftyLabel = new JLabel(FiftyFiftySmallNormalimage);
                    }
                    TempFiftyFiftyLabel.setBounds(430, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempFiftyFiftyLabel,new Integer(1));
            
                    if(lifelines[1] == true)
                    {
                        TempAskAFriendLabel = new JLabel(AskAFriendSmallDisabledimage);
                    }
                    else if(lifelines[1] == false)
                    {
                        TempAskAFriendLabel = new JLabel(AskAFriendSmallNormalimage);
                    }
                    TempAskAFriendLabel.setBounds(465, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempAskAFriendLabel,new Integer(1));
            
                    if(lifelines[2] == true)
                    {
                        TempAskTheAudienceLabel = new JLabel(AskTheAudienceSmallDisabledimage);
                    }
                    else if(lifelines[2] == false)
                    {
                        TempAskTheAudienceLabel = new JLabel(AskTheAudienceSmallNormalimage);
                    }
            
                    TempAskTheAudienceLabel.setBounds(500, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempAskTheAudienceLabel,new Integer(1));
            
                    ScoreReachedLabel = new JLabel(Scoreboard.intToString(Player.getPlayerScoreReached()));
                    ScoreReachedLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
                    ScoreReachedLabel.setForeground(new Color(0x52416B));
                    ScoreReachedLabel.setBounds(555, ycoordinate-10,100,40);
                    LeaderBoard_Pane.add(ScoreReachedLabel,new Integer(1));
             
                    FinalScoreLabel = new JLabel(Scoreboard.intToString(Player.getPlayerScore()));
                    FinalScoreLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
                    FinalScoreLabel.setForeground(new Color(0x42C36B));
                    FinalScoreLabel.setBounds(617, ycoordinate-10,100,40);
                    LeaderBoard_Pane.add(FinalScoreLabel,new Integer(1));
            
                }
          }
          Main_Panel.add(LeaderBoard_Pane);
        JPanel  BottomScoreBoard_Panel = new JPanel();  
        BottomScoreBoard_Panel.setOpaque(false);
        MainMenu_Button = new JButton("Menu Menu");
        MainMenu_Button.setFont(new Font("Arial", Font.BOLD, 18));
        MainMenu_Button.setForeground(new Color(0x42C36B));
        MainMenu_Button.setBackground(new Color(0x52416B));
        MainMenu_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        MainMenu_Button.setBorderPainted(true);
        MainMenu_Button.setContentAreaFilled(false);
        MainMenu_Button.setMargin(new Insets(10,2,2,2));
        MainMenu_Button.addActionListener(this);
        MainMenu_Button.setBounds(320,50,50,30);
        MainMenu_Button.setOpaque(true);
         BottomScoreBoard_Panel.add(MainMenu_Button);
        
        PlayAgain_Button = new JButton("Play Again");
        PlayAgain_Button.setFont(new Font("Arial", Font.BOLD, 18));
        PlayAgain_Button.setForeground(new Color(0x42C36B));
        PlayAgain_Button.setBackground(new Color(0x52416B));
        PlayAgain_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        PlayAgain_Button.setBorderPainted(true);
        PlayAgain_Button.setContentAreaFilled(false);
        PlayAgain_Button.setMargin(new Insets(10,2,2,2));
        PlayAgain_Button.setFocusPainted(false);
        PlayAgain_Button.addActionListener(this);
        PlayAgain_Button.setOpaque(true);
        PlayAgain_Button.setBounds(470,50,70,30);
        BottomScoreBoard_Panel.add( PlayAgain_Button);
          Main_Panel.add( BottomScoreBoard_Panel);
          
        frame.pack(); 
        frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
        if(music == true)
        {
            Background_music.close();
            Background_music.playLeaderBoard();
        }
    }
    
    public void showMultiPlayerLeaderBoard(PlayerBoard Multiplayerboard)
    {
        
        setMainPanel();
        JPanel TopOfLeaderBoard = new JPanel();
        TopOfLeaderBoard.setOpaque(false);
        
        JLabel LeaderBoard_Label = new JLabel("Multi-Player Leader Board");
         LeaderBoard_Label.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 38));
         LeaderBoard_Label.setForeground(new Color(0xFFFFFF));
        LeaderBoard_Label.setOpaque(false);
        TopOfLeaderBoard.add(LeaderBoard_Label);
        Main_Panel.add(TopOfLeaderBoard);
        
        JLayeredPane LeaderBoard_Pane = new JLayeredPane();
        ImageIcon LeaderBoardimage = new ImageIcon(leaderboardicon);
        ImageIcon FiftyFiftySmallNormalimage = new ImageIcon(fiftyfiftysmallnormal);
        ImageIcon FiftyFiftySmallDisabledimage = new ImageIcon(fiftyfiftysmalldisabled);
        ImageIcon AskAFriendSmallNormalimage = new ImageIcon(askafriendsmallnormal);
        ImageIcon AskAFriendSmallDisabledimage = new ImageIcon(askafriendsmalldisabled);
        ImageIcon AskTheAudienceSmallNormalimage = new ImageIcon(asktheaudiencesmallnormal);
        ImageIcon AskTheAudienceSmallDisabledimage = new ImageIcon(asktheaudiencesmalldisabled);
        
        LeaderBoard_Pane.setPreferredSize(new Dimension(960, 550));
         Vector Playerboard_vector = Multiplayerboard.returnPlayerBoard();
        int number_on_scoreboard;
        if(Playerboard_vector.isEmpty())
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
            Nothinghere_textpane.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
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
            HeadingsLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
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
                    Player Player = (Player) Playerboard_vector.elementAt(i);
                    ycoordinate = (i*60) + 60;  
                    TempJLabel = new JLabel(LeaderBoardimage);
                    TempJLabel.setBounds(0, ycoordinate,960,47);
                    LeaderBoard_Pane.add(TempJLabel,new Integer(0));
                    ycoordinate = ycoordinate+10;
                    Playername_textpane = new JTextPane();
                    Playername_textpane.setText( i+1+"  "+Player.getPlayerName());
                    Playername_textpane.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
                    Playername_textpane.setForeground(new Color(0xFFFFFF));
                    Playername_textpane.setEditable(false);
                    Playername_textpane.setOpaque(false);
                    Playername_textpane.setBounds(290, ycoordinate,320,40);
                    LeaderBoard_Pane.add(Playername_textpane,new Integer(1));
                    boolean[] lifelines = Player.getPlayerLifelines();
                    if(lifelines[0] == true)
                    {
                        TempFiftyFiftyLabel = new JLabel(FiftyFiftySmallDisabledimage);
                    }
                    else if(lifelines[0] == false)
                    {
                        TempFiftyFiftyLabel = new JLabel(FiftyFiftySmallNormalimage);
                    }
                    TempFiftyFiftyLabel.setBounds(430, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempFiftyFiftyLabel,new Integer(1));
            
                    if(lifelines[1] == true)
                    {
                        TempAskAFriendLabel = new JLabel(AskAFriendSmallDisabledimage);
                    }
                    else if(lifelines[1] == false)
                    {
                        TempAskAFriendLabel = new JLabel(AskAFriendSmallNormalimage);
                    }
                    TempAskAFriendLabel.setBounds(465, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempAskAFriendLabel,new Integer(1));
            
                    if(lifelines[2] == true)
                    {
                        TempAskTheAudienceLabel = new JLabel(AskTheAudienceSmallDisabledimage);
                    }
                    else if(lifelines[2] == false)
                    {
                        TempAskTheAudienceLabel = new JLabel(AskTheAudienceSmallNormalimage);
                    }
            
                    TempAskTheAudienceLabel.setBounds(500, ycoordinate,33,21);
                    LeaderBoard_Pane.add(TempAskTheAudienceLabel,new Integer(1));
            
                    ScoreReachedLabel = new JLabel(Scoreboard.intToString(Player.getPlayerScoreReached()));
                    ScoreReachedLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
                    ScoreReachedLabel.setForeground(new Color(0x52416B));
                    ScoreReachedLabel.setBounds(555, ycoordinate-10,100,40);
                    LeaderBoard_Pane.add(ScoreReachedLabel,new Integer(1));
             
                    FinalScoreLabel = new JLabel(Scoreboard.intToString(Player.getPlayerScore()));
                    FinalScoreLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
                    FinalScoreLabel.setForeground(new Color(0x42C36B));
                    FinalScoreLabel.setBounds(617, ycoordinate-10,100,40);
                    LeaderBoard_Pane.add(FinalScoreLabel,new Integer(1));
            
                }
          }
          Main_Panel.add(LeaderBoard_Pane);
        JPanel  BottomScoreBoard_Panel = new JPanel();  
        BottomScoreBoard_Panel.setOpaque(false);
        MainMenu_Button = new JButton("Menu Menu");
        MainMenu_Button.setFont(new Font("Arial", Font.BOLD, 18));
        MainMenu_Button.setForeground(new Color(0x42C36B));
        MainMenu_Button.setBackground(new Color(0x52416B));
        MainMenu_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        MainMenu_Button.setBorderPainted(true);
        MainMenu_Button.setContentAreaFilled(false);
        MainMenu_Button.setMargin(new Insets(10,2,2,2));
        MainMenu_Button.addActionListener(this);
        MainMenu_Button.setBounds(320,50,50,30);
        MainMenu_Button.setOpaque(true);
         BottomScoreBoard_Panel.add(MainMenu_Button);
        
        GoToLeaderboard_button = new JButton("Show LeaderBoard");
        GoToLeaderboard_button.setFont(new Font("Arial", Font.BOLD, 18));
        GoToLeaderboard_button.setForeground(new Color(0x42C36B));
        GoToLeaderboard_button.setBackground(new Color(0x52416B));
        GoToLeaderboard_button.setBorder(BorderFactory.createRaisedBevelBorder());
        GoToLeaderboard_button.setBorderPainted(true);
        GoToLeaderboard_button.setContentAreaFilled(false);
        GoToLeaderboard_button.setMargin(new Insets(10,2,2,2));
        GoToLeaderboard_button.addActionListener(this);
        GoToLeaderboard_button.setOpaque(true);
        GoToLeaderboard_button.setBounds(470,50,70,30);
        BottomScoreBoard_Panel.add(GoToLeaderboard_button);
          Main_Panel.add( BottomScoreBoard_Panel);
          
        frame.pack(); 
        frame.setLocationRelativeTo(null);
	frame.setVisible(true);
        if(music == true)
        {
            Background_music.close();
            Background_music.playLeaderBoard();
        }
    }
    // sets up the panel to enter Player name
    public void setEnterNamePanel()
    {
        //frame.remove(Main_Panel);
        setMainPanel();
        Name_stack = new Vector<String>(0);
        JPanel Blank_Panel1 = new JPanel();
         Blank_Panel1.setPreferredSize(new Dimension(10, 47));
        Blank_Panel1.setOpaque(false);
         Main_Panel.add(Blank_Panel1);
        
        EnterName_Panel = new JPanel();
        EnterName_Panel.setOpaque(false);
        EnterNameText = new JTextPane();
        EnterNameText.setText("Enter your name:");
        EnterNameText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        EnterNameText.setForeground(new Color(0xFFFFFF));
        EnterNameText.setEditable(false);
        EnterNameText.setOpaque(false);
        EnterName_Panel.add(EnterNameText);
         Main_Panel.add(EnterName_Panel);
       
        
        ImageIcon nameimage = new ImageIcon("images/nameicon.gif");
        EnterName_Pane = new JLayeredPane();
        EnterName_Pane.setPreferredSize(new Dimension(871, 87));
        JLabel EntryLabel = new JLabel(nameimage);
        EntryLabel.setBounds(0,0,871,47);
        EnterName_Pane.add( EntryLabel,new Integer(0));
        NameEntry =  new JTextPane();
        NameEntry.setEditable(true);
        NameEntry.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        NameEntry.setForeground(new Color(0xFFFF94));
        NameEntry.setBackground(new Color(0x000000));
        NameEntry.setCaretColor(new Color(0xFFFFFF));
        NameEntry.setMargin(new Insets(-2,-2,-2,-2));
        NameEntry.setBounds(320,4,290,40);
        EnterName_Pane.add(NameEntry,new Integer(1));
       
        
              
        Play_Button = new JButton("Play");
        Play_Button.setFont(new Font("Arial", Font.BOLD, 18));
        Play_Button.setForeground(new Color(0x42C36B));
        Play_Button.setBackground(new Color(0x52416B));
        Play_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        Play_Button.setBorderPainted(true);
        Play_Button.setContentAreaFilled(false);
        Play_Button.setMargin(new Insets(10,2,2,2));
        Play_Button.addActionListener(this);
        Play_Button.setBounds(320,50,50,30);
        Play_Button.setOpaque(true);
        EnterName_Pane.add(Play_Button, new Integer(0));
        
        Cancel_Button = new JButton("Cancel");
        Cancel_Button.setFont(new Font("Arial", Font.BOLD, 18));
        Cancel_Button.setForeground(new Color(0x42C36B));
        Cancel_Button.setBackground(new Color(0x52416B));
        Cancel_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        Cancel_Button.setBorderPainted(true);
        Cancel_Button.setContentAreaFilled(false);
        Cancel_Button.setMargin(new Insets(10,2,2,2));
        Cancel_Button.setOpaque(true);
        Cancel_Button.addActionListener(this);
        Cancel_Button.setBounds(470,50,70,30);
        EnterName_Pane.add( Cancel_Button, new Integer(0));
         Main_Panel.add( EnterName_Pane);
         frame.pack(); 
        frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
        if(music == true)
        {
            Background_music.close();
            Background_music.playEnterName();
        }
        
   }
    
    public void enterMultiNamePanel()
    {
        //frame.remove(Main_Panel);
        setMainPanel();
        Name_stack = new Vector<String>(0);
        JPanel Blank_Panel1 = new JPanel();
        Blank_Panel1.setPreferredSize(new Dimension(10, 47));
        Blank_Panel1.setOpaque(false);
         Main_Panel.add(Blank_Panel1);
        
        EnterName_Panel = new JPanel();
        EnterName_Panel.setOpaque(false);
        EnterNameText = new JTextPane();
        EnterNameText.setText("Enter your name:");
        EnterNameText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        EnterNameText.setForeground(new Color(0xFFFFFF));
        EnterNameText.setEditable(false);
        EnterNameText.setOpaque(false);
        EnterName_Panel.add(EnterNameText);
         Main_Panel.add(EnterName_Panel);
       
        
        ImageIcon nameimage = new ImageIcon("images/nameicon.gif");
        EnterName_Pane = new JLayeredPane();
        EnterName_Pane.setPreferredSize(new Dimension(871, 87));
        JLabel EntryLabel = new JLabel(nameimage);
        EntryLabel.setBounds(0,0,871,47);
        EnterName_Pane.add( EntryLabel,new Integer(0));
        NameEntry =  new JTextPane();
        NameEntry.setEditable(true);
        NameEntry.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
        NameEntry.setForeground(new Color(0xFFFF94));
        NameEntry.setBackground(new Color(0x000000));
        NameEntry.setCaretColor(new Color(0xFFFFFF));
        NameEntry.setMargin(new Insets(-2,-2,-2,-2));
        NameEntry.setBounds(320,4,290,40);
        EnterName_Pane.add(NameEntry,new Integer(1));
       
        
              
        Play_Button = new JButton("Play");
        Play_Button.setFont(new Font("Arial", Font.BOLD, 18));
        Play_Button.setForeground(new Color(0x42C36B));
        Play_Button.setBackground(new Color(0x52416B));
        Play_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        Play_Button.setBorderPainted(true);
        Play_Button.setContentAreaFilled(false);
        Play_Button.setMargin(new Insets(10,2,2,2));
        Play_Button.addActionListener(this);
        Play_Button.setBounds(280,50,50,30);
        Play_Button.setOpaque(true);
        EnterName_Pane.add(Play_Button, new Integer(0));
        
        AddPlayer_Button = new JButton("Add New Player");
        AddPlayer_Button.setFont(new Font("Arial", Font.BOLD, 18));
        AddPlayer_Button.setForeground(new Color(0x42C36B));
        AddPlayer_Button.setBackground(new Color(0x52416B));
        AddPlayer_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        AddPlayer_Button.setBorderPainted(true);
        AddPlayer_Button.setContentAreaFilled(false);
        AddPlayer_Button.setMargin(new Insets(10,2,2,2));
        AddPlayer_Button.addActionListener(this);
        AddPlayer_Button.setOpaque(true);
        AddPlayer_Button.setBounds(340,50,160,30);
        EnterName_Pane.add( AddPlayer_Button, new Integer(0));
        
        Cancel_Button = new JButton("Cancel");
        Cancel_Button.setFont(new Font("Arial", Font.BOLD, 18));
        Cancel_Button.setForeground(new Color(0x42C36B));
        Cancel_Button.setBackground(new Color(0x52416B));
        Cancel_Button.setBorder(BorderFactory.createRaisedBevelBorder());
        Cancel_Button.setBorderPainted(true);
        Cancel_Button.setContentAreaFilled(false);
        Cancel_Button.setMargin(new Insets(10,2,2,2));
        Cancel_Button.addActionListener(this);
        Cancel_Button.setOpaque(true);
        Cancel_Button.setBounds(510,50,70,30);
        EnterName_Pane.add( Cancel_Button, new Integer(0));
         Main_Panel.add( EnterName_Pane);
         frame.pack(); 
        frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
        if(music == true)
        {
            Background_music.close();
            Background_music.playEnterName();
        }
    }
     // sets up the lower answer panels A,B,C and D with background image icons. 
     private void setAnswerPanel()
    {
        AnswersLayeredPane = new JLayeredPane();
        AnswersLayeredPane.setPreferredSize(new Dimension(960, 100));
        Answers_Panel = new JPanel();
        
         
        Answers_Panel.setOpaque(false);
        
        ImageIcon lineimage = new ImageIcon(answerlineicon);
        ImageIcon middleline = new ImageIcon("images/linebetween.gif");
        ImageIcon answersimage = new ImageIcon(answericon);
        ImageIcon A_answerimage = new ImageIcon(a_answericon);
        ImageIcon A_answerselectimage = new ImageIcon(a_answerselecticon);
       
        // sets answer A text and icon
        A_AnswerText =  new JButton(A_answerimage);
       
        
        A_AnswerText.setSelectedIcon(A_answerselectimage);
        
        
        JLabel Line1 =  new JLabel(lineimage);
        Line1.setBounds(0,20,96,2);
        
        AnswerA_Label = new JLabel(answersimage);
        AnswerA_Label.setBounds(95,0,377,42);
        A_AnswerText.setBounds(105,2, 350, 38);
        //A_AnswerText.setText("Doo bee doadddasddasdasdadsaasdsadasasddaadsdasdsadasdas");
       
        AnswersLayeredPane.add(Line1,new Integer(0));
        setUpAnswerLabel(AnswerA_Label, A_AnswerText);
              
      
        // line between answer A and B
        JLabel LineBetween = new JLabel(middleline);
        LineBetween.setBounds(470,20,24,2);
        AnswersLayeredPane.add(LineBetween,new Integer(0));
       
        // set answer B text and icon
        ImageIcon B_answerimage = new ImageIcon(b_answericon);
        ImageIcon B_answerselectimage = new ImageIcon(b_answerselecticon);
        B_AnswerText = new JButton(B_answerimage);
        B_AnswerText.setSelectedIcon(B_answerselectimage);
        AnswerB_Label = new JLabel(answersimage);
        AnswerB_Label.setBounds(494,0,377,42);
        //B_AnswerText.setText("Doo who dee bee doo");
        B_AnswerText.setBounds(506,2, 350, 38);
        setUpAnswerLabel(AnswerB_Label, B_AnswerText);
        JLabel Line2 =  new JLabel(lineimage);
        Line2.setBounds(871,20,96,2);
        AnswersLayeredPane.add(Line2,new Integer(0));
        
         //set answer C text and icon
        ImageIcon C_answerimage = new ImageIcon(c_answericon);
        ImageIcon C_answerselectimage = new ImageIcon(c_answerselecticon);
        C_AnswerText = new JButton(C_answerimage);
        C_AnswerText.setSelectedIcon(C_answerselectimage);
        JLabel Line3 =  new JLabel(lineimage);
        Line3.setBounds(0,70,96,2);
        AnswerC_Label = new JLabel(answersimage);
        AnswerC_Label.setBounds(95,50,377,42);
        //C_AnswerText.setText("Doo bee dom dee doo");
        C_AnswerText.setBounds(105,52, 350, 38);
        AnswersLayeredPane.add(Line3,new Integer(0));
        setUpAnswerLabel(AnswerC_Label, C_AnswerText);
        
        // line between answer C and D
        JLabel LineBetween2 = new JLabel(middleline);
        LineBetween2.setBounds(470,70,24,2);
        AnswersLayeredPane.add(LineBetween2,new Integer(0));
        
         // set answer D text and icon
        ImageIcon D_answerimage = new ImageIcon(d_answericon);
        ImageIcon D_answerselectimage = new ImageIcon(d_answerselecticon);
        D_AnswerText = new JButton(D_answerimage);
        D_AnswerText.setSelectedIcon(D_answerselectimage);
        AnswerD_Label = new JLabel(answersimage);
        AnswerD_Label.setBounds(494,50,377,42);
        //D_AnswerText.setText("Doo  dooo rwewereewrwewerwgwerwesdffdssdfsdfdfsggwf?");
       
        D_AnswerText.setBounds(506,52, 350, 38);
        setUpAnswerLabel(AnswerD_Label, D_AnswerText);
        JLabel Line4 =  new JLabel(lineimage);
        Line4.setBounds(871,70,96,2);
        AnswersLayeredPane.add(Line4,new Integer(0));
        
        Answers_Panel.add(AnswersLayeredPane);
    }   
    // sets up the labels for the answer icons and text box on the game screen
    private void setUpAnswerLabel(JLabel Answerlabel, JButton  AnswerText)
    {
      
       
     AnswersLayeredPane.add( Answerlabel,new Integer(0));
      
        AnswerText.setForeground(new Color(0xFFFFFF));
        AnswerText.setBackground(new Color(0x000000));
        AnswerText.setBorderPainted(false);
        AnswerText.setContentAreaFilled(false);
        AnswerText.setMargin(new Insets(2,2,2,2));
        AnswerText.setHorizontalAlignment(SwingConstants.LEFT);
        AnswerText.addActionListener(this);
        AnswerText.setRolloverEnabled(false);
        AnswerText.setFocusPainted(false);
      
      
     
       AnswersLayeredPane.add( AnswerText,new Integer(1));
    }
    
  
    
  
   private void setQuestionPanel()
   {
       QuestionLayeredPane = new JLayeredPane();
       Question_Panel = new JPanel();
       
       
       Question_Panel.setSize(new Dimension(960, 88));
       
       Question_Panel.setOpaque(false);
       
       ImageIcon questionimage = new ImageIcon(questionicon);
       QuestionLabel = new JLabel(questionimage);
      
      
       //QuestionLabel.setBounds(0, 0, 754, 59);
       QuestionLabel.setBounds(0, 0, 960, 88);
       Question = new JTextArea();
       
       QuestionLayeredPane.setPreferredSize(new Dimension(960, 88));
      
       
       Question.setLineWrap(true);
       Question.setWrapStyleWord(true);
       Question.setForeground(new Color(0xFFFFFF));
       Question.setBackground(new Color(0x000000));
       Question.setEditable(false);
       Question.setBounds(130,5, 700, 78);
      // Question.setBounds(141,5, 468, 50);
       //Question_Panel.add(question);
      
     QuestionLayeredPane.add(QuestionLabel,new Integer(0));
   
     QuestionLayeredPane.add(Question,new Integer(1));
    Question_Panel.add(QuestionLayeredPane);
    
    
   
     
     
     
      
        
       
        
      
   }
   // takes in a character to show what function takes place; start, last answered or answered
   public void setScoreCells(char function, int cell)
   {
       if(function == 's')
       {
           switch(cell)
           {
               case 1: setScores(ScoreOne,ScoreOneAmount,ScoreOneIcon, ScoreOne_Panel, false); break;
               case 2: setScores(ScoreTwo,ScoreTwoAmount,ScoreTwoIcon, ScoreTwo_Panel, false); break;
               case 3: setScores(ScoreThree,ScoreThreeAmount,ScoreThreeIcon, ScoreThree_Panel, false); break;
               case 4: setScores(ScoreFour,ScoreFourAmount,ScoreFourIcon, ScoreFour_Panel, false); break;
               case 5: setScores(ScoreFive,ScoreFiveAmount,ScoreFiveIcon, ScoreFive_Panel, true); break;
               case 6: setScores(ScoreSix,ScoreSixAmount,ScoreSixIcon, ScoreSix_Panel, false); break;
               case 7: setScores(ScoreSeven,ScoreSevenAmount,ScoreSevenIcon, ScoreSeven_Panel, false); break;
               case 8: setScores(ScoreEight,ScoreEightAmount,ScoreEightIcon, ScoreEight_Panel, false); break;
               case 9: setScores(ScoreNine,ScoreNineAmount,ScoreNineIcon, ScoreNine_Panel,false); break;
               case 10: setScores(ScoreTen,ScoreTenAmount,ScoreTenIcon, ScoreTen_Panel, true); break;
               case 11: setScores(ScoreEleven,ScoreElevenAmount,ScoreElevenIcon, ScoreEleven_Panel, false); break;
               case 12: setScores(ScoreTweleve,ScoreTweleveAmount,ScoreTweleveIcon, ScoreTweleve_Panel, false); break;
               case 13: setScores(ScoreThirteen,ScoreThirteenAmount,ScoreThirteenIcon, ScoreThirteen_Panel, false); break;
               case 14: setScores(ScoreFourteen,ScoreFourteenAmount,ScoreFourteenIcon, ScoreFourteen_Panel, false); break;
               case 15: setScores(ScoreFifteen,ScoreFifteenAmount,ScoreFifteenIcon, ScoreFifteen_Panel, true); break;
               default: ; break;
               
           }
       }
       else if(function == 'a') // last selected
       {
            switch(cell)
           {
               case 1: selected(ScoreOne,ScoreOneAmount,ScoreOneIcon, ScoreOne_Panel, false); break;
               case 2: selected(ScoreTwo,ScoreTwoAmount,ScoreTwoIcon, ScoreTwo_Panel, false); break;
               case 3: selected(ScoreThree,ScoreThreeAmount,ScoreThreeIcon, ScoreThree_Panel, false); break;
               case 4: selected(ScoreFour,ScoreFourAmount,ScoreFourIcon, ScoreFour_Panel, false); break;
               case 5: selected(ScoreFive,ScoreFiveAmount,ScoreFiveIcon, ScoreFive_Panel, true); break;
               case 6: selected(ScoreSix,ScoreSixAmount,ScoreSixIcon, ScoreSix_Panel, false); break;
               case 7: selected(ScoreSeven,ScoreSevenAmount,ScoreSevenIcon, ScoreSeven_Panel, false); break;
               case 8: selected(ScoreEight,ScoreEightAmount,ScoreEightIcon, ScoreEight_Panel, false); break;
               case 9: selected(ScoreNine,ScoreNineAmount,ScoreNineIcon, ScoreNine_Panel, false); break;
               case 10: selected(ScoreTen,ScoreTenAmount,ScoreTenIcon, ScoreTen_Panel, true); break;
               case 11: selected(ScoreEleven,ScoreElevenAmount,ScoreElevenIcon, ScoreEleven_Panel, false); break;
               case 12: selected(ScoreTweleve,ScoreTweleveAmount,ScoreTweleveIcon, ScoreTweleve_Panel, false); break;
               case 13: selected(ScoreThirteen,ScoreThirteenAmount,ScoreThirteenIcon, ScoreThirteen_Panel, false); break;
               case 14: selected(ScoreFourteen,ScoreFourteenAmount,ScoreFourteenIcon, ScoreFourteen_Panel, false); break;
               case 15: selected(ScoreFifteen,ScoreFifteenAmount,ScoreFifteenIcon, ScoreFifteen_Panel, true); break;
               default: ; break;
               
           }  
           
       }
       else  // function is l
       {
           switch(cell)
           {
               case 1: selectScore(ScoreOne,ScoreOneAmount,ScoreOneIcon, ScoreOne_Panel); break;
               case 2: selectScore(ScoreTwo,ScoreTwoAmount,ScoreTwoIcon, ScoreTwo_Panel); break;
               case 3: selectScore(ScoreThree,ScoreThreeAmount,ScoreThreeIcon, ScoreThree_Panel); break;
               case 4: selectScore(ScoreFour,ScoreFourAmount,ScoreFourIcon, ScoreFour_Panel); break;
               case 5: selectScore(ScoreFive,ScoreFiveAmount,ScoreFiveIcon, ScoreFive_Panel); break;
               case 6: selectScore(ScoreSix,ScoreSixAmount,ScoreSixIcon, ScoreSix_Panel); break;
               case 7: selectScore(ScoreSeven,ScoreSevenAmount,ScoreSevenIcon, ScoreSeven_Panel); break;
               case 8: selectScore(ScoreEight,ScoreEightAmount,ScoreEightIcon, ScoreEight_Panel); break;
               case 9: selectScore(ScoreNine,ScoreNineAmount,ScoreNineIcon, ScoreNine_Panel); break;
               case 10: selectScore(ScoreTen,ScoreTenAmount,ScoreTenIcon, ScoreTen_Panel); break;
               case 11: selectScore(ScoreEleven,ScoreElevenAmount,ScoreElevenIcon, ScoreEleven_Panel); break;
               case 12: selectScore(ScoreTweleve,ScoreTweleveAmount,ScoreTweleveIcon, ScoreTweleve_Panel); break;
               case 13: selectScore(ScoreThirteen,ScoreThirteenAmount,ScoreThirteenIcon, ScoreThirteen_Panel); break;
               case 14: selectScore(ScoreFourteen,ScoreFourteenAmount,ScoreFourteenIcon, ScoreFourteen_Panel); break;
               case 15: selectScore(ScoreFifteen,ScoreFifteenAmount,ScoreFifteenIcon, ScoreFifteen_Panel); break;
               default: ; break;
               
           }  
       }
   }
 
    // sets i[ the game score board panels
    public void setGameScorePanels(String[] text)
    {
         int i = 15;
        ScoreFifteen_Panel = new JPanel();
        ScoreFifteen = new JTextPane();
        ScoreFifteen.setText("15");
        ScoreFifteenAmount = new JTextPane();
        ScoreFifteenAmount.setText(text[i]+" ");
        ScoreFifteenIcon = new JTextPane();
        //setScores(ScoreFifteen,ScoreFifteenAmount,ScoreFifteenIcon, ScoreFifteen_Panel);
       
       i--;
       ScoreFourteen_Panel = new JPanel();
       ScoreFourteen = new JTextPane();
       ScoreFourteen.setText("14");
       ScoreFourteenAmount = new JTextPane();
       ScoreFourteenAmount.setText(text[i]+"     ");
       ScoreFourteenIcon = new JTextPane();
       //setScores(ScoreFourteen,ScoreFourteenAmount,ScoreFourteenIcon, ScoreFourteen_Panel);
        i--;
       ScoreThirteen_Panel = new JPanel();
       ScoreThirteen = new JTextPane();
       ScoreThirteen.setText("13");
       ScoreThirteenAmount = new JTextPane();
       ScoreThirteenAmount.setText(text[i]+"     ");
       ScoreThirteenIcon = new JTextPane();
         i--;
      // setScores(ScoreThirteen,ScoreThirteenAmount,ScoreThirteenIcon, ScoreThirteen_Panel);
       
       ScoreTweleve_Panel = new JPanel();
       ScoreTweleve = new JTextPane();
       ScoreTweleve.setText("12");
       ScoreTweleveAmount = new JTextPane();
       ScoreTweleveAmount.setText(text[i]+"     ");
       ScoreTweleveIcon = new JTextPane();
         i--;
      // setScores(ScoreTweleve,ScoreTweleveAmount,ScoreTweleveIcon, ScoreTweleve_Panel);
       
       ScoreEleven_Panel = new JPanel();
       ScoreEleven = new JTextPane();
       ScoreEleven.setText("11");
       ScoreElevenAmount = new JTextPane();
       ScoreElevenAmount.setText(text[i]+"      ");
       ScoreElevenIcon = new JTextPane();
        i--;
      // setScores(ScoreEleven,ScoreElevenAmount,ScoreElevenIcon, ScoreEleven_Panel);
       
       ScoreTen_Panel = new JPanel();
       ScoreTen = new JTextPane();
       ScoreTen.setText("10");
       ScoreTenAmount = new JTextPane();
       ScoreTenAmount.setText(text[i]+"      ");
       ScoreTenIcon = new JTextPane();
        i--;
      // setScores(ScoreTen,ScoreTenAmount,ScoreTenIcon, ScoreTen_Panel);
       
       ScoreNine_Panel = new JPanel();
       ScoreNine = new JTextPane();
       ScoreNine.setText("  9");
       ScoreNineAmount = new JTextPane();
       ScoreNineAmount.setText(text[i]+"      ");
       ScoreNineIcon = new JTextPane();
        i--;
      // setScores(ScoreNine,ScoreNineAmount,ScoreNineIcon, ScoreNine_Panel);
       
       ScoreEight_Panel = new JPanel();
       ScoreEight = new JTextPane();
       ScoreEight.setText("  8");
       ScoreEightAmount = new JTextPane();
       ScoreEightAmount.setText(text[i]+"       ");
       ScoreEightIcon = new JTextPane();
        i--;
       //setScores(ScoreEight,ScoreEightAmount,ScoreEightIcon, ScoreEight_Panel);
       
       ScoreSeven_Panel = new JPanel();
       ScoreSeven = new JTextPane();
       ScoreSeven.setText("  7");
       ScoreSevenAmount = new JTextPane();
       ScoreSevenAmount.setText(text[i]+"       ");
       ScoreSevenIcon = new JTextPane();
        i--;
       //setScores(ScoreSeven,ScoreSevenAmount,ScoreSevenIcon, ScoreSeven_Panel);
       
       ScoreSix_Panel = new JPanel();
       ScoreSix = new JTextPane();
       ScoreSix.setText("  6");
       ScoreSixAmount = new JTextPane();
       ScoreSixAmount.setText(text[i]+"       ");
       ScoreSixIcon = new JTextPane();
        i--;
      // setScores(ScoreSix,ScoreSixAmount,ScoreSixIcon, ScoreSix_Panel);
       
       ScoreFive_Panel = new JPanel();
       ScoreFive = new JTextPane();
       ScoreFive.setText("  5");
       ScoreFiveAmount = new JTextPane();
       ScoreFiveAmount.setText(text[i]+"       ");
       ScoreFiveIcon = new JTextPane();
        i--;
       //setScores(ScoreFive,ScoreFiveAmount,ScoreFiveIcon, ScoreFive_Panel);
       
       ScoreFour_Panel = new JPanel();
       ScoreFour = new JTextPane();
       ScoreFour.setText("  4");
       ScoreFourAmount = new JTextPane();
       ScoreFourAmount.setText(text[i]+"         ");
       ScoreFourIcon = new JTextPane();
        i--;
       //setScores(ScoreFour,ScoreFourAmount,ScoreFourIcon, ScoreFour_Panel);
       
       ScoreThree_Panel = new JPanel();
       ScoreThree = new JTextPane();
       ScoreThree.setText("  3");
       ScoreThreeAmount = new JTextPane();
       ScoreThreeAmount.setText(text[i]+"         ");
       ScoreThreeIcon = new JTextPane();
        i--;
       //setScores(ScoreThree,ScoreThreeAmount,ScoreThreeIcon, ScoreThree_Panel);
      // selectScore(ScoreThree,ScoreThreeAmount,ScoreThreeIcon, ScoreThree_Panel);
       ScoreTwo_Panel = new JPanel();
       ScoreTwo = new JTextPane();
       ScoreTwo.setText("  2");
       ScoreTwoAmount = new JTextPane();
       ScoreTwoAmount.setText(text[i]+"         ");
       ScoreTwoIcon = new JTextPane();
        i--;
      // setScores(ScoreTwo,ScoreTwoAmount,ScoreTwoIcon, ScoreTwo_Panel);
      // selected(ScoreTwo,ScoreTwoAmount,ScoreTwoIcon, ScoreTwo_Panel);
       
       ScoreOne_Panel = new JPanel();
       ScoreOne = new JTextPane();
       ScoreOne.setText("  1");
       ScoreOneAmount = new JTextPane();
       ScoreOneAmount.setText(text[i]+"         ");
       ScoreOneIcon = new JTextPane();
     
       //setScores(ScoreOne,ScoreOneAmount,ScoreOneIcon, ScoreOne_Panel);
      // selected(ScoreOne,ScoreOneAmount,ScoreOneIcon, ScoreOne_Panel);
    }
    //  sets the icons and font colour for scores which have been seleced.
    private void selectScore(JTextPane TextPane, JTextPane Amount, JTextPane Icon, JPanel NewPanel)
    {
        NewPanel.setOpaque(true);
        Icon.setText("");
       
      
        ImageIcon iconimage = new ImageIcon(scoreiconlocation);
        Icon.insertIcon(iconimage);
        Icon.setBackground(new Color(0xF7A239));
        Icon.setOpaque(true);
        TextPane.setForeground(new Color(0xFFFF94));
        TextPane.setBackground(new Color(0xF7A239));
        TextPane.setOpaque(true);
        
        Amount.setForeground(new Color(0x100800));
        Amount.setBackground(new Color(0xF7A239));
        Amount.setOpaque(true);
        
       
        
    }
    
    private void selected(JTextPane TextPane, JTextPane Amount, JTextPane Icon, JPanel NewPanel, boolean ismilestone)
    {
        NewPanel.setOpaque(false);
        Icon.setText("");
       
      
        ImageIcon iconimage = new ImageIcon(scoreiconlocation);
        Icon.insertIcon(iconimage);
        Icon.setOpaque(false);
       
        TextPane.setForeground(new Color(0xFF8A00));
        TextPane.setOpaque(false);
        if(ismilestone == true)
        {
             Amount.setForeground(new Color(0xFFFFFF));
        }
        else
        {
            Amount.setForeground(new Color(0xFF8A00));
        }
        Amount.setOpaque(false);
    }
    
    private void setScores(JTextPane TextPane, JTextPane Amount, JTextPane icon, JPanel NewPanel, boolean ismilestone)
    {
        NewPanel.setBackground(new Color(0x0FF8A00));
        NewPanel.setOpaque(false);
        NewPanel.setBorder(BorderFactory.createEmptyBorder(-6,-6,-6,-6));
        
        icon.setText("  ");
        icon.setMargin(new Insets(0,0,0,0));
        icon.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
        icon.setEditable(false);
        icon.setOpaque(false);
        TextPane.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
        Amount.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
        TextPane.setMargin(new Insets(0,0,0,0));
        TextPane.setForeground(new Color(0xFF8A00));
        TextPane.setEditable(false);
        TextPane.setOpaque(false);
        Amount.setMargin(new Insets(0,0,0,0));
        if(ismilestone == true)
        {
             Amount.setForeground(new Color(0xFFFFFF));
        }
        else
        {
            Amount.setForeground(new Color(0xFF8A00));
        }
        Amount.setEditable(false);
        Amount.setOpaque(false);
        NewPanel.add(TextPane);
        NewPanel.add(icon);
        
        NewPanel.add(Amount);
        RightScore_Panel.add(NewPanel,BorderLayout.WEST);
    }
   
    
  
    // method to setup the game buttons on the game screen
    private void setGameButtons(JButton GameButton, String selectedimage, String disabledimage)
    {
        ImageIcon SelectedGameImage = new ImageIcon(selectedimage);
        ImageIcon DisabledGameImage = new ImageIcon(disabledimage);
        GameButton.setBorderPainted(false);
        GameButton.setContentAreaFilled(false);
        GameButton.setMargin(new Insets(2,2,2,2));
        GameButton.setSelectedIcon(SelectedGameImage);
        GameButton.setDisabledIcon(DisabledGameImage);
        GameButton.setRolloverEnabled(false);
        GameButton.setFocusPainted(false);
        
       GameButton.addActionListener(this); 
    }
    
    // adds menu items to the tool bar
    private void setMenuItems()
    {
            
            Menubar = new JMenuBar();
            // setting up File menu and its items 
            File_menu = new JMenu("File");
            File_menu.setMnemonic(KeyEvent.VK_A); 
            File_menu.getAccessibleContext().setAccessibleDescription( "File menu allows user acces to being new Single and Multi-Player games and restore questions so all can be asked"); 
            Menubar.add(File_menu);
            
            OnePlayer = new JMenuItem("1 Player");
            OnePlayer.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_1, ActionEvent.ALT_MASK)); 
            OnePlayer.addActionListener(this); 
            File_menu.add(OnePlayer);
            
            MultiPlayer = new JMenuItem("Multi-Player");
            MultiPlayer.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_2, ActionEvent.ALT_MASK)); 
            MultiPlayer.addActionListener(this); 
            File_menu.add(MultiPlayer);
            
            RestoreQuestions = new JMenuItem("Restore Questions");
            RestoreQuestions.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_3, ActionEvent.ALT_MASK)); 
            RestoreQuestions.addActionListener(this); 
            File_menu.add(RestoreQuestions);
            
            SkipQuestion = new JMenuItem("Skip Question");
            SkipQuestion.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_4, ActionEvent.ALT_MASK)); 
            SkipQuestion.addActionListener(this); 
            File_menu.add(SkipQuestion);
            
            // Setting up Edit menu and its items
            Edit_menu = new JMenu("Edit");
            Edit_menu.setMnemonic(KeyEvent.VK_A); 
            Edit_menu.getAccessibleContext().setAccessibleDescription( "Edit Menu allows user access to Edit Player Board (in Multi-Player mode), edit Player name, Edit player name, Music on/off and Ask a friend time limit"); 
            Menubar.add(Edit_menu);
            
            EditMultiPlayers = new JMenuItem("Edit Multi-Player Board");
            EditMultiPlayers.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_1, ActionEvent.ALT_MASK)); 
            EditMultiPlayers.addActionListener(this); 
            Edit_menu.add(EditMultiPlayers);
            
            EditPlayerName = new JMenuItem("Edit Player Name");
            EditPlayerName.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_2, ActionEvent.ALT_MASK)); 
            EditPlayerName.addActionListener(this); 
            Edit_menu.add(EditPlayerName);
            
                        
            MusicSetting = new JMenu("Music");
            MusicSetting.setMnemonic(KeyEvent.VK_3);
            Edit_menu.getAccessibleContext().setAccessibleDescription( "Music Setting sub-menu allows access to setting the in game music to on or off"); 
            Edit_menu.add(MusicSetting);
            
             MusicOn = new JMenuItem("Music ON"); 
             MusicOn.setMnemonic(KeyEvent.VK_1);
             MusicOn.addActionListener(this); 
             MusicOff = new JMenuItem("Music OFF"); 
             MusicOff.setMnemonic(KeyEvent.VK_1);
             MusicOff.addActionListener(this); 
            
            
               
                MusicSetting.add(MusicOff);
           
           
            
            
            
            Ask_A_Friend_Time_Limit = new JMenuItem("Edit Ask a friend time limit");
            Ask_A_Friend_Time_Limit.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_4, ActionEvent.ALT_MASK)); 
            Ask_A_Friend_Time_Limit.addActionListener(this); 
            Edit_menu.add(Ask_A_Friend_Time_Limit);
            
            // Setting for View Menu
            View_menu = new JMenu("View");
            View_menu.setMnemonic(KeyEvent.VK_A); 
            View_menu.getAccessibleContext().setAccessibleDescription( "View Menu allows user access to Player Score Board"); 
            Menubar.add(View_menu);
            
            ViewPlayerBoard = new JMenuItem("View Player board");
            ViewPlayerBoard.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_1, ActionEvent.ALT_MASK)); 
            ViewPlayerBoard.addActionListener(this); 
            View_menu.add(ViewPlayerBoard);
            
            // Setting Help menu
            Help_menu = new JMenu("Help");
            Help_menu.setMnemonic(KeyEvent.VK_A); 
            Help_menu.getAccessibleContext().setAccessibleDescription( "Help Menu allows user access to the Help screen and program version number"); 
            Menubar.add(Help_menu);
            
            HelpScreen = new JMenuItem("Help Screen");
            HelpScreen.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_1, ActionEvent.ALT_MASK)); 
            HelpScreen.addActionListener(this); 
            Help_menu.add(HelpScreen);
            
            Version = new JMenuItem("Version");
            Version.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_2, ActionEvent.ALT_MASK)); 
            Version.addActionListener(this); 
            Help_menu.add(Version);
            
    }
    
    /* following method allows a Game instance to set prequistes for a game, takes in
    */
    public void setGameStart()
    {
        if(music == true)
        {
           Background_music.close(); // stop the enter name music
        }
        game_info = new boolean[] {false,false,false,false,false};
       
        Scoreboard = new ScoreBoard(this);
        
        QuestionAsked = new Questions();
        //frame.remove(Main_Panel);
        setGameScreen();
        
        
    }
    
    // outlines steps to take when ending a game
    public void setEndGame()
    {
        if(Questionsbase.writeQuestionArrayToFile()==false)
        {
            errorMessage("could not update Question file, previous questions asked may be asked in next game");
        }
        
    }
    
    
    
    // takes in a Jlabel answer and flashes it green by starting a thread and causing a number of sleeps
    private void answerFlashGreen(JLabel Answerlabel, JButton AnswerText)
    {
       
        
        final JLabel Answer = Answerlabel;
        final JButton BText = AnswerText;
       final Runnable SmallThread = new Runnable()
       {
            
               boolean keepgoing =true;
               public void run()
               {
                   while(keepgoing == true)
                   {
                        try 
                        {
            
                           
                            ImageIcon answercorrectimage = new ImageIcon(answercorrecticon);
                            ImageIcon answericonimage = new ImageIcon(answericon);
                          
                            Answer.setIcon(answercorrectimage);
                            BText.setForeground(new Color(0x000000));
                            Answer.repaint();
                            Thread.sleep(300);
                           
                            Answer.setIcon(answericonimage);
                            BText.setForeground(new Color(0xFFFFFF));
                            Answer.repaint();
                            Thread.sleep(300);
                          
                            Answer.setIcon(answercorrectimage);
                            BText.setForeground(new Color(0x000000));
                            Answer.repaint();
                            Thread.sleep(300);
                           
                            Answer.setIcon(answericonimage);
                            BText.setForeground(new Color(0xFFFFFF));
                            Answer.repaint();
                            Thread.sleep(300);
                           
                            Answer.setIcon(answercorrectimage);
                            BText.setForeground(new Color(0x000000));
                            Answer.repaint();
                            
                            keepgoing = false;
                         }
                         catch (InterruptedException e) 
                         {
                           
                         }
                    }
                 }
            };
       new Thread(SmallThread).start();
       
        
    }
    
    //retrieves question and answer.
    public void showQuestion()
    {
        
        QuestionAsked = Questionsbase.askQuestion(Scoreboard.getDifficulty());
        
        Question.setText(QuestionAsked.getQuestion());
       
       String checkstring = Question.getText();
       
       if(checkstring.length() > 206)
       {
           
           Question.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));
       }
       else if(checkstring.length() <= 206 && checkstring.length() > 108)
       {
             Question.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
       }
      
       else if(checkstring.length() <= 108 && checkstring.length() >= 96)
       {
            Question.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
       }
       else
       {
           Question.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
       }
       game_info[0] = true;
    }
    
    //takes in a char and shows that answer on the GUI
    public void displayAnswer(char answerchar)
    {
        JButton AnswerText = new JButton();
        String checkstring = "";
        
       switch(answerchar)
       {
           case 'A': AnswerText = A_AnswerText;
                     checkstring =  QuestionAsked.getAAnswer();
                        game_info[1] = true; break;
           case 'B': AnswerText = B_AnswerText;
                     checkstring =  QuestionAsked.getBAnswer();
                        game_info[2] = true; break;
           case 'C': AnswerText = C_AnswerText; 
                     checkstring =  QuestionAsked.getCAnswer();
                        game_info[3] = true; break;
           case 'D': AnswerText = D_AnswerText; 
                     checkstring =  QuestionAsked.getDAnswer();
                        game_info[4] = true; break;
           default: ; break;
              
       }
       
       AnswerText.setText(checkstring);
      
       if(checkstring.length() >= 45)
       {
            AnswerText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 10));
       }
       else if(checkstring.length() >= 29 && checkstring.length() < 45)
       {
           
           AnswerText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 12));
       }
       else
       {
           AnswerText.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));
       }
       
    }
    
    //steps to be taken if a correct answer is given, change scoreboard and Player information
    public void answerCorrect(char answer)
    {   
        
        
        
            
            final Runnable SmallThread = new Runnable()
            {
            
                  boolean keepgoing =true;
                  public void run()
                  {
                      while(keepgoing == true)
                      {
                           try 
                           {
                                char answer = QuestionAsked.getAnswer();
                                switch(answer)
                                {
                                    case 'A': answerFlashGreen(AnswerA_Label,A_AnswerText); break;
                                    case 'B': answerFlashGreen(AnswerB_Label,B_AnswerText);break;
                                    case 'C': answerFlashGreen(AnswerC_Label,C_AnswerText); break;
                                    case 'D': answerFlashGreen(AnswerD_Label,D_AnswerText); break;
                                }
                                 // also display the amount on lower bar
                                   
                                
                                if(music == true)
                                {
                                    
                                    if(Scoreboard.isNextQuestionMilestone()==true)
                                    {
                                        Background_music.close();
                                        Background_music.playProgress();
                                         Thread.sleep(6000);
                                    }
                                    else if(Scoreboard.isNextLastQuestion() == true)
                                    {
                                        if(music == true)
                                        {
                                            Background_music.close();
                                            Background_music.playWinMillion();
                                        }
                                    }
                                    else
                                    {
                                        //if(Scoreboard.getDifficulty() != 'e')
                                        //{
                                            Background_music.close();
                                       // }
                                                                              
                                        Background_music.playSound(Scoreboard.getDifficulty(), 3);
                                         
                                        
                                    }
                                   
                                    
                                   
                                }
                                 Thread.sleep(2000);
                                 Scoreboard.nextQuestion();
                               
                                if(Scoreboard.isLastQuestion()==true)// won a million
                                {
                                    Game.Player.changePlayerScore(Scoreboard.getScoreInt()); 
                                    Game.Player.changePlayerScoreReached(Scoreboard.getScoreInt()); 
                                    AmountBar.setText(Scoreboard.getScoreString());
                                    Answers_Panel.setVisible(false);
                                    Question_Panel.setVisible(false);
                                    LowerGame_Panel.setVisible(true);
                                   // if(music == true)
                                   // {
                                    //    Background_music.close();
                                    //    Background_music.playWinMillion();
                                    //}
                               
                                    Thread.sleep(16000);
                                    Game.endGame();
                                    keepgoing= false;
                                }
                                else
                                {
                                       
                                    Game.Player.changePlayerScore(Scoreboard.getScoreInt()); 
                                    Game.Player.changePlayerScoreReached(Scoreboard.getScoreInt()); // update Player score reached
                                    
                                    AmountBar.setText(Scoreboard.getScoreString());
                                    Answers_Panel.setVisible(false);
                                    Question_Panel.setVisible(false);
                                    LowerGame_Panel.setVisible(true);
                                   
                                    
                                    int sleep = 2000;
                                    if(Scoreboard.getDifficulty() == 'e')
                                    {
                                        sleep = 0;
                                    }
                                    else  if(Scoreboard.getDifficulty() == 'm')
                                    {
                                        sleep = 2000;
                                    }
                                    else  if(Scoreboard.getDifficulty() == 'h')
                                    {
                                        sleep = 3000;
                                    }
                                    else if(Scoreboard.isMilestone())
                                    {
                                        sleep =2000;
                                    }
                                    
                                    Thread.sleep(sleep);
                                    Answers_Panel.setVisible(true);
                                    Question_Panel.setVisible(true);
                                    LowerGame_Panel.setVisible(false);
                                    Question.setText("");
                                    resetAnswerPanel(A_AnswerText, AnswerA_Label);
                                    resetAnswerPanel(B_AnswerText, AnswerB_Label);
                                    resetAnswerPanel(C_AnswerText, AnswerC_Label);
                                    resetAnswerPanel(D_AnswerText, AnswerD_Label);
                                   
                                    if(music == true)
                                    {
                                         Background_music.close();
                                       if(Scoreboard.getDifficulty() != 'e')
                                       {
                                            
                                          
                                             Background_music.playSound(Scoreboard.getDifficulty(), 0);
                                            
                                        }
                                       else
                                       {
                                           Background_music.playSound(Scoreboard.getDifficulty(), 1);
                                       }
                                        
                                       
                                    }
                                    
                                    game_info = new boolean[] {false,false,false,false,false};
                                   keepgoing= false;
                                    
                            }
                        }
                        catch (InterruptedException e) 
                        {
                            System.out.println(e);
                        }
                  }
              }
            };
            new Thread(SmallThread).start();
           
            
        
     
    }
    
    // method to reset the answer panel
    public void resetAnswerPanel(JButton AnswerText, JLabel Answer_Label)
    {
         AnswerText.setVisible(true);
         AnswerText.setText("");
         AnswerText.setForeground(new Color(0xFFFFFF));
         AnswerText.setBackground(new Color(0x000000));
         AnswerText.setSelected(false);
         Answer_Label.setIcon(new ImageIcon(answericon));
         
        
    }
    
    //steps to be taken if wrong answer is given, find and display correct answer, change player info and end game
    public void answerWrong()
    {
        
       final Runnable SmallThread = new Runnable()
            {
            
                  boolean keepgoing =true;
                  public void run()
                  {
                      while(keepgoing == true)
                      {
                           try 
                           {
                               
                                
                                char answer = QuestionAsked.getAnswer();
        
                                switch(answer)
                                {
                                    case 'A': answerFlashGreen(AnswerA_Label,A_AnswerText); break;
                                    case 'B': answerFlashGreen(AnswerB_Label,B_AnswerText); break;
                                    case 'C': answerFlashGreen(AnswerC_Label,C_AnswerText); break;
                                    case 'D': answerFlashGreen(AnswerD_Label,D_AnswerText); break;
                                }
                                
                                if(music == true)
                                {
                                    Background_music.close();
                                    Background_music.playSound(Scoreboard.getDifficulty(), 4);
                                }
                                Thread.sleep(3000);
                                
                               
                                Game.Player.changePlayerScore(Scoreboard.workOutScore()); 
                                AmountBar.setText("LOSE");
                                
                                Answers_Panel.setVisible(false);
                                Question_Panel.setVisible(false);
                                LowerGame_Panel.setVisible(true);
                                
                                  
                                Thread.sleep(3000);
                                game_info = new boolean[] {false,false,false,false,false};
                                Game.endGame();
                                keepgoing= false;
                            }
                            catch (InterruptedException e) 
                            {}
                       }
                  }
              };
              new Thread(SmallThread).start();
        
        
            
            
        
    }
    
    //if an answer is seleced before all answers are shown, this method goes checks and displays all answers
    public void checkAllAnswersDisplayed()
    {
        if(game_info[0] == false)
        {
            showQuestion();
        }
          char[] listofletters = new char[] {'A','B','C','D'};
          for(int i = 1; i<game_info.length; i++)
          {
             if(game_info[i]==false) // that question is not shown yet
             {
                    displayAnswer(listofletters[i-1]);
             }
          }
    }
    
    
    
    // takes in a char array of two wrong answers and disables their content in the answers panel
    private void removeTwoWrongAnswers(char[] two_wrong_answers)
    {
            char value = two_wrong_answers[0];
            
            checkRemoveAnswers(value);
            value = two_wrong_answers[1];
            
             checkRemoveAnswers(value);
        
            
        
    }
    
    // method to help in removing two wrong answers
    private void checkRemoveAnswers(char val)
    {
        switch(val)
            {
                case 'A': A_AnswerText.setText("");
                            A_AnswerText.setVisible(false); break;
                case 'B': B_AnswerText.setText("");
                            B_AnswerText.setVisible(false); break;      
                case 'C': C_AnswerText.setText("");
                            C_AnswerText.setVisible(false); break;
                case 'D': D_AnswerText.setText("");
                            D_AnswerText.setVisible(false); break;
                default: break;
            }
    }
    
    
    //This method takes in a string and outputs a message box to screen with ok button
    public void okDialogueBox(String message)
    {
        JOptionPane.showMessageDialog(frame,message);
    }
    
    //This method takes in a string and outputs a dialogue message box with yes or no answer, returns a boolean of result
    public boolean yesNoDialogueBox(String message)
    {
        int n = JOptionPane.showConfirmDialog(frame, message,"Make a Selection",JOptionPane.YES_NO_OPTION);


		if(n== JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
		{
			return false;
		}

    }
    
    //This method takes in a string and outputs it as a error message box
    public void errorMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, message,"Error", JOptionPane.ERROR_MESSAGE);
    }
    
    //This method accesss a file to show version of the game and prints a dialogue to screen
    public void version()
    {
        try
        {
            BufferedReader Readfile = new BufferedReader(new FileReader("files/version.dat"));
            String version_number = Readfile.readLine();
            JOptionPane.showMessageDialog(frame, " Who Wants To... \n Version: "+version_number+" \n Author: Rodney Mullen \n 2007","About Who Wants To...",JOptionPane.INFORMATION_MESSAGE);
            Readfile.close();
        }
        catch(IOException ioe)
        {
            errorMessage("System File: Version.dat is not found");
        }
    }
    
    // method to change Playername on game screen
    public void changePlayerNamePanel()
    {
        
        playername.setText(Game.Player.getPlayerName());
        PlayerName_Panel.repaint();
    }
    
    
    
    
    
    
    
    
    
   
    public void actionPerformed(ActionEvent e)
	{
                if(e.getSource() == StartSingleGame_Button)
                {
                    
                    Game = new GamePlayer();
                    Game.setWhoWantsToInstance(this);
                    Game.startGameProtocol();
                    
                }
                else if(e.getSource() == StartMultiGame_Button)
                {
                    Game = new GameMultiPlayer();
                    Game.setWhoWantsToInstance(this);
                    Game.startGameProtocol();
                 }
                
                 else if(e.getSource() == QuitGame_Button)
                {
                    
                     boolean responce =yesNoDialogueBox("Are you sure you want to Quit");
                     
                    
                    if(responce == true)
                    {
                        if(music == true)
                        {
                            Background_music.close();
                            Background_music.playSystemClose();
                        
                     
                     
                            try
                            {
                                Thread.sleep(9000);
                            }
                            catch(InterruptedException ie)
                            {
                                System.err.println(ie);
                            }
                        }
                     
                     frame.dispose();
                    }
                 }
                     
                else if(e.getSource() == FiftyFiftyGame_Button)
                {
                    if(Question.getText().compareTo("") == 0)
                    {
                        okDialogueBox("A Question must be asked before 5050 can be used");
                    }
                    else
                    {
                        FiftyFiftyGame_Button.setSelected(true);
                        FiftyFiftyGame_Button.removeActionListener(this);
                        if(yesNoDialogueBox("Are you sure you want 50:50")==true)
                        {
                            removeTwoWrongAnswers(Questionsbase.get5050(QuestionAsked));
                            Game.ask5050();
                            FiftyFiftyGame_Button.setSelected(false);
                            FiftyFiftyGame_Button.setEnabled(false);
                            checkAllAnswersDisplayed();
                            if(music==true)
                            {
                                Background_music.close();
                                Background_music.play5050();
                                Background_music.playSound(Scoreboard.getDifficulty(),1);
                            }
                        }
                        else
                        {
                            FiftyFiftyGame_Button.setSelected(false);
                            FiftyFiftyGame_Button.addActionListener(this);
                        }
                    }
                    
                }
                else if(e.getSource() == AskAFriendGame_Button)
                {
                    if(Question.getText().compareTo("") == 0)
                    {
                        okDialogueBox("A Question must be asked before Ask a Friend can be used");
                    }
                    else
                    {
                        AskAFriendGame_Button.setSelected(true);
                        AskAFriendGame_Button.removeActionListener(this);
                        if(yesNoDialogueBox("Are you sure you want to ask a friend"))
                        {
                            checkAllAnswersDisplayed();
                            int time_limit = Questionsbase.getAskAFriendTimeLimit();
                            okDialogueBox("You now have "+time_limit+" seconds to choose and ask a friend");
                            Game.askAFriend(time_limit);
                           
                            AskAFriendGame_Button.setSelected(false);
                            AskAFriendGame_Button.setEnabled(false);
                        
                        }
                        else
                        {
                             AskAFriendGame_Button.setSelected(false);
                             AskAFriendGame_Button.addActionListener(this);
                        }
                    }
                   
                }
                else if(e.getSource() == AskTheAudienceGame_Button)
                {
                    if(Question.getText().compareTo("") == 0)
                    {
                        okDialogueBox("A Question must be asked before Ask The Audience can be used");
                    }
                    else
                    {
                        AskTheAudienceGame_Button.setSelected(true);
                        AskTheAudienceGame_Button.removeActionListener(this);
                        if(yesNoDialogueBox("Are you sure you want to ask the Audience"))
                        {
                            checkAllAnswersDisplayed();
                            Game.askTheAudience();
                            
                            AskTheAudienceGame_Button.setSelected(false);
                            AskTheAudienceGame_Button.setEnabled(false);
                       
                        }
                        else
                        {
                            AskTheAudienceGame_Button.setSelected(false);
                            AskTheAudienceGame_Button.addActionListener(this);
                        }
                    }
                   
                }
                else if(e.getSource() == WithdrawGame_Button)
                {
                    
                    WithdrawGame_Button.setSelected(true);
                    WithdrawGame_Button.removeActionListener(this);
                     if(yesNoDialogueBox("Are you sure you want to Withdraw") == true)
                    {
                         final Runnable SmallThread = new Runnable()
                        {
            
                                
                                public void run()
                                {
                                    
                                        try 
                                        {
                                            if(game_info[0] == false)
                                            {
                                                showQuestion();
                                            }
                                            checkAllAnswersDisplayed();
                                            char answer = QuestionAsked.getAnswer();
                                            switch(answer)
                                            {
                                                case 'A': answerFlashGreen(AnswerA_Label,A_AnswerText); break;
                                                case 'B': answerFlashGreen(AnswerB_Label,B_AnswerText);break;
                                                case 'C': answerFlashGreen(AnswerC_Label,C_AnswerText); break;
                                                case 'D': answerFlashGreen(AnswerD_Label,D_AnswerText); break;
                                            }
                                            Thread.sleep(2000);
                                            Game.endGame();
                                        }
                                        catch(InterruptedException ie)
                                        {
                                            System.err.println(ie);
                                        }                                       
                                }
                         };
                         new Thread(SmallThread).start();
                    }
                    else
                    {
                         WithdrawGame_Button.setSelected(false);
                         WithdrawGame_Button.addActionListener(this);
                    }
                    
                }
                else if(e.getSource() == PlayGame_Button) // used to advance the game
                {
                    
                    char[] listofletters = new char[] {'A','B','C','D'};
                    if(game_info[0] == true) // question is on display
                    {
                        for(int i = 1; i<game_info.length; i++)
                        {
                            if(game_info[i]==false) // that question is not shown yet
                            {
                                displayAnswer(listofletters[i-1]);
                                break;
                            }
                        }
                            
                        
                    }
                    else // question has not appeared yet
                    {
                       
                        if(Scoreboard.getDifficulty() == 'e')
                        {
                            if(Scoreboard.getScoreInt() == 0) // before any questions asked
                            {
                                if(music==true)
                                {   
                                    
                                    Background_music.close();
                                    Background_music.playSound(Scoreboard.getDifficulty(),0);
                                
                                    try
                                    {
                                        Thread.sleep(3000);
                                    
                                        Background_music.close();
                                        Background_music.playSound(Scoreboard.getDifficulty(),1);
                                    
                                    }
                                    catch(InterruptedException ie)
                                    {
                                        System.err.println(ie);
                                    }
                                }
                                
                            }
                           
                        }
                        else
                        {
                            if(music==true)
                            {
                                Background_music.close();
                               
                                Background_music.playSound(Scoreboard.getDifficulty(),1);
                            }
                        }
                        
                        showQuestion();
                    }
                }
                
                else if(e.getSource() == A_AnswerText)
                {
                   checkAllAnswersDisplayed();
                  
                   int sleep=1000;
                   if(Scoreboard.getDifficulty() == 'e')
                   {
                        sleep = 1000;
                   }
                   else if(Scoreboard.getDifficulty() == 'm') // medium questtion
                   {
                       sleep = 7000;
                   }
                   else // hard question
                   {
                       sleep = 10000;
                   }
                   final int finalsleep = sleep;
                  
                   AnswerA_Label.setIcon(new ImageIcon(answerselectedicon));
                   A_AnswerText.setForeground(new Color(0x000000));
                   A_AnswerText.setSelected(true);
                   AnswerA_Label.repaint();
                   if(music==true)
                   {
                       if(Scoreboard.getDifficulty() != 'e')
                       {
                           Background_music.close();
                           Background_music.playSound(Scoreboard.getDifficulty(),2);
                       }
                        
                   }
                   
                   final Runnable SmallThread = new Runnable()
                    {
            
                        boolean keepgoing =true;
                        public void run()
                        {
                            while(keepgoing == true)
                           {
                                try 
                                {
            
                                    Thread.sleep(finalsleep);
                                    if(QuestionAsked.isAnswer('A')==true) // a is the correct answer
                                    {
                                        answerCorrect('A');
                                    }
                                    else
                                    {
                                        answerWrong();
                                    }
                                    keepgoing= false;
                           
                                }
                                catch (InterruptedException e) 
                                {}
                            }
                        }
                    };
                    new Thread(SmallThread).start();
                
                                       
                   
                   
                   
                  
                }
                 else if(e.getSource() == B_AnswerText)
                {
                   checkAllAnswersDisplayed();
                   int sleep=1000;
                   if(Scoreboard.getDifficulty() == 'e')
                   {
                        sleep = 1000;
                   }
                   else if(Scoreboard.getDifficulty() == 'm') // medium questtion
                   {
                       sleep = 7000;
                   }
                   else // hard question
                   {
                       sleep = 10000;
                   }
                   AnswerB_Label.setIcon(new ImageIcon(answerselectedicon));
                   B_AnswerText.setForeground(new Color(0x000000));
                   B_AnswerText.setSelected(true);
                   AnswerB_Label.repaint();
                   if(music==true)
                   {
                        if(Scoreboard.getDifficulty() != 'e')
                       {
                           Background_music.close();
                           Background_music.playSound(Scoreboard.getDifficulty(),2);
                       }
                   }
                   final int finalsleep = sleep;
                  final Runnable SmallThread = new Runnable()
                    {
            
                        boolean keepgoing =true;
                        public void run()
                        {
                            while(keepgoing == true)
                            {
                                try 
                                {
            
                                    Thread.sleep(finalsleep);
                                    if(QuestionAsked.isAnswer('B')==true) // a is the correct answer
                                    {
                                        answerCorrect('B');
                                    }
                                    else
                                    {
                                        answerWrong();
                                    }
                                    keepgoing= false;
                           
                                }
                                catch (InterruptedException e) 
                                {}
                            }
                        }
                    };
                    new Thread(SmallThread).start();
                   
                   
                   
                }
                 else if(e.getSource() == C_AnswerText)
                {
                   checkAllAnswersDisplayed();
                   int sleep=1000;
                   if(Scoreboard.getDifficulty() == 'e')
                   {
                        sleep = 1000;
                   }
                   else if(Scoreboard.getDifficulty() == 'm') // medium questtion
                   {
                       sleep = 7000;
                   }
                   else // hard question
                   {
                       sleep = 10000;
                   }
                  AnswerC_Label.setIcon(new ImageIcon(answerselectedicon));
                   C_AnswerText.setForeground(new Color(0x000000));
                   C_AnswerText.setSelected(true);
                   AnswerC_Label.repaint();
                   if(music==true)
                   {
                        if(Scoreboard.getDifficulty() != 'e')
                       {
                           Background_music.close();
                           Background_music.playSound(Scoreboard.getDifficulty(),2);
                       }
                   }
                   final int finalsleep = sleep;
                  final Runnable SmallThread = new Runnable()
                    {
            
                        boolean keepgoing =true;
                        public void run()
                        {
                           while(keepgoing == true)
                            {
                                try 
                                {
            
                                    Thread.sleep(finalsleep);
                                    if(QuestionAsked.isAnswer('C')==true) // c is the correct answer
                                    {
                                        answerCorrect('C');
                                    }
                                    else
                                    {
                                        answerWrong();
                                    }
                                    keepgoing= false;
                           
                                }
                                catch (InterruptedException e) 
                                {}
                           }
                        }
                    };
                     new Thread(SmallThread).start();
                   
                }
                 else if(e.getSource() == D_AnswerText)
                {
                  checkAllAnswersDisplayed();
                   int sleep=1000;
                   if(Scoreboard.getDifficulty() == 'e')
                   {
                        sleep = 1000;
                   }
                   else if(Scoreboard.getDifficulty() == 'm') // medium questtion
                   {
                       sleep = 7000;
                   }
                   else // hard question
                   {
                       sleep = 10000;
                   }
                   AnswerD_Label.setIcon(new ImageIcon(answerselectedicon));
                   D_AnswerText.setForeground(new Color(0x000000));
                   D_AnswerText.setSelected(true);
                   AnswerD_Label.repaint();
                   if(music==true)
                   {
                        if(Scoreboard.getDifficulty() != 'e')
                       {
                           Background_music.close();
                           Background_music.playSound(Scoreboard.getDifficulty(),2);
                       }
                   }
                    final int finalsleep = sleep;
                  final Runnable SmallThread = new Runnable()
                    {
            
                        boolean keepgoing =true;
                        public void run()
                        {
                            while(keepgoing == true)
                           {
                                try 
                                {
            
                                    Thread.sleep(finalsleep);
                                    if(QuestionAsked.isAnswer('D')==true) // a is the correct answer
                                    {
                                        answerCorrect('D');
                                    }
                                    else
                                    {
                                        answerWrong();
                                    }
                                    keepgoing= false;
                           
                                }
                                catch (InterruptedException e) 
                                {}
                            }
                        }
                    };
                     new Thread(SmallThread).start();
                   
                   
                }
                 else if(e.getSource() == Play_Button)
                {
                    
                   
                        Name_stack.addElement(NameEntry.getText().trim());
                    
                        Game.startGame(Name_stack);
                   
                        
                        
                    
                }
                else if(e.getSource() == AddPlayer_Button)
                {
                    if(NameEntry.getText().compareTo("") ==0)
                    {
                        okDialogueBox("Player name cannot be blank");
                        
                    }
                    else
                    {
                        Name_stack.addElement(NameEntry.getText().trim());
                        EnterNameText.setText(" Player: "+NameEntry.getText().trim()+" added, enter new player name");
                       NameEntry.setText("");
                    }
                        
                        
                    
                }
                
                 else if(e.getSource() == GoToLeaderboard_button)
                {
                    showLeaderBoard();
                 }
                
                 else if(e.getSource() == Cancel_Button)
                {
                   setStartScreen();
                }
                 else if(e.getSource() == MainMenu_Button)
                {
                   frame.remove(Main_Panel);
                   Game = null;
                   setStartScreen();
                   
                }
                 else if(e.getSource() == PlayAgain_Button)
                {
                         Game.startGameProtocol();
                     
                         
                    
                   
                }
                // the following allows the different operations in the menus.
                 else
                {
                    JMenuItem source = (JMenuItem)(e.getSource()); 
                    String actionperformed= source.getText();
                    if(actionperformed.compareTo(OnePlayer.getText())==0)
                    {
                        Game = new GamePlayer();
                        Game.setWhoWantsToInstance(this);
                        Game.startGameProtocol();
                    }
                    else if(actionperformed.compareTo(MultiPlayer.getText())==0)
		    {      
                        Game = new GameMultiPlayer();
                        Game.setWhoWantsToInstance(this);
                        Game.startGameProtocol(); 
                    }
                    else if(actionperformed.compareTo(RestoreQuestions.getText())==0)
		    {
                        if(Questionsbase !=null)
                        {
                             Questionsbase.clearAllQuestionArrays();
                             okDialogueBox("All questions restored");
                        }
                        else
                        {
                            okDialogueBox("Game must be started to restore questions");
                        }
                       
                        
                    }
                    
                    else if(actionperformed.compareTo(SkipQuestion.getText())==0)
		    {
                        if(QuestionAsked != null)
                        {
                            Question.setText("");
                            resetAnswerPanel(A_AnswerText, AnswerA_Label);
                            resetAnswerPanel(B_AnswerText, AnswerB_Label);
                            resetAnswerPanel(C_AnswerText, AnswerC_Label);
                            resetAnswerPanel(D_AnswerText, AnswerD_Label);
                            game_info = new boolean[] {false,false,false,false,false};
                            okDialogueBox("Question Skipped");
                            if(music==true)
                            {
                                if(Scoreboard.getDifficulty() == 'e')
                                {
                                    Background_music.close();
                                    Background_music.playSound(Scoreboard.getDifficulty(),1);
                                }
                                else
                                {
                                    Background_music.close();
                                    Background_music.playSound(Scoreboard.getDifficulty(),0);
                                }
                            }
                        }
                        else
                        {
                             okDialogueBox("Game must be started to skip a questions");
                        }
                       
                    }
                    
                    else if(actionperformed.compareTo(EditMultiPlayers.getText())==0)
		    {
                        
                        if(Game != null)
                        {
                            Game.editMultiPlayerNames();
                            
                        }
                        else
                        {
                            okDialogueBox("Game must be started to edit Multi-player names");
                        }
                        
                    }
                    
                    else if(actionperformed.compareTo(EditPlayerName.getText())==0)
		    {
                        if(Game != null)
                        {
                            Game.editPlayerName();
                            
                        }
                        else
                        {
                            okDialogueBox("Game must be started to edit player name");
                        }
                        
                        
                    }
                    
                    else if(actionperformed.compareTo(MusicOn.getText())==0)
		    {
                        MusicSetting.remove(MusicOn);
                        MusicSetting.add(MusicOff);
                        music = true;
                       
                    }
                    
                    else if(actionperformed.compareTo(MusicOff.getText())==0)
		    {
                        MusicSetting.remove(MusicOff);
                        MusicSetting.add(MusicOn);
                        music = false;
                        Background_music.close();
                    }
                    
                    else if(actionperformed.compareTo(Ask_A_Friend_Time_Limit.getText())==0)
		    {
                        DisplayGUI = new DisplayGUI();
                        DisplayGUI.editAskAFriendTimeLimit(Questionsbase);
                        
                    }
                    
                    else if(actionperformed.compareTo(ViewPlayerBoard.getText())==0)
		    {
                        DisplayGUI = new DisplayGUI();
                        DisplayGUI.showLeaderBoard(Playerboard);
                        
                    }
                    
                     else if(actionperformed.compareTo(HelpScreen.getText())==0)
		    {
                        //need to implement
                        
                    }
                    
                    
                    else if(actionperformed.compareTo(Version.getText())==0)
		    {
                        version();
                        
                    }
                 }
                
                
    }
    public static void createAndShowGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true); 

		try
		{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println("Error in main GUI");
		}
		
		JComponent newContentPane = new WhoWantsTo();
		newContentPane.setOpaque(true);
		
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{ 
			public void run() 
			{ 
				createAndShowGUI(); 
			} 
		}); 
    }
    
}
