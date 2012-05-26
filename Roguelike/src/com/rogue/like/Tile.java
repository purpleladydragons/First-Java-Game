package com.rogue.like;

import java.awt.Image;

public class Tile {
	boolean passable;
	Image image;
	
	public Tile(){
		int a = (int)(1+Math.random()*10);
		if (a < 7){passable = true;}
	}

}
