package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import state.State;
import state.StateComparator;

public class HillClimbing implements IAlgorithm {
	State current;
	long totalTime;
	int expandedNodes = 0;
	ArrayList<State> path = new ArrayList<State>();
	
	public HillClimbing(State initial) {
		this.current = initial;
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
		return current;
	}

	@Override
	public int getCost() {
		return current.getCostToReach();
	}

	@Override
	public int getExpandedNodes() {
		// TODO Auto-generated method stub
		return expandedNodes;
	}

	public void search() {
		int maxIterations = 10000;
		int iterations = 0;
		int sidewayMoves = 0;
		while (iterations++ < maxIterations && current.getStateCost() > 0) {
			ArrayList<State> next = current.getNextStates();
			expandedNodes += next.size();
			Collections.sort(next, new StateComparator());
			System.out.println(current.getStateCost());
			if (next.get(0).getStateCost() < current.getStateCost()) {
				current = next.get(0);
				sidewayMoves = 0;
			} else if (next.get(0).getStateCost() == current.getStateCost() && sidewayMoves < 10) {
				System.out.println("Moving sideway");
				sidewayMoves++;
				current = next.get(0);
			} else {
				System.out.println("Starting over!");
				current = State.random();
				path = new ArrayList<State>();
			}
			path.add(current);
		}
		System.out.println("Iterations = " + iterations);
	}

	@Override
	public ArrayList<State> getPath() {
		// TODO Auto-generated method stub
		return path;
	}
}
