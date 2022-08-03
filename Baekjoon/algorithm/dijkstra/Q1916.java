import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int from, to;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static class Node implements Comparable<Node> {
		int num;
		int cost;
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node node) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, node.cost);
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static List<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static int[] distance;
 	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		distance[start] = 0;
		
		pq.offer(new Node(start, distance[start]));
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int curNum = curNode.num;
			int curCost = curNode.cost;
			
			if(distance[curNum] < curCost)
				continue;
			
			for(int i = 0; i < graph.get(curNum).size(); i++) {
				int nextNum = graph.get(curNum).get(i).num;
				int nextCost = curCost + graph.get(curNum).get(i).cost;
				
				if(distance[nextNum] > nextCost) {
					distance[nextNum] = nextCost;
					pq.offer(new Node(nextNum, distance[nextNum]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		
		distance = new int[N + 1];
		Arrays.fill(distance, INF);
		
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<Node>());
		
		for(int i = 0; i < M; i++) {
			int from, to, cost;
			
			tokens = new StringTokenizer(bf.readLine());
			from = Integer.parseInt(tokens.nextToken());
			to = Integer.parseInt(tokens.nextToken());
			cost = Integer.parseInt(tokens.nextToken());
			
			graph.get(from).add(new Node(to, cost));
		}
		
		tokens = new StringTokenizer(bf.readLine());
		from = Integer.parseInt(tokens.nextToken());
		to = Integer.parseInt(tokens.nextToken());
		
		dijkstra(from);
		System.out.println(distance[to]);
	}
}