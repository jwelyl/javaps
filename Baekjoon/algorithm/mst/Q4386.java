import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Star {
		int num;	//	별 번호
		double x;	//	별 x 좌표
		double y;	//	별 y 좌표
		
		Star(int num, double x, double y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "s" + this.num + " : (" + this.x + ", " + this.y + ")";
		}
	}
	
	static class Edge {
		int num;		//	edge 번호
		Star s1;		//	별 1
		Star s2;		//	별 2
		double dist;	//	s1, s2 거리
		
		Edge(Star s1, Star s2) {
			this(0, s1, s2);
		}
		
		Edge(int num, Star s1, Star s2) {
			this.num = num;
			this.s1 = s1;
			this.s2 = s2;
			this.dist = Math.sqrt(Math.pow(this.s1.x - this.s2.x, 2) 
					+ Math.pow(this.s1.y - this.s2.y, 2));
		}
		
		@Override
		public String toString() {
			return "e" + this.num + " : [" + this.s1.toString() + ", " + this.s2.toString() + ", 길이 = " + dist + "]";	
		}
	}
	
	static int[] parents;
	
	static int N;
	static Star[] stars;
	static List<Edge> edges = new ArrayList<Edge>();
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int findParent(int x) {
		if(x == parents[x])
			return x;
		return parents[x] = findParent(parents[x]);
	}
	
	static void unionParents(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x != y) {
			if(x > y)
				parents[x] = y;
			else
				parents[y] = x;
		}
	}
	
	static double kruskal() {
		double mstCost = 0;
		int numOfEdges = 0;
		
		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				// TODO Auto-generated method stub
				return Double.compare(e1.dist, e2.dist);
			}
		});
		
//		for(Edge e : edges)
//			System.out.println(e);
		
		for(int i = 0; i < edges.size(); i++) {
			Star s1 = edges.get(i).s1;
			Star s2 = edges.get(i).s2;
			int num1 = s1.num;
			int num2 = s2.num;
			
			if(findParent(num1) != findParent(num2)) {
				unionParents(num1, num2);
				numOfEdges++;
				mstCost += edges.get(i).dist;
				
				if(numOfEdges == N - 1)
					break;
			}
		}
		
		return mstCost;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());
		stars = new Star[N];
		parents = new int[N];
		for(int i = 0; i < N; i++)
			parents[i] = i;
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			stars[i] = new Star(i, Double.parseDouble(tokens.nextToken()), Double.parseDouble(tokens.nextToken()));
		}
		
//		for(Star s : stars)
//			System.out.println(s);
//		
//		for(int i = 0; i < N; i++)
//			System.out.println(stars[i]);
		
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				Edge e = new Edge(stars[i], stars[j]);
//				System.out.println(e);
				edges.add(e);
			}
		}
		
		System.out.println(kruskal());
	}
}
