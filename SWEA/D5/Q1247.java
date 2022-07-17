package ssafy;

import java.util.*;

class Pos {
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int dist(int x, int y) {
		int res = (x > this.x) ? (x - this.x) : (this.x - x);
		res += ((y > this.y) ? (y - this.y) : (this.y - y));
		
		return res;
	}
}

public class Q1247 {
	static final int INF = 987654321;
	static int res = 0, ans = INF;
	static int N, cx, cy, hx, hy;
	
	static void dfs(int N, int idx, int start,  boolean[] visited, int[] indices, Pos[] customers) {
		visited[start] = true;
		indices[idx] = start;
		
		if(idx == N - 1) {
			res += customers[indices[0]].dist(cx, cy);
			for(int i = 1; i < N; i++)
				res += customers[indices[i]].dist(customers[indices[i - 1]].x, customers[indices[i - 1]].y);
			res += customers[indices[N - 1]].dist(hx, hy);
			
			if(res < ans)
				ans = res;
//			for(int i = 0; i < N; i++)
//				System.out.print(indices[i] + " ");
//			System.out.println();
//			for(int i = 0; i < N; i++)
//				System.out.print("(" + customers[indices[i]].x + ", " + customers[indices[i]].y + ") ");
//			System.out.println();
			res = 0;
		}
		else {
			for(int i = 0; i < N; i++) {
				if(!visited[i])
					dfs(N, idx + 1, i, visited, indices, customers);
			}
		}
		
		visited[start] = false;
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			Pos[] customers;
			int[] indices;
			boolean[] visited;
			ans = INF;
			
			N = sc.nextInt();
			cx = sc.nextInt();
			cy = sc.nextInt();
			hx = sc.nextInt();
			hy = sc.nextInt();
			visited = new boolean[N];
			indices = new int[N];
			Arrays.fill(visited, false);
			
			customers = new Pos[N];
			
			for(int i = 0; i < N; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				customers[i] = new Pos(x, y);
			}
			
			for(int i = 0; i < N; i++)
				dfs(N, 0, i, visited, indices, customers);
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
}