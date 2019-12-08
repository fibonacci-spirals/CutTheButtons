import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.URL;
class ImageImplement extends JPanel {

	  private Image img;


	  public ImageImplement(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

	}

class GameBoard extends JFrame implements ActionListener{
			CardLayout card;
			Container c;
			JPanel GamePanel=new JPanel();
			JPanel title=new JPanel();
			JPanel level=new JPanel();
			JPanel refree=new JPanel();
			JPanel Next= new JPanel();
			JPanel Restart=new JPanel();
			JPanel YouWon=new JPanel();
			JPanel StartGame=new JPanel();
			JPanel ImagePanel=new JPanel();
			JPanel ScorePanel=new JPanel();
			JPanel imgPanel=new JPanel();
			JPanel startpanel= new JPanel();
			JButton board[][]=new JButton[5][5];
			JButton restart;
			JButton next;
			JLabel Refree,Title,Score;
			JSplitPane GBoard,strt,tlevel,buttons,actions,end;
			char colour[][]=new char[5][5];
			int buttoncount,levelnum,pi,pj,ni,nj,score;
			int clicks=0,tscore=0;
			
			//Button icons
			ImageIcon redbutton,bluebutton,whitebutton,yellowbutton,pinkbutton,orangebutton,greenbutton; 
			GameBoard(){
					Dimension d=new Dimension(500,500);
					setPreferredSize(d);
					card=new CardLayout(70,60);
					c=getContentPane();
					c.setLayout(card);
					startPanelInit();
					c.add("StartPanel",StartGame);
					gamePanelInit();
					c.add("Game Panel",GamePanel);
					endPanelInit();
					c.add("ENd Panel",YouWon);
			}
			
			void endPanelInit(){
				end=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				Score=new JLabel();
				ImagePanel.setLayout(new BorderLayout());
				ImageImplement panel = new ImageImplement(new ImageIcon("youwon.jpg").getImage());
				ImagePanel.add(panel,BorderLayout.CENTER);
				ScorePanel.add(Score, BorderLayout.PAGE_END);
				end.setLeftComponent(ImagePanel);
				end.setRightComponent(ScorePanel);
				YouWon.add(end);
				}
			
