import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author koreii
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/1835
 * @difficuly LEVEL2
 * @performance 396MB   2602.19ms
 * @category # 완전탐색
 * @memo # 8! = 40,320이고 n이 최대 100이므로 대략 4,032,000의 반복이 이루어진다. 완전탐색이 가능하다. 
 * @etc # swith문에서 break를 걸어줘야 하는 경우를 잘 생각하자. 빠뜨려서 꼬였다.
 * 
 */

class Solution {
	char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
	int num = friends.length;
	int answer = 0;

    public int solution(int n, String[] data) {
        perm(0, new boolean[num], new char[num], n, data);
        
        return answer;
    }
    
    void perm(int nth, boolean[] used, char[] res, int n, String[] data) {
    	if(nth == num) {	//	한 줄 세우기 완성
    		Map<Character, Integer> position = new HashMap<>(); //	한 줄로 선 각 친구들의 위치 저장 map
    		boolean ok = true;	//	한 줄로 선 줄이 data 조건 만족할 경우
    		
    		for(int i = 0; i < num; i++) 
    			position.put(res[i], i);
    		
    		for(int i = 0; i < n; i++) {
    			char f1 = data[i].charAt(0);	//	조건문의 첫 번째 친구
    			char f2 = data[i].charAt(2);	//	조건문의 두 번째 친구
    			char op = data[i].charAt(3);	//	조건문
    			int dist = data[i].charAt(4) - '0';	//	조건문에서 주어진 두 친구 사이 거리
    			
    			int idx1 = position.get(f1);	//	첫 번째 친구 위치
    			int idx2 = position.get(f2);	//	두 번째 친구 위치
    			int dist12 = Math.abs(idx1 - idx2) - 1;	//	두 친구 사이 실제 거리
    			
    			switch(op) {
    			case '>':
    				if(dist12 <= dist) {	//	조건 불만족
    					ok = false;
    					break;
    				}
    				else break;	//	조건 만족시 break해야 함
    			case '=':
    				if(dist12 != dist) {
    					ok = false;
    					break;
    				}
    				else break;
    			case '<':
    				if(dist12 >= dist) {
    					ok = false;
    					break;
    				}
    				else break;
    			}
    			
    			if(!ok)
    				break;
    		}
    		
    		if(ok)
    			answer++;
    		
    		return;
    	}
    	
    	//	8명 친구 일렬로 세우는 모든 경우 구하기
    	for(int i = 0; i < num; i++) {
    		if(!used[i]) {
    			used[i] = true;
    			res[nth] = friends[i];
    			perm(nth + 1, used, res, n, data);
    			used[i] = false;
    		}
    	}
    }
}

public class 단체사진찍기 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] data1 = {"N~F=0", "R~T>2"};
		System.out.println(sol.solution(data1.length, data1));
		
		sol = new Solution(); 
		String[] data2 = {"M~C<2", "C~M>1"};
		System.out.println(sol.solution(data2.length, data2));
	}
}