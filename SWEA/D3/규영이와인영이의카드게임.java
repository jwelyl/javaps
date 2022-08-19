import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class 규영이와인영이의카드게임 {
    static boolean[] used;
    static int T;
    static int[] kyouyoung = new int[9];
    static int[] inyoung = new int[9];
    static int kyouyoungWin = 0;
    static int kyouyoungLose = 0;
 
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
     
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        T = Integer.parseInt(bf.readLine());
         
        for(int t = 1; t <= T; t++) {
            kyouyoungWin = 0;
            kyouyoungLose = 0;
            used = new boolean[19];
            tokens = new StringTokenizer(bf.readLine());
         
            for(int i = 0; i < 9; i++) {
                int num = Integer.parseInt(tokens.nextToken());
                used[num] = true;
                kyouyoung[i] = num;
            }
             
//          System.out.println("k : " + Arrays.toString(kyouyoung));
             
            int idx = 0;
            for(int i = 1; i <= 18; i++) {
                if(!used[i]) {
                    inyoung[idx++] = i;
                }
            }
//          System.out.println("i : " + Arrays.toString(inyoung));
             
            permutation(new int[9], new boolean[9], 0);
             
            System.out.println("#" + t + " " + kyouyoungWin + " " + kyouyoungLose);
        }   //  test-for end
    }   //  main end
     
    static void permutation(int[] res, boolean[] visited, int cnt) {
        if(cnt == 9) {
//          System.out.println("permutation(" + idx + ", " + cnt + ")");
//          System.out.println("res = " + Arrays.toString(res));
             
            int kScore = 0;
            int iScore = 0;
//          System.out.println("규영 점수 : " + kScore);
//          System.out.println("인영 점수 : " + iScore);
             
            for(int i = 0; i < 9; i++) {
                if(kyouyoung[i] > res[i])
                    kScore += (kyouyoung[i] + res[i]);
                else if(kyouyoung[i] < res[i])
                    iScore += (kyouyoung[i] + res[i]);
            }
             
//          System.out.println("규영 점수 : " + kScore);
//          System.out.println("인영 점수 : " + iScore);
             
            if(kScore > iScore)
                kyouyoungWin += 1;
            else if(kScore < iScore)
                kyouyoungLose += 1;
             
            return;
        }
         
        for(int i = 0; i < 9; i++) {
            if(!visited[i]) {
                 
                visited[i] = true;
                res[cnt] = inyoung[i];
                permutation(res, visited, cnt + 1);
                 
                visited[i] = false;
            }
        }
    }
}