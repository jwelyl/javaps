import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/19238
 * @difficuly G3
 * @performance 57636KB 328ms
 * @category #	구현, 시뮬레이션, BFS, 독해력
 * @memo	# 16236 아기 상어랑 비슷한 문제인것 같아서 복습하는 의미로 풀어봤고, 실제로 비슷했다.
 * @memo	# 아기 상어랑 비슷해서 지문 이해가 쉬웠고 그래서 빠뜨린 내용 없이 필요한 내용을 구현할 수 있었다.
 * @memo	# 물론 단 한번의 오류 없이 맞지는 않았고 디버깅 과정에서 추가된 것들도 있고 불필요해진 부분들도 있었지만 해결했다는 것에 의의를 뒀다.
 * @memo	# 단순 테스트 케이스를 맞춘 것 뿐만 아니라 문제에서 제시한 것들을 충실히 구현하니 한 번의 제출로 바로 AC를 받을 수 있었다.(출력 초과는 제외)
 * @etc		# 지문 이해력, 구현 능력, BFS 활용법, BFS에서 조건에 맞는 격자로 이동하기 등 여러 연습이 가능한 문제같다.
 * 
 */

public class BOJ_19238 {
	final static int NONE = -1;
	final static int INF = Integer.MAX_VALUE;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M; // 맵 크기 N * N, 손님 수 M
	static int[][] map; // 입력받은 map
	static int[][] dist; // 택시 위치(sy, sx)에서 각 격자까지의 최단거리 저장 배열
	static int sy, sx, sf, sr; // 택시 현재 위치 (sy, sx), 연료 보유량 sf, 소모 연료량 sr
	static int cnt = 0;			//	데려다준 손님 수
	static boolean[] ok;		//	손님 무사히 데려다줬는지
	static boolean isOk = true; // 모든 손님을 무사히 데려다줬는지

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		sf = Integer.parseInt(tokens.nextToken());

		map = new int[N][N];
		dist = new int[N][N];

		startPos = new int[M][2];
		destPos = new int[M][2];
		ok = new boolean[M];

		startList = new ArrayList<Pos>();
		destList = new ArrayList<Pos>();

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}

		tokens = new StringTokenizer(bf.readLine());
		sy = Integer.parseInt(tokens.nextToken()) - 1;
		sx = Integer.parseInt(tokens.nextToken()) - 1;

		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(bf.readLine());
			startPos[i][0] = Integer.parseInt(tokens.nextToken()) - 1;
			startPos[i][1] = Integer.parseInt(tokens.nextToken()) - 1;
			destPos[i][0] = Integer.parseInt(tokens.nextToken()) - 1;
			destPos[i][1] = Integer.parseInt(tokens.nextToken()) - 1;
		}
		
		simulation();
		
		if(isOk)
			System.out.println(sf);
		else
			System.out.println(-1);
	}

	// 손님 관련 class
	static class Pos implements Comparable<Pos> {
		int num; // 손님 번호
		int y; // 출발지 또는 도착지 위치 (y, x)
		int x;
		boolean isDest; // 출발지면 false, 목적지면 true
		int dist; // 현재 택시 위치 (sy, sx)에서 (y, x)까지 최단거리(도달 불가하면 INF)

		Pos(int num, int y, int x, boolean isDest, int dist) {
			this.num = num;
			this.y = y;
			this.x = x;
			this.isDest = isDest;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Pos [num=" + num + ", y=" + y + ", x=" + x + ", isDest=" + isDest + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(Pos p) {
			int ret = Integer.compare(this.dist, p.dist);

			if (ret == 0) {
				ret = Integer.compare(this.y, p.y);
				if (ret == 0)
					ret = Integer.compare(this.x, p.x);
			}

			return ret;
		}
	}

	static int[][] startPos;
	static int[][] destPos;

	// 각각 손님의 출발지, 도착지 목록
	static List<Pos> startList;
	static List<Pos> destList;

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}

	static void resetDist() { // 거리 계산 전 이전 거리 초기화
		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], NONE);
	}

	// bfs로 (sy, sx)에서 각 위치까지 최단거리 계산
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		int[] input = { sy, sx };
		
//		System.out.println("택시 출발점 정보 = " + Arrays.toString(input));
		
		resetDist();
		dist[sy][sx] = 0;
		q.offer(input);

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int[] output = q.poll();
				int cy = output[0];
				int cx = output[1];

				for (int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					int[] nInput = new int[2];

					if (!isIn(ny, nx) || map[ny][nx] == 1) // 다음 칸이 격자 벗어나거나 벽일 경우
						continue;
					if (dist[ny][nx] != NONE) // 이미 거리 결정된 경우
						continue;

					dist[ny][nx] = dist[cy][cx] + 1;
					nInput[0] = ny;
					nInput[1] = nx;
					q.offer(nInput);
				}
			} //
		} // while-end
	} // bfs-end

	static void simulation() {
		while (true) {
			Pos sPos = null; // 가장 가까운 손님 시작 위치
			Pos dPos = null; // 그 손님의 도착 위치

			startList = new ArrayList<Pos>();
			destList = new ArrayList<Pos>();

			bfs(); // (sy, sx)로부터 각 격자까지 거리 구함
			
//			System.out.println("====================출발점까지 거리=======================");
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++)
//					System.out.print(dist[i][j] + " ");
//				System.out.println();
//			}
//			System.out.println("==========================================================");

			for (int i = 0; i < M; i++) {
				int startY = startPos[i][0];
				int startX = startPos[i][1];
				int startDist = dist[startY][startX];

				if (startDist != NONE && !ok[i]) { // 아직 안 데려다 줬고, 도달할 수 있을 경우
					startList.add(new Pos(i, startY, startX, false, startDist));
				}
			}

			Collections.sort(startList);
			sPos = startList.size() > 0 ? startList.get(0) : null;
			
//			System.out.println("손님 출발점 " + sPos);
			if(sPos == null) {
				if(cnt < M)
					isOk = false;
				break;
			}

			if (sPos.dist > sf) {	//	시작점에서 손님 있는곳까지 갈 연료가 부족할 경우
				isOk = false;
				break;
			}
			
			sy = sPos.y;
			sx = sPos.x;
			sf -= sPos.dist;	//	기름 소모
//			System.out.println("출발점 도착 후 남은 기름 = " + sf);
			
			bfs();	//	새로 바뀐 (sy, sx)로부터 각 격자까지 거리 구함
			
//			System.out.println("====================도착점까지 거리=======================");
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++)
//					System.out.print(dist[i][j] + " ");
//				System.out.println();
//			}
//			System.out.println("==========================================================");

			int num = sPos.num;				//	손님 번호
			int destY = destPos[num][0];	//	손님의 도착지 정보
			int destX = destPos[num][1];
			int destDist = dist[destY][destX];
			
			if(destDist == NONE || destDist > sf) {	//	도착점까지 갈 연료가 부족할 경우
				isOk = false;
				break;
			}
			
			dPos = new Pos(num, destY, destX, true, destDist);
//			System.out.println("손님 도착점 " + dPos);
			
			sy = destY;
			sx = destX;
			sf -= destDist;
//			System.out.println("도착점 도착 후 남은 기름 = " + sf);
			sf += (2 * destDist);
//			System.out.println("재충전 후 남은 기름 = " + sf);
			
			ok[num] = true;
			cnt++;
		}
	}
}
