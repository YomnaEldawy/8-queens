package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import state.*;
import state.State.*;

public class KBeam implements IAlgorithm {
	PriorityQueue <State>  queue ;
	private ArrayList<State> Kboard = null;
	private int X; // number of iterations
	private int K; // number of initial states
	private boolean isDone = false;
	private int iterations = 0;
	private int bestEverScore=Integer.MAX_VALUE;
	private ArrayList<State> IndexesOfFirstBoards;
	HashSet<String> visitedBoards = new HashSet<String>();
	private int takenTime = 0;
	private State finalState;
	HashSet<State> explored = new HashSet<State>();
	private int expandedNodes = 0; 
	
	public KBeam(int X, int K) {
		queue = new PriorityQueue<State>(1,new StateComparator()); 
		IndexesOfFirstBoards = new ArrayList<State>();
		this.X = X;
		this.K = K;
		Kboard = new ArrayList<State>();
	}
	
	public void solve(){
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
		State newQueenBoard = null; //create new successor of k board 
		if(!isDone){
			for(int i = 0;i<K;i++){
				for(int j =0;j<8;j++){
					for(int x = 0;x<8;x++){
						if(!isDone){
							if(!Kboard.get(i).getBoard()[x][j]){
								State cur = Kboard.get(i);
								int[] rows = cur.getRowIndex().clone();
								int[] cols = cur.getColumnIndex().clone();
								newQueenBoard = new State(rows, cols, cur.getCostToReach()+1);
								int score = newQueenBoard.getStateCost();
                                if(score < bestEverScore)
                                	bestEverScore = score;
                                if(score == 0){
                                	isDone=true;
									System.out.println("Solution"+" ( "+iterations+ " iterations " +") " +" and result is :");
									newQueenBoard.printBoard();
									finalState = newQueenBoard;
									System.out.println(">>Score: "+score);
									break;                                
								    
                                }
							
							queue.add(newQueenBoard);
							explored.add(newQueenBoard);

							}
							
						}
					}
				}
			}
			
		}
	}

	public void generateFirstIterationBoards() {
		for(int i =0;i<K;i++){
			boolean flag = true;
			while(flag){
				State rand;
				do{
					rand = State.random();
				}while (visitedBoards.contains(rand.getEquivalentString()));
				Kboard.add(rand);
				IndexesOfFirstBoards.add(rand);
//				Kboard.get(Kboard.size()-1).generateQueenBoard();
//				//makes sure that the randomly generated board isn't equal to another randomly generated board
//				if(!(indexesEqual(Kboard.get(Kboard.size()-1).getIndexes())))
//				{
//					flag = false;
//					IndexesOfFirstBoards.add(Kboard.get(Kboard.size()-1));//add to list of Indexes the new Indexes of new unique board
//				}
			}
			System.out.println("First Step - Board number "+(i+1));
			int score = Kboard.get(Kboard.size()-1).getStateCost();
			Kboard.get(Kboard.size()-1).printBoard();
			System.out.println(">>Score : "+score);
			explored.add(Kboard.get(Kboard.size()-1));
			if(score==0){
				System.out.println(" finished after First K board creation "+ iterations + " iterations");
				Kboard.get(Kboard.size()-1).printBoard();
				finalState = Kboard.get(Kboard.size()-1);
				System.out.println(">>Score : "+score);
				isDone=true;
				break;
			}
		}
	}
	
//	private boolean indexesEqual(State s) {
//		return visitedBoards.contains(s.getEquivalentString());
//	}
//
//	private boolean isEqual(MyPair[] myPairs, MyPair[] indexes) {
//		for(int i =0;i<indexes.length;i++)
//		{
//			//one mismatch is enough for not being equal
//			if(myPairs[i].key()!=indexes[i].key())
//			{    		
//				return false;
//			}
//		}		return true;
//	}

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