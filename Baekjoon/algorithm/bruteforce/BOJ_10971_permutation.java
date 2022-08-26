import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

public class BOJ_10971_permutation {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	final static int INF = Integer.MAX_VALUE;
	static int N;
	static int[][] dist;
	static boolean[] isUsed;
	static int cost = 0, minCost = INF;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		dist = new int[N + 1][N + 1];
		isUsed = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			
			for(int j = 1; j <= N; j++)
				dist[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int start = 1; start <= N; start++) {
			isUsed[start] = true;
			permutation(0, start, isUsed, new int[N - 1]);
			Arrays.fill(isUsed, false);
		}
		
		System.out.println(minCost);
	}
	
	static void permutation(int nth, int startPos, boolean[] isUsed, int[] order) {
		if(nth == N - 1) {
//			System.out.println("\n경우의 수");
			cost = 0;
			
			if(dist[startPos][order[0]] == 0) {
				cost = INF;
				return;
			}
//			System.out.println("dist[" + startPos + "][" + order[0] + "] = " + dist[startPos][order[0]]);
			cost += dist[startPos][order[0]];
//			System.out.println("cost = " + cost);
			
			for(int i = 0; i < N - 2; i++) {
				int d = dist[order[i]][order[i + 1]];
				if(d == 0) {
					cost = INF;
					return;
				}
//				System.out.println("dist[" + order[i] + "][" + order[i + 1] + "] = " + dist[order[i]][order[i + 1]]);
				cost += d;
//				System.out.println("cost = " + cost);
			}
			
			if(dist[order[N - 2]][startPos] == 0) {
				cost = INF;
				return;
			}
			cost += dist[order[N - 2]][startPos];
//			System.out.println("dist[" + order[N - 2] + "][" + startPos + "] = " + dist[order[N - 2]][startPos]);
//			System.out.println("cost = " + cost);
			
			if(cost < minCost) {
				minCost = cost;
				
//				System.out.println("최소 비용 = " + cost);
//				System.out.println("최소 비용일 때 경로");
//				System.out.print(startPos + " -> ");
//				for(int i = 0; i < N - 1; i++)
//					System.out.print(order[i] + " -> ");
//				System.out.println(startPos);			
			}
			
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(isUsed[i])
				continue;
			
			isUsed[i] = true;
			order[nth] = i;
			permutation(nth + 1, startPos, isUsed, order);
			isUsed[i] = false;
		}
	}
}