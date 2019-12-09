package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import state.State;
import state.StateComparator;

public class HillClimbing implements IAlgorithm{
	State current;

	public HillClimbing(State initial) {
		this.current = initial;
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
		return current;
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

	public void search() {
		int maxIterations = 50000;
		int iterations = 0;
		while (iterations++ < maxIterations && current.getStateCost() > 0) {
			ArrayList<State> next = current.getNextStates();
			Collections.sort(next, new StateComparator());
			System.out.println(current.getStateCost());
			if (next.get(0).getStateCost() >= current.getStateCost()) {
				System.out.println("Starting over!");
				//int index = (int) (Math.random() * next.size());
				current = State.random();
				// current = next.get(index);
			}else {
				current = next.get(0);
			}
		}
		System.out.println(iterations);
	}

	@Override
	public ArrayList<State> getPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
