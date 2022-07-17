import java.util.*;

class Solution {
    int N, M;
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        
        int[][] diff = new int[N + 1][];
        for(int i = 0; i <= N; i++) {
            diff[i] = new int[M + 1];
            Arrays.fill(diff[i], 0);
        }
        
        for(int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1], c1 = skill[i][2];
            int r2 = skill[i][3], c2 = skill[i][4];
            int degree = skill[i][5];
             
            if(type == 1)   //  공격일 경우
                degree *= -1;
            
            diff[r1][c1] += degree;
            diff[r1][c2 + 1] -= degree;
            diff[r2 + 1][c1] -= degree;
            diff[r2 + 1][c2 + 1] += degree;
        }
        
        for(int i = 0; i <= N; i++) {
            for(int j = 1; j <= M; j++)
                diff[i][j] += diff[i][j - 1];
        }
        
        for(int i = 0; i <= M; i++) {
            for(int j = 1; j <= N; j++)
                diff[j][i] += diff[j - 1][i];
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                board[i][j] += diff[i][j];
                
                if(board[i][j] > 0)
                    answer += 1;
            }
        }
        
        return answer;
    }
}