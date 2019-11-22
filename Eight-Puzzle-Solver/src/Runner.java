
public class Runner {
	public static void main(String[] args) {
		String startSet = "125340678";
		A_Star_Solver solver = new  A_Star_Solver();
		System.out.println("Manhattan Distance");
		System.out.println("Is Solvable : "+solver.A_Star(startSet,"123456780", 1));
		System.out.println("----------------------------------------------------------");
		System.out.println("Euclidean Distance");
		System.out.println("Is Solvable : "+solver.A_Star(startSet,"123456780", 2));
	}
}
