package ssafy;

import java.util.*;

public class Q1238 {
	static class Edge {
		int from;
		int to;
		
		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
		
		@Override
		public boolean equals(Object o) {
			Edge e = (Edge)o;
			return (this.from == e.from && this.to == e.to);
		}

	};
	
	static int T = 0, N, start, res = 0, ans = 0;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer >> graph;
	static Set<Edge> vSet;
	
	static Scanner sc = new Scanner(System.in);
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int input = 1;
		q.offer(start);
		visited[start] = true;
		res = 0; ans = 0;
		
		while(!q.isEmpty()) {
			int tmp = 0;
			
			for(int i = 0; i < input; i++) {
				int cur = q.poll();
				
//				System.out.print("next : ");
				for(int j = 0; j < graph.get(cur).size(); j++) {
					int next = graph.get(cur).get(j);
					
					if(!visited[next]) {
						visited[next] = true;
						q.offer(next);
						tmp += 1;
						res = (res > next) ? res : next;
//						System.out.print(next + " ");
					}
				}
//				System.out.println();
			}
			
//			System.out.println("tmp = " + tmp + ", res = " + res);
			if(tmp > 0) {
				input = tmp;
				ans = res;
				res = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		while(sc.hasNext()) {
			String input1 = sc.nextLine();
			String input2 = sc.nextLine();
			String[] parse1 = input1.split(" ");
			String[] parse2 = input2.split(" ");

			N = Integer.parseInt(parse1[0]);
			start = Integer.parseInt(parse1[1]);
			
			
			visited = new boolean[101];
			Arrays.fill(visited, false);
			
			graph = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < 101; i++)
				graph.add(new ArrayList<Integer>());
			vSet = new HashSet<Edge>();
			
			for(int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(parse2[2 * i]);
				int to = Integer.parseInt(parse2[2 * i + 1]);
				Edge e = new Edge(from, to);
				
				if(!vSet.contains(e)) {
					vSet.add(e);
					graph.get(from).add(to);
				}
			}
			
			bfs();
			System.out.println("#" + (++T) + " " + ans);
			ans = 0;
		}
	}

}
