import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2468
 * @difficuly S1
 * @performance 14012KB   200ms
 * @category # DFS/BFS를 이용한 탐색
 * @memo Bruteforce에 DFS/BFS를 조합한 단순한 문제지만 물에 전혀 잠기지 않을 수도 있다는 조건을 캐치하지 못하면 맞왜틀? 소리 나올 문제
 * @etc 문제를 꼼꼼이 읽었지만 캐치 못한걸 보면 아직 내공이 부족하거나 국어 실력이 딸릴지도?
 *  
 */

public class BOJ_2468 {
	static int N;
	static int[][] graph;
	static boolean[][] visited;
	static int maxHeight;
	static int maxCnt, cnt;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void sink(int h) {
		cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] <= h)
					visited[i][j] = true;
				else
					visited[i][j] = false;
			}
		}
	}

	static void dfs(int y, int x) {
		visited[y][x] = true;

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (isIn(ny, nx) && !visited[ny][nx])
				dfs(ny, nx);
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());

		graph = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(tokens.nextToken());

				if (graph[i][j] > maxHeight)
					maxHeight = graph[i][j];
			}
		}

		for (int h = 0; h < maxHeight; h++) {
			sink(h);
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			maxCnt = (maxCnt > cnt) ? maxCnt : cnt;
		}
	
		System.out.println(maxCnt);
	}
}
