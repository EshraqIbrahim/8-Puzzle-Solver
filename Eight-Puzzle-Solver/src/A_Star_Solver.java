import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class A_Star_Solver {
 public boolean A_Star(String startSet,String goalSet,int heursticType) {
	PriorityQueue<Gameboard> frontier = new PriorityQueue<Gameboard>();
	HashSet<String> exploredSet = new HashSet<String>();
	frontier.add(new Gameboard(startSet,0, heursticType,null));
	
	Gameboard current = null;
	long timeElapsed = 0;
	long timeStart = System.currentTimeMillis();
	int nodesExpanded = 0;
	int cost = 0;
	int searchDepth = 0;
	
	
	while (!frontier.isEmpty()) {
		current = frontier.remove();
		exploredSet.add(current.getStringRepresentation());
		String currentSet = current.getStringRepresentation();
		if (!currentSet.equals(goalSet)) {
			int emptyIndex = currentSet.indexOf('0');
			if( emptyIndex+3 < 9 ) { // can move down
				String expandedSet = swap(currentSet,emptyIndex,emptyIndex+3); 
				if (!exploredSet.contains(expandedSet)) {
					nodesExpanded++;
					searchDepth = Math.max(searchDepth, current.cost + 1);
					frontier.add(new Gameboard(expandedSet, current.cost + 1, heursticType, current));
				}				
			}
			if(emptyIndex-3 > 0) { // can move up
				String expandedSet = swap(currentSet,emptyIndex,emptyIndex-3);
				if (!exploredSet.contains(expandedSet)) {
					nodesExpanded++;
					searchDepth = Math.max(searchDepth, current.cost + 1);
					frontier.add(new Gameboard(expandedSet, current.cost + 1, heursticType, current));
				}
				

			}
			if(emptyIndex-1 > 0 && ((emptyIndex / 3) == ((emptyIndex - 1) / 3 ))) { // can move right
				String expandedSet = swap(currentSet,emptyIndex,emptyIndex-1); 
				if (!exploredSet.contains(expandedSet)) {
					nodesExpanded++;
					searchDepth = Math.max(searchDepth, current.cost + 1);
					frontier.add(new Gameboard(expandedSet, current.cost + 1, heursticType, current));
				}
		

			}
			if(emptyIndex+1 < 9 && ((emptyIndex / 3) == ((emptyIndex + 1) / 3 ))) { // can move left
				String expandedSet = swap(currentSet,emptyIndex,emptyIndex+1); 
				if (!exploredSet.contains(expandedSet)) {
					nodesExpanded++;
					searchDepth = Math.max(searchDepth, current.cost + 1);
					frontier.add(new Gameboard(expandedSet, current.cost + 1, heursticType, current));
				}
			
			}
		

		}else {
			timeElapsed = System.currentTimeMillis() - timeStart;
			cost = current.cost;
			ArrayList<Gameboard> path = new ArrayList<Gameboard>();
			while (current != null) {
				path.add(current);
				current = current.parent;
			}
			Collections.reverse(path);
			for(int i = 0 ; i < path.size();i++) {
				System.out.println("Step " + i);
				int[][] game = path.get(i).currentGameBoard;
				for(int k = 0 ; k < 3 ; k++ ) {
					for(int j = 0 ; j < 3 ; j++) {
						System.out.print(game[k][j] + " ");
					}
					System.out.println();
				}
				
			}
			System.out.println("Cost Of Path : " + cost);
			System.out.println("Nodes Expanded : "+ nodesExpanded );
			System.out.println("Search Depth : "+ searchDepth );
			System.out.println("Running Time : "+ timeElapsed );
			frontier.clear();
			return true;
		}
	}
	
	
	return false;
 }
 
 private String swap(String current,int first,int second) {
	 StringBuilder swappedString = new StringBuilder(current);
	 char temp = current.charAt(first);
	 swappedString.setCharAt(first, current.charAt(second));
	 swappedString.setCharAt(second,temp);
	 return swappedString.toString();
 }
}
