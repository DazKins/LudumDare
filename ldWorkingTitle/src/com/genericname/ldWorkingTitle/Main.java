package com.genericname.ldWorkingTitle;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int[] Width = new int[2];
	public static int[] Height = new int[2];
	public static int maxHeight;
	public static int totWidth;
	public static final int SCALE = 3;
	public static final String NAME = "Game";
	
	private JFrame[] frame = new JFrame[2];
	

	public Main(){
		setMinimumSize(new Dimension(totWidth, maxHeight));
		setMaximumSize(new Dimension(totWidth, maxHeight));
		setPreferredSize(new Dimension(totWidth, maxHeight));
		
		for (int i = 0; i <2; i++){
			frame[i] = new JFrame(NAME + ": Window " + i);
			
			frame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame[i].setLayout(new BorderLayout());
			
			frame[i].add(this, BorderLayout.CENTER);
			frame[i].pack();
			
			frame[i].setResizable(false);
			frame[i].setLocationRelativeTo(null);
			frame[i].setVisible(true);
		}
		
		
	}

	public synchronized void start() {
		// TODO Auto-generated method stub
		
	}
	
	public synchronized void stop() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		getScreenSize.ScreenSize();
		fullSize();
		modSize();
		new Main().start();
	}
	
	
	private static void modSize(){
		for (int i = 0; i < 2; i++){
			Width[i] = Width[i]/SCALE;
			Height[i] = Height[i]/SCALE;
		}
	}
	private static void fullSize(){
		if (Height[0] <= Height[1]){
			maxHeight = Height[1];
		}else{
			maxHeight = Height[0];
		}
		totWidth = Width[0] + Width[1];
	}
	public void run() {

	}

}
