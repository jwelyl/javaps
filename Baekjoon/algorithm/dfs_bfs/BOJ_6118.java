import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class BOJ_6118 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] depth;
	
	static int maxDepth = 0;
	static int ans = 1;
	static int numOfAns = 1;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		graph = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++)
			graph[i] = new ArrayList<Integer>();
		
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		
		for(int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		bfs();
		
		System.out.println(ans + " " + maxDepth + " "  + numOfAns);
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[1] = true;
		depth[1] = 0;
		q.offer(1);
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				int cur = q.poll();
				
				for(int j = 0; j < graph[cur].size(); j++) {
					int next = graph[cur].get(j);
					
					if(!visited[next]) {
						depth[next] = depth[cur] + 1;
						visited[next] = true;
						if(maxDepth < depth[next]) {
							maxDepth = depth[next];
							ans = next;
							numOfAns = 1;
						}
						else if(maxDepth == depth[next]) {
							if(ans > next) 
								ans = next;
							numOfAns += 1;
						}
						q.offer(next);
					}
				}
			}
		}	//	while-end
	}	
}
