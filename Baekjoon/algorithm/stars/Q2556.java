import java.util.Scanner;

public class Q2556 {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append("*");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
