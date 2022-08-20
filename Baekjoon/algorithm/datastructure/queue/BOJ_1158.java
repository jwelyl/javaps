import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1158 {
	static int N, K;
//	static Scanner sc = new Scanner(System.in);
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static Queue<Integer> q = new LinkedList<Integer>();
	static Queue<Integer> result = new LinkedList<Integer>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		for(int i = 1; i <= N; i++)
			q.offer(i);
		
		while(!q.isEmpty()) {
			for(int k = 0; k < K; k++) {
				int out = q.poll();
				
				if(k + 1 != K)
					q.offer(out);
				else
					result.offer(out);
			}
		}
		
		sb.append("<");
		while(!result.isEmpty()) {
			sb.append(result.poll());
			if(result.isEmpty())
				sb.append(">");
			else
				sb.append(", ");
		}
		System.out.println(sb);
	}
}