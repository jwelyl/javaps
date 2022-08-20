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

public class BOJ_1697 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, K;
	static boolean[] visited = new boolean[100001];
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
	
		bfs();
		System.out.println(time);
	}
	
	static int time = 0;
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
//		visited[N] = true;
		q.offer(N);
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				int cur = q.poll();
				
				visited[cur] = true;
				if(cur == K) {
					return;
				}
				
				int[] delta = {cur - 1, cur * 2, cur + 1};
				
				for(int j = 0; j < 3; j++) {
					int next = delta[j];
					
					if(next < 0 || next > 100000)
						continue;
					
					if(!visited[next]) {
						q.offer(next);
					}
				}
			}
			time++;
		}
	}
}