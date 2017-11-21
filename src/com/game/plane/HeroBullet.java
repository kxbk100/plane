package com.game.plane;
/*1
 * 
 * 
 * ������:�ӵ�
 */
public class HeroBullet extends PlaneObject{
	private int speed = 3;
	
	
	//init
	public HeroBullet(int x, int y) {
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

