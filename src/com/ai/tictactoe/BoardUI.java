package com.ai.tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BoardUI extends JFrame implements ActionListener {
	private int BOARD_SIZE = 3;
	private JButton btnStart;
	private JButton btnResult;
	private JButton btnMoveState;
	int STATE = 0;
	int PC_STATE = 0;
	int HUMAN_STATE = 1;
	int blocks[][] = new int[3][3];
	Utils utils = new Utils();
	ArrayList<JButton> buttonList = new ArrayList<JButton>();

	public BoardUI() {
		super("Tic Tac Toe");

		btnStart = new JButton("Start Game");
		btnMoveState = new JButton("Move For X");
		btnResult = new JButton("Result");
		setLayout(new GridLayout(4, 3));
		for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
			JButton tempButton = new JButton("-");
			tempButton.addActionListener(this);
			buttonList.add(tempButton);
			add(tempButton);
		}
		add(btnMoveState);
		add(btnStart);
		add(btnResult);

		initBoard();

	}

	private void initBoard() {
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				blocks[i][j] = -1;
		State state = new State(blocks);
		int move = utils.ALPHA_BETA_SEARCH(state);
		buttonList.get(move).setText("X");
		int row = move / BOARD_SIZE;
		int colum = move % BOARD_SIZE;
		blocks[row][colum] = STATE;
		STATE = HUMAN_STATE;

	}

	private void printBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++)
				System.out.print(" " + blocks[i][j]);
			System.out.println("");

		}
		System.out.println("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMoveState) {

		} else if (e.getSource() == btnStart) {

		} else if (e.getSource() == btnResult) {

		} else {
			String text = ((JButton) (e.getSource())).getText().toString();

			if (text.equals("-")) {
				int pos = getButtonPosition(((JButton) (e.getSource())));
				if (pos != -1) {
					int row = pos / BOARD_SIZE;
					int colum = pos % BOARD_SIZE;
					blocks[row][colum] = STATE;
				}
				/*
				 * //printBoard(); State state = new State(blocks);
				 * //System.out.println("" + utils.isTerminal(state));
				 * utils.ALPHA_BETA_SEARCH(state);
				 */
				if (STATE == PC_STATE) {
					((JButton) (e.getSource())).setText("X");
					STATE = HUMAN_STATE;
				} else {

					((JButton) (e.getSource())).setText("O");
					STATE = PC_STATE;
					// printBoard();
					State state = new State(blocks);
					if (utils.winForHuman(state)) {
						JOptionPane.showMessageDialog(null, "HUMAN Won");
						state.printBoard();
					} else if (utils.isAllMovesDone(state)) {
						JOptionPane.showMessageDialog(null, "Match Draw.");
						state.printBoard();
					} else {
						int move = utils.ALPHA_BETA_SEARCH(state);

						if (move != -1) {
							buttonList.get(move).setText("X");
							int row = move / BOARD_SIZE;
							int colum = move % BOARD_SIZE;
							blocks[row][colum] = STATE;
							state = new State(blocks);
							if (utils.winForPC(state)) {
								JOptionPane.showMessageDialog(null, "PC Won");
								state.printBoard();
							} else if (utils.isAllMovesDone(state)) {
								JOptionPane.showMessageDialog(null, "Match Draw.");
								state.printBoard();
							}
						} else
							System.out.println("error");
						STATE = HUMAN_STATE;
					}
				}

			} else
				JOptionPane.showMessageDialog(null, "Illegal move");

		}

	}

	private int getButtonPosition(JButton btn) {
		int s = buttonList.size();
		for (int i = 0; i < s; i++) {
			if (buttonList.get(i) == btn)
				return i;
		}
		return -1;
	}

}
