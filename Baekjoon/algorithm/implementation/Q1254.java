import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1254 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static String input;
	static int ans = 0;
	
	static boolean isPalindrome(String str) {
		int len = str.length();
		int mid = len / 2;
		int left, right;
		
		if(len % 2 == 0) {
			left = mid - 1;
			right = mid;
		}
		else {
			left = mid - 1;
			right = mid + 1;
		}
		
		while(left >= 0 && right < len) {
			if(str.charAt(left) != str.charAt(right))
				return false;
			
			left--;
			right++;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input = bf.readLine();
//		System.out.println(isPalindrome(input) ? "YES!!" : "NO!!");
	
		for(int i = 0; i < input.length(); i++) {
			String partial = input.substring(i);
			
			if(isPalindrome(partial)) {
				ans = input.length() + i;
				break;
			}
		}
		
		System.out.println(ans);
	
	}

}
