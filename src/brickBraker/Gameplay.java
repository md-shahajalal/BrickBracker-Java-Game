package brickBraker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
public class Gameplay extends JPanel implements ActionListener,KeyListener{
	private Timer timer;
	private int delay=8;
	private int totalBricks=21;
	
	private int paddleX=315;
	private int paddleMovement=20;
	private int ballX=350;
	private int ballY=530;
	private int ballXdir=-1;
	private int ballYdir=-2;
	private boolean play=false;
	private int score=0;
	private  BrickBuilder brickBuilder;
	
  public Gameplay()
  {
	   brickBuilder=new BrickBuilder(3, 7);
	  addKeyListener(this);
	  setFocusable(true);
	  setFocusTraversalKeysEnabled(false);
	  timer=new Timer(delay,this);
	  timer.start();
	  
  }
  public void paint(Graphics g)
  {
	  //background
      
	  g.setColor(Color.black);
	  g.fillRect(1,1,690,590);
	  
	  //bricks
	  brickBuilder.draw((Graphics2D) g);
	  
	  //borders
	  g.setColor(Color.cyan);
	  g.fillRect(0,0,5,590);
	  g.fillRect(685,0,5,590);
	  //g.fillRect(10,570,680,5);
	  g.fillRect(5,0,680,5);
	  
	  //scores
	  g.setColor(Color.white);
	  g.setFont(new Font("serif",Font.BOLD, 25));
	  g.drawString(""+score,570,35);
	  //paddle 
	  g.setColor(Color.green);
	  g.fillRect(paddleX,550,100,8);
	
	  
	  //ball
	  g.setColor(Color.YELLOW);
	  g.fillOval(ballX,ballY,20,20);
	  
	  if(ballY>=580)
	  {
		  play=false;
		  ballXdir=0;
		  ballYdir=0;
		  paddleMovement=0;
		  g.setColor(Color.red);
		  g.setFont(new Font("serif",Font.BOLD, 25));
		  g.drawString("Game Over😞,Scores: "+score,250,280);
		  g.setFont(new Font("serif",Font.BOLD, 15));
		  g.drawString("press Enter to Restart",250,320);
		  //paddle 
	  }
	  
	  if(totalBricks==0)
	  {
		  play=false;
		  ballXdir=0;
		  ballYdir=0;
		  paddleMovement=0;
		  
		  g.setColor(Color.green);
		  g.setFont(new Font("serif",Font.BOLD, 25));
		  g.drawString("You Win!!!! 😀",250,280);
		  
		  g.setFont(new Font("serif",Font.BOLD, 15));
		  g.drawString("press Enter to Restart",250,320);
	  }
	  
	  
	  g.dispose();
  }
  
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(paddleX<=565)
			{
				paddleX+=paddleMovement;
				play=true;
			}
			else 
			{
				paddleX=585;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(paddleX>=25)
			{
				paddleX-=paddleMovement;
				play=true;
			}
			else
			{
				paddleX=5;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play=true;
				 totalBricks=21;
				 score=0;
				 paddleX=315;
				 paddleMovement=20;
				 ballX=350;
				 ballY=530;
				 ballXdir=-1;
				 ballYdir=-2;
				 brickBuilder.reSet();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(play)
		{
			
			if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(paddleX,550,100,8)))
			{
				ballYdir=-ballYdir;
			}
			
			A:for(int i=0;i<brickBuilder.row;i++)
			{
				for(int j=0;j<brickBuilder.col;j++)
				{
					if(brickBuilder.map[i][j]>0)
					{
						Rectangle ball=new Rectangle(ballX,ballY,20,20);
						int width=brickBuilder.brickWidth;
						int height=brickBuilder.brickHeight;
						int x=80+j*width;
						int  y=50+i*height;
						Rectangle brickRect=new Rectangle(x,y,width,height);
						Rectangle brick=brickRect;
						if(ball.intersects(brick))
						{
							brickBuilder.setBrickValue(0, i, j);
							totalBricks--;
							score+=5;
							if(ballX+19<=brick.x || ballX+1>=brick.x+brick.width)
							{
								ballXdir=-ballXdir;
							}
							else
							{
								ballYdir=-ballYdir;
							}
							break A;
						}
					}
					//break A;
				}
			}
			ballX+=ballXdir;
			ballY+=ballYdir;
			if(ballX<=5)
			{
				ballXdir=-ballXdir;
			}
			else  if(ballX>=670)
			{
				ballXdir=-ballXdir;
			}
			else  if(ballY<=5)
			{
				ballYdir=-ballYdir;
			}
		}
		
		repaint();
		
	}

}
