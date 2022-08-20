import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

public class BOJ_18870 {
	static Map<Integer, Integer> rank = new HashMap<Integer, Integer>();
	static int N;
	static int[] input;
	static int[] sorted;
	static int cnt = -1;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		input = new int[N];
		sorted = new int[N];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(tokens.nextToken());
			sorted[i] = input[i];
		}
		
		Arrays.sort(sorted);
		
		int value = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			if(value < sorted[i]) {
				value = sorted[i];
				rank.put(sorted[i], ++cnt);
			}
			else
				rank.put(sorted[i], cnt);
		}
		
		for(int i = 0; i < N; i++) 
			sb.append(rank.get(input[i])).append(" ");
		sb.append("\n");
		System.out.println(sb);
	}
}