			void startPanelInit(){
				strt= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				imgPanel.setLayout(new BorderLayout());
				ImageImplement panel = new ImageImplement(new ImageIcon("Titlepage.png").getImage());
				imgPanel.add(panel,BorderLayout.PAGE_END);
				JButton startgame=new JButton("Start Game!");
				startgame.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e1){
						card.next(c);
						
					}
				});
				startpanel.add(startgame,BorderLayout.PAGE_END);
				strt.setLeftComponent(imgPanel);
				strt.setRightComponent(startpanel);
				StartGame.add(strt);
				
				
				
			}
			void gamePanelInit(){
				
				levelnum=0;
				redbutton=new ImageIcon("RedButton.png"); 
				pinkbutton=new ImageIcon("PinkButton.png"); 
				bluebutton=new ImageIcon("BlueButton.png"); 
				greenbutton=new ImageIcon("GreenButton.png"); 
				whitebutton=new ImageIcon("WhiteButton.png"); 
				yellowbutton=new ImageIcon("YellowButton.png");
				orangebutton=new ImageIcon("OrangeButton.png"); 		
				Title=new JLabel("CUT THE BUTTONS!");
				 GBoard = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
				 tlevel= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				 buttons=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				 actions=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				 level.setLayout(new GridLayout(5,5,5,5));
					int i,j,basex=50, basey=50,bsize=15;
					
					for(i=0;i<5;i++){
						for(j=0;j<5;j++){
							colour[i][j]='x';
							board[i][j]=new JButton();
							board[i][j].addActionListener(this);
							board[i][j].setActionCommand(String.valueOf(i)+String.valueOf(j));
							board[i][j].setPreferredSize(new Dimension(55, 55));
							level.add(board[i][j]);
							board[i][j].setVisible(false);
						}
					}
					setTutorial();
					
					title.add(Title);
					Refree=new JLabel("Start Game!");
					next=new JButton("Next Level");
					restart=new JButton("Restart");
					restart.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ev){
							switch(levelnum){
								case 0: setTutorial();
												break;
								case 1: initLevel1();
												break;
								case 2: initLevel2();
												break;
								case 3: initLevel3();
												break;
								case 4: initLevel4();
												break;
								case 5: initLevel5();
												break;
								
							}
						}
					});
					next.setEnabled(false);
					next.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e){ /*Code goes here*/
						levelnum++;
						clicks=0;
							switch(levelnum){
								case 1: initLevel1();
											
												break;
								case 2: initLevel2();
											
											break;
								case 3: initLevel3();
											break;
								case 4: initLevel4();
											break;
								case 5: initLevel5();
											break;
								case 6: Score.setText("Total Score="+tscore);
										card.next(c);
										playSound();														
						}
						System.out.println(level);
						}
					});
					
					
					tlevel.setLeftComponent(title);
					tlevel.setRightComponent(level);
					refree.add(Refree);
					Next.add(next);
					Restart.add(restart);
					actions.setLeftComponent(Restart);
					actions.setRightComponent(Next);
					buttons.setLeftComponent(refree);
					buttons.setRightComponent(actions);
					GBoard.setLeftComponent(tlevel);
					GBoard.setRightComponent(buttons);
					
					GamePanel.add(GBoard,BorderLayout.CENTER);
					
					setSize(500,500);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setVisible(true);
					
			}
			
			void setTutorial(){
					levelnum=0;
					board[0][1].setIcon(whitebutton);
					colour[0][1]='w';
					board[0][1].setVisible(true);
					board[0][3].setIcon(yellowbutton);
					colour[0][3]='y';
					board[0][3].setVisible(true);
					board[1][1].setIcon(bluebutton);
					colour[1][1]='b';
					board[1][1].setVisible(true);
					board[1][2].setIcon(yellowbutton);
					colour[1][2]='y';
					board[1][2].setVisible(true);
					board[2][1].setIcon(bluebutton);
					colour[2][1]='b';
					board[2][1].setVisible(true);
					board[4][1].setIcon(bluebutton);
					colour[4][1]='b';
					board[4][1].setVisible(true);
					board[3][0].setIcon(yellowbutton);
					colour[3][0]='y';
					board[3][0].setVisible(true);
					board[2][3].setIcon(whitebutton);
					colour[2][3]='w';
					board[2][3].setVisible(true);
					board[2][2].setIcon(redbutton);
					colour[2][2]='r';
					board[2][2].setVisible(true);
					board[2][4].setIcon(redbutton);
					colour[2][4]='r';
					board[2][4].setVisible(true);
					buttoncount=10;
			}
			
			void initLevel1(){
				Refree.setText("Start Game!");
				next.setEnabled(false);
				for(int i=0;i<5;i++)
						for(int j=0;j<5;j++)
							board[i][j].setVisible(true);	
					board[0][0].setIcon(redbutton);
				colour[0][0]='r';
				board[0][1].setIcon(redbutton);
				colour[0][1]='r';
				board[0][2].setIcon(pinkbutton);
				colour[0][2]='p';
				board[0][3].setIcon(whitebutton);
				colour[0][3]='w';
				board[0][4].setIcon(whitebutton);
				colour[0][4]='w';
				board[1][0].setIcon(yellowbutton);
				colour[1][0]='y';
				board[1][1].setIcon(redbutton);
				colour[1][1]='r';
				board[1][2].setIcon(pinkbutton);
				colour[1][2]='p';
				board[1][3].setIcon(pinkbutton);
				colour[1][3]='p';
				board[1][4].setIcon(bluebutton);
				colour[1][4]='b';
				board[2][0].setIcon(yellowbutton);
				colour[2][0]='y';
				board[2][1].setIcon(whitebutton);
				colour[2][1]='w';
				board[2][2].setIcon(whitebutton);
				colour[2][2]='w';
				board[2][3].setIcon(bluebutton);
				colour[2][3]='b';
				board[2][4].setIcon(bluebutton);
				colour[2][4]='b';
				board[3][0].setIcon(yellowbutton);
				colour[3][0]='y';
				board[3][1].setIcon(redbutton);
				colour[3][1]='r';
				board[3][2].setIcon(yellowbutton);
				colour[3][2]='y';
				board[3][3].setIcon(pinkbutton);
				colour[3][3]='p';
				board[3][4].setIcon(bluebutton);
				colour[3][4]='b';
				board[4][0].setIcon(whitebutton);
				colour[4][0]='w';
				board[4][1].setIcon(bluebutton);
				colour[4][1]='b';
				board[4][2].setIcon(whitebutton);
				colour[4][2]='w';
				board[4][3].setIcon(whitebutton);
				colour[4][3]='w';
				board[4][4].setIcon(bluebutton);
				colour[4][4]='b';
				buttoncount=25;
			}
			void initLevel2(){
				Refree.setText("Start Game!");
				next.setEnabled(false);
				for(int i=0;i<5;i++)
						for(int j=0;j<5;j++)
							board[i][j].setVisible(true);	
				board[0][0].setIcon(redbutton);
				colour[0][0]='r';
				board[0][1].setIcon(greenbutton);
				colour[0][1]='g';
				board[0][2].setIcon(whitebutton);
				colour[0][2]='w';
				board[0][3].setIcon(whitebutton);
				colour[0][3]='w';
				board[0][4].setIcon(whitebutton);
				colour[0][4]='w';
				board[1][0].setIcon(pinkbutton);
				colour[1][0]='p';
				board[1][1].setIcon(redbutton);
				colour[1][1]='r';
				board[1][2].setIcon(bluebutton);
				colour[1][2]='b';
				board[1][3].setIcon(yellowbutton);
				colour[1][3]='y';
				board[1][4].setIcon(bluebutton);
				colour[1][4]='b';
				board[2][0].setIcon(pinkbutton);
				colour[2][0]='p';
				board[2][1].setIcon(greenbutton);
				colour[2][1]='g';
				board[2][2].setIcon(whitebutton);
				colour[2][2]='w';
				board[2][3].setIcon(redbutton);
				colour[2][3]='r';
				board[2][4].setIcon(bluebutton);
				colour[2][4]='b';
				board[3][0].setIcon(pinkbutton);
				colour[3][0]='p';
				board[3][1].setIcon(greenbutton);
				colour[3][1]='g';
				board[3][2].setIcon(pinkbutton);
				colour[3][2]='p';
				board[3][3].setIcon(yellowbutton);
				colour[3][3]='y';
				board[3][4].setIcon(bluebutton);
				colour[3][4]='b';
				board[4][0].setIcon(pinkbutton);
				colour[4][0]='p';
				board[4][1].setIcon(redbutton);
				colour[4][1]='r';
				board[4][2].setIcon(whitebutton);
				colour[4][2]='w';
				board[4][3].setIcon(yellowbutton);
				colour[4][3]='y';
				board[4][4].setIcon(bluebutton);
				colour[4][4]='b';
				buttoncount=25;
			}	
			void initLevel3(){
				
				Refree.setText("Start Game!");
				next.setEnabled(false);
				for(int i=0;i<5;i++)
						for(int j=0;j<5;j++)
							board[i][j].setVisible(true);	
				board[0][0].setIcon(whitebutton);
				colour[0][0]='w';
				board[0][1].setIcon(orangebutton);
				colour[0][1]='o';
				board[0][2].setIcon(yellowbutton);
				colour[0][2]='y';
				board[0][3].setIcon(greenbutton);
				colour[0][3]='g';
				board[0][4].setIcon(yellowbutton);
				colour[0][4]='y';
				board[1][0].setIcon(pinkbutton);
				colour[1][0]='p';
				board[1][1].setIcon(whitebutton);
				colour[1][1]='w';
				board[1][2].setIcon(pinkbutton);
				colour[1][2]='p';
				board[1][3].setIcon(whitebutton);
				colour[1][3]='w';
				board[1][4].setIcon(greenbutton);
				colour[1][4]='g';
				board[2][0].setIcon(greenbutton);
				colour[2][0]='g';
				board[2][1].setIcon(pinkbutton);
				colour[2][1]='p';
				board[2][2].setIcon(greenbutton);
				colour[2][2]='g';
				board[2][3].setIcon(orangebutton);
				colour[2][3]='o';
				board[2][4].setIcon(greenbutton);
				colour[2][4]='g';
				board[3][0].setIcon(orangebutton);
				colour[3][0]='o';
				board[3][1].setIcon(yellowbutton);
				colour[3][1]='y';
				board[3][2].setIcon(yellowbutton);
				colour[3][2]='y';
				board[3][3].setIcon(whitebutton);
				colour[3][3]='w';
				board[3][4].setIcon(yellowbutton);
				colour[3][4]='y';
				board[4][0].setIcon(orangebutton);
				colour[4][0]='o';
				board[4][1].setIcon(pinkbutton);
				colour[4][1]='p';
				board[4][2].setIcon(orangebutton);
				colour[4][2]='o';
				board[4][3].setIcon(orangebutton);
				colour[4][3]='o';
				board[4][4].setIcon(orangebutton);
				colour[4][4]='o';
				buttoncount=25;
			}	
			
			void initLevel4(){
				
				Refree.setText("Start Game!");
				next.setEnabled(false);
				for(int i=0;i<5;i++)
						for(int j=0;j<5;j++)
							board[i][j].setVisible(true);	
				board[0][0].setIcon(pinkbutton);
				colour[0][0]='p';
				board[0][1].setIcon(greenbutton);
				colour[0][1]='g';
				board[0][2].setIcon(bluebutton);
				colour[0][2]='b';
				board[0][3].setIcon(pinkbutton);
				colour[0][3]='p';
				board[0][4].setIcon(pinkbutton);
				colour[0][4]='p';
				board[1][0].setIcon(yellowbutton);
				colour[1][0]='y';
				board[1][1].setIcon(pinkbutton);
				colour[1][1]='p';
				board[1][2].setIcon(greenbutton);
				colour[1][2]='g';
				board[1][3].setIcon(redbutton);
				colour[1][3]='r';
				board[1][4].setIcon(greenbutton);
				colour[1][4]='g';
				board[2][0].setIcon(redbutton);
				colour[2][0]='r';
				board[2][1].setIcon(greenbutton);
				colour[2][1]='g';
				board[2][2].setIcon(bluebutton);
				colour[2][2]='b';
				board[2][3].setIcon(pinkbutton);
				colour[2][3]='p';
				board[2][4].setIcon(bluebutton);
				colour[2][4]='b';
				board[3][0].setIcon(redbutton);
				colour[3][0]='r';
				board[3][1].setIcon(greenbutton);
				colour[3][1]='g';
				board[3][2].setIcon(yellowbutton);
				colour[3][2]='y';
				board[3][3].setIcon(bluebutton);
				colour[3][3]='b';
				board[3][4].setIcon(yellowbutton);
				colour[3][4]='y';
				board[4][0].setIcon(redbutton);
				colour[4][0]='r';
				board[4][1].setIcon(pinkbutton);
				colour[4][1]='p';
				board[4][2].setIcon(bluebutton);
				colour[4][2]='b';
				board[4][3].setIcon(redbutton);
				colour[4][3]='r';
				board[4][4].setIcon(yellowbutton);
				colour[4][4]='y';
				buttoncount=25;
			}
			void initLevel5(){
				
				Refree.setText("Start Game!");
				next.setEnabled(false);
				for(int i=0;i<5;i++)
						for(int j=0;j<5;j++)
							board[i][j].setVisible(true);	
				board[0][0].setIcon(bluebutton);
				colour[0][0]='b';
				board[0][1].setIcon(bluebutton);
				colour[0][1]='b';
				board[0][2].setIcon(bluebutton);
				colour[0][2]='b';
				board[0][3].setIcon(pinkbutton);
				colour[0][3]='p';
				board[0][4].setIcon(greenbutton);
				colour[0][4]='g';
				board[1][0].setIcon(orangebutton);
				colour[1][0]='o';
				board[1][1].setIcon(orangebutton);
				colour[1][1]='o';
				board[1][2].setIcon(bluebutton);
				colour[1][2]='b';
				board[1][3].setIcon(orangebutton);
				colour[1][3]='o';
				board[1][4].setIcon(yellowbutton);
				colour[1][4]='y';
				board[2][0].setIcon(pinkbutton);
				colour[2][0]='p';
				board[2][1].setIcon(pinkbutton);
				colour[2][1]='p';
				board[2][2].setIcon(greenbutton);
				colour[2][2]='g';
				board[2][3].setIcon(pinkbutton);
				colour[2][3]='p';
				board[2][4].setIcon(redbutton);
				colour[2][4]='r';
				board[3][0].setIcon(bluebutton);
				colour[3][0]='b';
				board[3][1].setIcon(redbutton);
				colour[3][1]='r';
				board[3][2].setIcon(yellowbutton);
				colour[3][2]='y';
				board[3][3].setIcon(redbutton);
				colour[3][3]='r';
				board[3][4].setIcon(redbutton);
				colour[3][4]='r';
				board[4][0].setIcon(greenbutton);
				colour[4][0]='g';
				board[4][1].setIcon(yellowbutton);
				colour[4][1]='y';
				board[4][2].setIcon(orangebutton);
				colour[4][2]='o';
				board[4][3].setIcon(orangebutton);
				colour[4][3]='o';
				board[4][4].setIcon(redbutton);
				colour[4][4]='r';
				buttoncount=25;
			}
				public static void main(String [] args){
					GameBoard game=new GameBoard();
					
				}
					
			int validate(int pi,int pj,int ni,int nj){
				if(pi==ni&&pj==nj)//samebutton
					return -1;
				if(pi-ni==pj-nj)//leading diagonal
					return 3;
				else if(pi==ni)//same row
					return 1;
				else if(pj==nj)//same column
					return 2;
				else if(pi+pj==ni+nj)//trailing diagonal
					return 4;
				return -1;
			}
			public void actionPerformed(ActionEvent evnt){
				clicks++;
				int l=-1,h=-1,li=-1,i,j;
				if(clicks%2==0){
					int buttonid=Integer.parseInt(evnt.getActionCommand());
					boolean yes=false;
					ni=buttonid/10;
					nj=buttonid%10;
					int valid=validate(pi,pj,ni,nj);
					System.out.println("pi= "+pi+"pj= "+pj+"ni= "+ni+"nj= "+nj+"valid= "+valid);
					if (valid<0){
						yes=false;
						//Refree.setText("Button "+evnt.getActionCommand()+ " Clicked! ");
					}
					else if(valid==1){//COLOUR VALIDATE
						//Same row
						if(pj<nj){
								l=pj;
								h=nj;
						}
						else	{
								l=nj;
								h=pj;
						}
						li=pi;
						for(i=l;i<h;i++)
							if(colour[pi][i]!=colour[pi][i+1])
								if(colour[pi][i]!='x'&&colour[pi][i+1]!='x'){
									yes=false;
									break;
								}
						if(i==h)
							yes=true;
						
						
					}
					else if(valid==2){
						if(pi<ni){//same column
								l=pi;
								h=ni;
						}
						else	{
								l=ni;
								h=pi;
						}
						li=pj;
						for(i=l;i<h;i++)
							if(colour[i][pj]!=colour[i+1][pj])
								if(colour[i][pj]!='x'&&colour[i+1][pj]!='x'){
								yes=false;
								break;
							}
						if(i==h)
							yes=true;
								
					}
					else if(valid==3){
						
						if(pi<ni){
								l=pi;
								h=ni;
								li=pj;
						}
						else	{
								l=ni;
								h=pi;
								li=nj;
						}
						for(i=l,j=li;i<h;i++,j++){//leadingdiagonal
							if(colour[i][j]!=colour[i+1][j+1])
								if(colour[i][j]!='x'&&colour[i+1][j+1]!='x'){
								yes=false;
								break;
							}
						}
						if(i==h)
							yes=true;
					}
					else if(valid==4){
						
						if(pi<ni){//trailing diagonal
							l=pi;
							h=ni;
							li=pj;
							
						}
						else{
							l=ni;
							h=pi;
							li=nj;
							
						}
						for(i=l,j=li;i<h;i++,j--){
							if(colour[i][j]!=colour[i+1][j-1])
								if(colour[i][j]!='x'&&colour[i+1][j-1]!='x'){
									yes=false;
									break;
							}
						}
						if(i==h)
							yes=true;
			
					}
					if(yes){
						Refree.setText("Yaay!");
						System.out.println("l= "+l+"h= "+h+"li= "+li+"valid= "+valid);
						removeButtons(l,h,li,valid);
					}
					else
						Refree.setText("Uh oh, wrong move!");
					}
			else{
				int buttonid=Integer.parseInt(evnt.getActionCommand());
				pi=buttonid/10;
				pj=buttonid%10;
				Refree.setText("Click another button!");
			}
		}
		void removeButtons(int l, int h, int li, int valid){
			int i,j;
			
			if (valid==1){//same row
				for(i=l;i<=h;i++){
					
					if(colour[li][i]!='x'){
						buttoncount--;		
						colour[li][i]='x';
						board[li][i].setVisible(false);
					}
					
				}
			}
			if(valid==2){//same column
				for(i=l;i<=h;i++){
					
					if(colour[i][li]!='x'){
						colour[i][li]='x';
						buttoncount--;
						board[i][li].setVisible(false);
					}
				}
			}
			if(valid==3){//Leading Diagonals
				for(i=l,j=li;i<=h;i++,j++){
					
					if(colour[i][j]!='x'){
						colour[i][j]='x';
						buttoncount--;
						board[i][j].setVisible(false);
					}
				}
			}
			if(valid==4){//Trailing Diagonals
				for(i=l,j=li;i<=h;i++,j--){
					
					if(colour[i][j]!='x'){
						colour[i][j]='x';
						buttoncount--;
						board[i][j].setVisible(false);
					}
				}
			}
			System.out.println("ButtonCount="+buttoncount);
			if(buttoncount==0){
				if(levelnum!=0){
					int sc=scoreCalc();
				Refree.setText("You Won!!!YOUR SCORE:"+ sc);
				tscore+=sc;
				}else
				Refree.setText("Tutorial Completed!");
				next.setEnabled(true);
			}
		}
		
		int scoreCalc(){
			int baseScore=50;
			int diff=150-clicks;
			if(diff>0)
			baseScore+=diff*10;
			return baseScore;
		}
		private void playSound(){
			new AudioPlayer02();
		}
		
		
}
class AudioPlayer02 {

  AudioFormat audioFormat;
  AudioInputStream audioInputStream;
  SourceDataLine sourceDataLine;
  AudioPlayer02(){
	  playAudio();
  }

  
  private void playAudio() {
    try{
      File soundFile =
                   new File("boomclap.wav");
      audioInputStream = AudioSystem.
                  getAudioInputStream(soundFile);
      audioFormat = audioInputStream.getFormat();
      System.out.println(audioFormat);

      DataLine.Info dataLineInfo =
                          new DataLine.Info(
                            SourceDataLine.class,
                                    audioFormat);

      sourceDataLine =
             (SourceDataLine)AudioSystem.getLine(
                                   dataLineInfo);

      new PlayThread().start();
    }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }


class PlayThread extends Thread{
  byte tempBuffer[] = new byte[10000];

  public void run(){
    try{
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      int cnt;
      while((cnt = audioInputStream.read(
           tempBuffer,0,tempBuffer.length)) != -1){
                       
        if(cnt > 0){
          sourceDataLine.write(
                             tempBuffer, 0, cnt);
        }
      }
sourceDataLine.drain();
    sourceDataLine.close();

    }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
}

}