import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1620 {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Map<Integer, String> numToName = new HashMap<>();
	static Map<String, Integer> nameToNum = new HashMap<>();
	
;	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] input = bf.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
	
		for(int i = 1; i <= N; i++) {
			String name = bf.readLine();
			numToName.put(i, name);
			nameToNum.put(name, i);
		}
		
		for(int i = 1; i <= M; i++) {
			String query = bf.readLine();
			char ch = query.charAt(0);
			
			if('0' <= ch && ch <= '9') {
				int num = Integer.parseInt(query);
				sb.append(numToName.get(num)).append("\n");
			}
			else {
				sb.append(nameToNum.get(query)).append("\n");
			}
		}

		System.out.println(sb);
	}	

}
