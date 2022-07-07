package ssafy;

import java.util.*;

public class Q1249 {
	final static int INF = 987654321;
	static int N;

	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static int[][] arr;
	static int[][] val;

	static class Pair {
		int y;
		int x;
		int v;
		
		Pair(int y, int x, int v) {
			this.y = y;
			this.x = x;
			this.v = v;
		}
	};
	
	public static void bfs(int sy, int sx, int ey, int ex) {
		Queue<Pair> q = new LinkedList<Pair>();
		val[sy][sx] = arr[sy][sx];
		q.offer(new Pair(sy, sx, val[sy][sx]));
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int cy = p.y;
			int cx = p.x;
			int cv = p.v;
			
			for(int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				int nv;
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N)
					continue;
				
				nv = cv + arr[ny][nx];
				
				if(nv < val[ny][nx]) {
					val[ny][nx] = nv;
					q.offer(new Pair(ny, nx, val[ny][nx]));
				}
				
			}
		}
		
	}

	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			sc.nextLine();
			
			arr = new int[N][];
			val = new int[N][];

			for (int i = 0; i < N; i++) {
				arr[i] = new int[N];
				val[i] = new int[N];
				Arrays.fill(val[i], INF);
			}

			for (int i = 0; i < N; i++) {
				String input = sc.nextLine();
				for (int j = 0; j < N; j++) 
					arr[i][j] = (input.charAt(j) - '0');
			}
			
			bfs(0, 0, N - 1, N - 1);
			
			System.out.println("#" + test_case + " " + val[N - 1][N - 1]);
		}
	}
}