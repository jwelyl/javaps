import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_7785 {
	static int N;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static Set<String> set = new HashSet<String>();
//	static Set<String> set = new TreeSet<String>(new Comparator<String>() {
//
//		@Override
//		public int compare(String s1, String s2) {
//			// TODO Auto-generated method stub
//			return -s1.compareTo(s2);
//		}
//		
//	});
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < N; i++) {
			String name, io;
			
			tokens = new StringTokenizer(bf.readLine());
			
			name = tokens.nextToken();
			io = tokens.nextToken();
			
			if(io.equals("enter"))
				set.add(name);
			else if(io.equals("leave")) {
				if(set.contains(name))
					set.remove(name);
			}
		}
	
//		for(String s : set) {
//			sb.append(s).append("\n");
//		}
		List<String> list = new ArrayList<String>();
		Iterator<String> iter = set.iterator();
		
		while(iter.hasNext())
			list.add(iter.next());
		
		Collections.sort(list, Collections.reverseOrder());
		for(String s: list)
			sb.append(s).append("\n");
		
		System.out.println(sb);
	}
}
