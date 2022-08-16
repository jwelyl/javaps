import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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

public class BOJ_16197 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static char[][] field;
	
	static int y1 = -1, x1 = -1;
	static int y2 = -1, x2 = -1;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static int ans = Integer.MAX_VALUE;
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		field = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String input = bf.readLine();
			for(int j = 0; j < M; j++) {
				field[i][j] = input.charAt(j);
				
				if(field[i][j] == 'o') {
					if(y1 == -1 && x1 == -1) {
						y1 = i;
						x1 = j;
					}
					else {
						y2 = i;
						x2 = j;
					}
				}
			}
		}
		
		dfs(y1, x1, y2, x2, 0);
		
//		System.out.println("최종 정답");
		System.out.println(ans);
	}
	
	static void dfs(int cy1, int cx1, int cy2, int cx2, int cnt) {
		boolean b1 = isIn(cy1, cx1);
		boolean b2 = isIn(cy2, cx2);
		
		if(cnt > 10 || (!b1 && !b2)) {
			if(ans == Integer.MAX_VALUE)
				ans = -1;
			return;
		}
		
		if(cnt <= 10 && ((b1 && !b2) || (!b1 && b2))) {
			if(ans == -1)
				ans = cnt;
			else
				ans = Math.min(ans, cnt);
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int ny1 = cy1 + dy[d];
			int nx1 = cx1 + dx[d];
			int ny2 = cy2 + dy[d];
			int nx2 = cx2 + dx[d];
			
			if(isIn(ny1, nx1) && field[ny1][nx1] == '#') {
				ny1 = cy1;
				nx1 = cx1;
			}
			
			if(isIn(ny2, nx2) && field[ny2][nx2] == '#') {
				ny2 = cy2;
				nx2 = cx2;
			}
		
			dfs(ny1, nx1, ny2, nx2, cnt + 1);
		}
	}
}
