package algorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;

import state.State;

public class Genetic implements IAlgorithm {

	State current;
	PriorityQueue<State> fringe = new PriorityQueue<State>();
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
	public State getFinalState() {
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
		while (current.getStateCost() > 0) {
			
		}
	}

}
