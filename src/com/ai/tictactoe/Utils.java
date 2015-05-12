package com.ai.tictactoe;

import java.util.ArrayList;

public class Utils {

	int PLUS_INIFINITY = 999999;
	int MINUS_INFINITY = -999999;
	int PC_VALUE=0;
	int HUMAN_VALUE=1;
	int size = 3;
	boolean found=false;
	ArrayList<Integer> moveList=new ArrayList<Integer>();
	public int ALPHA_BETA_SEARCH(State state) {
 
		MAX_VALUE(state,MINUS_INFINITY, PLUS_INIFINITY);
		int v=MAX_VALUE(state, MINUS_INFINITY, PLUS_INIFINITY);
		State nextSate=null;
		for(int i=0;i<state.getChildList().size();i++)
		{
			if(state.getChildList().get(i).getEvaluationVal()==v)
			{
				
				nextSate=state.getChildList().get(i);
				break;
			}
			System.out.println("utils: "+state.getChildList().get(i).getEvaluationVal());
			state.getChildList().get(i).printBoard();
		}
		int move=getMovePosition(state,nextSate);
		System.out.println("row: "+ move/size + "col: "+move%size+ "value: " + v);
		return move;
	}
	private int getMovePosition(State parent,State nextState)
	{
		if(nextState==null)
		{
			System.out.println("next state null");
			return -1;
		}
		int [][] parentBlock=parent.getBlocks();
		int [][] childBlocks=nextState.getBlocks();
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
				if(parentBlock[i][j]!=childBlocks[i][j])
				{
					int x=i*size+j;
					if(!isAlreadyInTheList(x))
					{
					moveList.add(x);
					return x;
					}
				}
				
		}
		return -1;
	}
	private boolean isAlreadyInTheList(int val)
	{
		for(int i=0;i<moveList.size();i++)
		{
			if(moveList.get(i)==val)
				return true;
		}
		return false;
	}

	private int MAX_VALUE(State state, int alpha, int beta) {
		if(isTerminal(state))
			return state.getEvaluationValue();
		int v=MINUS_INFINITY;
		ArrayList<State> list=state.getActionStates(PC_VALUE);
		for(int i=0;i<list.size();i++)
		{
			state.getChildList().add(list.get(i));
			int val=MIN_VALUE(list.get(i), alpha, beta);
			v=getMax(v,val);
			if(v>=beta)
			{
				state.setEvaluationValue(v);
				return v;
			}
			alpha=getMax(alpha, v);
		}
		state.setEvaluationValue(v);
		return v;
	}
	private int MIN_VALUE(State state, int alpha, int beta) {
		if(isTerminal(state))
			return state.getEvaluationValue();
		int v=PLUS_INIFINITY;
		ArrayList<State> list=state.getActionStates(HUMAN_VALUE);
		for(int i=0;i<list.size();i++)
		{
			state.getChildList().add(list.get(i));
			int val=MAX_VALUE(list.get(i), alpha, beta);
			v=getMin(v,val);
			if(v<=alpha)
			{ state.setEvaluationValue(v);
				return v;
			}
			beta=getMin(beta, v);
		}
		state.setEvaluationValue(v);
		return v;
			
	}
	private int getMin(int a,int b)
	{
		if(a>b)
			return b;
		else
			return a;
	}
	private int getMax(int a,int b)
	{
		if(a>b)
			return a;
		else
			return b;
	}
	public boolean isTerminal(State state)
	{
		if(isAllMovesDone(state) || isWon(state))
			return true;
		else
			return false;
					 
	}
	private boolean isAllMovesDone(State state) {
		int block[][] = state.getBlocks();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				if (block[i][j] == -1)
					return false;
			}
		return true;
	}
	

	private boolean isWon(State state) {
		if (winForRow(state, 0) || winForColumn(state, 0) || winForCross1(state, 0) || winForCross2(state, 0)
				|| winForRow(state, 1) || winForColumn(state, 1) || winForCross1(state, 1) || winForCross2(state, 1)) {
			return true;
		}
		else
			return false;
	}

	private boolean winForRow(State state, int value) {
		int block[][] = state.getBlocks();
		for (int i = 0; i < size; i++) {
			boolean win = true;
			for (int j = 0; j < size; j++) {
				if (block[i][j] != value)
					win = false;

			}
			if (win)
			{ 
				//System.out.println("win for row"+value);
				return true;
			}
		}
		return false;
	}

	private boolean winForColumn(State state, int value) {
		int block[][] = state.getBlocks();
		for (int i = 0; i < size; i++) {
			boolean win = true;
			for (int j = 0; j < size; j++) {
				if (block[j][i] != value)
					win = false;

			}
			if (win)
			{
				//System.out.println("win for col"+value);
				return true;
			}
		}
		return false;
	}

	private boolean winForCross1(State state, int value) {
		int block[][] = state.getBlocks();
		boolean win = true;
		for (int i = 0; i < size; i++) {

			if (block[i][i] != value)
				win = false;

		}
		if (win)
		{
			//System.out.println("win for cross1"+value);
			return true;
		}

		return false;
	}

	private boolean winForCross2(State state, int value) {
		int block[][] = state.getBlocks();
		int l = 0;
		int m = size - 1;
		boolean win = true;
		for (int i = 0; i < size; i++) {

			if (block[l][m] != value)
				win = false;
			l++;
			m--;

		}
		if (win)
		{
			//System.out.println("win for coross2"+value);
			return true;
		}

		return false;
	}
}
