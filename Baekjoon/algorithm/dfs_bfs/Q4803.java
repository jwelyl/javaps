import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;
	static int nTrees = 0;
	static boolean isTree = true;
	
	static void dfs(int v, int p) {
//		System.out.println("dfs(" + v + ", " + p + ")");
		visited[v] = true;
		
		for(int i = 0; i < graph.get(v).size(); i++) {
			int next = graph.get(v).get(i);
			
			if(next == p)
				continue;
			else if(visited[next])
				isTree = false;
			else
				dfs(next, v);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int t = 1;
		while(true) {
			nTrees = 0;
			tokens = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			graph = new ArrayList<ArrayList<Integer>>();
			
			if(N == 0 && M == 0)
				break;
			
			for(int i = 0; i < N + 1; i++)
				graph.add(new ArrayList<Integer>());
			
			visited = new boolean[N + 1];
			
			for(int i = 0; i < M; i++) {
				int a, b;
				tokens = new StringTokenizer(bf.readLine());
				a = Integer.parseInt(tokens.nextToken());
				b = Integer.parseInt(tokens.nextToken()); 
				
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
//			for(int i = 1; i <= N; i++) {
//				System.out.print("graph[" + i + "] : ");
//				for(int j = 0; j < graph.get(i).size(); j++)
//					System.out.print(graph.get(i).get(j) + " ");
//				System.out.println();
//			}
			for(int i = 1; i <= N; i++) {
				isTree = true;
				
				if(!visited[i]) {
					nTrees++;
					dfs(i, -1);
					if(!isTree)
						nTrees--;
				}
			}
			
			if(nTrees > 1)
				sb.append("Case " + t + ": " + "A forest of " + nTrees + " trees.\n");
			else if(nTrees == 1)
				sb.append("Case " + t + ": " + "There is one tree.\n");
			else
				sb.append("Case " + t + ": " + "No trees.\n");
			t++;
		}
		System.out.println(sb.toString());
	}
}