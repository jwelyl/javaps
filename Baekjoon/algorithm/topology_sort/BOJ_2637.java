import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2637
 * @difficuly G2
 * @performance 11800KB		80ms
 * @category # 위상정렬(Topology Sort) + 동적계획법(Dynamic Programming)
 * @memo 문제를 처음 봤을 때에는 그림을 그려본 후 위상정렬을 생각하긴 했지만 재귀적으로 해결해보려 했음
 * @memo 재귀함수를 잘못 구현해서 예시조차 틀렸었지만 논리적으로 허점 없게 구현하는데는 성공함
 * @memo 하지만 재귀함수로 구현한 결과 시간 초과가 났음
 * @memo 위상정렬과 DP를 접목시키면 생각보다 어렵지 않게 해결 가능했음.
 * @memo 단, 한 중간부품, 완제품에 필요한 기본부품이 여러가지라서 2차원 배열을 사용했음.
 * @memo Gold 2 치고는 그렇게 어렵진 않았던 문제 같음
 * @etc 재귀함수나, dfs 등의 방법을 다시 고민해보기. 위상 정렬 없이 dp를 접목시켜서 가능할까?
 * 
 */

public class BOJ_2637 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static ArrayList<Integer[]>[] graph;	//	Node가 [subpart 이름, subpart 개수]인 그래프
	static Set<Integer> base = new HashSet<>();	//	기본 부품 집합
	static int[] indegrees;
	static int[][] dp;						//	dp[partNum][subpartNum] : partNum을 1개 만드는데 필요한 subpartNum
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		
		graph = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++)
			graph[i] = new ArrayList<Integer[]>();
		for(int i = 1; i <= N; i++)
			base.add(i);
		indegrees = new int[N + 1];
		dp = new int[N + 1][N + 1];
		
		for(int i  = 0; i < M; i++) {
			int partName, subpart, numOfSubpart;
			tokens = new StringTokenizer(bf.readLine());
			partName = Integer.parseInt(tokens.nextToken());
			subpart = Integer.parseInt(tokens.nextToken());
			numOfSubpart = Integer.parseInt(tokens.nextToken());
			
			base.remove(partName);	//	partName은 기본 부품이 아니므로 제거
			
			//	partName을 만드는데 subpart가 numOfSubpart개 필요함
			Integer[] node = {partName, numOfSubpart};
			graph[subpart].add(node);
			indegrees[partName]++;
		}
		
		topologySort();
		for(int i = 1; i <= N; i++) {
			if(base.contains(i))
				sb.append(i).append(" ").append(dp[N][i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void topologySort() {
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(indegrees[i] == 0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int subpart = q.poll();
			
			for(int i = 0; i < graph[subpart].size(); i++) {
				Integer[] node = graph[subpart].get(i);
				int partNum = node[0];		//	subpart로 만들 part 번호
				int subpartCnt = node[1];	//	subpart로 partNum 만드는데 필요한 개수
				indegrees[partNum]--;
				
				if(base.contains(subpart)) 	//	subpart가 기본 부품일 경우
					dp[partNum][subpart] += subpartCnt;	//	partNum을 만드는데 subpart가 subpartCnt개 필요함
				else {	//	subpart가 기본 부품이 아닐 경우
					for(int basePart : base) {
						//	subpart를 만드는데 basePart가 dp[subpart][basePart]개 필요함
						//	그런 subpart가 partNum을 만드는데 subpartCnt개 필요함
						//	따라서 partNum을 만드는데에는 basePart가 dp[subpart][basePart] * subpartCnt개 만큼 추가로 필요함
						dp[partNum][basePart] += dp[subpart][basePart] * subpartCnt;
					}
				}
				
				if(indegrees[partNum] == 0)
					q.offer(partNum);
			}
		}
	}
	
}
