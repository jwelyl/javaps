import java.util.Scanner;

public class Q2443 {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		
		for(int i = N; i > 0; i--) {
			for(int j = 0; j < N - i; j++)
				sb.append(" ");
			for(int j = 0; j < 2 * i - 1; j++)
				sb.append("*");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
