import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
	static int N, M, ans = Integer.MAX_VALUE;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static char[][] field;
	static boolean[][][] visited;
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	static class Node {
		int y;		//	현재 위치 y 좌표
		int x;		//	현재 위치 x 좌표
		int cnt;	//	벽을 부순 횟수(최대 1)
		int cost;	//	(0, 0)에서 (y, x)까지 오는데 비용
	
		Node(int y, int x, int cnt, int cost) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.cost = cost;
		}
	}
	
	static void bfs() {
		boolean ok = false;
		Queue<Node> q = new LinkedList<Node>();
		visited[0][0][0] = true;
		q.offer(new Node(0, 0, 0, 1));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int curY = node.y;			//	현재 위치 (y, x)
			int curX = node.x;
			int curCnt = node.cnt;		//	현재 위치에 오기까지 벽을 부순 횟수
			int curCost = node.cost;	//	현재 위치에 오기까지 이동한 거리
			
			for(int d = 0; d < 4; d++) {
				int nextY = curY + dy[d];		//	주변 4방향의 다음 위치 (ny, nx)
				int nextX = curX + dx[d];
				int nextCnt = curCnt;			//	다음 위치 가는데까지 벽을 부순 횟수
				int nextCost = curCost + 1;		//	다음 위치 가는데까지 이동한 거리(현재 위치's + 1)
			
				if(!isIn(nextY, nextX))		//	범위 벗어날 경우
					continue;
				
				if(field[nextY][nextX] == '1') {	//	다음 방문할 위치가 벽일 경우
					if(nextCnt == 0) {	//	아직 벽을 부술 수 있을 경우
						nextCnt++;	//	벽을 하나 부숨
						visited[nextCnt][nextY][nextX] = true;	//	벽을 부숴서 방문
						q.offer(new Node(nextY, nextX, nextCnt, nextCost));
					}
				}
				else {
					if(nextY == N - 1 && nextX == M - 1) {
						ok = true;
						ans = Integer.min(ans, nextCost);
					} else {
						if(!visited[nextCnt][nextY][nextX]) {
							visited[nextCnt][nextY][nextX] = true;
							q.offer(new Node(nextY, nextX, nextCnt, nextCost));
						}
					}
					
				}
			}
		
		}
		
		if(!ok)
			ans = -1;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		field = new char[N][M];
		visited = new boolean[2][N][M];

		
		for(int i = 0; i < N; i++) 
			field[i] = bf.readLine().toCharArray();
		
		if(N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		
		bfs();
		
		System.out.println(ans);
	}

}