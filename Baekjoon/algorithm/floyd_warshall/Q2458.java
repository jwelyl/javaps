import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int LOSE = -1, UNKNOWN = 0, WIN = 1;
	static int N, M, ans;
	static int[][] graph;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static void floydWarshall() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(graph[i][j] == UNKNOWN) {
						//	i가 k를 이기고, k가 j를 이길 경우, i는 k를 이김
						if(graph[i][k] == WIN && graph[k][j] == WIN)
							graph[i][j] = WIN;
						//	i가 k에게 지고, k가 j에게 질 경우, i는 j에게 짐
						else if(graph[i][k] == LOSE && graph[k][j] == LOSE)
							graph[i][j] = LOSE;
					}
				}
			}
		}
	}
	
	static void printGraph() {
		System.out.println("\n*************** Debugging ***************");
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++)
				System.out.printf("%2d ", graph[i][j]);
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String line = bf.readLine();
		tokens = new StringTokenizer(line);
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		graph = new int[N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			int loser, winner;
			
			line = bf.readLine();
			tokens = new StringTokenizer(line);
			
			loser = Integer.parseInt(tokens.nextToken());
			winner = Integer.parseInt(tokens.nextToken());
			
			graph[loser][winner] = LOSE;
			graph[winner][loser] = WIN;
		}
		
		
//		printGraph();
		floydWarshall();
//		printGraph();
		
		for(int i = 1; i <= N; i++) {
			int winOrLose = 0;	//	i가 j를 이기거나 진 횟수
			
			for(int j = 1; j <= N; j++)	{
				if(graph[i][j] != UNKNOWN)	//	i가 j를 이기거나 질 경우
					winOrLose += 1;
			}
			
			if(winOrLose == N - 1)	//	 i가 i를 제외한 다른 모두와 우열이 명확할 경우
				ans++;	//	i의 등수를 확실히 알 수 있음
		}
		
		System.out.println(ans);
	}
}