import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;
	static int height, treeSize;
	static int[] segtree;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		nums = new int[N];
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(bf.readLine());
		
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		treeSize = (int)Math.pow(2, height + 1);
		segtree = new int[treeSize];
		
		init(1, 0, N - 1);
		
//		System.out.println("\n\n");
//		for(int num : segtree)
//			System.out.print(num + " ");
//		System.out.println();
		
		for(int i = 0; i < M; i++) {
			int a, b;
			tokens = new StringTokenizer(bf.readLine());
			
			a = Integer.parseInt(tokens.nextToken()) - 1;
			b = Integer.parseInt(tokens.nextToken()) - 1;
			
			sb.append(get(1, a, b, 0, N - 1)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int init(int node, int start, int end) {
		if(start == end)
			return segtree[node] = nums[start];
	
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		return segtree[node] = Math.min(init(lc, start, mid), init(rc, mid + 1, end));
	}
	
	static int get(int node, int left, int right, int start, int end) {
		if(left > end || right < start)
			return Integer.MAX_VALUE;
		else if(left <= start && end <= right)
			return segtree[node];
		
		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;
		
		return Integer.min(get(lc, left, right, start, mid), get(rc, left, right, mid + 1, end));
	}
}