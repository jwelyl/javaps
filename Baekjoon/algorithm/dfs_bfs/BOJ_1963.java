import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_1963 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int T, start, end;
	static boolean[] sieve = new boolean[10000];	//	false면 소수, true면 1 또는 합성수
	static boolean[] visited;
	static int time = 0;
	
	static void makeEratosthenesSieve() {
		sieve[1] = true;
		
		for(int i = 1; (i * i) <= 9999; i++) {
			if(!sieve[i]) {
				for(int j = i * i; j <= 9999; j += i)
					sieve[j] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		makeEratosthenesSieve();
		
		T = Integer.parseInt(bf.readLine());
		for(int t = 0; t < T; t++) {
			visited = new boolean[10000];
			time = 0;
			
			tokens = new StringTokenizer(bf.readLine());
			start = Integer.parseInt(tokens.nextToken());
			end = Integer.parseInt(tokens.nextToken());
	
			bfs();
			
			if(time == -1)
				sb.append("Impossible\n");
			else
				sb.append(time).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int charArrToInt(char[] str) {
		int ret = 0;
		int mult = 1000;
		
		for(int i = 0; i < 4; i++) {
			ret += (mult * (str[i] - '0'));
			mult /= 10;
		}
		
		return ret;
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int size = q.size();
//			System.out.println("현재 시각 = " + time);
			for(int i = 0; i < size; i++) {
				int cur = q.poll();
//				System.out.println("현재 숫자 = " + cur);
				char[] origin = String.valueOf(cur).toCharArray();
				char[] tmp = Arrays.copyOf(origin, origin.length);
//				System.out.println("origin = " + Arrays.toString(origin));
//				System.out.println("tmp = " + Arrays.toString(tmp));
				
				if(cur == end) {
//					System.out.println("종료 시각 = " + time);
					return;
				}
				
				for(int k = 0; k < 4; k++) {
//					System.out.println("k = " + k);
					for(int j = 0; j <= 9; j++) {
						if(k == 0 && j == 0)
							continue;
						else if(k == 3 && (j % 2 == 0 || j == 5))
							continue;
							
						tmp[k] = (char)(j + '0');
						
						int next = charArrToInt(tmp);
//						System.out.println("next = " + next);
						
						if(sieve[next] || visited[next])
							continue;
						else {
							visited[next] = true;
							q.offer(next);
						}
					}
					tmp[k] = origin[k];
				}
			}	//	for-qsize-end
			time++;
		}	//	while-end
	}	//	bfs-end
}
