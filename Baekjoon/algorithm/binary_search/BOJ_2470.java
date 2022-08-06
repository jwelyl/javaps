import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2470
 * @difficuly G5
 * @performance 31360KB   312ms
 * @category # 이분탐색(Binary Search)
 * @memo 유사 문제인 2467은 정렬된 데이터가 주어져서 바로 binary search를 생각할 수 있었지만, 2470은 정렬되지 않음.
 * @memo 2467을 풀어보지 않고 2470을 만났다면 정렬 후 binary search를 생각할 수 있을까?
 * @memo 적어도 모든 두 쌍의 용액을 검사하는 것은 O(N^2)으로 N이 최대 10만이므로 불가함을 캐치해야 함.
 * @memo 2467의 경우 Binary Search임을 알아도 어떻게 적용할지 몰랐었음.
 * @memo 임의의 한 용액을 잡고 그 용액과의 합이 0에 가장 가까운 용액을 Binary Search로 찾는다는 idea가 어려웠음.
 * @memo 모든 용액에 대해 binary search를 적용하면 O(NlogN)의 시간복잡도를 가지게 됨.
 * @memo Binary Search 문제를 많이, 여러번 풀어봐야 할 듯.
 * @etc Binary Search 문제를 오랜만에 풀어서 mid + 1, mid - 1로 갱신해야 하는걸 단순 ++, --로 시간 초과를 많이 냄.
 * @etc 딱 정확한 값을 찾는 것이 아닌 최대한 가까운 값을 찾는게 목표라 조건을 추가했어야 했음.
 *  
 */

public class BOJ_2470 {
	static int N;	//	용액 개수
	static int[] solutions;	//	용액 배열
	static int minSumAbs = Integer.MAX_VALUE;	//	두 용액의 합 중 가장 0의 가까운 값
	static int left, right;	//	합이 0에 가장 가까운 용액의 위치
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());
		solutions = new int[N];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			solutions[i] = Integer.parseInt(tokens.nextToken());
			
		Arrays.sort(solutions);	//	정렬함
		
		for(int i = 0; i < N - 1; i++) {
			int sumAbs;		//	두 용액의 합의 절댓값
			int start = i + 1;	//	i+1번째 용액부터
			int end = N - 1;	//	마지막 용액까지
			int target = -solutions[i];	//	i번째 용액과 합이 0인 용액 찾기
			
			int idx = binarySearchIterative(start, end, target);	//	target과 가장 가까운 용액의 위치	
			sumAbs = (int)Math.abs(solutions[i] + solutions[idx]);
			
			if(sumAbs < minSumAbs) {	//	알려진 합이 0에 가까운 두 용액보다 더 가까운 두 용액 발견
				minSumAbs = sumAbs;
				left = i;
				right = idx;
			}
		}
		
		System.out.println(solutions[left] + " " + solutions[right]);
	}
	
	static int binarySearchIterative(int start, int end, int target) {
		int mid = (start + end) / 2;
		int ret = -1;
		int diff = Integer.MAX_VALUE;
		
		while(start <= end) {
			if(solutions[mid] == target) {	//	정확히 합이 0이 되는 용액을 찾은 경우
				ret = mid;	//	해당 용액 index 반환
				break;		//	더 볼 것도 없음
			}
			
			if(diff > Math.abs(target - solutions[mid])) {	//	새로 발견한 용액과의 합이 더 0에 가까울 경우
				diff = Math.abs(target - solutions[mid]);
				ret = mid;
			}
			
			if(target > solutions[mid])	//	찾으려는 값보다 클 경우
				start = mid + 1;		//	더 큰 용액 범위 검색
			else						//	더 작을 경우
				end = mid - 1;			//	더 작은 용액 범위 검색
			
			mid = (start + end) / 2;	//	다시 반으로 줄임
		}
		
		return ret;
	}
	
	//	13
	//	148 -132 127 78 37 -187 -15 23 96 192 -95 -37 -102
	//	-37 37
	
	//	4
	//	-100 -2 -1 10
	//	-2 -1
}
