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

public class BOJ_2589 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	static char[][] map;
	static int[][] dist;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	static int maxDist = 0;
	
	static void reset() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				dist[i][j] = -1;
		}
	}
	
	static boolean canMove(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M) && (map[y][x] == 'L');
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		map = new char[N][M];
		dist = new int[N][M];
		
		for(int i = 0; i < N; i++) 
			map[i] = bf.readLine().toCharArray();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'L') {
					reset();
					bfs(i, j);
				}
			}
		}
		
		System.out.println(maxDist);
	}
	
	static void bfs(int y, int x) {
//		System.out.println("bfs(" + y + ", " + x + ")");
		int[] input = {y, x, 0};
		Queue<int[]> q = new LinkedList<>();
		q.offer(input);
		dist[y][x] = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				int[] arr = q.poll();
				int cy = arr[0];
				int cx = arr[1];
				int cd = arr[2];
				
//				System.out.println("(cy, cx) = (" + cy + ", " + cx + ")");
				for(int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					int nd = cd + 1;
					
//					System.out.println("d = " + d);
					if(canMove(ny, nx) && dist[ny][nx] == -1) {
						int[] nInput = {ny, nx, nd};
//						System.out.print("nInput = ");
//						System.out.println(Arrays.toString(nInput));
						dist[ny][nx] = nd;
						
						if(dist[ny][nx] > maxDist) {
							maxDist = dist[ny][nx];
//							System.out.println("maxDist = " + maxDist);
						}
						
						q.offer(nInput);
					}
				}	//	for-4-dir
			}	//	for-q-size
		}	//	while
	}	//	bfs
}
