import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1431 {
	static int N;
	static String[] serials;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		N = Integer.parseInt(bf.readLine());
		serials = new String[N];
		
		for(int i = 0; i < N; i++) {
			serials[i] = bf.readLine();
		}
		
		Arrays.sort(serials, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				// TODO Auto-generated method stub
				int ret = Integer.compare(s1.length(), s2.length());
				
				if(ret == 0) {
					int s1Sum = 0, s2Sum = 0;
					
					for(int i = 0; i < s1.length(); i++) {
						char ch1 = s1.charAt(i);
						char ch2 = s2.charAt(i);
						
						if('0' <= ch1 && ch1 <= '9')
							s1Sum += (ch1 - '0');
						if('0' <= ch2 && ch2 <= '9')
							s2Sum += (ch2 - '0');
					}
					
					ret = Integer.compare(s1Sum, s2Sum);
					
					if(ret == 0) {
						ret = s1.compareTo(s2);
					}
				}
				return ret;
			}
		});
		
		for(String s : serials)
			System.out.println(s);

	}

}