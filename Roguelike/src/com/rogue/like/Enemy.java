package com.rogue.like;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Enemy {
	Image still;
	Image attack;
	Image image;
	int x,y,dx,dy;
	int health;
	int damage;
	int turn;
	boolean attacking;
	boolean dead;
	
	
	public Enemy(){
		ImageIcon ii = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/estill.png");
		ImageIcon iib = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/eattack.png");
		still = ii.getImage();
		attack = iib.getImage();
		image = still;
		x = 6;
		y = 6;
		health = 1000;
		attacking = true;
		
	}
	
	
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImage(){
		return image;
	}
	
	public void checkHealth(){
		if (health < 1){
			die();
		}
	}
	
	public int getHealth(){
		return health;
	}
	
	public void die(){
		image = null;
		attacking = false;
		dead = true;
	}
	
	public boolean getDead(){
		return dead;
	}
	
	public int genDamage(){
		damage = 1 + (int)(Math.random() * 50);
		return damage;
	}
	
	public void attack(Character c){
		if ((Math.abs(c.getX() - x) < 2 )&& (Math.abs(c.getY() - y) < 2) && attacking == true){
			image = attack;
			c.health-= genDamage();
			c.checkHealth();
			
			}
		}
	
	public void move(int cx, int cy){ //need to sleep/timer this so that it can't buttrape everything
		
		turn++;
		
		if (turn % 15 == 0 && turn % 45 != 0){
			if(cx < x){
				
				x--;
			} else if(cx > x){
				x++;
			}
			
			if(cy < y){
				y--;
			} else if(cy > y) {
				y++;
			}
		}
	}

	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		if (attacking == false){
			if (key == KeyEvent.VK_LEFT ){
				dx = -1;
			}
			if (key == KeyEvent.VK_RIGHT ){
				dx = 1;
			} 
			
			if (key == KeyEvent.VK_UP){
				dy = -1; //y axis starts from top left; i.e making value more negative goes up not down
			}
			
			if (key == KeyEvent.VK_DOWN ){
				dy = 1;
			} 
		}
		
		if (key == KeyEvent.VK_SPACE){
			image = attack;
		}
		
		
		
	}
	
	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){
			dx = 0;
		}
		if (key == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
		if (key == KeyEvent.VK_UP){
			dy = 0;
		}
		
		if (key == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		if(key == KeyEvent.VK_SPACE){
			image = still;
		}
	
		
	}
	
}
