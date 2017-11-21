package com.game.plane;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Specialplane1 extends PlaneObject implements Gameinform{
	private int xspeed = 1;
	private int yspeed = 2;
	private int awardType;//奖励类型
	private BufferedImage[] images = {PlanePanel.ep1,PlanePanel.ep2,PlanePanel.ep3,PlanePanel.ep4,PlanePanel.ep5,
			PlanePanel.ep6,PlanePanel.ep7,PlanePanel.ep8,PlanePanel.ep9,PlanePanel.ep10};//随机出现
	public Specialplane1() {
		Random rand = new Random();
		int i = rand.nextInt(10);
		this.image = images[i];
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		
		x = rand.nextInt(PlanePanel.WIDTH - width);
		awardType = rand.nextInt(3);
	}
	
	
	@Override
	public boolean outofBounds() {
		// TODO Auto-generated method stub
		return y>PlanePanel.HEIGHT;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		x += xspeed;
		y += yspeed;
		if(x > PlanePanel.WIDTH-width){  
			xspeed = -1;
		}
		if(x <0){
			xspeed = -1;
		}
	}

	@Override
	public int gettype() {
		
		return awardType;
	}

}
