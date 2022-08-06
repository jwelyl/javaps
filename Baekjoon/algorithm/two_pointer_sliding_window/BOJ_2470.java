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
 * @performance 31944KB   300ms
 * @category # 투 포인터(Two Pointers)
 * @memo 2467처럼 정렬만 한다면 투 포인터 풀이도 생각해볼 수 있음.
 * @memo 2467을 풀어보지 않고 2470을 만났다면 정렬 후 투 포인터를 생각할 수 있을까?
 * @memo 적어도 모든 두 쌍의 용액을 검사하는 것은 O(N^2)으로 N이 최대 10만이므로 불가함을 캐치해야 함.
 * @memo 2467의 경우 투 포인터일까 의심은 했지만 구체적으로 어떻게 할 진 몰랐음.
 * @memo 양 끝의 용액을 잡고 합이 0에 가까워지도록 양 끝 용액을 움직이면 됨.
 * @memo 모든 용액에 대해 Two Pointers를 적용하면 O(N)의 시간복잡도를 가지게 됨.
 * @memo Two Pointers 문제를 많이, 여러번 풀어봐야 할 듯.
 * @etc  구현 자체는 Binary Search보다도 쉬움.
 *  
 */

public class BOJ_2470 {
	static int N;	//	용액 개수
	static int[] solutions;	//	용액 배열
	static int minSumAbs = Integer.MAX_VALUE;	//	두 용액의 합 중 가장 0의 가까운 값
	static int left, right;	//	합이 0에 가장 가까운 용액의 위치
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int start, end;	//	투 포인터 알고리즘에 쓰일 포인터
 	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());
		solutions = new int[N];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++)
			solutions[i] = Integer.parseInt(tokens.nextToken());
			
		Arrays.sort(solutions);	//	정렬함
		
		start = 0; end = N - 1;	//	양 끝에서부터 검색
		
		while(start < end) {	//	start < end(서로 다른 두 용액을 섞어야 하므로 등호 빠짐)
			int sum = solutions[start] + solutions[end];	//	두 용액의 합
			int sumAbs = Math.abs(sum);	//	합의 절댓값
			
			if(sumAbs == 0) {	//	만약 두 용액의 합이 정확히 0일 경우 굳이 더 볼 필요 없음
				left = start;
				right = end;
				break;
			}
			
			if(minSumAbs > sumAbs) {	//	새로운 두 용액의 합이 이전 두 용액의 합보다 작을 경우
				minSumAbs = sumAbs;
				left = start;
				right = end;
			}
			
			if(sum > 0)	//	용액의 합이 0보다 클 경우 큰 용액을 작게 해야 함
				end--;
			else		//	용액의 합이 0보다 작을 경우 작은 용액을 크게 해야 함
				start++;
		}
		
		System.out.println(solutions[left] + " " + solutions[right]);
	}
}
