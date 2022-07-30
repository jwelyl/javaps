import java.util.Scanner;

public class Main {
	static int A, B, max, min, mod, GCD, LCM;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		A = sc.nextInt();
		B = sc.nextInt();
		
		if(A > B) {
			max = A;
			min = B;
		} else {
			max = B;
			min = A;
		}
		
		do {
			mod = max % min;
			max = min;
			min = mod;
		} while(mod != 0);
		
		GCD = max;
		LCM = GCD * (A / GCD) * (B / GCD);
		
		System.out.println(GCD);
		System.out.println(LCM);
	}
}
