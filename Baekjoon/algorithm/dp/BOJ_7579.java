import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/7579
 * @difficuly G3
 * @performance 16568KB   120ms
 * @category DP, 배낭 문제(Knapsack)
 * @memo 배낭 문제지만 조금 응용해야 했음. 기존의 배낭 문제가 최대 무게가 정해지고 그 무게 내에서 최대 이익을 내는 문제였다면
 * @memo 해당 문제는 특정 이익 이상을 내기 위한 최소 무게를 찾는 문제였음. 배낭 문제를 어떻게 적용하나 어려웠음.
 * @memo 배낭 문제 자체가 배운지 오래되서 다시 복습했고 이해가 잘 됐지만, 이 문제에 적용시키는 것은 다른 이들의 도움을 받았음. 
 * @memo 비활성화 비용이 0인 앱도 존재함을 주의해야 함. 다행히 예시부터 0인 앱이 주어져서 쉽게 해결했지만 안주어질 경우 문제 조건으로 찾아내야 함.
 * @etc 배낭 문제를 응용할 수 있는 DP 문제도 종종 출제되는 것 같으니 이번 문제를 해결한 것을 계기로 더 찾아서 풀어봐야겠다.
 *  
 */

public class BOJ_7579 {
	//	N : 앱의 개수, M : 필요한 메모리, maxCost : 모든 앱을 비활성화시켰을 때의 비용, leastCost : M의 메모리 확보를 위한 최소 비용
	static int N, M, maxCost, leastCost = Integer.MAX_VALUE;
	static int[] memory;	//	각 앱을 비활성화시켰을 때 얻을 수 있는 메모리
	static int[] cost;		//	각 앱을 비활성화시킬 때의 비용	
	static int[][] dp;		//	Knapsack 배열
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		memory = new int[N + 1];
		cost = new int[N + 1];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++)
			memory[i] = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(tokens.nextToken());
			maxCost += cost[i];	//	최대로 들 수 있는 비용 계산
		}
		
		dp = new int[N + 1][maxCost + 1];
		knapsack();
		System.out.println(leastCost);
	}
	
	static void knapsack() {
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= maxCost; j++) {	//	비활성화 비용이 0인 앱도 존재함
				if(cost[i] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = Integer.max(dp[i - 1][j], memory[i] + dp[i - 1][j - cost[i]]);
			
				if(dp[i][j] >= M) {
					if(leastCost > j)
						leastCost = j;
				}
			}
		}
	}
}