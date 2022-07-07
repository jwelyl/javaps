package ssafy;

import java.util.*;

class Pair {
	public int first;
	public int second;
	
	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

public class Q1226 {
	static int dy[] = {0, 1, 0, -1};
	static int dx[] = {1, 0, -1, 0};
		
	static boolean bfs(int sy, int sx, char[][] maze) {
		Queue<Pair> q = new LinkedList<Pair>();
		maze[sy][sx] = '4';
		q.offer(new Pair(sy, sx));
		
		while(!q.isEmpty()) {
			Pair pair = q.poll();
			int curY = pair.first;
			int curX = pair.second;
			
			for(int i = 0; i < 4; i++) {
				int nextY = curY + dy[i];
				int nextX = curX + dx[i];
				
				if(nextY < 0 || nextY >= 16 || nextX < 0 || nextX >= 16)
					continue;
				
				if(maze[nextY][nextX] == '1' || maze[nextY][nextX] == '4')
					continue;
				
				if(maze[nextY][nextX] == '3')
					return true;
				
				maze[nextY][nextX] = '4';
				q.offer(new Pair(nextY, nextX));
			}
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) {
			int tc;
			int sy = 1, sx = 1;
			char[][] maze = new char[16][16];
			
			tc = sc.nextInt();
			sc.nextLine();
			for(int i = 0; i < 16; i++) {
				String str = sc.nextLine();
				for(int j = 0; j < 16; j++) {
					maze[i][j] = str.charAt(j);
				
					if(maze[i][j] == '2') {
						sy = i;
						sx = j;
					}
				}
			}
			
			System.out.println("#" + tc + " " + (bfs(sy, sx, maze) ? 1 : 0));
		}
	}
}
