import java.util.Arrays;
import java.util.Scanner;

public class Main {
	final static int INF = Integer.MAX_VALUE;
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	static int[] dp;
	static int[] parents;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		
		dp = new int[N + 1];
		Arrays.fill(dp, INF);
		
		parents = new int[N + 1];
		
		dp[1] = 0;
		parents[1] = 0;
		
		for(int i = 2; i <= N; i++) {
			if(i % 3 == 0) {
				if(dp[i] > dp[i / 3] + 1) {
					dp[i] = dp[i / 3] + 1;
					parents[i] = i / 3;
				}
			}
			
			if(i % 2 == 0) {
				if(dp[i] > dp[i / 2] + 1) {
					dp[i] = dp[i / 2] + 1;
					parents[i] = i / 2;
				}
			}
			
			if(dp[i] > dp[i - 1] + 1) {
				dp[i] = dp[i - 1] + 1;
				parents[i] = i - 1;
			}
		}
		
		int cur = N;
		sb.append(dp[cur] + "\n");
		while(cur != 0) {
			sb.append(cur + " ");
			cur = parents[cur];
		}
		
		System.out.println(sb);
	}
}