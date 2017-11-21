package com.game.plane;
//1
import java.awt.image.BufferedImage;

public class Heroplane extends PlaneObject{
	private BufferedImage[] images = {};
	private int doubleFire;
	private int threeFire;
	
	private int life;
	//init
	private int speed = 4;
	public Heroplane() {
		life = 21;
		doubleFire = 0;
		threeFire = 3;
		images  = new BufferedImage[] {PlanePanel.hero0, PlanePanel.hero1};
		image = PlanePanel.hero0;
		width = image.getHeight();//�����֪���ǲ���ͼƬ�Ŀ��
		height = image.getHeight();
		x = 600;
		y = 500;
	}
	
	//˫������
	public int isDoubleFire() {
		return doubleFire;
	}
	public void setDoubleFire(int doubleFire) {
		this.doubleFire = doubleFire;
	}
	//��������
	public int isthreefire() {
		return threeFire;
	}
	public void setthreeFire(int threeFire) {
		this.threeFire = threeFire;
	}
	public void addDoubleFire() {
		doubleFire = 40;
	}
	public void addthreeFire() {
		threeFire = 80;
	}
	public void addlife() {
		life++;
	}
	public void declife() {
		life--;
	}
	public int getlife() {
		return life;
	}
	
	public void move(int x, int y) {
		this.x = x-width/2;
		this.y = y-height/2;
	}
	
	public HeroBullet[] shoot() {
		int xStep = width/4;//�ƶ���
		int yStep = 20;
		if(threeFire == 80) {
			HeroBullet[] bullets = new HeroBullet[3];
			bullets[0] = new HeroBullet(x + xStep, y-yStep);
			bullets[1] = new HeroBullet(x + 2*xStep, y-yStep);
			bullets[2] = new HeroBullet(x + 3*xStep, y-yStep);
			return bullets;
		}else if(doubleFire == 40){
			HeroBullet[] bullets = new HeroBullet[2];
			bullets[0] = new HeroBullet(x+xStep,y-yStep);  //y-yStep(�ӵ���ɻ���λ��)
			bullets[1] = new HeroBullet(x+3*xStep,y-yStep);
			return bullets;
		}else {
			HeroBullet[] bullets = new HeroBullet[1];
			bullets[0] = new HeroBullet(x+2*xStep,y-yStep); 
			return bullets;
		}
	}
	
	public boolean hit(PlaneObject other) {
		int x1 = other.x- this.width/4;
		int x2 = other.x+ this.width/4 + other.width;
		int y1 = other.y+ this.height/4;
		int y2 = other.y  + other.height; //y����������
		
		int herox = this.x + this.width/2;           //Ӣ�ۻ�x�������ĵ����
		int heroy = this.y + this.height/2;              //Ӣ�ۻ�y�������ĵ����
		
		return herox>x1 && herox<x2 && heroy>y1 && heroy<y2;   //���䷶Χ��Ϊײ����
	}
	@Override
	public boolean outofBounds() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
		if(life > 10) {
			//��������ͼ��ı�
			image = images[1];
		}else {
			image = images[0];
		}
	}
	public void moveUp() {
		y -= speed;
		if (y < 0)
			speed = -speed;
	}

	public void moveDown() {
		y += speed;
		if (y >PlanePanel.HEIGHT - height)
			y = 0;
	}

	public void moveLeft() {
		x -= speed;
		if (x < 0)
			speed = -speed;
	}

	public void moveRight() {
		x +=speed;
		if (x >PlanePanel.WIDTH - width)
			speed = -speed;
	}
	
	
}