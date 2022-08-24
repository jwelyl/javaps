import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/17404
 * @difficuly G4
 * @performance 12104KB 96ms
 * @category # Dynamic Programming
 * @memo 1149 RGB거리 문제에서 첫 번째 집과 N번째 집의 색이 같으면 안된다는 조건이 추가됨.
 * @memo 첫 번째 집의 색깔을 미리 정해놓은 뒤, 각 경우에 따라 N번째 집까지 칠했을 때 최소비용을 구하면 됨.
 * @memo 첫 번째 집의 색깔을 정해놓았으면 다른 색을 칠했을때의 비용을 INF로 설정하여 다른 색을 칠하는 경우가 선택되지 않게 할 수 있음.
 * @memo 이 때 INF 값을 Overflow가 발생하지 않게 적절히 큰 값으로 정하거나, 아래 코드처럼 적절히 처리하는 방법도 있음.
 * @memo 첫 번째 집을 색칠한 색의 경우에 따라서 N번째 집까지 칠한 비용을 구한 후, 첫 번째 집과 색이 같은 경우는 제외하고 최소 비용을 찾는다.
 * @memo 첫 번째 집을 색칠하는 3가지 경우에 따라 최소 비용이 존재할텐데 그 중 최솟값이 정답
 * @etc 1149 RGB 거리는 DP에 익숙하다면 점화식을 금방 찾아낼 수 있었는데 그곳에 좀 더 기교를 추가한 문제 같다.
 * 
 */

public class BOJ_17404 {
	final static int R = 0, G = 1, B = 2;
	final static int INF = Integer.MAX_VALUE;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int[][] colors;
	static int[][] dp;
	static int[] nth;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());

		colors = new int[N + 1][3];
		dp = new int[N + 1][3];
		nth = new int[3];
		for (int i = 0; i < 3; i++)
			nth[i] = INF;

		for (int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 3; j++)
				colors[i][j] = Integer.parseInt(tokens.nextToken());
		}

		for (int k = 0; k < 3; k++) {	//	첫 번째 집을 k(k = 0 : R, 1 : G, 2 : B)로 칠하는 경우
			for (int i = 0; i < 3; i++) {
				if (i == k)	//	첫 번째 집에 k색을 칠하는 비용
					dp[1][i] = colors[1][i];
				else	//	다른 색일 경우 첫 번째 집 칠하는 비용을 INF로 설정
					dp[1][i] = INF;
			}

			for (int i = 2; i <= N; i++) {
				int dp1 = (dp[i - 1][G] == INF) ? INF : (dp[i - 1][G] + colors[i][R]);
				int dp2 = (dp[i - 1][B] == INF) ? INF : (dp[i - 1][B] + colors[i][R]);

				dp[i][R] = Integer.min(dp1, dp2);	//	i번째 집을 R로 칠하기 위해 i-1번째 집을 G, B로 칠한 경우의 비용과 비교

				dp1 = (dp[i - 1][B] == INF) ? INF : (dp[i - 1][B] + colors[i][G]);
				dp2 = (dp[i - 1][R] == INF) ? INF : (dp[i - 1][R] + colors[i][G]);

				dp[i][G] = Integer.min(dp1, dp2);	//	i번째 집을 G로 칠하기 위해 i-1번째 집을 B, R로 칠한 경우의 비용과 비교

				dp1 = (dp[i - 1][R] == INF) ? INF : (dp[i - 1][R] + colors[i][B]);
				dp2 = (dp[i - 1][G] == INF) ? INF : (dp[i - 1][G] + colors[i][B]);

				dp[i][B] = Integer.min(dp1, dp2);	//	i번째 집을 B로 칠하기 위해 i-1번째 집을 R, G로 칠한 경우의 비용과 비교

				if (i == N) {
					for(int j = 0; j < 3; j++) {
						if(j == k) continue;	//	N번째 집을 첫 번째 집과 같은 색으로 칠할 수 없음
						
						nth[k] = Integer.min(nth[k], dp[i][j]);
					}
				}
			}
		}

		System.out.println(Math.min(nth[R], Math.min(nth[G], nth[B])));
	}
}