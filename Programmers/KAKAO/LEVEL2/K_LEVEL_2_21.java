import java.util.*;

class Solution {
    final int INF = 987654321;
    int maxGap = -INF;  //  라이언이 이길 경우 라이언과 어피치의 최대 점수차
    int[] ryanWin = new int[11];  //  라이언이 이겼을 때 기록
    boolean found = false;

    void copyRyan(int[] ryan) { //  ryan이 이긴 경우를 찾았으므로 복제(deepCopy)
        for(int i = 0; i < 11; i++)
            ryanWin[i] = ryan[i];
    }

    int isRyanWinnner(int[] apeach, int[] ryan) {  //  라이언이 승리하면 점수차, 패배하면 -1 반환
        int aScore = 0; //  어피치가 얻을 점수
        int rScore = 0; //  라이언이 얻을 점수

        for(int i = 0; i <= 10; i++) {  //  10 - i 점 과녁
            if(apeach[i] == 0 && ryan[i] == 0)  //  둘 다 못 맞췄을 경우 둘 다 점수 없음
                continue;
            else if(ryan[i] == 0)               //  어피치만 맞췄을 경우 어피치가 점수 얻음
                aScore += (10 - i);
            else if(apeach[i] == 0)             //  라이언만 맞췄을 경우 라이언이 점수 얻음
                rScore += (10 - i);
            else {  //  둘 다 맞췄을 경우 맞춘 개수 비교
                if(apeach[i] >= ryan[i])   // 어피치가 같거나 더 많이 맞춘 경우
                    aScore += (10 - i);
                else
                    rScore += (10 - i);
            }
        }

        if(rScore > aScore) { //  라이언 점수가 더 높을 경우 라이언 승리
            found = true;
            return rScore - aScore;
        }
        else return -1;
    }

    void dfs(int n, int depth, int idx, int[] ryan, int[] apeach) {
        ryan[idx] += 1;

        if(depth < n) {
            for(int i = idx; i < 11; i++)
                dfs(n, depth + 1, i, ryan, apeach);
        }
        else {  //  라이언 배열 결정
            int gap = isRyanWinnner(apeach, ryan);
            if(gap != -1) {   //  라이언 승리를 만족할 경우
                if(gap > maxGap) {  //  이번 경우가 라이언과 어피치의 점수차가 더 클 경우
                    maxGap = gap;
                    // ryanWin = ryan;
                    copyRyan(ryan);
                }
                else if(gap == maxGap) {    //  점수차가 같을 경우
                    for(int i = 10; i >= 0; i--) {  //  가장 작은 점수를 더 많이 맞출 경우 
                        if(ryanWin[i] < ryan[i]) {
                            // ryanWin = ryan;
                            copyRyan(ryan);

                            break;
                        }
                        else if(ryanWin[i] == ryan[i])
                            continue;
                        else break;
                    }
                }
            }
        }

        ryan[idx] -= 1;
    }

    public int[] solution(int n, int[] info) {
        int[] noAns = {-1}; //  라이언이 이길 수  없을 경우 return
        for(int i = 0; i < 11; i++) {
            int[] ryan = new int[11];
            Arrays.fill(ryan, 0);

            dfs(n, 1, i, ryan, info);
        }

        if(found)
            return ryanWin;
        else
            return noAns;
    }
}