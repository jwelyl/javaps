package boj;

import java.util.Scanner;

public class EratosthenesSieve {
	static int N, T;
	static int[] sieve;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int input = 0;
		// TODO Auto-generated method stub
		System.out.print("구하려는 범위(2 ~ N) 입력 : ");
		N = sc.nextInt();

		sieve = new int[N + 1];

		for (int i = 1; i <= N; i++)
			sieve[i] = i;

		for (int i = 2; i <= (int) Math.sqrt(N); i++) {
			if (sieve[i] == 0)
				continue;

			for (int j = i * i; j <= N; j += i)
				sieve[j] = 0;
		}

		System.out.print("테스트 케이스 개수(T) 입력 : ");
		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			input = N + 1;
			while(input > N) {
				System.out.printf("#%d. 2 ~ %d 사이 정수 입력 : ", t, N);
				input = sc.nextInt();
			}
			System.out.println("" + t + ". " + input + (sieve[input] == 0 ? "은 합성수" : "은 소수"));
		}
	}
}
