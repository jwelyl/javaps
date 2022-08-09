import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/11505
 * @difficuly G1
 * @performance 98884KB   484ms
 * @category 세그먼트 트리(Segment Tree)
 * @memo 기본적인 세그먼트 트리 문제지만 합이 아니라 곱이여서 좀 더 생각할 것이 많았다.
 * @memo 일단 누적합에서 범위에서 벗어날 경우 0을 return한 것과 다르게 1을 return해야 했다.
 * @memo 모듈로 연산으로 두 수의 곱의 나머지를 계산할 수 있어야 했다.
 * @memo update의 경우 합과 다르게 느린 전파를 사용하기 어려워서 아래부터 값을 수정하며 위로 올라가 전체적인 트리 값을 변경해야 했음.
 * @memo update가 가장 어려웠던 것 같다.
 * @memo 최종 결과가 int형인 것이지 계산 중간 결과는 long이 충분히 나올 수 있으므로 자료형을 long으로 해야 했다.
 * @etc https://www.acmicpc.net/blog/view/9 를 참고하자.
 *  
 */

public class BOJ_11505 {
	final static int mod = 1_000_000_007;
	static int N, M, K;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int[] nums;
	
	static int height, treeSize;
	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		nums = new int[N];
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		treeSize = (1 << (height + 1));
		
		tree = new long[treeSize];
		
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(bf.readLine());
		
		init(1, 0, N - 1);
		
		M += K;
		
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b, c;
			
			if(a == 1) {	//	update
				b = Integer.parseInt(tokens.nextToken()) - 1;
				c = Integer.parseInt(tokens.nextToken());
				
				nums[b] = c;
				update(1, 0, N - 1, b, c);
			}
			else if(a == 2) {	//	partial mult
				b = Integer.parseInt(tokens.nextToken()) - 1;
				c = Integer.parseInt(tokens.nextToken()) - 1;
			
				sb.append(mult(1, 0, N - 1, b, c) + "\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static long init(int node, int start, int end) {
		if(start == end)
			return (tree[node] = nums[start]);
	
		int mid = (start + end) / 2;
		int lc = node * 2;
		int rc = node * 2 + 1;
		
		return (tree[node] = ((init(lc, start, mid) % mod) * (init(rc, mid + 1, end) % mod)) % mod);
	}
	
	static long mult(int node, int start, int end, int left, int right) {
		if(right < start || left > end)	//	범위 밖인 경우 1 반환
			return 1;
		else if(left <= start && end <= right)	//	노드 범위가 구하려는 범위에 포함
			return tree[node];
		
		int mid = (start + end) / 2;
		int lc = node * 2;
		int rc = node * 2 + 1;
		
		return (mult(lc, start, mid, left, right) % mod) * (mult(rc, mid + 1, end, left, right) % mod) % mod;
	}
	
	static long update(int node, int start, int end, int idx, int change) {
		if(idx < start || idx > end)	//	범위 밖일 경우 수정 x
			return tree[node];
		else if(start == end)
			return tree[node] = change;	//	단말 노드일 경우 값 변경
		
		int mid = (start + end) / 2;
		int lc = node * 2;
		int rc = node * 2 + 1;
		
		return tree[node] = ((update(lc, start, mid, idx, change) % mod) * (update(rc, mid + 1, end, idx, change) % mod)) % mod;
	}
}
 BOJ_11505 {
    
}
