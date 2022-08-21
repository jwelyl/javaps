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

public class BOJ_16928 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	static int[] delta = {1, 2, 3, 4, 5, 6};
	static int[] sl = new int[101];
	static boolean[] visited = new boolean[101];
	
	static boolean isIn(int pos) {
		return (1 <= pos && pos <= 100);
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		for(int i = 0; i < N + M; i++) {
			tokens = new StringTokenizer(bf.readLine());
			int pos = Integer.parseInt(tokens.nextToken());
			int nextPos = Integer.parseInt(tokens.nextToken());
		
			sl[pos] = nextPos;
		}
		
		bfs();
		System.out.println(cast);
	}
	
	static int cast = 0;
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			cast++;
			
			for(int i = 0; i < size; i++) {
				int curPos = q.poll();
				
				for(int d = 0; d < 6; d++) {
					int nextPos = curPos + delta[d];
					
					if(isIn(nextPos) && !visited[nextPos]) {
						if(nextPos == 100)
							return;
						
						if(sl[nextPos] != 0) {
							visited[nextPos] = true;
							nextPos = sl[nextPos];
						}
						visited[nextPos] = true;
						q.offer(nextPos);
					}
				}
			}
		}
	}
}
