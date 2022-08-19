import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Ladder1 {
    static int[][] ladders;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int tcNum, result = -1;
    static boolean answer = false;
    static int[] dy = {0, 1, 0};
    static int[] dx = {1, -1, 0};
     
    static boolean isInX(int x, int y) {
        return 0 <= x && x < 100 && ladders[y][x] == 1;
    }
     
    static void doLadder(int x) {
        int y = 0;
//      System.out.println(x + "에서 사다리 타기 시각");
         
        while(y < 100) {
            if(y == 99) {
                if(ladders[y][x] == 2) {
                    answer = true;
                    break;
                }
            }
             
            int left = x - 1;
            int right = x + 1;
            int next;
             
            if(isInX(left, y)) {
                next = left;
                 
                while(isInX(next - 1, y))
                    next = next - 1;
                x = next;
            }
            else if(isInX(right, y)) {
                next = right;
                 
                while(isInX(next + 1, y))
                    next = next + 1;
                x = next;
            }
            y++;
        }
//      System.out.println(x + "에서 사다리 타기 끝");
    }
     
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        for(int t = 0; t < 10; t++) {
            answer = false;
            result = -1;
            ladders = new int[100][100];    //  사다리 할당  
            tcNum = Integer.parseInt(bf.readLine());    //  테스트케이스 번호
         
            for(int i = 0; i < 100; i++) {                               //  사다리 입력
                tokens = new StringTokenizer(bf.readLine());
                for(int j = 0; j < 100; j++)
                    ladders[i][j] = Integer.parseInt(tokens.nextToken());
            }
             
            for(int x = 0; x < 100; x++) {
                if(ladders[0][x] != 0) {
                    doLadder(x);
                    if(answer) {
                        result = x;
                        break;
                    }
                }
            }
             
            System.out.println("#" + tcNum + " " + result);
        }
    }
}