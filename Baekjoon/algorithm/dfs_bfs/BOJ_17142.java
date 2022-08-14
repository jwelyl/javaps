import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

public class BOJ_17142 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	static int[][] lab;
	static int[][] cost;

	static int mustVisit = 0;
	static int actualVisit = 0;
	static int time = 0;					//	활성화시키는 각 경우에 대하여 바이러스 전부 퍼지는 최소 시간
	static int ans = Integer.MAX_VALUE;		//	모든 경우 통틀어서 바이러스 전부 퍼지는 최소 시간
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		lab = new int[N][N];
		cost = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			
			for(int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(tokens.nextToken());
				
				if(lab[i][j] != 1) {	//	벽이 아닐 경우 바이러스가 퍼져야 함
					mustVisit++;
					
					if(lab[i][j] == 2) {	//	바이러스를 놓을 수 있는 위치일 경우
						virusList.add(new Pos(i, j));
					}
				}
			}
		}
		mustVisit -= (virusList.size() - M);	//	비활성화된 바이러스 위치는 굳이 퍼지지 않아도 됨
		
//		System.out.println("In main");
//		System.out.println("mustVisit = " + mustVisit);
//		System.out.println("actualVisit = " + actualVisit);
		
		
		combination(0, 0, new int[M]);
		System.out.println(ans);
	}
	
	//	바이러스 놓을 수 있는 곳 좌표 class
	static class Pos {
		int y;
		int x;
		
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	//	활성화 시킬 바이러스가 있는 모든 위치 Pos 리스트
	static List<Pos> virusList = new ArrayList<Pos>();
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
	
	static void reset() {
		time = 0;
		actualVisit = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				cost[i][j] = -1;
			}
		}
	}
	
	static void combination(int nth, int startIdx, int[] result) {
		if(nth == M) {
			reset();
			bfs(result);
			
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++)
//					System.out.print(cost[i][j] + " ");
//				System.out.println();
//			}
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			if(time == -1) {
				if(ans == Integer.MAX_VALUE)
					ans = -1;
			}
			else {
				if(ans == -1)
					ans = time;
				else
					ans = Math.min(ans, time);
			}
			
			return;
		}
		
		for(int i = startIdx; i < virusList.size(); i++) {
			result[nth] = i;
			combination(nth + 1, i + 1, result);
		}
	}
	
	static void bfs(int[] result) {
		Queue<Pos> q = new LinkedList<Pos>();
		for(int i = 0; i < result.length; i++) {
			Pos p = virusList.get(result[i]);
			int y = p.y;
			int x = p.x;
			
			cost[y][x] = time;
			actualVisit++;
			q.offer(p);
		}
//		System.out.println("mustVisit = " + mustVisit);
//		System.out.println("actualVisit = " + actualVisit);
		if(mustVisit == actualVisit)
			return;
		
		
		WHILE : while(!q.isEmpty()) {
			int size = q.size();
			time++;
//			System.out.println("time = " + time);
			
			for(int i = 0; i < size; i++) {
				Pos p = q.poll();
				int cy = p.y;
				int cx = p.x;
				
				for(int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					
					if(!isIn(ny, nx))	//	범위를 벗어날 경우 skip
						continue;
					
					if(lab[ny][nx] == 1)	//	벽일 경우 skip
						continue;
					
					if(cost[ny][nx] != -1)	//	이미 방문한 곳일 경우 skip
						continue;
					
					//	lab[ny][nx] = 0 : 빈 칸이거나, 2 : 비활성화 바이러스일 경우
					cost[ny][nx] = time;	//	방문함
					if(lab[ny][nx] == 0) {	//	빈 칸일 경우 바이러스가 반드시 퍼져야 하는 칸임	
						actualVisit++;
					}	
					if(actualVisit == mustVisit) 	//	방문해야 할 모든 칸을 방문한 경우
						break WHILE;
					
					q.offer(new Pos(ny, nx));
				}
			}
		}	//	while-end
		
		if(actualVisit < mustVisit)	//	모든 칸에 퍼지지 못한 경우
			time = -1;
		
	}	//	bfs-end

	
}
