import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly
 * @performance KB ms
 * @category #
 * @memo
 * @etc
 * 
 */

public class BOJ_9375 {
	static int T, N;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
//	static Map<String, String> clothesCategory;	//	(옷 이름, 종류)
	static Map<String, Integer> categoryCnt;	//	(옷 종류, 개수)
	static ArrayList<String> categories;		//	옷 종류 모음
	static int ans;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(bf.readLine());
			ans = 1;
			
//			clothesCategory = new HashMap<>();
			categoryCnt = new HashMap<>();
			categories = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				String clothes, category;
				tokens = new StringTokenizer(bf.readLine());
				clothes = tokens.nextToken();
				category = tokens.nextToken();
				
//				clothesCategory.put(clothes, category);
				if(!categoryCnt.containsKey(category)) {
					categoryCnt.put(category, 1);
					categories.add(category);
				} else 
					categoryCnt.put(category, categoryCnt.get(category) + 1);
			}
			
			for(String category: categories) {
				int cnt = categoryCnt.get(category);
				ans *= (cnt + 1);	//	안입는 경우까지 포함 모든 경우의 수
			}
			ans -= 1;	//	모두 안 입는 경우는 제외
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
