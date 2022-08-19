import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
 
/**
 * 
 * @author koreii
 * @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYHg4cRqHpADFAV6&contestProbId=AV5LyE7KD2ADFAXc&probBoxId=AYHg4cRqHpEDFAV6+&type=PROBLEM&problemBoxTitle=8%EC%9B%94+1%EC%A3%BC&problemBoxCnt=++5+
 * @difficuly D3
 * @performance 21496KB   128ms
 * @category # 구현, 시뮬레이션
 * @memo 시키는 것만 충실히 구현하면 구현 자체는 어렵지 않았음
 * @memo 이동, 발포를 모듈화
 * @memo 맵을 입력받을 때 초기 전차의 위치를 찾아서 저장하는 과정
 * @memo 각 테스트케이스가 끝나고 빈 줄 없이 바로 다음 테스트케이스 출력해야함
 * @etc 크게 어렵지도 않고 재미있는 문제였던 것 같음.
 *  
 */
public class 상호의배틀필드 {
    static int T, H, W, N;  //  테스트케이스 개수, 세로 길이, 가로 길이, 명령어 개수
    static int posy, posx;  //  전차 위치
    static char[][] map;    //  맵
    static char[] tankDir = {'^', '>', 'v', '<'};
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static Map<Character, Integer> dirMap = new HashMap<>();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//  static BufferedReader bf;
    static StringTokenizer tokens;
    static StringBuilder sb;
     
    static void drawMap(int t) {
        sb = new StringBuilder();
         
//      System.out.print("#" + t + " ");
        sb.append("#" + t + " ");
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
//              System.out.print(map[i][j]);
                sb.append(map[i][j]);
            }
//          System.out.println();
            sb.append("\n");
        }
         
//      System.out.println(sb);
        System.out.print(sb);
    }
     
    static void init() {    //  이동명령어에 따른 이동방향 정하기
        dirMap.put('U', 0);
        dirMap.put('R', 1);
        dirMap.put('D', 2);
        dirMap.put('L', 3);
    }
     
    static void go(int dir) {
        if(dir == 0) {  //  위로 이동
            map[posy][posx] = '^';  //  방향을 위로 틈
            if(isIn(posy - 1, posx) && map[posy - 1][posx] == '.') {    //  위로 한칸 이동가능할 경우
                map[posy][posx] = '.';  //  현재 위치는 비움
                posy -= 1;              //  위로 한칸 이동
                map[posy][posx] = '^';  //  위의 위치에 전차 배치
            }
             
        } else if(dir == 1) {   //  우로 이동
            map[posy][posx] = '>';
            if(isIn(posy, posx + 1) && map[posy][posx + 1] == '.') {    //  오른쪽으로 한칸 이동가능할 경우
                map[posy][posx] = '.';  //  현재 위치는 비움
                posx += 1;              //  오른쪽으로 한칸 이동
                map[posy][posx] = '>';   //  오른쪽의 위치에 전차 배치
            }
             
        } else if(dir == 2) {   // 아래로 이동
            map[posy][posx] = 'v';
            if(isIn(posy + 1, posx) && map[posy + 1][posx] == '.') {    //  아래로 한칸 이동가능할 경우
                map[posy][posx] = '.';  //  현재 위치는 비움
                posy += 1;              //  아래로 한칸 이동
                map[posy][posx] = 'v';  //  아래의 위치에 전차 배치
            }
             
        } else if(dir == 3) {   // 좌로 이동
            map[posy][posx] = '<';
            if(isIn(posy, posx - 1) && map[posy][posx - 1] == '.') {    //  왼쪽으로 한칸 이동가능할 경우
                map[posy][posx] = '.';  //  현재 위치는 비움
                posx -= 1;              //  왼쪽으로 한칸 이동
                map[posy][posx] = '<';   //  왼쪽의 위치에 전차 배치
            }
        }
    }
     
    static void shoot() {
        int dir = -1;   //  현재 전차가 바라보는 방향
        for(int d = 0; d < 4; d++) {
            if(map[posy][posx] == tankDir[d]) {
                dir = d;
                break;
            }
        }
         
        int sy = posy, sx = posx;   //  포탄 발삳된 위치
         
        while(true) {
            sy = sy + dy[dir];  //  포탄이 전차가 바라보는 방향으로 이동
            sx = sx + dx[dir];
             
            if(!isIn(sy, sx))   //  이동하려는 위치가 맵 밖일 경우
                 break;
             
            if(map[sy][sx] == '*') {    //  이동하려는 위치가 벽돌 벽일 경우
                map[sy][sx] = '.';  //벽을 부숨
                break;
            }
             
            if(map[sy][sx] == '#') {    //  이동하려는 위치가 강철 벽일 경우
                break;  //  도탄!
            }
        }
    }
     
    static void inputMap(int H, int W) throws IOException {
        map = new char[H][W];
         
        for(int i = 0; i < H; i++) {
            String line = bf.readLine();
            for(int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                  
                if(map[i][j] == '^' || map[i][j] == '>' || map[i][j] == 'v' || map[i][j] == '<') {
                    posy = i;
                    posx = j;
                }
            }
        }
    }
     
    static boolean isIn(int y, int x) {
        return (0 <= y && y < H) && (0 <= x && x < W);
    }
     
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
//      bf = new BufferedReader(new FileReader("battlefield_input.txt"));
         
        T = Integer.parseInt(bf.readLine());
        init();
         
        for (int t = 1; t <= T; t++) {
            String cmds;
             
            tokens = new StringTokenizer(bf.readLine());
             
            H = Integer.parseInt(tokens.nextToken());
            W = Integer.parseInt(tokens.nextToken());
 
            inputMap(H, W);
         
            N = Integer.parseInt(bf.readLine());
            cmds = bf.readLine();
             
            for(int i = 0; i < N; i++) {
                char cmd = cmds.charAt(i);
                 
                if(cmd == 'S')
                    shoot();
                else {
                    int dir = dirMap.get(cmd);
                    go(dir);
                }
            }
             
            drawMap(t);
         
        }   //  test loop
    }   //  main
}