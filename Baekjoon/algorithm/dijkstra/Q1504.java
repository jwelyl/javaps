//package boj_dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int N, E;
	static int a, b, c;
	static int v1, v2;
	static int ans1, ans2;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static int[][] distance;

	static class Node implements Comparable<Node> {
		int vertex;
		int distance;

		Node(int v, int d) {
			vertex = v;
			distance = d;
		}

		@Override
		public int compareTo(Node node) {
			if (this.distance != node.distance)
				return this.distance - node.distance;
			else
				return this.vertex - node.vertex;
		}
	};

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[start][start] = 0;
		pq.offer(new Node(start, distance[start][start]));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int cur = node.vertex;
			int curDist = node.distance;

			if (distance[start][cur] < curDist)
				continue;

			for (int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i).vertex;
				int nextDist = curDist + graph.get(cur).get(i).distance;

				if (nextDist < distance[start][next]) {
					distance[start][next] = nextDist;
					pq.offer(new Node(next, distance[start][next]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());

		distance = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++)
			Arrays.fill(distance[i], INF);

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<Node>());

		for (int i = 0; i < E; i++) {
			tokens = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			c = Integer.parseInt(tokens.nextToken());

			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		tokens = new StringTokenizer(bf.readLine());
		v1 = Integer.parseInt(tokens.nextToken());
		v2 = Integer.parseInt(tokens.nextToken());
		
		dijkstra(v1);
		dijkstra(v2);

		//	1 -> v1 -> v2 -> N
		if(distance[v1][1] == INF || distance[v1][v2] == INF || distance[v2][N] == INF)
			ans1 = INF;
		else
			ans1 = distance[v1][1] + distance[v1][v2] + distance[v2][N];
		
		//	1 -> v2 -> v1 -> N
		if(distance[v2][1] == INF || distance[v2][v1] == INF || distance[v1][N] == INF)
			ans2 = INF;
		else
			ans2 = distance[v2][1] + distance[v2][v1] + distance[v1][N];
	
		if(ans1 == INF && ans2 == INF)
			System.out.println(-1);
		else
			System.out.println(Math.min(ans1, ans2));
	}
}