package com.rogue.like;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Cursor {
	Image image;
	Image yellow;
	Image blue;
	int x,y,dx,dy,selcount;
	boolean selected;
	boolean moving;
	
	public Cursor() {
		ImageIcon ii = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/cursor.png");
		ImageIcon iib = new ImageIcon("/home/austin/workspace/Roguelike/src/resources/cursorblue.png");
		yellow = ii.getImage();
		blue = iib.getImage();
		image = yellow;
		moving = false;
		x = 0;
		y = 0;
		selcount=0;
	}
	
	public void move(){
		
		x += dx;
		y += dy;
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
	
	public boolean getSelected(){
		return selected;
	}
	
	public boolean getMoving(){
		return moving;
	}
	
	public void select(Cursor cu, Character ch, int cux, int cuy, int chx, int chy){
		if (selected == true){
			image = blue;
			if((cux == chx) && (cuy == chy) && (ch.getMoving()==false)){
				ch.move();
				cu.x = ch.x;
				cu.y = ch.y;	
			}
		} else{
			ch.setMoveCountX(0);
			ch.setMoveCountY(0);
			image = yellow;
		}
	}
	
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		if ((dx == 0) && (dy == 0)){
			if (key == KeyEvent.VK_LEFT && x > 0){
				dx = -1;
			}
			if (key == KeyEvent.VK_RIGHT && x < 7){
				dx = 1;
			} 
			
			if (key == KeyEvent.VK_UP && y > 0){
				dy = -1; //y axis starts from top left; i.e making value more negative goes up not down
			}
			
			if (key == KeyEvent.VK_DOWN && y < 7){
				dy = 1;
			} 
			
			if (key == KeyEvent.VK_Z){
				selcount++;
				if (selcount % 2 != 0){
					selected = true;
					 
				} else {
					selected = false;
					
				}
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
		
		
		/*if (key == KeyEvent.VK_Z){
			selected = false;
		}*/
		
		
	
	}
	
}
