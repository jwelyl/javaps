import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class 무선충전 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
     
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
     
    static ArrayList<Integer>[][] map;    //  map[i][j]에는 좌표 (i, j)에 범위가 닿는 충전기 번호들이 존재함
     
    static int[] bcY;       //  BC의 y 좌표
    static int[] bcX;       //  BC의 x 좌표
    static int[] bcPower;   //  BC의 충전량
    static int[] bcRange;   //  BC의 충전 범위
     
    static int T, M, A;
    static int[] dirA;  //  A 이동 궤적
    static int[] dirB;  //  B 이동 궤적
    static int aY, aX;  //  A 위치
    static int bY, bX;  //  B 위치
     
    static int sum = 0; //  A, B 모두 얻은 충전량
     
    static int manhattanDist(int fromY, int fromX, int toY, int toX) {
        return (int)Math.abs(fromY - toY) + (int)Math.abs(fromX - toX);
    }
     
    static boolean isIn(int y, int x) {
        return (0 <= y && y < 10) && (0 <= x && x < 10);
    }
     
    static void makeMap() {
        map = new ArrayList[10][10];
         
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
                map[i][j] = new ArrayList<Integer>(); //  (i, j)에 범위 닿는 충전기가 없을 경우 map[i][j]는 빈 배열
    }
     
    static void sortMap() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Collections.sort(map[i][j], new Comparator<Integer>() {
                    @Override
                    public int compare(Integer i1, Integer i2) {
                        return -Integer.compare(bcPower[i1], bcPower[i2]);
                    }
                });
            }
        }
    }
     
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
         
        for(int t = 1; t <= T; t++) {
            aY = 0; aX = 0;
            bY = 9; bX = 9;
            sum = 0;
            makeMap();
             
            tokens = new StringTokenizer(bf.readLine());
         
            M = Integer.parseInt(tokens.nextToken());
            A = Integer.parseInt(tokens.nextToken());
             
            dirA = new int[M + 1];
            dirB = new int[M + 1];
             
            tokens = new StringTokenizer(bf.readLine());
            for(int i = 1; i <= M; i++)
                dirA[i] = Integer.parseInt(tokens.nextToken());
            tokens = new StringTokenizer(bf.readLine());
            for(int i = 1; i <= M; i++)
                dirB[i] = Integer.parseInt(tokens.nextToken());
 
            bcY = new int[A];
            bcX = new int[A];
            bcPower = new int[A];
            bcRange = new int[A];
             
            for(int i = 0; i < A; i++) {
                int num, y, x, power, range;
                 
                tokens = new StringTokenizer(bf.readLine());
                num = i;
                x = Integer.parseInt(tokens.nextToken()) - 1;
                y = Integer.parseInt(tokens.nextToken()) - 1;
                range = Integer.parseInt(tokens.nextToken());
                power = Integer.parseInt(tokens.nextToken());
                 
                bcY[num] = y;
                bcX[num] = x;
                bcPower[num] = power;
                bcRange[num] = range;
 
                //  맨허튼 거리가 충전 범위 이하인 칸들은 모두 num 충전기의 충전 범위임
                for(int ii = 0; ii < 10; ii++) {
                    for(int jj = 0; jj < 10; jj++) {
                        if(manhattanDist(y, x, ii, jj) <= range) {
                            map[ii][jj].add(num);
                        }
                    }
                }
            }   //  input-end
            sortMap();
             
            for(int i = 0; i <= M; i++) {
                aY = aY + dy[dirA[i]];
                aX = aX + dx[dirA[i]];
                bY = bY + dy[dirB[i]];
                bX = bX + dx[dirB[i]];
         
                int numA, numB; //  A, B가 각각 (aY, aX), (bY, bX)에 위치할 때 접근할 수 있는 충전기 번호
                int cntA = map[aY][aX].size();  //  A가 (aY, aX)에서 접근 가능한 충전기 개수
                int cntB = map[bY][bX].size();  //  B가 (bY, bX)에서 접근 가능한 충전기 개수
                 
                if(cntA == 0)
                    numA = -1;  //  A가 접근할 수 있는 충전기가 없음
                else
                    numA = map[aY][aX].get(0);
                 
                if(cntB == 0)
                    numB = -1;  //  B가 접근할 수 있는 충전기가 없음
                else
                    numB = map[bY][bX].get(0);
                 
                if(numA == -1 && numB == -1) {
                    continue;
                }
                 
                if(numA == -1 && numB != -1) {
                    sum += bcPower[numB];
                    continue;
                }
                if(numA != -1 && numB == -1) {
                    sum += bcPower[numA];
                    continue;
                }
                 
                if(numA != -1 && numB != -1) {
                    if(numA != numB) {
                        sum += (bcPower[numA] + bcPower[numB]);     
                        continue;
                    }
                    else {  //  두 개 모두 접근 가능한 가장 강력한 충전기가 같을 경우
                        if(cntA == 1 && cntB == 1)
                            sum += (bcPower[numA]);
                        else if(cntA == 1 && cntB > 1) {
                            numB = map[bY][bX].get(1);
                            sum += (bcPower[numA] + bcPower[numB]);
                        }
                        else if(cntA > 1 && cntB == 1) {
                            numA = map[aY][aX].get(1);
                            sum += (bcPower[numA] + bcPower[numB]);
                        }
                        else if(cntA > 1 && cntB > 1) {
                            int numA2 = map[aY][aX].get(1);
                            int numB2 = map[bY][bX].get(1);
                             
                            if(bcPower[numA2] >= bcPower[numB2])
                                sum += (bcPower[numA2] + bcPower[numB]);
                            else
                                sum += (bcPower[numA] + bcPower[numB2]);
                        }
                    }
                }
            }   //  이동-end
            sb.append("#").append(t).append(" ").append(sum).append("\n");
             
        }   //  testcase-end
         
        System.out.println(sb);
    }   //  main-end
}