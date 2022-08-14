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
 * @see https://www.acmicpc.net/problem/
 * @difficuly
 * @performance KB ms
 * @category #
 * @memo
 * @etc
 * 
 */

public class BOJ_17141 {
	static int N, M;
	static int[][] lab;
	static int[][] cost;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	// 전체 바이러스 놓을 수 있는 위치
	static List<Pos> virusPos = new ArrayList<>();

	static int mustVisit = 0; // 바이러스가 모두 퍼지기 위해 방문해야 하는 칸 수
	static int actualVisit = 0; // 바이러스가 실제로 퍼진 칸 수
	static int time = 0; // 각 경우마다 걸리는 최소 시간
	static int ans = Integer.MAX_VALUE; // 모든 경우 통틀어 최소 시간

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		lab = new int[N][N];
		cost = new int[N][N];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());

			for (int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(tokens.nextToken());

				if (lab[i][j] != 1) {
					mustVisit++;
					
					if (lab[i][j] == 2)
						virusPos.add(new Pos(i, j));
				}
			}
		}

		combination(0, 0, new int[M]);
		
//		System.out.println("++++++++++++++++++++++최종 정답+++++++++++++++++++++++");
		System.out.println(ans);
	}

	// 바이러스 놓을 수 있는 위치
	static class Pos {
		int y;
		int x;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "[" + y + ", " + x + "]";
		}
	}

	// 모든 경우의 수(조합) 계산
	// nth번째 뽑기, startIdx부터 뽑아서, result[nth]에 index 저장
	static void combination(int nth, int startIdx, int[] result) {
		if (nth == M) { // M개 모두 뽑았을 경우
//			System.out.println("경우의 수");
//			for(int i = 0; i < result.length; i++)
//				System.out.println(virusPos.get(result[i]));
			reset();		//	퍼뜨리기 전 reset
			bfs(result);	//	퍼뜨림
			
//			System.out.println("***************bfs 후 퍼진 상태***************");
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.print(cost[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("***************방문한 갯수***************");
//			System.out.println("방문해야 할 갯수 = " + mustVisit);
//			System.out.println("방문한 갯수 = " + actualVisit);
//			System.out.println("시간 = " + time);
//			System.out.println("****************************************");
			
			if(time == -1) {	//	만약 다 퍼지지 못한 경우
				if(ans == Integer.MAX_VALUE) {	//	만약 지금까지 단 한번도 다 퍼지지 못한 경우
					ans = -1;
				}
			}
			else {	//	다 퍼진 경우
				if(ans == -1) {	//	이전까지 단 한번도 다 퍼지지 못한 경우
					ans = time;
				}
				else {
					ans = Math.min(ans, time);
				}
			}

			return;
		}

		// 바이러스 놓을 수 있는 전체 위치 개수 중 M개를 뽑음
		for (int i = startIdx; i < virusPos.size(); i++) {
			result[nth] = i; // 선택한 virus pos의 index 저장
			combination(nth + 1, i + 1, result);
		}
	}

	// 방문하려는 (y, x)가 유효 범위 내인지 check
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	// 바이러스 놓는 경우마다 탐색 전에 초기화시킴
	static void reset() {
		time = 0; // timer 0으로 맞춰놓음
		actualVisit = 0;	//	방문한 칸 수 0으로 설정

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				cost[i][j] = -1;	//	모든 칸 방문 안한 상태로 변경
		}
	}

	// 뽑은 M개 바이러스 놓은 위치 바탕으로 퍼뜨림
	static void bfs(int[] result) {
		Queue<Pos> q = new LinkedList<Pos>();

		// 처음 바이러스 놓은 곳 방문 시간 0으로 하고 큐에 삽입
		for (int i = 0; i < result.length; i++) {
			Pos p = virusPos.get(result[i]);
			int y = p.y;
			int x = p.x;

			actualVisit++;
			cost[y][x] = time;
			q.offer(p);
		}

		while (!q.isEmpty()) {
			int size = q.size();
			time++; // 다음에 퍼질 곳은 1초 증가 상태
//			System.out.println("time = " + time);

			// 1초 전의 virus들을 모두 꺼냄
			for (int i = 0; i < size; i++) {
				Pos p = q.poll();
				int cy = p.y;
				int cx = p.x;

				// 각 뽑은 바이러스 기준으로 4방 탐색
				for (int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];

					if (!isIn(ny, nx) || lab[ny][nx] == 1) // 범위 벗어나거나 벽일 경우 skip
						continue;

					if (cost[ny][nx] != -1) // 이미 방문한 경우
						continue;

					actualVisit++;
					cost[ny][nx] = time;
					q.offer(new Pos(ny, nx));
				}
			}
		}	//	while
		
		if(actualVisit != mustVisit)	//	모든 칸에 다 퍼지지 못한 경우
			time = -1;
		else time--;
	}	//	bfs
}
