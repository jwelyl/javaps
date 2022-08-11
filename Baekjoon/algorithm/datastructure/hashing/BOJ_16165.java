import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_16165 {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Map<String, String> memberToGroup = new HashMap<String, String>();
	static Map<String, ArrayList<String>> groupToMembers = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] input = bf.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		for(int i = 0; i < N; i++) {
			String groupName = bf.readLine();
			int num = Integer.parseInt(bf.readLine());
			
			groupToMembers.put(groupName, new ArrayList<String>());
		
			for(int j = 0; j < num; j++) {
				String memberName = bf.readLine();
				memberToGroup.put(memberName, groupName);
				groupToMembers.get(groupName).add(memberName);
			}
		}
		
		for(int i = 0; i < M; i++) {
			String name = bf.readLine();
			int quiz = Integer.parseInt(bf.readLine());
			
			if(quiz == 0) {
				List<String> list = groupToMembers.get(name);
				Collections.sort(list);
				
				for(String s : list)
					sb.append(s).append("\n");
			}
			else if(quiz == 1) {
				sb.append(memberToGroup.get(name)).append("\n");
			}
		}

		System.out.println(sb);
	}

}
