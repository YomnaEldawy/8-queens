package state;

import java.util.ArrayList;

public class State {
	
	boolean[][] board; // a cell with 1 means a queen, 0 means no queen
	int costToReach; // number of nodes visited from initial state
	
	State(boolean[][] board, int costToReach){
		this.board = board;
		this.costToReach = costToReach;
	}
	
	//TODO: get all possible next states. any queen can move up, down, left or right
	ArrayList<State> getNextStates(){
		
		return null;
	}
}
