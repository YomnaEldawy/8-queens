package gui;

import algorithms.Genetic;
import state.State;

public class GeneticDemo {
	public static void main(String[] args) {
		State s1 = State.random();
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
}
