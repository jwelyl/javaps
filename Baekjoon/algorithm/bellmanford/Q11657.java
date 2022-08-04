import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/11657
 * @difficuly G4
 * @performance 16900KB   264ms
 * @category # 최단거리 찾는 문제(Bellman-Ford 알고리즘)
 * @memo 기본적인 벨먼포드 알고리즘이었지만 오랜만에 풀어서 복습하는 용도로 품
 * @memo Dijkstra Algorithm 때와 다르게 relaxation 과정에서 오버플로우가 발생할 가능성이 크므로 주의
 * @memo 출력 초과를 long 자료형을 사용하여 해결함
 * @etc 
 *  
 */

public class Main {
	static final long INF = Long.MAX_VALUE;
	static final int START = 1;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	
	static class Edge {
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	static List<Edge> edgeList = new ArrayList<Edge>();
	static long[] distance;
	
	static boolean bellmanford() {
		boolean nonNegCycle = true;
		
		distance = new long[N + 1];
		Arrays.fill(distance, INF);
		distance[START] = 0;
		
		for(int i = 1; i <= N - 1; i++) {
			for(int j = 0; j < edgeList.size(); j++) {
				int from = edgeList.get(j).from;
				int to = edgeList.get(j).to;
				int cost = edgeList.get(j).cost;
				
				long after = distance[from] == INF ? INF : distance[from] + cost;
				
				if(after < distance[to]) {
					distance[to] = after;
				}
			}
		}
		
		for(int j = 0; j < edgeList.size(); j++) {
			int from = edgeList.get(j).from;
			int to = edgeList.get(j).to;
			int cost = edgeList.get(j).cost;
			
			long after = distance[from] == INF ? INF : distance[from] + cost;
			
			if(after < distance[to]) {
				nonNegCycle = false;
				break;
			}
		}
		
		return nonNegCycle;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < M; i++) {
			int from, to, cost;
			tokens = new StringTokenizer(bf.readLine());
			
			from = Integer.parseInt(tokens.nextToken());
			to = Integer.parseInt(tokens.nextToken());
			cost = Integer.parseInt(tokens.nextToken());
			
			edgeList.add(new Edge(from, to, cost));
		}
		
//		boolean result = bellmanford();
//		System.out.println("nonNegCycle = " + result);
		
		if(bellmanford()) {
			for(int i = 2; i <= N; i++)
				sb.append((distance[i] == INF ? -1 : distance[i]) + "\n");
		} else {
			sb.append(-1 + "\n");
		}
		
		System.out.print(sb);
	}
}