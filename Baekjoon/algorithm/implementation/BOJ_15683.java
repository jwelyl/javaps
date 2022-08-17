import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {
	static int N, M;
	static int[][] map;
	static int numOfWalls = 0;
	static int ans = Integer.MAX_VALUE;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new CCTV(map[i][j], i, j));
				}
				else if(map[i][j] == 6)
					numOfWalls++;
			}
		}
		
		numOfCCTV = cctvList.size();
		perm(0, new int[numOfCCTV]);
		System.out.println(ans);
	}
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static class CCTV {	//	CCTV 정보
		int type;		//	CCTV 위치
		int y;
		int x;
		int[] dir = new int[4];
		
		{
			for(int i = 0; i < 4; i++)
				dir[i] = -1;
		}
		
		CCTV(int type, int y, int x) {
			this.type = type;
			this.y = y;
			this.x = x;
			
			if(type == 1)
				dir[0] = 0;
			else if(type == 2) {
				dir[0] = 0;
				dir[1] = 2;
			}
			else if(type == 3) {
				dir[0] = 0;
				dir[1] = 3;
			}
			else if(type == 4) {
				dir[0] = 0;
				dir[1] = 3;
				dir[2] = 2;
			}
			else {
				dir[0] = 0;
				dir[1] = 1;
				dir[2] = 2;
				dir[3] = 3;
			}
		}
		
		void rotate() {
			for(int d = 0; d < 4; d++) {
				if(dir[d] != -1)
					dir[d] = (dir[d] + 1) % 4;
			}
		}
		
		void rotate(int n) {
			for(int i = 0; i < n; i++)
				rotate();
		}
		
		void reset() {
			if(this.type == 1)
				dir[0] = 0;
			else if(this.type == 2) {
				dir[0] = 0;
				dir[1] = 2;
			}
			else if(this.type == 3) {
				dir[0] = 0;
				dir[1] = 3;
			}
			else if(this.type == 4) {
				dir[0] = 0;
				dir[1] = 3;
				dir[2] = 2;
			}
			else {
				dir[0] = 0;
				dir[1] = 1;
				dir[2] = 2;
				dir[3] = 3;
			}
		}
	}
	
	static List<CCTV> cctvList = new ArrayList<>();
	static int numOfCCTV; 
	
	static void perm(int nth, int[] numOfRotation) {
		if(nth == numOfCCTV) {
			for(int i = 0; i < numOfCCTV; i++) {
				int cnt = numOfRotation[i];
				cctvList.get(i).rotate(cnt);
			}
			
			int res = calculate();
			
			if(res < ans)
				ans = res;
			
			for(int i = 0; i < numOfCCTV; i++) {
				cctvList.get(i).reset();
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			numOfRotation[nth] = i;
			perm(nth + 1, numOfRotation);
		}
	}
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	static int calculate() {
		int canSee = 0;
		char[][] tmp = new char[N][M];

		for(int i = 0; i < numOfCCTV; i++) {	//	모든 cctv에 대하여
			CCTV cctv = cctvList.get(i);
			int cctvY = cctv.y;
			int cctvX = cctv.x;
			
			for(int d = 0; d < 4; d++) {
				int dir = cctv.dir[d];
				if(dir == -1)
					continue;
				
				for(int cnt = 1; ; cnt++) {
					int ny = cctvY + dy[dir] * cnt;
					int nx = cctvX + dx[dir] * cnt;
					
					if(!isIn(ny, nx) || map[ny][nx] == 6)
						break;
					
					if(map[ny][nx] == 0 && tmp[ny][nx] != '#') {
						tmp[ny][nx] = '#';
						canSee++;
					}
				}
			}
			
		}
		
		return N * M - canSee - numOfCCTV - numOfWalls;
	}
}
