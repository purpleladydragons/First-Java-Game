package com.rogue.like;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Character {
	Image image;
	Image still;
	Image attack;
	int x,y,dx,dy,movecountx,movecounty,healthtimer;
	int health, damage;
	boolean moving,attacking,dead;
	
	public Character(){
		ImageIcon ii = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/character.png");
		ImageIcon iib = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/attack.png");
		still=ii.getImage();
		attack = iib.getImage();
		image = still;
		moving = false;
		x = 0;
		y = 0;
 		int level = 1;
		health = 500;
		int strength = 3;
		int speed = 5;
		movecountx = 0;
		movecounty = 0;
		healthtimer = 0;
	}
	
	public void move(){
		x+= dx;
		y+= dy;
		if (dx != 0 || dy != 0){
			moving = true;
		}	
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
	
	public boolean getMoving(){
		return moving;
	}
	
	public void setMoveCountX(int x){
		movecountx = x;
	}
	
	public void setMoveCountY(int y){
		movecounty = y;
		
	}
	
	public boolean getAttacking(){
		return attacking;
	}
	
	public void checkHealth(){
		if(health < 1){
			die();
		}
	}
	
	public int getHealth(){
		return health;
	}
	
	public int genDamage(){
		damage = 1 + (int)(Math.random() * 50);
		return damage;
	}
	
	public void die(){
		image = null;
		attacking = false;
		dead = true;
	}
	
	public boolean getDead(){
		return dead;
	}

	public void attack(Enemy e){
		if (attacking == true){
			if ((Math.abs(e.getX() - x) < 2) && ((Math.abs(e.getY() - y) < 2))){
				e.health-= genDamage();
				e.checkHealth();
			}
		}
	}
	
	public void addHealth(int x){
		healthtimer++;
		if(health < 500 && healthtimer % 12 == 0)
			health += x;
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		if (dx == 0 && dy == 0){
			if(attacking == false){
				if (key == KeyEvent.VK_LEFT && x > 0 /*&& movecountx > -3*/){
					movecountx--;
					dx = -1;
				}
				if (key == KeyEvent.VK_RIGHT && x < 7 /*&& movecountx < 3*/){
					movecountx++;
					dx = 1;
				} 
			
				if (key == KeyEvent.VK_UP && y > 0 /*&& movecounty > -3*/){
					movecounty--;
					dy = -1; //y axis starts from top left; i.e making value more negative goes up not down
				}
				
				if (key == KeyEvent.VK_DOWN && y < 7 /*&& movecounty < 3*/){
					movecounty++;
					dy = 1;
				} 
			}
			
			if (key == KeyEvent.VK_SPACE){
				attacking = true; 
				image = attack;
			}
		}
	}


	public void keyReleased(KeyEvent e){
		moving = false;
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
			attacking = false;
			image = still;
		}
		
	
		
	}
	
	
}
