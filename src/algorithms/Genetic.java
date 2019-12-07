package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import state.State;

public class Genetic implements IAlgorithm {

	State current;
	
	PriorityQueue<State> fringe = new PriorityQueue<State>(1, new StateComparator());
	HashMap<String, Boolean> isVisited;
	public Genetic(State initial) {
		this.current = initial;
		fringe.add(current);
		search();
	}
	@Override
	public int getRunTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[][] getFinalState() {
		// TODO Auto-generated method stub
		return null;
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
			ArrayList<State> allStates = new ArrayList<State>(fringe);
			int index1 = (int) (Math.random() * allStates.size());
			int index2 = (int) (Math.random() * allStates.size());
			State newBorn = crossOver(allStates.get(index1), allStates.get(index2));
			String s = newBorn.getEquivalentString();
			if (isVisited.get(s)) continue;
			isVisited.put(s, true);
			fringe.add(newBorn);
			if (iterations++ > 1000000) break;
		}
	}
	
	private State crossOver(State s1, State s2) {
		int[] rows1 = s1.getRowIndex();
		int[] cols1 = s1.getColumnIndex();
		int[] rows2 = s2.getRowIndex();
		int[] cols2 = s2.getColumnIndex();
		int[] rows = new int[8], cols = new int[8];
		boolean[][] containsQueen = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
			if (Math.random() > 0.5 || containsQueen[rows2[i]][cols2[i]]) {
				rows[i] = rows1[i];
				cols[i] = cols1[i];
			}else {
				rows[i] = rows2[i];
				cols[i] = cols2[i];
			}
			containsQueen[rows[i]][cols[i]] = true;			
		}
		State s = new State(rows, cols, max(s1.getCostToReach(), s2.getCostToReach()));
		return s;
	}
	
	int max (int n1, int n2) {
		return n1 > n2? n1: n2; 
	}
}

class StateComparator implements Comparator<State>{

	@Override
	public int compare(State s1, State s2) {
		if (s1.getStateCost() > s2.getStateCost())
			return 1;
		return -1;
	}
	
}

