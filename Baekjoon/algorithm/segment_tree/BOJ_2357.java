import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;

	static int height, treeSize;
	static int[] minSegtree;
	static int[] maxSegtree;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		nums = new int[N];
		height = (int) Math.ceil(Math.log(N) / Math.log(2));
		treeSize = (int) Math.pow(2, height + 1);

		minSegtree = new int[treeSize];
		maxSegtree = new int[treeSize];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(bf.readLine());
		}

		init(false, minSegtree, 1, 0, N - 1);
		init(true, maxSegtree, 1, 0, N - 1);
		
//		System.out.println();
//		System.out.print("minSegtree : ");
//		for(int num : minSegtree)
//			System.out.print(num + " ");
//		System.out.println();
//		System.out.print("maxSegtree : ");
//		for(int num : maxSegtree)
//			System.out.print(num + " ");
//		System.out.println();

		for (int i = 0; i < M; i++) {
			int a, b;

			tokens = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(tokens.nextToken()) - 1;
			b = Integer.parseInt(tokens.nextToken()) - 1;

			sb.append(get(false, minSegtree, 1, a, b, 0, N - 1)).append(" ")
					.append(get(true, maxSegtree, 1, a, b, 0, N - 1)).append("\n");
		}

		System.out.println(sb);
	}

	static int init(boolean isMax, int[] segtree, int node, int start, int end) {
		if (start == end) { // 단말 노드일 경우
			return segtree[node] = nums[start];
		}

		int left = node * 2;
		int right = node * 2 + 1;
		int mid = (start + end) / 2;

		if (!isMax) {
			return segtree[node] = Integer.min(init(isMax, segtree, left, start, mid),
					init(isMax, segtree, right, mid + 1, end));
		} else {
			return segtree[node] = Integer.max(init(isMax, segtree, left, start, mid),
					init(isMax, segtree, right, mid + 1, end));
		}
	}

	static int get(boolean isMax, int[] segtree, int node, int left, int right, int start, int end) {
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		if(isMax) {	//	최댓값
			if(left > end || right < start)
				return Integer.MIN_VALUE;
			else if(left <= start && end <= right)
				return segtree[node];
			else
				return Integer.max(get(isMax, segtree, lc, left, right, start, mid),
									get(isMax, segtree, rc, left, right, mid + 1, end));
			
		}
		else {	//	최솟값
			if(left > end || right < start)
				return Integer.MAX_VALUE;
			else if(left <= start && end <= right)
				return segtree[node];
			else
				return Integer.min(get(isMax, segtree, lc, left, right, start, mid),
									get(isMax, segtree, rc, left, right, mid + 1, end));
		}
	}
}