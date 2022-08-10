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
 * @see https://www.acmicpc.net/problem/11725
 * @difficuly S2
 * @performance 71780KB   504ms
 * @category # DFS/BFS를 탐색, 트리의 부모 찾기
 * @memo 문제 자체는 전혀 어렵지 않고 진작에 해결했지만 실행 시간을 줄이기 위해 다시 봄
 * @memo StringBuilder를 꼭 사용하자. 2196ms 걸리던 코드가 StringBuilder로 대체하니까 576ms로 크게 감소했다.
 * @memo ArrayList<ArrayList<Integer>> 대신 ArrayList<Integer>[]를 사용하는 것을 고려해보자. 큰 차이는 아니지만 미세하게라도 더 빠른것 같다.
 * @etc 단 ArrayList<Integer>[]을 이용할 경우 배열을 할당할 때 raw 타입으로 할당해야 Compile error가 나지 않는다. 대신 경고가 뜬다.
 *  
 */

public class BOJ_11725 {
	final static int ROOT = 1;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
//	static List<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer>[] tree;
	
	static boolean[] visited;
	static int[] parents;
	static int N;
	
	static void dfs(int v) {
		visited[v] = true;
		
		for(int i = 0; i < tree[v].size(); i++) {
			int child = tree[v].get(i);
			
			if(!visited[child]) {
				parents[child] = v;
				dfs(child);
			}
		}
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		visited[ROOT] = true;
		q.offer(ROOT);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < tree[cur].size(); i++) {
				int child = tree[cur].get(i);
				
				if(!visited[child]) {
					visited[child] = true;
					parents[child] = cur;
					q.offer(child);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		visited = new boolean[N + 1];
		parents = new int[N + 1];
		
		tree = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++)
			tree[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < N - 1; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		dfs(ROOT);
//		bfs();
		
		for(int i = 2; i < N + 1; i++) 
//			System.out.println(parents[i]);
			sb.append(parents[i]).append("\n");
		System.out.println(sb);
	}
}
