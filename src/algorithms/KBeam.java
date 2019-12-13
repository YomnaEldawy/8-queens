package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import state.State;
import state.StateComparator;


public class KBeam implements IAlgorithm {
	PriorityQueue <State>  queue ;
	private ArrayList<State> Kboard = null;
	private int X; // number of iterations
	private int K; // number of initial states
	private boolean isDone = false;
	private int iterations = 0;
	private int bestEverScore=Integer.MAX_VALUE;
	private ArrayList<State> IndexesOfFirstBoards ;
	private int takenTime = 0;
	private State finalState;
	HashSet<State> explored = new HashSet<State>();
	HashSet<String> visited = new HashSet<String>();
	private int expandedNodes = 0; 
	
	public KBeam(int X, int K) {
		queue = new PriorityQueue<State>(1,new StateComparator()); 
		IndexesOfFirstBoards = new ArrayList<State>();
		this.X = X;
		this.K = K;
		Kboard = new ArrayList<State>();
		solve();
	}
	
	private void solve(){
		long startTime = System.currentTimeMillis();
		System.out.println("iteration "+ iterations);
		//get first k randomly generated states
		generateFirstIterationBoards(); 
		//get the successors of the first k generated states
		generateSuccessors();
		while (iterations<X && !isDone)
		{ 
			/*if we didn't get the goal 
			 * from the first generated successors
			 * then pick the best k states and get its successors
			 * repeat until reach the goal 
			 * or number of iterations finishes
			 */
			Kboard = pickBestKs();
			generateSuccessors();
		}
		long endTime   = System.currentTimeMillis();
		takenTime = (int) (endTime-startTime);
		expandedNodes = explored.size();
		if(!isDone)	
			System.out.println("Fail ! Couldnt Solve in "+ iterations+ " Iterations, Best Score Was "+bestEverScore);
		System.out.println("Time taken(Milliseconds): " + (endTime-startTime)+ " ,No of expanded nodes: "+expandedNodes+" ,cost to the final board: "+ (iterations+1));

	}
	
	public  ArrayList<State> pickBestKs() {
		if(!isDone)
		{
			iterations++;
			System.out.println("iteration "+ iterations);
			Kboard = new ArrayList<State>();//holds K Best Boards
			for(int i=0;i<K;i++)
			{
				Kboard.add(queue.peek());
				System.out.println( "Chosen Board Number "+(i+1));
				queue.peek().printBoard();
				System.out.println(">>Score : "+queue.peek().getStateCost());
				queue.poll();
			}
		}
		return Kboard;	}

	public void generateSuccessors() {
		queue.clear(); //remove the last successors
		ArrayList<State> neighbors;
		for (int i = 0; i < K; i++) {
			neighbors = Kboard.get(i).getNextStates();
			for (int j = 0; j < neighbors.size(); j++) {
				int score = neighbors.get(j).getStateCost();
				if (score < bestEverScore) bestEverScore = score;
				if(score == 0){
                	isDone=true;
					System.out.println("Solution"+" ( "+iterations+ " iterations " +") " +" and result is :");
					neighbors.get(j).printBoard();
					finalState = neighbors.get(j);
					System.out.println(">>Score: "+score);
					break;                                
				    
                }
				queue.add(neighbors.get(j));
				explored.add(neighbors.get(j));
				visited.add(neighbors.get(j).getEquivalentString());
			}
		}
	}

	public void generateFirstIterationBoards() {
		while (IndexesOfFirstBoards.size() < K) {
			State s;
			do {
				s = State.random();
			}while(visited.contains(s.getEquivalentString()));
			IndexesOfFirstBoards.add(s);
			Kboard.add(s);
			visited.add(s.getEquivalentString());
		}
		System.out.println("First generation");
	}
	
	@Override
	public int getRunTime() {
		// TODO Auto-generated method stub
		return takenTime;
	}

	@Override
	public State getFinalState() {
		// TODO Auto-generated method stub
		return finalState;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return iterations+1;
	}

	@Override
	public int getExpandedNodes() {
		// TODO Auto-generated method stub
		return expandedNodes;
	}

	@Override
	public ArrayList<State> getPath() {
		// TODO Auto-generated method stub
		return null;
	}

}