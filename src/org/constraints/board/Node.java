package org.constraints.board;

import java.util.ArrayList;


public class Node {
	
	//fields
	private int row;
	private int variable;
	private ArrayList<Integer> domain;
	private boolean visited;
	
	
	//default constructor
	public Node() {
		row = 0;
		variable = 0;
		domain = new ArrayList<Integer>();
		visited = false;
	}
	
	//parameterized constructor
	public Node(int row, int col) {
		this.row = row;
		variable = col;
		visited = false;
	}
	
	//getters and setters
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public int getVariable() {
		return variable;
	}

	public void setVariable(int variable) {
		this.variable = variable;
	}

	public ArrayList<Integer> getDomain() {
		return domain;
	}

	public void setDomain(ArrayList<Integer> domain) {
		this.domain = domain;
	}
	
	public void removeDomain(int col) {
		domain.remove(col);
	}
	
	
}
