package state;

import java.util.Comparator;


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