import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/16935
 * @difficuly S1
 * @performance 58872KB   404ms
 * @category # 구현
 * @memo 방법론적으로 봤을 땐 16926 배열 돌리기 1보다 훨씬 쉬웠던 문제
 * @memo 예시도 각 연산 별로 하나씩 존재해서 각 연산을 잘 구현했는지 확인하기 용이함
 * @memo 단, 여러 연산을 연달아 적용할 때 잘 적용되게 하기 위해서 참조변수 개념을 잘 알아야 했음.
 * @memo int[][] 참조변수 origin이 int[][] 참조변수 tmp가 가리키는 배열을 가리킨 후, tmp로 새로운 배열을 할당해도
 * @memo origin이 가리키는 배열이 tmp가 새로 할당한 배열로 바뀌거나 하지는 않음.
 * @memo 이 부분이 혼란스러워서 일일이 deep copy를 해야하나 했었음.
 * @etc  3, 4번 연산에서 전치행렬로 바꾼 다음에는 N과 M의 값도 서로 바꿔주는 절차를 밟아야 했다.
 *  
 */

public class BOJ_16935 {
	static int[][] origin;
	static int[][] tmp;
	static int N, M, R;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());

		origin = new int[N][M];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++)
				origin[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < R; i++) {
			int opt = Integer.parseInt(tokens.nextToken());
			operation(opt);
		}
		
		for(int i = 0; i < origin.length; i++) {
			for(int j = 0; j < origin[i].length; j++)
				sb.append(origin[i][j]).append(" ");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void operation(int num) {
		switch(num) {
		case 1:
			operation1();
			break;
		case 2:
			operation2();
			break;
		case 3:
			operation3();
			break;
		case 4:
			operation4();
			break;
		case 5:
			operation5();
			break;
		case 6:
			operation6();
		}
	}

	static void operation1() {
		tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				tmp[N - 1 - i][j] = origin[i][j];
		}

		origin = tmp;
	}

	static void operation2() {
		tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				tmp[i][M - 1 - j] = origin[i][j];
		}
		
		origin = tmp;
	}
	
	static void operation3() {
		tmp = new int[M][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				tmp[j][N - 1 - i] = origin[i][j];
		}
		
		origin = tmp;

		int swap = N;
		N = M;
		M = swap;
	}
	
	static void operation4() {
		tmp = new int[M][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tmp[M - 1 - j][i] = origin[i][j];
			}
		}
		
		origin = tmp;
		
		int swap = N;
		N = M;
		M = swap;
	}
	
	static void operation5() {
		int midy = N / 2;
		int midx = M / 2;
		
		tmp = new int[N][M];
	
		for(int i = 0; i < midy; i++) {
			for(int j = 0; j < midx; j++)
				tmp[i][j + M / 2] = origin[i][j];
		}
		
		for(int i = 0; i < midy; i++) {
			for(int j = midx; j < M; j++)
				tmp[i + N / 2][j] = origin[i][j];
		}
		
		for(int i = midy; i < N; i++) {
			for(int j = midx; j < M; j++) {
				tmp[i][j - M / 2] = origin[i][j];
			}
		}
		
		for(int i = midy; i < N; i++) {
			for(int j = 0; j < M / 2; j++)
				tmp[i - N / 2][j] = origin[i][j];
		}
		
		origin = tmp;
	}
	
	static void operation6() {
		int midy = N / 2;
		int midx = M / 2;
		
		tmp = new int[N][M];
	
		for(int i = 0; i < midy; i++) {
			for(int j = 0; j < midx; j++)
				tmp[i + N / 2][j] = origin[i][j];
		}
		
		for(int i = 0; i < midy; i++) {
			for(int j = midx; j < M; j++)
				tmp[i][j - M / 2] = origin[i][j];
		}
		
		for(int i = midy; i < N; i++) {
			for(int j = midx; j < M; j++) {
				tmp[i - N / 2][j] = origin[i][j];
			}
		}
		
		for(int i = midy; i < N; i++) {
			for(int j = 0; j < M / 2; j++)
				tmp[i][j + M / 2] = origin[i][j];
		}
		
		origin = tmp;
	}
}
