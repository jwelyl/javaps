import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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

public class BOJ_1269 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int NA, NB;
	static Set<Integer> setA = new HashSet<Integer>(), setB = new HashSet<Integer>();
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		NA = Integer.parseInt(tokens.nextToken());
		NB = Integer.parseInt(tokens.nextToken());
	
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < NA; i++) {
			setA.add(Integer.parseInt(tokens.nextToken()));
		}
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < NB; i++) {
			setB.add(Integer.parseInt(tokens.nextToken()));
		}
		
		for(int num : setA) {
			if(!setB.contains(num))
				ans++;
		}
		for(int num : setB) {
			if(!setA.contains(num))
				ans++;
		}
		
		System.out.println(ans);
	}
}
