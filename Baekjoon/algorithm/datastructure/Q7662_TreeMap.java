import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static TreeMap<Integer, Integer> numMap;
	static int T, K;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(bf.readLine());

		for (int t = 0; t < T; t++) {
			numMap = new TreeMap<>();

			K = Integer.parseInt(bf.readLine());

			for (int k = 0; k < K; k++) {
				char cmd;
				int num;
				tokens = new StringTokenizer(bf.readLine());

				cmd = tokens.nextToken().charAt(0);
				num = Integer.parseInt(tokens.nextToken());

				if (cmd == 'I') {
					numMap.put(num, numMap.getOrDefault(num, 0) + 1);
//					System.out.println(numMap);
				} else if (cmd == 'D') {
					if (numMap.size() == 0)
						continue;

					int outNum = (num == -1) ? numMap.firstKey() : numMap.lastKey();
					int cnt = numMap.get(outNum);
					
					if(cnt == 1)
						numMap.remove(outNum);
					else
						numMap.put(outNum, --cnt);
//					System.out.println(numMap);
				} 
			} // for command
			
			if(numMap.isEmpty())
				System.out.println("EMPTY");
			else
				System.out.println(numMap.lastKey() + " " + numMap.firstKey());
		} // for testcase
	} // main
}
