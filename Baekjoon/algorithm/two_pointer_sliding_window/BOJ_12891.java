import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/12891
 * @difficuly S2
 * @performance 21600KB   204ms (이전 코드)
 * @performance 21784KB	  220ms (Map 사용한 현재 코드)
 * @category # 슬라이딩 윈도우
 * @memo S의 길이가 최대 10^6, |P| <= |S|이므로 순열과 조합, 부분집합 풀이는 꿈도 못꿈
 * @memo 수학적으로 접근하려 했지만 녹록치 않았음
 * @memo 슬라이딩 윈도우를 이용하면 아주 가볍게 풀리는 문제
 * @etc 부분 문자열의 정의가 무엇인지, 문제 조건이 뭔지 정확히 파악했다면 슬라이딩 윈도우를 떠올렸을수도??
 *  
 */

public class Main {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int S, P;						//	주어진 문자열 길이, 만들 문자열 길이
	static char[] dna;						//	주어진 문자열
	static int numOfPassword;				//	조건을 만족하는 부분 문자열 개수
	
	static Map<Character, Integer> base = new HashMap<>();
	static int[] required = new int[4];		//	A, C, G, T 순으로 필요한 개수
	static int[] current = new int[4];		//	A, C, G, T 순으로 현재 부분 문자열에 포함된 개수
	
	static void check() {	//	현재 부분 문자열이 조건 만족하는지 check
		boolean ok = true;
		
		for(int i = 0; i < 4; i++) {	
			if(current[i] < required[i]) {	//	A, C, G, T 중 하나라도 요구하는 개수보다 모자를 경우
				ok = false;
				break;
			}
		}
		
		if(ok)
			numOfPassword++;
	}
	
	public static void main(String[] args) throws IOException {
		base.put('A', 0);	//	각 염기서열 별 index
		base.put('C', 1);
		base.put('G', 2);
		base.put('T', 3);
		
		tokens = new StringTokenizer(bf.readLine());
		
		S = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		
		dna = bf.readLine().toCharArray();
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < 4; i++)
			required[i] = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < P; i++) {	//	index 0부터 시작하는 부분문자열에 포함된 각 알파벳 개수
			int idx = base.get(dna[i]);
			current[idx] += 1;
		}
		check();
		
		for(int i = 1; i <= S - P; i++) {
			int idx = base.get(dna[i - 1]);	//	이전 부분 문자열의 처음 1개를 제거
			current[idx] -= 1;
			
			idx = base.get(dna[i + P - 1]);	//	새로운 부분 문자열의 마지막 1개를 추가 
			current[idx] += 1;
			
			check();
		}
		
		System.out.println(numOfPassword);
	}
}