import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int n, m, x, y, ans;
	static int nTrees = 0;
	static ArrayList<ArrayList<Integer>> trees = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;	//	0일 경우 방문 x, 방문했을 경우 해당 노드의 루트 노드 번호 입력
	
	static void dfs(int v) {
		visited[v] = true;
		
		for(int i = 0; i < trees.get(v).size(); i++) {
			int next = trees.get(v).get(i);
			
			if(!visited[next])
				dfs(next);
		}
	}
	
	static int getDistance(int from, int to) {
		Queue<Integer> q = new LinkedList<Integer>();
		int input = 1;
		int res = 0;
		visited[from] = true;
		q.offer(from);
		
		while(!q.isEmpty()) {
			int cnt = 0;
			
			for(int i = 0; i < input; i++) {
				int cur = q.poll();
				if(cur == to)
					return res;
				
				for(int j = 0; j < trees.get(cur).size(); j++) {
					int next = trees.get(cur).get(j);
					
					if(next == to) 
						return ++res;
					
					if(!visited[next]) {
						visited[next] = true;
						q.offer(next);
						cnt++;
					}
				}
			}
			
			input = cnt;
			res++;
		}
	
		return -1;
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String line = bf.readLine();
		n = Integer.parseInt(line);
		
		for(int i = 0; i <= n; i++)
			trees.add(new ArrayList<Integer>());
		visited = new boolean[n + 1];
		
		line = bf.readLine();
		tokens = new StringTokenizer(line);
		x = Integer.parseInt(tokens.nextToken()); y = Integer.parseInt(tokens.nextToken());
		
		line = bf.readLine();
		m = Integer.parseInt(line);
		
		for(int i = 0; i < m; i++) {	//	forest 생성
			int a, b;
			
			line = bf.readLine();
			tokens = new StringTokenizer(line);
			
			a = Integer.parseInt(tokens.nextToken()); b = Integer.parseInt(tokens.nextToken());
			trees.get(a).add(b);
			trees.get(b).add(a);
		}
		
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) 
				dfs(i);
		}
		
//		System.out.println(Arrays.toString(visited));
		
//		if(roots[x] != roots[y])
//			ans = -1;
//		else {
//			Arrays.fill(roots, 0);
//			ans = getDistance(x, y);
//		}
		
		Arrays.fill(visited, false);
		ans = getDistance(x, y);
		
		System.out.println(ans);
	}

}