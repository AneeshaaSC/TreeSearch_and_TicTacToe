import java.util.Arrays;

// Class to do the basic movement of up, down, left and right for any given node

public class Move {

	private Node doMovement(Node n, int[] newAgentPosition) {
		Node x = null;
		if(Arrays.equals(n.getState().getPositionA(), newAgentPosition)) {
			State s = new State(n.getState().getGridSize(), n.getState().getPositionAgent(), n.getState().getPositionB(), n.getState().getPositionC(), newAgentPosition, n.getState().getBlockedTiles());
			x = new Node(s, n, n.getLevel() + 1, n.getCost() + 1);
		} else if(Arrays.equals(n.getState().getPositionB(), newAgentPosition)) {
			State s = new State(n.getState().getGridSize(), n.getState().getPositionA(), n.getState().getPositionAgent(), n.getState().getPositionC(), newAgentPosition, n.getState().getBlockedTiles());
			x = new Node(s, n, n.getLevel() + 1, n.getCost() + 1);
		} else if(Arrays.equals(n.getState().getPositionC(), newAgentPosition)) {
			State s = new State(n.getState().getGridSize(), n.getState().getPositionA(), n.getState().getPositionB(), n.getState().getPositionAgent(), newAgentPosition, n.getState().getBlockedTiles());
			x = new Node(s, n, n.getLevel() + 1, n.getCost() + 1);
		} else {
			State s = new State(n.getState().getGridSize(), n.getState().getPositionA(), n.getState().getPositionB(), n.getState().getPositionC(), newAgentPosition, n.getState().getBlockedTiles());
			x = new Node(s, n, n.getLevel() + 1, n.getCost() + 1);
		}
		return x;
	}
	

	// Move Left. Current state is fed to the method,upon which the movement needs to be done
	// -1 is to simulate leftward movement. New Node returned as result of the movement

	public Node moveLeft(Node n) {
		if(n.getState().getPositionAgent()[0] - 1 >= 1 && !checkInterference(n.getState().getPositionAgent()[0] - 1, n.getState().getPositionAgent()[1], n.getState().getBlockedTiles())) {
			return doMovement(n, new int[] {n.getState().getPositionAgent()[0] - 1, n.getState().getPositionAgent()[1]});
		} else {
			return null;
		}		
	}
	
	
	// Move Down. Current state is fed to the method,upon which the movement needs to be done
	// +1 is to simulate leftward movement. New Node returned as result of the movement
	
	public Node moveDown(Node n) {
		if(n.getState().getPositionAgent()[1] + 1 <= n.getState().getGridSize()[1] && !checkInterference(n.getState().getPositionAgent()[0], n.getState().getPositionAgent()[1] + 1, n.getState().getBlockedTiles())) {
			return doMovement(n, new int[] {n.getState().getPositionAgent()[0], n.getState().getPositionAgent()[1] + 1});
		} else {
			return null;
		}		
	}
	
 
	// Move Right. Current state is fed to the method,upon which the movement needs to be done
	// +1 is to simulate leftward movement. New Node returned as result of the movement
	
	public Node moveRight(Node n) {
		if(n.getState().getPositionAgent()[0] + 1 <= n.getState().getGridSize()[0] && !checkInterference(n.getState().getPositionAgent()[0] + 1, n.getState().getPositionAgent()[1], n.getState().getBlockedTiles())) {
			return doMovement(n, new int[] {n.getState().getPositionAgent()[0] + 1, n.getState().getPositionAgent()[1]});
		} else {
			return null;
		}		
	}
	
// Movement of blocks

	public Node moveUp(Node n) {
		if(n.getState().getPositionAgent()[1] - 1 >= 1 && !checkInterference(n.getState().getPositionAgent()[0], n.getState().getPositionAgent()[1] - 1, n.getState().getBlockedTiles())) {
			return doMovement(n, new int[] {n.getState().getPositionAgent()[0], n.getState().getPositionAgent()[1] - 1});
		} else {
			return null;
		}		
	}
	
//Checks if a coordinate interferes with the blocked tiles

	private boolean checkInterference(int x, int y, int[][] unreachableCoords) {
		boolean flag= false;
		if(unreachableCoords != null) {
			for(int[] coord : unreachableCoords) {
				if(x == coord[0] && y == coord[1]) {
					flag = true;
					break;
				}
			}
		}		
		return flag;
	}
}