import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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

public class BOJ_1516 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int[] indegrees;
	static int[] buildTimes;
	static int[] completeTimes;
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		indegrees = new int[N + 1];
		buildTimes = new int[N + 1];
		completeTimes = new int[N + 1];
		
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		
		Arrays.fill(completeTimes, Integer.MIN_VALUE);
	
		for(int num = 1; num <= N; num++) {
			tokens = new StringTokenizer(bf.readLine());
			
			buildTimes[num] = Integer.parseInt(tokens.nextToken());
			
//			System.out.print("\nnum(" + num + ")의 우선 노드들 : ");
			while(tokens.hasMoreTokens()) {
				int former = Integer.parseInt(tokens.nextToken());
				
				if(former != -1) {
//					System.out.print(former + ", ");
					graph.get(former).add(num);
					indegrees[num]++;
				}
			}
		}
		
//		System.out.print("indegrees : ");
//		for(int i = 1; i <= N; i++)
//			System.out.print(indegrees[i] + " ");
//		System.out.println();
//		
//		System.out.print("buildTimes : ");
//		for(int i = 1; i <= N; i++)
//			System.out.print(buildTimes[i] + " ");
//		System.out.println();
		
		topologySort();
		for(int i = 1; i <= N; i++) 
			sb.append(completeTimes[i]).append("\n");
		
		System.out.println(sb);
		
	}	//	main-end
	
	static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(indegrees[i] == 0) {
				q.offer(i);
				completeTimes[i] = buildTimes[i];
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				
				indegrees[next]--;
				completeTimes[next] = Integer.max(completeTimes[next], completeTimes[cur] + buildTimes[next]);
				
				if(indegrees[next] == 0)
					q.offer(next);
			}
		}
	}
}
