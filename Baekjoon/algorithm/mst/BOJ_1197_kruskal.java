import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1197_kruskal {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int T, V, E;
	static long mstCost;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge e) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, e.cost);
		}
	}
	
//	static List<Edge> edgeList;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws IOException {
//		T = Integer.parseInt(bf.readLine());
		T = 1;
		
		for(int t = 1; t <= T; t++) {
//			edgeList = new ArrayList<Edge>();
			
			tokens = new StringTokenizer(bf.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			
			edgeList = new Edge[E];
			
			parents = new int[V + 1];
			for(int i = 1; i <= V; i++)
				parents[i] = i;
			
			for(int i = 0; i < E; i++) {
				int from, to, cost;
				tokens = new StringTokenizer(bf.readLine());
				
				from = Integer.parseInt(tokens.nextToken());
				to = Integer.parseInt(tokens.nextToken());
				cost = Integer.parseInt(tokens.nextToken());
			
//				edgeList.add(new Edge(from, to, cost));
				edgeList[i] = new Edge(from, to, cost);
			}
			
			kruskal();
//			sb.append("#").append(t).append(" ").append(mstCost).append("\n");
			sb.append(mstCost).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int[] parents;
	
	static int findParent(int x) {
		if(x == parents[x])
			return x;
		
		return parents[x] = findParent(parents[x]);
	}
	
	static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x == y)
			return;
		else if(x < y)
			parents[y] = x;
		else
			parents[x] = y;
	}
	
	static void kruskal() {
		int numOfEdge = 0;
		mstCost = 0;
//		Collections.sort(edgeList);
		Arrays.sort(edgeList);
		
		for(int i = 0; i < E; i++) {
//			Edge e = edgeList.get(i);
			Edge e = edgeList[i];
			int x = e.from;
			int y = e.to;
			int cost = e.cost;
			
			x = findParent(x);
			y = findParent(y);
			if(x == y)
				continue;
			
			union(x, y);
			mstCost += cost;
			numOfEdge++;
			
			if(numOfEdge == V - 1)
				break;
		}
		
	}
}