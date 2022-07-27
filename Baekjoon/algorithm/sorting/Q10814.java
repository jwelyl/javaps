import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Member {
		int age;
		String name;
		int order;
		
		Member(int age, String name, int order) {
			this.age = age;
			this.name = name;
			this.order = order;
		}
		
		@Override
		public String toString() {
			return age + " " + name;
		}
	}
	
	static List<Member> members = new ArrayList<Member>();
	static int N;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < N; i++) {
			int age;
			String name;
			tokens = new StringTokenizer(bf.readLine());
			
			age = Integer.parseInt(tokens.nextToken());
			name = tokens.nextToken();
			
			members.add(new Member(age, name, i));
		}
		
		Collections.sort(members, new Comparator<Member>() {

			@Override
			public int compare(Member m1, Member m2) {
				// TODO Auto-generated method stub
				if(m1.age == m2.age)
					return Integer.compare(m1.order, m2.order);
				return Integer.compare(m1.age, m2.age);
			}
		});
		
		for(Member m : members)
			System.out.println(m);
	}

}
