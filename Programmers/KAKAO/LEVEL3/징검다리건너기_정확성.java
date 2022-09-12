import java.util.Arrays;

class Solution {
	final int MAX_LEN = 200_000;		//	stones 배열의 최대길이 20만
	final int MAX_NUM = 200_000_000;	//	stones 배열의 최댓값 2억
	
    public int solution(int[] stones, int k) {
        int answer = binarySearch(stones, k);
        return answer;
    }
    
    //	stones 징검다리를 최대 k개의 다리를 뛰어넘어 num명이 통과할 수 있는지 확인
    boolean canCross(int[] stones, int k, int num) {
    	int[] tmp = Arrays.copyOf(stones, stones.length);	//	stones 배열을 복사
    	
    	for(int i = 1; i <= num; i++) {
    		boolean cross = false;	//	i번째 친구가 건널 수 있는지 check
    		int idx = -1;			//	i번째 친구 시작 위치
    		
    		while(idx < tmp.length) {	//	i번째 친구가 다 건널 때 까지
    			boolean kCross = false;	//	idx로부터 k개의 징검다리를 확인하여 도달할 수 있는 징검다리 있으면 true
    			//	idx로부터 갈 수 있는 최대 k칸 확인
   
    			for(int j = 1; j <= k; j++) {
    				if(idx + j >= tmp.length) {	//	현재 위치에서 j칸 떨어진 위치는 징검다리 다 건넌 다음임
    					idx = idx + j;
    					kCross = true;
    					break;
    				}
    				else if(tmp[idx + j] >= 1) {	//	현재 위치에서 j칸 떨어진 위치로 이동 가능할 경우
    					idx = idx + j;
    					kCross = true;
    					tmp[idx]--;
    					break;
    				}
    				else {	//	현재 위치에서 j칸 떨어진 위치로 이동 불가능할 경우
    					continue;	//	j 1 증가
    				}
    			}
    			
    			if(!kCross) {	//	현재 위치 idx로부터 k개의 징검다리 중 디딜 수 있는 게 없을 경우
    				break;		
    			}
    			
    			if(idx >= tmp.length)	//	다 건넌 경우
    				cross = true;
    			else					//	아직 다 건너진 못한 경우
    				continue;
    		}
    		
    		if(!cross)
    			return false;
    	}
    	
    	return true;
    }
    
    int binarySearch(int[] stones, int k) {
    	int start = 1;		//	최소 1명은 건널 수 있음
    	int end = MAX_NUM;	//	아무리 많이 건너도 2억명이 최대임	
    	int mid = (start + end) / 2;	//	mid명이 건넌다고 가정
    	int ret = 0;
    	
    	while(start <= end) {
//    		System.out.println("start = " + start + ", end = " + end + ", mid = " + mid);
    		boolean ok = canCross(stones, k, mid);
//    		System.out.println("ok = " + ok);
    		
    		if(ok) {	//	mid명이 건널 수 있을 경우 더 건널 수 있는지 check
    			ret = mid;
    			start = mid + 1;
    		}
    		else 	//	mid명이 건널 수 없을 경우 더 적은 인원이 건널 수 있는지 check
    			end = mid - 1;
  
    		mid = (start + end) / 2;
    	}

    	return ret;
    }
}

public class 징검다리건너기_정확성 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		
		System.out.println(sol.solution(stones, k));
	}
}
