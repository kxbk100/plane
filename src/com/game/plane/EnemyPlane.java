package com.game.plane;
//1
//������Խ��,�����еķ����ͷɻ����ٶ�
import java.util.Random;

public class EnemyPlane extends PlaneObject implements Enemy{
	private int speed = 3;//�ٶ�
	/*
	 * ��ʼ��
	 */
	
	public EnemyPlane() {
		this.image = PlanePanel.ep1;//����ͼ��
		width = PlanePanel.ep1.getWidth();//ͼƬ�Ŀ��
		height = PlanePanel.ep1.getHeight();//�߶�
		y = -height;//??????????
		Random rand = new Random();
		x = rand.nextInt(PlanePanel.WIDTH - width);//panel�Ŀ�ȼ�ȥͼ��Ŀ�Ⱦ��ǳ��ֵ�����
	}
	//��÷���
	public int getScore() {
		return 5;
	}
	
	//Խ�紦��
	public boolean outofBounds() {
		return y>PlanePanel.HEIGHT;
	}
	
	public void step() {
		y+=speed;
	}
}
