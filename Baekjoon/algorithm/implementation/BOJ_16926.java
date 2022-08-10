import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/16926
 * @difficuly S1
 * @performance 45312KB   652ms
 * @category # 구현
 * @memo 처음 풀었을땐 전혀 감이 안잡혔지만 방법을 알고 나면 쉽게 구현 가능
 * @memo 처음 접했을 때 해당 idea를 떠올리는 것은 매우 어려울 것 같음.
 * @memo 풀었던 문제지만 다시 풀어봤는데 한 번에 안풀렸음. index를 잘못 써서 발생했음.
 * @etc  x에 cnt가 아니라 0을 대입했을 때 두 번째 껍질 돌릴 때부터 에러 발생, 찾아내는 데 은근 시간이 많이 걸림
 *  
 */

public class BOJ_16926 {
	static int N, M, R;		//	세로, 가로, 회전 횟수
	static int rotateCnt;	//	회전해야 하는 사각형 껍질 횟수(최외곽부터 중심부까지)
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		rotateCnt = Math.min(N, M) / 2;	//	회전해야 할 사각형 수 계산
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			
			for(int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		rotateCCW(R);	//	R번 회전
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				sb.append(arr[i][j] + " ");
			sb.append("\n");
		}
		sb.append("\n");
		System.out.println(sb);
	}
	
	static void rotateCCW(int r) {
		for(int i = 0; i < r; i++) 
			rotateCCW();
	}
	
	static void rotateCCW() {	//	각 사각형 껍질 반시계방향으로 1칸씩 이동
		for(int cnt = 0; cnt < rotateCnt; cnt++) {
			int tmp = arr[cnt][cnt];	//	처음에 손실되는 값 backup
			
			for(int x = cnt; x < M - cnt - 1; x++)
				arr[cnt][x] = arr[cnt][x + 1];
			
			for(int y = cnt; y < N - cnt - 1; y++)
				arr[y][M - cnt - 1] = arr[y + 1][M - cnt - 1];
			
			for(int x = M - cnt - 1; x > cnt; x--)
				arr[N - cnt - 1][x] = arr[N - cnt - 1][x - 1];
			
			for(int y = N - cnt - 1; y > cnt; y--)
				arr[y][cnt] = arr[y - 1][cnt];
			
			arr[cnt + 1][cnt] = tmp;
		}
	}
}
