import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SWEA_2001 {
    static int T, N, M, max;
    static int[][] flies;
    static int[][] accFlies;
     
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
     
    static void calcAccFlies() {
        accFlies[0][0] = flies[0][0];
         
        for(int i = 1; i < N; i++)
            accFlies[0][i] = accFlies[0][i - 1] + flies[0][i];
        for(int i = 1; i < N; i++)
            accFlies[i][0] = accFlies[i - 1][0] + flies[i][0];
         
        for(int i = 1; i < N; i++) {
            for(int j = 1; j < N; j++) {
                accFlies[i][j] = accFlies[i - 1][j] + accFlies[i][j - 1] - accFlies[i - 1][j - 1] + flies[i][j];
            }
        }
    }
     
    static int partialSum(int y1, int x1, int y2, int x2) {
        int ret = 0;
         
        if(y1 == 0 && x1 == 0)
            ret = accFlies[y2][x2];
        else if(y1 == 0)
            ret = accFlies[y2][x2] - accFlies[y2][x1 - 1];
        else if(x1 == 0)
            ret = accFlies[y2][x2] - accFlies[y1 - 1][x2];
        else
            ret = accFlies[y2][x2] - accFlies[y2][x1 - 1] - accFlies[y1 - 1][x2] + accFlies[y1 - 1][x1 - 1];
         
        return ret;
    }
     
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        T = Integer.parseInt(bf.readLine());
         
        for(int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(tokens.nextToken());
            M = Integer.parseInt(tokens.nextToken());
            max = -1;
             
            flies = new int[N][N];
            accFlies = new int[N][N];
             
            for(int i = 0; i < N; i++) {
                tokens = new StringTokenizer(bf.readLine());
                for(int j = 0; j < N; j++) {
                    flies[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }
             
            calcAccFlies();
             
            for(int y1 = 0; y1 <= N - M; y1++) {
                for(int x1 = 0; x1 <= N - M; x1++) {
                    int y2 = y1 + M - 1;
                    int x2 = x1 + M - 1;
                     
                    int tmp = partialSum(y1, x1, y2, x2);
//                  System.out.println("tmp = " + tmp);
                     
                    if(tmp > max)
                        max = tmp;
                }
            }
             
            System.out.println("#" + t + " " + max);
        }
 
    }
 
}