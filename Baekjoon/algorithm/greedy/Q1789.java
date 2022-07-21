import java.util.Scanner;

public class Main {
	static long S, N, sum;
	static Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		S = sc.nextLong();
		sum = N;
		
		do {
			N++;
			sum += N;
			
			if(sum == S) {
				System.out.println(N);
				return;
			}
		} while(sum <= S);
	
		sum -= N;
		N--;
		System.out.println(N);
		
	}
}