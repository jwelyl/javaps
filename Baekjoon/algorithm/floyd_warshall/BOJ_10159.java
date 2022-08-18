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

public class BOJ_10159 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	final static int HEAVY =  1;	//	dp[a][b] = HEAVY : a가 b보다 무거움
	final static int LIGHT = -1;	//	dp[b][a] = LIGHT : b가 a보다 가벼움
	static int N, M;
	static int[][] dp;				//	floyd-warshall algorithm 적용 결과
	static int[] unknown;			//	unknown[i] : i와 무게 비교 불가한 것들 개수
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
	
		dp = new int[N + 1][N + 1];
		unknown = new int[N + 1];
		
		for(int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			dp[a][b] = HEAVY;
			dp[b][a] = LIGHT;
		}
		
		floydWarshall();
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++)
//				System.out.print(dp[i][j] + " ");
//			System.out.println();
//		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(j != i && dp[i][j] == 0)
					unknown[i]++;
			}
		}
		
		for(int i = 1; i <= N; i++)
			sb.append(unknown[i]).append("\n");
	
		System.out.println(sb);
	}
	
	static void floydWarshall() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(dp[i][k] == HEAVY && dp[k][j] == HEAVY)
						dp[i][j] = HEAVY;
					else if(dp[i][k] == LIGHT && dp[k][j] == LIGHT)
						dp[i][j] = LIGHT;
				}
			}
		}
	}
}
