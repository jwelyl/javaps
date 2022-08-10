import java.util.Scanner;

public class BOJ_10974 {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		permutation(0, new boolean[N], new int[N]);
		System.out.println(sb);
	}

	static void permutation(int nth, boolean[] used, int[] res) {
//		System.out.println("permutation(" + nth + ")");
		
		if(nth == N) {
			for(int i = 0; i < N; i++)
				sb.append(res[i] + " ");
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!used[i]) {
				used[i] = true;
				res[nth] = i + 1;
			
				permutation(nth + 1, used, res);
				
				used[i] = false;
			}
		}
	}
}
