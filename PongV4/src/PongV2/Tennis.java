package PongV2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.InputStream;
import java.util.Random;


public class Tennis extends Applet implements Runnable, KeyListener
{
	private final int WIDTH = 700;
	private final int HEIGHT = 500;
	private Thread tennisThread;
	private UserPaddle player1;
	private PongBall ball1; 
	private ComputerPaddle computerPaddle;
	private boolean gameStartCondition; 
	private boolean gameFinishedCondition; 
	private Graphics gfx;
	private Image img; 
	private Sound sound; 
	
	public void init()
	{ 
		this.resize(WIDTH, HEIGHT);
		gameStartCondition = false;
		gameFinishedCondition = false; 
		this.addKeyListener(this);
		player1 = new UserPaddle(1); //Instantiating a player1 object
		ball1 = new PongBall(); 
		computerPaddle = new ComputerPaddle(2, ball1);
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics(); 
		tennisThread = new Thread(this);
		tennisThread.start();
		Sound.sound1.play();
		setFocusable(true);
		requestFocusInWindow();
		
	}
	
	
	public void paint(Graphics g)
	{
		gfx.setColor(Color.BLUE);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if(ball1.getXPosition() < -10 || ball1.getXPosition() > 710)
		{
			gfx.setColor(Color.red);
			gfx.drawString("GAME OVER", 350, 250);
			gameFinishedCondition = true; 
			boolean computerCondition = ball1.getComputerCollision();
			boolean humanCondition = ball1.getHumanCollision();
			
			if(computerCondition)
			{
				gfx.drawString("COMPUTER WINS", 350, 280);
			}
			else if(humanCondition)
			{
				gfx.drawString("HUMAN WINS", 350, 280);
			}
			else if(!computerCondition && !humanCondition)
			{
				gfx.drawString("COMPUTER WINS", 350, 280);
			}
		
		}
		else
		{
			player1.draw(gfx);
			ball1.draw(gfx);
			computerPaddle.draw(gfx);
			gfx.drawLine(350, 10, 350, 490);
			gfx.drawOval(250, 165, 200, 200);
		}
		
		if(!gameStartCondition)
		{
			gfx.setColor(Color.GREEN);
			gfx.drawString("Welcome to Pong by Sanjay Rajasekaran", 250, 100);
			gfx.drawString("Press Enter to Start the Game", 250, 130);
			gfx.drawString("Press UP Key or \"W\" to move paddle up", 250, 160);
			gfx.drawString("Press DOWN Key or \"S\" to move paddle down", 250, 190);
		}
		
		g.drawImage(img, 0, 0, this);
		
	}
	
	
	
	public void update(Graphics g)
	{
		paint(g);
	}

	
	public void run() 
	{
		for(;;)
		{
			if(gameStartCondition && !gameFinishedCondition)
			{ 
				player1.move(); //Each time this for loop iterates the player's paddle must be moved
				computerPaddle.move(); //Each time this loop iterates the AI paddle will move
				ball1.move();
				ball1.checkCollission(player1, computerPaddle);
				System.out.println("Collision Counter: " + computerPaddle.getCollision());
				System.out.println("Y Velocity: " + computerPaddle.getYVelocity());
			}
			
		
			repaint(); //This is the engine that will keep on running the game
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void keyTyped(KeyEvent e) 
	{
		
		
	}

	
	public void keyPressed(KeyEvent e) //Pressing the key
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			player1.setUpAcceleration(true); 
			//System.out.println("In up");
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player1.setDownAcceleration(true);
			//System.out.println("In down");
		}
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			player1.setUpAcceleration(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			player1.setDownAcceleration(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			gameStartCondition = true; 
		}
		
			
		
	}

	
	public void keyReleased(KeyEvent e) //Letting go of the key
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			player1.setUpAcceleration(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player1.setDownAcceleration(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			player1.setUpAcceleration(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			player1.setDownAcceleration(false);
		}
		else
			System.out.println("You pressed an invalid key");
		
	}	
	
	public int randomNumber()
	{
		Random rand = new Random();
		int x = rand.nextInt(10) + 1;
		return x; 
	}
	
	
	
}

	