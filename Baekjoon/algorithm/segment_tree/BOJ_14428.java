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

public class BOJ_14428 {

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
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		treeSize = (int)Math.pow(2, height + 1);
		segtree = new int[treeSize];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());
		
		init(1, 0, N - 1);
		
		M = Integer.parseInt(bf.readLine());
		for(int i = 0; i < M; i++) {
			int cmd, a, b;
			tokens = new StringTokenizer(bf.readLine());
		
			cmd = Integer.parseInt(tokens.nextToken());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			if(cmd == 1) {
				a -= 1;
				
				nums[a] = b;
				update(1, a, 0, N - 1);
			}
			else if(cmd == 2) {
				if(a > b) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				
				a -= 1;
				b -= 1;
				
				sb.append(minIdx(1, a, b, 0, N - 1) + 1).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int init(int node, int start, int end) {
		if(start == end)
			return segtree[node] = start;
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		int idx1 = init(lc, start, mid);
		int idx2 = init(rc, mid + 1, end);
		
		if(nums[idx1] == nums[idx2])
			return segtree[node] = (idx1 < idx2) ? idx1 : idx2;
		
		return segtree[node] = (nums[idx1] > nums[idx2]) ? idx2 : idx1;
	}
	
	static int minIdx(int node, int left, int right, int start, int end) {
		if(left > end || right < start)
			return -1;
		
		if(left <= start && end <= right)
			return segtree[node];
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		int idx1 = minIdx(lc, left, right, start, mid);
		int idx2 = minIdx(rc, left, right, mid + 1, end);
		
		if(idx1 == -1)
			return idx2;
		if(idx2 == -1)
			return idx1;
		
		if(nums[idx1] == nums[idx2])
			return (idx1 < idx2) ? idx1 : idx2;
		
		return (nums[idx1] > nums[idx2]) ? idx2 : idx1;
	}
	
	static int update(int node, int idx, int start, int end) {
		if(idx < start || end < idx)
			return segtree[node];
		
		if(start == end) {
			return segtree[node] = idx;
		}
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		int idx1 = update(lc, idx, start, mid);
		int idx2 = update(rc, idx, mid + 1, end);

		if(nums[idx1] == nums[idx2])
			return segtree[node] = (idx1 < idx2) ? idx1 : idx2;
		
		return segtree[node] = (nums[idx1] > nums[idx2]) ? idx2 : idx1;
	}
}
