package gui;

import java.util.ArrayList;
import java.util.Scanner;

import algorithms.*;
import state.State;
public class Demo {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int[] rows = new int[8];
		int[] columns = new int[8];
		for (int i = 0; i < 8; i++) {
			System.out.println("Please enter the index of queen " + i + ": (row then column)");
			int row = 0, column = 0;
			row = input.nextInt();
			column = input.nextInt();
			if (row < 0 || row > 7 || column < 0 || column > 7)
				i--;
			else {
				rows[i] = row;
				columns[i] = column;
			}
		}
		
		/**
		 * testing state neighbors
		 */
		State s = new State(rows, columns, 0);
		System.out.println("initial");
		s.printBoard();
		System.out.println();
		s.getNextStates();
		
		/*
		 * This should be the way we call any algorithm
		 */
		String algorithmName;
		System.out.println("Enter the algorithm name");
		algorithmName = input.nextLine();
		// TODO: switch case and create an algorithm instance accordingly
		// for example, this is similar to what will be written in case of CSP
		IAlgorithm algorithm = new CSP(s);
		System.out.println(algorithm.getRunTime());
		System.out.println(algorithm.getCost());
		System.out.println(algorithm.getExpandedNodes());
		input.close();
	}

}
