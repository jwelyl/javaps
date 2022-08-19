import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int T;
    static int N;
    static int[][] synergy;
    static int minDiff;
     
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
     
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        T = Integer.parseInt(bf.readLine());
         
        for(int t = 1; t <= T; t++) {
            minDiff = Integer.MAX_VALUE;
            N = Integer.parseInt(bf.readLine());
             
            synergy = new int[N][N];
            for(int i = 0; i < N; i++) {
                tokens = new StringTokenizer(bf.readLine());
                for(int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }
             
            combination(0, 0, new boolean[N]);
            sb.append("#").append(t).append(" ").append(minDiff).append("\n");
        }
         
        System.out.println(sb);
    }
     
    static void combination(int nth, int start, boolean[] selected) {
        if(nth == N / 2) {
            int sum1 = 0;
            int sum2 = 0;
             
            int[] food1 = new int[N / 2];
            int idx1 = 0;
            int[] food2 = new int[N / 2];
            int idx2 = 0;
             
            for(int i = 0; i < N; i++) {
                if(selected[i]) {
                    food1[idx1++] = i;
                }
                else {
                    food2[idx2++] = i;
                }
            }
             
            for(int i = 0; i < N / 2; i++) {
                for(int j = i + 1; j < N / 2; j++) {
                    sum1 += (synergy[food1[i]][food1[j]] + synergy[food1[j]][food1[i]]);
                }
            }
             
            for(int i = 0; i < N / 2; i++) {
                for(int j = i + 1; j < N / 2; j++) {
                    sum2 += (synergy[food2[i]][food2[j]] + synergy[food2[j]][food2[i]]);
                }
            }
             
            if(minDiff > Math.abs(sum1 - sum2)) {
                minDiff = Math.abs(sum1 - sum2);
            }
        }
         
        for(int i = start; i < N; i++) {
            selected[i] = true;
            combination(nth + 1, i + 1, selected);
            selected[i] = false;
        }
    }
 
}