import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class 최적경로 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
 
    static int T, N;
    static int ans = Integer.MAX_VALUE;
 
    static class Pos {
        int y;
        int x;
 
        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
         
        @Override
        public String toString() {
             
            return "[" + y + ", " + x + "]";
        }
    }
 
    static Pos company = null;
    static Pos[] customers = null;
    static Pos home = null;
     
    static int manhattanDistance(Pos p1, Pos p2) {
        return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
    }
 
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
 
        for (int t = 1; t <= T; t++) {
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(bf.readLine());
            customers = new Pos[N];
             
            tokens = new StringTokenizer(bf.readLine());
            int y, x;
             
            y = Integer.parseInt(tokens.nextToken());
            x = Integer.parseInt(tokens.nextToken());
            company = new Pos(y, x);
             
            y = Integer.parseInt(tokens.nextToken());
            x = Integer.parseInt(tokens.nextToken());
            home = new Pos(y, x);
             
            for(int i = 0; i < N; i++) {
                y = Integer.parseInt(tokens.nextToken());
                x = Integer.parseInt(tokens.nextToken());
                customers[i] = new Pos(y, x);
            }
             
            permutation(0, new boolean[N], new int[N]);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
         
        System.out.println(sb);
    }
     
    static void permutation(int nth, boolean[] used, int[] order) {
        if(nth == N) {
            int midSum = 0;
//          System.out.println("경우의 수");
//          for(int i = 0; i < N; i++)
//              System.out.print(customers[order[i]] + " ");
//          System.out.println();
             
            midSum += manhattanDistance(company, customers[order[0]]);
            for(int i = 0; i < N - 1; i++)
                midSum += manhattanDistance(customers[order[i]], customers[order[i + 1]]);
            midSum += manhattanDistance(customers[order[N - 1]], home);
             
//          System.out.println("midSum = " + midSum);
            if(midSum < ans)
                ans = midSum;
             
            return;
        }
         
        for(int i = 0; i < N; i++) {
            if(!used[i]) {
                used[i] = true;
                order[nth] = i;
                permutation(nth + 1, used, order);
                used[i] = false;
            }
        }
    }
}