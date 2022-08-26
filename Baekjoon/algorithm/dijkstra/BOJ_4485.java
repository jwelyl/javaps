import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly
 * @performance KB ms
 * @category #
 * @memo
 * @etc
 * 
 */

public class BOJ_4485 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	final static int INF = Integer.MAX_VALUE;
	static int N;
	static int[][] map;
	static int[][] dist;

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}

	static class Node implements Comparable<Node> {
		int y;
		int x;
		int cost;

		Node(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
		
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		};
	}

	public static void main(String[] args) throws IOException {
		int t = 1;
		while (true) {
			N = Integer.parseInt(bf.readLine());
			if(N == 0) break;
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(tokens.nextToken());
			}
			
			dist = new int[N][N];
			for(int i = 0; i < N; i++)
				Arrays.fill(dist[i], INF);
			
			dijkstra();
			sb.append("Problem ").append(t++).append(": ").append(dist[N - 1][N - 1]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[0][0] = map[0][0];
		pq.offer(new Node(0, 0, dist[0][0]));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int cy = node.y;
			int cx = node.x;
			int ccost = node.cost;
			
			if(ccost > dist[cy][cx])
				continue;
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				int ncost = ccost;
				
				if(!isIn(ny, nx))
					continue;
				
				ncost += map[ny][nx];
				
				if(ncost < dist[ny][nx]) {
					dist[ny][nx] = ncost;
					pq.offer(new Node(ny, nx, dist[ny][nx]));
				}
			}
		}
	}
}