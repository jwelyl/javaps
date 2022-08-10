import java.util.Arrays;
import java.util.Scanner;

public class Q10993 {
	static int N, H = 1, W = 1, midH, midW;
	static char[][] stars;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	static void drawStars(int N, int fy, int ty, int fx, int tx) {
		int h = ty - fy + 1;		//	현재(N) 높이
		int w = tx - fx + 1;		//	현재(N) 너비
		int midy = (fy + ty) / 2;	//	현재(N) 세로 중점
		int midx = (fx + tx) / 2;	//	현재(N) 가로 중점
		
		if(N == 1) {
			stars[midy][midx] = '*';
			return;
		}
		
		h = (h - 1) / 2;		//	이전(N-1) 높이
		w = 2 * h - 1;			//	이전(N-1) 너비
		
		if(N % 2 == 0) {
			for(int i = fx; i <= tx; i++)
				stars[fy][i] = '*';
			
			for(int i = fy + 1; i <= ty; i++) {
				int diff = i - fy;
				stars[i][fx + diff] = '*';
				stars[i][tx - diff] = '*';
			}
		
			fy = fy + 1;
			ty = midy;
		}
		else {
			for(int i = fx; i <= tx; i++)
				stars[ty][i] = '*';
			
			for(int i = ty - 1; i >= fy; i--) {
				int diff = ty - i;
				stars[i][fx + diff] = '*';
				stars[i][tx - diff] = '*';
			}
			
			fy = midy;
			ty = ty - 1;
		}
		
		fx = midx - (w / 2);
		tx = midx + (w / 2);
		drawStars(N - 1, fy, ty, fx, tx);
	}
	
	static String dTS(String str) {
		int end = str.length() - 1;
		
		while(str.charAt(end) == ' ')
			end--;
		
		return str.substring(0, end + 1);
	}
	
	public static void main(String[] args) {
		N = sc.nextInt();
		for(int i = 2; i <= N; i++) {
			H = 2 * H + 1;
			W = 2 * H - 1;
		}
		
//		System.out.println("N = " + N + ", H = " + H + ", W = " + W);
		stars = new char[H][W];
		for(int i = 0; i < H; i++)
			Arrays.fill(stars[i], ' ');
	
		drawStars(N, 0, H - 1, 0, W - 1);
	
		for(int i = 0; i < H; i++)
			sb.append(dTS(new String(stars[i])) + "\n");
	
		System.out.println(sb.toString());
	}
}