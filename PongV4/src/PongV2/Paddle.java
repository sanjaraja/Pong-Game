package PongV2;

import java.awt.Graphics;

public interface Paddle 
{
	public void draw(Graphics g);
	public void move();
	public int getYPosition();
}