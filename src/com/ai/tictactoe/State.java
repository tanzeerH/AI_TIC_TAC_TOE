package com.ai.tictactoe;

import java.util.ArrayList;

public class State {

	int size=3;
	int pc_value=0;
	private int blocks [][]=new int[3][3];
	private ArrayList<State> childList=new ArrayList<State>();
	private int evaluationValue=-999999;
	public State(int [][] blocks) {
		this.blocks=blocks;
	}
	public int[][] getBlocks()
	{
		return blocks;
	}
	public ArrayList<State> getChildList()
	{
		return this.childList;
	}
	public int getEvaluationVal()
	{
		return this.evaluationValue;
	}
	public void setEvaluationValue(int val)
	{
		this.evaluationValue=val;
	}
	public int getEvaluationValue() {
		//printBoard();
		
		int v=getColumnValue()+getRowValue()+getCross1Value()+getCross2Value();
		//System.out.println("value: "+v);
		this.evaluationValue=v;
		return v;
	}
	private int getColumnValue()
	{
		int value = 0;

		for (int i = 0; i < size; i++) {
			int empty = 0;
			int pc = 0;
			int hum = 0;
			for (int j = 0; j < size; j++) {
				if (blocks[i][j] == -1)
					empty++;
				else if (blocks[i][j] == 0)
					pc++;
				else if (blocks[i][j] == 1)
					hum++;
			}
			if (pc == 3)
				value += 100;
			else if (pc == 2 && empty == 1)
				value += 10;
			else if (pc == 1 && empty == 2)
				value += 1;
			else if (hum == 3)
				value -= 100;
			else if (hum == 2 && empty == 1)
				value -= 10;
			else if (hum == 1 && empty == 2)
				value -= 1;

		}
		
		
		return value;
	}
	private int getRowValue()
	{
		int value = 0;

		for (int i = 0; i < size; i++) {
			int empty = 0;
			int pc = 0;
			int hum=0;
			for (int j = 0; j < size; j++) {
				if (blocks[j][i] == -1)
					empty++;
				else if (blocks[j][i] == 0)
					pc++;
				else if (blocks[j][i] == 1)
					hum++;
			}
			if (pc == 3)
				value += 100;
			else if (pc == 2 && empty == 1)
				value += 10;
			else if (pc == 1 && empty == 2)
				value += 1;
			else if (hum == 3)
				value -= 100;
			else if (hum == 2 && empty == 1)
				value -= 10;
			else if (hum == 1 && empty == 2)
				value -= 1;

		}
		
		
		return value;
	}

	private int getCross1Value() {
		int value = 0;

		int empty = 0;
		int pc = 0;
		int hum = 0;
		for (int j = 0; j < size; j++) {
			if (blocks[j][j] == -1)
				empty++;
			else if (blocks[j][j] == 0)
				pc++;
			else if (blocks[j][j] == 1)
				hum++;
		}
		if (pc == 3)
			value += 100;
		else if (pc == 2 && empty == 1)
			value += 10;
		else if (pc == 1 && empty == 2)
			value += 1;
		else if (hum == 3)
			value -= 100;
		else if (hum == 2 && empty == 1)
			value -= 10;
		else if (hum == 1 && empty == 2)
			value -= 1;

		return value;

	}
	private int getCross2Value() {
		int value = 0;
		int l = 0;
		int m = size - 1;
		int empty = 0;
		int pc = 0;
		int hum = 0;
		for (int j = 0; j < size; j++) {
			if (blocks[j][j] == -1)
				empty++;
			else if (blocks[j][j] == 0)
				pc++;
			else if (blocks[j][j] == 1)
				hum++;
			l++;
			m--;
		}
		if (pc == 3)
			value += 100;
		else if (pc == 2 && empty == 1)
			value += 10;
		else if (pc == 1 && empty == 2)
			value += 1;
		else if (hum == 3)
			value -= 100;
		else if (hum == 2 && empty == 1)
			value -= 10;
		else if (hum == 1 && empty == 2)
			value -= 1;

		return value;

	}
	public void printBoard() {
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
				System.out.print(" "+blocks[i][j]);
			System.out.println("");
			
		}
		System.out.println("");
	}
	public ArrayList<State> getActionStates(int moveValue)
	{
		ArrayList<State> actionList=new ArrayList<State>();
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				if(blocks[i][j]==-1)
				{
					int b[][]=copyBoard();
					b[i][j]=moveValue;
					State state=new State(b);
					actionList.add(state);
					
				}
			}
		return actionList;
	}

	private int [][] copyBoard() {
		int [][] temlBlocks=new int[size][size];
		for (int i = 0; i <size; i++)
			for (int j = 0; j <size; j++)
				temlBlocks[i][j] =blocks[i][j];
		return temlBlocks;

	}
}
