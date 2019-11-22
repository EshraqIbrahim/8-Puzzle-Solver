import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Gameboard implements Comparable<Gameboard>{
	int[][] currentGameBoard;
	Gameboard parent;
	String gameBoard = "";
	int cost = 0;
	int heuristicType = 1;
	int heuristicOneValue = 0;
	int heuristicTwoValue = 0;
	int totalCost = 0;
	Map<Integer,Point> goalSet =  new HashMap<Integer,Point>();
	
	public Gameboard(String gameBoard,int cost,int heuristicType,Gameboard parent){
		goalSet.put(1, new Point(0,0)); 
		goalSet.put(2, new Point(0,1)); 
		goalSet.put(3, new Point(0,2)); 
		goalSet.put(4, new Point(1,0)); 
		goalSet.put(5, new Point(1,1)); 
		goalSet.put(6, new Point(1,2)); 
		goalSet.put(7, new Point(2,0)); 
		goalSet.put(8, new Point(2,1)); 
	
		this.gameBoard  = gameBoard;
		this.cost = cost;
		this.heuristicType = heuristicType;
		this.parent = parent;
		setGameBoard();
		calculateManhattanheuristic();
		calculateEuclideanHeuristic();
		calculateTotalCost(heuristicType);
	}
	
	void setGameBoard() {
		currentGameBoard = new int[3][3];
		int counter  = 0;
		for(int i = 0 ; i < 3; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				currentGameBoard[i][j] = Character.getNumericValue(gameBoard.charAt(counter));
//				System.out.println("Counter"+counter);
//				System.out.println(gameBoard);

				counter++;
			}
		}
	}
	
	void calculateManhattanheuristic() {
		for(int i = 0 ; i < currentGameBoard.length ; i++) {
			for(int j = 0 ; j < currentGameBoard.length;j++) {
				if(currentGameBoard[i][j] != 0) {
					Point goal = goalSet.get(currentGameBoard[i][j]);
					heuristicOneValue += Math.abs(i - goal.x) - Math.abs(j - goal.y);
				}
			}
		}
	}
	
	void calculateEuclideanHeuristic() {
		for(int i = 0 ; i < currentGameBoard.length ; i++) {
			for(int j = 0 ; j < currentGameBoard.length;j++) {
				if(currentGameBoard[i][j] != 0) {
					Point goal = goalSet.get(currentGameBoard[i][j]);
					heuristicTwoValue += Math.sqrt(Math.pow(i - goal.x, 2) - Math.pow(j - goal.y,2));
				}
			}
		}
	}
	
	void calculateTotalCost(int heuristicType) {
		if(heuristicType == 1) {
			totalCost = cost + heuristicOneValue;
		}else {
			totalCost = cost + heuristicTwoValue;
		}
	}
	
	public String getStringRepresentation() {
	
		return gameBoard;
		
	}
	
	public int getTotalCost() {
		return totalCost;
	}
	
	public int getHeuristicValue(int heuristicType) {
		if(heuristicType == 1) {
			return heuristicOneValue;
		}else {
			return heuristicTwoValue;
		}
	}
	
	public int[][] getCurrentGameBoard() {
		return currentGameBoard;
	}
	
	@Override
	public int compareTo(Gameboard other) {
		// Returns the comparison of f(n) from both puzzles.
		// Necessary for implementing the priority queue heuristics.
		int priority1 = 0;
		int priority2 = 0;
		if(heuristicType == 1) {
			priority1 = cost + heuristicOneValue ;
			priority2 = other.cost + other.heuristicOneValue;
		}else {
			priority1 = cost + heuristicTwoValue ;
			priority2 = other.cost + other.heuristicTwoValue;
		}
	
		
		if (priority1 < priority2) {
			return -1;
		} else if (priority1 > priority2) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
