import java.util.Arrays;
import java.util.Scanner;

public class Q10997 {
	static int N, H = 7, W = 5;
	static char[][] stars;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	static String dTS(String str) {
		int end = str.length() - 1;
		while(str.charAt(end) == ' ')
			end--;
		return str.substring(0, end + 1);
	}

	static void drawStars(int N, int fy, int ty, int fx, int tx) {
//		System.out.println("drawStars(" + N + ", " + fy + ", " + ty + ", " + fx + ", " + tx + ")");
		
		for(int i = fx; i <= tx; i++) {
			stars[fy][i] = '*';
			stars[ty][i] = '*';
		}
		for(int i = fy + 1; i < ty; i++) {
			stars[i][fx] = '*';
			stars[i][tx] = '*';
		}
		stars[fy + 1][tx] = ' ';
		stars[fy + 2][tx - 1] = '*';
		
		if(N == 2) {
			stars[H / 2][W / 2] = '*';
			stars[H / 2 - 1][W / 2] = '*';
			stars[H / 2 - 1][W / 2 + 1] = '*';
			stars[H / 2 + 1][W / 2] = '*';
			return;
		}
		
		drawStars(N - 1, fy + 2, ty - 2, fx + 2, tx - 2);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		
		if(N == 1) {
			System.out.println("*");
			return;
		}
		
		for(int i = 2; i < N; i++) {
			H += 4;
			W += 4;
		}
		
//		System.out.println("H = " + H + ", W = " + W);
		
		stars = new char[H][W];
		for(int i = 0; i < H; i++)
			Arrays.fill(stars[i], ' ');
		
		drawStars(N, 0, H - 1, 0, W - 1);
		
		for(int i = 0; i < H; i++)
			sb.append(dTS(new String(stars[i])) + "\n");
		
		System.out.println(sb.toString());
	}
}
