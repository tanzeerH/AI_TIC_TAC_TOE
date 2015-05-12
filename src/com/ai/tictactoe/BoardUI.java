package com.ai.tictactoe;


import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BoardUI extends JFrame{
	private int BOARD_SIZE=3;
	public BoardUI() {
		super("Tic Tac Toe");
		setLayout(new GridLayout(3,3));
		for(int i=0;i<BOARD_SIZE*BOARD_SIZE;i++)
		{
			add(new JButton(""+i));
		}
	
		
		
	}

}
