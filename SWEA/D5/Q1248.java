package ssafy;

import java.util.*;

public class Q1248 {
	final static int NONE = -1, ROOT = 1;
	static int T, V, E, V1, V2;
	static int ancestor, treeSize;
	static boolean[] visited;
	static int[] parents;
	static int[] depth;
	static ArrayList<ArrayList<Integer>> tree;
	static Scanner sc = new Scanner(System.in);
	
	static void init() {
		ancestor = 0;
		treeSize = 0;
		
		parents = new int[V + 1];
		depth = new int[V + 1];
		visited = new boolean[V + 1];
		
		tree = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < V + 1; i++)
			tree.add(new ArrayList<Integer>());
		
		Arrays.fill(parents, NONE);
		Arrays.fill(depth, 0);
		Arrays.fill(visited, false);
	}

	static void setDepth(int v, int d) {
		visited[v] = true;
		depth[v] = d;
		
		for(int i = 0; i < tree.get(v).size(); i++) {
			int c = tree.get(v).get(i);
			
			if(!visited[c])
				setDepth(c, d + 1);
		}
	}
	
	static void countSubtreeSize(int v) {
		visited[v] = true;
		treeSize += 1;
		
		for(int i = 0; i < tree.get(v).size(); i++) {
			int c = tree.get(v).get(i);
			
			if(!visited[c])
				countSubtreeSize(c);
		}
	}
	
	static int findAncestor() {
		int v1 = V1;
		int v2 = V2;
		int p1 = parents[v1];
		int p2 = parents[v2];
		
		while(p1 != p2) {
			if(depth[v1] == depth[v2]) {
				v1 = p1;
				v2 = p2;
			}
			else if(depth[v1] > depth[v2])
				v1 = p1;
			else
				v2 = p2;
			
			p1 = parents[v1];
			p2 = parents[v2];
		}
		
		return p1;
	}
	
	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			V = sc.nextInt();
			E = sc.nextInt();
			V1 = sc.nextInt();
			V2 = sc.nextInt();
			
			init();
			
			for(int i = 0; i < E; i++) {
				int p = sc.nextInt();
				int c = sc.nextInt();
				
				parents[c] = p;
				tree.get(p).add(c);
			}
			
			setDepth(ROOT, 0);
			ancestor = findAncestor();
			
			Arrays.fill(visited, false);
			countSubtreeSize(ancestor);
			
			System.out.println("#" + t + " " + ancestor + " " + treeSize);
		}
	}
}

