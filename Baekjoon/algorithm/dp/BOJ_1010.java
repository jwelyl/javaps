import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/1010
 * @difficuly S5
 * @performance 13656KB   88ms
 * @category # 조합, DP
 * @memo 몇 가지 간단한 경우를 떠올려보면 조합의 경우의 수를 구해야한다는 것은 쉽게 캐치 가능
 * @memo 재귀함수로 조합 경우의 수 구해보고 시간 초과 발생해서 DP로 해결했는데, 뭐 그것까진 그럴수 있다 치자.
 * @memo 근데 N, M 조건은 읽지도 않고 조합이니까 바로 combination 재귀 코드부터 짜는건 답이 없다.
 * @etc  코드를 작성하기 전에 N, M 등 input 크기 적어두고 어떤 알고리즘은 가능하고, 어떤 알고리즘은 불가능할지 생각하고 키보드에 손 올려라.
 * @etc  그리고 M!/(N!*(M -N)!) 으로 경우의 수 수학적으로 구하는 것으로도 해결해보기
 *  
 */

public class BOJ_1010 {
	static long[][] comb;
	static int T, N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 0; t < T; t++) {
			tokens = new StringTokenizer(bf.readLine());
			
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
		
			comb = new long[M + 1][M + 1];
			for(int i = 0; i <= M; i++)
				comb[i][0] = 1;
			comb[1][1] = 1;
			
			for(int i = 2; i <= M; i++)
				comb[i][i] = 1;
			
			for(int i = 2; i <= M; i++) {
				for(int j = 1; j < i; j++)
					comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
			}
			
			sb.append(comb[M][N]).append("\n");
		}
		
		System.out.println(sb);
	}
}
