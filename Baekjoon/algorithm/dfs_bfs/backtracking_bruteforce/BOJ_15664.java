import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
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

public class BOJ_15664 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	static int[] nums;
	static Set<String> set = new LinkedHashSet<String>();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		nums = new int[N];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());
		Arrays.sort(nums);
		
		combination(0, 0, new int[M]);
		for(String num : set)
			sb.append(num);
		System.out.println(sb);
	}
	
	static void combination(int nth, int startIdx, int[] result) {
		if(nth == M) {
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < M; i++) {
				builder.append(result[i]);
				if(i != M - 1)
					builder.append(" ");
			}
			builder.append("\n");
			
			set.add(builder.toString());
			return;
		}
		
		for(int i = startIdx; i < N; i++) {
			result[nth] = nums[i];
			combination(nth + 1, i + 1, result);
		}
	}
}
