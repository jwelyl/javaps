import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N, M, ans = 0;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static Set<String> stringSet = new HashSet<String>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String line = bf.readLine();
		String[] inputs = line.split(" ");
		N = Integer.parseInt(inputs[0]);	M = Integer.parseInt(inputs[1]);

		for(int i = 0; i < N; i++) {
			line = bf.readLine();
			stringSet.add(line);
		}
		
		for(int i = 0; i < M; i++) {
			line = bf.readLine();
			if(stringSet.contains(line))
				ans++;
		}
		
		System.out.println(ans);
	}

}
