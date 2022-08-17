package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_2407 {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
//	long ans = 1;
	static BigInteger numerator = BigInteger.ONE;
	static BigInteger denominator = BigInteger.ONE;
	static BigInteger ans = BigInteger.ONE;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		M = Integer.min(M, N - M);

		for(int i = M; i >= 1; i--) {
			numerator = numerator.multiply(new BigInteger(String.valueOf(N - M + i)));
			denominator = denominator.multiply(new BigInteger(String.valueOf(i)));
		}

		ans = numerator.divide(denominator);
		System.out.println(ans);
	}
}
