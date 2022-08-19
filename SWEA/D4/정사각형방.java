import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class 정사각형방 {
    static int T;
    static int N;
    static int[][] square;
    static boolean[][] visited;
 
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
 
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
 
    static int ansNum = Integer.MAX_VALUE;
    static int ansCnt = 0;
 
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        T = Integer.parseInt(bf.readLine());
 
        for (int t = 1; t <= T; t++) {
            ansNum = Integer.MAX_VALUE;
            ansCnt = 0;
            N = Integer.parseInt(bf.readLine());
 
            square = new int[N][N];
//          visited = new boolean[N][N];
 
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++)
                    square[i][j] = Integer.parseInt(tokens.nextToken());
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = square[i][j];
                    int cnt = 0;
                     
//                  if(!visited[i][j]) {
//                      System.out.println("num = " + num);
                        cnt = dfs(i, j, 1);
//                  }
                     
                    if(cnt > ansCnt) {
                        ansCnt = cnt;
                        ansNum = num;
                    } else if(cnt == ansCnt && num < ansNum) {
                        ansNum = num;
                    }
                }
            }
             
            sb.append("#" + t + " " + ansNum + " " + ansCnt + "\n");
        }
         
        System.out.println(sb);
    }
 
    static boolean isIn(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N);
    }
 
    static int dfs(int y, int x, int cnt) {
//      System.out.println("dfs(" + y + ", " + x + ", " + cnt + ")");
        int curNum = square[y][x];
//      visited[y][x] = true;
         
        for(int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            int nextNum;
             
            if(!isIn(ny, nx) /*|| visited[ny][nx]*/)
                continue;
             
            nextNum = square[ny][nx];
            if(nextNum == curNum + 1)
                return dfs(ny, nx, cnt + 1);
        }
         
        return cnt;
    }
}