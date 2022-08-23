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

public class BOJ_1063 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
	
	static Map<String, Integer> dir = new HashMap<>();
	
	static String kingPos, stonePos;
	static int N;
	static int ky, kx, sy, sx;
	
	public static void main(String[] args) throws IOException {
		init();
		
		tokens = new StringTokenizer(bf.readLine());
		kingPos = tokens.nextToken();
		stonePos = tokens.nextToken();
		N = Integer.parseInt(tokens.nextToken());
	
		kx = kingPos.charAt(0) - 'A';
		ky = kingPos.charAt(1) - '1';
		sx = stonePos.charAt(0) - 'A';
		sy = stonePos.charAt(1) - '1';
		
		for(int i = 0; i < N; i++) {
			String move = bf.readLine();
			move(move);
		}
		
		System.out.println((char)(kx + 'A') + "" + (ky + 1));
		System.out.println((char)(sx + 'A') + "" + (sy + 1));
	}
	
	static void init() {
		dir.put("R", 0);	dir.put("RB", 1);
		dir.put("B", 2);	dir.put("LB", 3);
		dir.put("L", 4);	dir.put("LT", 5);
		dir.put("T", 6);	dir.put("RT", 7);
	}
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < 8) && (0 <= x && x < 8);
	}
	
	static void move(String move) {
		int d = dir.get(move);
		int nky = ky + dy[d];
		int nkx = kx + dx[d];
		int nsy = sy;
		int nsx = sx;
		
		if(!isIn(nky, nkx))
			return;
		if(nky == nsy && nkx == nsx) {
			nsy += dy[d];
			nsx += dx[d];
			
			if(!isIn(nsy, nsx))
				return;
		}
		
		ky = nky;
		kx = nkx;
		sy = nsy;
		sx = nsx;
	}
}
