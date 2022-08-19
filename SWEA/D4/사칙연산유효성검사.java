import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class 사칙연산유효성검사 {
    static int T = 10;
    static int N;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
     
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
         
        for(int t = 1; t <= T; t++) {
            isValid = true;
            N = Integer.parseInt(bf.readLine());
         
            nodeList = new Node[N + 1];
            for(int i = 1; i <= N; i++)
                nodeList[i] = new Node();
            rootNode = nodeList[1];
             
            for(int i = 1; i <= N; i++) {
                String[] input = new String[4];
                int idx = 0;
                 
                tokens = new StringTokenizer(bf.readLine());
                 
                while(tokens.hasMoreTokens()) {
                    input[idx++] = tokens.nextToken();
                }
                 
                int num = Integer.parseInt(input[0]);
                char ch = input[1].charAt(0);
                Node left = (input[2] == null) ? null : nodeList[Integer.parseInt(input[2])]; 
                Node right = (input[3] == null) ? null : nodeList[Integer.parseInt(input[3])]; 
                 
                nodeList[num].ch = ch;
                nodeList[num].left = left;
                nodeList[num].right = right;
            }
             
            dfs(rootNode);
             
            sb.append("#").append(t).append(" ").append(isValid ? 1 : 0).append("\n");
        }
         
        System.out.println(sb);
    }
 
    static class Node {
        char ch;
        Node left;
        Node right;
    }
     
    static Node rootNode;
    static Node[] nodeList;
     
    static boolean isValid = true;
    static void dfs(Node node) {
        char ch = node.ch;
         
        if('0' <= ch && ch <= '9') {  //  숫자일 경우
            if(node.left != null || node.right != null) {   //  숫자인데 자식 노드가 있을 경우
                isValid = false;
                return;
            }
        }
        if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {  //  연산자일 경우
            if(node.left == null || node.right == null) {   //  연산자인데 자식 노드가 하나라도 없을 경우
                isValid = false;
                return;
                 
            }
        }
         
        if(node.left != null)
            dfs(node.left);
        if(node.right != null)
            dfs(node.right);
    }
}