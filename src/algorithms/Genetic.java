package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import state.State;
import state.StateComparator;

public class Genetic implements IAlgorithm {

	long totalTime = 0;
	PriorityQueue<State> fringe = new PriorityQueue<State>(1, new StateComparator());
	HashSet<String> visited = new HashSet<String>();
	int expandedNodes = 0;

	public Genetic() {
		long start = System.currentTimeMillis();
		search();
		totalTime = System.currentTimeMillis() - start;
	}

	@Override
	public int getRunTime() {
		return (int) totalTime;
	}

	@Override
	public State getFinalState() {
		return fringe.peek();
	}

	@Override
	public int getCost() {
		return fringe.peek().getCostToReach();
	}

	@Override
	public int getExpandedNodes() {
		return expandedNodes;
	}

	@Override
	public ArrayList<State> getPath() {
		return null;
	}

	int trials = 0;

	private void search() {
		int iterations = 0;
		for (int i = 0; i < 50; i++) {
			State s1 = State.random();
			if (!visited.contains(s1.getEquivalentString())) {
				visited.add(s1.getEquivalentString());
				fringe.add(s1);
			}
		}
		while (fringe.peek().getStateCost() > 0 && ++iterations < 60000) {
			ArrayList<State> statesList = new ArrayList<State>(fringe);
			int limit = statesList.size();
			int index1 = (int) (Math.pow(Math.random(), 3) * limit);
			int index2 = (int) (Math.pow(Math.random(), 3) * limit);
			ArrayList<State> children = crossOver(statesList.get(index1), statesList.get(index2));
			for (int i = 0; i < 2; i++) {
				if (!visited.contains(children.get(i).getEquivalentString())) {
					fringe.add(children.get(i));
					visited.add(children.get(i).getEquivalentString());
					expandedNodes++;
				}
			}
		}
		System.out.println("iterations = " + iterations);
		if (trials++ < 3 && fringe.peek().getStateCost() > 0) {
			fringe.clear();
			visited.clear();
			System.out.println("Attempt " + trials + " failed, starting over");
			search();
		}

	}

	private ArrayList<State> crossOver(State s1, State s2) {
		int[] newRows = new int[8], newCols = new int[8];
		int[] newRows2 = new int[8], newCols2 = new int[8];
		boolean[][] board = new boolean[8][8];
		boolean[][] board2 = new boolean[8][8];
		int[] r1 = s1.getRowIndex(), c1 = s1.getColumnIndex();
		int[] r2 = s2.getRowIndex(), c2 = s2.getColumnIndex();
		for (int i = 0; i < 8; i++) {
			double probability = Math.random();
			if (probability > 0.5 && !board[r1[i]][c1[i]]) {
				newRows[i] = r1[i];
				newCols[i] = c1[i];
				newRows2[i] = r2[i];
				newCols2[i] = c2[i];
			} else {
				newRows[i] = r2[i];
				newCols[i] = c2[i];
				newRows2[i] = r1[i];
				newCols2[i] = c1[i];
			}
			while (board[newRows[i]][newCols[i]]) {
				newRows[i] = (int) (Math.random() * 8);
				newCols[i] = (int) (Math.random() * 8);
			}
			while (board2[newRows2[i]][newCols2[i]]) {
				newRows2[i] = (int) (Math.random() * 8);
				newCols2[i] = (int) (Math.random() * 8);
			}
			board[newRows[i]][newCols[i]] = true;
			board2[newRows2[i]][newCols2[i]] = true;
		}
		State child = new State(newRows, newCols, 1 + max(s1.getCostToReach(), s2.getCostToReach()));
		State child2 = new State(newRows2, newCols2, 1 + max(s1.getCostToReach(), s2.getCostToReach()));
		ArrayList<State> nextGeneration = new ArrayList<State>();
		nextGeneration.add(child);
		nextGeneration.add(child2);
		return nextGeneration;
	}

	private int max(int n1, int n2) {
		return n1 > n2 ? n1 : n2;
	}
}
