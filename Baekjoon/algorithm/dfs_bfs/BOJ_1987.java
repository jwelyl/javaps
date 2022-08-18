import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/1987
 * @difficuly Gold4
 * @performance 12540KB   880ms
 * @category # DFS, Backtracking
 * @memo 방문 조건이 전형적인 DFS/BFS와는 조금 다르지만 어렵지 않은 문제
 * @memo Backtracking 개념을 활용하기 좋은 문제, 현재 위치에서 볼일 다 본 후 방문하지 않았던 것처럼 처리하기
 * @etc BFS로는 안되겠지 아마?
 *  
 */

public class BOJ_1987 {

	static int R, C, ans = 0;
	static char[][] field;
	static boolean[] isUsed = new boolean[26];
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		tokens = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		field = new char[R][C];
		
		for(int i = 0; i < R; i++)
			field[i] = bf.readLine().toCharArray();
		
		dfs(0, 0, 1);
		System.out.println(ans);
	}
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < R) && (0 <= x && x < C);
	}
	
	static void dfs(int y, int x, int num) {
		isUsed[field[y][x] - 'A'] = true;
		
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(isIn(ny, nx) && !isUsed[field[ny][nx] - 'A'])
				dfs(ny, nx, num + 1);
		}
		
		ans = Integer.max(ans, num);
		isUsed[field[y][x] - 'A'] = false;
	}
}
