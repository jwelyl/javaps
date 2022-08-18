import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/1726
 * @difficuly G3
 * @performance 12500KB 96ms
 * @category # BFS, 다차원 배열(필수인지는 잘 모르겠네)
 * @memo 한 번 이동할 때 바라보는 방향으로 최대 3칸 이동 가능, 전진은 안하고 방향만 돌리는 경우도 상태 변화로 간주해야함
 * @memo 이동했거나, 이동하지 않았어도 방향 바꾼 경우를 모두 Queue에 새로 삽입해야 함.
 * @memo 단 해당 칸에 해당 방향으로 들어온 경우를 check해서 중복될 경우 Queue에 삽입 안함.
 * @memo 이를 check하기 위해 3차원 visited 배열이 필요했음.
 * @memo 1칸 이동한 경우, 2칸 이동한 경우, 3칸 이동한 경우 모두 가능할 경우 Queue에 삽입
 * @memo 2칸 이상 이동할 경우 중간에 막히지 않았어야 함. 2칸 째에서 막혔다면 3칸 이동은 불가함.
 * @memo 방향이 동, 서, 남, 북 순이라 조금 까다로웠는데 회전 함수를 살짝 길게 작성하는 대신 bfs에서는 편하게 사용했음.
 * @etc BFS 문제로 기존 4방 탐색을 응용하면 그래도 스스로 해결 가능했음.
 * 
 */

public class BOJ_1726 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;

	static int[] dy = { 0, 0, 1, -1 }; // 각 방향별 delta(동(0), 서(1), 남(2), 북(3))
	static int[] dx = { 1, -1, 0, 0 };

	static int turnRight(int dir) {
		int ret;

		switch (dir) {
		case 0:
			ret = 2;
			break;
		case 1:
			ret = 3;
			break;
		case 2:
			ret = 1;
			break;
		default:
			ret = 0;
			break;
		}

		return ret;
	}

	static int turnLeft(int dir) {
		int ret;

		switch (dir) {
		case 0:
			ret = 3;
			break;
		case 1:
			ret = 2;
			break;
		case 2:
			ret = 0;
			break;
		default:
			ret = 1;
			break;
		}

		return ret;
	}

	static class Robot {
		int y; // 로봇 위치한 y 좌표
		int x; // 로봇 위치한 x 좌표
		int dir; // 로봇이 바라보는 방향(동 : 0, 서 : 1, 남 : 2, 북 : 3)

		Robot(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}

	static int[][] map;
	static boolean[][][] visited;

	static int sy, sx, sdir;
	static int ey, ex, edir;
	static int time = 0;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new int[N][M];
		visited = new boolean[4][N][M];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}

		tokens = new StringTokenizer(bf.readLine());
		sy = Integer.parseInt(tokens.nextToken()) - 1;
		sx = Integer.parseInt(tokens.nextToken()) - 1;
		sdir = Integer.parseInt(tokens.nextToken()) - 1;

		tokens = new StringTokenizer(bf.readLine());
		ey = Integer.parseInt(tokens.nextToken()) - 1;
		ex = Integer.parseInt(tokens.nextToken()) - 1;
		edir = Integer.parseInt(tokens.nextToken()) - 1;
		
		bfs();
		System.out.println(time);
	}

	static void bfs() {
		Queue<Robot> q = new LinkedList<>();
		visited[sdir][sy][sx] = true;
		q.offer(new Robot(sy, sx, sdir));
		
		WHILE : while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for(int i = 0; i < size; i++) {
				Robot r = q.poll();
				int cy = r.y;
				int cx = r.x;
				int cdir = r.dir;
				
				if(cy == ey && cx == ex && cdir == edir) {
					time--;
					break WHILE;
				}
				
				int ny = cy;
				int nx = cx;
				int ndir = cdir;
				
				//	현재 위치에서 바라보는 방향으로 1칸, 2칸, 3칸 가봄(갈 수 있을 경우)
				for(int k = 1; k <= 3; k++) {
					ny = cy + dy[cdir] * k;
					nx = cx + dx[cdir] * k;
					
					//	범위 밖이거나 중간에 벽이 있으면 stop
					if(!isIn(ny, nx) || map[ny][nx] == 1)
						break;
					
					if(!visited[ndir][ny][nx]) {
						visited[ndir][ny][nx] = true;
						q.offer(new Robot(ny, nx, ndir));
					}
				}	//	3칸 탐색-end
				
				ny = cy;
				nx = cx;
				ndir = turnRight(cdir);
				if(!visited[ndir][ny][nx]) {
					visited[ndir][ny][nx] = true;
					q.offer(new Robot(ny, nx, ndir));
				}
				ndir = turnLeft(cdir);
				if(!visited[ndir][ny][nx]) {
					visited[ndir][ny][nx] = true;
					q.offer(new Robot(ny, nx, ndir));
				}
			}	
		}	//	while-end
	}	//	bfs-end
}
 BOJ_1726 {
    
}
