import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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

public class BOJ_14427 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static int[] nums;

	static int height, treeSize;
	static int[] segtree;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());

		nums = new int[N];
		height = (int) Math.ceil(Math.log(N) / Math.log(2));
		treeSize = (int) Math.pow(2, height + 1);
		segtree = new int[treeSize];

		tokens = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());

//		System.out.print("nums : ");
//		for (int i = 0; i < N; i++)
//			System.out.print(nums[i] + " ");
//		System.out.println();

		init(1, 0, N - 1);

//		System.out.print("\n\nsegtree : ");
//		for (int i = 0; i < treeSize; i++)
//			System.out.print(segtree[i] + " ");
//		System.out.println();

//		while (true) {
//			int from, to;
//			tokens = new StringTokenizer(bf.readLine());
//			from = Integer.parseInt(tokens.nextToken());
//			to = Integer.parseInt(tokens.nextToken());
//
//			System.out.println(minIdx(1, from, to, 0, N - 1));
//		}
		M = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(bf.readLine());
			
			int cmd = Integer.parseInt(tokens.nextToken());
			
			if(cmd == 2)
				sb.append(minIdx(1, 0, N - 1, 0, N - 1) + 1).append("\n");
			else if(cmd == 1) {
				int idx = Integer.parseInt(tokens.nextToken()) - 1;
				int value = Integer.parseInt(tokens.nextToken());
				
				nums[idx] = value;
//				System.out.print("\n\nupdated nums : ");
//				for (int j = 0; j < N; j++)
//					System.out.print(nums[j] + " ");
//				System.out.println();
//				
				update(1, idx, 0, N - 1);
//				
//				System.out.print("\n\nupdated segtree : ");
//				for (int j = 0; j < treeSize; j++)
//					System.out.print(segtree[j] + " ");
//				System.out.println();
			}
		}
		
		System.out.println(sb);
	}

	static int init(int node, int start, int end) {
		if (start == end)
			return segtree[node] = start;

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;

		int lIdx = init(lc, start, mid);
		int rIdx = init(rc, mid + 1, end);

		if (nums[lIdx] == nums[rIdx])
			return segtree[node] = Math.min(lIdx, rIdx);
		else
			return segtree[node] = ((nums[lIdx] > nums[rIdx]) ? rIdx : lIdx);
	}

	static int minIdx(int node, int left, int right, int start, int end) {
		if (left > end || right < start)
			return -1;

		if (left <= start && end <= right)
			return segtree[node];

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;

		int idx1 = minIdx(lc, left, right, start, mid);
		int idx2 = minIdx(rc, left, right, mid + 1, end);

		if (idx1 == -1)
			return idx2;
		if (idx2 == -1)
			return idx1;

		if (nums[idx1] == nums[idx2])
			return Math.min(idx1, idx2);
		else
			return ((nums[idx1] > nums[idx2]) ? idx2 : idx1);
	}

	static int update(int node, int idx, int start, int end) {
//		System.out.println("update(" + node + ", " + idx + ", " + start + ", " + end + ")");
		if (idx < start || idx > end)
			return segtree[node];

		if (start == end) {
//			System.out.println("update 된 nums 값 : " + nums[idx]);
//			System.out.println("node = " + node);
//			segtree[node] = idx;
//			System.out.println("segtree[node] = " + segtree[node]);
			return segtree[node] = idx;
		}

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		int idx1 = update(lc, idx, start, mid);
		int idx2 = update(rc, idx, mid + 1, end);
//		System.out.println("idx1 = " + idx1);
//		System.out.println("idx2 = " + idx2);
		
		if (nums[idx1] == nums[idx2])
			return segtree[node] = Math.min(idx1, idx2);
		else
			return segtree[node] = (((nums[idx1] > nums[idx2]) ? idx2 : idx1));
	}
}
