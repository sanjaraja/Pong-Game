package PongV2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class PongBall 
{
	private double xVelocity;
	private double yVelocity;
	private double x;
	private double y;
	private final int HEIGHTBALL = 20;
	private final int WIDTHBALL = 20; 
	private boolean humanCollision;
	private boolean computerCollision; 
	private int computerCounter; 
	private int randNumber; 
	private Random rand; 
	
	//Constructor:
	public PongBall()
	{
		x = 350;
		y = 250;
		xVelocity = -2; //Gives low angle to start with
		yVelocity = 1;  //Gives low angle to start with
		computerCounter = 0; 
		rand = new Random();
		randNumber = 0; 
	}
	
	
	//Moves the pong ball:
	public void move()
	{
		x = x + xVelocity;
		y = y + yVelocity; 
		
		//This series of if statements will make sure ball does not go off the screen:
		if(y < 10)
		{
			yVelocity = -yVelocity; //Makes sure ball travels back in opposite direction
		}
		
		if(y > 490)
		{
			yVelocity = -yVelocity; //Makes sure ball travels back in opposite direction
		}
	}
	
	//This draw the pong ball:
	public void draw(Graphics g)
	{
		int xPosBall = (int)x - 10;
		int yPosBall = (int)y - 10;
		g.setColor(Color.white);
		g.fillOval(xPosBall, yPosBall, HEIGHTBALL, WIDTHBALL);
	}
	/**
	 * 
	 * @return xPosition of the pong ball
	 */
	
	//This method will check for the collision between ball and paddle:
	public void checkCollission(Paddle p1, Paddle p2)
	{
		if(x <= 50) //First x-bound that needs to be checked
		{
			if(y >= p1.getYPosition() && y <= p1.getYPosition() + 80)
			{
				xVelocity = -xVelocity; 
				humanCollision = true;
				computerCollision = false;
				increaseX();
			}
		}
		else if(x >= 650) //Second x-bound that needs to be checked
		{
			if(y >= p2.getYPosition() && y <= p2.getYPosition() + 80)
			{
				xVelocity = -xVelocity; 
				computerCollision = true;
				computerCounter ++; 
				humanCollision = false;
			}
		}
	}
	public int getXPosition() 
	{
		int xPosition = (int)x;
		return xPosition;
	}
	
	/**
	 * 
	 * @return yPosition of the pong ball
	 */
	public int getYPosition() 
	{
		int yPosition = (int)y;
		return yPosition;
	}
	
	public int getFalseYPosition()
	{
		int yPosition = (int)y;
		int tempPosition = yPosition; 
		randNumber = rand.nextInt(10) + 1;
		if(randNumber == 1)
		{
			int rand2Number = rand.nextInt(50) + 20;
			yPosition += rand2Number;
		}
		else if(randNumber == 2)
		{
			int rand2Number = rand.nextInt(50) + 20;
			yPosition -= rand2Number;
		}
		else
		{
			yPosition = tempPosition; 
		}
		
		return yPosition; 
	}
	
	/**
	 * 
	 * @return humanCollision condition
	 */
	public boolean getHumanCollision()
	{
		return humanCollision;
	}
	
	/**
	 * 
	 * @return computerCollision condition
	 */
	public boolean getComputerCollision()
	{
		return computerCollision; 
	}
	
	public void increaseX()
	{
		xVelocity += 0.385; 
	}
	
	public int getComputerHits()
	{
		return computerCounter;
	}
	
	public int getRandNumber()
	{
		return randNumber; 
	}
	
}