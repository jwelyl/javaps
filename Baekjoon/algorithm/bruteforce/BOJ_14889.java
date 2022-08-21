import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_14889 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int[][] arr;
	static int left = 0, right = 0;
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		combination(0, N / 2, 0, new int[N / 2]);
		System.out.println(minDiff);
	}
	
	static void combination(int nth, int r, int startIdx, int[] selected) {
		if(nth == r) {
			boolean[] isSelected = new boolean[N];
			int idx = 0;
			int[] left = new int[r];
			int[] right = new int[r];
			int leftSum = 0;
			int rightSum = 0;
			int diff = 0;
			
			for(int i = 0; i < r; i++) {
				isSelected[selected[i]] = true;
				left[idx++] = selected[i];
			}
			
			idx = 0;
			for(int i = 0; i < N; i++) {
				if(!isSelected[i])
					right[idx++] = i;
			}
			
			for(int i = 0; i < r - 1; i++) {
				for(int j = i + 1; j < r; j++) {
					leftSum += arr[left[i]][left[j]];
					leftSum += arr[left[j]][left[i]];
					rightSum += arr[right[i]][right[j]];
					rightSum += arr[right[j]][right[i]];
				}
			}
			
			diff = Math.abs(leftSum - rightSum);
			minDiff = Math.min(diff, minDiff);
		
			return;
		}
		
		for(int i = startIdx; i < N; i++) {
			selected[nth] = i;
			combination(nth + 1, r, i + 1, selected);
		}
	}
}
