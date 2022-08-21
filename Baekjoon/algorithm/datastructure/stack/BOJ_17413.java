import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
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

public class BOJ_17413 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static String input;
	static Queue<String> queue = new LinkedList<String>();
	static Stack<String> stack = new Stack<String>();
	
	static void emptyStack() {
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
	}
	
	static void emptyQueue() {
		while(!queue.isEmpty()) {
			sb.append(queue.poll());
		}
	}
	
	public static void main(String[] args) throws IOException {
		input = bf.readLine();
		
		boolean tag = false;
		for(int i = 0; i < input.length(); i++) {
			String cur = input.substring(i, i + 1);
			if(cur.equals("<")) {	//	tag가 시작될 경우
				tag = true;
				emptyStack();
				queue.offer(cur);
				continue;
			}
			else if(cur.equals(">")) {	//	tag가 끝날 경우
				tag = false;
				emptyQueue();
				sb.append(cur);
				continue;
			}
			else if(!tag && cur.equals(" ")) {	//	한 단어가 끝난 경우
				emptyStack();
				sb.append(cur);
				continue;
			}
			
			if(!tag)
				stack.push(cur);
			else
				queue.offer(cur);
		}
		emptyStack();
		
		System.out.println(sb);
	}
}
