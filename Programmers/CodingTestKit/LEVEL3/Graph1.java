import java.util.*;

class Node implements Comparable<Node> {
	public int num;			//	노드 번호
	public int distance;	//	노드 거리
	
	public Node(int num, int distance) {	//	노드 생성자
		this.num = num;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(Node other) {	//	정렬 기준(distance가 작은 노드가 먼저 반환)
		if(this.distance < other.distance)
			return -1;
		return 1;
	}
}

class Solution {
	final int INF = (int)1e9;
	int[] dist;
	
	ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	
	void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		dist[start] = 0;
		pq.offer(new Node(start, dist[start]));
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int curNum = curNode.num;
			int curDist = curNode.distance;
			
			if(dist[curNum] < curDist)
				continue;
			
			for(int i = 0; i < graph.get(curNum).size(); i++) {
				int nextNum = graph.get(curNum).get(i).num;
				int nextDist = curDist + graph.get(curNum).get(i).distance;
				
				if(nextDist < dist[nextNum]) {
					dist[nextNum] = nextDist;
					pq.offer(new Node(nextNum, dist[nextNum]));
				}
			}
		}
	}
	
	public int solution(int n, int[][] edge) {
        int answer = 0;
        int max = 0;
        
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        
        for(int i = 0; i <= n; i++)
        	graph.add(new ArrayList<Node>());
        
        for(int i = 0; i < edge.length; i++) {
        	int a = edge[i][0];
        	int b = edge[i][1];
        	
        	graph.get(a).add(new Node(b, 1));
        	graph.get(b).add(new Node(a, 1));
        }
        
        dijkstra(1);
        
        for(int i = 1; i <= n; i++) {
        	if(dist[i] > max) {
        		max = dist[i];
        		answer = 1;
        	}
        	else if(dist[i] == max)
        		answer += 1;
        }

        return answer;
    }
}
