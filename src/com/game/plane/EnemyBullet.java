package com.game.plane;


/*1
 * 
 * 
 * ·ÉÐÐÎï:×Óµ¯
 */
public class EnemyBullet extends PlaneObject{
	private int speed = 3;
	
	
	//init
	public EnemyBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = PlanePanel.bossbullet;
	}
	
	public void step() {
		x += speed;
		y += speed;
		if(x > PlanePanel.WIDTH-width){  
			speed = -1;
		}
		if(x < 0){
			speed = 1;
		}
	}
	
	public boolean outofBounds() {
		return y < -height;
	}

}
