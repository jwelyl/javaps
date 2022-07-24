import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N, X;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static class Element implements Comparable<Element> {
		int content;
		
		Element(int content) {
			this.content = content;
		}

		@Override
		public int compareTo(Element e) {
			if(Math.abs(this.content) != Math.abs(e.content))
				return Math.abs(this.content) - Math.abs(e.content);
			return this.content - e.content;
		}
	};
	
	static PriorityQueue<Element> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String line = bf.readLine();
		N = Integer.parseInt(line);
		
		for(int i = 0; i < N; i++) {
			line = bf.readLine();
			X = Integer.parseInt(line);
			
			if(X != 0)
				pq.offer(new Element(X));
			else {
				int x = pq.isEmpty() ? 0 : pq.poll().content;
				sb.append(x + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
