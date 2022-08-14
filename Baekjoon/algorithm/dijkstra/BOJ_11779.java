import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_11779 {
	final static int INF = Integer.MAX_VALUE;	//	두 정점 사이 이어진 간선이 없을 때 INF
	final static int NONE = -1;					//	이전 정점이 없을 때
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	
	static List<ArrayList<Pos>> graph = new ArrayList<ArrayList<Pos>>(); 
	static int[] distance;
	static int[] parents;
	static int start, end;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		
		distance = new int[N + 1];		
		parents = new int[N + 1];
		
		Arrays.fill(distance, INF);
		Arrays.fill(parents, NONE);
		
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<Pos>());
		
		for(int i = 0; i < M; i++) {
			int from, to, cost;
			
			tokens = new StringTokenizer(bf.readLine());
			
			from = Integer.parseInt(tokens.nextToken());
			to = Integer.parseInt(tokens.nextToken());
			cost = Integer.parseInt(tokens.nextToken());
		
			graph.get(from).add(new Pos(to, cost));
		}
		
		tokens = new StringTokenizer(bf.readLine());
		start = Integer.parseInt(tokens.nextToken());
		end = Integer.parseInt(tokens.nextToken());
		
//		for(int i = 1; i <= N; i++) {
//			System.out.print("graph[" + i + "] : ");
//			
//			for(int j = 0; j < graph.get(i).size(); j++) {
//				System.out.print(graph.get(i).get(j));
//			}
//			System.out.println();
//		}
		
		dijkstra(start);
		
//		System.out.print("distance : ");
//		for(int i = 1; i <= N; i++)
//			System.out.print(distance[i] + " ");
//		System.out.println();
//		
//		System.out.print("parents : ");
//		for(int i = 1; i <= N; i++)
//			System.out.print(parents[i] + " ");
//		System.out.println();
		
		
		sb.append(distance[end]).append("\n");
		printPath(end);
	}
	
	static void printPath(int end) {
		Stack<Integer> stack = new Stack<>();
		int pathSize = 0;
		int cur = end;
		
		while(cur != NONE) {
			stack.push(cur);
			cur = parents[cur];
		}
		
		pathSize = stack.size();
//		System.out.println("pathSize = " + pathSize);
		
		sb.append(pathSize).append("\n");
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		sb.append("\n");
		System.out.println(sb);
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		distance[start] = 0;
		pq.offer(new Pos(start, distance[start]));
		
		while(!pq.isEmpty()) {
			Pos nowP = pq.poll();
			int now = nowP.num;
			int nowCost = nowP.cost;
			
			if(nowCost > distance[now])
				continue;
			
			for(int i = 0; i < graph.get(now).size(); i++) {
				Pos nextP = graph.get(now).get(i);
				int next = nextP.num;
				int nextCost = nowCost + nextP.cost;
				
				if(nextCost < distance[next]) {
					distance[next] = nextCost;
					parents[next] = now;
					pq.offer(new Pos(next, distance[next]));
				}
			}
		}
	}
	
	static class Pos implements Comparable<Pos> {
		int num;
		int cost;
		
		Pos(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pos p) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, p.cost);
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + num + " | " + cost + "] ";
		}
	}
}
