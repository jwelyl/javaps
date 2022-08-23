package algo220823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static List<Integer>[] graph;
	static boolean[] visited;
	static boolean ans = false;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		graph = new ArrayList[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++)
			graph[i] = new ArrayList<Integer>();

		for (int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i = 0; i < N; i++)
			Collections.sort(graph[i]);

		for (int i = 0; i < N; i++) {
			Arrays.fill(visited, false);
			dfs(i, 1);
		}
		
		System.out.println(ans ? 1 : 0);
	}

	static void dfs(int v, int depth) {
		if(ans) {
			return;
		}
		
		if (depth == 5) {
			ans = true;
			return;
		}

		visited[v] = true;

		if (!ans) {
			for (int i = 0; i < graph[v].size(); i++) {
				int next = graph[v].get(i);

				if (!visited[next])
					dfs(next, depth + 1);
			}
		}
		
		visited[v] = false;
		return;
	}
}
