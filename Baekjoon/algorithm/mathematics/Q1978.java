import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, ans = 0;
	static boolean[] notPrime = new boolean[1001];	//	false면 소수, true면 합성수
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static void makeEratosthenesSieve() {
		Arrays.fill(notPrime, false);
		notPrime[1] = true;	//	1은 소수 아님
		
		for(int i = 2; i <= (int)Math.sqrt(1000); i++) {
			if(notPrime[i])	//	i가 합성수일 경우 skip
				continue;
			
			//	1 * i부터 (i-1)*i까지의 i의 배수는 이미 이전에 확인됨. 그 이후 i의 배수들은 합성수  
			for(int j = i * i; j <= 1000; j += i)
				notPrime[j] = true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		makeEratosthenesSieve();
		
//		for(int i = 1; i <= 50; i++) {
//			System.out.print(i + " : " + notPrime[i] + ", ");
//		}
		
		N = Integer.parseInt(bf.readLine());
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(tokens.nextToken());
			if(!notPrime[num])
				ans++;
		}
		
		System.out.println(ans);
	}

}
