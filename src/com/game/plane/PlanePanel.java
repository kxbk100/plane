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
	//���ñ���ͼƬ��λ��
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 800;
	//����״̬����
	public int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	private static final int GG = 3;
	
	//ͼ��
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
		//�ƺ����Եõ���ַ
		public static String path = System.getProperty("user.dir") +"\\img";
		//��ֵ�� ����Լ�����������
		//String ͼƬ����
		//bufferedimage ͼƬ
		public static HashMap<String, BufferedImage> maps = new HashMap<>();
		//������Դ
		//��̬��ǰ����
		static {
			//�õ�img�е��ز�����
			File[] files = new File(path).listFiles();
			for(int i = 0;i<files.length; i++) {
				//��ͼƬ���ص���������
				try {
					maps.put(files[i].getName(), ImageIO.read(files[i]));
					//���Ѿ���ͼƬ�Ļ���ӳ�䵽��ַ��
					
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
	private Timer timer;//��ʱ��
	private int intervel = 1000/100;//ʱ����?
	private PlaneObject[] planes = {};//�����ɻ�������
	private EnemyBullet[] bullets = {};
	private HeroBullet[]  Hbullets = {};
	
	private Heroplane hero = new Heroplane();//����һ������
//	Point bgpoint = new Point(0, -1300);
//	public PlanePanel() {
////		new Thread( new bgThread()).start();;
//	}
	public void paint(Graphics g) {
		super.paint(g);
//		//����?
//		BufferedImage image = new BufferedImage(600, 700, BufferedImage.TYPE_INT_RGB);
//		//����?
//		Graphics gs = image.getGraphics();
//		//������?
//		gs.drawImage(PlanePanel.maps.get("map.jpg"),0,0,this);
//		//��дһ��??
		g.drawImage(background,0, 0,null);
		paintHero(g); // ��Ӣ�ۻ�
		paintEnemyBullets(g); // ���ӵ�
		paintHeroBullets(g);
		paintPlaneObjects(g); // ��������
		paintScore(g); // ������
		paintState(g); // ����Ϸ״̬
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
		case PAUSE: // ��ͣ״̬
			g.drawImage(pause, 0, 0, null);
			break;
		case GG: // ��Ϸ��ֹ״̬
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
		
		g.drawString("SCORE:" + score, x, y); // ������
		y=y+20; // y������20
		g.drawString("LIFE:" + hero.getlife(), x, y); // ����
		
		
	}
	
	public void action() {
		//������?
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
//			public void mouseExited(MouseEvent e) { // ����˳�
//				if (state == RUNNING) {   // ��Ϸδ��������������Ϊ��ͣ
//					state = PAUSE;
//				}
//			}
//			public void mouseClicked(MouseEvent e) { // �����
//				switch (state) {
//				case START:
//					state = RUNNING; // ����״̬������
//					break;
//				case GG: // ��Ϸ�����������ֳ�
//					planes = new PlaneObject[0]; // ��շ�����
//					bullets = new EnemyBullet[0]; // ����ӵ�
//					Hbullets = new HeroBullet[0];
//					hero = new Heroplane(); // ���´���Ӣ�ۻ�
//					score = 0; // ��ճɼ�
//					state = START; // ״̬����Ϊ����
//					break;
//				}
//			}
//		};
//		this.addMouseListener(l);
//		this.addMouseMotionListener(l);
		MouseAdapter l = new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) { // �����
				switch (state) {
				case START:
					state = RUNNING; // ����״̬������
					break;
				case GG: // ��Ϸ�����������ֳ�
					planes = new PlaneObject[0]; // ��շ�����
					bullets = new EnemyBullet[0]; // ����ӵ�
					Hbullets = new HeroBullet[0];
					hero = new Heroplane(); // ���´���Ӣ�ۻ�
					score = 0; // ��ճɼ�
					state = START; // ״̬����Ϊ����
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
					outOfBoundsAction(); // ɾ��Խ������Ｐ�ӵ�
					checkGameOverAction(); // �����Ϸ����
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
			PlaneObject obj = nextOne(); // �������һ��������
			planes = Arrays.copyOf(planes, planes.length + 1);
			planes[planes.length - 1] = obj;
		}
	}
	public void stepAction() {
		for (int i = 0; i < planes.length; i++) { // ��������һ��
			PlaneObject f = planes[i];
			f.step();
		}

		for (int i = 0; i < bullets.length; i++) { // �ӵ���һ��
			EnemyBullet b = bullets[i];
			b.step();
		}	
		for (int i = 0; i < Hbullets.length; i++) { // �ӵ���һ��
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
		if (shootTime % 30 == 0) {// 300���뷢һ��
			shootTime = 0;//��ֹһֱ�ۼӳ������
			HeroBullet[] bs = hero.shoot();//����ӵ�
			Hbullets = Arrays.copyOf(Hbullets, Hbullets.length + bs.length); // ����
			System.arraycopy(bs, 0, Hbullets, Hbullets.length - bs.length,bs.length); // ׷������
			System.out.println(Hbullets.length);
		}
		
	}
	
	//�ӵ����������ײ���
	public void bangAction() {
		for(int i = 0; i < bullets.length ; i++) {
			HeroBullet b = Hbullets[i];
			bang(b);
		}
	}
	public void outOfBoundsAction() {
		// ɾ��Խ������Ｐ�ӵ�
		int index1= 0,index2 = 0; // ����
		PlaneObject[] planelives = new PlaneObject[planes.length]; // ���ŵķ�����
		for (int i = 0; i < planes.length; i++) {
			PlaneObject p = planes[i];
			if (!p.outofBounds()) {
				planelives[index1++] = p; // ��Խ�������
			}
		}
		planes = Arrays.copyOf(planelives, index1); // ����Խ��ķ����ﶼ����
		
		index1 = 0;// ��������Ϊ0
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
		Hbullets = Arrays.copyOf(HbulletLives, index2);// ����Խ����ӵ�����
	}
	/**�����Ϸ������״̬*/
	public void checkGameOverAction() {
		if (isGameOver()==true) {
			state = GG; // �ı�״̬
		}
	}
	/**�ж���û�˾ͽ�����Ϸ*/
	public boolean isGameOver() {
		for(int i =0; i<planes.length;i++ ) {
			int index = -1;
			PlaneObject obj = planes[i];
			if (hero.hit(obj)) { // ���Ӣ�ۻ���������Ƿ���ײ
				hero.declife(); // ����
				if(hero.isthreefire() == 80){
					hero.setthreeFire(3);
				}else{					
					hero.setDoubleFire(0); // ˫���������
				}
				index = i; // ��¼���ϵķ���������
			}
			if (index != -1) {
				PlaneObject t = planes[index];
				planes[index] = planes[planes.length - 1];
				planes[planes.length - 1] = t; // ���ϵ������һ�������ｻ��

				planes = Arrays.copyOf(planes, planes.length - 1); // ɾ�����ϵķ�����
			}
		}
		return hero.getlife() <= 0;
	}
	
	/** �ӵ��ͷ�����֮�����ײ��� */
	
	//ǿ������ת���ǿ黹û���,����gettpye����
	public void bang(HeroBullet bullet) {
		int index = -1; // ���еķ���������
		for (int i = 0; i < planes.length; i++) {
			PlaneObject obj = planes[i];
			if (obj.shooted(bullet)) { // �ж��Ƿ����
				index = i; // ��¼�����еķ����������
				break;
			}
		}
		if (index != -1) { // �л��еķ�����
			PlaneObject one = planes[index]; // ��¼�����еķ�����

			PlaneObject temp = planes[index]; // �����еķ����������һ�������ｻ��
			planes[index] = planes[planes.length - 1];
			planes[planes.length - 1] = temp;

			planes = Arrays.copyOf(planes, planes.length - 1); // ɾ�����һ��������(�������е�)

			// ���one������(���˼ӷ֣�������ȡ)
			if (one instanceof Enemy) { // ������ͣ��ǵ��ˣ���ӷ�
				Enemy e = (Enemy) one; // ǿ������ת��
				score += e.getScore(); // �ӷ�
			} else { // ��Ϊ���������ý���
				Gameinform a = (Gameinform)one;
				int type = a.gettype(); // ��ȡ��������
				switch (type) {
				case Gameinform.DOUBLEBULLET:
					if(hero.isDoubleFire() == 40){
						hero.addthreeFire();
						break;
					}
					hero.addDoubleFire(); // ����˫������
					break;
				case Gameinform.LIFE:
					hero.addlife(); // ���ü���
					break;
				}
			}
		}
	}

	/**
	 * ������ɷ�����
	 * ����ʹ�ò�ͬ��class�ļ�������������ķɻ�
	 * �ټ���һ�������,ʹ�����������,���ַɻ�������,������ɻ���ͼƬҲҪ���
	 * Ȼ�������ض�ʱ��/�ض����� �ϳ���boss
	 * boss���ӵ�����ֲ�ͬ�Ĺ켣,ֱ�ߺ�б��
	 * @return ���������
	 */
	public static PlaneObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(30); // [0,20)
		if (type < 2) {
			return new Specialplane1();//�н���
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
