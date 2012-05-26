package com.rogue.like;

import javax.swing.*;

public class Frame extends JFrame{

	
	public Frame(){
		add(new Board());
		setTitle("Chipendalia");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,428);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args){
		new Frame();
	}

}
