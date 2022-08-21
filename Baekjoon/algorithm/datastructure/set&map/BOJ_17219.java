import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_17219 {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static Map<String, String> map = new HashMap<String, String>();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		for(int i = 0; i < N; i++) {
			String site, password;
			tokens = new StringTokenizer(bf.readLine());
			site = tokens.nextToken();
			password = tokens.nextToken();
		
			map.put(site, password);
		}
		
		for(int i = 0; i < M; i++) {
			String site = bf.readLine();
			
			sb.append(map.get(site)).append("\n");
		}
		System.out.print(sb);
	}
}
