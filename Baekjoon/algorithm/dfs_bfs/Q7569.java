import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/7569
 * @difficuly G5
 * @performance 120824KB   684ms
 * @category # 너비우선탐색(BFS), 격자문제 응용(3차원)
 * @memo 새로운 하루마다 새로 익은 토마토들을 Queue에 삽입하고 모두 꺼내면서 주변 안 익은 토마토들을 익혀나가면 됨
 * @memo 3차원이지만 2차원 토마토 문제(7576) 문제를 해결했으면 어렵지 않게 해결 가능함
 * @etc 
 *  
 */

public class Main {
	static final int E = -1, RT = 1, NT = 0; // 각각 빈칸, 익은 토마토, 안익은 토마토

	static class RipenTomato {	//	익은 토마토 class
		int z, y, x;	//	토마토 위치

		RipenTomato(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}

	static Queue<RipenTomato> q = new LinkedList<RipenTomato>();	//	bfs에 사용할 Queue, 처음 입력받을 때 익은 토마토들을 저장할 목적으로도 사용

	static int[][][] graph;	//	토마토 저장 상태(편의상 graph라 함)
	static int M, N, H;	//	가로, 세로, 높이

	static int nTomatoes = 0, nRTomatoes = 0, nNRTomatoes = 0; // 각각 전체 토마토, 익은 토마토, 안익은 토마토 개수
	static int days = 0;	//	모두 익을 때까지 소모 일수

	static int[] dz = { 0, 0, 0, 0, -1, 1 };	//	6방향
	static int[] dy = { 0, 1, 0, -1, 0, 0 };
	static int[] dx = { 1, 0, -1, 0, 0, 0 };

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static boolean isIn(int z, int y, int x) {
		return (0 <= z && z < H) && (0 <= y && y < N) && (0 <= x && x < M);
	}
	
	static void bfs() {
		int input = q.size();	//	그날 새로 익은 토마토 개수(해당 토마토들 기준으로 주변에 안익은 토마토들이 익어감)
		
		while(!q.isEmpty()) {
			int tmp = 0;
			for(int i = 0; i < input; i++) {	//	오늘 새로 익은 토마토들
				RipenTomato rt = q.poll();
				int curz = rt.z;
				int cury = rt.y;
				int curx = rt.x;
				
				for(int d = 0; d < 6; d++) {	//	그 중 한 토마토의 주변 6방향 확인
					int nextz = curz + dz[d];
					int nexty = cury + dy[d];
					int nextx = curx + dx[d];
				
					//	다음 칸이 범위를 벗어날 경우
					if(!isIn(nextz, nexty, nextx))
						continue;
					
					//	다음 칸이 이미 익은 토마토이거나 빈 칸일 경우
					if(graph[nextz][nexty][nextx] == E || graph[nextz][nexty][nextx] == RT)
						continue;
				
					nNRTomatoes -= 1;	//	덜 익은 토마토 1개 감소
					nRTomatoes += 1;	//	익은 토마토 1개 증가
					
					if(nRTomatoes == nTomatoes && nNRTomatoes == 0) {	//	모든 토마토가 익었을 경우
						days++;	//	그 날 하루 추가
						return;
					}
					
					tmp++;				//	오늘 하루 새로 익은 토마토 1개 증가
					graph[nextz][nexty][nextx] = RT;
					q.offer(new RipenTomato(nextz, nexty, nextx));
				}	//	익은 토마토 기준 6방향 확인
			}	//	하루에 새로 익을 토마토 구하기
			
			input = tmp;
			days++;
		}	//	while-end
		days = -1;	//	모든 토마토가 익지 못할 경우
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());

		graph = new int[H][N][M];

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				tokens = new StringTokenizer(bf.readLine());
				for (int m = 0; m < M; m++) {
					graph[h][n][m] = Integer.parseInt(tokens.nextToken());

					if (graph[h][n][m] != E) {	//	빈 칸이 아닐 경우
						nTomatoes++;
						
						if (graph[h][n][m] == RT) { // 익은 토마토의 경우
							nRTomatoes++;
							q.offer(new RipenTomato(h, n, m));
						}
						else	// 안익은 토마토의 경우
							nNRTomatoes++;
					}
				}
			}
		}
		
//		System.out.println("--------------------정답-----------------------");
		if(nTomatoes == nRTomatoes)	//	처음 입력받았을 때 익은 토마토 수랑 전체 토마토 수가 같을 경우, 즉 처음부터 모두 익은 상태인 경우
			System.out.println(0);
		else {
			bfs();	// bfs로 익은 토마토들을 중심으로 안익은 토마토들도 익혀나감
			System.out.println(days);
		}

	}
	
	static void printGraph() {
		System.out.println("***********************************************************");
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				for(int m = 0; m < M; m++) {
					System.out.print(graph[h][n][m] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println("***********************************************************");
		System.out.println("전체 토마토 개수 = " + nTomatoes);
		System.out.println("안익은 토마토 개수 = " + nNRTomatoes);
		System.out.println("익은 토마토 개수 = " + nRTomatoes);
		System.out.println("***********************************************************");
	}
}