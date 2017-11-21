package com.game.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



class PlanePanel extends JPanel implements KeyListener{
	//设置背景图片的位置
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 800;
	//声明状态常量
	public int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	private static final int GG = 3;
	
	//图像
		public static BufferedImage background;
		public static BufferedImage start;
		public static BufferedImage ep1,ep2,ep3,ep4,ep5,ep6,ep7,ep8,ep9,ep10,ep12,ep13,ep14,ep15,epb;
		public static BufferedImage boss1;
		public static BufferedImage boss2;
		//public static BufferedImage bee;
		public static BufferedImage bullet1,bullet2,bullet3;
		public static BufferedImage hero0,hero1;
		public static BufferedImage warning;
		public static BufferedImage bang;
		public static BufferedImage pause;
		public static BufferedImage bossbullet;
		public static BufferedImage addbullet;
		public static BufferedImage gg;
		//似乎可以得到地址
		public static String path = System.getProperty("user.dir") +"\\img";
		//键值对 泛型约束输入的内容
		//String 图片名字
		//bufferedimage 图片
		public static HashMap<String, BufferedImage> maps = new HashMap<>();
		//加载资源
		//静态提前加载
		static {
			//拿到img中的素材名字
			File[] files = new File(path).listFiles();
			for(int i = 0;i<files.length; i++) {
				//将图片加载到集合里面
				try {
					maps.put(files[i].getName(), ImageIO.read(files[i]));
					//这已经将图片的缓存映射到地址上
					
			//	background =new ImageIcon(maps.get("brackground_1.gif"));
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			background = maps.get("map.jpg");
			addbullet = maps.get("addbullet.gif");
			start = maps.get("bg.jpg");
			pause = maps.get("pause.png");
			gg = maps.get("gameover.png");
			bang = maps.get("blow.gif");
			boss1 = maps.get("boss_1.png");
			boss2 = maps.get("boss_2.png");
			bossbullet = maps.get("BossBullet.png");
			ep1 = maps.get("ep_1.png");
			ep2 = maps.get("ep_2.png");
			ep3= maps.get("ep_3.png");
			ep4= maps.get("ep_4.png");
			ep5= maps.get("ep_5.png");
			ep6= maps.get("ep_6.png");
			ep7= maps.get("ep_7.png");
			ep9= maps.get("ep_9.png");
			ep8= maps.get("ep_8.png");
			ep10= maps.get("ep_10.png");
			ep12= maps.get("ep_12.png");
			ep13= maps.get("ep_13.png");
			ep14= maps.get("ep_14.png");
			ep15= maps.get("ep_15.png");
			epb = maps.get("epb_1.png");
			hero0 = maps.get("my_1.png");
			hero1 = maps.get("my_2.png");
			bullet1 = maps.get("myb_1.png");
			bullet2 = maps.get("myb_2.png");
			bullet3 = maps.get("myb_3.png");;
			warning = maps.get("warning.gif");
		}

	private int score = 0;
	private Timer timer;//定时器
	private int intervel = 1000/100;//时间间隔?
	private PlaneObject[] planes = {};//声明飞机的数组
	private EnemyBullet[] bullets = {};
	private HeroBullet[]  Hbullets = {};
	
	private Heroplane hero = new Heroplane();//创建一个对象
//	Point bgpoint = new Point(0, -1300);
//	public PlanePanel() {
////		new Thread( new bgThread()).start();;
//	}
	public void paint(Graphics g) {
		super.paint(g);
//		//画板?
//		BufferedImage image = new BufferedImage(600, 700, BufferedImage.TYPE_INT_RGB);
//		//画笔?
//		Graphics gs = image.getGraphics();
//		//画背景?
//		gs.drawImage(PlanePanel.maps.get("map.jpg"),0,0,this);
//		//再写一个??
		g.drawImage(background,0, 0,null);
		paintHero(g); // 画英雄机
		paintEnemyBullets(g); // 画子弹
		paintHeroBullets(g);
		paintPlaneObjects(g); // 画飞行物
		paintScore(g); // 画分数
		paintState(g); // 画游戏状态
	}
	public void paintHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	public void paintEnemyBullets(Graphics g) {
		for(int i =0; i< Hbullets.length; i++) {
			HeroBullet h = Hbullets[i];
			g.drawImage(h.getImage(), h.getX()-h.getWidth()/2, h.getY() -h.getHeight()/2, null);
			
		}	
	}
	public void paintHeroBullets(Graphics g) {
		for(int i =0; i< bullets.length; i++) {
			EnemyBullet b = bullets[i];
			g.drawImage(b.getImage(), b.getX()-b.getWidth()/2, b.getY() - b.getHeight()/2, null);
		}
		//System.out.println(bullets.length);
			
		
	}
	public void paintPlaneObjects(Graphics g) {
		for(int i =0; i<planes.length; i++) {
			PlaneObject p =planes[i];
			g.drawImage(p.getImage(), p.getX(), p.getY(), null);
		}
	}
	
	public void paintState(Graphics g) {
		switch(state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE: // 暂停状态
			g.drawImage(pause, 0, 0, null);
			break;
		case GG: // 游戏终止状态
			g.drawImage(gg, 0, 0, null);
			break;
		}
	}
	public void paintScore(Graphics g) {
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		
		g.setColor(new Color(0xff0000));
		g.setFont(font);
		
		g.drawString("SCORE:" + score, x, y); // 画分数
		y=y+20; // y坐标增20
		g.drawString("LIFE:" + hero.getlife(), x, y); // 画命
		
		
	}
	
	public void action() {
		//鼠标监听?
//		MouseAdapter l = new MouseAdapter() {
//			public void mouseMoved(MouseEvent e) {
//				if(state == RUNNING) {
//					int x = e.getX();
//					int y = e.getY();
//					hero.move(x, y);
//				}
//			}
//			public void mouseEntered(MouseEvent e) {
//				if(state == PAUSE) {
//					state = RUNNING;
//				}
//			}
//			
//			public void mouseExited(MouseEvent e) { // 鼠标退出
//				if (state == RUNNING) {   // 游戏未结束，则设置其为暂停
//					state = PAUSE;
//				}
//			}
//			public void mouseClicked(MouseEvent e) { // 鼠标点击
//				switch (state) {
//				case START:
//					state = RUNNING; // 启动状态下运行
//					break;
//				case GG: // 游戏结束，清理现场
//					planes = new PlaneObject[0]; // 清空飞行物
//					bullets = new EnemyBullet[0]; // 清空子弹
//					Hbullets = new HeroBullet[0];
//					hero = new Heroplane(); // 重新创建英雄机
//					score = 0; // 清空成绩
//					state = START; // 状态设置为启动
//					break;
//				}
//			}
//		};
//		this.addMouseListener(l);
//		this.addMouseMotionListener(l);
		MouseAdapter l = new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) { // 鼠标点击
				switch (state) {
				case START:
					state = RUNNING; // 启动状态下运行
					break;
				case GG: // 游戏结束，清理现场
					planes = new PlaneObject[0]; // 清空飞行物
					bullets = new EnemyBullet[0]; // 清空子弹
					Hbullets = new HeroBullet[0];
					hero = new Heroplane(); // 重新创建英雄机
					score = 0; // 清空成绩
					state = START; // 状态设置为启动
					break;
				}
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		
//		KeyAdapter k = new KeyAdapter() {
//			public void keyPressed(KeyEvent e){
//				switch(e.getKeyCode()){
//					case KeyEvent.VK_UP:
//						hero.y -= 4;
//						break;
//					case KeyEvent.VK_DOWN:
//						hero.y += 4;
//						break;
//					case KeyEvent.VK_LEFT:
//						hero.x -= 4;
//						break;
//					case KeyEvent.VK_RIGHT:
//						hero.x += 4;
//						break;
//				}
//			}
//			
//		};
//		this.addKeyListener(k);
		//cant und
		timer = new Timer();
		timer.schedule( new TimerTask() {
			public void run() {
				if(state == RUNNING) {
					randomplaneAction();
					stepAction();
					shootAction();
					bangAction();
					outOfBoundsAction(); // 删除越界飞行物及子弹
					checkGameOverAction(); // 检查游戏结束
				}
				repaint();
			}
		},intervel,intervel);
	}
	int planeEnteredIndex = 0;
	
	
	public void randomplaneAction() {
		planeEnteredIndex++;
		if(planeEnteredIndex % 40 == 0) {
			planeEnteredIndex =0;
			PlaneObject obj = nextOne(); // 随机生成一个飞行物
			planes = Arrays.copyOf(planes, planes.length + 1);
			planes[planes.length - 1] = obj;
		}
	}
	public void stepAction() {
		for (int i = 0; i < planes.length; i++) { // 飞行物走一步
			PlaneObject f = planes[i];
			f.step();
		}

		for (int i = 0; i < bullets.length; i++) { // 子弹走一步
			EnemyBullet b = bullets[i];
			b.step();
		}	
		for (int i = 0; i < Hbullets.length; i++) { // 子弹走一步
			HeroBullet h= Hbullets[i];
			h.step();
		}
		
		hero.step();
	}
	public void planeStepAction() {
		for (int i = 0; i < planes.length; i++) {
			PlaneObject p = planes[i];
			p.step();
		}
	}
	int shootTime = 0;
	public void shootAction() {
		shootTime++;
		if (shootTime % 30 == 0) {// 300毫秒发一颗
			shootTime = 0;//防止一直累加出现溢出
			HeroBullet[] bs = hero.shoot();//打出子弹
			Hbullets = Arrays.copyOf(Hbullets, Hbullets.length + bs.length); // 扩容
			System.arraycopy(bs, 0, Hbullets, Hbullets.length - bs.length,bs.length); // 追加数组
			System.out.println(Hbullets.length);
		}
		
	}
	
	//子弹与飞行物碰撞检测
	public void bangAction() {
		for(int i = 0; i < bullets.length ; i++) {
			HeroBullet b = Hbullets[i];
			bang(b);
		}
	}
	public void outOfBoundsAction() {
		// 删除越界飞行物及子弹
		int index1= 0,index2 = 0; // 索引
		PlaneObject[] planelives = new PlaneObject[planes.length]; // 活着的飞行物
		for (int i = 0; i < planes.length; i++) {
			PlaneObject p = planes[i];
			if (!p.outofBounds()) {
				planelives[index1++] = p; // 不越界的留着
			}
		}
		planes = Arrays.copyOf(planelives, index1); // 将不越界的飞行物都留着
		
		index1 = 0;// 索引重置为0
		EnemyBullet[] bulletLives = new EnemyBullet[bullets.length];
		HeroBullet[] HbulletLives = new HeroBullet[Hbullets.length];
		for (int i = 0; i < bullets.length; i++) {
			EnemyBullet b = bullets[i];
			HeroBullet h = Hbullets[i];
			if (!b.outofBounds()|| !h.outofBounds()) {
				bulletLives[index1++] = b;
				HbulletLives[index2++] = h;
				
			}
		}
		bullets = Arrays.copyOf(bulletLives, index1); 
		Hbullets = Arrays.copyOf(HbulletLives, index2);// 将不越界的子弹留着
	}
	/**检查游戏结束的状态*/
	public void checkGameOverAction() {
		if (isGameOver()==true) {
			state = GG; // 改变状态
		}
	}
	/**判断命没了就结束游戏*/
	public boolean isGameOver() {
		for(int i =0; i<planes.length;i++ ) {
			int index = -1;
			PlaneObject obj = planes[i];
			if (hero.hit(obj)) { // 检查英雄机与飞行物是否碰撞
				hero.declife(); // 减命
				if(hero.isthreefire() == 80){
					hero.setthreeFire(3);
				}else{					
					hero.setDoubleFire(0); // 双倍火力解除
				}
				index = i; // 记录碰上的飞行物索引
			}
			if (index != -1) {
				PlaneObject t = planes[index];
				planes[index] = planes[planes.length - 1];
				planes[planes.length - 1] = t; // 碰上的与最后一个飞行物交换

				planes = Arrays.copyOf(planes, planes.length - 1); // 删除碰上的飞行物
			}
		}
		return hero.getlife() <= 0;
	}
	
	/** 子弹和飞行物之间的碰撞检查 */
	
	//强制类型转换那块还没清楚,还有gettpye那里
	public void bang(HeroBullet bullet) {
		int index = -1; // 击中的飞行物索引
		for (int i = 0; i < planes.length; i++) {
			PlaneObject obj = planes[i];
			if (obj.shooted(bullet)) { // 判断是否击中
				index = i; // 记录被击中的飞行物的索引
				break;
			}
		}
		if (index != -1) { // 有击中的飞行物
			PlaneObject one = planes[index]; // 记录被击中的飞行物

			PlaneObject temp = planes[index]; // 被击中的飞行物与最后一个飞行物交换
			planes[index] = planes[planes.length - 1];
			planes[planes.length - 1] = temp;

			planes = Arrays.copyOf(planes, planes.length - 1); // 删除最后一个飞行物(即被击中的)

			// 检查one的类型(敌人加分，奖励获取)
			if (one instanceof Enemy) { // 检查类型，是敌人，则加分
				Enemy e = (Enemy) one; // 强制类型转换
				score += e.getScore(); // 加分
			} else { // 若为奖励，设置奖励
				Gameinform a = (Gameinform)one;
				int type = a.gettype(); // 获取奖励类型
				switch (type) {
				case Gameinform.DOUBLEBULLET:
					if(hero.isDoubleFire() == 40){
						hero.addthreeFire();
						break;
					}
					hero.addDoubleFire(); // 设置双倍火力
					break;
				case Gameinform.LIFE:
					hero.addlife(); // 设置加命
					break;
				}
			}
		}
	}

	/**
	 * 随机生成飞行物
	 * 可以使用不同的class文件创建特殊种类的飞机
	 * 再加上一个随机数,使用两个随机数,出现飞机的种类,和特殊飞机的图片也要随机
	 * 然后是在特定时间/特定分数 上出现boss
	 * boss的子弹会出现不同的轨迹,直线和斜线
	 * @return 飞行物对象
	 */
	public static PlaneObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(30); // [0,20)
		if (type < 2) {
			return new Specialplane1();//有奖励
		} else if(type >3 && type < 10){
			return new Specialplane2();
		}else{
			return new EnemyPlane();
		}
	}
//	class bgThread implements Runnable{
//		@Override
//		public void run() {
//			while(true) {
//				if(bgpoint.y == 700) {
//					bgpoint.y = -1300;
//				}
//				bgpoint.y += 1;
//				
//				repaint();
//			}
//			
//		}
//	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			hero.moveUp();
			repaint();
			break;
		case KeyEvent.VK_DOWN:
			hero.moveDown();
			repaint();
			break;
		case KeyEvent.VK_LEFT:
			hero.moveLeft();
			repaint();
			break;
		case KeyEvent.VK_RIGHT:
			hero.moveRight();
			repaint();
			break;
	}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
