import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly G1
 * @performance 330312KB 1400ms
 * @category # segment tree
 * @memo
 * @etc
 * 
 */

public class BOJ_2268 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static long[] nums;

	static int height, treeSize;
	static long[] segtree;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		nums = new long[N];
		height = (int) Math.ceil(Math.log(N) / Math.log(2));
		treeSize = (int) Math.pow(2, height + 1);
		segtree = new long[treeSize];

		init(1, 0, N - 1);
		
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(bf.readLine());

			int cmd, a, b;

			cmd = Integer.parseInt(tokens.nextToken());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());

			if(cmd == 0) {
				if(a > b) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				
				sb.append(sum(1, 0, N - 1, a - 1, b - 1)).append("\n");
			}
			else {
				long diff = b - nums[a- 1];
				nums[a - 1] = b;
				
				update(1, 0, N - 1, a - 1, diff);
				
//				System.out.println();
//				System.out.print("nums : ");
//				for(int s : nums)
//					System.out.print(s + " ");
//				System.out.println();
//				
//				System.out.println();
//				System.out.print("segtree : ");
//				for(int s : segtree)
//					System.out.print(s + " ");
//				System.out.println();
			
			}
		}
		
		System.out.println(sb);
	}

	static long init(int node, int start, int end) {
		if (start == end) // 단말 노드일 경우
			return segtree[node] = nums[start];

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;

		return segtree[node] = init(lc, start, mid) + init(rc, mid + 1, end);
	}

	static long sum(int node, int start, int end, int left, int right) {
		if (left > end || right < start) // 합을 구하려는 범위 [left, right]가 [start, end]와 겹치지 않을 경우
			return 0;

		if (left <= start && end <= right) // 합을 구하려는 범위가 노드 범위보다 넓을 경우
			return segtree[node];

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;

		return sum(lc, start, mid, left, right) + sum(rc, mid + 1, end, left, right);
	}

	static void update(int node, int start, int end, int idx, long diff) {
//		System.out.println("update(" + node + ", "+ start + ", " + end + ", " + idx + ", " + diff + ")");
		
		if (idx < start || idx > end) // 바꾸려는 idx가 [start, end]에 포함되지 않을 경우
			return;

		segtree[node] += diff;

		if (start != end) {	//	해당 노드가 단말 노드가 아닐 경우
			int lc = node * 2;
			int rc = node * 2 + 1;
			int mid = (start + end)/ 2;
			
			//	자식 노드들도 update
			update(lc, start, mid, idx, diff);
			update(rc, mid + 1, end, idx, diff);
		}
	}
}
