/**
 * This class is going to provide 
 * the implementation for the methods in the
 * paddle class
 */
package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class UserPaddle implements Paddle
{
	private double y; //Represents the y position of the paddle
	private double yVelocity;
	private boolean upAcceleration; //Keeps track of whether or not ball is moving up
	private boolean downAcceleration; //Keeps track of whether or not ball is moving down
	private int player; //Represents whether or not is player1 or player2
	private int x; //Represents the x position of the paddle
	private final double GRAVITY = 0.94; //This is the gravity constant
	
	//Constructor:
	public UserPaddle(int player)
	{
		upAcceleration = false;
		downAcceleration = false;
		y = 210;
		yVelocity = 0;
		if(player == 1)
		{
			x = 20; //On the left side of the frame
		}
		else
		{
			x = 660; //On the right side of the frame
		}
	}
	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(x, (int) y, 20, 80); //This creates the paddle
	}

	
	public void move() 
	{
		if(upAcceleration) //If ball is going up the velocity needs to decrease when it goes up
		{
			yVelocity = yVelocity - 2;
		}
		else if(downAcceleration) //If ball is going down then we want to increase the yVelcoity
		{
			yVelocity = yVelocity + 2;
		}
		else if(!upAcceleration && !downAcceleration)
		{
			yVelocity *= GRAVITY; //Forcing the yVelocity to get smaller when both conditions are false
		}
		
		if(yVelocity >= 5) //Stabilizes the speed of the paddle
		{
			yVelocity = 5;
		}
		else if(yVelocity <= -5) //Stabilizes the speed of the paddle
		{
			yVelocity = -5;
		}
			
		y+= yVelocity;
		
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
	
	public void setUpAcceleration(boolean condition) //changing upAcceleration
	{
		upAcceleration = condition;
	}

	public void setDownAcceleration(boolean condition) //changing downAcceleration
	{
		downAcceleration = condition;
	}
	
	public int getYPosition() 
	{
		int yValue = (int) y;
		return yValue;
		
	}
	

}