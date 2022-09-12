import java.util.Arrays;

/**
 * 
 * @author koreii
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/92341
 * @difficuly LEVEL3
 * @performance 101MB   149.85ms
 * @category # 이진탐색, parametric search
 * @memo # 한 명이 징검다리를 건널 수 있는지 파악하는 데 O(stones.length * k)가 걸림.
 * @memo # 최대 2억명이 건널 수 있음(stones 배열의 최댓값이 2억)
 * @memo # parametric search로 건널 수 있는 인원 수를 찾으려해도 mid명을 확인하는데 O(mid * stones.length * k)가 걸림
 * @memo # mid명이 모두 건널 수 있는지 직접 확인하지 말고 mid - 1명이 이미 건넌 상태를 가정 후 한 명이 추가로 건널 수 있는지 확인해야 함
 * @etc # 정확성 & 효율성 문제, 효율성은 idea가 생소한 편이었고 정확성도 생각보다 쉽지 않았음
 * 
 */

class Solution {
	final int MAX_LEN = 200_000;		//	stones 배열의 최대길이 20만
	final int MAX_NUM = 200_000_000;	//	stones 배열의 최댓값 2억
	
    public int solution(int[] stones, int k) {
        int answer = binarySearch(stones, k);
        return answer;
    }
    
    //	현재 stones 징검다리를 1명이 건널 수 있을지 check
    boolean canCross(int[] stones, int k) {
//    	System.out.println("stones : " + Arrays.toString(stones));
    	int idx = -1;	//	현재 친구가 시작하는 위치
    	
    	while(idx < stones.length) {	//	현재 친구가 다 건널 때까지
    		boolean cross = false;
    		
    		//	현재 친구 위치로부터 다음 k개 중에 건널 경우가 존재하나 check 
    		for(int i = 1; i <= k; i++) {
    			if(idx + i >= stones.length) {	//	현재 위치에서 i칸 다음이면 다 건널 경우
    				idx = idx + i;
    				cross = true;
    				break;
    			}
    			else if(stones[idx + i] >= 1) {	//	현재 위치에서 i칸 다음 징검다리에 발 딛을 수 있을 경우
    				idx = idx + i;
    				stones[idx]--;
    				cross = true;
    				break;
    			}
    			else {	//	현재 위치에서 i칸 다음으로 갈 수 없을 경우
    				continue;	//	i칸 다음 징검다리로는 이동 불가하므로 i+1칸 다음 징검다리 확인해야함
    			}
    		}
    		
    		if(!cross)	//	현재 위치에서 다음 k개 칸 중 갈 수 있는 칸이 없을 경우
    			return false;
    	}
    	
    	return true;
    }
    
    int binarySearch(int[] stones, int k) {
    	int start = 1;
    	int end = MAX_NUM;
    	int mid = (start + end) / 2;	//	이번에 징검다리를 건널 사람 수가 mid 명이라고 가정
    	int ret = 0;
    	
    	while(start <= end) {
//    		System.out.println("mid = " + mid);
    		int[] tmp = Arrays.copyOf(stones, stones.length);
    		
    		//	일단 mid - 1명이 건넜다고 가정
    		for(int i = 0; i < tmp.length; i++) {
    			if(tmp[i] >= mid - 1)
    				tmp[i] -= (mid - 1);
    			else
    				tmp[i] = 0;
    		}
    		
    		//	마지막 1명이 더 건널 수 있나 확인
    		if(canCross(tmp, k)) {	//	더 건널 수 있으면 총 mid명이 더 건널 수 있음
    			ret = mid;
    			start = mid + 1;	//	mid명보다 더 건널 수 있나 확인
    		}
    		else {	//	더 건널 수 없으면 mid명보다 더 적은 친구들이 건널 수 있는지 확인해야 함
    			end = mid - 1;
    		}
    		
    		mid = (start + end) / 2;
    	}
    	
    	return ret;
    }
}

public class 징검다리건너기_정확성N효율성 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		
		System.out.println(sol.solution(stones, k));
	}
}
