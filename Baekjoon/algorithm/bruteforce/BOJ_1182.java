import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_1182 {
	static int N, S;
	static int[] nums;
	static int cnt = 0;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		
		nums = new int[N];
		tokens = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());
		
		for(int r = 1; r <= N; r++)
			combination(0, r, 0, new int[r]);
		
		 System.out.println(cnt);
	}
	
	static void combination(int nth, int r, int startIdx, int[] res) {
		if(nth == r) {
//			System.out.println("\n경우의 수");
			int sum = 0;
			for(int num : res) {
//				System.out.print(num +  " ");
				sum += num;
			}
//			System.out.println();
			
			if(sum == S)
				cnt++;
			
			return;
		}
		
		for(int i = startIdx; i < N; i++) {
			res[nth] = nums[i];
			combination(nth + 1, r, i + 1, res);
		}
	}
}
