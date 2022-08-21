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

public class BOJ_6603 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int K;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		while(true) {
			tokens = new StringTokenizer(bf.readLine());
			K = Integer.parseInt(tokens.nextToken());
			
			if(K == 0)
				break;
			
			nums = new int[K];
			for(int i = 0; i < K; i++) 
				nums[i] = Integer.parseInt(tokens.nextToken());
		
			combination(0, 0, new int[6]);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void combination(int nth, int startIdx, int[] result) {
		if(nth == 6) {
			for(int i = 0; i < 6; i++)
				sb.append(result[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = startIdx; i < K; i++) {
			result[nth] = nums[i];
			combination(nth + 1, i + 1, result);
		}
	}
}
