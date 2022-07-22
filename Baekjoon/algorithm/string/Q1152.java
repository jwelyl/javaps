import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer token;
	static Scanner sc = new Scanner(System.in);
	static String line;
	
	public static void main(String[] args) {
		int ans = 0;
		
		line = sc.nextLine();
		token = new StringTokenizer(line);
		
		while(token.hasMoreTokens()) {
			token.nextToken();
			ans++;
		}
		
		System.out.println(ans);
	}
}
