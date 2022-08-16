package boj_implementation;

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

public class BOJ_1913 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int[][] snails;
	static boolean[][] visited;
	static int cnt = 0;
	static int num, target;
	static int ansY = -1, ansX = -1;
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int[] ry = {-1, -1, 1, 1};
	static int[] rx = {1, -1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		num = N * N;
		
		target = Integer.parseInt(bf.readLine());
		
		snails = new int[N][N];
		visited = new boolean[N][N];
		
		int start = 0;
		while(cnt < N * N) {
			int cy = start;
			int cx = start;
			
			for(int d = 0; d < 4; d++) {
				while(isIn(cy, cx) && !visited[cy][cx]) {
					if(num == target) {
						ansY = cy;
						ansX = cx;
					}
					
					cnt++;
					snails[cy][cx] = num--;
					visited[cy][cx] = true;
					
					cy += dy[d];
					cx += dx[d];
				}
				
				cy += ry[d];
				cx += rx[d];
			}
			
			start++;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				sb.append(snails[i][j]).append(" ");
			sb.append("\n");
		}
		sb.append(ansY + 1).append(" ").append(ansX + 1).append("\n");
		
		System.out.println(sb);
	}
}
