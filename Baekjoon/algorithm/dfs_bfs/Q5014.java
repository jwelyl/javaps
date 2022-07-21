import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static String error = "use the stairs";
	static int F, S, G, U, D, ans;
	static boolean[] visited;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static boolean canGo(int cur) {
		return (1 <= cur && cur <= F) && !visited[cur];
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int input = 0;
		
		visited[S] = true;
		q.offer(S);
		input++;
		
		while(!q.isEmpty()) {
			int tmp = 0;
			
			for(int i = 0; i < input; i++) {
				int cur = q.poll();
				int next;
				
				if(cur == G)
					return;
				
				//	위로 가는 경우
				next = cur + U;
				if(canGo(next)) {
					q.offer(next);
					visited[next] = true;
					tmp++;
				}
				
				//	아래로 가는 경우
				next = cur - D;
				if(canGo(next)) {
					q.offer(next);
					visited[next] = true;
					tmp++;
				}
			}	//	for-end
			ans++;
			input = tmp;
		}	//	while-end	
		
		ans = -1;
	}	//	bfs-end
	
	public static void main(String[] args) throws IOException {
		String line  = bf.readLine();
		tokens = new StringTokenizer(line);
		
		F = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		G = Integer.parseInt(tokens.nextToken());
		U = Integer.parseInt(tokens.nextToken());
		D = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[F + 1];
		
		bfs();
		
		if(ans != -1)
			System.out.println(ans);
		else
			System.out.println(error);
	}
}
