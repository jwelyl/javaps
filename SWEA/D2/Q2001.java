package ssafy;

import java.util.*;

public class Q2001 {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N, M, res = 0, ans = 0;
			int[][] arr;
			
			N = sc.nextInt();
			M = sc.nextInt();
			
			arr = new int[N][];
			for(int i = 0; i < N; i++)
				arr[i] = new int[N];
			
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					arr[i][j] = sc.nextInt();
			
			for(int i = 0; i <= N - M; i++) {
				for(int j = 0; j <= N - M; j++) {
					res = 0;
					
//					System.out.println("i = " + i + ", j = " + j);
//					System.out.println();
					for(int h = i; h < i + M; h++) {
						for(int w = j; w < j + M; w++) {
//							System.out.println("h = " + h + ", w = " + w);
							res += arr[h][w];
						}
					}
					
//					System.out.println("res = " + res + "\n");
					if(res > ans)
						ans = res;
				}
			}
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
}