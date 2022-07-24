import java.util.Scanner;

public class Q2440 {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		N = sc.nextInt();
		
		for(int i = N; i > 0; i--) {
			for(int j = 0; j < i; j++)
				sb.append("*");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
