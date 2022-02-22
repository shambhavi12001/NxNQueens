package org.constraints.board;

import java.util.*;

public class ForwardChecking {
	
	//variables
	private ArrayList<Node> squares = new ArrayList<Node>();
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Node> positions = new ArrayList<Node>(); // a list of positions
	private ArrayList<ArrayList<Node>> deleted;
	private String initialPos;
	private int backTrackForward;
	private int solutions = 0;
	private ArrayList<ArrayList<Node>> solutionList;
	private int breakLoop;
	private ArrayList<Integer> backTrackList;
	
	//default constructor
	public ForwardChecking() {
		squares = generateBoard(); //checks on the board
		nodes = generateNodes(); //nodes available to us
		deleted = new ArrayList<ArrayList<Node>>(); // node that need to be deleted (2D array format: rows/columns)
		initialPos = null;
		backTrackForward = 0;
		solutionList = new ArrayList<ArrayList<Node>>();
		backTrackList = new ArrayList<Integer>(); //keeps a track of back tracking

	}
	
	
	//getters and setters
	
	public int getBackTrackForward() {
		return backTrackForward;
	}

	public String getIntitialPos() {
		return initialPos;
	}

	public int getSolutions() {
		return solutions;
	}

	public void setSolutions(int solutions) {
		this.solutions = solutions;
	}

	public void setIntitialPos(String intitialPos) {
		this.initialPos = intitialPos; //takes the initial position
	}
	
	public ArrayList<Integer> getBackTrackList() {
		return backTrackList;
	}

	public void setBackTrackList(ArrayList<Integer> backTrackList) {
		this.backTrackList = backTrackList;
	}

	public ArrayList<ArrayList<Node>> getSolutionList() {
		return solutionList;
	}

