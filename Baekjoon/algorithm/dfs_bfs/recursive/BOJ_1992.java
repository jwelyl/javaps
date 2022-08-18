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

public class BOJ_1992 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static char[][] input;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		input = new char[N][N];

		for (int i = 0; i < N; i++)
			input[i] = bf.readLine().toCharArray();

		System.out.println(quadTree(N, 0, 0));
	}

	static String quadTree(int size, int r, int c) {
		if(size == 1) {
			return new StringBuilder(input[r][c] + "").toString();
		}
		else {
			String s1 = quadTree(size / 2, r, c);
			String s2 = quadTree(size / 2, r, c + size / 2);
			String s3 = quadTree(size / 2, r + size / 2, c);
			String s4 = quadTree(size / 2, r + size / 2, c + size / 2);
			
			if(s1.equals(s2) && s2.equals(s3) && s3.equals(s4) && s1.length() == 1)
				return s1;
			else
				return new StringBuilder("(").append(s1).append(s2).append(s3)
												.append(s4).append(")").toString();
		}
	}
}
