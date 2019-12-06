package algorithms;

import java.util.ArrayList;

public interface IAlgorithm {
	int getRunTime(); // returns running time in milliseconds
	int[][] getFinalState(); // returns the final board (1 = queen, 0 = no queen)
	int getCost(); // number of nodes from initial states to final state
	int getExpandedNodes(); // number of nodes expanded
	ArrayList<boolean[][]> getPath(); // path from initial state to final state
}
