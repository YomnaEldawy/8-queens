package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import state.State;

public class Genetic implements IAlgorithm {

	State current;
	
	PriorityQueue<State> fringe = new PriorityQueue<State>(1, new StateComparator());
	
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
		while (current.getStateCost() > 0) {
			
		}
	}
	
	private State crossOver(State s1, State s2) {
		
		return null;
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

