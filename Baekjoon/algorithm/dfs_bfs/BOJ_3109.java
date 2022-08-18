import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_3109 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int R, C;
	static char[][] map; // 지도
	static boolean[][] visited; // 해당 칸에 이미 파이프가 깔려있는지

	static int[] dy = { -1, 0, 1 };
	static int[] dx = { 1, 1, 1 };
	static int ans = 0;

	static boolean isIn(int y, int x) { // 범위 벗어나는지 check
		return (0 <= y && y < R) && (0 <= x && x < C);
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = bf.readLine().toCharArray();
		}

		for (int row = 0; row < R; row++) {
			if (!visited[row][0])
				dfs(row, 0);
		}

		System.out.println(ans);
	}

	static boolean dfs(int row, int col) {
//		System.out.println("dfs(" + row + ", " + col + ")");

		if (col == C - 1) {
			ans++;
			return true;
		}

		visited[row][col] = true;

		for (int d = 0; d < 3; d++) {
			int nrow = row + dy[d];
			int ncol = col + dx[d];
			
			if(!isIn(nrow, ncol))
				continue;
			if(map[nrow][ncol] == 'x')
				continue;
			if(visited[nrow][ncol])
				continue;
			
			if(dfs(nrow, ncol))
				return true;
		}

		return false;
	}
}
