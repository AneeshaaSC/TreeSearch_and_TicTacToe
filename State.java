// State class created to implement the basic structure of a State of the Puzzle

public class State {
	private int[] gridSize, positionA, positionB, positionC, positionAgent;
	private int[][] blockedTiles;
	
	// Create a State without blocked tiles

	public State(int[] gridSize, int[] positionA, int[] positionB, int[] positionC, int[] positionAgent) {
		this.gridSize = gridSize;
		this.positionA = positionA;
		this.positionB = positionB;
		this.positionC = positionC;
		this.positionAgent = positionAgent;
	}
	
	// Create a State without blocked tiles

	public State(int[] gridSize, int[] positionA, int[] positionB, int[] positionC, int[] positionAgent, int[]...blockedTiles) {
		this.gridSize = gridSize;
		this.positionA = positionA;
		this.positionB = positionB;
		this.positionC = positionC;
		this.positionAgent = positionAgent;
		this.blockedTiles = blockedTiles;
	}
	

	public int[] getGridSize() {
		return gridSize;
	}


	public int[] getPositionA() {
		return positionA;
	}


	public int[] getPositionB() {
		return positionB;
	}


	public int[] getPositionC() {
		return positionC;
	}


	public int[] getPositionAgent() {
		return positionAgent;
	}
	

	public int[][] getBlockedTiles() {
		return blockedTiles;
	}

// Code to print node in a grid
	
	public String toString() {		
		String output = "";
		
        for (int y = 1; y <= gridSize[0]; y++) { //columns =4
            for (int x = 1; x <= gridSize[1]; x++) { //rows=4
                if(x == positionA[0] && y == positionA[1]) {
                	output += "[A] ";
                } else if(x == positionB[0] && y == positionB[1]) {
                	output += "[B] ";
                } else if(x == positionC[0] && y == positionC[1]) {
                	output += "[C] ";
                } else if(x == positionAgent[0] && y == positionAgent[1]) {
                	output += "[@] ";
                } else {
                	boolean flag = false;
                	if(blockedTiles != null) {
                		for(int[] coord : blockedTiles) {
                    		if(coord[0] == x && coord[1] == y) {
                    			output += "[-] ";
                    			flag = true;
                    			break;
                    		}
                    	}
                	}
                	if(!flag) {
                		output += "[ ] ";
                	}                	
                }            	
            }
            output += "\n";
        } 
        return output;
	}
}