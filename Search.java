import java.util.*;

/* this Class has the main method to run the different search algorithms */

public class Search {

	public static void main(String[] args) {
		
		//feed in info on gridSize(4x4), position of block A, position of block B, position of block C, position of  Agent
		// passed into state class - initial state information
		// making sure block positions are close to Goal state for BFS, else it throws out of memory error
		
		//State start = new State(new int[] {4,4}, new int[] {2,2}, new int[] {2,3}, new int[] {1,4}, new int[] {3, 3});
		
		//State goal = new State(null, new int[] {2,2}, new int[] {2,3}, new int[] {2, 4}, null);
		State start = new State(new int[] {4,4}, new int[] {1,2}, new int[] {1,3}, new int[] {4, 3}, new int[] {2, 1});
		State goal = new State(null, new int[] {2,2}, new int[] {2,3}, new int[] {2, 4}, null);
		
		Node node = new Node(start);
		
		Search search = new Search();
//		Runs search methods	
//		search.BFS(node, goal);
		search.DFS(node, goal);
//		search.IDS(node, goal);
// 		search.aStar(node, goal);
	}
	
	//Depth-First Search

	public void DFS(Node root, State goal) 
	{
		int nodesExpanded = 0;
		// Stack is a Last In First Out (LIFO) data structure, to keep track of DFS frontier
		Stack<Node> stack = new Stack<>();

		// root is the initial or start state
		System.out.println("Start Depth-First Search with initial state:\n" + root.getState());
		stack.add(root);		// to expand root 
		while(!stack.isEmpty()) 
		{
			ArrayList<Node> successors = new ArrayList<>();
			
			//get node from stack. last node is popped or read first, it is a stack data structure
			Node current = stack.pop();
			
			//Check if we're at the goal state
			if(Goal_test(current.getState(), goal)) 
			{
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished Depth-First Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:\n");
				/*
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				*/
				break;
			}
			//Expands Node if solution not found. Branches are going to be all possible moves from current state
			nodesExpanded++;
			successors = Add_child_nodes(current);
			//Add successors to the stack (randomly for DFS)
			Collections.shuffle(successors);
			for(Node child : successors) {
				if(child != null) {
					stack.add(child);
				}				
			}
		}
	}
	
	//Breadth-First Search
	
	public void BFS(Node root, State goal) {
		int nodesExpanded = 0;
		// queue to keep track of frontier
		Queue<Node> queue = new LinkedList<Node>();

		// root is nothing but the initial or start state
		System.out.println("Start Breadth-First Search with initial state:\n" + root.getState());
		queue.add(root);
		while(!queue.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			//Remove queue head
			Node current = queue.remove();
			//Check if the solution is found
			if(Goal_test(current.getState(), goal)) {
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished Breadth-First Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:\n");
				/*
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				*/
				break;
			}
			//Expands Node if solution not found
			nodesExpanded++;			
			successors = Add_child_nodes(current);
			
			//Add successors to the queue
			for(Node child : successors) 
			{
				if(child != null) {
					queue.add(child);
				}				
			}
		}
	}
	
	//Iterative Deepening Search

	public void IDS(Node root, State goal) {
		//Initially set maxDepth to 0
		int maxDepth = 0, nodesExpanded = 0;
		// stack to keep track of frontier
		Stack<Node> stack = new Stack<>();

		// root is nothing but the initial or start state
		System.out.println("Start Iterative Deepening Search with initial state:\n" + root.getState());
		stack.add(root);		
		while(!stack.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			//Pop from top of the stack
			Node current = stack.pop();
			//Checks if the popped Node is a solution
			if(Goal_test(current.getState(), goal)) {
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished Iterative Deepening Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:");
				/*
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				*/
				break;
			//Expands Node if the depth of the Node is less than the maxDepth
			} else if(current.getLevel() < maxDepth)
			{
				nodesExpanded++;
				successors = Add_child_nodes(current);
				for(Node child : successors) {
					if(child != null) {
						stack.add(child);
					}				
				}
			}
			//If stack size = 0, meaning that there is no solution for the current maxDepth, increase value of maxDepth by 1 
			//and adds the root to the stack so the search can be started again with a higher maximum depth
			if(stack.size() == 0) 
			{
				stack.push(root);
				maxDepth++;
			}	
			
		}		
	}
	
	
	// A* Search

	public void aStar(Node root, State goal) 
	{
		int nodesExpanded = 0;
		// PriorityQueue to keep track of frontier, and select cheapest node to expand first
		PriorityQueue<Node> pri_queue = new PriorityQueue<>();

		// root is nothing but the initial or start state
		System.out.println("Start A* Search with initial state:\n" + root.getState());
		pri_queue.add(root);
		while(!pri_queue.isEmpty()) 
		{
			ArrayList<Node> successors = new ArrayList<>();
			//Remove queue head
			Node current = pri_queue.poll();
			//Check if head of the queue is the solution
			if(Goal_test(current.getState(), goal)) 
			{
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished A* Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:");				
				/*
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				*/
				break;
			}
			//Expands Node if solution not found
			nodesExpanded++;
			successors = Add_child_nodes(current);

			//Add successors to the queue
			for(Node child : successors)
			{
				if(child != null) 
				{
					child.calculateCostUsingHeuristic(goal);
					pri_queue.add(child);
				}				
			}
		}
	}
	
	// Perform goal test- to see if desired end state is reached

	private boolean Goal_test(State current, State goal) {
		if(Arrays.equals(current.getPositionA(), goal.getPositionA()) && Arrays.equals(current.getPositionB(), goal.getPositionB()) && Arrays.equals(current.getPositionC(), goal.getPositionC())) 
		{
			return true;
		} else 
		{
			return false;
		}
	}
	
	public ArrayList<Node> Add_child_nodes(Node current_node)
	{
		ArrayList<Node> child_nodes = new ArrayList<>();
		Move m = new Move();
		child_nodes.add(m.moveUp(current_node));
		child_nodes.add(m.moveDown(current_node));
		child_nodes.add(m.moveLeft(current_node));
		child_nodes.add(m.moveRight(current_node));
		return child_nodes;
	}
}