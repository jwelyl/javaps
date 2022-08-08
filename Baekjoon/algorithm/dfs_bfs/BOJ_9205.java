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
 * @see https://www.acmicpc.net/problem/9205
 * @difficuly S1
 * @performance 13160KB   120ms
 * @category # BFS를 이용한 탐색, 인접리스트를 이용한 그래프 구축
 * @memo 중요 지점(집, 편의점, 도착점)의 좌표가 주어지니 해당 점들을 정점으로 하는 그래프만 그리면 됨.
 * @memo 좌표 평면 전체를 격자 그래프화 하려하니 메모리 초과가 일어나는 것은 당연하다.
 * @memo 인접 리스트를 만들 때 한번에 이동 가능한 최대 이동거리가 1000m임을 감안하여 두 점 사이의 거리가 1000m 이하이면 두 정점을 이음
 * @memo BFS 과정에서도 visited 체크 잘해야 큐가 안 터지고 정상 종료될 수 있음.
 * @etc 보기보다 까다로웠던 문제였던것 같다. 좌표 평면으로 풀려 하니 좌표 변환하는데에 시간이 오래 걸렸다.
 * @etc 좌표 변환을 위해 코드도 지저분해졌고 결정적으로 방문하는 지점이 너무 많아져서 메모리 초과가 발생했다.
 * @etc 집, 편의점, 도착점 외 중간 지점들을 전혀 고려할 필요가 없었다는 걸 캐치했어야 했다.
 *  
 */

public class BOJ_9205 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static List<ArrayList<Integer>> graph;

	static int T, N;
	static Point[] pList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());

		for (int t = 0; t < T; t++) {
			int y, x;
			
			N = Integer.parseInt(bf.readLine());
			pList = new Point[N + 2];
			visited = new boolean[N + 2];

			tokens = new StringTokenizer(bf.readLine());
			y = Integer.parseInt(tokens.nextToken());
			x = Integer.parseInt(tokens.nextToken());
			pList[0] = new Point(0, y, x);

			for (int i = 1; i <= N; i++) {
				tokens = new StringTokenizer(bf.readLine());
				y = Integer.parseInt(tokens.nextToken());
				x = Integer.parseInt(tokens.nextToken());
				pList[i] = new Point(i, y, x);
			}

			tokens = new StringTokenizer(bf.readLine());
			y = Integer.parseInt(tokens.nextToken());
			x = Integer.parseInt(tokens.nextToken());
			pList[N + 1] = new Point(N + 1, y, x);

			makeGraph();
			bfs();
		}
		
		System.out.println(sb);
	}

	static void bfs() {
		int pNum = pList[0].pNum; // 시작 정점 번호
		Queue<Integer> q = new LinkedList<Integer>();

		visited[pNum] = true; // 시작 정점 방문
		q.offer(pNum);

		while (!q.isEmpty()) {
			int curNum = q.poll(); // 현재 정점 번호

			for (int i = 0; i < graph.get(curNum).size(); i++) {
				int nextNum = graph.get(curNum).get(i); // 다음 정점 번호

				if(visited[nextNum])
					continue;
				
				if (nextNum == N + 1) {	//	다음 정점이 도착 정점일 경우
					sb.append("happy\n");
					return;
				}
				
				visited[nextNum] = true;
				q.offer(nextNum);
			}
		}
		
		sb.append("sad\n");
	}

	static void makeGraph() {
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 2; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < N + 1; i++) {
			for (int j = i + 1; j < N + 2; j++) {
				Point p1 = pList[i];
				Point p2 = pList[j];

				if (mDist(p1, p2) <= 1000) {
					graph.get(p1.pNum).add(p2.pNum);
					graph.get(p2.pNum).add(p1.pNum);
				}
			}
		}
	}

	static class Point {
		int pNum;
		int y;
		int x;

		Point(int pNum, int y, int x) {
			this.pNum = pNum;
			this.y = y;
			this.x = x;
		}
	}

	static int mDist(Point p1, Point p2) {
		return (int) (Math.abs(p1.y - p2.y)) + (int) (Math.abs(p1.x - p2.x));
	}
}
