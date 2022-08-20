import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class BOJ_1874 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static Stack<Integer> stack = new Stack<>();
	static int[] sequence;
	static ArrayList<Character> answer = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		sequence = new int[N];

		for (int i = 0; i < N; i++)
			sequence[i] = Integer.parseInt(bf.readLine());

		int value = 1;
		for (int i = 0; i < N; i++) {
			int num = sequence[i];
			int next = -1;

			if (num >= value) {
				for (int j = value; j <= num; j++) {
					stack.push(j);
					answer.add('+');
				}
				next = stack.pop() - 1;
				answer.add('-');
				value = num + 1;
			}
			else {
				int out = stack.peek();
				if(num < out)
					break;
				
				stack.pop();
				answer.add('-');
			}
		}
		
		if(answer.size() != 2 * N)
			sb.append("NO\n");
		else {
			for(Character ch : answer)
				sb.append(ch).append("\n");
		}
		
		System.out.println(sb);
	}
}
