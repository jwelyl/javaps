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

public class BOJ_1613 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, K;
	static int S;
	
	final static int FORMER = -1;	//	dp[a][b] = FORMER : a가 b보다 먼저	
	final static int LATTER =  1;	//	dp[b][a] = LATTER : b가 a보다 먼저
	
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
	
		dp = new int[N + 1][N + 1];
		
		for(int k = 0; k < K; k++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
		
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			dp[a][b] = FORMER;
			dp[b][a] = LATTER;
		}
		
		floydWarshall();
		
		S = Integer.parseInt(bf.readLine());
		for(int s = 0; s < S; s++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
		
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
		
			sb.append(dp[a][b]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void floydWarshall() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(dp[i][k] == FORMER && dp[k][j] == FORMER)
						dp[i][j] = FORMER;
					else if(dp[i][k] == LATTER && dp[k][j] == LATTER)
						dp[i][j] = LATTER;
				}
			}
		}
	}
}
