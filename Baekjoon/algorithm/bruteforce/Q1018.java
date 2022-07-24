import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] chess;
	static int black = Integer.MAX_VALUE, white = Integer.MAX_VALUE;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	static int calcBlack(char[][] newChess) {
		int res = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == j % 2 && newChess[i][j] == 'W')
					res++;
				else if (i % 2 != j % 2 && newChess[i][j] == 'B')
					res++;
			}
		}

		return res;
	}

	static int calcWhite(char[][] newChess) {
		int res = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == j % 2 && newChess[i][j] == 'B')
					res++;
				else if (i % 2 != j % 2 && newChess[i][j] == 'W')
					res++;
			}
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		String line = bf.readLine();
		tokens = new StringTokenizer(line);

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		chess = new char[N][M];

		for (int i = 0; i < N; i++) {
			line = bf.readLine();
			chess[i] = line.toCharArray();
		}

		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				char[][] tmp = new char[8][8];
				for (int k = 0; k < 8; k++) {
					tmp[k] = Arrays.copyOfRange(chess[i + k], j, j + 8);
				}
	
				black = Math.min(black, calcBlack(tmp));
				white = Math.min(white, calcWhite(tmp));
			}
		}

		System.out.println(Math.min(black, white));
	}
}
