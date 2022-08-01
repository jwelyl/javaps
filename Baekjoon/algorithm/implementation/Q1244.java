import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static boolean[] switches;
	static int[] sex;
	static int[] num;
	
	static void print() {
		for(int i = 1; i <= N; i++) {
			if(switches[i])
				sb.append(1);
			else
				sb.append(0);
			
			if(i % 20 == 0)
				sb.append("\n");
			else
				sb.append(" ");
		}
		
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		switches = new boolean[N + 1];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(tokens.nextToken());
			switches[i] = (num == 1) ? true : false;
		}
		
		M = Integer.parseInt(bf.readLine());
		sex = new int[M + 1];
		num = new int[M + 1];
		
		for(int i = 1; i <= M; i++) {
			tokens = new StringTokenizer(bf.readLine());
			sex[i] = Integer.parseInt(tokens.nextToken());
			num[i] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int i = 1; i <= M; i++) {
			if(sex[i] == 1) {	//	남자일 경우
				for(int j = 1; j <= N; ) {
					if(j % num[i] == 0) {
						switches[j] = !switches[j];
						j += num[i];
					}
					else j++;
				}
			}
			else {
				switches[num[i]] = !switches[num[i]];
				
//				for(int j = 1; num[i] + j <= N && num[i] + j > 0;) {
//					if(switches[num[i] + j] != switches[num[i] - j])
//						break;
//					switches[num[i] + j] = !switches[num[i] + j];
//					switches[num[i] - j] = !switches[num[i] - j];
//					j++;
//				}
				
				for(int j = 1; ;) {
					int left = num[i] - j;
					int right = num[i] + j;
					
					if(1 <= left && right <= N && switches[left] == switches[right]) {
						switches[left] = !switches[left];
						switches[right] = !switches[right];
						j++;
					}
					else break;
				}
			}
		}
		print();
	}
}
