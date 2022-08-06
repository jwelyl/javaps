import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {
	static int N;
	static int[] solutions;
	static int start, end, resStart, resEnd;
	static int absSum = Integer.MAX_VALUE;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
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
			int tmp = solutions[end] + solutions[start];
			
			if(absSum > (int)Math.abs(tmp)) {
				absSum = (int)Math.abs(tmp);
				resStart = start;
				resEnd = end;
			}
		
			if(tmp > 0)
				end--;
			else if(tmp == 0) {
				resStart = start;
				resEnd = end;
				break;
			}
			else
				start++;
		}
		
		System.out.println(solutions[resStart] + " " + solutions[resEnd]);
	}

}