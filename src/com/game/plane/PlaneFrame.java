package com.game.plane;


import javax.swing.JFrame;


/*
 * @ClassName: PlaneFrame
 * @Description:创建一个游戏主窗口
 * @author: Zhang
 *
 */
public class PlaneFrame extends JFrame{
	
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 
	 * 
	 * 构造方法
	 */
	public PlaneFrame() {
		PlanePanel jp;
		jp = new PlanePanel();
		this.setContentPane(jp);
		this.setTitle("aaaaaaaaaa123");
		this.setSize(PlanePanel.WIDTH, PlanePanel.HEIGHT);
		//this.setIconImage(new ImageIcon("img/js.jpg").getImage()); 
		jp.action();
		
		//设置居中
		this.setLocationRelativeTo(null);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		//System.out.println(path);
		new PlaneFrame();
}
}
