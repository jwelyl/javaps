package algo220823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_무리개수 {
	static int T, N, M, cnt;
	static int[] parents;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	static int findParent(int x) {
		if (x == parents[x])
			return x;
		return parents[x] = findParent(parents[x]);
	}

	static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);

		if (x == y)
			return;
		
//		System.out.println("x = " + x);
//		System.out.println("y = " + y);
		cnt--;
//		System.out.println("CNT = " + cnt);
		if (x > y)
			parents[x] = y;
		else
			parents[y] = x;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(bf.readLine());

		for (int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(bf.readLine());

			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			cnt = N;
//			System.out.println("CNT = " + cnt);

			parents = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parents[i] = i;

			for (int i = 0; i < M; i++) {
				int a, b;
				tokens = new StringTokenizer(bf.readLine());

				a = Integer.parseInt(tokens.nextToken());
				b = Integer.parseInt(tokens.nextToken());
				
				union(a, b);
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		
		System.out.print(sb);
	}

}
