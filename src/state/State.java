package state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class State {

	boolean[][] board; // a cell with 1 means a queen, 0 means no queen
//    MyPair[] indexes ;	
//	public MyPair[] getIndexes() {
//		return indexes;
//	}
//
//	public void setIndexes(MyPair[] indexes) {
//		this.indexes = indexes;
//	}
//
//	public class MyPair
//	{
//		private final int i;
//		private final int j;
//
//		public MyPair(int aKey, int aValue)
//		{
//			i   = aKey;
//			j = aValue;
//		}
//
//		public int key()   { return i; }
//		public int value() { return j; }
//	}
//	public State(){
//		indexes = new MyPair[8];
//	}

	public boolean[][] getBoard() {
		return board;
	}

	public void setBoard(boolean[][] board) {
		this.board = board;
	}

	public int getCostToReach() {
		return costToReach;
	}

	public void setCostToReach(int costToReach) {
		this.costToReach = costToReach;
	}

	public int[] getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int[] rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int[] getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int[] columnIndex) {
		this.columnIndex = columnIndex;
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

	// TODO: get all possible next states. each queen can move up, down, left, right
	// or diagonally
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
		int attackingPairs = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = i + 1; j < 8; j++) {
				//System.out.println("Comparing: ");
				//System.out.println(rowIndex[i] + ", " + columnIndex[i] + " and " + rowIndex[j] + ", " + columnIndex[j]);
				if (rowIndex[i] == rowIndex[j] // same row
						|| columnIndex[i] == columnIndex[j] // same column
						|| Math.abs(rowIndex[j] - rowIndex[i]) == Math.abs(columnIndex[j] - columnIndex[i]) // same
																											// diagonal
				)
				{
					//System.out.println("attacking pairs = " + attackingPairs);
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
		int[] rows = new int[8], cols = new int[8];
		for (int i = 0; i < 8; i++) {
			int rand1 = (int) (Math.random() * 8);
			int rand2 = (int) (Math.random() * 8);
			while (board[rand1][rand2]) {
				rand1 = (int) (Math.random() * 8);
				rand2 = (int) (Math.random() * 8);
			}
			rows[i] = rand1;
			cols[i] = rand2;
			board[rand1][rand2] = true;
		}
		return new State(rows, cols, 0);
	}
//	//kBeam functions
//	//generate random board
//	public void generateQueenBoard()
//	{
//		Random rand = new Random();
//		this.rowIndex = new int[8];
//		this.columnIndex= new int[8];
//		board = new boolean[8][8];
//		for(int i=0;i<8;i++)
//		{
//			int n = rand.nextInt(8);
//			rowIndex[i] = n;
//			columnIndex[i] = i;
//			board[n][i] = true;
//			indexes[i] = new MyPair(n,i);
//		}
//
//	}
//    //generate new successor by moving a queen on the board
//	public void generateQueenBoardFromGivenBestBoard( int j, int x)
//	{
//		board[indexes[j].i][indexes[j].j]=false;//Second board replaces at his own board ,at specific column, 1 to be 0
//		board[x][j]= true;//complete replacement of 1
//		indexes[j] = new MyPair(x,j);//save that index for later
//		columnIndex[j] = j;
//		rowIndex[j] = x;
//	}
//	
//	public void copy(State oldQueenBoard) {
//		this.board = new boolean[8][8];
//		for (int i = 0; i < oldQueenBoard.board.length; i++) {
//			this.board[i] = Arrays.copyOf(oldQueenBoard.board[i], oldQueenBoard.board[i].length);
//
//		}
//
//		this.indexes = oldQueenBoard.indexes.clone();
//		this.rowIndex = oldQueenBoard.rowIndex.clone();
//		this.columnIndex = oldQueenBoard.columnIndex.clone();
//	}
//	
	
}