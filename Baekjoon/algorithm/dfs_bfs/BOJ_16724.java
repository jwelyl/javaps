import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
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

public class BOJ_16724 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] finished;

	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static Pos[][] parents;

	static Map<Character, Integer> dirMap = new HashMap<>();
	static int numOfCycles = 0;

	static void init() {
		dirMap.put('R', 0);
		dirMap.put('U', 1);
		dirMap.put('L', 2);
		dirMap.put('D', 3);
	}

	public static void main(String[] args) throws IOException {
		init();

		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M];
		finished = new boolean[N][M];
		parents = new Pos[N][M];

		for (int i = 0; i < N; i++)
			map[i] = bf.readLine().toCharArray();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
//					System.out.println("(" + i + ", " + j + ")");
					dfs(i, j);
				}
			}
		}
		
		System.out.println(numOfCycles);
	}

	static class Pos {
		int y;
		int x;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static void dfs(int y, int x) {
//		System.out.println("dfs(" + y + ", " + x + ")");
		visited[y][x] = true;

		int ny = y + dy[dirMap.get(map[y][x])];
		int nx = x + dx[dirMap.get(map[y][x])];

		if (!visited[ny][nx]) {
			parents[ny][nx] = new Pos(y, x);
			dfs(ny, nx);
		}
		else if(!finished[ny][nx]) {
			makeCycle(y, x, ny, nx);
		}

		finished[y][x] = true;
	}
	
	static void makeCycle(int cury, int curx, int ny, int nx) {
//		System.out.println("makeCycle(" + cury + ", " + curx + ", " + ny + ", " + nx + ")");
		
		if(cury == ny && curx == nx) {
//			System.out.println("makeCycle(" + cury + ", " + curx + ", " + ny + ", " + nx + ") 종료");
			numOfCycles++;
			return;
		}
		
		int y = parents[cury][curx].y;
		int x = parents[cury][curx].x;
		makeCycle(y, x, ny, nx);
	}

}