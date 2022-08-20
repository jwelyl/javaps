import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/11812
 * @difficuly G3
 * @performance 59120KB   424ms
 * @category # Complete K-ary tree에서의 임의의 노드의 부모 노드 찾기
 * @memo 노드의 개수가 최대 10^15개를 넘어가기 때문에 DFS는 어림도 없음. 그만한 배열을 선언할 수도 없음.
 * @memo 수학적으로 접근하여 해결해야 함. 루트 노드를 1번으로 볼 경우 직관적으로 부모 노드의 번호를 구하기 어려웠음. 공식은 존재함.
 * @memo 루트 노드를 0번으로 볼 경우 상대적으로 쉽게 특정 노드의 부모 노드를 찾을 수 있음
 * @memo K=1인 skewed tree의 처리를 해주어야 했음.
 * @memo skewed tree 처리를 해도 각 노드의 깊이를 직접 구할 경우 시간 초과가 발생했음.
 * @memo 정확한 깊이를 몰라도 정점 번호가 클 수록 깊이 존재한다는 성질을 이용하면 쉽게 풀 수 있음.
 * @etc 다른 사람의 풀이를 보니 높이를 직접 구해도 시간초과에 걸리지 않았음. 높이 구하는 함수를 확인하고 다시 시도해보기.
 *  
 */

public class Main {
	static long N;
	static int K, Q;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static long parentOf(long x) {	//	x : 0 ~ N - 1
		if(x == 0)
			return 0;
		
		if(x % K == 0)			
			return x / K - 1;
		
		return x / K;
	}
	
	static long distanceOf(long x, long y) {
		if(K == 1)	//	skewed tree일 경우
			return (long)Math.abs(y - x);
		
		long ret = 0;
		
		while(x != y) {	//	x와 y가 같지 않을 경우
			long X = Math.max(x, y);	//	더 큰 정점(X)이 더 깊은 위치에 존재(적어도 같은 깊이)
			long Y = Math.min(x, y);
			
			x = parentOf(X);	//	더 깊은 위치에 있는 정점을 부모 위치로 이동
			y = Y;
			ret++;
		}	//	둘이 같아질 때까지 이동
		
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		
		N = Long.parseLong(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
	
		for(int i = 0; i < Q; i++) {
			long a, b;
			
			tokens = new StringTokenizer(bf.readLine());
			a = Long.parseLong(tokens.nextToken()) - 1;
			b = Long.parseLong(tokens.nextToken()) - 1;
			
			sb.append(distanceOf(a, b) + "\n");
		}
	
		System.out.println(sb);
	}

}