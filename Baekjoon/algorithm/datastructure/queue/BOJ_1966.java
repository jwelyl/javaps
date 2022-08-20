import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

public class BOJ_1966 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int T, N, M, cnt = 0;
	static int[] priorities;
	static Queue<Document> q;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 0; t < T; t++) {
			cnt = 0;
			tokens = new StringTokenizer(bf.readLine());
			
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			q = new LinkedList<Document>();
			priorities = new int[N];
			
			tokens = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++) {
				int priority = Integer.parseInt(tokens.nextToken());
//				System.out.println("Priority = " + priority);
				boolean isTarget = (i == M);
				
				priorities[i] = priority;
				q.offer(new Document(priority, isTarget));
			}
			
			Arrays.sort(priorities);
			
			while(true) {
				Document p = q.poll();
				
				if(p.priority != priorities[N - 1 - cnt]) {
					q.offer(p);
				}
				else {
					cnt++;
					if(p.isTarget)
						break;
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static class Document {
		int priority;
		boolean isTarget;
		
		Document(int priority, boolean isTarget) {
			this.priority = priority;
			this.isTarget = isTarget;
		}
	}
}
