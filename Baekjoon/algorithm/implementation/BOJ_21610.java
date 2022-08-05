import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/21610
 * @difficuly Gold5
 * @performance 16468KB   256ms
 * @category # 단순 구현, 시뮬레이션
 * @memo 시키는 것을 얼마나 잘 이해하고 충실히 구현하냐의 문제라서 할 말이 많진 않음
 * @memo ny, nx 헷갈려서 이동 오류, 구름이 있었다가 사라진 곳은 매 반복마다 초기화시켜줘야 함
 * @etc 정말 정말 또 풀긴 싫은 문제. 근데 그나마 쉬운 문제
 *  
 */


public class Main {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int[][] field;
	static boolean[][] wasCloud;
	
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int sum = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		field = new int[N][N];
		wasCloud = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++)
				field[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
//		printField("초기 상태");
		
		//	초기 구름
		clouds.add(new Cloud(N - 2, 0));
		clouds.add(new Cloud(N - 2, 1));
		clouds.add(new Cloud(N - 1, 0));
		clouds.add(new Cloud(N - 1, 1));
		
//		printCloudPos("초기 구름 생성 후 위치");
		
		for(int i = 0; i < M; i++) {
			int d, s;
			tokens = new StringTokenizer(bf.readLine());
			d = Integer.parseInt(tokens.nextToken());
			s = Integer.parseInt(tokens.nextToken());
		
			move(d - 1, s);
			rain();
			copyWater();
			makeClouds();
		}
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				sum += field[i][j];
		
//		System.out.println();
		System.out.println(sum);
	}
	
	static class Cloud {
		int y;
		int x;
		
		Cloud(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "(" + this.y + ", " + this.x + "), field = " + field[this.y][this.x];
		}
	}

	//	구름들 모음
	static List<Cloud> clouds = new ArrayList<>();
	static List<Cloud> deleted = new ArrayList<>();
	
	//	1. 구름을 dir 방향으로 cnt 칸만큼 이동시킴
	static void move(int dir, int cnt) {
		for(int i = 0; i < clouds.size(); i++) {
			int cy = clouds.get(i).y;	//	i번째 구름 현재 위치
			int cx = clouds.get(i).x;
			
//			System.out.println("현재 구름 위치 = (" + cy + "," + cx + ")");
			int ny = cy; 
			int nx = cx;
			
			for(int c = 0; c < cnt; c++) {
				ny = ny + dy[dir];
				nx = nx + dx[dir];
				
				if(ny == -1)	//	범위 벗어날 경우 자동 보정
					ny = N - 1;
				else if(ny == N)
					ny = 0;
				
				if(nx == -1)
					nx = N - 1;
				else if(nx == N)
					nx = 0;
				
//				System.out.println("한 칸씩 이동할때마다 구름 위치 = (" + ny + "," + nx + ")");
			}
			
			clouds.get(i).y = ny;	//	구름 이동 후 위치 설정
			clouds.get(i).x = nx;
		}
		
//		printCloudPos(dir + " 방향으로 " + cnt +"만큼 구름 이동 후 위치");
	}
	
	//	2, 3. 구름에서 비가 내린 후 구름 있던 칸의 물의 양 1 증가
	static void rain() {
		for(int i = 0; i < clouds.size(); i++) {
			Cloud c = clouds.get(i);
			
			field[c.y][c.x] += 1;
		}
		
//		printField("비 내린 후");
	}
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
	
	//	4. 물이 증가된 칸에서 물 복사 마법 시전 후 구름 제거
	static void copyWater() {
		for(int i = 0; i < clouds.size(); i++) {	//	제거된 구름들 확인
			Cloud c = clouds.get(i);
			int cy = c.y;
			int cx = c.x;
			int cnt = 0;	//	제거된 구름들 기준 대각선 방향으로 물 있는 지역 개수
			
			for(int d = 1; d < 8; d += 2) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(isIn(ny, nx) && field[ny][nx] > 0)	//	칸 안벗어나면서 물이 존재할 경우
					cnt++;
			}
			
			field[cy][cx] += cnt;	//	해당 칸 물의 양 증가
			wasCloud[cy][cx] = true;	//	구름이 있었던 칸으로 표시
		}
		
		clouds.clear();	//	구름들 완전히 제거
		
//		printField("물 복사 후");
	}
	
	static void resetWasCloud() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				wasCloud[i][j] = false;
	}
	
	//	5. 물이 저장된 양이 2 이상인 모든 곳에서 구름 생성
	static void makeClouds() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(field[i][j] >= 2 && !wasCloud[i][j]) {	//	이전에 구름 사라졌던 곳 제외
					field[i][j] -= 2;
					clouds.add(new Cloud(i, j));
				}
			}
		}
		
		resetWasCloud();
//		printCloudPos("구름 생성 후 위치");
//		printField("구름 생성 후 필드");
	}
	
	static void printField(String msg) {
		System.out.println("\n\n" + msg);
		System.out.println("*************************************");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("*************************************");
	}
	
	static void printCloudPos(String msg) {
		System.out.println(msg);
		System.out.println("###################################");
		for(Cloud c: clouds) {
			System.out.println(c);
		}
		System.out.println("###################################");
	}
	
}