	//function to generate the board to keep a track of the domain of all the variables
	public ArrayList<Node> generateBoard() {
		
		ArrayList<Node> checks = new ArrayList<Node>();
	
		for(int i = 1; i <= 8; ++i) {
			Node square = new Node();
			square.setRow(i);
			square.setDomain(new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8)));
			checks.add(square);
		}
		
		return checks;
		
	}
	
	//to keep a track of all the possible checks in the board
	public ArrayList<Node> generateNodes() {
		
		ArrayList<Node> checks = new ArrayList<Node>();
	
		for(int i = 1; i <= 8; ++i) {
			for(int j = 1; j <= 8; ++j) {
				Node node = new Node(i, j);
				checks.add(node);
			}
		}
		return checks;
	}
	
	public Node assignInitialNode(String initialPos) {
		Node initialNode = new Node(Integer.parseInt(initialPos.substring(0, 1)), ((int)initialPos.charAt(1))-64);
		
		return initialNode;
		
	}
	
	
	//recursive backtracking
	public void backTrack(Node node) {
		this.breakLoop = 0;
		this.backTrackForward++;
		for(Node deletedNode : deleted.get(deleted.size()-1)) {
			if((squares.get(deletedNode.getRow()-1).getDomain().size() == 0))
			{
				squares.get(deletedNode.getRow()-1).getDomain().add(deletedNode.getVariable());
			}
			else if(squares.get(deletedNode.getRow()-1).getDomain().get(squares.get(deletedNode.getRow()-1).getDomain().size() - 1) < deletedNode.getVariable())
			{
				squares.get(deletedNode.getRow()-1).getDomain().add(deletedNode.getVariable());
			}
			else
			{
				for(int i : squares.get(deletedNode.getRow()-1).getDomain()) {
					if(i > deletedNode.getVariable()) {
						squares.get(deletedNode.getRow()-1).getDomain().add(squares.get(deletedNode.getRow()-1).getDomain().indexOf(i), deletedNode.getVariable());
						break;
					}
				}
				
			}
			if(nodes.size() == 0){
				nodes.add(deletedNode);
			}
			else if((nodes.get(nodes.size() - 1).getRow() * 8 + nodes.get(nodes.size() - 1).getVariable()) < (deletedNode.getRow() * 8 + deletedNode.getVariable())) {
				nodes.add(deletedNode);
			}
			else {
				for(Node n : nodes) {
					if((n.getRow() * 8 + n.getVariable()) > (deletedNode.getRow() * 8 + deletedNode.getVariable())) {
						nodes.add(nodes.indexOf(n), deletedNode);
						break;
					}
				}
			}
		}
		for(Node deletedNode : deleted.get(0)) {
			if(deletedNode.getRow() == 2) {
				breakLoop ++;
			}
		}
		if(!(squares.get(1).getDomain().size() == 0 && breakLoop == 8))
		{
			for(Node check : deleted.get(deleted.size() - 2)){
				if(check.getVariable() > node.getVariable()) {
					
					nodes.remove(check);
					squares.get(node.getRow()-1).getDomain().removeAll(Collections.singleton(node.getVariable()));
					break;
				}
			}
			deleted.remove(deleted.size() - 1);
			squares.get(positions.size()-1).getDomain().removeAll(Collections.singleton(node.getVariable()));
			positions.remove(node);

			node = positions.get(positions.size()-1);
			while(checkDomain() == true) {
	
				if(squares.get(1).getDomain().size() == 0 && breakLoop == 8) { //condition which checks that there are no further solutions left
					
					break;
				}
				backTrack(node);

			}
		}
	}
	
	//when a variable is assigned a value it removes all the nodes that can't have a queen assigned to them and limits the domain
	public void removeNodes(Node node) { 
		for(Node check : nodes) {
			if((!(check.getRow() == node.getRow() && check.getVariable() == node.getVariable())) &&
					(check.getRow() == node.getRow() || check.getVariable() == node.getVariable() || 
					(Math.abs(check.getRow() - node.getRow()) == Math.abs(check.getVariable() - node.getVariable())))) {
			
				if(deleted.size() < positions.size()){
					deleted.add(new ArrayList<Node>());
					deleted.get(deleted.size()-1).add(check);
					
				}
				else {
					deleted.get(deleted.size()-1).add(check);
				
				}
	
				squares.get(check.getRow()-1).getDomain().removeAll(Collections.singleton(check.getVariable()));
				
			}
		}
		for(Node deletedNode : deleted.get(deleted.size()-1)) {
			nodes.remove(deletedNode);
		}
		if(deleted.size() != positions.size()) {
			deleted.add(new ArrayList<Node>());
		}
		if(deleted.size() > 1) {
			deleted.get(deleted.size() - 2).add(node);
			for(Node check : deleted.get(deleted.size() - 2)){
				if(check.getVariable() > node.getVariable()) {
					nodes.remove(check);
					if(!(positions.contains(node))) {
						squares.get(node.getRow()-1).getDomain().removeAll(Collections.singleton(node.getVariable()));
					}
					break;
				}
			}
			
		}

		
	}
	
	public Node assignNode(int row) {
		Node node = new Node(row, squares.get(row - 1).getDomain().get(0));
		return node;
	}
	
	public boolean checkDomain() {
		boolean isEmpty = false;
		for(Node check : squares) {
			if(check.getDomain().size() == 0) {
				isEmpty = true;
				return isEmpty;
			}
		}
		return isEmpty;
	}
	
	public void run_forward_checking(String initialPos) {
		while(true) {
			forwardChecking(initialPos);
			if(positions.size() == 8) {
				printSolutions();
				this.backTrackList.add(this.backTrackForward);
				solutions++;
			}
			if(squares.get(1).getDomain().size()==0 && breakLoop == 8) {
				break;
			}
			backTrack(positions.get(positions.size()-1));

			initialPos = positions.get(positions.size()-1).getRow()+1 + "" + ((char)(squares.get(positions.size()).getDomain().get(0) + 64));
			
		}
			
	}
	
	public void printSolutions() {
		this.solutionList.add(this.solutions, new ArrayList<Node>());
			for(Node pos : positions) {
				this.solutionList.get(this.solutions).add(pos);
			}
		
	}

	public void forwardChecking(String initialPos) {
		
		Node node = assignInitialNode(initialPos);
		int i = 0;
		while(positions.size() < 8) {
			i = 0;
			node.setVisited(true);
			positions.add(node);
			removeNodes(node);
			for(Node n : nodes) {
				
				if(n.getRow() == node.getRow() && n.getVariable() == node.getVariable()) {
					break;
				}
				i++;
			}
			if(i < nodes.size()) {
				nodes.remove(i);
			}
			
			while((checkDomain() == true) && !(squares.get(1).getDomain().size() == 0 && breakLoop == 8)) {
				backTrack(node);
			}
			if(positions.size() == 8 || nodes.size() == 0) {
				break;
			}
			if(squares.get(1).getDomain().size() == 0 && breakLoop == 8) {
				break;
			}
			if(squares.get(positions.size()).getDomain().size()>0) {
				node = assignNode(positions.size() + 1);	
			}
			
		}
		
	}

}
