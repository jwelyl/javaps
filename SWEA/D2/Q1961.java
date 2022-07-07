package ssafy;

import java.util.*;

public class Q1961 {
	static void turn90(int N, int[][] before, int[][] after) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) 
				after[j][N - 1 -i] = before[i][j];
		}
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
	
		for (int test_case = 1; test_case <= T; test_case++) {
			int N;
			int[][] arr, arr90, arr180, arr270;
			
			N = sc.nextInt();
			
			arr = new int[N][];
			arr90 = new int[N][];
			arr180 = new int[N][];
			arr270 = new int[N][];
			
			for(int i = 0; i < N; i++) {
				arr[i]= new int[N];
				arr90[i]= new int[N];
				arr180[i]= new int[N];
				arr270[i]= new int[N];
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++)
					arr[i][j] = sc.nextInt();
			}
			
			turn90(N, arr, arr90);
			turn90(N, arr90, arr180);
			turn90(N, arr180, arr270);
			
			System.out.println("#" + test_case);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++)
					System.out.print(arr90[i][j]);
				System.out.print(" ");
				for(int j = 0; j < N; j++)
					System.out.print(arr180[i][j]);
				System.out.print(" ");
				for(int j = 0; j < N; j++)
					System.out.print(arr270[i][j]);
				System.out.println();
			}
		}
	}
}
