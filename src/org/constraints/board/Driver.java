package org.constraints.board;

import java.util.*;

public class Driver {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the position of a queen: ");
		String position = sc.nextLine();
		
		ForwardChecking b = new ForwardChecking();
		b.run_forward_checking(position);
		//b.printSolutions();
		DAC d = new DAC();
		d.run_dac(position);
		System.out.println("\nQueen 1 in column " + position + ":\n");
		
		for(ArrayList<Node> solution : b.getSolutionList()) {
			if(b.getSolutionList().get(0).size() != 8) {
				System.out.println("No Solutions");
				break;
			}
			
			if(solution.size() == 8) {
				System.out.println("\nSolution " + (b.getSolutionList().indexOf(solution) + 1) + " with queen 1 in " + position + ": ");
				System.out.println("The positions of the Queens are: \n");
				
				for(Node pos : solution) {
					System.out.println("Row" + pos.getRow() + ": " + pos.getRow() + "" + ((char)(pos.getVariable() + 64)));
				}
			}
			
			System.out.println("Total numbers of backtracks before this solution was "
					+ "found:\nForward Checking: " + b.getBackTrackList().get(b.getSolutionList().indexOf(solution))
					+"\nDirectional Look Ahead: " + d.getBackTrackList().get(b.getSolutionList().indexOf(solution)));
			
					
		}
		System.out.println("\nTotal numbers of backtracks before all solutions having a "
				+ "queen in " + position + " was found (or found there are no solutions): \n"
				+ "Forward Checking: " + b.getBackTrackForward()
				+ "\nDirectional Look Ahead: " + d.getBackTrackDirectional());
		sc.close();
		
	}

}
