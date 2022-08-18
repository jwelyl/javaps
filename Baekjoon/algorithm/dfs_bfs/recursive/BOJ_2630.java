import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_2630 {
	static int N;
	static int[][] papers;

	final static int WHITE = 0;
	final static int BLUE  = 1;
	
	static int numWhite = 0;
	static int numBlue = 0;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		papers = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++)
				papers[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		partition(0, 0, N);
		System.out.println(numWhite);
		System.out.println(numBlue);
	}
	
	//	좌측 상단 꼭짓점이 (r, c), 한 변 길이 size인 종이가 한 장의 종이인지 check
	//	색이 전부 같다면 1장의 종이(true), 한 칸이라도 다를 경우 1장 종이 x(false)
	static boolean isOnePaper(int r, int c, int size) {
		int color = papers[r][c];
		boolean ret = true;
		
		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				if(color != papers[i][j]) {
					ret = false;
					break;
				}
			}
		}
		
		return ret;
	}
	
	static void partition(int r, int c, int size) {
		if(isOnePaper(r, c, size)) {	//	한 장의 종이일 경우
			if(papers[r][c] == WHITE)
				numWhite++;
			else
				numBlue++;
			return;
		}
		
		//	한 장의 종이가 아닐 경우 4등분하여 각각이 한 장의 종이가 될 수 있는지 check
		partition(r, c, size / 2);
		partition(r, c + size / 2, size / 2);
		partition(r + size / 2, c, size / 2);
		partition(r + size / 2, c + size / 2, size / 2);
	}
	
}
