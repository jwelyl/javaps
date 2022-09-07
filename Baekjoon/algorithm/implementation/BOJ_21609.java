import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/21609
 * @difficuly G2
 * @performance 17872KB 320ms
 * @category # 구현, 시뮬레이션, BFS, 중력 문제(ex. 11559 Puyo Puyo), 배열 회전 문제(90도 회전이라 할만함)
 * @memo 예제를 틀린 이유 : 문제 조건을 또 제대로 안읽음. 무지개 블록 개수로 비교하는 조건을 누락시킴.
 * @memo 0%에서 바로 틀린 이유 : 무지개 블록은 여러 블록 그룹이 중복으로 가질 수 있다함. 즉 어떤 블록 그룹에 포함된 무지개 블록이 다른 블록 그룹에도 포함 가능
 * @memo 그래, 무지개 블록 개수 비교 누락한건 잘못이라 치자. 근데 왜 중복으로 가질 수 있다는건지 이해가 안됨. 결국 질문 게시판 보고 해결했는데 아직도 잘 모르겠음.
 * @etc 그 동안 연습을 많이 해서 그런지 조그만 기능마다 메서드를 나누어 구현하니 생각보단 할만 했음.
 * 
 */

public class BOJ_21609 {
	final static int EMPTY = Integer.MAX_VALUE;
	final static int BLACK = -1, RAINBOW = 0;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static int[][] map;
	static int score = 0;

	public static void main(String[] args) throws IOException {
//		map = new int[][]{{2, 2, -1, 3, 1}, {EMPTY, EMPTY, 2, 0, -1}, {EMPTY, EMPTY, EMPTY, 1, 2}, {-1, EMPTY, 1, 3, 2}, {EMPTY, EMPTY, 2, 2, 1}};
//		N = map.length;

//		printMap();
//		gravity();
//		System.out.println("중력 작용 후");
//		printMap();
//		rotateCCW90();
//		System.out.println("반시계방향 90도 회전 후");
//		printMap();

		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}

		simulation();
		System.out.println(score);
	}

	static void simulation() {
		while (true) {
			if (!findBlockGroup())
				break;

//			System.out.println("가장 큰 블록 그룹 지운 후");
//			printMap();

//			System.out.println("중력 작용 후");
			gravity();
//			printMap();

//			System.out.println("반시계 방향 90도 회전 후");
			rotateCCW90();
//			printMap();

//			System.out.println("중력 작용 후");
			gravity();
//			printMap();
		}
	}

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}

	static class BlockGroup implements Comparable<BlockGroup> {
		int blockY;
		int blockX;
		int blockCnt;
		int blockRainbowCnt;

		BlockGroup(int blockY, int blockX, int blockCnt, int blockRainbowCnt) {
			this.blockY = blockY;
			this.blockX = blockX;
			this.blockCnt = blockCnt;
			this.blockRainbowCnt = blockRainbowCnt;
		}

		@Override
		public int compareTo(BlockGroup o) {
			int ret = -Integer.compare(this.blockCnt, o.blockCnt);
			if (ret == 0) {
				ret = -Integer.compare(this.blockRainbowCnt, o.blockRainbowCnt);
				if (ret == 0) {
					ret = -Integer.compare(this.blockY, o.blockY);
					if (ret == 0) {
						ret = -Integer.compare(this.blockX, o.blockX);
					}
				}
			}
			return ret;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[(" + blockY + ", " + blockX + "), size = " + blockCnt + "]";
		}
	}

	static boolean findBlockGroup() {
		BlockGroup bg = null;
		List<BlockGroup> groupList = new ArrayList<BlockGroup>();
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && (1 <= map[i][j] && map[i][j] <= M))
					bfs(i, j, map[i][j], visited, groupList);
				
				for(int k = 0; k < N; k++) {
					for(int l = 0; l < N; l++) {
						if(visited[k][l] && map[k][l] == RAINBOW)
							visited[k][l] = false;
					}
				}
			}
		}

		if (groupList.size() != 0) {
			Collections.sort(groupList);
			bg = groupList.get(0);
		}

		if (bg == null || bg.blockCnt < 2)
			return false;
//		System.out.println("지울 블록 그룹 정보 = " + bg);
		deleteBlocks(bg.blockY, bg.blockX, map[bg.blockY][bg.blockX]);
		return true;
	}

	static void deleteBlocks(int blockY, int blockX, int color) {
		Queue<int[]> q = new LinkedList<>();
		int deleted = 0;

		map[blockY][blockX] = EMPTY;
		deleted = 1;
		q.offer(new int[] { blockY, blockX });

		while (!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.peek()[1];
			q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];

				// 범위 밖이거나 검정색이거나 빈 칸일 경우
				if (!isIn(ny, nx) || map[ny][nx] == BLACK || map[ny][nx] == EMPTY)
					continue;

				// 같은 색이거나 무지개색이고 아직 안 지운 경우
				if (map[ny][nx] == color || map[ny][nx] == RAINBOW) {
					map[ny][nx] = EMPTY;
					deleted += 1;
					q.offer(new int[] { ny, nx });
				}
			}
		}

//		System.out.println("지워진 블록 개수 = " + deleted);

		score += (deleted * deleted);
//		System.out.println("누적 점수 = " + score);
	}

	// (y, x) : 기준 블록의 좌표, color : 기준 블록의 색상
	static void bfs(int y, int x, int color, boolean[][] visited, List<BlockGroup> groupList) {
		Queue<int[]> q = new LinkedList<>();
		int blockCnt = 0; 			// 블록 그룹에 포함된 블록 개수
		int blockRainbowCnt = 0;	//	블록 그룹에 포함된 무지개 블록 개수
		visited[y][x] = true;
		q.offer(new int[] { y, x });
		blockCnt++;

		while (!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.peek()[1];
			q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];

				// 범위 밖이거나 검정색이거나 빈 칸일 경우
				if (!isIn(ny, nx) || map[ny][nx] == BLACK || map[ny][nx] == EMPTY)
					continue;

				// 같은 색이거나 무지개색이고 아직 방문 안한 경우
				if ((map[ny][nx] == color || map[ny][nx] == RAINBOW) && !visited[ny][nx]) {
					visited[ny][nx] = true;
					blockCnt++;
					if(map[ny][nx] == RAINBOW)
						blockRainbowCnt++;
					q.offer(new int[] { ny, nx });
				}
			}
		} // while-end

		BlockGroup bg = new BlockGroup(y, x, blockCnt, blockRainbowCnt);
		groupList.add(bg);
	}

	// 중력 작용
	static void gravity() {
		// 0열부터 N열까지
		for (int w = 0; w < N; w++) {
			for (int i = 0; i < N - 1; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (map[j][w] == BLACK) // 검은 블록은 움직이지 않음
						continue;

					if (map[j][w] == EMPTY) {
						if (map[j - 1][w] == BLACK)
							continue;
						else {
							map[j][w] = map[j - 1][w];
							map[j - 1][w] = EMPTY;
						}
					}
				}
			}
		}
	} // gravity-end

	// 반시계방향으로 90도 회전
	static void rotateCCW90() {
		int[][] tmp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				tmp[N - 1 - j][i] = map[i][j];
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = tmp[i][j];
	}

	// 맵 출력해봄
	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == EMPTY)
					System.out.print("E ");
				else
					System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
