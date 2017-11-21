package com.game.plane;
/*1
 * 
 * 
 * ·ÉÐÐÎï:×Óµ¯
 */
public class Bullet extends PlaneObject{
	private int speed = 3;
	
	
	//init
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = PlanePanel.bullet1;
	}
	
	public void step() {
		y-=speed;
	}
	
	public boolean outofBounds() {
		return y < -height;
	}

}
