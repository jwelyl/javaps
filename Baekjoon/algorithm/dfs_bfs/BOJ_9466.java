import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/9466
 * @difficuly G3
 * @performance 330172KB   2348ms
 * @category # DFS를 이용한 Cycle 판별 + Cycle에 속한 정점 찾기
 * @memo 문제에서 요구하는 것이 Cycle에 속하지 않은 정점 찾기라는 것은 쉽게 간파했음.
 * @memo 모든 정점에 대해 DFS를 하는 방법도 생각을 안해본 것은 아니지만, 매우 비효율적일 것이 자명함
 * @memo 시도해봤자 시간 초과에 걸릴 것이 자명하여 시도하지도 않았음.
 * @memo 최대한 적은 DFS를 통해 그래프 내에 Cycle을 이루는 정점을 찾아야 함
 * @memo 단순 cycle 존재 판별에 비해 어려웠음. 스스로 구현하기 위해서 두 번까지 방문하는 방법을 써봤음.
 * @memo Cycle에 포함되는 정점의 주변 정점들도 두번 방문하는 오류가 발생, 해결을 시도했지만 실패했음.
 * @memo 이전 학부 때 알고리즘 수업에서 배운 내용 + 구글링으로 해결, 코드를 보고 그림을 그려보면 쉽게 이해 가능
 * @etc 맞추긴 했지만 3초 시간 제한에 빠듯하게 맞춤. 다른 사람들은 1초가 채 안되서 맞춤.
 * @etc 다른 사람들과의 코드를 비교해보고 받아들일 수 있는 선에서 내 코드를 개선시킬 필요가 있어보임.
 * @etc 굳이 그래프를 만들지 않고 입력으로 주어지는 배열로도 충분히 해결 가능하긴 함. catch했지만 그래프 연습 차원에서 그래프 생성함.
 
 *  
 */

public class BOJ_9466 {
	static int T, N;
	static List<ArrayList<Integer>> graph;
	static boolean[] visited;			//	방문 상태
	static boolean[] finished;			//	해당 노드의 dfs가 완전히 끝난 경우 true
	static int[] parents;				//	해당 노드 직전에 방문 노드
	static List<Integer> cycleList;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(bf.readLine());
			visited = new boolean[N + 1];
			finished = new boolean[N + 1];
			parents = new int[N + 1];
			
			cycleList = new ArrayList<Integer>();
			
			graph = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i <= N; i++)
				graph.add(new ArrayList<Integer>());
			
			tokens = new StringTokenizer(bf.readLine());
			
			for(int from = 1; from <= N; from++) {
				int to = Integer.parseInt(tokens.nextToken());
				
				graph.get(from).add(to);
			}
			
			for(int i = 1; i <= N; i++) 
				if(!visited[i])
					dfs(i);
//			printGraph();
			sb.append(N - cycleList.size() + "\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int v) {
		visited[v] = true;	//	현재 정점 v 방문 처리
		
		for(int next : graph.get(v)) {
//			System.out.println(next);
			if(!visited[next]) {	//	next를 처음 방문할 경우
				parents[next] = v;	//	next 이전에 v를 방문했음
				dfs(next);
			}
			else if(!finished[next]) {
				findCycle(v, next);
			}
			else;	//	cross하므로 cycle 아님
		}
		
		finished[v] = true;
	}
	
	static void findCycle(int v, int next) {
		cycleList.add(v);
		
		if(v == next)
			return;
		findCycle(parents[v], next);
	}
	
	static void printGraph() {
		System.out.println("\n***********************************************");
		for (int i = 1; i <= N; i++) {
			int size = graph.get(i).size();
			System.out.print(i + " : ");
			if (size != 0)
				System.out.print("-> ");
			for (int j = 0; j < graph.get(i).size(); j++) {
				System.out.print(graph.get(i).get(j));

				if (j != size - 1)
					System.out.print(" -> ");
			}
			System.out.println();
		}
		System.out.println("***********************************************");
		System.out.println(cycleList);
		System.out.println("***********************************************");
	}
}
