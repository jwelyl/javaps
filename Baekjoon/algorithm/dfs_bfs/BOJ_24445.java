import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

public class BOJ_24445 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M, R;
	static int[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int time = 1;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
	
		visited = new int[N + 1];
		
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		
		for(int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i = 1; i <= N; i++)
			Collections.sort(graph.get(i), Collections.reverseOrder());
	
		bfs();
		
		for(int i = 1; i <= N; i++)
			sb.append(visited[i]).append("\n");
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[R] = time++;
		q.offer(R);
		
		while(!q.isEmpty()) {
			int cnum = q.poll();
			
			for(int i = 0; i < graph.get(cnum).size(); i++) {
				int next = graph.get(cnum).get(i);
				
				if(visited[next] == 0) {
					visited[next] = time++;
					q.offer(next);
				}
			}
		}
	}
}
