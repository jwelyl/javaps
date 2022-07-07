package ssafy;

import java.util.*;

public class Q1974 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int[][] sudoku = new int[10][10];
			boolean result = true;

			for (int i = 1; i <= 9; i++)
				for (int j = 1; j <= 9; j++)
					sudoku[i][j] = sc.nextInt();

			// 3 x 3 test
			for (int i = 1; i <= 7; i += 3) {
				for (int j = 1; j <= 7; j += 3) {
					Set<Integer> nums = new HashSet<Integer>();

					for (int h = 0; h < 3; h++) {
						for (int w = 0; w < 3; w++) {
							nums.add(sudoku[i + h][j + w]);
						}
					}

					if (nums.size() != 9) {
						result = false;
						break;
					}
				}

				if (!result)
					break;
			}

			//	width test
			if (result) {
				for (int i = 1; i <= 9; i++) {
					Set<Integer> nums = new HashSet<Integer>();

					for (int j = 1; j <= 9; j++)
						nums.add(sudoku[i][j]);

					if (nums.size() != 9) {
						result = false;
						break;
					}
				}
			}

			// height test
			if (result) {
				for (int i = 1; i <= 9; i++) {
					Set<Integer> nums = new HashSet<Integer>();

					for (int j = 1; j <= 9; j++)
						nums.add(sudoku[j][i]);

					if (nums.size() != 9) {
						result = false;
						break;
					}
				}
			}

			System.out.println("#" + test_case + " " + (result ? 1 : 0));
		}
	}
}
