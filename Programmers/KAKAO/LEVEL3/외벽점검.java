class Solution {
	int num;			//	weak point 개수
	int nFriends;		//	친구 수
    int[] weakPoint;	//	원형 벽 고려 취약지점
    int answer;			//	최소 친구의 수
	
	public int solution(int n, int[] weak, int[] dist) {
        num = weak.length;
        nFriends = dist.length;
        answer = nFriends + 1;	//	필요 친구 수를 전체 친구 수 + 1로 설정(계속 이상태면 점검 불가임)
        
        weakPoint = new int[num * 2];
        for(int i = 0; i < num; i++)
        	weakPoint[i] = weak[i];
        for(int i = num; i < num * 2; i++)
        	weakPoint[i] = weak[i - num] + n;
        
        permutation(0, new boolean[nFriends], dist, new int[nFriends]);
        
        if(answer == nFriends + 1)	//	친구들만으로 점검할 수 없을 경우
        	answer = -1;
        
        return answer;
    }
	
	void permutation(int nth, boolean[] used, int[] dist, int[] result) {
		if(nth == nFriends) {		//	친구 순서 정하는 경우
			for(int start = 0; start < num; start++) {
				//	start번째 위치의 취약지점부터 탐사함
				int cnt = 0;		//	start번째 위치의 취약지점부터 탐색할 때 필요한 친구 수
				int checked = 0;	//	start번째 위치위 취약지점부터 탐색할 때 점검할 수 있는 취약지점 개수
				int idx = start;	//	i번째 친구가 탐색 시작할 index
				
				for(int i = 0; i < nFriends; i++) {
					//	i번째 친구 탐색 시작
					cnt++;			//	idx부터 친구 i가 탐색 시작
					checked++;		//	idx 취약 지점 점검
					
					//	친구 i가 idx 이후로도 얼마나 탐색 가능한지 확인
					int j = 1;
					for(; ; j++) {
						if(checked == num)
							break;
						
//						System.out.println("다음 점검할 취약지점 위치 = " + weakPoint[idx + j]);
						if(weakPoint[idx + j] <= weakPoint[idx] + result[i]) {
//							System.out.println("지점 " + weakPoint[idx + j] + " 점검 ok");
							checked++;
							continue;
						}
						else {
//							System.out.println("지점 " + weakPoint[idx + j] + " 점검 못함. 다음 친구");
							idx = idx + j;
							break;
						}
					}
//					System.out.println("총 점검한 개수 = " + checked);
					
					if(checked == num) {
//						System.out.println("총 점검에 필요한 친구 수 = " + cnt);
						if(answer > cnt)
							answer = cnt;
						break;
					}
				}
			}
			
			return;
		}
		
		for(int i = 0; i < nFriends; i++) {
			if(!used[i]) {
				used[i] = true;
				result[nth] = dist[i];
				permutation(nth + 1, used, dist, result);
				used[i] = false;
			}
		}
	}
}

public class 외벽점검 {
	public static void main(String[] args) {
		int n = 12;
		int[] weak = {1, 5, 6, 10};
		int[] dist = {1, 2, 3, 4};
		Solution sol = new Solution();
		
		System.out.println("=================test case 1=================");
		System.out.println(sol.solution(n, weak, dist));
		
		n = 12;
		weak = new int[] {1, 3, 4, 9, 10};
		dist = new int[] {3, 5, 7};
		sol = new Solution();
		
		System.out.println("=================test case 2=================");
		System.out.println(sol.solution(n, weak, dist));
	}
}
