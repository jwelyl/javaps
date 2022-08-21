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

public class BOJ_1748 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int numOfN;
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		numOfN = (int)Math.floor(Math.log10(N)) + 1;
	
		for(int i = 1; i < numOfN; i++) {
			ans += i * 9 * Math.pow(10, i - 1);
		}
		ans += numOfN * (N - Math.pow(10, numOfN - 1) + 1);
		
		System.out.println(ans);
	}
}
