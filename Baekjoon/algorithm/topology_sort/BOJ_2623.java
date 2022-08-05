import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2623
 * @difficuly G3
 * @performance 12386KB   112ms
 * @category # 위상정렬(Topology sort)
 * @memo 결과적으론 아주 기본적인 위상정렬 문제였음. 위상정렬 알고리즘을 알고, Cycle 발생 시 예외처리를 안다면 매우 쉬웠던 문제
 * @etc 문제 설명문의 예시만 보고 문제를 풀려 해서 입력 예시가 어떤 형식인지를 정확히 파악하지 않아서 문제가 산으로 갔음.
 * @etc 문제 입력 형식을 파악 못해서 cycle이 생기는 그래프가 순서가 정해지는 것으로 오해해 시간을 허비함.
 *  
 */

public class BOJ_2623 {
	static int N, M;	//	가수 수, 보조 PD 수
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int[] indegrees;
	
	static List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();	//	graph
	static List<Integer> order = new ArrayList<>();
	
	static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(indegrees[i] == 0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			order.add(cur);
			
			for(int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				
				indegrees[next] -= 1;
				if(indegrees[next] == 0)
					q.offer(next);
			}
		}
		
		if(order.size() != N)
			sb.append(0 + "\n");
		else {
			for(int num : order)
				sb.append(num + "\n");
		}
		
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		indegrees = new int[N + 1];
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(bf.readLine());
			tokens.nextToken();
			
			int first = Integer.parseInt(tokens.nextToken());
			while(tokens.hasMoreTokens()) {
				int second = Integer.parseInt(tokens.nextToken());
				
				graph.get(first).add(second);
				indegrees[second]++;
				
				first = second;
			}
		}
		
//		printGraph();
		
		topologySort();
	}
	
	static void printGraph() {
		System.out.println("****************************************");
		for(int i = 1; i <= N; i++) {
			System.out.print("graph[" + i + "] : ");
			for(int j = 0; j < graph.get(i).size(); j++) {
				System.out.print(graph.get(i).get(j));
				if(j != graph.get(i).size() - 1)
					System.out.print(" - > ");
			}
			System.out.println();
		}
		System.out.println("****************************************");
		System.out.print("indegrees : ");
		for(int i = 1; i <= N; i++)
			System.out.print(indegrees[i] + " ");
		System.out.println();
	}
}
