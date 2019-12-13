package state;

import java.util.ArrayList;

public class State {

	boolean[][] board; // a cell with 1 means a queen, 0 means no queen

	public int getCostToReach() {
		return costToReach;
	}

	public int[] getRowIndex() {
		return rowIndex;
	}

	public int[] getColumnIndex() {
		return columnIndex;
	}

	public boolean[][] getBoard() {
		return board;
	}

	/*
	 * returns a string containing rowIndex elements concatenated with colIndex used
	 * to hash a state
	 */
	public String getEquivalentString() {
		String s = "";
		for (int n : rowIndex)
			s = s + n;
		for (int n : columnIndex)
			s = s + n;
		return s;
	}

	int costToReach; // number of nodes visited from initial state
	int[] rowIndex;
	int[] columnIndex;

	public State(int[] rows, int[] columns, int cost) {
		this.rowIndex = rows;
		this.columnIndex = columns;
		this.costToReach = cost;
		fillBoard();
	}

	/**
	 * @return all possible next states by moving each queen in all 8 different
	 *         directions (horizontally, vertically and diagonally)
	 */
	public ArrayList<State> getNextStates() {
		ArrayList<State> next = new ArrayList<State>();
		for (int i = 0; i < 8; i++) {
			State s = null;
			for (int i2 = rowIndex[i] - 1; i2 <= rowIndex[i] + 1; i2++) {
				for (int j2 = columnIndex[i] - 1; j2 <= columnIndex[i] + 1; j2++) {
					if (i2 == rowIndex[i] && j2 == columnIndex[i])
						continue;
					s = moveOneQueen(i2, j2, i);
					if (s != null) {
						next.add(s);
					}
				}
			}
		}
		return next;
	}

	/**
	 * positions a queen in a different cell
	 * 
	 * @param i2    index of the new row
	 * @param j2    index of the new column
	 * @param index index of the queen in rowIndex and colIndex arrays
	 * @return
	 */
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

	/**
	 * @return number of attacking pairs
	 */
	public int getStateCost() {
		int attackingPairs = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = i + 1; j < 8; j++) {
				// System.out.println("Comparing: ");
				// System.out.println(rowIndex[i] + ", " + columnIndex[i] + " and " +
				// rowIndex[j] + ", " + columnIndex[j]);
				if (rowIndex[i] == rowIndex[j] // same row
						|| columnIndex[i] == columnIndex[j] // same column
						|| Math.abs(rowIndex[j] - rowIndex[i]) == Math.abs(columnIndex[j] - columnIndex[i]) // same
																											// diagonal
				) {
					// System.out.println("attacking pairs = " + attackingPairs);
					attackingPairs++;
				}
			}
		}
		return attackingPairs;
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

	public static State random() {
		boolean[][] board = new boolean[8][8];
		int[] rows = new int[8];
		int[] cols = { 0, 1, 2, 3, 4, 5, 6, 7 };
		for (int i = 0; i < 8; i++) {
			int rand;
			do {
				rand = (int) (Math.random() * 8);
			} while (board[rand][i]);
			rows[i] = rand;
			board[rand][i] = true;
		}
		return new State(rows, cols, 0);
	}

}