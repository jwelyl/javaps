import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

public class BOJ_24484 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M, R;
	static int[] visited;
	static int[] depth;
	static int time = 1;
	
	static List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static long sum = 0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		
		visited = new int[N + 1];
		depth = new int[N + 1];
		Arrays.fill(depth, -1);
		
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
	
		dfs(R, 0);
		
		for(int i = 1; i <= N; i++) {
			sum += ((long)visited[i] * (long)depth[i]);
		}
		
		System.out.println(sum);
	
	}
	
	static void dfs(int v, int d) {
		visited[v] = time++;
		depth[v] = d;
		
		for(int i = 0; i < graph.get(v).size(); i++) {
			int next = graph.get(v).get(i);
			
			if(visited[next] == 0 && depth[next] == -1)
				dfs(next, d + 1);
		}
	}
}
