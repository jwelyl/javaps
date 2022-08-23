import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_1149 {
	final static int R = 0, G = 1, B = 2;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int[][] colors;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
	
		colors = new int[N + 1][3];
		dp = new int[N + 1][3];
		
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < 3; j++)
				colors[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		dp[1][R] = colors[1][R];
		dp[1][G] = colors[1][G];
		dp[1][B] = colors[1][B];
		
		for(int i = 2; i <= N; i++) {
			dp[i][R] = Integer.min(dp[i - 1][G], dp[i - 1][B]) + colors[i][R];
			dp[i][G] = Integer.min(dp[i - 1][B], dp[i - 1][R]) + colors[i][G]; 
			dp[i][B] = Integer.min(dp[i - 1][R], dp[i - 1][G]) + colors[i][B];
		}
		
		System.out.println(Integer.min(dp[N][R], Integer.min(dp[N][G], dp[N][B])));
	}
}
