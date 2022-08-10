import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/17406
 * @difficuly G4
 * @performance 38172KB		356ms
 * @category # 구현
 * @memo 배열 돌리기 1에서 배운 배열 돌리는 방법과 순열 경우의 수를 계산할 수 있으면 각 기능별로 잘 나누어서 구현하면 어렵진 않다.
 * @etc 배열 돌리기 1은 처음 배열을 돌려봐서 어렵고, 2는 idea가 필요했다면 3, 4는 노가다성이 짙었다.
 * 
 */

public class BOJ_17406 {
	static int N, M, K;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	static int[][] origin;
	static int[][] arr;
	static int[][] tmp;
	static int minValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		origin = new int[N][M];
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++)
				origin[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		copyOriginToArr();

		rotationList = new Rotation[K];	//	회전 명령 리스트
		for (int k = 0; k < K; k++) {
			int r, c, s;
			tokens = new StringTokenizer(bf.readLine());

			r = Integer.parseInt(tokens.nextToken());
			c = Integer.parseInt(tokens.nextToken());
			s = Integer.parseInt(tokens.nextToken());

			rotationList[k] = new Rotation(r, c, s);
		}
		
		permutation(0, new boolean[K], new int[K]);
		
		System.out.println(minValue);
	}

	//	회전 명령을 표현하는 객체
	static class Rotation {
		int r;
		int c;
		int s;
		int len;			//	회전시킬 정사각형 한 변 길이
		int rotateCnt;		//	회전시킬 정사각형 껍질 수

		Rotation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.len = 2 * s + 1;
			this.rotateCnt = this.len / 2;
		}
	}

	static Rotation[] rotationList;

	static void rotate(Rotation rotation) {
		int r = rotation.r;
		int c = rotation.c;
		int s = rotation.s;
		int len = rotation.len;
		int rotateCnt = rotation.rotateCnt;

		// 임시 배열 tmp
		tmp = new int[len][len];

		// arr 배열의 회전시킬 부분을 임시 배열 tmp에 복사
		for (int i = r - s - 1; i <= r + s - 1; i++) {
			for (int j = c - s - 1; j <= c + s - 1; j++)
				tmp[i - r + s + 1][j - c + s +1] = arr[i][j];
		}

		// 복사한 부분 회전시킴
		for (int cnt = 0; cnt < rotateCnt; cnt++) {
			int keep = tmp[cnt][cnt];

			for (int i = cnt; i < len - cnt - 1; i++)
				tmp[i][cnt] = tmp[i + 1][cnt];
			for (int i = cnt; i < len - cnt - 1; i++)
				tmp[len - cnt - 1][i] = tmp[len - cnt - 1][i + 1];
			for (int i = len - cnt - 1; i > cnt; i--)
				tmp[i][len - cnt - 1] = tmp[i - 1][len - cnt - 1];
			for (int i = len - cnt - 1; i > cnt; i--)
				tmp[cnt][i] = tmp[cnt][i - 1];

			tmp[cnt][cnt + 1] = keep;
		}

		//	arr 배열에 회전시킨 부분을 다시 복사
		for (int i = r - s - 1; i <= r + s - 1; i++) {
			for (int j = c - s - 1; j <= c + s - 1; j++)
				arr[i][j] = tmp[i - r + s + 1][j - c + s +1];
		}
	}
	
	//	배열 arr의 각 행의 합 중 최소인 값 찾아서 반환
	static int minSum() {
		int rowSum = 0;
		int ret = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			rowSum = 0;
			
			for(int j = 0; j < M; j++)
				rowSum += arr[i][j];
		
			if(ret > rowSum)
				ret = rowSum;
		}
		
		return ret;
	}
	
	static void permutation(int nth, boolean[] isSelected, int[] order) {
		if(nth == K) {	//	회전 순서 정해졌으면
//			System.out.print("회전 순서 경우의 수 : ");
//			for(int i = 0; i < K; i++)
//				System.out.print(order[i] + " ");
//			System.out.println();
			
			for(int i = 0; i < K; i++) {	//	정해진 순서에 따라 회전시킴
				Rotation r = rotationList[order[i]];
				rotate(r);
			}
			
			int res = minSum();	//	회전한 배열의 값
			
			if(minValue > res) 	//	최솟값이 갱신될 경우 변경
				minValue = res;
			
			copyOriginToArr();	//	arr 배열을 회전시키기 전으로 돌려놓음
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(!isSelected[i]) {
				order[nth] = i;
				isSelected[i] = true;
				
				permutation(nth + 1, isSelected, order);
				
				isSelected[i] = false;
			}
		}
	}
	
	static void copyOriginToArr() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				arr[i][j] = origin[i][j];
		}
	}
}
