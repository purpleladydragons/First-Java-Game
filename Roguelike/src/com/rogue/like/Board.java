package com.rogue.like;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
	Image grass,water;
	int turn,tileholder;
	Character ch;
	Cursor cu;
	Enemy en;
	Timer timer,anim,enemytime;
	Tile tiles[][] = new Tile[8][8];
	
	
	public Board(){

		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++){
				tiles[i][j] = new Tile();
			
			}
		ch = new Character();
		cu = new Cursor();
		en = new Enemy();
		addKeyListener(new TAdapter());
		setFocusable(true);
		setSize(400,400);
		setBackground(Color.WHITE);
		
		ImageIcon i = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/grass.jpg");
		ImageIcon j = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/water.jpg");
		grass = i.getImage();
		water = j.getImage();
		
		turn = 0;
		
		
		anim = new Timer(60, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(ch.getDead() == true){
					lose();
				} else if(en.getDead() ==true){
					win();
				}
				
				
				
				if((cu.getSelected() == false) && (cu.getMoving() == false)){
					cu.move();
				} 
				cu.select(cu,ch,cu.getX(),cu.getY(),ch.getX(),ch.getY());
				ch.attack(en);
				ch.addHealth((int)(1 + Math.random()*50));
					
				en.move(ch.getX(),ch.getY());
				en.attack(ch);

			
			repaint();
			}
		});
		
		anim.start();
	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				if (tiles[i][j].passable == true){
					g.drawImage(grass,i*50, j*50,null);
				} else {
					g.drawImage(water,i*50,j*50,null);
				}
		g.setColor(Color.RED);
		
		g.drawImage(ch.getImage(), ch.getX()*50, ch.getY()*50, this);
		g.drawImage(en.getImage(),en.getX()*50,en.getY()*50,this);
		g.drawImage(cu.getImage(),cu.getX()*50, cu.getY()*50, this);
		g.drawString(String.valueOf(ch.getHealth()),ch.getX()*50 + 10, ch.getY()*50 + 60);
		g.drawString(String.valueOf(en.getHealth()), en.getX()*50 + 10, en.getY()*50 + 60);
		
		if(en.attacking == true){
			g.drawString(String.valueOf(en.damage),ch.getX()*50,ch.getY()*50);
		}
		if(ch.attacking == true){
			g.drawString(String.valueOf(ch.damage),en.getX()*50,en.getY()*50);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		
	}


	
	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e){
			ch.keyReleased(e);
			cu.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e){
			if (cu.getSelected() == false){
				cu.keyPressed(e);
			} else{
				cu.keyPressed(e);
				ch.keyPressed(e);
				
			}
		}
	}
	
	public void lose(){
		System.out.println("You lose!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void win(){
		System.out.println("You win!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);   
		
	}
}
	
