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

public class BOJ_11478 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static String input;
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		input = bf.readLine();
		
		for(int i = 0; i < input.length(); i++) {
			for(int j = i; j < input.length(); j++) {
				String str = input.substring(i, j + 1);
				set.add(str);
			}
		}
		
		System.out.println(set.size());
	}
}
