import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/16236
 * @difficuly G3
 * @performance 22936KB 180ms
 * @category #	구현, 시뮬레이션, BFS, 독해력
 * @memo	# Python, C++에 이어 Java로도 풀어봄. 앞에 두번 풀어본 경험때문에 쉽게 풀 수 있을거라고 생각했다가 개털림.
 * @memo	# 상어의 크기 만큼의 물고기 개수를 잡아먹어야 크기가 1 증가하는데 이 부분을 간과하고 풀었었음.
 * @memo	# 처음 9를 입력 받아 상어 위치를 파악한 후 9를 방치해서 bfs 과정에서 이동에 방해가 되었음. 최단 거리를 잘못 계산하게 됨.
 * @memo	# 상어의 위치가 정해질 때마다 bfs를 한 번 하면 되는데 필드 위의 모든 물고기에 대해 bfs를 했음. 당연히 시간 초과 발생.
 * @memo	# 격자형태 field이므로 visited 배열 대신 상어 위치에서 각 격자까지 거리 저장하는 배열을 사용함.
 * @memo	# 이때 아직 거리 미정인 격자는 -1로, 상어보다 크기가 커서 도달할 수 없는 물고기 격자는 INF로 설정함.
 * @memo	# 지문 잘못 읽은 것도, 9를 제거하는 것도 스스로 찾아냈지만 BFS를 비효율적으로 썼고 이를 캐치하지 못했다.
 * @etc		# 지문 이해력, 구현 능력, BFS 활용법, BFS에서 조건에 맞는 격자로 이동하기 등 여러 연습이 가능한 문제같다.
 * 
 */

public class BOJ_16236 {
	final static int NONE = -1; // 아직 거리가 정해지지 않음
	final static int INF = Integer.MAX_VALUE; // 상어보다 더 커서 갈 수 가 없음
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int[][] field; // 입력 field
	static int[][] dist; // (sy, sx)로부터의 거리

	static int sy, sx, ssize = 2, scnt = 2; // 상어 위치 (sy, sx), 상어 크기, 상어가 성장을 위해 먹어야 할 양
	static int time = 0;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		field = new int[N][N];
		dist = new int[N][N];

		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], NONE);

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				field[i][j] = Integer.parseInt(tokens.nextToken());

				if (field[i][j] == 9) { // 초기 상어 위치 정하기
					sy = i;
					sx = j;
					field[i][j] = 0;
				}
			}
		}
		
		simulation();
		System.out.println(time);

	}
	
	static class Fish {
		int y;
		int x;
		int dist;	//	상어와의 거리
		
		Fish(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
	
	static List<Fish> fishList;

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}

	static void resetDist() {
		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], -1);
	}

	// (sy, sx)를 중심으로 다른 칸들까지의 최단 경로 계산해서 dist에 저장
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		int[] input = { sy, sx, 0 };
		
		fishList = new ArrayList<Fish>();	//	매 턴마다 bfs 후 상어가 먹을 수 있는 물고기 리스트 저장
		resetDist();
		dist[sy][sx] = 0;
		q.offer(input);

		while (!q.isEmpty()) {
			int[] output = q.poll();
			int cy = output[0];
			int cx = output[1];
			int cdist = output[2];

			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				int ndist = cdist + 1;

				if (!isIn(ny, nx)) // 다음 칸이 벗어날 경우
					continue;

				if (dist[ny][nx] == INF) // 다음 칸이 도달 불가능할 경우
					continue;

				if (dist[ny][nx] != NONE) // 이미 거리 알아낸 칸일 경우
					continue;

				if (field[ny][nx] > ssize) { // 상어보다 물고기가 더 클 경우
					dist[ny][nx] = NONE;	//	도달 불가로 설정
					continue;
				}
				
				int[] nInput = new int[3];
				dist[ny][nx] = ndist;
				nInput[0] = ny;
				nInput[1] = nx;
				nInput[2] = ndist;
				q.offer(nInput);
				
				//	빈 칸이 아니면서, 상어보다 크기가 작은 물고기일 경우
				if(field[ny][nx] != 0 && field[ny][nx] < ssize && dist[ny][nx] != NONE && dist[ny][nx] != INF) {
					fishList.add(new Fish(ny, nx, dist[ny][nx]));
				}
			}
		}	//	while-end
	}	//	bfs-end
	
	static void eat(Fish fish) {	//	상어가 물고기 fish를 잡아먹음
		sy = fish.y;
		sx = fish.x;
		field[sy][sx] = 0;	//	필드값 0으로 변경
		
		scnt--;	//	잡아먹어야 할 수 1 감소
		if(scnt == 0) {	//	0이 되면
			ssize++;	//	상어 크기 1 증가
			scnt = ssize;	//	상어가 먹어야 할 증가
		}
		
		time += fish.dist;
	}
	
	static void simulation() {
		while(true) {
			Fish target = null;
			int minDist = Integer.MAX_VALUE;
			int minY = -1, minX = -1;
			
			bfs();	//	bfs로 각 격자까지 거리 찾고 잡아먹을 수 있는 더 작은 물고기들 리스트 생성해옴
			for(Fish fish : fishList) {
				int fy = fish.y;
				int fx = fish.x;
				int fDist = fish.dist;
				
				if(fDist < minDist) {	//	거리가 가장 짧은 물고기
					minDist = fDist;
					minY = fy;
					minX = fx;
					target = fish;
				} else if(fDist == minDist) {	//	거리가 같을 경우
					if(fy < minY) {	//	가장 위쪽에 있는 물고기
						minY = fy;
						minX = fx;
						target = fish;
					} else if(fy == minY && fx < minX) {	//	높이도 같을 경우 가장 왼쪽에 있는 물고기
						minY = fy;
						minX = fx;
						target = fish;
					}
				}
			}
		
			if(target == null)	//	해당하는 물고기가 없을 경우 return
				return;
			else eat(target);	//	해당하는 물고기 잡아먹음
		}
	}
}