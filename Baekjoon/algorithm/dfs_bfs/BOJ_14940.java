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

public class BOJ_14940 {
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	final static int NONE = 0;			//	원래 갈 수 없는 땅 0
	final static int CANT = -1;			//	가로막혀서 못 간 땅 -1
	final static int NVISIT = -2;		//	아직 방문안한 땅 -2
	
	static int N, M;
	static int[][] map;
	static int[][] dist;
	static int sy, sx;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		dist = new int[N][M];
		for(int i = 0; i < N; i++)
			Arrays.fill(dist[i], NVISIT);
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				
				if(map[i][j] == 2) {
					sy = i;
					sx = j;
				}
			}
		}
		
		bfs();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != NONE && dist[i][j] == NVISIT)	//	갈수 없는 땅이 아닌데 가로막혀서 못 간 경우
					sb.append(CANT).append(" ");
				else if(map[i][j] == NONE && dist[i][j] == NVISIT)	//	원래 갈수 없는 땅이라 못 간 경우
					sb.append(NONE).append(" ");
				else
					sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
//		System.out.println("sy = " + sy);
//		System.out.println("sx = " + sx);
		dist[sy][sx] = 0;
		q.offer(new int[] {sy, sx});
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				int cy = q.peek()[0];
				int cx = q.peek()[1];
				q.poll();
				
				for(int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					
					if(!isIn(ny, nx))	//	범위 벗어날 경우
						continue;
					
					if(map[ny][nx] == NONE) {	//	원래 갈 수 없는 땅
						dist[ny][nx] = NONE;
						continue;
					}
					
					if(dist[ny][nx] == NVISIT) {	//	갈 수 있는 땅이고 아직 방문 안함
						dist[ny][nx] = dist[cy][cx] + 1;
						q.offer(new int[] {ny, nx});
					}
				}
			}	
		}	//	while-end
	}	//	bfs-end
}