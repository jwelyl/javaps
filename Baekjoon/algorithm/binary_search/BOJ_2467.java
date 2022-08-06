import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {
	static int N;
	static int[] solutions;
	static int minAbsSum = Integer.MAX_VALUE, left, right;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());

		solutions = new int[N];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			solutions[i] = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < N - 1; i++) {
			int start = i + 1;
			int end = N - 1;
			int target = -solutions[i];
			
			int idx = binarySearchIterative(target, start, end);
//			System.out.println("i = " + i + ", idx = " + idx);
			int absSum = Math.abs(solutions[i] + solutions[idx]);
//			System.out.println("absSum = " + absSum);
			
			if(minAbsSum > absSum) {
				minAbsSum = absSum;
				left = i;
				right = idx;
			}
		}
		
		System.out.println(solutions[left] + " " + solutions[right]);
	}
	
	static int binarySearchIterative(int target, int start, int end) {
		int mid = (start + end) / 2;
		int ret = 0;
		int diff = Integer.MAX_VALUE;
		
		while(start <= end) {
			int tmp = (int)Math.abs(solutions[mid] - target);
			
			if(tmp == 0) {
				ret = mid;
				break;
			}
			
			if(diff > tmp) {
				diff = tmp;
				ret = mid;
			}
			
			if(solutions[mid] < target)
				start = mid + 1;
			else
				end = mid - 1;
			
			mid = (start + end) / 2;
		}
		
		return ret;
	}

}