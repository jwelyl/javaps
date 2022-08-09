import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12851_fail {
	final static int MAX = 2 * 100_000;
	static int N, K;
	static int depth = 0, cnt =0;	//	가장 처음 K를 만나는 깊이, 해당 깊이에서 K를 만나는 횟수
	static boolean[] visited = new boolean[MAX + 1];
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		N = sc.nextInt();
		K = sc.nextInt();
		
		bfs();
	}
	
	static boolean isIn(int cur) {
		return 0 <= cur && cur <= MAX;
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int input = 1;	//	깊이 0에서 Queue에 삽입된 개수	
		q.offer(N);		//	깊이 0일때 N 유일하게 삽입
		visited[N] = true;	//	N은 방문처리
		
		while(!q.isEmpty()) {
			boolean isFound = false;	//	현재 깊이에서 K에 도달할 경우 true
			int tmp = 0;				//	Queue에 추가된 다음 깊이 정점 개수
			
//			System.out.println("depth = " + depth);
			for(int i = 0; i < input; i++) {	//	현재 깊이에서 Queue에 삽입한 만큼 poll
				int cur = q.poll();
				int next;
//				System.out.print(cur + " ");
				
				if(cur == K) {			//	목적지랑 일치할 경우
					isFound = true;		
					cnt++;				//	발견 개수 1 증가
					continue;			//  현재 정점에서 다음 정점 탐색 필요 없음
				}
				
				next = cur - 1;
				if(isIn(next) && !visited[next] || next == K) {
					visited[next] = true;
					q.offer(next);
					tmp++;
				}
				
				next = cur + 1;
				if(isIn(next) && !visited[next] || next == K) {
					visited[next] = true;
					q.offer(next);
					tmp++;
				}
				
				next = cur * 2;
				if(isIn(next) && !visited[next] || next == K) {
					visited[next] = true;
					q.offer(next);
					tmp++;
				}
			}
//			System.out.println();
			
			if(isFound)
				break;
			
			depth++;	//	깊이 1 증가
			input = tmp;	//	이번 깊이에서 Queue에 집어넣은 개수
		}
		
		System.out.println(depth);
		System.out.println(cnt);
	}
}