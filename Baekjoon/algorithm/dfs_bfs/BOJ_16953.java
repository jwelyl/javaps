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

public class BOJ_16953 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int A, B;
	
	static boolean isIn(long num) {
		return (1 <= num && num <= B);
	}
	
	static int time = 1;
	static int ans = -1;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		A = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
	
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<Long> q = new LinkedList<Long>();
		q.offer((long)A);
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for(int i = 0; i < size; i++) {
				long cur = q.poll();
				long[] nexts = {cur * 10 + 1, 2 * cur};
				
				for(long next : nexts) {
					if(isIn(next)) {
						if(next == B) {
							ans = time;
							return;
						}
						q.offer(next);
					}
				}
			}
		}
	}
}