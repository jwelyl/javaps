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
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_1275 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, Q;
	static int[] nums;
	
	static int height, treeSize;
	static long[] segtree;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		
		nums = new int[N];
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		treeSize = (int)Math.pow(2, height + 1);
		segtree = new long[treeSize];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());
		
		init(1, 0, N - 1);
		
		for(int i = 0; i < Q; i++) {
			int x, y, a, b;
			tokens = new StringTokenizer(bf.readLine());
		
			x = Integer.parseInt(tokens.nextToken()) - 1;
			y = Integer.parseInt(tokens.nextToken()) - 1;
			a = Integer.parseInt(tokens.nextToken()) - 1;
			b = Integer.parseInt(tokens.nextToken());
			
			if(x > y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			
			sb.append(sum(1, x, y, 0, N - 1) + "\n");
			
			long diff = (long)b - nums[a];
			nums[a] = b;
			update(1, a, diff, 0, N - 1);
		}
		
		System.out.println(sb);
	}
	
	static long init(int node, int start, int end) {
		if(start == end)	//	단말 노드일 경우
			return segtree[node] = nums[start];
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		return segtree[node] = init(lc, start, mid) + init(rc, mid + 1, end);
	}
	
	static long sum(int node, int left, int right, int start, int end) {
		if(left > end || right < start)
			return 0;
		
		if(left <= start && end <= right)
			return segtree[node];
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		return sum(lc, left, right, start, mid) + sum(rc, left, right, mid + 1, end);
	}
	
	static void update(int node, int idx, long diff, int start, int end) {
		if(idx < start || idx > end)
			return;
		
		segtree[node] += diff;
		
		if(start != end) {
			int lc = node * 2;
			int rc = node * 2 + 1;
			int mid = (start + end) / 2;
			
			update(lc, idx, diff, start, mid);
			update(rc, idx, diff, mid + 1, end);
		}
	}
}
