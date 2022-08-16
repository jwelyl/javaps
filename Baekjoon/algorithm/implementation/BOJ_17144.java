import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
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

public class BOJ_17144 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int R, C, T;
	static int[][] room;
	static int uppery = -1, upperx = -1;
	static int lowery = -1, lowerx = -1;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());

		room = new int[R][C];

		for (int i = 0; i < R; i++) {
			tokens = new StringTokenizer(bf.readLine());

			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(tokens.nextToken());

				if (room[i][j] == -1) {
					if (uppery == -1 && upperx == -1) {
						uppery = i;
						upperx = j;
					} else {
						lowery = i;
						lowerx = j;
					}
				}
			}
		}

		for (int t = 0; t < T; t++) {
			spreadDust();
			rotate();
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++)
				ans += (room[i][j] > 0 ? room[i][j] : 0);
		}
		
		System.out.println(ans);

//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				System.out.print(room[i][j] + " ");
//			}
//			System.out.println();
//		}

	}

	static class Pos { // 미세먼지 존재하는 칸 좌표, 미세먼지 양
		int y;
		int x;
		int dust;

		Pos(int y, int x, int dust) {
			this.y = y;
			this.x = x;
			this.dust = dust;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "(" + y + ", " + x + ") : " + dust;
		}
	}

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	// 방 범위 안에 있는지 check
	static boolean isIn(int y, int x) {
		return (0 <= y && y < R) && (0 <= x && x < C);
	}

	// 미세먼지가 한 번 확산될 경우
	static void spreadDust() {
		Queue<Pos> q = new LinkedList<Pos>();

		// 미세먼지가 퍼지기 전 미세먼지가 있는 칸들 모두 큐에 삽입
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) {
					q.offer(new Pos(i, j, room[i][j]));
				}
			}
		}
//		System.out.println("queue-size = " + q.size());

		while (!q.isEmpty()) {
			Pos p = q.poll(); // 퍼지기 전 미세먼지 있던 칸 상태(좌표, 미세먼지 양)
			int cy = p.y; // 좌표
			int cx = p.x;
			int cdust = p.dust; // 퍼지기 전 미세먼지 양
			int spread = cdust / 5; // 주변으로 퍼질 양

//			System.out.println(p);

			for (int d = 0; d < 4; d++) { // (cy, cx) 인접한 4칸 확인
				int ny = cy + dy[d];
				int nx = cx + dx[d];

				if (!isIn(ny, nx)) // 범위 벗어날 경우
					continue;

				if (room[ny][nx] == -1) // 주편 칸이 공기청정기일 경우 무시
					continue;

				room[ny][nx] += spread; // 주변 칸으로 퍼짐
				room[cy][cx] -= spread; // 원래 칸에 있던 먼지는 줄어듬
			}

//			System.out.println("(cy, cx)");
//			System.out.println("(" + cy + ", " + cx + ") : " + room[cy][cx]);
		} // while-end
	} // spreadDust-end

	static void rotate() {
		rotateCCW(); // 공기청정기 윗 부분 반시계방향 순환
		rotateCW(); // 공기청정기 아랫부분 시계방향 순환
	}

	static void rotateCCW() {
		int H = uppery + 1;
		int[][] arr = new int[H][C];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < C; j++)
				arr[i][j] = room[i][j];
		}

		for (int i = H - 2; i > 0; i--)
			arr[i][0] = arr[i - 1][0];
		for (int i = 0; i < C - 1; i++)
			arr[0][i] = arr[0][i + 1];
		for (int i = 0; i < H - 1; i++)
			arr[i][C - 1] = arr[i + 1][C - 1];
		for (int j = C - 1; j > 0; j--)
			arr[H - 1][j] = arr[H - 1][j - 1];
		arr[H - 1][1] = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < C; j++)
				room[i][j] = arr[i][j];
		}
	}

	static void rotateCW() {
		int H = R - lowery;
		int[][] arr = new int[H][C];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < C; j++) {
				arr[i][j] = room[i + lowery][j];
			}
		}

		for (int i = 1; i < H - 1; i++)
			arr[i][0] = arr[i + 1][0];
		for (int i = 0; i < C - 1; i++)
			arr[H - 1][i] = arr[H - 1][i + 1];
		for (int i = H - 1; i > 0; i--)
			arr[i][C - 1] = arr[i - 1][C - 1];
		for (int i = C - 1; i > 0; i--)
			arr[0][i] = arr[0][i - 1];
		arr[0][1] = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < C; j++)
				room[i + lowery][j] = arr[i][j];
		}
	}
}
