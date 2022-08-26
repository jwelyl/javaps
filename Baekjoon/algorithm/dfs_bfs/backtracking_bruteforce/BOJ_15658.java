package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_15658 {
	static int N;
	static int[] nums;
	static int[] opr = new int[4];	//	opr[0] : + 개수, opr[1] : - 개수, opr[2] : * 개수, opr[3] : / 개수
 	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		nums = new int[N];
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < 4; i++)
			opr[i] = Integer.parseInt(tokens.nextToken());
		
		dfs(0, nums[0]);
		
		System.out.println(MAX + "\n" + MIN);
	}
	
	static void dfs(int nth, int result) {
		if(nth == N - 1) {
			if(result > MAX)
				MAX = result;
			if(result < MIN)
				MIN = result;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(opr[i] == 0)	//	해당 연산자를 다 썼을 경우
				continue;
			
			int nResult = result;
			opr[i]--;
			
			switch(i) {
			case 0:
				nResult += nums[nth + 1];
				break;
			case 1:
				nResult -= nums[nth + 1];
				break;
			case 2:
				nResult *= nums[nth + 1];
				break;
			case 3:
				nResult /= nums[nth + 1];
				break;
			}
			
			dfs(nth + 1, nResult);    
			opr[i]++;
		}
	}
}