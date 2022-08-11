import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {
	static int L, R, C;
	static char[][][] map;
	static boolean[][][] visited;
	
	static int sz, sy, sx;
	static int time;

	static int[] dz = { 0, 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, 0, -1, 0, 0 };
	static int[] dx = { 1, 0, -1, 0, 0, 0 };
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		while(true) {
			time = 0;
			tokens = new StringTokenizer(bf.readLine());
			L = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
		
			if(L == 0 && R == 0 && C == 0)
				break;
			
			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			
			for(int l = 0; l < L; l++) {
				for(int r = 0; r < R; r++) {
					String line = bf.readLine();
					
					for(int c = 0; c < C; c++) {
						if(line.charAt(c) == 'S') {
							sz = l;
							sy = r;
							sx = c;
						}
						
						map[l][r][c] = line.charAt(c);
					}
				}
				
				bf.readLine();	//	빈 줄 입력
			}
			
//			for(int i = 0; i < L; i++) {
//				for(int j = 0; j < R; j ++) {
//					System.out.println(map[i][j].toString());
//					sb.append(map[i][j]).append("\n");?
//				}
//				System.out.println();
//				sb.append("\n");
//			}
			bfs();
//			sb.append("\n\n");
			
			if(time == -1)
				sb.append("Trapped!\n");
			else
				sb.append("Escaped in ").append(time).append(" minute(s).\n");
			
		}
		
		System.out.println(sb);
	}

	static class Node {
		int z;
		int y;
		int x;
		
		Node(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}
	
	static boolean isIn(int z, int y, int x) {
		return (0 <= z && z < L) && (0 <= y && y < R) && (0 <= x && x < C);
	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<Node>();
		visited[sz][sy][sx] = true;
		q.offer(new Node(sz, sy, sx));
//		sb.append("(" + sz + ", " + sy + ", " + sx + ")\n");
		time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for(int i = 0; i < size; i++) {
				Node node = q.poll();
				int cz = node.z;
				int cy = node.y;
				int cx = node.x;
				
				for(int d = 0; d < 6; d++) {
					int nz = cz + dz[d];
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					
					if(isIn(nz, ny, nx) && map[nz][ny][nx] != '#' && !visited[nz][ny][nx] ) {
//						sb.append("(" + nz + ", " + ny + ", " + nx + ")\n");
						
						if(map[nz][ny][nx] == 'E') 
							return;
						
						visited[nz][ny][nx] = true;
						q.offer(new Node(nz, ny, nx));
					}
				}
			}
		}
		
		time = -1;
	}
}
