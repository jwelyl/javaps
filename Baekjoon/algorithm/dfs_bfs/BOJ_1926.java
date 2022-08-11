import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1926 {
	static int N, M;
	static int[][] graph;
	static int numOfComps = 0;
	static int maxSize = 0;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			
			for(int j = 0; j < M; j++)
				graph[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] == 1) {
					numOfComps += 1;
					maxSize = Integer.max(maxSize, dfs(i, j));
				}
			}
		}
		
		System.out.println(numOfComps + "\n" + maxSize);
	}
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	static int dfs(int y, int x) {
		int ret = 1;
		
		graph[y][x] = 0;
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(isIn(ny, nx) && graph[ny][nx] == 1)
				ret += dfs(ny, nx);
		}
		
		return ret;
	}
}