package brickBraker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickBuilder {
	
	public int map[][];
	public int row,col;
	public  int brickHeight;
	public int brickWidth;
	
	public BrickBuilder(int row,int col)
	{
		this.row=row;
		this.col=col;
		map=new int[row][col];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				map[i][j]=1;
			}
		}
		brickWidth=540/col;
		brickHeight=150/row;
	}
	public void draw(Graphics2D g)
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(map[i][j]>0)
				{
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
				}
			}
		}
	}
	public void setBrickValue(int value,int i,int j)
	{
		map[i][j]=value;
	}
	public void reSet()
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				map[i][j]=1;
			}
		}
	}
	

}
