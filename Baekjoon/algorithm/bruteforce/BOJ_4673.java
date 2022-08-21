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

public class BOJ_4673 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		for(int num = 1; num <= 10000; num++) {
			boolean isSelfNumber = false;
			
			for(int x = 1; x < num; x++) {
				if(d(x) == num)
					isSelfNumber = true;
			}
			
			if(!isSelfNumber)
				sb.append(num).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int d(int n) {
		int ret = n;
		char[] numStr = String.valueOf(n).toCharArray();
	
		for(Character ch : numStr)
			ret += (ch - '0');
		
		return ret;
	}
}
