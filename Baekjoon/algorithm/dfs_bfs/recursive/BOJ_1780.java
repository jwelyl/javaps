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

public class BOJ_1065 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, ans = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		for(int i = 1; i <= N; i++) {
			if(isHansu(i))
				ans++;
		}
		
		System.out.println(ans);
	}
	
	static boolean isHansu(int num) {
		char[] numArr = String.valueOf(num).toCharArray();
		if(numArr.length == 1)
			return true;
		
		int diff = numArr[1] - numArr[0];
		
		for(int i = 1; i < numArr.length - 1; i++) {
			if(diff != numArr[i + 1] - numArr[i])
				return false;
		}
		
		return true;
	}
}
