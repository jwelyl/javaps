import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2617 {
	static int N, M;
	static int[][] graph;
	static int ans = 0;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		graph = new int[N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			int lighter, heavier;
			tokens = new StringTokenizer(bf.readLine());
			
			heavier = Integer.parseInt(tokens.nextToken());
			lighter = Integer.parseInt(tokens.nextToken());
			
			
			graph[lighter][heavier] = -1;
			graph[heavier][lighter] = 1;
		}
		
		floydWarshall();
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++)
//				System.out.print(graph[i][j] + " ");
//			System.out.println();
//		}
		
		for(int i = 1; i <= N; i++) {
			int more = 0, less = 0;
			
			for(int j = 1; j <= N; j++) {
				if(graph[i][j] == -1)
					more++;
				else if(graph[i][j] == 1)
					less++;
			}
			
			if(more > N / 2 || less > N / 2)
				ans++;
		}
		
		System.out.println(ans);
	}
	
	static void floydWarshall() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(graph[i][k] == -1 && graph[k][j] == -1)
						graph[i][j] = -1;
					else if(graph[i][k] == 1 && graph[k][j] == 1)
						graph[i][j] = 1;
				}
			}
		}
	}

}
