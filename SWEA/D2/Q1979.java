package ssafy;

import java.util.*;

public class Q1979 {
	static boolean isWidthOk(int y, int x, int N, int K, int[][] arr) {
		if(x + K - 1 >= N)
			return false;
		
		if(x - 1 >= 0 && arr[y][x - 1] == 1)
			return false;
		
		for(int i = x; i < x + K; i++) {
			if(arr[y][i] == 0)
				return false;
		}
		
		if(x + K == N || arr[y][x + K] == 0) {
//			System.out.println("(" + y + ", " + x + ")에서 가로로 배치");
			return true;
		}
		else
			return false;
	}
	
	static boolean isHeightOk(int y, int x, int N, int K, int[][] arr) {
		if(y + K - 1 >= N)
			return false;
		
		if(y - 1 >= 0 && arr[y - 1][x] == 1)
			return false;
		
		for(int i = y; i < y + K; i++) {
			if(arr[i][x] == 0)
				return false;
		}
		
		if(y + K == N || arr[y + K][x] == 0) {
//			System.out.println("(" + y + ", " + x + ")에서 세로로 배치");
			return true;
		}
		else
			return false;
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N, K;
			int ans = 0;
			int[][] arr;
			
			N = sc.nextInt();
			K = sc.nextInt();
			
			arr = new int[N][];
			for(int i = 0; i < N; i++)
				arr[i] = new int[N];
			
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					arr[i][j] = sc.nextInt();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] == 0)
						continue;
					
					if(isWidthOk(i, j, N, K, arr)) 
						ans += 1;
					
					if(isHeightOk(i, j, N, K, arr))
						ans += 1;
				}
			}
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
}
