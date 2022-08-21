import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_20529 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int T, N;
	static String[] mbtis;
	static int minDist;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(bf.readLine());
			mbtis = new String[N];
			minDist = Integer.MAX_VALUE;

			tokens = new StringTokenizer(bf.readLine());
			for (int i = 0; i < N; i++) {
				mbtis[i] = tokens.nextToken();
			}

			if (N <= 32) {
				combination(0, 0, new int[3]);
				sb.append(minDist).append("\n");
			}
			else
				sb.append(0).append("\n");
		}
		System.out.println(sb);
	}

	static void combination(int nth, int startIdx, int[] result) {
		if (nth == 3) {
			int dist3 = psyDist(mbtis[result[0]], mbtis[result[1]]);
			dist3 += psyDist(mbtis[result[1]], mbtis[result[2]]);
			dist3 += psyDist(mbtis[result[2]], mbtis[result[0]]);

			if (dist3 < minDist)
				minDist = dist3;

			return;
		}

		for (int i = startIdx; i < N; i++) {
			result[nth] = i;
			combination(nth + 1, i + 1, result);
		}
	}

	static int psyDist(String mbti1, String mbti2) {
		int ret = 0;

		for (int i = 0; i < 4; i++) {
			if (mbti1.charAt(i) != mbti2.charAt(i))
				ret++;
		}

		return ret;
	}
}