import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14921 {
	static int N;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int[] solutions;
	static int start, end;
	static int minSum;
	static int minSumAbs = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());
		
		solutions = new int[N];
		start = 0;
		end = N - 1;
		
		tokens = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < N; i++)
			solutions[i] = Integer.parseInt(tokens.nextToken());
		
		while(start < end) {
			int sum = solutions[start] + solutions[end];
			int sumAbs = Math.abs(sum);
			
			if(sumAbs == 0) {
				minSum = sum;
				minSumAbs = sumAbs;
				break;
			}
			else {
				if(minSumAbs > sumAbs) {
					minSumAbs = sumAbs;
					minSum = sum;
				}
			}
			
			if(sum > 0) end--;
			else start++;
		}
	
		System.out.println(minSum);
	}
}
