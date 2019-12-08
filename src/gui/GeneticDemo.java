package gui;

import algorithms.Genetic;
import state.State;

public class GeneticDemo {
	public static void main(String[] args) {
		State s1 = randomValid();
		System.out.println("Initial state:");
		s1.printBoard();
		Genetic g = new Genetic(s1);
		g.search();
		State f = g.getFinalState();
		System.out.println("final state");
		f.printBoard();
		System.out.println(f.getStateCost());
		System.out.println(f.getCostToReach());
		
	}
	
	public static State randomValid() {
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
}
