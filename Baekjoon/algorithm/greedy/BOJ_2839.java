import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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

public class BOJ_2839 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());

		// 5의 개수 = i
		for (int i = 0; (5 * i) <= N; i++) {
//			System.out.println("5의 개수 = " + i + "개");
			int remain = N - (5 * i);
			int cnt;

			if (remain % 3 != 0)
				cnt = -1;
			else
				cnt = i + (remain / 3);
//			System.out.println("cnt : " + cnt);

			if (cnt == -1) {
				if (ans == Integer.MAX_VALUE)
					ans = -1;
			} else {
				if (ans == -1)
					ans = cnt;
				else
					ans = Integer.min(ans, cnt);
			}
		}

		System.out.println(ans);
	}
}
 BOJ_2839 {
    
}
