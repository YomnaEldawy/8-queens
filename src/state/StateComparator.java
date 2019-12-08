package state;

import java.util.Comparator;

/**
 * This class could be used when creating a priority queue.
 * peek element will contain the state with minimum number of attacking pairs
 * @author Yomna
 *
 */

public class StateComparator implements Comparator<State> {

	@Override
	public int compare(State s1, State s2) {
		if (s1.getStateCost() > s2.getStateCost())
			return 1;
		if (s1.getStateCost() < s2.getStateCost())
			return -1;
		return 0;
	}

}