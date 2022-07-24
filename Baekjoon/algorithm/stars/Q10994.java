import java.util.Arrays;
import java.util.Scanner;

public class Q10994 {
	static int N, W;
	static char[][] stars;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	static void drawStars(int N, int from, int to) {
		for(int i = from; i <= to; i++) {
			stars[from][i] = '*';
			stars[to][i] = '*';
		}
		for(int i = from + 1; i <= to - 1; i++) {
			stars[i][from] = '*';
			stars[i][to] = '*';
		}
		
		if(N > 1) 
			drawStars(N - 1, from + 2, to - 2);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		N = 4 * N - 3;
		
		stars = new char[N][N];
		for(int i = 0; i < N; i++)
			Arrays.fill(stars[i], ' ');
		
		drawStars(N, 0, N - 1);
		for(int i = 0; i < N; i++)
			sb.append(new String(stars[i]) + "\n");
	
		System.out.println(sb.toString());
	}
}
