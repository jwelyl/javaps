import java.util.Arrays;

class Solution {
    public int[] solution(int n, int[] info) {

        comb(n, 0, 0, new int[11], info);
        
        if(answer.length == 0)
            answer = new int[]{-1};
        return answer;
    }

    int[] answer = {};
    int maxDiff = -1;       //  라이언이 어피치를 이긴 최대 점수차, -1일 경우 이긴 적 없음

    void deepCopy(int[] result) {
        answer = new int[11];

        for(int i = 0; i < 11; i++)
            answer[i] = result[i];
    }

    //	result[i] : ryan이 10 - i점 과녁을 맞춘 개수
    void comb(int n, int nth, int startIdx, int[] result, int[] info) {
        if(nth == n) {
            int appeach = 0;    //  어피치 점수
            int ryan = 0;       //  라이언 점수
            int diff = 0;       //  라이언이 이길 경우 점수차

            //  10 - i점을 맞춘 개수 비교
            for(int i = 0; i <= 10; i++) {
                //  어피치가 10 - i점을 더 많이 맞추거나, 둘이 맞춘 개수가 같고 0개가 아닐 경우
                if(info[i] >= result[i]) {
                	if(info[i] != 0)
                		appeach += (10 - i);	//	어피치가 10 - i 점을 얻음
                }
                else
                	ryan += (10 - i);	//	라이언이 10 - i 점을 얻음
            	
//            	if(info[i] == 0 && result[i] == 0)
//            		continue;
//            	else if(result[i] == 0)
//            		appeach += (10 - i);
//            	else if(info[i] == 0)
//            		ryan += (10 - i);
//            	else {
//            		if(info[i] >= result[i])
//            			appeach += (10 - i);
//            		else
//            			ryan += (10 - i);
//            	}
            }

            if(appeach >= ryan) //  이번 경우는 어피치가 이김
                return;
            else {  //  라이언이 이긴 경우
                diff = ryan - appeach;

                if(diff > maxDiff) {  //  최대 점수차로 이긴 경우
//                    System.out.println("점수차 = " + diff);
                	maxDiff = diff;
                    // answer = result;
//                	System.out.println(Arrays.toString(result));
                    deepCopy(result);
                }
                else if(diff == maxDiff) {  //  점수차 같을 경우
                    for(int i = 10; i >= 0; i--) {
                        if(result[i] > answer[i]) {    //  이번 결과에서 더 낮은 점수를 많이 맞춘 경우
                            // answer = result;
//                        	System.out.println("더 적은 점수를 많이 맞춤");
//                        	System.out.println(Arrays.toString(result));
                        	deepCopy(result);
                            break;
                        }
                        else if(result[i] == answer[i])
                            continue;
                        //	이전 결과에서 낮은 점수 맞춘 개수가 더 클 경우 break
                        else break;
                    }
                }
            }

            return;
        }

        for(int i = startIdx; i < 11; i++) {
            result[i]++;
            comb(n, nth + 1, i, result, info);
            result[i]--;
        }
    } 
}

public class 양궁대회 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        System.out.println("정답");
        System.out.println(Arrays.toString(sol.solution(5, info)));
    }
}
