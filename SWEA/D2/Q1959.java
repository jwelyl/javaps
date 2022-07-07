/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
package ssafy;

import java.util.*;

public class Q1959 {
	static int T, N, M;
	static Scanner sc = new Scanner(System.in);
	static int res = 0, answer = 0;
	
	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int[] smaller, bigger, tmp;
			
			answer = 0;
			N = sc.nextInt();	
			M = sc.nextInt();
			
			smaller = new int[N];
			bigger = new int[M];
			
			for(int i = 0; i < N; i++)
				smaller[i] = sc.nextInt();
			for(int i = 0; i < M; i++)
				bigger[i] = sc.nextInt();
			
			if(N > M) {
				int tmp2 = N;
				N = M;
				M = tmp2;
				
				tmp = smaller;
				smaller = bigger;
				bigger = tmp;
			}
			
			for(int i = 0; i <= M - N; i++) {
				res = 0;
				
				for(int j = 0; j < N; j++)
					res += (smaller[j] * bigger[j + i]);
			
				if(res > answer)
					answer = res;
			}

			System.out.println("#" + t + " " + answer);
		}
	}

}


