import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class 암호문1 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static int T = 10, N, M;
    static int[] nums;
     
    public static void main(String[] args) throws IOException {
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(bf.readLine());
            nums = new int[N];
             
            tokens = new StringTokenizer(bf.readLine());
            for(int i = 0; i < N; i++)
                nums[i] = Integer.parseInt(tokens.nextToken());
             
            M = Integer.parseInt(bf.readLine());
             
            int x, y;
            int[] added;
            tokens = new StringTokenizer(bf.readLine());
            for(int i = 0; i < M; i++) {
                char cmd = tokens.nextToken().charAt(0);
                x = Integer.parseInt(tokens.nextToken());
                y = Integer.parseInt(tokens.nextToken());
             
                added = new int[y];
                for(int j = 0; j < y; j++) {
                    added[j] = Integer.parseInt(tokens.nextToken());
                }
                 
                nums = concat(x, y, added);
            }
             
            sb.append("#" + t + " ");
            for(int i = 0; i < 10; i++)
                sb.append(nums[i] + " ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
     
    static int[] concat(int x, int y, int[] added) {
        int[] ret = new int[nums.length + added.length];
         
        for(int i = 0; i < x; i++)
            ret[i] = nums[i];
        for(int i = x; i < x + added.length; i++)
            ret[i] = added[i - x];
        for(int i = x + added.length; i < ret.length; i++)
            ret[i] = nums[i - added.length];
         
        return ret;
    }
}