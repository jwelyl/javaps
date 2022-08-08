import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2473
 * @difficuly G3
 * @performance 14060KB   172ms
 * @category # 투 포인터(Two Pointers)
 * @memo 2470처럼 투 포인터를 이용해 풀 수 있음.
 * @memo 투 포인터를 이용해 풀 경우 왼쪽부터 pivot을 정하고 pivot 오른쪽에 투 포인터를 이용하면 O(N^2)의 시간복잡도를 가짐
 * @memo 시간 제한이 1초이고 시간복잡도만 보면 매우 안좋아보이지만 N이 최대 5000이라 충분히 가능함. 
 * @memo 시간 제한 1초에 N이 최대 100000이었던 2470과 다른 점(대신 세 용액을 고려해야 해서 필연적으로 시간복잡도가 커짐)
 * @memo 세 용액의 합을 구하다보니 합이 int 범위를 넘어갈 수 있었음. 이를 고려해야 정답 처리받을 수 있음. 
 * @etc  idea는 금방 떠올렸으나 코드가 쓸 데 없이 난잡해졌었는데 쉽게 생각하면 됐었음. 결국 오답/정답을 가른 건 자료형이었음.
 * @etc 유사 문제 : https://www.acmicpc.net/problem/3151
 *   
 */

public class BOJ_2473 {
	static int N;
	static long[] solutions;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int left, mid, right;
	static long minSum = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());
		solutions = new long[N];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			solutions[i] = Long.parseLong(tokens.nextToken());
		Arrays.sort(solutions);
		
		boolean found = false;	//	세 용액의 합이 정확히 0일 경우 true
		for(int pivot = 0; pivot < N - 2; pivot++) {	//	pivot 결정 O(N)
			long target = -solutions[pivot];
			int start = pivot + 1;
			int end = N - 1;
			
			while(start < end) {	//	pivot 오른쪽에 two pointer 적용 O(N)
				long twoSum = solutions[start] + solutions[end];
				
				if(twoSum == target) {	//	정확히 세 용액의 합이 0일 경우
					minSum = 0;
					left = pivot;
					mid = start;
					right = end;
					found = true;	//	종료 flag
					break;
				}
				
				//	세 용액의 합이 최소가 되는 지점 찾을 경우
				if(minSum > (long)Math.abs(-target + twoSum)) {
					minSum = (long)Math.abs(-target + twoSum);	//	최소 합 갱신
					left = pivot;
					mid = start;
					right = end;
				}
				
				if(twoSum > target)	//	오른쪽 두 용액의 합이 구하려는 값보다 클 경우
					end--;	//	end 감소시켜서 합 감소시킴
				else			//	구하려는 값보다 작을 경우
					start++;	//	start를 증가시켜서 합 증가시킴
			}
			
			if(found)
				break;
		}
		
		System.out.println(solutions[left] + " " + solutions[mid] + " " + solutions[right]);
	}
}
