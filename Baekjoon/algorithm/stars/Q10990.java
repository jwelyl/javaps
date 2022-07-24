import java.util.Arrays;
import java.util.Scanner;

public class Q10990 {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	static char[][] stars;
	
	static String deleteTrailingSpaces(String str) {
		int end = str.length() - 1;
		
		while(str.charAt(end) == ' ')
			end--;
		
		return str.substring(0, end + 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		
		stars = new char[N][2 * N - 1];
		for(int i = 0; i < N; i++)
			Arrays.fill(stars[i], ' ');
		
		for(int i = 0; i < N; i++) {
			stars[i][N - 1 - i] = '*';
			stars[i][N - 1 + i] = '*';
		}
		
		for(int i = 0; i < N; i++)
			sb.append(deleteTrailingSpaces(new String(stars[i])) + "\n");
		System.out.println(sb.toString());
	}

}
