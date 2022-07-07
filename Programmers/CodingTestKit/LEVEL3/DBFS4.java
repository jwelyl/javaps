import java.util.*;

class Solution {
	List<String> route = new ArrayList<String>();
	boolean[] visited;
	boolean ok = false;
	int n, cnt = 0;
	
	void dfs(String start, String[][] tickets) {
//		System.out.println("dfs(" + start + ")");
		
		if(cnt == n)
			ok = true;
		route.add(start);
		
		for(int i = 0; i < n; i++) {
			if(!visited[i] && start.equalsIgnoreCase(tickets[i][0])) {
				visited[i] = true;
				cnt++;
				dfs(tickets[i][1], tickets);
			
				if(!ok) {
					cnt--;
					visited[i] = false;
					route.remove(route.size() - 1);
				}
			}
		}
	}
	
	public String[] solution(String[][] tickets) {
        String[] answer = {};
        n = tickets.length;
        visited = new boolean[n];
        Arrays.fill(visited, false);
        
        Arrays.sort(tickets, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				return o1[0].compareToIgnoreCase(o2[0]) != 0 ? o1[0].compareToIgnoreCase(o2[0]) : o1[1].compareToIgnoreCase(o2[1]);
			}
        });
        
        dfs("ICN", tickets);
        
//        for(String s: route)
//        	System.out.print(s + " ");
//        System.out.println();
        
        answer = new String[route.size()];
        for(int i = 0; i < answer.length; i++)
        	answer[i] = route.get(i);
        
        return answer;
    }
}
