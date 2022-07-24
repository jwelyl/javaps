import java.util.Scanner;

public class Q10995 {
	static int N;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	static String deleteTrailingSpaces(String str) {
		int end = str.length() - 1;
		
		while(str.charAt(end) == ' ')
			end--;
		return str.substring(0, end + 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		N = sc.nextInt();
		
		for(int i = 1; i <= N; i++) {
			if(i % 2 == 0) {
				for(int j = 0; j < N; j++)
					sb.append(" *");
				sb.append("\n");
			}
			else {
				for(int j = 0; j < N; j++)
					sb.append("* ");
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
