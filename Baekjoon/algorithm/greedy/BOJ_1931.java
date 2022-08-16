import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly 
 * @performance KB   ms
 * @category # 
 * @memo 
 * @etc 
 * 
 */

public class BOJ_1931 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int lastStart = -1, lastEnd = -1;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		meetings = new Meeting[N];
		for(int i = 0; i < N; i++) {
			int start, end;
			tokens = new StringTokenizer(bf.readLine());
			
			start = Integer.parseInt(tokens.nextToken());
			end = Integer.parseInt(tokens.nextToken());
		
			meetings[i] = new Meeting(start, end);
		}
		
		Arrays.sort(meetings);
		
		for(int i = 0; i < N; i++) {
			Meeting meeting = meetings[i];
			int start = meeting.start;
			int end = meeting.end;
			
			if(start >= lastEnd) {
				lastStart = start;
				lastEnd = end;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;
		
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting m) {
			// TODO Auto-generated method stub
			int ret = Integer.compare(this.end, m.end);
			
			if(ret == 0)
				ret = Integer.compare(this.start, m.end);
			
			return ret;
		}
	}
	
	static Meeting[] meetings;
}
