/**
 * This class is going to provide 
 * the implementation for the methods in the
 * AI paddle class
 */
package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class ComputerPaddle implements Paddle
{
	private double y; //Represents the y position of the paddle
	private double yVelocity;
	private int player; //Represents whether or not is player1 or player2
	private int x; //Represents the x position of the paddle
	private final double GRAVITY = 0.94; //This is the gravity constant
	PongBall b1; //This instance of PongBall class will help track position of the ball
	private int collisionCounter; 
	
	//Constructor:
	public ComputerPaddle(int player, PongBall b)
	{
		b1 = b; //New instantiaion within the AI constructor
		y = 210;
		yVelocity = 1;
		if(player == 1)
		{
			x = 20; //On the left side of the frame
		}
		else
		{
			x = 660; //On the right side of the frame
		}
		collisionCounter = 0; 
	}
	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(x, (int) y, 20, 80); //This creates the paddle
	}

	
	public void move() 
	{
		updateCollision(b1.getComputerHits());
		int colNum = getCollision(); 
		y = b1.getYPosition() - 40;
		
		if(colNum % 25 == 0 && colNum != 0)
		{
			y = b1.getYPosition() + colNum; 
		}
		
		//This series of if statements will prevent the paddle from the exiting the frame:
		if(y < 0)
		{
			y = 0;
		}
		else if(y > 420)
		{
			y = 420;
		}
	}
	
	public int getYPosition() 
	{
		int yValue = (int) y;
		return yValue;
		
	}
	
	public void updateCollision(int num)
	{
		collisionCounter = num;  
	}
	
	public int getCollision()
	{
		return collisionCounter; 
	}
	
	public double getYVelocity()
	{
		return yVelocity; 
	}
	

	

}