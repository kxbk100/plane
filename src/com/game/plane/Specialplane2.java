package com.game.plane;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Specialplane2 extends PlaneObject implements Gameinform{
	private int xspeed = 1;
	private int yspeed = 2;
	private int awardType;//奖励类型
	private BufferedImage[] images = {PlanePanel.ep12,PlanePanel.ep13,PlanePanel.ep14,PlanePanel.ep15};//随机出现
	public Specialplane2() {
		Random rand = new Random();
		int i = rand.nextInt(4);
		this.image = images[i];
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		
		x = rand.nextInt(PlanePanel.WIDTH - width/2);
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
		x -= xspeed;
		y += yspeed;
		if(x > PlanePanel.WIDTH-width/2){  
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
