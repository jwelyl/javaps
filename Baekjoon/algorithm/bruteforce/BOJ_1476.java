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

public class BOJ_1476 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int E, S, M;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		E = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		for(int x = 1; ; x++) {
			int e = (x % 15 == 0) ? 15 : x % 15;
			int s = (x % 28 == 0) ? 28 : x % 28;
			int m = (x % 19 == 0) ? 19 : x % 19;
			
			if(e == E && s == S && m == M) {
				System.out.println(x);
				break;
			}
		}
	
	}
}
