package boj;

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
 * @difficuly G4
 * @performance 158068KB   568ms
 * @category # 구현, 시뮬레이션, BFS
 * @memo 상대적으로 쉬운 90도 회전이라 어렵진 않았음. 문제에서 시키는대로 충실히 구현하면 되긴 하는데,
 * @memo 회전시킨 다음에 녹이기도 같이 하는게 마법 1회임을 지문만 보고 캐치 못했음.
 * @memo 이게 문제를 제대로 안읽어서 그런지, 아니면 문제가 기술을 잘못 한건지는 확인해보기
 * @memo But, 제대로 안 읽었을 가능성이 매우 높음.
 * @memo 다행히 테스트케이스로 뭔가 잘못됨을 캐치 가능했지만 실전에선 기대하기 어려움
 * @memo bfs도 어렵진 않았지만 조건을 잘 따져보기.
 * @etc 그래도 중간 출력으로 디버깅하기도 크게 어렵진 않았던 문제. 이렇게만 나오면 좋겠다.
 * 
 */

public class BOJ_20058 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < arrSize) && (0 <= x && x < arrSize);
	}

	static int N, Q, arrSize;
	static int[][] arr;
	static int[] spell;
	static boolean[][] visited;
	static int sum = 0;
	static int maxCnt = 0, cnt = 0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		
		arrSize = (int)Math.pow(2, N);
		arr = new int[arrSize][arrSize];
		visited = new boolean[arrSize][arrSize];
		spell = new int[Q];
		
		for(int i = 0; i < arrSize; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < arrSize; j++)
				arr[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < Q; i++)
			spell[i] = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < Q; i++) {
			fireStorm(spell[i]);
//			System.out.println(i + "번째 마법 " + spell[i] + " 시전 후");
//			printArr();
		}
		
//		printArr();
		
//		System.out.println("총 얼음 양");
		System.out.println(sum);
		
		for(int i = 0; i < arrSize; i++) {
			for(int j = 0; j < arrSize; j++) {
				cnt = 0;
				
				if(!visited[i][j] && arr[i][j] > 0) {
					bfs(i, j);
//					System.out.println("(" + i + ", " + j + ")에서 시작한 덩어리 개수 = " + cnt);
					if(cnt > maxCnt)
						maxCnt =  cnt;
				}
			}
		}
		
//		System.out.println("최대 덩어리 크기");
		System.out.println(maxCnt);
	}
	
	static void fireStorm(int l) {
		int smallSize = (int)Math.pow(2,  l);
		
		for(int i = 0; i < arrSize; i += smallSize) {
			for(int j = 0; j < arrSize; j += smallSize)
				rotateSmallArr(i, j, smallSize);
		}
		
		thaw();
	}
	
	static void rotateSmallArr(int r, int c, int smallSize) {
//		System.out.println("(" + r + ", " + c +")");
		int[][] before = new int[smallSize][smallSize];	//	원본 배열 arr을 복사할 배열
		int[][] after = new int[smallSize][smallSize];	//	복사한 배열 before를 회전시킨 결과 배열 
		
		for(int i = 0; i < smallSize; i++) {	//	배열 복사
			for(int j = 0; j < smallSize; j++)
				before[i][j] = arr[i + r][j + c];
		}
		
		for(int i = 0; i < smallSize; i++) {	//	배열 회전
			for(int j = 0; j < smallSize; j++) 
				after[j][smallSize - 1 - i] = before[i][j];
		}
		
		for(int i = 0; i < smallSize; i++) {	//	배열 복사
			for(int j = 0; j < smallSize; j++)
				arr[i + r][j + c] = after[i][j];
		}
	}
	
	static void thaw() {	//	해동시키기
		int[][] thaw = new int[arrSize][arrSize];	//	해당 칸을 해동시킬지 확인, -1이면 해동, 0이면 그대로
		
		for(int i = 0; i < arrSize; i++) {
			for(int j = 0; j < arrSize; j++) {
				int cnt = 0;	//	주변 칸 얼음 개수
				for(int d = 0; d < 4; d++) {	//	주변 최대 4칸 확인
					int ny = i + dy[d];
					int nx = j + dx[d];
					
					if(!isIn(ny, nx))
						continue;
					
					if(arr[ny][nx] > 0)	//	주변 칸이 얼음일 경우
						cnt++;
				}
				if(cnt < 3)		//	주변에 얼음이 3개보다 적을 경우
					thaw[i][j] = -1;	//	녹여야함
			}
		}
		
		sum = 0;	//	녹이면서 총 얼음 합도 같이 계산
		for(int i = 0; i < arrSize; i++) {
			for(int j = 0; j < arrSize; j++) {
				arr[i][j] += thaw[i][j];
				if(arr[i][j] < 0)	//	한 칸 얼음이 모두 녹은 경우(0 이하)
					arr[i][j] = 0;	//	0으로 설정
				sum += arr[i][j];
			}
		}
	}
	
	static void bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		int[] input = {y, x};
		
		for(int i = 0; i < arrSize; i++) {
			for(int j= 0; j < arrSize; j++)
				visited[i][j] = false;
		}
		
		visited[y][x] = true;
		q.offer(input);
		cnt++;
		
		while(!q.isEmpty()) {
			int[] output = q.poll();
			int cy = output[0];
			int cx = output[1];
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				int[] nInput = new int[2];
				
				//	범위 벗어나거나 이미 방문했거나 얼음이 아니거나
				if(!isIn(ny, nx) || visited[ny][nx] || arr[ny][nx] == 0)
					continue;
				
				visited[ny][nx] = true;
				nInput[0] = ny;
				nInput[1] = nx;
				
				q.offer(nInput);
				cnt++;
			}
		}
	}
	
	static void printArr() {
		for(int i = 0; i < arrSize; i++) {
			for(int j = 0; j < arrSize; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}
}
