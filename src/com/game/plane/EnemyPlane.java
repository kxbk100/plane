package com.game.plane;
//1
//包含有越界,被击中的分数和飞机的速度
import java.util.Random;

public class EnemyPlane extends PlaneObject implements Enemy{
	private int speed = 3;//速度
	/*
	 * 初始化
	 */
	
	public EnemyPlane() {
		this.image = PlanePanel.ep1;//设置图像
		width = PlanePanel.ep1.getWidth();//图片的宽度
		height = PlanePanel.ep1.getHeight();//高度
		y = -height;//??????????
		Random rand = new Random();
		x = rand.nextInt(PlanePanel.WIDTH - width);//panel的宽度减去图像的宽度就是出现的区域
	}
	//获得分数
	public int getScore() {
		return 5;
	}
	
	//越界处理
	public boolean outofBounds() {
		return y>PlanePanel.HEIGHT;
	}
	
	public void step() {
		y+=speed;
	}
}
