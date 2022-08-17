import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5676 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[] nums;

	static int height, treeSize;
	static char[] segtree;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String input;
		while ((input = bf.readLine()) != null) {
			tokens = new StringTokenizer(input);
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());

			nums = new int[N];

			height = (int) Math.ceil(Math.log(N) / Math.log(2));
			treeSize = (int) Math.pow(2, height + 1);
			segtree = new char[treeSize];

			tokens = new StringTokenizer(bf.readLine());
			for (int i = 0; i < N; i++)
				nums[i] = Integer.parseInt(tokens.nextToken());
			
			init(1, 0, N - 1);

			for (int i = 0; i < M; i++) {
				char cmd;
				int a, b;

				tokens = new StringTokenizer(bf.readLine());
				cmd = tokens.nextToken().charAt(0);
				a = Integer.parseInt(tokens.nextToken());
				b = Integer.parseInt(tokens.nextToken());

				if (cmd == 'C') {
					a -= 1;
					nums[a] = b;
					update(1, a, 0, N - 1);
				} else if (cmd == 'P') {
					a -= 1;
					b -= 1;
					
					if(a > b) {
						int tmp = a;
						a = b;
						b = tmp;
					}
					
					sb.append(query(1, a, b, 0, N - 1));
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	static char init(int node, int start, int end) {
		if (start == end) {
			if (nums[start] > 0)
				segtree[node] = '+';
			else if (nums[start] == 0)
				segtree[node] = '0';
			else
				segtree[node] = '-';

			return segtree[node];
		}

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;

		char lv = init(lc, start, mid);
		char rv = init(rc, mid + 1, end);

		if (lv == '0' || rv == '0')
			return segtree[node] = '0';
		if (lv != rv)
			return segtree[node] = '-';
		else
			return segtree[node] = '+';
	}

	static char query(int node, int left, int right, int start, int end) {
		if (left > end || right < start)
			return '+';

		if (left <= start && end <= right)
			return segtree[node];

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;

		char lv = query(lc, left, right, start, mid);
		char rv = query(rc, left, right, mid + 1, end);

		if (lv == '0' || rv == '0')
			return '0';
		if (lv != rv)
			return '-';
		else
			return '+';
	}

	static char update(int node, int idx, int start, int end) {
		if (idx < start || idx > end)
			return segtree[node];

		if (start == end) {
			if (nums[idx] == 0)
				segtree[node] = '0';
			else if (nums[idx] < 0)
				segtree[node] = '-';
			else
				segtree[node] = '+';

			return segtree[node];
		}

		int lc = node * 2;
		int rc = node * 2 + 1;
		int mid = (start + end) / 2;

		char lv = update(lc, idx, start, mid);
		char rv = update(rc, idx, mid + 1, end);

		if (lv == '0' || rv == '0')
			return segtree[node] = '0';
		if (lv != rv)
			return segtree[node] = '-';
		else
			return segtree[node] = '+';
	}
}
