import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

public class BOJ_2252 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	static int[] indegree;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		indegree = new int[N + 1];
		graph = new ArrayList[N + 1];
		
		for(int i = 0; i <= N; i++)
			graph[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			graph[a].add(b);
			indegree[b]++;
		}
		
		topologySort();
	}
	
	static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		List<Integer> ans = new ArrayList<Integer>();
		
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			ans.add(now);
			
			for(int i = 0; i < graph[now].size(); i++) {
				int next = graph[now].get(i);
				
				indegree[next]--;
				
				if(indegree[next] == 0)
					q.offer(next);
			}
		}
		
		for(int num : ans)
			sb.append(num).append(" ");
		System.out.println(sb);
	}
}
