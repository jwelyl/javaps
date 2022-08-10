import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/10989
 * @difficuly B1
 * @performance 480640KB   1676ms
 * @category # 정렬, Counting Sort, 입출력
 * @memo Bronze 문제라 매우 간단할 줄 알고 IDE도 안켜고 Scanner와 Arrays.sort, println으로 풀었더니 시간 초과가 떴었다.
 * @memo 또한 8MB 제한인데 최대 10,000,000개의 정수가 들어오므로 모든 정수를 배열에 저장할 경우 적게 잡아도 40MB가 필요했다. Counting Sort가 필요했다.
 * @memo 수의 개수가 10,000,000개인 것이지 수의 최대 크기는 10,000이므로 Counting sort를 쓰기에 적절했다.
 * @memo Arrays.sort 대신 Counting sort를 사용, Scanner 대신 BufferedReader를 사용하고 println 대신 StringBuilder를 사용했더니 메모리 초과가 떴다.
 * @memo StringBuilder 대신 println을 사용하면 다시 시간초과가 발생했다.
 * @memo StringBuilder를 사용했는데 메모리 초과가 나는 이유를 봤더니 줄바꿈을 추가할 때 append가 아니라 +로 concat 연산을 해서 그렇다.
 * @memo concat(+)를 사용하면 새로운 String을 만들지 않으려고 StringBuilder를 사용하는 장점이 퇴색된다.
 * 
 * @etc Bronze여도 얻을 것은 분명 존재한다는 것을 깨달았다.
 *  
 */

public class BOJ_10989 {
    static int N;
    static int[] cnt = new int[10_001];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder(); 
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(bf.readLine());
            cnt[num]++;
        }
        
        for(int i = 0; i <= 10_000; i++) {
            for(int j = 0; j < cnt[i]; j++) {
                sb.append(i).append("\n");
//                sb.append(i + "\n");	//	메모리 초과
            }
        }
        
        System.out.println(sb);
    }
}