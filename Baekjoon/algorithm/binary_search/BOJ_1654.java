import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/1654
 * @difficuly S2
 * @performance 15268KB   140ms
 * @category # Binary Search를 이용하여 적정 값을 찾는 Parametric Search
 * @memo Parametric Search임을 간파하는 것은 어렵지 않았다. 그러나,
 * @memo 50% 정도에서 틀리는데 틀리는 이유를 찾는 것이 어려웠음.
 * @memo 입력받은 높이들 중 최대 높이를 end로 하여 mid가 결정되는데 주어지는 범위가 int 범위 끝까지이므로 간당간당했음.
 * @memo int의 최댓값이 입력으로 주어질 경우 int 자료형을 사용할 경우 mid가 중간에 overflow가 발생할 수 있었음.
 * @memo 다른 문제들에서는 왜 이런 문제가 발생하지 않았나 생각해보면 다른 문제들은 큰 수여도 10억 등, int 범위 끝자락까지는 아니었던 것 같음.
 * @memo 또한 개수를 구할 때 mid로 나누는 과정이 있어서 mid가 0이 될 경우에 대한 예외처리도 필요했음. 이는 Runtime error 메시지로 쉽게 캐치 가능할듯.
 * @etc 오랜만에 푸는 Parametric Search 문제였는데 자료형 조건을 잘 읽었어야 했음. 아직 문제 조건, 필요한 자료형 결정에 미숙한 것 같음.
 *  
 */

public class BOJ_1654 {
	static int K, N, maxHeight = -1;
	static int[] heights;
	static int cnt = 0;
	static long ans = -1;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] str = bf.readLine().split(" ");
		K = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		
		heights = new int[K];
		for(int i = 0; i < K; i++) {
			heights[i] = Integer.parseInt(bf.readLine());
			if(maxHeight < heights[i])
				maxHeight = heights[i];
		}
		
		parametricSearch(0, maxHeight);
		System.out.println(ans);
	}
	
	static void parametricSearch(long start, long end) {
		long mid = (start + end) / 2;
		if(mid == 0)
			mid = 1;	//	나누는 최솟값 1로 보정
		
		while(start <= end) {
			for(int i = 0; i < K; i++)
				cnt += heights[i] / mid;
			
			if(cnt >= N) {			//	mid로 잘랐더니 개수가 충분할 경우
				ans = mid;			//	일단 mid를 정답의 후보로 저장
				start = mid + 1;	//	더 길게 자를 수 있나 확인
			}
			else					//	mid로 잘랐더니 개수가 부족할 경우
				end = mid - 1;		//	더 짧게 잘라야 함
		
			mid = (start + end) / 2;	//	다음에 자를 길이
			if(mid == 0)
				mid = 1;
			cnt = 0;
		}
	}

}
