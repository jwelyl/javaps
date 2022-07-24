import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.StringReader;

public class Q2442 {
	static int N;
//	static String input = "5\n";
	static BufferedReader bfStandard;
	static BufferedReader bfString;
//	static File file = new File("Q2442.txt");
//	static BufferedReader bfFile; 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		bfStandard = new BufferedReader(new InputStreamReader(System.in));
//		bfString = new BufferedReader(new StringReader(input));
//		bfFile = new BufferedReader(new FileReader(file));
	
		N = Integer.parseInt(bfStandard.readLine());
//		N = Integer.parseInt(bfString.readLine());
//		N = Integer.parseInt(bfFile.readLine());
	
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < N - i; j++)
				sb.append(" ");
			for(int j = 0; j < 2 * i - 1; j++)
				sb.append("*");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	
	}
}
