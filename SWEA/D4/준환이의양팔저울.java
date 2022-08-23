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

public class SWEA_양팔저울 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int T, N, ans;
	static int[] weights;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(bf.readLine());
			ans = 0;
			
			weights = new int[N];
			tokens = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++)
				weights[i] = Integer.parseInt(tokens.nextToken());
		
//			System.out.println("\ntest case " + t);
			permutation(0, new boolean[N], new int[N]);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	static void partition(int[] weights, int nth, int left, int right) {
		if(right > left)
			return;
		else if(nth == N) {
			ans++;
			return;
		}
	
		partition(weights, nth + 1, left + weights[nth], right);
		partition(weights, nth + 1, left, right + weights[nth]);
	}
	
	static void permutation(int nth, boolean[] isUsed, int[] result) {
		if(nth == N) {
			partition(result, 0, 0, 0);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				result[nth] = weights[i];
				permutation(nth + 1, isUsed, result);
				isUsed[i] = false;
			}
		}
	}
}
