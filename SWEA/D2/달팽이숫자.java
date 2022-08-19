import java.util.Scanner;
 
public class 달팽이숫자 {
    static int T, N, num = 1;
    static int[][] snail;
    static boolean[][] visited;
 
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
 
    static Scanner sc = new Scanner(System.in);
 
    static void printBools() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visited[i][j] ? "t " : "f ");
            }
            System.out.println();
        }
        System.out.println();
    }
 
    static void fill(int cnt, boolean bool) {
        for (int i = cnt; i <= N - cnt - 1; i++) {
            for (int j = cnt; j <= N - cnt - 1; j++)
                visited[i][j] = bool;
        }
    }
 
    static boolean canMove(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N) && (!visited[y][x]);
    }
 
    static void dfs(int y, int x) {
        snail[y][x] = num++;
        visited[y][x] = true;
 
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
 
            if (canMove(ny, nx))
                dfs(ny, nx);
        }
    }
 
    public static void main(String[] args) {
        T = sc.nextInt();
 
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            num = 1;
 
            snail = new int[N][N];
            visited = new boolean[N][N];
 
            for (int i = 0; i < (N + 1) / 2; i++) {
                fill(i + 1, true);
                dfs(i, i);
                fill(i + 1, false);
            }
 
            System.out.println("#" + t);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    System.out.print(snail[i][j] + " ");
                System.out.println();
            }
        }
    }
}