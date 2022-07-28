import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N, K;	//	y축 세로, x축 가로, 직사각형 개수
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static boolean[][] visited;
	static int nComps = 0, area = 0;
	static List<Integer> areaList = new ArrayList<>();
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	static boolean isIn(int y, int x) {
		return (0 <= y && y < M) && (0 <= x && x < N) && !visited[y][x];
	}
	
	static class Point {
		int y;
		int x;
		
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static void bfs(int y, int x) {
		Queue<Point> q = new LinkedList<Point>();
		visited[y][x] = true;
		q.offer(new Point(y, x));
		area++;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int cy = cur.y;
			int cx = cur.x;
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(isIn(ny, nx)) {
					visited[ny][nx] = true;
					area++;
					q.offer(new Point(ny, nx));
				}
			}
		}
		
		areaList.add(area);
		area = 0;
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
	
		visited = new boolean[M][N];
		
		for(int i = 0; i < K; i++) {
			int x0, y0, x1, y1;
			tokens = new StringTokenizer(bf.readLine());
			
			x0 = Integer.parseInt(tokens.nextToken());
			y0 = Integer.parseInt(tokens.nextToken());
			x1 = Integer.parseInt(tokens.nextToken());
			y1 = Integer.parseInt(tokens.nextToken());
			
			for(int h = y0; h < y1; h++) {
				for(int w = x0; w < x1; w++)
					visited[h][w] = true;
			}
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					nComps++;
					bfs(i, j);
				}
			}
		}
		
		System.out.println(nComps);
		Collections.sort(areaList);
		for(Integer i : areaList)
			System.out.print(i + " ");
		System.out.println();
	}
}
