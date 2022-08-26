import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/1600
 * @difficuly G3
 * @performance 75668KB 452ms
 * @category BFS, 3차원 visited 배열
 * @memo	 2206번 - 벽 부수고 이동하기 풀고 풀어서 그런지 쉽게 해결 가능했음. 사실상 똑같은 문제.
 * @memo	 심지어 1*1일 때 처리해야 하는 것도 똑같음.(다른 방법으로 처리를 고민해보기)
 * @etc		 GOLD 3 치곤 날먹 가능한 문제 (아 달다.)
 * 
 */

public class BOJ_1600 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int K;
	static int W, H;
	static int[][] map;
	static boolean[][][] visited;
	static int time = 0;

	static int[] dy = { 1, 0, -1, 0, -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dx = { 0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < H) && (0 <= x && x < W);
	}

	static class Monkey {
		int y; // 원숭이 좌표
		int x;
		int jump; // 말처럼 뛸 수 있는 횟수

		Monkey(int y, int x, int jump) {
			this.y = y;
			this.x = x;
			this.jump = jump;
		}
	}

	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(bf.readLine());
		tokens = new StringTokenizer(bf.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());

		map = new int[H][W];
		visited = new boolean[K + 1][H][W];

		for (int i = 0; i < H; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < W; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}

		if (W == 1 && H == 1)
			System.out.println(0);
		else {
			bfs();
			System.out.println(time);
		}
	}

	static void bfs() {
		Queue<Monkey> q = new LinkedList<>();
		boolean isOk = false;
		visited[K][0][0] = true;
		q.offer(new Monkey(0, 0, K));

		OUTTER: while (!q.isEmpty()) {
			int size = q.size();
			time++;

			for (int i = 0; i < size; i++) {
				Monkey m = q.poll();
				int cy = m.y;
				int cx = m.x;
				int cjump = m.jump;

				for (int d = 0; d < 12; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					int njump = cjump;

					if (d >= 4) { // 말처럼 이동하는 경우
						if (cjump == 0) // 말처럼 이동할 수 있는 횟수가 없을 경우
							break;
						njump = cjump - 1; // 점프 가능 횟수 1 감소
					}

					if (!isIn(ny, nx) || map[ny][nx] == 1) // 범위 벗어나거나 장애물일 경우
						continue;

					if (!visited[njump][ny][nx]) { // 장애물 아니고 아직 방문 안했을 경우
						if (ny == H - 1 && nx == W - 1) {
							isOk = true;
							break OUTTER;
						}
						visited[njump][ny][nx] = true;
						q.offer(new Monkey(ny, nx, njump));
					}
				}
			}
		} // while-end

		if (!isOk)
			time = -1;
	} // bfs-end
}