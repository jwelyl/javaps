import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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

public class BOJ_10026 {
	static int N;
	static char[][] colors;
	
	static boolean[][] visited;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int normal = 0;
	static int abnormal = 0;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		colors = new char[N][N];
		visited = new boolean[N][N];
	
		for(int i = 0; i < N; i++)
			colors[i] = bf.readLine().toCharArray();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					normal++;
					bfs(i, j, colors[i][j], true);
				}
			}
		}
		
		for(int i = 0; i < N; i++)
			Arrays.fill(visited[i], false);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					abnormal++;
					bfs(i, j, colors[i][j], false);
				}
			}
		}
		
		System.out.println(normal + " " + abnormal);
	}
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
	
	static void bfs(int y, int x, char ch, boolean isNormal) {
		Queue<int[]> q = new LinkedList<>();
		int[] input = {y, x};
		visited[y][x] = true;
		q.offer(input);
		
		while(!q.isEmpty()) {
			int[] output = q.poll();
			int cy = output[0];
			int cx = output[1];
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				int[] nInput = new int[2];
				
				if(!isIn(ny, nx))
					continue;
				
				if(isNormal) {
					if(ch == colors[ny][nx] && !visited[ny][nx]) {
						visited[ny][nx] = true;
						nInput[0] = ny;
						nInput[1] = nx;
						q.offer(nInput);
					}
				}
				else {
					if(((ch != 'B' && colors[ny][nx] != 'B') || (ch == 'B' && colors[ny][nx] == 'B')) && !visited[ny][nx]) {
						visited[ny][nx] = true;
						nInput[0] = ny;
						nInput[1] = nx;
						q.offer(nInput);
					}
				}
			}
		}
	}
}