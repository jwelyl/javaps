import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_7576 {
	final static int RIPEN = 1, NRIPEN = 0, EMPTY = -1;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	static int[][] tomatoes;
	static Queue<Tomato> q = new LinkedList<>();
	
	static int ripen = 0;	//	익은 토마토 개수
	static int total = 0;	//	전체 칸 수 N * M - 빈칸 개수
	static int time = 0;
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	static void bfs() {
//		System.out.println("bfs!!");
		while(!q.isEmpty()) {
//			System.out.println("bfs-while");
			int size = q.size();
//			System.out.println("time = " + time);
			time++;
			
			for(int i = 0; i < size; i++) {
				Tomato t = q.poll();
				int cy = t.y;
				int cx = t.x;
				
				for(int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					
					if(!isIn(ny, nx) || tomatoes[ny][nx] != NRIPEN)
						continue;
					
					tomatoes[ny][nx] = RIPEN;
					ripen++;
//					Tomato nt = new Tomato(ny, nx);
//					System.out.println(nt);
//					q.offer(nt);
					q.offer(new Tomato(ny, nx));
				}
			}
		}
		time--;
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
	
		total = N * M;
		tomatoes = new int[N][M];
		
//		System.out.println("there");
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				tomatoes[i][j] = Integer.parseInt(tokens.nextToken());
				if(tomatoes[i][j] == RIPEN) {
					ripen++;
					q.offer(new Tomato(i, j));
				}
				else if(tomatoes[i][j] == EMPTY)
					total--;
			}
		}
//		System.out.println("Here!");
		
		bfs();
		
		if(ripen != total)
			System.out.println(-1);
		else 
			System.out.println(time);
	}
	
	static class Tomato {
		int y;
		int x;
		
		Tomato(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "tomato(" + y + ", " + x + ")";
		}
	}
}