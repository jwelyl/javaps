import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2042
 * @difficuly G1
 * @performance 119504KB   536ms
 * @category 세그먼트 트리(Segment Tree)
 * @memo 기본적인 세그먼트 트리 문제로 직접 풀었다기 보단 세그먼트 트리를 공부하는 의미가 큰 문제였다.
 * @memo 세그먼트 트리를 안보고 구현하려면 꽤 많은 연습이 필요할 것 같다. 그래도 처음 봤을 때 보단 훨씬 이해도 잘되고 익숙해졌다.
 * @etc https://www.acmicpc.net/blog/view/9 를 참고하자.
 *  
 */
public class BOJ_2042 {
	static int N, M, K;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static long[] nums, tree;
	static int height, treeSize;	//	segtree height, size
	
	//	nums: 수 배열
	//	tree: 세그먼트 트리
	//	node: 세그먼트 트리 노드번호(1~)
	//	start, end : 노드가 담당하는 합의 범위(start ~ end)
	static long init(long[] nums, long[] tree, int node, int start, int end) {
		if(start == end)
			return tree[node] = nums[start];
		else
			return (tree[node] = init(nums, tree, node * 2, start, (start + end) / 2)
								+ init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end));
	}
	
	//	node가 담당하는 구간이 start ~ end이고, 구해야하는 합의 범위가 left ~ right
	static long sum(long[] tree, int node, int start, int end, int left, int right) {
		if(left > end || right < start) {	//	구해야 할 범위가 node가 담당하는 범위 밖일 경우
			return 0;
		}
		if(left <= start && end <= right) {	//	합을 구하려는 범위에 해당 노드의 범위가 포함될 경우
			return tree[node];
		}
		
		//	위에 해당하지 않을 경우 왼쪽 자식 노드와 오른쪽 노드에서 합을 찾아서 더함
		return (sum(tree, node * 2, start, (start + end) /2, left, right) +
				sum(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}
	
	//	idx의 수를 diff만큼 변화시킴
	static void update(long[] tree, int node, int start, int end, int idx, long diff) {
		if(idx < start || idx > end) return;	//	idx가 범위에 포함되지 않을 경우

		tree[node] = tree[node] + diff;	//	범위 합 증가
		if(start != end) {	//	node가 단말 노드가 아니면
			update(tree, node * 2, start, (start + end) / 2, idx, diff);	//	왼쪽 자식 노드 update
			update(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);	//	오른쪽 자식 노드 update
		}
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		M = M + K;
	
		height = (int)Math.ceil(Math.log10(N) / Math.log10(2));
		treeSize = (1 << (height + 1));
	
		nums = new long[N];
		tree = new long[treeSize];
		
		for(int i = 0; i < N; i++)
			nums[i] = Long.parseLong(bf.readLine());
		
		init(nums, tree, 1, 0, N - 1);

		for(int i = 0; i < M; i++) {
			int a;
			
			tokens = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(tokens.nextToken());
			
			if(a == 1) {	//	update
				int b = Integer.parseInt(tokens.nextToken()) - 1;
				long c = Long.parseLong(tokens.nextToken());
				long diff = c - nums[b];
				
				nums[b] = c;
				update(tree, 1, 0, N - 1, b, diff);
			}
			else if(a == 2) {	//	partial sum
				int b = Integer.parseInt(tokens.nextToken()) - 1;
				int c = Integer.parseInt(tokens.nextToken()) - 1;
				
				sb.append(sum(tree, 1, 0, N - 1, b, c) + "\n");
			}
		}
		
		System.out.println(sb);
	}
}
