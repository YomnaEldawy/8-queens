package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import state.State;
import state.StateComparator;

public class Genetic implements IAlgorithm {

	State current;

	PriorityQueue<State> fringe = new PriorityQueue<State>(1, new StateComparator());
	HashSet<String> visited = new HashSet<String>();

	public Genetic(State initial) {
		fringe.add(initial);
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

	public void search() {
		int iterations = 0;
		while (fringe.peek().getStateCost() > 0 && ++iterations < 90000) {
			if (fringe.size() < 100) {
				State s1 = randomValid(), s2 = randomValid();
				if (!visited.contains(s1)) {
					visited.add(s1.getEquivalentString());
					fringe.add(s1);
				}
				if (!visited.contains(s2)) {
					visited.add(s2.getEquivalentString());
					fringe.add(s2);
				}
			}
			ArrayList<State> statesList = new ArrayList<State>(fringe);
			int limit = statesList.size();
			int index1 = (int) (Math.pow(Math.random(), 3) * limit);
			int index2 = (int) (Math.pow(Math.random(), 3) * limit);
			State child = crossOver(statesList.get(index1), statesList.get(index2));
			if (!visited.contains(child.getEquivalentString())) {
				fringe.add(child);
				visited.add(child.getEquivalentString());
			}
		}
		System.out.println("iterations = " + iterations);
	}

	public State crossOver(State s1, State s2) {
		int[] newRows = new int[8], newCols = new int[8];
		boolean[][] board = new boolean[8][8];
		int[] r1 = s1.getRowIndex(), c1 = s1.getColumnIndex();
		int[] r2 = s2.getRowIndex(), c2 = s2.getColumnIndex();
		for (int i = 0; i < 8; i++) {
			double probability = Math.random();
			if (probability > 0.5 && !board[r1[i]][c1[i]]) {
				newRows[i] = r1[i];
				newCols[i] = c1[i];
			} else {
				newRows[i] = r2[i];
				newCols[i] = c2[i];
			}
			while (board[newRows[i]][newCols[i]]) {
				newRows[i] = (int) (Math.random() * 8);
				newCols[i] = (int) (Math.random() * 8);
			}
			board[newRows[i]][newCols[i]] = true;
		}
		State child = new State(newRows, newCols, 1 + max(s1.getCostToReach(), s2.getCostToReach()));
		return child;
	}

	int max(int n1, int n2) {
		return n1 > n2 ? n1 : n2;
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
