package com.ai.tictactoe;

import javax.swing.JFrame;

public class TestClass {
	
	
	public static void main(String[] args) {
		BoardUI boardUI=new BoardUI();
		boardUI.setVisible(true);
		boardUI.setSize(500,500);
		boardUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
