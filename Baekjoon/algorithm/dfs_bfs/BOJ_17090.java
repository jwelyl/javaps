import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_17090 {
	final static int ZERO = 0; // 아직 방문 안함.
	final static int NONE = -1; // 이곳에서 출발하면 탈출 불가.
	final static int PASS = 1; // 이곳에서 출발하면 탈출 가능.
	final static int UNKNOWN = 2;	//	방문은 했지만 아직 결정 안난 상태

	static int N, M;
	static char[][] map;
	static int[][] check;
	static int ans = 0;

	static Map<Character, Integer> dirMap = new HashMap<>();
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	static void init() {
		dirMap.put('R', 0);
		dirMap.put('D', 1);
		dirMap.put('L', 2);
		dirMap.put('U', 3);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();

		String[] input = bf.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		map = new char[N][M];
		check = new int[N][M];

		for (int i = 0; i < N; i++)
			map[i] = bf.readLine().toCharArray();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check[i][j] == ZERO) {
					dfs(i, j);
					if (check[i][j] == PASS)
						ans++;
				} else if (check[i][j] == PASS)
					ans++;
			}
		}
		
		System.out.println(ans);
	}

	static boolean isOut(int y, int x) {
		return !((0 <= y && y < N) && (0 <= x && x < M));
	}

	static int dfs(int y, int x) {
		char ch = map[y][x];
		int ny = y + dy[dirMap.get(ch)];
		int nx = x + dx[dirMap.get(ch)];
		check[y][x] = UNKNOWN;

		if(isOut(ny, nx))
			return check[y][x] = PASS;
		
		if(check[ny][nx] == NONE || check[ny][nx] == UNKNOWN) {
			return check[y][x] = NONE;
		}
		else if(check[ny][nx] == PASS)
			return check[y][x] = PASS;
		else
			return check[y][x] = dfs(ny, nx);
	}
}
