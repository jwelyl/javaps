import java.util.Arrays;
import java.util.Scanner;

public class Q10991 {
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
		for(int i = 0; i < N; i++)
			Arrays.fill(stars[i], ' ');
		
		for(int i = 0; i < H; i++) {
			if(i % 2 == 0) {
				for(int j = 0; j <= i; j += 2) {
					stars[i][mid - j] = '*';
					stars[i][mid + j] = '*';
				}
			}
			else {
				for(int j = 1; j <= i; j += 2) {
					stars[i][mid - j] = '*';
					stars[i][mid + j] = '*';
				}
			}
		}
		
		for(int i = 0; i < N; i++)
			sb.append(deleteTrailingSpaces(new String(stars[i])) + "\n");
		
		System.out.println(sb.toString());
		
	}

}
