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

public class Main {
	final static int NONE = 0;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int[][] dist;
	static boolean[] visited;
	static int sum, minSum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		dist = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 1; j <= N; j++)
				dist[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			visited[i] = true;
			dfs(i, i, visited, new int[N - 1], 0, 0);
			visited[i] = false;
		}
	
		System.out.println(minSum);
	}
	
	/**
	 * 
	 * @param start       : 외판원 순회 경로의 시작 정점
	 * @param now         : 외판원 순회 경로 상의 정점 중 현재 정점
	 * @param visited	  : 정점 방문 여부 확인
	 * @param pos         : nth번째 방문한 정점
	 * @param costSum     : start부터 now까지 경로 비용 누적합
	 * @param nth         : 경로에 포함될 정점 중 시작 정점 start를 제외한 정점들이 몇 번째 방문했는지 나타내는 변수(N - 1이면 종료)
	 */
	static void dfs(int start, int now, boolean[] visited, int[] pos, int costSum, int nth) {
		if(nth == N - 1) {	//	모든 정점 방문
			if(dist[pos[nth - 1]][start] == NONE)
				return;
			
			costSum += dist[pos[nth - 1]][start];
			sum = costSum;
			
			if(minSum > sum)
				minSum = sum;
			
			return;
		}
		
		//	다음 정점 찾기
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && dist[now][i] != NONE) {	//	i번 도시를 아직 방문하지 않았고, now에서 i까지 길이 존재
				visited[i] = true;	//	i번째 도시 방문 처리
				pos[nth] = i;		//	nth번째 방문한 도시 i
				dfs(start, i, visited, pos, costSum + dist[now][i], nth + 1);	//	도시 i를 now로 변경, costSum에 now->i 비용을 추가해서 dfs 호출
				visited[i] = false;
			}
			else //	i번째 도시를 이미 방문했거나 now->i 간선이 없을 경우 가지치기함
				continue;
		}		
	}
}