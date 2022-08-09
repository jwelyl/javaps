import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {
	static int N, M, K;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static char[][] graph;
	static int[][][] visited;	//	visited[k][y][x] : k번 벽을 뚫을 수 있을 때 (y, x)까지의 거리
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
	
		graph = new char[N][M];
		visited = new int[K + 1][N][M];
		
		for(int i = 0; i < N; i++) 
			graph[i] = bf.readLine().toCharArray();

		if(N == 1 && M == 1)
			ans = 1;
		else bfs();
			
		System.out.println(ans);
	}
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean ok = false;
		visited[K][0][0] = 1;	//	시작점은 벽을 하나도 안부순 상태
		q.offer(new Node(0, 0, K));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cy = node.y;
			int cx = node.x;
			int ccnt = node.cnt;
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				int ncnt = ccnt;
				
				if(!isIn(ny, nx))	//	범위를 벗어날 경우
					continue;
				
				if(graph[ny][nx] == '0') {	//	벽이 아닐 경우
					if(ny == N - 1 && nx == M - 1) {	//	도착점일 경우
						ok = true;
						visited[ncnt][ny][nx] = visited[ccnt][cy][cx] + 1;
						ans = Math.min(ans, visited[ncnt][ny][nx]);
					}
					else {
						if(visited[ncnt][ny][nx] == 0) {	//	벽이 아니면서 처음 방문할 경우
							visited[ncnt][ny][nx] = visited[ccnt][cy][cx] + 1;
							q.offer(new Node(ny, nx, ncnt));
						}
					}
				}
				else {	//	벽일 경우
					if(ncnt >= 1) {	//	부술 수 있는 벽의 개수가 남았을 경우
						ncnt -= 1;
						
						if(visited[ncnt][ny][nx] == 0) {	//	아직 방문하지 않았을 경우
							visited[ncnt][ny][nx] = visited[ccnt][cy][cx] + 1;
							q.offer(new Node(ny, nx, ncnt));
						}
					}
				}
			}
		}
		
		if(!ok)
			ans = -1;
	}
	
	static class Node {
		int y;
		int x; 
		int cnt;	//	부술 수 있는 벽의 개수(K부터 시작)
		
		Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
