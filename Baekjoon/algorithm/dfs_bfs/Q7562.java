import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	static boolean[][] visited;
	
	static int T, I, FY, FX, TY, TX, ans;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static class Pos {
		int y;
		int x;
		
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	};
	
	static boolean canMove(int y, int x) {
		return (0 <= y && y < I) && (0 <= x && x < I) && !visited[y][x];
	}
	
	static void bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		int input = 0;
		
		visited[FY][FX] = true;
		q.offer(new Pos(FY, FX));
		input++;
		
		while(!q.isEmpty()) {
			int tmp = 0;
			for(int i = 0; i < input; i++) {
				Pos p = q.poll();
				int CY = p.y;
				int CX = p.x;
				
				if(CY == TY && CX == TX)
					return;
				
				for(int d = 0; d < 8; d++) {
					int NY = CY + dy[d];
					int NX = CX + dx[d];
					
					if(canMove(NY, NX)) {
						tmp++;
						q.offer(new Pos(NY, NX));
						visited[NY][NX] = true;
					}
				}	//	inner-for
			}	//	outter-for
			
			ans++;
			input = tmp;
		}	//	while
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(bf.readLine());
//		System.out.println("T = " + T);
		
		for(int t = 0; t < T; t++) {
			String line = bf.readLine();
			ans = 0;
			I = Integer.parseInt(line);
//			System.out.println("I = " + I);
			visited = new boolean[I][I];
			
			line = bf.readLine();
			
			tokens = new StringTokenizer(line);
			FY = Integer.parseInt(tokens.nextToken());
			FX = Integer.parseInt(tokens.nextToken());
			
//			System.out.println("FY = " + FY + ", FX = " + FX);
			
			line = bf.readLine();
			
			tokens = new StringTokenizer(line);
			TY = Integer.parseInt(tokens.nextToken());
			TX = Integer.parseInt(tokens.nextToken());
			
//			System.out.println("TY = " + TY + ", TX = " + TX);
			
			bfs();
			
			System.out.println(ans);
		}
	}
}
