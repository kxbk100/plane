package com.game.plane;
//1
import java.awt.Image;
import java.awt.image.BufferedImage;


public abstract class PlaneObject {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;//图片
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	//出界 return true表示出界
	public abstract boolean outofBounds();
	
	//飞行物移动一步
	public abstract void step();
	//true 被击中
	//当前飞行物是否被击中
	public boolean shooted(HeroBullet bullet) {
		int x = bullet.x; //子弹的坐标
		int y = bullet.y;
		return this.x < x && x< this.x + width && this.y < y && y< this.y +height;
	}

}