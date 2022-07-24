import java.util.Arrays;
import java.util.Scanner;

public class Q13015 {
	static int N, H, W, midH;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	static char[][] stars;
	
	static String dTS(String str) {
		int end = str.length() - 1;
		
		while(str.charAt(end) == ' ')
			end--;
		
		return str.substring(0, end + 1);
	}
	
	public static void main(String[] args) {
		N = sc.nextInt();
		H = 2 * N - 1;
		W = 4 * N - 3;
		midH = H / 2;
		
		stars = new char[H][W];
		for(int i = 0; i < H; i++)
			Arrays.fill(stars[i], ' ');
		
		for(int i = 0; i < N; i++) {
			stars[0][i] = '*';
			stars[H - 1][i] = '*';
			stars[0][W - 1 - i] = '*';
			stars[H - 1][W - 1- i] = '*';
		}
		
		for(int i = 1; i < midH; i++) {
			stars[i][i] = '*';
			stars[i][W - 1 - i] = '*';
			stars[i][i + N - 1] = '*';
			stars[i][W - i - N] = '*';
		}
		
		for(int i = midH; i > 0; i--) {
			stars[H - 1 - i][i] = '*';
			stars[H - 1 - i][W - 1 - i] = '*';
			stars[H - 1 - i][i + N - 1] = '*';
			stars[H - 1 - i][W - i - N] = '*';
		}
		
		
		for(int i = 0; i < H; i++) {
			sb.append(dTS(new String(stars[i])) + "\n");
		}
		System.out.println(sb.toString());
	}
}
