import java.util.Arrays;
import java.util.Scanner;

public class Q10992 {
	static int N, H, W, mid;
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
		H = N;
		W = 2 * N - 1;
		mid = W / 2;
	
		stars = new char[H][W];
		
		for(int i = 0; i < N - 1; i++)
			Arrays.fill(stars[i], ' ');
		Arrays.fill(stars[N - 1], '*');
		
		for(int i = 0; i < N - 1; i++) {
			stars[i][mid - i] = '*';
			stars[i][mid + i] = '*';
			sb.append(deleteTrailingSpaces(new String(stars[i])) + "\n");
		}
		sb.append(deleteTrailingSpaces(new String(stars[N - 1])) + "\n");
	
		System.out.println(sb.toString());
	}

}
