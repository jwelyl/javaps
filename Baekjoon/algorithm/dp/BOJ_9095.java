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

public class BOJ_9095 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int T, N;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(bf.readLine());
			dp = new int[N + 1];
			
			dp[1] = 1;
			if(N >= 2)
				dp[2] = 2;
			if(N >= 3)
				dp[3] = 4;
			if(N >= 4) {
				for(int i = 4; i <= N; i++)
					dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}
			
			sb.append(dp[N]).append("\n");
		}
		
		System.out.print(sb);
	}
}
