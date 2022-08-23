package algo220823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_서로소집합 {
	static int T, N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int[] parents;
	
	static int findParent(int x) {
		if(x == parents[x])
			return x;
		
		return parents[x] = findParent(parents[x]);
	}
	
	static void union(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x == y)
			return;
		else if(x < y)
			parents[y] = x;
		else
			parents[x] = y;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(bf.readLine());
			sb.append("#").append(t).append(" ");
			
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			parents = new int[N + 1];
			for(int i = 1; i <= N; i++)
				parents[i] = i;
			
			for(int i = 0; i < M; i++) {
				int cmd, a, b;
				tokens = new StringTokenizer(bf.readLine());
				
				cmd = Integer.parseInt(tokens.nextToken());
				a = Integer.parseInt(tokens.nextToken());
				b = Integer.parseInt(tokens.nextToken());
			
				if(cmd == 0) {
					union(a, b);
				}
				else {
					if(findParent(a) == findParent(b))
						sb.append(1);
					else
						sb.append(0);
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	

}
