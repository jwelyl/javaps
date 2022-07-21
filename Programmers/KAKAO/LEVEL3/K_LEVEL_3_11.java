import java.util.*;

class Node {    //  Queue에 삽입할 Node 클래스 정의
    int y;          //  y 좌표(행)
    int x;          //  x 좌표(열)
    int dir;        //  (y, x)로 올 때 방향
    int price;      //  (y, x)까지 경주로 건설한 비용
    
    Node(int y, int x, int dir, int price) {
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.price = price;
    }
}

class Solution {
    final int NONE = -1;                    //  시작점은 방향이 없음
    final int INF = Integer.MAX_VALUE;      //  최댓값 INF
    int n;                                  //  부지 크기
    int[][][] cost;                         //  cost[d][y][x] : d방향으로 왔을 때 (y, x)까지 경주로 건설 비용
    
    //  오른쪽, 아래쪽, 왼쪽, 위쪽 순
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    
    boolean canMove(int y, int x, int[][] board) {  //  (y, x)로 이동 가능한 지 check
        //  범위를 벗어나지 않고 (y, x)가 벽이 아닐 경우 true
        return (0 <= y && y < n) && (0 <= x && x < n) && (board[y][x] == 0);
    }
    
    void bfs(int[][] board) {
        Queue<Node> q = new LinkedList<Node>(); //  큐
        for(int d = 0; d < 4; d++)
            cost[d][0][0] = 0;
        q.offer(new Node(0, 0, NONE, cost[0][0][0]));
        
        while(!q.isEmpty()) {
            Node curNode = q.poll();    //  현재 위치 (cy, cx)
            int cy = curNode.y;
            int cx = curNode.x;
            int cd = curNode.dir;       
            int cp = curNode.price;
        
            for(int d = 0; d < 4; d++) {    //  다음 위치 (ny, nx)
                int ny = cy + dy[d];
                int nx = cx + dx[d];
                int nd = d;            //   (ny, nx)로 가기 위한 방향
                int np = cp;           //   (ny, nx)까지 경주로 건설하는 데 드는 비용
                
                if(!canMove(ny, nx, board)) //  다음 위치로 갈 수 없을 경우
                    continue;
                
                if(cd == NONE)  //  현재 위치 오는데 방향이 없을 경우(시작점 (0, 0))
                    np += 100;  //  직선 도로만추가
                else if(cd % 2 == nd % 2)   //  다음 위치 가는 데 코너가 필요 없을 경우
                    np += 100;              //  직선 도로만 추가
                else                        //  다음 위치 가는 데 코너도 필요한 경우
                    np += 600;              //  직선 도로 + 코너 추가
                
                if(np < cost[nd][ny][nx]) { //  nd 방향으로 (ny, nx)까지 오는 비용이 갱신될 경우
                    cost[nd][ny][nx] = np;  //  비용 갱신
                    q.offer(new Node(ny, nx, nd, np));  //  큐에 삽입
                }
            }    
        }
    }
    
    public int solution(int[][] board) {
        int answer = INF;
        n = board.length;
        
        //  cost 배열 할당 및 초기화
        cost = new int[4][n][n];
        for(int d = 0; d < 4; d++) {
            for(int i = 0; i < n; i++)
                Arrays.fill(cost[d][i], INF);
        }
  
        bfs(board);
        
        for(int d = 0; d < 4; d++) {    //  4방향 중 최소 비용인 방향을 최솟값으로 설정
            if(answer > cost[d][n - 1][n - 1])  
                answer = cost[d][n - 1][n - 1];
        }
        
        return answer;
    }
}