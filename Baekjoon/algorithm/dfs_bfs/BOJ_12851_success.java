import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12851_success {
	final static int MAX = 100_001;
	static int N, K;
	static boolean[] visited = new boolean[MAX];
	static int depth = 0, cnt = 0;
	
	static Scanner sc = new Scanner(System.in);
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		K = sc.nextInt();
	
		if(N == K) {
			depth = 0;
			cnt = 1;
			System.out.println(depth + "\n" + cnt);
		} else {
			bfs();
		}
	}
	
	static boolean isIn(int next) {
		return 0 <= next && next < MAX;
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		
		while(!q.isEmpty()) {
			int input = q.size();
			
			depth++;	//	next들의 깊이로 1 증가
			for(int i = 0; i < input; i++) {
				int cur = q.poll();
				int[] next = {cur - 1, cur + 1, cur * 2};
				
				visited[cur] = true;	//	현재 층들의 정점들을 방문 처리
				
				for(int j = 0; j < 3; j++) {
					if(isIn(next[j]) && !visited[next[j]])
						q.offer(next[j]);
					
					if(next[j] == K)
						cnt++;
				}
			}
			
			if(cnt > 0)	 {	//	K에 도달함.
				q.clear();	//	Queue를 비움
				break;
			}
		}
		
		System.out.println(depth + "\n" + cnt);
	}

}
