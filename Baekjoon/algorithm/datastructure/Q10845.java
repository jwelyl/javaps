import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//push X: 정수 X를 큐에 넣는 연산이다.
//pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//size: 큐에 들어있는 정수의 개수를 출력한다.
//empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
//front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.

public class Main {
	static int N;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		String line = bf.readLine();
		int X = -1;	//	가장 최근에 삽입된 element
		N = Integer.parseInt(line);
		
		for(int i = 0; i < N; i++) {
			String cmd;
			
			line = bf.readLine();
			tokens = new StringTokenizer(line);
			
			cmd = tokens.nextToken();
			
			if(cmd.equals("push")) {
				X = Integer.parseInt(tokens.nextToken());
				q.offer(X);
			} else if(cmd.equals("pop")) {
				if(q.isEmpty())
//					System.out.println(-1);
					sb.append("-1\n");
				else
//					System.out.println(q.poll());
					sb.append(q.poll() + "\n");
			} else if(cmd.equals("size")) {
//				System.out.println(q.size());
				sb.append(q.size() + "\n");
			} else if(cmd.equals("empty")) {
				if(q.isEmpty())
//					System.out.println(1);
					sb.append(1 + "\n");
				else
//					System.out.println(0);
					sb.append(0 + "\n");
			} else if(cmd.equals("front")) {
				if(q.isEmpty())
//					System.out.println(-1);
					sb.append(-1 + "\n");
				else
//					System.out.println(q.peek());
					sb.append(q.peek() + "\n");
			} else if(cmd.equals("back")) {
				if(q.isEmpty())
//					System.out.println(-1);
					sb.append(-1 + "\n");
				else
//					System.out.println(X);
					sb.append(X + "\n");
			}
			
		}
		
		System.out.println(sb);
	}
}
