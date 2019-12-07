package state;

import java.util.ArrayList;

public class State {

	boolean[][] board; // a cell with 1 means a queen, 0 means no queen
	int costToReach; // number of nodes visited from initial state
	int[] rowIndex;
	int[] columnIndex;
	
	public State(int[] rows, int[] columns, int cost) {
		this.rowIndex = rows;
		this.columnIndex = columns;
		this.costToReach = cost;
		fillBoard();
	}

	// TODO: get all possible next states. each queen can move up, down, left, right
	// or diagonally
	public ArrayList<State> getNextStates() {
		ArrayList<State> next = new ArrayList<State>();
		for (int i = 0; i < 8; i++) {
			State s = null;
			for (int i2 = rowIndex[i] - 1; i2 <= rowIndex[i] + 1; i2++ ) {
				for (int j2 = columnIndex[i] - 1; j2 <= columnIndex[i] + 1; j2++) {
					if (i2 == rowIndex[i] && j2 == columnIndex[i]) continue;
					s = moveOneQueen(i2, j2, i);
				}
			}
			if (s != null) next.add(s);
		}
		return next;
	}

	private State moveOneQueen(int i2, int j2, int index) {
		if (i2 < 0 || i2 > 7 || j2 < 0 || j2 > 7 || board[i2][j2])
			return null;
		int[] tempRow = rowIndex.clone();
		int[] tempCol = columnIndex.clone();
		tempRow[index] = i2;
		tempCol[index] = j2;
		State next = new State(tempRow, tempCol, costToReach + 1);
		return next;
	}
	
	private void fillBoard() {
		this.board = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
			board[rowIndex[i]][columnIndex[i]] = true;
		}
	}

	// TODO:
	/**
	 * @return number of attacking pairs
	 */
	public int getStateCost() {
		return 0;
	}
	

	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j])
					System.out.print("Q");
				else
					System.out.print(".");
			}
			System.out.println();
		}
	}
}
