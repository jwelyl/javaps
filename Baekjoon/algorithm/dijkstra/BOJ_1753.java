import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_1753 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	final static int INF = Integer.MAX_VALUE;
	static int V, E;
	static int K;
	
	static int[] dist;
	static List<int[]>[] graph; 
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
	
		K = Integer.parseInt(bf.readLine());
		
		graph = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++)
			graph[i] = new ArrayList<>();
		
		dist = new int[V + 1];
		
		for(int i = 0; i < E; i++) {
			int from, to, cost;
			int[] input = new int[2];
			
			tokens = new StringTokenizer(bf.readLine());
			
			from = Integer.parseInt(tokens.nextToken());
			to = Integer.parseInt(tokens.nextToken());
			cost = Integer.parseInt(tokens.nextToken());
			
			input[0] = to;
			input[1] = cost;
			
			graph[from].add(input);
		}
		
		dijkstra();
		for(int i = 1; i <= V; i++) {
			if(dist[i] != INF)
				sb.append(dist[i]).append("\n");
			else
				sb.append("INF\n");
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[1], o2[1]);
			}
		});
		int[] input = {K, 0};
		pq.offer(input);
		Arrays.fill(dist, INF);
		dist[K] = 0;
		
		while(!pq.isEmpty()) {
			int[] output = pq.poll();
			int cvertex = output[0];
			int cdist = output[1];
			
			if(cdist > dist[cvertex])
				continue;
			
			for(int i = 0; i < graph[cvertex].size(); i++) {
				int nvertex = graph[cvertex].get(i)[0];
				int ndist = cdist + graph[cvertex].get(i)[1];
				
				if(ndist < dist[nvertex]) {
					int[] nInput = new int[2];
					dist[nvertex] = ndist;
					nInput[0] = nvertex;
					nInput[1] = ndist;
					pq.offer(nInput);
				}
			}
		}
	}
}