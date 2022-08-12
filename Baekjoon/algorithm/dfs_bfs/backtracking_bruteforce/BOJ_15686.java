import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int N, M;
	static int minSum = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());

				if (map[i][j] == 1)
					homes.add(new Pos(i, j));
				else if (map[i][j] == 2)
					chickenList.add(new Pos(i, j));
			}
		}

		combination(0, 0, new int[M]);
//		System.out.println(minSum);
		sb.append(minSum);
		System.out.println(sb);
	}

	static class Pos {
		int y; // 집 y 좌표
		int x; // 집 x 좌표

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static List<Pos> chickenList = new ArrayList<Pos>();
	static List<Pos> homes = new ArrayList<Pos>();

	static int mDistance(Pos p1, Pos p2) {
		return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
	}

	static void combination(int nth, int start, int[] indices) {
		if (nth == M) {
			int sum = 0;

//			for (int i = 0; i < indices.length; i++)
//				System.out.print(indices[i] + " ");
//			System.out.println();

			for (int i = 0; i < homes.size(); i++) {
				Pos home = homes.get(i);
				int chickenDist = Integer.MAX_VALUE;

				for (int j = 0; j < M; j++) {
					Pos chicken = chickenList.get(indices[j]);
					chickenDist = Integer.min(chickenDist, mDistance(home, chicken));
				}

				sum += chickenDist;
			}

			if (minSum > sum)
				minSum = sum;

			return;
		}

		for (int i = start; i < chickenList.size(); i++) {
			indices[nth] = i;
			combination(nth + 1, i + 1, indices);
		}
	}
}