package algo220823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_MST_PRIM {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	static int T, V, E;
	static long mstCost;

	static class Edge implements Comparable<Edge> {
		int next;
		int cost;
		
		Edge(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge e) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, e.cost);
		}
	}
	
	static ArrayList<Edge>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
//		T = 1;

		for (int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(bf.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
		
			graph = new ArrayList[V + 1];
			for(int i = 0; i <= V; i++)
				graph[i] = new ArrayList<Edge>();
			
			visited = new boolean[V + 1];
			for(int i = 0; i < E; i++) {
				int from, to, cost;
				
				tokens = new StringTokenizer(bf.readLine());
				
				from = Integer.parseInt(tokens.nextToken());
				to = Integer.parseInt(tokens.nextToken());
				cost = Integer.parseInt(tokens.nextToken());
			
				graph[from].add(new Edge(to, cost));
				graph[to].add(new Edge(from, cost));
			}
			prim();
			sb.append("#").append(t).append(" ").append(mstCost).append("\n");
		}


		System.out.print(sb);
	}

	static void prim() {
		int numOfEdges = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		mstCost = 0;
		
		visited[1] = true;
		for(int i = 0; i < graph[1].size(); i++) {
			Edge e = graph[1].get(i);
			pq.offer(e);
		}
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int next = e.next;
			int cost = e.cost;
			
			if(visited[next])
				continue;
			
			visited[next] = true;
			mstCost += cost;
			numOfEdges += 1;
			
			if(numOfEdges == V - 1)
				break;
			
			for(int i = 0; i < graph[next].size(); i++) {
				Edge ne = graph[next].get(i);
				int nnext = ne.next;
				
				if(!visited[nnext])
					pq.offer(ne);
			}
		}
	}
}