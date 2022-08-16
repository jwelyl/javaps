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

public class BOJ_18436 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static int[] nums;
	
	static int height, treeSize;
	static int[] segtree;	//	구간에서 짝수 개수 출력 segtree
	
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
//		System.out.print("\n\ninitial segtree : ");
//		for(int s : segtree)
//			System.out.print(s + " ");
//		System.out.println();
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
//				
//				System.out.print("\n\nupdated segtree : ");
//				for(int s : segtree)
//					System.out.print(s + " ");
//				System.out.println();
			}
			else if(cmd == 2) {
				a -= 1;
				b -= 1;
				sb.append(sum(1, a, b, 0, N - 1)).append("\n");
			}
			else if(cmd == 3) {
				int cnt = b - a + 1;
				a -= 1;
				b -= 1;
				sb.append(cnt - sum(1, a, b, 0, N - 1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int init(int node, int start, int end) {
		if(start == end) {
			return segtree[node] = (nums[start] % 2 == 0) ? 1: 0;
		}
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		return segtree[node] = init(lc, start, mid) + init(rc, mid + 1, end);
	}
	
	static int sum(int node, int left, int right, int start, int end) {
		if(left > end || right < start)
			return 0;
		
		if(left <= start && end <= right)
			return segtree[node];
	
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		return sum(lc, left, right, start, mid) + sum(rc, left, right, mid + 1, end);
	}
	
	static int update(int node, int idx, int start, int end) {
		if(idx < start || idx > end)
			return segtree[node];
		
		if(start == end) {
			return segtree[node] = (nums[idx] % 2 == 0) ? 1 : 0;
		}
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		return segtree[node] = update(lc, idx, start, mid) + update(rc, idx, mid + 1, end);
	}
}
