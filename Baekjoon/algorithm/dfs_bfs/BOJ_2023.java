import java.util.Scanner;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/2023
 * @difficuly G5
 * @performance 13192KB   116ms
 * @category # 소수 판별 방법, DFS(*)
 * @memo 일단 메모리 제한이 4MB이고 N이 최대 8이라 에라토스테네스의 체를 이용할 경우 적게 잡아도 100MB의 메모리가 필요해서 불가능함
 * @memo 임의의 정수의 소수 판정 방법을 이용해서 소수인지 판별해야 했음.
 * @memo 모든 N자릿수를 소수 판별할 경우 N=8일 때 10000000~99999999를 모두 검사해야 해서 당연히 시간초과가 남.(이를 캐치하지 못하다니)
 * @memo dfs를 이용하여 한 자릿수부터 시작, 끝에 한 자릿수씩 추가하며 소수인지 판별, N자릿수가 될 경우 출력하는 방법으로 했어야 함.
 * @etc 문제에서 요구하는 명시적인 것들은 캐치했지만 시간, 메모리 제한사항과 예상 복잡도를 유심히 파악하지 않았음.
 * @etc N의 크기에 따른 시간복잡도별 시간 제한 등을 숙지할 필요가 있을듯
 *  
 */

public class Main {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		N = sc.nextInt();
	
		dfs("", 0);
		
		System.out.println(sb);
	}
	
	static void dfs(String str, int digits) {
//		System.out.println("digits = " + digits);
		
		if(digits == N) {
//			System.out.println(str);
			sb.append(str + "\n");
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			String cur = str + i;
			if(isPrime(Integer.parseInt(cur)))
				dfs(cur, digits + 1);
		}
	}
	
	static boolean isPrime(int x) {
		if(x < 2) return false;
		
		for(int i = 2; i <= (int)Math.sqrt(x); i++) {
			if(x % i == 0)
				return false; 
		}
		
		return true;
	}
}