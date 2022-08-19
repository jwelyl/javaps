import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 은서파
 * @since 2022. 8. 19.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */

public class 좋은저녁 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    static int N, M, K;
    static boolean[][] graph;
    static List[] graph2;
    static LinkNode [] graph3;
    
    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new StringReader(instr));
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken()) + 1;
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        makeGraphLinkNode();
        System.out.println(Arrays.toString(graph3));
        dfsLinkNode(K, new boolean[N]);
        output.append("\n");
        bfsLinkNode();
       /*
        makeGraphList();
        System.out.println(Arrays.toString(graph2));
        dfsList(K, new boolean[N]);
        output.append("\n");
        bfsList();
        */
        /*for(boolean [] row: graph) {
            System.out.println(Arrays.toString(row));
        }*/

        // makeGraphMatrix();
        //dfsMatrix(K, new boolean[N]);
        //output.append("\n");
        //bfsMatrix();
        System.out.println(output);
    }
    
    
    static class LinkNode{
        int idx;
        LinkNode next;
        
        public LinkNode(int idx, LinkNode next) {
            this.idx = idx;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[idx=" + idx + ", next=" + next + "]";
        }
    }
    
    static void makeGraphLinkNode() throws IOException {
        graph3 = new LinkNode[N];

        for (int m = 0; m < M; m++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            // 그래프의 방향 - 양방향 그래프
            graph3[a] = new LinkNode(b, graph3[a]);
            graph3[b] = new LinkNode(a, graph3[b]);
        }
    }
    
    static void makeGraphList() throws IOException {
        graph2 = new List[N];
        for(int i=1; i<N; i++) {
            graph2[i] = new ArrayList<>(); // i와 연결된 노드의 번호만을 관리한다.
        }
        for (int m = 0; m < M; m++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            // 그래프의 방향 - 양방향 그래프
            graph2[a].add(b);
            graph2[b].add(a);
        }
    }
    
    

    static void makeGraphMatrix() throws IOException {
        graph = new boolean[N][N];
        for (int m = 0; m < M; m++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            // 그래프의 방향 - 양방향 그래프

            graph[a][b] = graph[b][a] = true;
        }
    }

    static void bfsOriginal() {
        // 1. 준비물
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        // 2. 초기화 작업
        q.offer(K);

        while (!q.isEmpty()) {
            // 3. 대장 데려오기
            Integer head = q.poll();
            if (visited[head]) {
                continue;
            }

            // 4. 할일 처리
            visited[head] = true;
            output.append(head).append(" ");

            // 5. 자식 탐색
            for (int c = 0; c < N; c++) {
                if (graph[head][c] && !visited[c]) {
                    q.offer(c); // 담을 때는 아직 미방문이었다.!!
                }
            }
        }
    }

    static void bfsMatrix() {
        // 1. 준비물
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        // 2. 초기화 작업
        q.offer(K);
        visited[K] = true;
        int turn = 0;
        while (!q.isEmpty()) {

            int size = q.size(); // 현재 담겨 있는 것들!!

            while (size-- > 0) {
                // 3. 대장 데려오기
                Integer head = q.poll();

                // 4. 할일 처리
                visited[head] = true;
                output.append(head).append(" ");

                // 5. 자식 탐색
                for (int c = 0; c < N; c++) {
                    if (graph[head][c] && !visited[c]) {
                        q.offer(c); // 담을 때는 아직 미방문이었다.!!
                        visited[c] = true;
                    }
                }
            }
            turn++;
        }
        System.out.printf("총 %d 번만에 탐색 완료", turn);
    }

    static void dfsMatrix(int v, boolean[] visited) {
        // 1. 할일 처리(ex: 방문 처리)
        visited[v] = true;
        output.append(v).append(" ");

        // 2. 자식 탐색
        for (int c = 0; c < N; c++) {
            // 연결되어있고 미방문이면 가보자.
            if (graph[v][c] && !visited[c]) {
                dfsMatrix(c, visited);
            }
        }
    }
    
    static void dfsList(int v, boolean[] visited) {
        // 1. 할일 처리(ex: 방문 처리)
        visited[v] = true;
        output.append(v).append(" ");

        // 2. 자식 탐색
        List<Integer> childs = graph2[v]; // 자식을 행렬에서 가져오는게 아니네!!
        for(Integer child: childs) {
            if(!visited[child]) {
                dfsList(child, visited);
            }
        }
    }
    
    static void dfsLinkNode(int v, boolean[] visited) {
        // 1. 할일 처리(ex: 방문 처리)
        visited[v] = true;
        output.append(v).append(" ");

        // 2. 자식 탐색 - graph3 활용
        LinkNode child = graph3[v]; // 자식을 행렬에서 가져오는게 아니네!!
        while(child !=null) {
            if(!visited[child.idx]) {
                dfsLinkNode(child.idx, visited);
            }
            child = child.next;
        }
    }
    static void bfsList() {
        // 1. 준비물
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        // 2. 초기화 작업
        q.offer(K);
        visited[K] = true;
        int turn = 0;
        while (!q.isEmpty()) {

            int size = q.size(); // 현재 담겨 있는 것들!!

            while (size-- > 0) {
                // 3. 대장 데려오기
                Integer head = q.poll();

                // 4. 할일 처리
                visited[head] = true;
                output.append(head).append(" ");

                // 5. 자식 탐색
                List<Integer> childs = graph2[head];
                for(Integer child: childs) {
                    if(!visited[child]) {
                        visited[child]=true;
                        q.offer(child);
                    }
                }
            }
            turn++;
        }
        System.out.printf("총 %d 번만에 탐색 완료%n", turn);
    }
    
    static void bfsLinkNode() {
        // 1. 준비물
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        // 2. 초기화 작업
        q.offer(K);
        visited[K] = true;
        int turn = 0;
        while (!q.isEmpty()) {

            int size = q.size(); // 현재 담겨 있는 것들!!

            while (size-- > 0) {
                // 3. 대장 데려오기
                Integer head = q.poll();

                // 4. 할일 처리
                visited[head] = true;
                output.append(head).append(" ");

                // 5. 자식 탐색
                LinkNode child = graph3[head];
                while(child!=null) {
                    if(!visited[child.idx]) {
                        visited[child.idx]=true;
                        q.offer(child.idx);
                    }
                    child = child.next;
                }
            }
            turn++;
        }
        System.out.printf("총 %d 번만에 탐색 완료%n", turn);
    }
    // REMOVE_START
    static String instr = "4 5 1\r\n" +
            "1 2\r\n" +
            "1 3\r\n" +
            "1 4\r\n" +
            "2 4\r\n" +
            "3 4";
    // REMOVE_END

}
