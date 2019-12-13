package gui;

import algorithms.Genetic;
import algorithms.HillClimbing;
import algorithms.IAlgorithm;
import algorithms.KBeam;
import state.State;

public class AlgorithmViewer {

	public static void main(String[] args) {
		IAlgorithm algor = new KBeam(1000000, 100);
		System.out.println("Final state:");
		//algor.getFinalState().printBoard();
		System.out.println("Expanded nodes: " + algor.getExpandedNodes());
		System.out.println("Running time: " + algor.getRunTime() + " ms");
		System.out.println("Cost: " + algor.getCost());

	}

}
