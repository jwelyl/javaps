import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/21610
 * @difficuly Gold5
 * @performance 11512KB   76ms
 * @category # 단순 구현, 문자열 parsing
 * @memo ::의 위치를 찾기 위해 indexOf를 이용하고, :: 전후로 prefix, suffix로 나눔
 * @memo 그 외 짜잘한 부분 예외처리 등 노가다성이 짙지만 어려진 않았다.
 * @memo 근데 시험 등 급박한 상황에서 당황하지 않고 잘 풀수 있을진 모르겠네.
 * @etc 문자열 연습이 많이 필요할 것 같다.
 *  
 */

public class BOJ_3107 {
	static String IPv6 = "";
	static String prefix = "";
	static String suffix = "";
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int semiIdx;
		
		IPv6 = bf.readLine();
		semiIdx = IPv6.indexOf("::");
//		System.out.println(semiIdx);
		
		if(semiIdx == -1) 
			prefix = IPv6;
		else {
			if(semiIdx == 0)
				prefix = "";
			else
				prefix = IPv6.substring(0, semiIdx);
			
			if(semiIdx + 2 < IPv6.length())
				suffix = IPv6.substring(semiIdx + 2);
		}
//		
//		System.out.println("prefix : " + prefix);
//		System.out.println("suffix : " + suffix);
		
		tokens = new StringTokenizer(prefix, ":");
		int numOfPrefix = tokens.countTokens();
		
		for(int i = 0; i < numOfPrefix; i++) {
			String token = tokens.nextToken();
			int numOf0s = 4 - token.length();
			String zeros = "";
			for(int j = 0; j < numOf0s; j++)
				zeros += "0";
			
			token = zeros + token;
			
			if(i == numOfPrefix - 1 && numOfPrefix == 8)
				sb.append(token + "\n");
			else
				sb.append(token + ":");
		}
		
		tokens = new StringTokenizer(suffix, ":");
		int numOfSuffix = tokens.countTokens();
		int numOf4Zeros = 8 - numOfPrefix - numOfSuffix;
		
		for(int i = 0; i < numOf4Zeros; i++) {
			if(i == numOf4Zeros - 1 && numOfSuffix == 0)
				sb.append("0000\n");
			else
				sb.append("0000:");
		}
		
		for(int i = 0; i < numOfSuffix; i++) {
			String token = tokens.nextToken();
			int numOf0s = 4 - token.length();
			String zeros = "";
			for(int j = 0; j < numOf0s; j++)
				zeros += "0";
			
			token = zeros + token;
			
			if(i == numOfSuffix - 1)
				sb.append(token + "\n");
			else
				sb.append(token + ":");
		}

		
		System.out.println(sb);
	}

}
