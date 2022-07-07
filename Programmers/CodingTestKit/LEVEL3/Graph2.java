import java.util.*;

class Solution {
	final int WIN = 1;
	final int UNKNOWN = 0;
	final int LOSE = -1;
	int graph[][];
	
	void printGraph(int n) {
        for(int i = 1; i <= n; i++) {
        	for(int j = 1; j <= n; j++) {
        		System.out.print(graph[i][j] + " ");
        	}
        	System.out.println();
        }
	}
	
	void floydWarshall(int n) {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(graph[i][k] == WIN && graph[k][j] == WIN) {
						graph[i][j] = WIN;
						graph[j][i] = LOSE;
					}
				}
			}
		}
	}
	
	public int solution(int n, int[][] results) {
        int answer = 0;
        
        graph = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++)
        	Arrays.fill(graph[i], UNKNOWN);
        
        
        for(int i = 0; i < results.length; i++) {
        	int winner = results[i][0];
        	int loser = results[i][1];
        	
        	graph[winner][loser] = WIN;
        	graph[loser][winner] = LOSE;
        }
        
        floydWarshall(n);
        // printGraph(n);
        
        for(int i = 1; i <= n; i++) {
        	int unknown = 0;
        	
        	for(int j = 1; j <= n; j++) {
        		if(graph[i][j] == UNKNOWN)
        			unknown += 1;
        	}
        	
        	if(unknown == 1)
        		answer += 1;
        }
        
        return answer;
    }
}
