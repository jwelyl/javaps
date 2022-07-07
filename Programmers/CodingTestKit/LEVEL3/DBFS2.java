import java.util.*;

class Solution {
	ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	boolean visited[];
	
	void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				
				if(!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
	}
	
	public int solution(int n, int[][] computers) {
		int answer = 0;
		visited = new boolean[n];
		Arrays.fill(visited, false);
		
		for(int i = 0; i < n; i++)
			graph.add(new ArrayList<Integer>());
		
		for(int i = 0; i < computers.length; i++) {
			for(int j = 0; j < computers[i].length; j++) {
				if(i != j && computers[i][j] == 1)
					graph.get(i).add(j);
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				answer += 1;
				bfs(i);
			}
		}
		
		return answer;
	}
}
