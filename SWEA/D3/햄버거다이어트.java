import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class 햄버거다이어트 {
    static int T;
    static int N, L;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
 
    static int[] weight;
    static int[] price;
    static boolean[] isSelected;
 
    static int[][] dp;
 
    static int ans = 0;
 
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        T = Integer.parseInt(bf.readLine());
 
        for (int t = 1; t <= T; t++) {
            ans = 0;
            tokens = new StringTokenizer(bf.readLine());
 
            N = Integer.parseInt(tokens.nextToken());
            L = Integer.parseInt(tokens.nextToken());
 
            weight = new int[N + 1];
            price = new int[N + 1];
            isSelected = new boolean[N + 1];
            dp = new int[N + 1][L + 1];
 
            for (int i = 1; i <= N; i++) {
                tokens = new StringTokenizer(bf.readLine());
 
                price[i] = Integer.parseInt(tokens.nextToken());
                weight[i] = Integer.parseInt(tokens.nextToken());
            }
             
            knapsack();
            //subset(1);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
 
    static void knapsack() {
        for (int i = 0; i <= L; i++)
            dp[0][i] = 0;
        for (int i = 0; i <= N; i++)
            dp[i][0] = 0;
 
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                if (weight[i] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Integer.max(dp[i - 1][j], price[i] + dp[i - 1][j - weight[i]]);
                 
                if(ans < dp[i][j])
                    ans = dp[i][j];
            }
        }
    }
     
    static void subset(int idx) {
        if(idx == N + 1) {
            int sumPrice = 0;
            int sumWeight = 0;
             
            for(int i = 1; i <= N; i++) {
                if(isSelected[i]) {
                    sumPrice += price[i];
                    sumWeight += weight[i];
                }
            }
             
            if(sumWeight > L)
                return;
             
            if(sumPrice > ans)
                ans = sumPrice;
             
            return;
        }
         
        isSelected[idx] = true;
        subset(idx + 1);
         
        isSelected[idx] = false;
        subset(idx + 1);
    }
 
}