import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	final static int INF = Integer.MAX_VALUE;	//	방문하지 않은 위치 비용
	final static int NONE = -1;					//	무방향
	
	int N;
    int[][][] cost;	//	cost[d][y][x] : (y, x)에 d방향으로 왔을때의 비용
    
    int[] dy = {-1, 0, 1, 0};	//	4방향 탐색(상, 우, 하, 좌 순)
    int[] dx = {0, 1, 0, -1};
	
	public int solution(int[][] board) {
        int answer = INF;
        
        N = board.length;
        cost = new int[4][N][N];
        for(int d = 0; d < 4; d++) {
        	for(int i = 0; i < N; i++)
        		Arrays.fill(cost[d][i], INF);	//	모든 위치까지 가는 비용 INF로 초기화
        }
      
        bfs(board);
        
        for(int d = 0; d < 4; d++) 
        	answer = Integer.min(answer, cost[d][N - 1][N - 1]);
        
        return answer;
    }
	
	static class Node {
		int y;
		int x;
		int dir;
		int cost;
	
		Node(int y, int x, int dir, int cost) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cost = cost;
		}
	}
	
	boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
	
	void bfs(int[][] board) {
		Queue<Node> q = new LinkedList<>();
		for(int d = 0; d < 4; d++)	//	시작점 비용은 0으로 초기화
        	cost[d][0][0] = 0;
		q.offer(new Node(0, 0, NONE, 0));
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				Node node = q.poll();
				int cy = node.y;
				int cx = node.x;
				int cdir = node.dir;
				int ccost = node.cost;
				
				for(int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					int ndir = d;
					int ncost = ccost;
					
					//	범위 벗어나거나 벽일 경우
					if(!isIn(ny, nx) || board[ny][nx] == 1)
						continue;
					
					if(cdir == NONE)	//	현재 방향이 NONE일 경우 다음 방향에 관계없이 100원만 추가
						ncost += 100;
					else {
						if(cdir % 2 == ndir % 2)	//	방향을 꺾을 필요가 없을 경우
							ncost += 100;
						else						//	방향을 꺾어야 할 경우
							ncost += 600;
					}
					
					//	아직 방문안했거나, 새롭게 방문하는 경우가 비용이 더 절감될 경우
					if(cost[ndir][ny][nx] > ncost) {
						cost[ndir][ny][nx] = ncost;
						q.offer(new Node(ny, nx, ndir, ncost));
					}	
				}
			}	//	for-size end
		}	//	while-end
	}	//	bfs-end
}
	
public class 경주로건설 {
	public static void main(String[] args) {
		int[][] board = {
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0}
		};
		
		Solution sol = new Solution();
		System.out.println(sol.solution(board));
	}
}
