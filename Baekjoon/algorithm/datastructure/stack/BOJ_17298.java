import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly
 * @performance KB ms
 * @category #
 * @memo
 * @etc
 * 
 */

public class BOJ_17298 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int[] input;
	static Stack<Integer> ans = new Stack<>();
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		input = new int[N];

		tokens = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(tokens.nextToken());
		}

		for (int i = N - 1; i >= 0; i--) {
			while(true) {
				if(stack.isEmpty()) {
					stack.push(input[i]);
					ans.push(-1);
					break;
				}
				
				int top = stack.peek();
				if(top > input[i]) {
					stack.push(input[i]);
					ans.push(top);
					break;
				}
				
				stack.pop();
			}
		}

		while (!ans.isEmpty())
			sb.append(ans.pop()).append(" ");
		System.out.println(sb);
	}
}
