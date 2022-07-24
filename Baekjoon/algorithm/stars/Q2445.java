import java.util.Scanner;

public class Q2445 {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		N = sc.nextInt();
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++)
				sb.append("*");
			for(int j = 0; j < 2 * N - 2 * i; j++)
				sb.append(" ");
			for(int j = 0; j < i; j++)
				sb.append("*");
			sb.append("\n");
		}
		
		for(int i = N; i > 0; i--) {
			for(int j = 0; j < i; j++)
				sb.append("*");
			for(int j = 0; j < 2 * N - 2 * i; j++)
				sb.append(" ");
			for(int j = 0; j < i; j++)
				sb.append("*");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
