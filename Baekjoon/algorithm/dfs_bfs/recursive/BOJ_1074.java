import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
	static int N, r, c;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	static int[][] base = { { 0, 1 }, { 2, 3 } };

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		r = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
	
		System.out.println(z(N, r, c));
	}
	
	static int z(int N, int r, int c) {
		if(N == 1)
			return base[r][c];
		
		int start = 0, mid = (int)Math.pow(2, N - 1), end = (int)Math.pow(2, N) - 1;
		int diff = (int)Math.pow(2, 2 * N - 2);
	
		if(start <= r && r < mid && start <= c && c < mid)
			return z(N - 1, r, c);
		
		if(start <= r && r < mid && mid <= c && c <= end)
			return z(N - 1, r, c - mid) + diff;
		
		if(mid <= r && r <= end && start <= c && c < mid)
			return z(N - 1, r - mid, c) + 2 * diff;
		
		if(mid <= r && r <= end && mid <= c && c <= end)
			return z(N - 1, r - mid, c- mid) + 3 * diff;
		
		return 0;
	}
}
