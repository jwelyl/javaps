import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
	static int N, M, V;
	static int[][] adjMatrix;
	static boolean[] visited;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static void reset() {
		Arrays.fill(visited, false);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		V = Integer.parseInt(tokens.nextToken());

		adjMatrix = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}
		
		dfs(V);
		sb.append("\n");
		reset();
		bfs();
		sb.append("\n");
		
		System.out.println(sb);
	}
	
	static void dfs(int v) {
		visit(v);
		
		for(int i = 1; i <= N; i++) {
			if(adjMatrix[v][i] == 1 && !visited[i])
				dfs(i);
		}
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		visit(V);
		q.offer(V);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 1; i <= N; i++) {
				if(adjMatrix[cur][i] == 1 && !visited[i]) {
					visit(i);
					q.offer(i);
				}
			}
		}
	}
	
	static void visit(int v) {
		visited[v] = true;
		sb.append(v).append(" ");
	}

}
