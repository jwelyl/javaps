import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static char[][] stars;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	static void drawStars(int idx, int y, int x) {
		if(idx == 0) {
			stars[y][x] = '*';
			return;
		}
		
		drawStars(idx - 1, y, x);
		drawStars(idx - 1, y, (int)(x + Math.pow(2, idx - 1)));
		drawStars(idx - 1, (int)(y + Math.pow(2, idx - 1)), x);
	}
	
	public static void main(String[] args) {
		N = sc.nextInt();
		stars = new char[(int)Math.pow(2, N)][(int)Math.pow(2,  N)];
		for(int i = 0; i < stars.length; i++)
			Arrays.fill(stars[i], ' '); 
		
		drawStars(N, 0, 0);
		
		for(int i = 0; i < stars.length; i++) {
			String str = new String(stars[i]);
			str = str.trim();
			sb.append(str + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
