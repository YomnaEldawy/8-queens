package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import state.State;

public class Genetic implements IAlgorithm {

	State current;

	PriorityQueue<State> fringe = new PriorityQueue<State>(5, new StateComparator());
	HashSet<State> visited = new HashSet<State>();
	public Genetic(State initial) {
		//this.current = initial;
		//fringe.add(current);
		ArrayList<State> neighbors = initial.getNextStates();
		fringe.addAll(neighbors);
		visited.addAll(neighbors);
		search();
	}

	@Override
	public int getRunTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public State getFinalState() {
		// TODO Auto-generated method stub
		return fringe.peek();
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExpandedNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<State> getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	private void search() {
		int iterations = 0;
		while (fringe.peek().getStateCost() > 0) {
			iterations++;
			ArrayList<State> allStates = new ArrayList<State>(fringe);
			int index1 = (int) (Math.pow(Math.random(), 2) * allStates.size());
			int index2 = (int) (Math.pow(Math.random(), 2) * allStates.size());
			State newBorn = crossOver(allStates.get(index1), allStates.get(index2));
			if (visited.contains(newBorn)) continue;
			fringe.add(newBorn);
			if (iterations >= 100000)
				break;
		}
	}

	private State crossOver(State s1, State s2) {
		int[] rows1 = s1.getRowIndex();
		int[] cols1 = s1.getColumnIndex();
		int[] rows2 = s2.getRowIndex();
		int[] cols2 = s2.getColumnIndex();
		int[] rows = new int[8], cols = new int[8];
		boolean[][] containsQueen = new boolean[8][8];
//		System.out.println("mating: ");
//		s1.printBoard();
//		System.out.println("and");
//		s2.printBoard();
		for (int i = 0; i < 8; i++) {
			if (Math.random() > 0.5 && !containsQueen[rows1[i]][cols1[i]]) {
				rows[i] = rows1[i];
				cols[i] = cols1[i];
			} else {
				rows[i] = rows2[i];
				cols[i] = cols2[i];
			}
			containsQueen[rows[i]][cols[i]] = true;
		}
		State s = new State(rows, cols, max(s1.getCostToReach(), s2.getCostToReach()));
		return s;
	}

	int max(int n1, int n2) {
		return n1 > n2 ? n1 : n2;
	}
}

class StateComparator implements Comparator<State> {

	@Override
	public int compare(State s1, State s2) {
		if (s1.getStateCost() > s2.getStateCost())
			return 1;
		if (s1.getStateCost() < s2.getStateCost())
			return -1;
		return 0;
	}

}
