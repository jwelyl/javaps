import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

public class BOJ_1759 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int L, C;
	static char[] chs;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		chs = new char[C];
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < C; i++) {
			chs[i] = tokens.nextToken().charAt(0);
		}
		
		Arrays.sort(chs);
//		System.out.println(Arrays.toString(chs));
		combination(0, 0, 0, 0, new char[L]);
		System.out.print(sb);
	}
	
	static boolean isVowel(char ch) {
		return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
	}
	
	static void combination(int nth, int startIdx, int nV, int nC, char[] result) {
		if(nth == L) {
			if(!(nV >= 1 && nC >= 2))
				return;
			
			for(char ch : result)
				sb.append(ch);
			sb.append("\n");
			return;
		}
		
		for(int i = startIdx; i < C; i++) {
			char ch = chs[i];
			result[nth] = ch;
			if(isVowel(ch)) 
				combination(nth + 1, i + 1, nV + 1, nC, result);
			else 
				combination(nth + 1, i + 1, nV, nC + 1, result);
		}
	}
}
