package gui;

import algorithms.HillClimbing;
import state.State;

public class HillClimbingDemo {

	public static void main(String[] args) {
		State init = State.random();
		System.out.println("Initially");
		init.printBoard();
		HillClimbing hc = new HillClimbing(init);
		System.out.println("Finally");
		hc.getFinalState().printBoard();
		System.out.println(hc.getFinalState().getStateCost());

	}

}
