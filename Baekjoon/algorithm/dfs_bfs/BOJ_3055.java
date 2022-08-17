import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
	static int R, C;
	static char[][] map;
	static int sy, sx;
	static int time = 0;

	static List<Pos> fireList = new ArrayList<Pos>();
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
	
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String input = bf.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				
				if(map[i][j] == 'S') {
					sy = i;
					sx = j;
				}
				else if(map[i][j] == '*')
					initialFires.add(new Pos(i, j));
			}
		}
		
		bfs();
		
		if(time == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(time);
	}
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < R) && (0 <= x && x < C);
	}
	
	static void bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		Queue<Pos> fq = new LinkedList<Pos>();
		boolean isOk = false;
		map[sy][sx] = 'V';	//	방문 처리함(다시 못가게 V로 처리)
		q.offer(new Pos(sy, sx));
		for(Pos p : initialFires)
			fq.offer(p);
		
		OUTTER : while(!q.isEmpty()) {
			int qSize = q.size();
			int fSize = fq.size();
			time++;
			
			for(int f = 0; f < fSize; f++) {
				Pos fire = fq.poll();
				int fy = fire.y;
				int fx = fire.x;
				
				for(int d = 0; d < 4; d++) {
					int nfy = fy + dy[d];
					int nfx = fx + dx[d];
					
					if(isIn(nfy, nfx) && (map[nfy][nfx] == '.' || map[nfy][nfx] == 'V')) {
						map[nfy][nfx] = '*';
						fq.offer(new Pos(nfy, nfx));
					}
				}
			}
			
			for(int i = 0; i < qSize; i++) {
				int cy = q.peek().y;
				int cx = q.peek().x;
				q.poll();
				
				for(int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					
					if(isIn(ny, nx) && map[ny][nx] != 'X' && map[ny][nx] != '*' && map[ny][nx] != 'V') {
						if(map[ny][nx] == 'D') {
							isOk = true;
							break OUTTER;
						}
						
						map[ny][nx] = 'V';
						q.offer(new Pos(ny, nx));
					}
				}
			}
			
//			System.out.println("한번 이동");
//			for(int i = 0; i < R; i++) {
//				for(int j = 0; j < C; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			
		}
		
		q.clear();
		fq.clear();
		if(!isOk)
			time = -1;
	}
	
	static class Pos {
		int y;
		int x;
		
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static List<Pos> initialFires = new ArrayList<>();
}
