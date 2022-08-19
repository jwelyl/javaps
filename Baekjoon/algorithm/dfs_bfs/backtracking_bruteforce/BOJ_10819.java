import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819 {
	static int N;
	static int[] nums;
	static int ans = Integer.MIN_VALUE;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		nums = new int[N];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());
		
		perm(0, new boolean[N], new int[N]);
		System.out.println(ans);
	}
	
	static int calc(int[] result) {
		int ret = 0;
		
		for(int i = 0; i < N - 1; i++)
			ret += Math.abs(result[i] - result[i + 1]);
		
		return ret;
	}

	static int cnt = 0;
	static void perm(int nth, boolean[] isUsed, int[] result) {
		if(nth == N) {
			int res = calc(result);
//			System.out.println("경우의 수 " + (cnt++));
//			for(int num : result)
//				System.out.print(num + " ");
//			System.out.println();
			if(res > ans)
				ans = res;
			
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				result[nth] = nums[i];
				perm(nth + 1, isUsed, result);
				isUsed[i] = false;
			}
		}
	}
}
